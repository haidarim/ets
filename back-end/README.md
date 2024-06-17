# Running the Back-end
All commands in this README page must be run in the same directory as the README file i.e. `back-end` directory. 

## Prepare the database:
1. Build the image and assign it the name `ets`
```bash
 docker build -t ets_db ./postgres-container/

```

2. Run the the image in a container: 

**NOTE:** make sure that the port 5432 is free by running the commands:
```bash
sudo systemctl stop postgresql.service   
```

Other wise tyou can specify some other port when running the container for the local machine. 

To run the image in backgroung in the container of the name `db` use: 
```bash

docker run --name db --env-file ./postgres-container/.env --rm -d  -p 5432:5432 ets_db 

```

To stop the container: 
```bash
docker stop db 
```
