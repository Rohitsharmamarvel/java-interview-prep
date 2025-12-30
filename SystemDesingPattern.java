
HOW SYSTEM DESIGN THINKING ACTUALLY EVOLVES

There is a natural evolution:

> Code-level problems → Design Patterns (GoF) → Application architecture → Distributed systems → Large-scale design



You asked for thinking depth, not shortcuts.
So we start from GoF design patterns, because:

They teach why problems happen

They teach how engineers think

System design patterns are just these ideas at scale



---

STAGE 1: CLASS-LEVEL DESIGN PROBLEMS

(This is where everything starts)

Before systems, before microservices, engineers faced problems like:

Objects created again and again

Tight coupling

Huge if-else

Code breaking when adding new features

No control over object lifecycle


These problems existed even in single JVM apps.

From here came Design Patterns.


---

FIRST DESIGN PATTERN (START POINT)

🔹 Singleton Pattern

We start here because every system design concept builds on this.


---

1️⃣ THE ORIGINAL PROBLEM (Very Important)

Situation (early days)

Imagine a Java application:

Reads config from file

Manages DB connections

Writes logs


Question: 👉 Should there be 1 config manager or 100?

If every class does:

new ConfigManager();

Problems:

Same config loaded many times

Wasted memory

Inconsistent state

Hard to control lifecycle



---

Core Problem Statement

> Some objects should have only one instance across the application.



This is the real problem.
Not “pattern”, not “definition”.


---

2️⃣ BAD / NAIVE SOLUTION (What juniors do)

ConfigManager c1 = new ConfigManager();
ConfigManager c2 = new ConfigManager();

Now:

Two objects

Two states

No control


This breaks consistency.


---

3️⃣ FIRST REAL SOLUTION (Singleton born)

Idea:

> Restrict object creation and provide a single global access point.




---

Basic Singleton (easy version)

public class ConfigManager {

    private static ConfigManager instance;

    private ConfigManager() { }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
}

Usage:

ConfigManager config = ConfigManager.getInstance();


---

4️⃣ NEW PROBLEM APPEARS (Important)

This works… until multi-threading.

If two threads call getInstance() at the same time:

Two instances can be created ❌


So the problem evolves.


---

5️⃣ IMPROVED SOLUTION (Thread safety)

public class ConfigManager {

    private static volatile ConfigManager instance;

    private ConfigManager() {}

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }
}

This is Double-Checked Locking.


---

6️⃣ NOW THINK LIKE A 25+ LPA INTERVIEWER

I won’t ask: ❌ “What is Singleton?”

I will ask:

👉 What problems does Singleton create?

Correct answers:

Global state

Hard to test

Tight coupling

Hidden dependencies

Violates SRP sometimes



---

7️⃣ THIS IS WHERE MODERN THINKING STARTS

Engineers realized:

> “The problem is not single instance.
The problem is who controls object creation.”



This leads to:

🔥 Dependency Injection (DI)

🔥 Inversion of Control (IoC)

Spring took this idea and said:

You don’t manage Singleton

Container manages it



---

Spring Example (Real world)

@Service
public class ConfigManager {
}

By default:

Spring creates one instance

Thread-safe lifecycle

Easy testing

Easy mocking


You still get Singleton, without Singleton code.


---

8️⃣ PERFECT INTERVIEW ANSWER (Memorize)

> Singleton was originally used to ensure a single instance of critical components like configuration or logging.
However, manual Singleton introduces global state and testing issues.
In modern Spring applications, we rely on Dependency Injection and IoC container to manage singleton lifecycle in a cleaner and more testable way.



This answer screams senior.


---

9️⃣ WHY WE STARTED WITH THIS

Because next patterns evolve from this thinking:

Factory → object creation control

Builder → complex object construction

Strategy → behavior change without if-else

Observer → loose coupling

Proxy → control access


And later:

Load Balancer = Singleton at system level

API Gateway = Proxy at system level

Circuit Breaker = Strategy + State at system level


Nothing is random.


---





.......................



DESIGN PATTERN #2

🔹 Factory Pattern

(This is where “expert thinking” really starts)

Singleton solved “how many objects”.
Factory solves “who creates objects”.


---

1️⃣ THE ORIGINAL PROBLEM (Real, not theory)

You write code like this 👇

PaymentService payment;

if (type.equals("UPI")) {
    payment = new UpiPayment();
} else if (type.equals("CARD")) {
    payment = new CardPayment();
} else if (type.equals("NETBANKING")) {
    payment = new NetBankingPayment();
}

