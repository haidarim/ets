#!/bin/bash

# This script is useful to build docker images, run then and execute them in containers
# Also useful for testign enpoints i.e. APIs provided by back-end 
#
#



HDIR="."
FDIR="orelease-etc-frontend/er-to-code"
BDIR="orelease-etc-backend"
CONFDIR="orelease-etc-config"
DBDIR="orelease-etc-database/postgres"
VERSION="0.0.1"

REPO="haidarim/etc"
BNAME="backend"
FNAME="frontend"
DBNAME="db"

post_new_user() {
    curl -i -v -X POST \
        "http://localhost:8080/api0/sign-up" \
        -H "Content-Type: application/json" \
        -d '{"username": "test_user", "password": "123456789", "email": "user@user.user"}'
}

login() {
    curl -s -v -X POST \
        "http://localhost:8080/api0/sign-in" \
        -H "Content-Type: application/json" \
        -d '{"username": "test_user", "password": "123456789"}'
}

send_wrong_user(){
    curl -i -v -X POST \
        "http://localhost:8080/api0/sign-in" \
        -H "Content-Type: application/json" \
        -d '{"username": "test_user", "password": "123456780"}'
}

get_project(){
    local token="$1"
    local id="$2"
    echo token is: $token
    echo id is: $id
    curl -v -X GET \
        "http://localhost:8080/api0/project/$id" \
        -H "Authorization: Bearer $token"
}

push(){
	local img="$1"
	docker tag "$img":"$VERSION" "$REPO":"$img""-v""$VERSION"
	docker rmi "$img":"$VERSION"
	docker push "$REPO":"$img""-v""$VERSION"
}

if [ "$#" -eq 0 ]; then
    echo "args required!"
elif [ "$1" == "post" ]; then
    if [ "$2" == "-nU" ]; then
        post_new_user
    elif [ "$2" == "-login" ]; then
        login
    elif [ "$2" == "-wronguser" ]; then
        send_wrong_user
    fi
elif  [ "$1" == "get" ]; then 
    if [ "$2" == "-project" ]; then
        response=$(login)
        token=$(echo $response | jq -r '.token')
        id=$(echo $response | jq -r '.id')
        
        if [ -z "$token" ] || [ "$token" == "null" ]; then
            echo "Failed to obtain token. Response: $response"
        else
            get_project "$token" "$id"
        fi
    fi
elif [ "$1" == "build" ];then
    if [ "$2" == "backend" ]; then
    	docker build -t "$BNAME":"$VERSION" "$BDIR/"
    elif [ "$2" == "db" ]; then
		export $(grep -v '^#' "$CONFDIR/.env"| xargs) 
		docker build \
  			--build-arg POSTGRES_DB=$POSTGRES_DB \
  			--build-arg POSTGRES_USER=$POSTGRES_USER \
  			--build-arg POSTGRES_PASSWORD=$POSTGRES_PASSWORD \
  			-t "$DBNAME":"$VERSION" "$DBDIR/"
		push "$DBNAME"
    elif [ "$2" == "frontend" ]; then
		docker build -t "$FNAME":"$VERSION" "$FDIR/"
	   	push "$FNAME"	
    fi	    
elif [ "$1" == "run" ]; then
    if [ "$2" == "backend" ]; then
        # docker run --name "$BNAME" --rm -d --network "$BNAME":"$VERSION"
		# TODO
		echo todo 
    elif [ "$2" == "frontend" ]; then
        docker run --name "$FNAME" --rm -d --network host "$REPO":"$FNAME""-v""$VERSION"
    elif [ "$2" == "db" ]; then
		export $(grep -v '^#' "$CONFDIR/.env"| xargs)
        docker run --name "$DBNAME" --rm -d \
            -p 5432:5432 \
            --env-file "$CONFDIR/.env" "$REPO":"$DBNAME""-v""$VERSION"
    fi
elif [ "$1" == "exec" ] && [ "$2" == "db" ]; then
    export $(grep -v '^#' "$CONFDIR/.env"| xargs)
    docker exec -it "$DBNAME"  psql -U $POSTGRES_USER -d $POSTGRES_DB
else
    echo "bad args"
fi
