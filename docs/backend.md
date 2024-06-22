

## Spring boot: 
Used libraries/tools: 
- 
-
 
### Layers:

#### Controller: 
Handles HTTP requests, delegates work to services, and manages responses.
mapping, get, post ,...

#### Service:
Implements business logic and orchestrates repository operations.

#### Model: 
Defines data structures (entities) used throughout the application.

#### Data access: 
Responsible for database interactions (Create, Read, Update, Delete (CRUD) operations).


### application properties file: 
The application.properties file is a configuration file used in Spring Boot applications to define properties and settings for the application. 

Purpose of application.properties

1. Configuration Management: It provides a centralized place to configure properties such as database connection details, server port, logging levels, etc.

2. Profile Management: Spring Boot supports different profiles (application-{profile}.properties or application-{profile}.yml) to manage configurations for different environments (e.g., development, testing, production).

3. Externalized Configuration: Helps in separating configuration from code, promoting easier maintenance, and facilitating environment-specific configuration.

**Some configurations and concepts used in `application.properties`:**
1. Dependency Injection (DI):
```properties
# Example of configuring a bean using @Bean annotation in a configuration class
myBean.name=John
myBean.age=30
```
2. Aspect-Oriented Programming (AOP): 
```properties
# Example of configuring logging aspect
logging.level.com.example=DEBUG
```
3. Spring Data: for data management i.e. database
```properties
# Example of configuring Spring Data JPA with PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
spring.datasource.username=myusername
spring.datasource.password=mypassword
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

4. Hibernate properties: Hibernate is popular Object-Relational Mapping (ORM) framework that simplifies interaction between Java application objects and relational databases. 
**JPA:** jpa == Java Persistence API, managing data between java objects and RDB in application
```properties
# Hibernate properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

5. Spring Boot: application configs e.g.  port
```properties
# Example of basic Spring Boot configuration
server.port=8080
spring.application.name=my-application
spring.profiles.active=dev
```

6. Spring Security: 
```properties
# Example of configuring basic authentication in Spring Security
spring.security.user.name=user
spring.security.user.password=password
spring.security.user.roles=USER

```

there are more configuration that can be founded in the spring docs. 

### Lombok: 
Lombok is a Java library that helps reduce boilerplate code in Java classes. It provides annotations to automatically generate methods such as getters, setters, constructors, toString, equals, and hashCode methods at compile time. This reduces the amount of repetitive code that developers need to write, thereby improving code readability and maintainability.

To use Lombok in a class import it `lombok.Data`, and then annotate the class with `@Data`