Looks fine at first… but here’s the real problem:

Business logic + object creation mixed

Every new payment method → modify this code

Huge if-else chains

Violates Open/Closed Principle


👉 Code breaks when requirements change.

This is the real pain that created Factory.


---

2️⃣ NAIVE THINKING (Junior mistake)

Junior says:

> “I’ll just add one more if”



Senior says:

> “This code will rot.”




---

3️⃣ CORE IDEA OF FACTORY (Important)

> Separate object creation from object usage



In simple words:

Client should not know which class is created

Client should depend on abstraction, not concrete class


This is expert thinking.


---

4️⃣ BASIC FACTORY SOLUTION

Step 1: Common interface

public interface Payment {
    void pay();
}

Step 2: Implementations

public class UpiPayment implements Payment {
    public void pay() {
        System.out.println("UPI payment");
    }
}

public class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

Step 3: Factory class

public class PaymentFactory {

    public static Payment getPayment(String type) {

        if ("UPI".equals(type)) return new UpiPayment();
        if ("CARD".equals(type)) return new CardPayment();

        throw new IllegalArgumentException("Invalid payment type");
    }
}

Usage (Client code)

Payment payment = PaymentFactory.getPayment("UPI");
payment.pay();


---

5️⃣ WHAT PROBLEM DID THIS SOLVE?

✅ Client code clean
✅ Object creation centralized
✅ Business logic separate
✅ Easier to change implementation

But wait… new problem appears.


---

6️⃣ NEXT PROBLEM (Advanced thinking)

Every time you add:

WalletPayment

You still modify:

PaymentFactory

So:

Factory becomes God-class

Still violates Open/Closed (partially)


This is where Factory Method comes in.


---

7️⃣ FACTORY METHOD (More flexible)

Instead of one big factory, each type knows how to create itself.

public abstract class PaymentFactory {
    abstract Payment createPayment();
}

public class UpiPaymentFactory extends PaymentFactory {
    Payment createPayment() {
        return new UpiPayment();
    }
}

Usage:

PaymentFactory factory = new UpiPaymentFactory();
Payment payment = factory.createPayment();
payment.pay();

Now:

Adding new payment → new factory class

No modification to existing code


This is Open/Closed Principle done right.


---

8️⃣ REAL-WORLD SPRING CONNECTION (VERY IMPORTANT)

Spring itself is a Factory of objects (beans).

When you write:

@Service
public class OrderService {}

Spring internally does:

Scan class

Create object

Store it

Inject it when needed


👉 ApplicationContext.getBean()
is literally a Factory Method.


---

9️⃣ 25+ LPA INTERVIEW QUESTION (TRICKY)

Question:
👉 Why not just use new everywhere?

BAD ANSWER ❌

> Because Factory is design pattern



GOOD ANSWER ✅

> Using new tightly couples code to concrete implementations, making it hard to extend, test, and maintain. Factory abstracts creation and supports scalability and clean architecture.




---

🔥 EVEN TRICKIER QUESTION

👉 When would you NOT use Factory?

Correct answer:

When object creation is simple

When no variation is expected

Over-engineering hurts readability


This shows maturity.


---

10️⃣ THINKING UPGRADE (Very important)

Factory is NOT about:

❌ saving lines of code

Factory IS about:

✅ controlling change
✅ protecting core logic
✅ future-proofing code

This mindset later becomes:

API Gateway (factory for APIs)

Load balancer (factory for requests)

Message broker (factory for work)


Same thinking. Bigger scale.


---

QUICK CHECK (answer this)

👉 What exact problem does Factory solve that Singleton does not?



Perfect. Short, clean, interview-ready.


---

✅ Answer: What exact problem does Factory solve that Singleton does not?

Singleton controls how many instances of a class exist.
Factory controls which implementation of a class is created and hides object creation logic from the client.
Singleton is about instance lifecycle, Factory is about decoupling creation from usage.

That’s it. Sharp and correct.


---
.....................





Now we move to

DESIGN PATTERN #3

🔹 Builder Pattern

(This is where complexity management begins)


---

1️⃣ ORIGINAL PROBLEM (Real-life pain)

You have a class with many fields:

User(String name, int age, String email, String address,
     String phone, boolean active, boolean verified)

Now usage looks like this:

User user = new User("Rohit", 25, null, null, null, true, false);

Problems:

Hard to read

Easy to mess up parameter order

Optional fields create confusion

