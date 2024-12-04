# Stage 1: Build stage/layer 1
FROM postgres:14 AS builder

# Define build arguments
ARG POSTGRES_DB
ARG POSTGRES_USER
ARG POSTGRES_PASSWORD

# Set environment variables using build arguments
ENV POSTGRES_DB=${POSTGRES_DB}
ENV POSTGRES_USER=${POSTGRES_USER}
ENV POSTGRES_PASSWORD=${POSTGRES_PASSWORD}

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

