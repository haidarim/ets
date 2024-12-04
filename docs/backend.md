

## Spring boot: 
Used libraries/tools: 
- 
-
 
### Layers:

#### Controller: 
A controller in a Spring Boot application handles HTTP requests, delegates work to services, and manages responses. The controller class is annotated with `@RestController`, indicating that it is a web controller and its methods return JSON responses directly. Methods in a controller class can be annotated with different annotations based on the request method type, such as `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, etc.

The controller classes can also be annotated with `@RequestMapping` to specify the base path for all request mappings in that controller. This helps in organizing and managing different endpoints under a common path.

**Service in Router/Controller Layer:** The service used for the controller class will be defined as a private variable and annotated with `@Autowired`. 
This annotation will inject service bean into the controller. Similar to NodeJs where we import the service in the controller module but the key difference is that in spring boot the `@Autowired` does kind of dependency injection while in NodeJs we do export-and-import.  

**Reading variables from URL:** When a client sends an HTTP request, such as a GET or DELETE request, with a URL that contains dynamic segments (path variables), Spring MVC allows you to define mappings that capture these segments and pass them as arguments to your controller methods using `@PathVariable`. So we can read a value from URL, This annotation being defined before a parameter in a method (method parameter annotation).for example: 
```java
@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);
    if (user != null) {
        return ResponseEntity.ok(user);
    } else {
        return ResponseEntity.notFound().build();
    }
}
```

**Reading request's body:** The `@RequestBody` annotation in Spring (including Spring MVC and Spring Boot) is used to bind the body of the HTTP request to a Java object in the controller method. This allows to extract data that's sent in the request body and use it within the back-end application. This annotation also being defined before a parameter in a controller method.

Example: 
```Java
@PostMapping
public ResponseEntity<User> createUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);
    return ResponseEntity.ok(createdUser);
}
```

**Sending response:** `ResponseEntity<T>` in Spring (including Spring MVC and Spring Boot) is used to handle HTTP responses and send responses back to the client (typically the front-end side in a web application). It encapsulates the entire HTTP response, including status code, headers, and body.

Example: 
```Java
@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);
    if (user != null) {
        return ResponseEntity.ok(user); // HTTP 200 OK with user object in the body
    } else {
        return ResponseEntity.notFound().build(); // HTTP 404 Not Found
    }
}
```

Example of a controller class in Spring Boot:
```Java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Handle GET requests to /users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Handle GET requests to /users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Handle POST requests to /users
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Handle PUT requests to /users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Handle DELETE requests to /users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

