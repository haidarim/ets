#!/bin/bash

current_dir=$(dirname "$(readlink -f "$0")")

post_new_user() {
    curl -i -X POST \
        "http://localhost:8080/api0/sign-up" \
        -H "Content-Type: application/json" \
        -d '{"username": "test_user", "password": "123456789", "email": "user@user.user"}'
}

sign_in() {
    curl -s -X POST \
        "http://localhost:8080/api0/sign-in" \
        -H "Content-Type: application/json" \
        -d '{"username": "test_user", "password": "123456789"}'
}

send_wrong_user(){
    curl -i -X POST \
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

if [ "$#" -eq 0 ]; then
    echo "args required!"
elif [ "$1" == "post" ]; then
    if [ "$2" == "-nU" ]; then
        post_new_user
    elif [ "$2" == "-u" ]; then
        sign_in
    elif [ "$2" == "-bU" ]; then
        send_wrong_user
    elif [ "$2" == "--get-project" ]; then
        response=$(sign_in)
        token=$(echo $response | jq -r '.token')
        id=$(echo $response | jq -r '.id')
        
        if [ -z "$token" ] || [ "$token" == "null" ]; then
            echo "Failed to obtain token. Response: $response"
        else
            get_project "$token" "$id"
        fi
    fi
elif [ "$1" == "run" ]; then
    if [ "$2" == "back" ]; then
        # TODO: IMPLEMENT FOR RUNNING THE BACKEND
        :
    elif [ "$2" == "front" ]; then
        # TODO: IMPLEMENT FOR RUNNING THE FRONT END
        :
    elif [ "$2" == "db" ]; then
        docker run --name db --rm -d \
            --network host \
            --env-file "$current_dir/back-end/postgres-container/.env" ets_db:latest
    fi
elif [ "$1" == "exec" ] && [ "$2" == "db" ]; then
    docker exec -it db psql -U dev_user -d testdb
else
    echo "bad args"
fi
