# Quick intro to docker: 

## Dockerfile: 
A Dockerfile is a text file that contains a series of instructions (commands) that Docker uses to assemble an image. These instructions define the environment inside the container, such as setting the base image, copying files, installing dependencies, and configuring how the container should run.

Example: 
```Dockerfile 
# Use an official Python runtime as a parent image
FROM python:3.8-slim

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Install any needed packages specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Make port 80 available to the world outside this container
EXPOSE 80

# Define environment variable
ENV NAME World

# Run app.py when the container launches
CMD ["python", "app.py"]
```

## Layers: 
Each instruction in a Dockerfile creates a new layer in the image. Layers are stacked, and each one is a delta of the previous layer. This makes Docker images lightweight, as layers can be reused across different images, reducing redundancy and saving space.

## RUN vs CMD vs ENTRYPOINT 
**RUN:**
- Used to execute a command during the image build process.
- Typically used to install software packages.

**CMD:**
- Sets default command and/or parameters for an executing container.
- One CMD per Image. 
- Can be overridden by any command line arguments provided at docker run.

**ENTRYPOINT:**
- Configures a container that will run as an executable.
- Can not be overridden by command line arguments, but can be combined with CMD to provide default arguments.

**CMD vs ENTRYPOINT:**
- CMD: Provides defaults for an executing container.It can be overridden with command line arguments, while ENTRYPOINT cannot be fully overridden but can have additional parameters passed to it.
Example: 
```Dockerfile
CMD ["echo", "Hello!"]
```
- ENTRYPOINT: Configures a container to run as an executable.
Example: 
```Dockerfile
ENTRYPOINT ["python", "app.py"]
```

## EXPOSE vs PORT
- expose: internal between other containers. 
Example: 
```Dockerfile
EXPOSE 8080
```
- port (port x:y): external 
Example:
```bash
docker run -p <8080:80 my-image>
```

## Build the image 
to build the image use the command below:
```bash
sudo docker build -t <image_name>:tag <path_to_the_dockerfile.txt>
```
the `-t` is used to tag the image with a name and/or a tag i.e. version nr.

## .dockerignore
to prevent copying  specified files into the container 
Example: 
```bash
node_modules
*.log
*.md
```

## Listing all images: 
```bash
docker images
```

## remove an image
```bash
docker rmi <imageName>
```

## running 
When the image is build, we can wun our image in a conotainer using `docker run command`. 

**To assign a name to the conatiner:**
```bash
docekr run --name <nameOdContainer> <imageName>
```

**Other switches used with `docker run`:**
- `-d`: used to run the container as background process 
- `--ernv-file <path-to-env-file>`: To specify the environment variables needed for to running the conatiner. 

- `-p` for forwarding containers port, local:container 

- `--rm`: to say ti the docker that rm the container after running it. 


## executing commands inside the running container:
`docker exec` is a command in Docker that allows you to run a command inside a running Docker container. 

Example1: 
```bash
docker exec -it <containerName> bash

```

Example running the psql in a specific databse.
```bash
docker exec -it <containerNameOrID> psql -U <user> -d <databseName>
```

## Listing all containers 
To list all containers: 
```bash
docker ps -a
```

## Listing all running container 
To list all running containers: 
```bash
docker ps
```

## stoping a container 
To stop a container:
```bash
docker stop <container_id>
```
## removing a container 
```bash
docker rm <container_id>
```
To remove all containers:
```bash
docker container prune
```





## Choosing a specific layer 
While building an image, you can target a specific layer for testing using the --target flag with docker build. This is useful for multi-stage builds.

```Dockerfile
# syntax=docker/dockerfile:1

FROM node:12 AS builder
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=builder /app/build /usr/share/nginx/html

```
Build the first stage only:

```bash
docker build --target builder -t myapp:builder .
```

## Debug in Dockerfile 
To aviod in Dockerfile:
- latest:  as it can lead to unexpected version changes.





## Network types in Docker: 
- Bridge (default): used to estabilish internal communication with other containers. If the type of network is not specified the default will be this type. 
Example:
```bash
docker network create --driver bridge my_bridge_network
```

- Host Network: To make a network directly to the host, The container shares the host's network stack.
```bash
docker run --network host myapp
```

- Overlay network: Enables multi-host communication for Docker Swarm services.
Example: 
```bash
docker network create -d overlay my_overlay_network

```

- Macvian Network: Assigns a MAC address to each container, making it appear as a physical device on the network.

Example: 
```bash
docker network create -d macvlan \
    --subnet=xxx.xxx.x.x \
    --gateway=yy.yy.y.y \
    -o parent=eth0 my_macvlan_network

```

- IPvlan Network: Similar to Macvlan but uses routing instead of bridging.

Example: 
```bash
docker network create -d ipvlan \
    --subnet=192.168.1.0/24 \
    --gateway=192.168.1.1 \
    -o parent=eth0 my_ipvlan_network

```

- None Network: Disables networking for the container.
Example: 

```bash
docker run --network none myapp
```


- Custom Networks: Allows the creation of user-defined networks for more control over the network configuration.

```bash
docker network create my_custom_network
docker run --network my_custom_network myapp
```



## Containers' logs: 

```bash
docker logs <nameOrContainerID>
```


