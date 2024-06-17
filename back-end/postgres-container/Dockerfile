# Stage 1: Build stage/layer 1
FROM postgres:14 AS builder

# Set environment variables directly in the Dockerfile
ENV POSTGRES_DB=testdb
ENV POSTGRES_USER=dev_user
ENV POSTGRES_PASSWORD=010101

# Copy the template to the container
COPY init.sql.template /init.sql.template

# Substitute environment variables in init.sql.template using sed
RUN sed -i "s|\${POSTGRES_DB}|$POSTGRES_DB|g" /init.sql.template && \
    sed -i "s|\${POSTGRES_USER}|$POSTGRES_USER|g" /init.sql.template && \
    sed -i "s|\${POSTGRES_PASSWORD}|$POSTGRES_PASSWORD|g" /init.sql.template

# Stage 2/layer 2: Final stage with PostgreSQL image
FROM postgres:14

EXPOSE 5432

# Copy init.sql from builder stage/layer
COPY --from=builder /init.sql.template /docker-entrypoint-initdb.d/init.sql
