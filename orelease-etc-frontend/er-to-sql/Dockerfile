
# Base Image i.e. the image that the app will be based on 
FROM node:14

# The work directory inside the container 
WORKDIR /er-to-sql-front


# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# install dependencies in the container 
RUN npm install 

# Copy files from the current directory in local machine into docker-container work directory i.e. er-to-sql-front
COPY . .

# Expose the port the app runs on 
EXPOSE 3000

# finally run the application 
CMD ["npm", "start"]