```

#### Service Layer (Business Logic): 
Implements business logic and orchestrates repository operations. This layer sits between the controller (presentation layer) and the repository (data access layer), and it is responsible for implementing the application's business rules and logic. We can bring the repository by DI using `@Autowired`
annotation. 

**Transactional Methods (@Transactional):**
- Read operations marked as @Transactional(readOnly = true).
- Write operations that manage transactions (@Transactional ensures atomicity).

Example: 
```Java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User createUser(User user) {
        // Business logic for user creation (e.g., validation, setting defaults)
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            // Update user properties based on business logic
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            // Save updated user
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
```

#### Model: 
Defines data structures (entities) used throughout the application. The Entity class can have attributes such as data posted by user as well as attributes that being used only in the back-end and attributes that being used in both front and back-end. The model/entity class being annotated with `@Entity` and can use other annotation for the class as well as class's variables. 


Example: 
```Java
// imports such as jakarta for entity and table definition, lmbok to generate constructor and getter-setter, etc. 

@Table(name="tasks")
@Entity 
@Data
public class Task {
    @Id // to mark the id of the entity (primary key) 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String taskName;
    private String taskPriority;
    private String status;
    //...
}
```
**@GeneratedValue:** used to configure id value generation. 
1. IDENTITY: `@GeneratedValue(strategy = GenerationType.IDENTITY)`: The id being generated by DBMS. 
2. SEQUENCE:  
```Java 
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
```
- The `@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")` annotation specifies that the id value will be generated using a sequence named user_seq.
- The `@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)` annotation defines the sequence generator.




#### Data access: 
Responsible for database interactions (Create, Read, Update, Delete (CRUD) operations). 
A repository in Spring boot is an `interface` which `extends` the `JpaRespository<EntityName, IdtypeOfEntity>`. 
The repository class being annotated with `@Repository`. This interface can be either empty and only use the JPA's CRUID or for customization on queries defining methods/queries. 
Example 1: 
```Java

// imports, such as the entity for repositoty, JpaRepository, etc. 

public interafce extends JpaRepository<User, Long>{
    // Additional custom query methods can be defined here if needed
}

```

Example 2: 
```Java
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
```

Example 3: 
```Java
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    List<User> findByEmail(@Param("email") String email);
}
```


### Configuration: 
The application configs can be defined in a class that in turn being annotated by `@Configuration`

Example for CORS:
```Java
@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") 
            .allowedOrigins("*") // Allow all origins
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP verbs
            .allowedHeaders("*"); // Allow all headers
}

```

### Asynchronous processes in Spring Boot: 
Spring Boot supports asynchronous processing, which allows to define asynchronous services and call them asynchronously, similar to  async and await in Node.js. The asynchronous process can be used in Controller, Service and Repository (Router, Service, Data access layers). Using asynchronous process makes the application more responsive.

In the context of asynchronous methods and calling them from another thread, this can be done in different ways using various techniques. A method can be defined as `asynchronous` and then called by other threads. Caller threads can invoke these methods and wait for their completion before continuing with other tasks in a code block. This can result in either blocking the entire thread or just pausing the execution of the specific code block where the async method is called.

In Node.js, using `await` pauses only the execution of the function in which `await` is called until the asynchronous operation completes. The rest of the code block within that function will resume execution afterward. However, this does not block the entire thread running the application. Node.js operates on a single-threaded event loop, allowing the application to continue processing other events, such as handling new incoming requests, while the async function is paused.

In Spring Boot, calling `CompletableFuture.get()` will block the calling thread until the asynchronous operation completes. This means the entire thread will be paused while waiting for the result. To avoid blocking,  use non-blocking methods like `thenApply`, `thenAccept`, and similar methods, which allow you to handle the result of the future without blocking the calling thread.

Example in Node: In the example below, the second await call will not be executed until the first call `await fetch('/url://')` is returned, as well as rest of the code sequence will be executed after second call returned. 
```javascirpt
async function getDataAsync() {
  try {
    const res = await fetch('/url://');
    const resBody = await response.body();
    console.log(resBody);
  } catch (err) {
    console.log('Error:' + err.message);
  }
}
```

Example in Spring boot: The code after `.thenAccept(...)` will be executed after the task is performed by the service, and the code sequence in the method is paused, but not entire the thread!
```Java
@GetMapping("/async")
public ResponseEntity<String> asyncEndpoint() {
    CompletableFuture<String> futureResult = myService.asyncOperation();

    futureResult.thenAccept(result -> {
        System.out.println("Async operation result: " + result);
        // Further processing if needed
    });

    return ResponseEntity.ok("Request received, processing asynchronously");
}
```

**Reader/Writer problem:**


To enable asynchronous process in the Spring boot application we  need:
- Configuration for asynchronous process.
- Marking methods for asynchronous. 
- Using methods with `thenApply`


#### Configuration: 
The configuration can be either in its own class, or in same config class as e.g. CORS, and the config class should also include the `@EnableAsync` annotation. But for better code (cleaner code) and to practice the single responsibility principle (SRP) is it preferred to be in it own configuration class. 

In the config calss (@Configuration) for the asynchronous process, we can define thread-pooling mechanism using `@Bean(name = "nameOfTheFactoryMethod")` annotation, and then implementing thread-pool mechanism in the factory method (the method annotated with @Bean is a factory method which returns an object that later being managed by Spring Inversion of Control, IoC, as a bean the configuration).

Example: using ThreadPoolTaskExecutor as the bean configuration for asynchronous process configuration in Spring boot application. 
```Java

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize();
        return executor;
    }
}

```

Different types of thread-pooling mechanisms are available for asynchronous process in Spring boot/Java (also known as different kind of ExecutorService). Some of them are as following: 

1. **ThreadPoolTaskExecutor:**: is a robust implementation of ExecutorService provided by Spring Framework. It allows to configure a thread pool with various parameters such as core size, maximum size, queue capacity, and thread names. 
- `setCorePoolSize(int corePoolSize)`: The core pool size determines the minimum number of threads that are kept alive in the pool, even if they are idle. In this case, the pool will always have at least 2 threads running.

- `setMaxPoolSize(int max)`: The maximum pool size is the limit on the number of threads that can be created in the pool. When the pool reaches this size, it cannot create additional threads and will start queuing tasks if the queue is not full.

- `setQueueCapacity(int cap)`: he queue capacity determines how many tasks can be queued before new tasks are rejected or handled by a rejection policy. 
- `setThreadNamePrefix("AsyncThread-")`:Thread name prefixes help in identifying and debugging threads. 

Note: if the number of active threads is below the core pool size and the queue is not full, the task will definitely be executed immediately by creating a new thread. The queue is used primarily when the number of active threads is equal to or greater than the core pool size.

This mechanism is recommended for general-purpose asynchronous task execution in Spring applications where tasks can be submitted for parallel execution.

2. **ScheduledThreadPoolExecutor:** Commonly used for scheduling tasks to run periodically or after a delay.
This mechanism is recommended for scheduled tasks where precise timing and periodic execution are required, often used in backend services or batch processing jobs.

Example: 
```Java
@Configuration
@EnableScheduling
public class ScheduledConfig {

    @Bean(name = "taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("ScheduledThreadPoolExecutor-");
        scheduler.initialize();
        return scheduler;
    }
}
```


3. **ForkJoinPool:** Useful for parallel processing and divide-and-conquer algorithms.

Example: 
```java
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Bean(name = "forkJoinPool")
    public Executor forkJoinPool() {
        return new ForkJoinPool();
    }
}
```

#### Marking methods for asynchronous:
Asynchronous methods in Spring boot being annotate with `@Async("NameOfTheThreadPoolBean")`. These methods can return `CompletableFuture<T>` which is a type of `Future` similar to `Promise` in node. A Future in java is an interface representing the result of an asynchronous computation. 

Example: 
```java

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Boolean> updateUser(User user) {
        return repository.findByUsername(user.getUsername())
                .thenApply(existingOpt -> {
                    if (existingOpt.isPresent()) {
                        User existing = existingOpt.get();
                        existing.setUsername(user.getUsername());
                        existing.setEmail(user.getEmail());
                        existing.setPassword(user.getPassword());
                        repository.save(existing);
                        return true;
                    }
                    return false;
                });
    }

}

```

... 

#### Using async methods in Controller: 
Example: 
```java
@PutMapping
public CompletableFuture<ResponseEntity<Void>> updateUser(@RequestBody User user) {
    return service.updateUser(user)
            .thenApply(updated -> {
                if (updated) {
                    return ResponseEntity.status(HttpStatus.OK).build();
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            });
    }

```

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

### Web Server Thread Pool Management: **TODO**

### Lombok: 
Lombok is a Java library that helps reduce boilerplate code in Java classes. It provides annotations to automatically generate methods such as getters, setters, constructors, toString, equals, and hashCode methods at compile time. This reduces the amount of repetitive code that developers need to write, thereby improving code readability and maintainability.

To use Lombok in a class import it `lombok.Data`, and then annotate the class with `@Data`





















...
It is important to think about reader/writer pwoblem using asynchronous process! while there will not be deadlock since using asynch structure, then in node the thread going off and in java an thread pool takes care of scheduling, but when it comed to race conddition or data inconstence, multi reader can read at the same time bit, multi writers cannot, and even writer and reader cannot attempt to the resource at same time

i use syncronization for those methods that reders and writers both share with each other to mark the critical section, and those methods should not be asycnh rather synch. in this way i can solve even race condition, and using synch methods deadlock safity is also already considered!

Note that using node the race condition and deadlock are not  problem since the node is single-threaded threads will be accessed to the shared resource in turn, sleep wkae and release. In Spring boot we should take care of  reace condition, and deadlock is not a problem because we remove the hold and wait factor, by having thread-pool that gives each call to the asynch metgod a worker thread from the pool, a thread will not have a resource and wiat for another if the asynch method does't implmmnet hold and wait. 
....




### Security: 