Constructors explode as fields grow


This is not scalable code.


---

2️⃣ NAIVE SOLUTIONS (and why they fail)

❌ Multiple constructors

User(name, age)
User(name, age, email)
User(name, age, email, address)

This leads to:

Constructor explosion

Maintenance nightmare



---

3️⃣ CORE IDEA OF BUILDER

> Separate object construction from object representation



In simple terms:

Build object step by step

Make code readable

Handle optional fields cleanly



---

4️⃣ BASIC BUILDER IMPLEMENTATION

public class User {

    private String name;
    private int age;
    private boolean active;

    private User() {}

    public static class Builder {
        private String name;
        private int age;
        private boolean active;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public User build() {
            User u = new User();
            u.name = this.name;
            u.age = this.age;
            u.active = this.active;
            return u;
        }
    }
}

Usage:

User user = new User.Builder()
        .name("Rohit")
        .age(25)
        .active(true)
        .build();

Readable. Safe. Clean.


---

5️⃣ WHAT PROBLEM DID BUILDER SOLVE?

✅ No constructor confusion
✅ Optional fields handled gracefully
✅ Immutable objects possible
✅ Code readability improved


---

6️⃣ ADVANCED THINKING (SDE-2 level)

Builder + Immutability

private final String name;
private final int age;

Now:

Thread-safe

No partial object state

Great for DTOs


This is why:

Lombok @Builder

Protobuf builders

AWS SDK request builders


all exist.


---

7️⃣ 25+ LPA INTERVIEW QUESTION (TRICKY)

Question:
👉 Why not use setters instead of Builder?

Correct answer:

Setters allow partially constructed objects

Hard to enforce required fields

Break immutability

Unsafe in multi-threaded systems



---

8️⃣ WHEN NOT TO USE BUILDER (Very important)

Don’t say “always”.

❌ Don’t use Builder when:

Object has 2–3 fields

No optional parameters

Simple POJO


Over-design is also bad design.


---

9️⃣ THINKING UPGRADE

Builder teaches you:

Manage complexity

Construct safely

Separate creation from use


Later this evolves into:

Request builders

Config builders

System configuration objects


Same mindset. Bigger scale.


---

NEXT PATTERN (Behavior control starts)

👉 Strategy Pattern

This is where if-else starts dying.

Say Next when ready.
........................................


......................................





---

DESIGN PATTERN #4

🔹 Strategy Pattern

(Killing if-else, enabling real extensibility)


---

1️⃣ ORIGINAL PROBLEM (Real-world, not theory)

You write code like this 👇

double calculateDiscount(User user, double amount) {

    if (user.isPremium()) {
        return amount * 0.8;
    } else if (user.isFestivalOffer()) {
        return amount * 0.7;
    } else if (user.isEmployee()) {
        return amount * 0.6;
    }

    return amount;
}

Problems:

Logic grows endlessly

Every new rule → modify method

Hard to test

Business rules tightly coupled


This is code smell at scale.


---

2️⃣ NAIVE FIX (still wrong)

Add more if-else.

This works short-term.
Fails long-term.


---

3️⃣ CORE IDEA OF STRATEGY

> Encapsulate interchangeable behavior and switch it at runtime



In simple words:

Each algorithm = separate class

Client decides which one to use

No if-else



---

4️⃣ BASIC STRATEGY IMPLEMENTATION

Step 1: Strategy interface

public interface DiscountStrategy {
    double apply(double amount);
}

Step 2: Concrete strategies

public class PremiumDiscount implements DiscountStrategy {
    public double apply(double amount) {
        return amount * 0.8;
    }
}

public class FestivalDiscount implements DiscountStrategy {
    public double apply(double amount) {
        return amount * 0.7;
    }
}

Step 3: Context

public class BillingService {

    private DiscountStrategy strategy;

    public BillingService(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double amount) {
        return strategy.apply(amount);
    }
}

Usage:

BillingService service =
        new BillingService(new PremiumDiscount());

service.calculate(1000);


---

5️⃣ WHAT PROBLEM DID STRATEGY SOLVE?

✅ Removed if-else ✅ Open/Closed Principle ✅ Easy testing ✅ Runtime behavior change


---

6️⃣ SPRING + STRATEGY (REAL LIFE)

@Component("PREMIUM")
class PremiumDiscount implements DiscountStrategy {}

@Component("FESTIVAL")
class FestivalDiscount implements DiscountStrategy {}

@Service
class BillingService {

    private final Map<String, DiscountStrategy> strategies;

