#!/bin/bash

current_dir=$(dirname "$(readlink -f "$0")")

post_new_user() {
    curl -i -X POST \
        "http://localhost:8080/api0/sign-up" \
        -H "Content-Type: application/json" \
        -d '{"username": "test_user", "password": "123456789", "email": "user@user.user"}'
}

sign_in() {
    curl -i -X POST \
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



if [ "$#" -eq 0 ]; then
    echo "args required!"
elif [ "$1" == "post" ]; then
    if [ "$2" == "-nU" ]; then
        post_new_user
    elif [ "$2" == "-u" ]; then
       # post_new_user
       # echo -e "----------> created new user <--------\n\n\n\n"  # Use -e to enable interpretation of backslash escapes
        sign_in
    elif [ "$2" == "-bU" ]; then
	send_wrong_user
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
            --env-file $current_dir/back-end/postgres-container/.env ets_db:latest
    fi
elif [ "$1" == "exec" ] && [ "$2" == "db" ]; then
    docker exec -it db psql -U dev_user -d testdb
else
    echo "bad args"
fi
