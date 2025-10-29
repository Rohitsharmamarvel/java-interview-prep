Perfect. Here’s your ready-to-paste Markdown version — cleanly formatted for GitHub.

Just copy everything below and paste it into your file:
Core_Java_Interview_Preparation.md
inside your repo java-interview-prep.


---

# 🧠 Core Java Interview Preparation

Comprehensive Core Java fundamentals for interview prep — covering concepts, real-world examples, tricky questions, common pitfalls, and production usage.

---

## 1️⃣ Object-Oriented Programming (OOP)

**Key Concepts**
- **Encapsulation**: Keep state private, expose intent via methods  
- **Abstraction**: Expose only necessary details (interfaces, abstract classes)  
- **Inheritance**: Reuse behavior, but prefer composition  
- **Polymorphism**: Code against abstractions, not implementations  

**Example**
```java
interface Payment { void pay(double amount); }

class CreditCardPayment implements Payment {
  public void pay(double amount) { /* call gateway */ }
}

class OrderService {
  private final Payment payment;
  public OrderService(Payment payment) { this.payment = payment; }
  public void checkout(double amount) { payment.pay(amount); }
}

Tricky Questions

Q: When to use abstract class vs interface?
A: Abstract class when shared state or base implementation needed; interface for contracts or multiple inheritance of type.

Q: What is Liskov Substitution Principle (LSP)?
A: Subtypes must be replaceable for their base types without breaking program behavior.


Pitfalls

Deep inheritance chains

Exposing internal mutable state


Best Practices

Prefer composition over inheritance

Keep interfaces small and focused


Used In Production

Service design, domain modeling, dependency injection



---

2️⃣ JVM, Memory Model, and Garbage Collection

Memory Areas

Heap – objects live here

Stack – method frames and references

Metaspace – class metadata

GC – automatic memory cleanup (G1, Parallel, ZGC, Shenandoah)


Tricky Questions

Q: What does volatile guarantee?
A: Visibility and ordering, not atomicity.

Q: What is “safe publication”?
A: Ensuring an object is visible fully initialized to all threads (via final fields, volatile, or synchronized).


Pitfalls

Misusing volatile for compound operations

Poor GC tuning


In Production

Use jstat, jmap, jstack, profilers (YourKit, Flight Recorder)

Tune heap and monitor GC pauses



---

3️⃣ Primitives, Boxing, and Memory

Concepts

Primitives (int, double) are lightweight.

Boxed types (Integer, Double) are objects — slower and heap-allocated.


Tricky Question

Why Integer a = 127; Integer b = 127; a == b → true, but 128 → false?
➤ Because JVM caches Integer values from -128 to 127.


Pitfalls

Autoboxing/unboxing in tight loops

Using == for boxed comparisons instead of .equals()



---

4️⃣ equals() and hashCode()

Rules

Equal objects must have the same hashCode.

Avoid using mutable fields in these methods.


Example

@Override
public boolean equals(Object o) { /* type check + compare */ }

@Override
public int hashCode() { return Objects.hash(field1, field2); }

Pitfall

Changing field values after putting object in HashMap breaks lookup.



---

5️⃣ Collections Framework & Generics

Key Interfaces

List, Set, Map, Queue


Common Implementations

ArrayList → fast random access

LinkedList → rare, more overhead

HashMap → O(1) average

TreeMap → ordered O(log n)


Generics Example

Map<String, List<Order>> ordersByCustomer = new HashMap<>();

Tricky Questions

List<? extends Number> (read-only) vs List<? super Integer> (write Integer)


Pitfalls

ConcurrentModificationException

Using raw types


Production Tips

Use ConcurrentHashMap for concurrent access

Prefer immutable collections for shared data



---

6️⃣ Multithreading & Concurrency

Core APIs

ExecutorService, CompletableFuture, Lock, CountDownLatch, Semaphore


Example

ExecutorService ex = Executors.newFixedThreadPool(10);
CompletableFuture.supplyAsync(() -> fetchData(), ex)
  .thenApply(this::process)
  .exceptionally(e -> handle(e));

Tricky Questions

Deadlock – circular lock dependency

Difference: synchronized (auto-lock) vs ReentrantLock (manual, flexible)


Pitfalls

Using too many threads manually

Blocking calls inside async logic


Production

Right-size thread pools

Monitor thread dumps and queue metrics



---

7️⃣ Exception Handling

Concepts

Checked → recoverable

Unchecked → programming errors

Try-with-resources for auto-close


Example

try (InputStream in = Files.newInputStream(path)) {
  // work
} catch (IOException e) {
  throw new MyAppException("Failed", e);
}

Pitfalls

Catching Exception blindly

Swallowing exceptions



---

8️⃣ Java 8+ Features: Lambdas, Streams, Optional

Example

List<String> names = users.stream()
  .map(User::getName)
  .filter(n -> !n.isEmpty())
  .distinct()
  .collect(Collectors.toList());

Tricky

Streams are lazy — no execution until terminal op (collect, forEach).


Pitfalls

Overusing parallel streams

Returning Optional fields in DTOs



---

9️⃣ Strings & Immutability

Concepts

String is immutable → safe, cached, thread-safe

Use StringBuilder for concatenation


Tricky Question

Why is String immutable?
➤ For caching, thread safety, and security (interning)



---

🔟 I/O, NIO, and Serialization

Tips

Prefer NIO (Files, Paths, Channels`)

Avoid Java built-in serialization — use JSON (Jackson, Gson)


Tricky

Java serialization can cause compatibility and security issues



---

11️⃣ Reflection & Annotations

Reflection

Allows runtime inspection and modification of classes


Spring Usage

Reflection + annotations used for Dependency Injection (DI) and AOP


Pitfalls

Overuse slows startup and reduces type safety



---

12️⃣ Common Design Patterns

Factory

Builder

Strategy

Observer

Dependency Injection


Example (Builder)

Person p = Person.builder()
  .name("Rohit")
  .age(30)
  .build();


---

13️⃣ Performance Optimization

Checkpoints

CPU usage

GC pressure

Thread contention

I/O bottlenecks


Tools

JVisualVM, JFR, async-profiler


Best Practices

Profile before optimizing

Cache only when measured beneficial



---

14️⃣ Testing

Frameworks

JUnit, Mockito, Testcontainers


Tips

Unit tests: logic

Integration tests: infra (DB, MQ)

Avoid flaky tests for async logic



---

✅ Quick Mastery Checklist

OOP fundamentals

equals/hashCode, immutability

Collections & generics

Multithreading basics

Streams & Optional

JVM memory & GC

Exception handling

NIO & design patterns

Testing strategy



---

End of Notes

---

### 🔧 Now what to do
1. Copy everything inside the code block above (from `# 🧠 Core Java Interview Preparation` onward).  
2. Go to your GitHub repo → open your file  
   `Core_Java_Interview_Preparation.md`  
3. Paste the content  
4. Commit  

Then open it — it’ll look beautifully formatted on GitHub with headings, code syntax, and spacing.  

---

Would you like me to create the next Markdown section for **Spring & Spring Boot interview topics** next?
