1. Code base: Single code base (git repo, ...) for the project 

```lua 
master (Production branch)
|-- README
|-- docs/
|-- front-end (front-end work)/
|   |-- src/
|   |-- tests/    
|   |-- Dockerfile
|       
|-- backend/
|   |-- service1 (backend work fot he microservice1)/
|   |-- service2 (backend work fot he microservice2)/   
|
|-- .github/
|   |-- workflows/
|       |-- cicd.yml      # Workflow file for CI/CD
|       |-- other_workflows/  

hotfixes_issues (issues/improvment)
|
|-- fix-name

stage (Staging environment branch/health check before merging to master)
|-- README
|-- docs/
|-- front-end (front-end work)/
|   |-- src/
|   |-- tests/    
|   |-- Dockerfile
|       
|-- backend/
|   |-- service1 (backend work fot he microservice1)/
|   |-- service2 (backend work fot he microservice2)/   
  
```

2. Dependency: The secend part of 12 factor app is Dependency: use virtualenv instead widly insatlling dependencies.

Isolating Dependencies: Each project or application can have its own isolated environment where dependencies are installed. This isolation prevents conflicts between different projects that might require different versions of the same dependency.

Consistency: By using virtual environments within Docker containers, you ensure that the application runs consistently regardless of the host environment. This consistency is crucial for DevOps teams aiming to deploy applications across different infrastructure setups reliably.



3. Configuration: Configurations should be stored in the environment (.env). This means sensitive information like credentials or configuration settings should not be hardcoded in the codebase but instead be passed through environment variables. This approach enhances security, portability, and maintainability of applications.

3.1. Creating environment variable in github secrets:
- Settings --> Secrets--> New repository secret. 
- give name and value to the secret. 

To access the secret in workflow: 
Example: 
```yaml
- name: Build Docker image
        run: docker build . -t myapp --build-arg DATABASE_URL="${{ secrets.SecretName }}"
```

In this example:

- The DATABASE_URL secret is accessed using ${{ secrets.DATABASE_URL }} and passed to the Docker build process using `--build-arg`

3.2. Using Environment Variables in Docker Containers:
In Docker, you can pass environment variables to containers during runtime or build time. Here’s how to handle environment variables in Docker:

- In dockerfile before running
Example: 
```Dockerfile
# Set environment variable
ENV DATABASE_URL="postgresql://username:password@db_host/mydatabase"
```

- During running: 
Example:
```bash
docker run -e DATABASE_URL="postgresql://username:password@db_host/mydatabase" -p 8000:8000 myapp
``` 

4. Backing service: This principle emphasizes treating backing services (like databases, message queues, SMTP servers, etc.) as attached resources that can be accessed via a URL or similar connection details, they can be deatteched and replaced easily. 

5. Build, release, run: writing CI CD, meaning building, releasing and running the application in another environment than the deploying environment.  

6. Processes: The product (application) should be stateless i.e. it should not store users' data, it should not los data

7. Port binding: Having different ports for each service, for example if we have a server main server that listens to three other mini servers, the main server should have three ports, each for a specific mini server. 

8. Concurrency: which focuses on scaling your application horizontally by adding more processes (or instances) to handle increased load, rather than vertically scaling a single process.

9. Disposibility: Writing health check tests before starting server, as well as ensuring that when server goes down it will finish its tasks first and then going to exit. 

10. Dev/Prod Parity: The stage or dev branch should be exaxtly a copy of the production branch to ensure that codes tested in the stage will have same behavior in the production. The stage branch is to test features first before mergin in the production branch. 

11. Log: To make error handling easier, the log should be balanced, not to big, not to simple or hard to understand. After 

12. Admin Prcesses: The DevOps/admin process to run the application. 
