
# 1️⃣ CORE JAVA (Must-Know)

## Java Basics
- JVM vs JRE vs JDK  
- How Java is platform independent  
- Stack vs Heap memory  
- Pass by value (very common trick question)  
- Primitive vs Wrapper classes  

## OOP (VERY IMPORTANT)
- Encapsulation  
- Inheritance  
- Polymorphism (overloading vs overriding)  
- Abstraction  
- Interface vs Abstract class  
- Composition vs Inheritance  
- IS-A vs HAS-A  
- SOLID principles (basics)

## String (Always Asked)
- String literal vs `new String()`  
- String Pool  
- Why String is immutable  
- `==` vs `equals()`  
- `String` vs `StringBuilder` vs `StringBuffer`  
- `intern()`  
- What happens during String concatenation  
- Why String is a good HashMap key  

## equals() & hashCode()
- Contract rules  
- Why both are required  
- What happens if overridden incorrectly  
- Mutable fields problem  

## Collections Framework (VERY IMPORTANT)
- List vs Set vs Map  
- ArrayList vs LinkedList  
- HashMap internal working  
- HashMap vs ConcurrentHashMap  
- HashSet internal working  
- TreeMap vs HashMap  
- Fail-fast vs Fail-safe  
- Comparator vs Comparable  
- Immutability in collections  

## Exception Handling
- Checked vs Unchecked exceptions  
- try-catch-finally  
- try-with-resources  
- Custom exceptions  
- Best practices  
- Exception vs Error  

## Multithreading & Concurrency (Asked at 4 YOE)
- Thread lifecycle  
- Runnable vs Callable  
- `synchronized` keyword  
- `volatile` keyword  
- ExecutorService  
- Thread pools  
- Future vs CompletableFuture  
- Deadlock & race condition  
- Atomic variables  
- Concurrent collections  

## Java 8+ Features (MANDATORY)
- Lambda expressions  
- Functional interfaces  
- Stream API (filter, map, reduce, collect)  
- Intermediate vs terminal operations  
- Optional  
- Method references  
- Parallel streams (when to avoid)

## JVM Internals (Basic Level)
- Class loading mechanism  
- Garbage Collection basics  
- GC types (G1, Parallel)  
- Memory leaks causes  
- Heap tuning basics  

---

# 2️⃣ SPRING & SPRING BOOT (CRITICAL)

## Spring Core
- What is Spring  
- IOC & Dependency Injection  
- Bean lifecycle  
- `@Component` vs `@Service` vs `@Repository`  
- `@Autowired` (constructor vs field injection)  
- `@Qualifier`  
- Bean scopes (Singleton vs Prototype)  

## Spring Boot Core
- Why Spring Boot  
- Auto-configuration  
- Starter dependencies  
- `application.properties` vs `application.yml`  
- Spring Profiles  
- Embedded Tomcat  
- `@SpringBootApplication`  

## REST APIs (Asked in Every Interview)
- `@RestController`  
- `@RequestMapping` vs `@GetMapping`  
- `@PathVariable` vs `@RequestParam`  
- `@RequestBody`  
- ResponseEntity  
- HTTP methods & status codes  
- Idempotency  
- Pagination & sorting  

## Exception Handling
- `@ControllerAdvice`  
- `@ExceptionHandler`  
- Global vs local exception handling  

## Validation
- `@Valid`  
- `@NotNull`, `@Size`, `@Email`  
- Custom validators  

## Spring Data JPA / Hibernate (VERY IMPORTANT)
- Entity lifecycle  
- `@Entity` vs `@Table`  
- Primary key generation strategies  
- OneToOne, OneToMany, ManyToOne  
- FetchType.LAZY vs EAGER  
- Cascade types  
- JPQL vs Native Query  
- Pagination  
- Transactions  
- `@Transactional`  
- N+1 problem  
- Optimistic vs Pessimistic locking  

## Spring Boot Advanced
- Filters vs Interceptors  
- AOP (what, why, use cases)  
- `@Async`  
- Scheduling (Cron jobs)  
- Caching (Redis, `@Cacheable`)  
- Spring Events  

## Security (Basic to Medium)
- Authentication vs Authorization  
- Spring Security flow  
- JWT token flow  
- Filter chain  
- PasswordEncoder  

---

# 3️⃣ MICROSERVICES (COMMON AT 4 YOE)

- Monolith vs Microservices  
- Inter-service communication  
- RestTemplate vs WebClient  
- Feign Client  
- Circuit breaker (Resilience4j)  
- Retry & timeout strategies  
- Distributed tracing  
- Config management  
- Service discovery  

---

# 4️⃣ DATABASE (EXPECTED)

- SQL vs NoSQL  
- Indexing  
- Normalization  
- ACID properties  
- Isolation levels  
- Transactions  
- Deadlocks  
- Pagination strategies  

---

# 5️⃣ SYSTEM DESIGN (HIGH-LEVEL EXPECTATION)

- Functional vs Non-functional requirements  
- Load balancing  
- Caching strategies  
- Database scaling  
- Message queues  
- Async processing  
- Idempotency  
- Rate limiting  
- CAP theorem  
- High availability  

---

# 🔥 INTERVIEW REALITY CHECK
At **4 years of experience**, interviewers test:
- Do you understand **WHY**, not just WHAT  
- Have you handled **failures in production**  
- Can you explain **trade-offs**  
- Can you design **simple, scalable systems**

---

# ✅ RECOMMENDED STUDY ORDER
1️⃣ Java Collections + Multithreading  
2️⃣ Spring Boot + JPA deep dive  
3️⃣ REST APIs + Exception handling  
4️⃣ AWS (SQS, SNS, EC2)  
5️⃣ System Design scenarios  

---