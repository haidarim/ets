## init file 
The `init.sql.template` file is used to dynamically create a postgres database, user and give access to the user (dev_user). If the database already exists nothing being created. 

This file uses .env file to initialize username and the use's password as well as the database name, i.e. variables. 

The code in this file is Postgres and code bloks as: 
```sql
DO $$
DECLARE
    -- Optional variable declarations
BEGIN
    -- SQL statements and logic here
END $$;
```

## Dockerfile 
The docker file consists of two stages: 
1. Build stage (builder): in this stage the base will be build such as base image (postgres), setting environment variables, and copying the init.sql.template from local machine into the container /init.sql.tempplate

Then using `RUN` we  Substitute environment variables in init.sql.template using sed

2. This stage will copy the /init.sql.template file which have now the varibles into the `/docker-entrypoint-initdb.d/x.sql` and the `x.sql` file then being used by the postgres in the container. 

we specify that `--from builder /init.sql.template` so the docker know wich layer and what to be copied. 

The `docker-entrypoint-initdb.d` directory is a special directory recognized by the PostgreSQL Docker image's entrypoint script.