    BillingService(Map<String, DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    double calculate(String type, double amount) {
        return strategies.get(type).apply(amount);
    }
}

This is enterprise-level strategy usage.


---

7️⃣ 25+ LPA INTERVIEW QUESTION (TRICKY)

👉 Strategy vs Factory — difference?

Correct answer:

Factory chooses which object to create

Strategy chooses which behavior to execute

Factory often used once

Strategy used throughout lifecycle



---

8️⃣ VERY TRICKY QUESTION

👉 Can Strategy replace all if-else?

Correct answer: ❌ No.
Simple conditions don’t need Strategy.
Use it when behavior changes frequently.


---

9️⃣ THINKING UPGRADE

Strategy pattern becomes:

Load balancing algorithms

Retry strategies

Authentication mechanisms

Payment routing


At system level:

Circuit Breaker = Strategy + State

Rate Limiting = Strategy


Same thinking. Bigger scale.


---

QUICK CHECK (answer this)

👉 When would Strategy be a bad choice?

Reply in 2 lines.
Next we go to Observer Pattern — where decoupling really starts.
................






.................





.................


---

DESIGN PATTERN #5

🔹 Observer Pattern

(Loose coupling begins here)


---

1️⃣ ORIGINAL PROBLEM (Very real)

You have a system like this:

void placeOrder(Order order) {
    saveOrder(order);
    sendEmail(order);
    sendSms(order);
    updateAnalytics(order);
}

Problems:

One method doing too much

Tight coupling

Adding new action → modify core logic

Business logic polluted with side-effects


This breaks Single Responsibility badly.


---

2️⃣ NAIVE SOLUTION (Junior move)

Add more method calls:

sendPush();
sendWhatsapp();
sendSlack();

Now:

Code becomes fragile

Every change risks breaking order flow



---

3️⃣ CORE IDEA OF OBSERVER

> One object changes state → multiple interested objects get notified automatically



Key idea:

Subject doesn’t care who is listening

Observers decide what to do


This is decoupling done right.


---

4️⃣ BASIC OBSERVER IMPLEMENTATION

Step 1: Observer interface

public interface OrderObserver {
    void update(Order order);
}

Step 2: Concrete observers

public class EmailService implements OrderObserver {
    public void update(Order order) {
        System.out.println("Email sent");
    }
}

public class AnalyticsService implements OrderObserver {
    public void update(Order order) {
        System.out.println("Analytics updated");
    }
}

Step 3: Subject

public class OrderService {

    private List<OrderObserver> observers = new ArrayList<>();

    public void register(OrderObserver observer) {
        observers.add(observer);
    }

    public void placeOrder(Order order) {
        save(order);
        notifyObservers(order);
    }

    private void notifyObservers(Order order) {
        observers.forEach(o -> o.update(order));
    }
}


---

5️⃣ WHAT PROBLEM DID OBSERVER SOLVE?

✅ No tight coupling
✅ Add new behavior without touching core logic
✅ Clean responsibility separation
✅ Extensible system


---

6️⃣ REAL-WORLD SPRING USAGE (IMPORTANT)

Spring has this built-in.

Event publishing

applicationEventPublisher.publishEvent(
    new OrderCreatedEvent(order)
);

Event listener

@EventListener
public void handleOrder(OrderCreatedEvent event) {
    // send email / analytics / notification
}

This is Observer Pattern, production-grade.


---

7️⃣ SYSTEM DESIGN CONNECTION (VERY IMPORTANT)

Observer evolves into:

Kafka consumers

AWS SNS subscribers

Webhooks

Event-driven architecture


Same concept. Bigger system.


---

8️⃣ 25+ LPA INTERVIEW QUESTION (TRICKY)

👉 Observer vs Strategy?

Correct answer:

Strategy → one behavior chosen

Observer → many listeners notified

Strategy is pull

Observer is push



---

9️⃣ VERY IMPORTANT PITFALL

👉 Observer can cause cascading failures

If:

One observer is slow

Others get delayed


Solution:

Async observers

Message queues

Event buffering


This is senior thinking.


---

10️⃣ THINKING UPGRADE

Observer teaches:

Event-first thinking

Decoupling side effects

Reactive systems


Later becomes:

Event sourcing

CQRS

Streaming systems



---

QUICK CHECK (Answer this)

👉 Why is synchronous Observer dangerous in large systems?

Reply in 2–3 lines.
Next we go to Decorator Pattern — controlled behavior extension without inheritance.