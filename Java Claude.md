# 🚀 Core Java Interview Preparation - Topic 1

## **Object-Oriented Programming (OOPs) Concepts**

Let's start with the most important topic - **OOPs**. This is the foundation of Java, and it's 100% asked in interviews.

---

## **1. ENCAPSULATION (Data Hiding)**

### **What is it?**
Encapsulation means wrapping data (variables) and code (methods) together in a single unit (class), and hiding sensitive data from the outside world.

### **How to achieve it?**
- Make variables **private**
- Provide public **getter/setter methods**

### **Real Example:**
```java
public class BankAccount {
    private double balance;  // Private - no direct access
    private String accountNumber;
    
    // Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    // Getter - for reading
    public double getBalance() {
        return balance;
    }
    
    // Setter with validation - controlled access
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount!");
        }
    }
    
    public void withdraw(double amount) {
        if(amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }
    
    // accountNumber is read-only (no setter)
    public String getAccountNumber() {
        return accountNumber;
    }
}
```

### **Why Encapsulation? (Benefits)**
1. **Data Security** - Direct access prevented, validation added
2. **Flexibility** - Internal implementation can change without affecting outside code
3. **Control** - You decide what to expose and what to hide
4. **Read-only/Write-only** - Can make fields read-only (only getter) or write-only (only setter)

### **Tricky Interview Questions:**

**Q1: Can we achieve 100% encapsulation in Java?**
**Answer:** Yes! Make all variables private and provide only public getters/setters. Don't expose any internal data structures directly.

**Q2: What's the difference between Encapsulation and Abstraction?**
**Answer:** 
- **Encapsulation** = Data hiding (HOW you hide - using private variables, getters/setters)
- **Abstraction** = Implementation hiding (WHAT you hide - showing only essential features, hiding complexity)

**Q3: If everything is private, how is it different from having no access at all?**
**Answer:** With encapsulation, we provide **controlled access** through methods. We can add validation, logging, security checks in getters/setters. Direct access would bypass all this.

---

## **2. INHERITANCE (IS-A Relationship)**

### **What is it?**
Inheritance allows one class to acquire properties and methods of another class. The child class inherits from the parent class.

### **Types of Inheritance in Java:**
1. **Single Inheritance** - One parent, one child
2. **Multilevel Inheritance** - Child becomes parent for another child
3. **Hierarchical Inheritance** - One parent, multiple children
4. **Multiple Inheritance** - ❌ NOT supported via classes (but via interfaces)

### **Real Example:**
```java
// Parent class
public class Employee {
    protected String name;
    protected int empId;
    protected double baseSalary;
    
    public Employee(String name, int empId, double baseSary) {
        this.name = name;
        this.empId = empId;
        this.baseSalary = baseSalary;
    }
    
    public void displayInfo() {
        System.out.println("Employee: " + name);
        System.out.println("ID: " + empId);
    }
    
    public double calculateSalary() {
        return baseSalary;
    }
}

// Child class 1
public class Developer extends Employee {
    private String programmingLanguage;
    private double bonusPerProject;
    private int projectsCompleted;
    
    public Developer(String name, int empId, double baseSalary, 
                     String language) {
        super(name, empId, baseSalary);  // Call parent constructor
        this.programmingLanguage = language;
        this.bonusPerProject = 5000;
    }
    
    // Method Overriding
    @Override
    public double calculateSalary() {
        return baseSalary + (bonusPerProject * projectsCompleted);
    }
    
    // New method specific to Developer
    public void code() {
        System.out.println(name + " is coding in " + programmingLanguage);
    }
    
    public void setProjectsCompleted(int projects) {
        this.projectsCompleted = projects;
    }
}

// Child class 2
public class Manager extends Employee {
    private int teamSize;
    private double incentivePerMember;
    
    public Manager(String name, int empId, double baseSalary, int teamSize) {
        super(name, empId, baseSalary);
        this.teamSize = teamSize;
        this.incentivePerMember = 2000;
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary + (incentivePerMember * teamSize);
    }
    
    public void conductMeeting() {
        System.out.println(name + " is conducting a meeting with " + 
                          teamSize + " members");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Developer dev = new Developer("Rahul", 101, 60000, "Java");
        dev.setProjectsCompleted(3);
        dev.displayInfo();  // Inherited method
        dev.code();  // Own method
        System.out.println("Salary: " + dev.calculateSalary());
        
        Manager mgr = new Manager("Priya", 201, 80000, 5);
        mgr.displayInfo();  // Inherited method
        mgr.conductMeeting();  // Own method
        System.out.println("Salary: " + mgr.calculateSalary());
    }
}
```

### **Key Concepts:**

**`super` keyword:**
- Calls parent class constructor: `super(params)`
- Calls parent class method: `super.methodName()`
- Must be first line in child constructor

**`protected` access modifier:**
- Accessible in same package + child classes (even in different packages)
- More accessible than private, less than public

### **Tricky Interview Questions:**

**Q1: Why doesn't Java support multiple inheritance through classes?**
**Answer:** To avoid the **Diamond Problem**. 
```
     A
    / \
   B   C
    \ /
     D
```
If class D inherits from both B and C, and both B and C have the same method from A, which one should D use? This creates ambiguity. Java solved this by:
- Not allowing multiple class inheritance
- Allowing multiple interface inheritance (since interfaces only declare methods, no ambiguity)

**Q2: Can we override static methods?**
**Answer:** No! Static methods belong to the class, not objects. They are **hidden**, not overridden. This is called **Method Hiding**.
```java
class Parent {
    static void display() {
        System.out.println("Parent static");
    }
}

class Child extends Parent {
    static void display() {  // This is hiding, not overriding
        System.out.println("Child static");
    }
}

// Usage
Parent p = new Child();
p.display();  // Output: "Parent static" (based on reference type)
```

**Q3: Can we override private methods?**
**Answer:** No! Private methods are not visible to child classes, so they cannot be overridden. Child class can have a method with same name, but it's a new method, not an override.

**Q4: What happens if parent constructor throws an exception?**
**Answer:** Child object creation fails. The child constructor implicitly calls `super()`, and if that fails, the child object is never created.

**Q5: Can constructors be inherited?**
**Answer:** No! Constructors are not inherited. Each class must define its own constructors. However, child constructors can call parent constructors using `super()`.

---

## **3. POLYMORPHISM (Many Forms)**

### **What is it?**
One entity (method/object) behaving differently in different situations.

### **Types:**

### **A) Compile-Time Polymorphism (Method Overloading)**

Same method name, different parameters.

```java
public class Calculator {
    // Different number of parameters
    public int add(int a, int b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Different data types
    public double add(double a, double b) {
        return a + b;
    }
    
    // Different order of parameters
    public String add(String a, int b) {
        return a + b;
    }
    
    public String add(int a, String b) {
        return a + b;
    }
}
```

**Rules for Method Overloading:**
1. Method name must be same
2. Parameter list must be different (type, number, or order)
3. Return type can be different (but alone it's not enough)
4. Access modifier can be different
5. Can throw different exceptions

### **B) Runtime Polymorphism (Method Overriding)**

Child class provides specific implementation of parent class method.

```java
public class PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing payment: " + amount);
    }
}

public class CreditCardPayment extends PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment: " + amount);
        System.out.println("Adding 2% processing fee");
        // Credit card specific logic
    }
}

public class UPIPayment extends PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment: " + amount);
        System.out.println("Instant transfer, no fee");
        // UPI specific logic
    }
}

public class NetBankingPayment extends PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing NetBanking payment: " + amount);
        System.out.println("Redirecting to bank portal");
        // NetBanking specific logic
    }
}

// Real-world usage
public class CheckoutService {
    public void checkout(PaymentProcessor processor, double amount) {
        // Same method call, different behavior at runtime
        processor.processPayment(amount);
    }
    
    public static void main(String[] args) {
        CheckoutService service = new CheckoutService();
        
        // Runtime polymorphism in action
        service.checkout(new CreditCardPayment(), 1000);
        service.checkout(new UPIPayment(), 1000);
        service.checkout(new NetBankingPayment(), 1000);
    }
}
```

**Rules for Method Overriding:**
1. Method signature must be exactly same (name + parameters)
2. Return type must be same or covariant (subtype)
3. Access modifier cannot be more restrictive (can be less restrictive)
4. Cannot override final, static, or private methods
5. Can throw same, subclass, or no exception (but not broader exceptions)

### **Tricky Interview Questions:**

**Q1: What is the output?**
```java
class Parent {
    public void display() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    public void display() {
        System.out.println("Child");
    }
}

public class Test {
    public static void main(String[] args) {
        Parent p = new Child();  // Upcasting
        p.display();
    }
}
```
**Answer:** Output is "Child"
**Why?** Method to be called is decided at runtime based on the **object type** (Child), not reference type (Parent). This is **Dynamic Method Dispatch**.

**Q2: Can we overload main method?**
**Answer:** Yes! But JVM will only call `public static void main(String[] args)`.
```java
public class Test {
    public static void main(String[] args) {
        System.out.println("Original main");
        main(10);
    }
    
    public static void main(int x) {  // Overloaded
        System.out.println("Overloaded main: " + x);
    }
}
```

**Q3: Overloading vs Overriding - Key Differences:**

| Feature | Overloading | Overriding |
|---------|-------------|------------|
| When decided | Compile-time | Runtime |
| Where happens | Same class | Parent-Child |
| Parameters | Must differ | Must be same |
| Return type | Can differ | Must be same/covariant |
| Access modifier | Can differ | Cannot be more restrictive |
| static methods | Can be overloaded | Cannot be overridden (hidden instead) |
| private methods | Can be overloaded | Cannot be overridden |

**Q4: What is Covariant Return Type?**
**Answer:** From Java 5, overriding method can return a subtype of the return type declared in parent.
```java
class Animal {
    public Animal getAnimal() {
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    public Dog getAnimal() {  // Dog is subtype of Animal
        return new Dog();
    }
}
```

---

4. ABSTRACTION (Hiding Complexity)
What is it?
Showing only essential features and hiding implementation details.
How to achieve?
Abstract Classes (0-100% abstraction)
Interfaces (100% abstraction)
A) Abstract Classes
// Cannot instantiate abstract class
public abstract class Vehicle {
    protected String brand;
    protected int year;
    
    // Concrete method (with implementation)
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    
    // Concrete method
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
    
    // Abstract method (no implementation)
    public abstract void start();
    public abstract void stop();
    public abstract double calculateFuelEfficiency();
}

public class Car extends Vehicle {
    private int numberOfDoors;
    
    public Car(String brand, int year, int doors) {
        super(brand, year);
        this.numberOfDoors = doors;
    }
    
    @Override
    public void start() {
        System.out.println("Car engine started with key");
    }
    
    @Override
    public void stop() {
        System.out.println("Car engine stopped");
    }
    
    @Override
    public double calculateFuelEfficiency() {
        return 15.5;  // km per liter
    }
}

public class Bike extends Vehicle {
    private boolean hasGear;
    
    public Bike(String brand, int year, boolean hasGear) {
        super(brand, year);
        this.hasGear = hasGear;
    }
    
    @Override
    public void start() {
        System.out.println("Bike started with kick/self-start");
    }
    
    @Override
    public void stop() {
        System.out.println("Bike stopped");
    }
    
    @Override
    public double calculateFuelEfficiency() {
        return 45.0;  // km per liter
    }
}
Key Points about Abstract Classes:
Cannot create object of abstract class: new Vehicle() ❌
Can have constructors (called by child classes)
Can have both abstract and concrete methods
Can have instance variables
Can have static methods
Use abstract keyword for class and methods
B) Interfaces
// Interface - 100% abstraction (before Java 8)
public interface Payable {
    // public static final by default
    double TAX_RATE = 0.18;
    
    // public abstract by default
    double calculatePayment();
    void processPayment();
}

public interface Trackable {
    String getTrackingId();
    String getStatus();
}

// Class can implement multiple interfaces
public class Order implements Payable, Trackable {
    private String orderId;
    private double amount;
    private String status;
    
    public Order(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = "PENDING";
    }
    
    @Override
    public double calculatePayment() {
        return amount + (amount * TAX_RATE);
    }
    
    @Override
    public void processPayment() {
        System.out.println("Processing payment for order: " + orderId);
        System.out.println("Total amount: " + calculatePayment());
        this.status = "PAID";
    }
    
    @Override
    public String getTrackingId() {
        return orderId;
    }
    
    @Override
    public String getStatus() {
        return status;
    }
}
Java 8+ Interface Features:
public interface ModernInterface {
    // Abstract method (must be implemented)
    void abstractMethod();
    
    // Default method (can be overridden, but not mandatory)
    default void defaultMethod() {
        System.out.println("This is default implementation");
        helperMethod();  // Can call private methods
    }
    
    // Static method (cannot be overridden)
    static void staticMethod() {
        System.out.println("Static method in interface");
    }
    
    // Private method (Java 9+) - for code reuse within interface
    private void helperMethod() {
        System.out.println("Helper logic");
    }
}
Tricky Interview Questions:
Q1: Abstract Class vs Interface - When to use what?
Abstract Class - Use when:
You want to share code among related classes
You need non-final or non-static fields
You need constructors
Classes are closely related (IS-A relationship)
Example: Vehicle → Car, Bike (all are vehicles)
Interface - Use when:
Unrelated classes should implement same behavior
You want to specify behavior, not implementation
You need multiple inheritance
Classes have CAN-DO relationship
Example: Flyable → Bird, Airplane, Drone (can fly, but unrelated)
Q2: Can interface extend another interface?
Answer: Yes! Interface can extend one or more interfaces.
interface A {
    void methodA();
}

interface B {
    void methodB();
}

interface C extends A, B {
    void methodC();
}

// Class implementing C must implement all three methods
class MyClass implements C {
    public void methodA() { }
    public void methodB() { }
    public void methodC() { }
}
Q3: Can abstract class implement interface without implementing its methods?
Answer: Yes! Abstract class doesn't need to implement interface methods. Child concrete class must implement them.
interface Printable {
    void print();
}

abstract class Document implements Printable {
    // Not implementing print() - that's fine for abstract class
    abstract void format();
}

class PDFDocument extends Document {
    // Must implement both
    public void print() {
        System.out.println("Printing PDF");
    }
    
    void format() {
        System.out.println("Formatting PDF");
    }
}
Q4: What if a class implements two interfaces with same default method?
Answer: Compilation error! Class must override the method.
interface A {
    default void display() {
        System.out.println("A");
    }
}

interface B {
    default void display() {
        System.out.println("B");
    }
}

class MyClass implements A, B {
    @Override
    public void display() {  // Must override
        A.super.display();  // Can call specific interface's method
        // or B.super.display();
        // or your own implementation
    }
}
Q5: Can we create object of interface?
Answer: No direct instantiation, but YES using anonymous class or lambda!
interface Greeting {
    void sayHello(String name);
}

// Anonymous class
Greeting g1 = new Greeting() {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
};

// Lambda (functional interface)
Greeting g2 = (name) -> System.out.println("Hello " + name);
Want to continue with more OOPs concepts, or should we move to the next major topic? Let me know! 🚀
We still have:
Access Modifiers in detail
final, static, this, super keywords
Inner Classes
Object class methods (equals, hashCode, toString, clone)
Or we can move to Collections Framework, Multithreading





.
.
.
.
.
.
.
.
.
.
.
.
.
.
.
.
.
.
.
.
.





---

1️⃣ Inheritance vs Composition

First, forget Java syntax.
Understand the idea.


---

🔹 Inheritance = EXTENDS

Inheritance means:

> One class is a type of another class



Example:

Car is a Vehicle

Dog is an Animal


class Car extends Vehicle { }

This creates a tight bond. Child depends heavily on parent.


---

🔹 Composition = HAS-A

Composition means:

> One class uses another class



Example:

Car has an Engine

Order has a PaymentService


class Car {
    private Engine engine;
}

This creates a loose bond. You can change engine without breaking car.


---

🔥 Key difference (very important)

Inheritance:

Compile-time binding

Tight coupling

Hard to change later


Composition:

Runtime flexibility

Loose coupling

Easy to extend and replace



---

2️⃣ IS-A vs HAS-A (don’t confuse this)

This is where many people mess up.


---

IS-A relationship

Used with inheritance

Ask this question:

> Can I say “X is a Y” naturally?



Examples:

Car is a Vehicle ✅

Admin is a User ✅

Order is a Payment ❌


If sentence feels wrong → inheritance is wrong.


---

HAS-A relationship

Used with composition

Ask this:

> Does X use Y?



Examples:

Order has a PaymentService ✅

User has an Address ✅

Car has an Engine ✅



---

Interview-ready line

> IS-A represents inheritance and should be used only when there is a true hierarchical relationship. HAS-A represents composition and is preferred for flexibility and loose coupling.




---

3️⃣ Why Composition is Preferred (THIS IS PBC DEPTH 🔥)

Interviewers LOVE this question.

Short answer:

Because inheritance breaks easily.

Real answer:

Because requirements change.


---

❌ Where inheritance breaks design

Example: Payment

class Payment {
    void pay() {}
}

class CreditCardPayment extends Payment {}
class UpiPayment extends Payment {}
class WalletPayment extends Payment {}

Now product says:

Add retry logic

Add logging

Add fraud check


You add it in parent → affects all children
You add it in child → code duplication

Inheritance starts hurting.


---

✅ Same thing with composition

class OrderService {
    private PaymentProcessor processor;
}

interface PaymentProcessor {
    void pay();
}

Now:

Switch UPI to Wallet → no inheritance change

Add decorator → no class explosion



---

🔥 Interview killer line

> Inheritance is rigid and exposes internal behavior. Composition is flexible and allows behavior to change at runtime, which is why it’s preferred in real systems.




---

4️⃣ Where Inheritance SHOULD be used

Don’t say “never use inheritance” — that’s a red flag.

Use inheritance when:

Relationship is stable

Base class truly represents shared identity

You don’t expect behavior change


Examples:

Exception hierarchy

DTO base classes

Framework base classes (Spring)



---

5️⃣ SOLID Principles (Simple + Real)

Now the big one.


---

S — Single Responsibility Principle (SRP)

> A class should have one reason to change



❌ Bad:

class OrderService {
    void createOrder() {}
    void saveToDB() {}
    void sendEmail() {}
}

Too many responsibilities.

✅ Good:

OrderService
OrderRepository
EmailService


---

O — Open/Closed Principle (OCP)

> Open for extension, closed for modification



❌ Bad:

if(type.equals("UPI")) {}
else if(type.equals("CARD")) {}

Every new payment → modify code.

✅ Good:

PaymentProcessor interface

Add new implementation, no change to existing code.


---

L — Liskov Substitution Principle (LSP)

> Child should replace parent without breaking behavior



❌ Violation:

class Bird {
    void fly() {}
}

class Ostrich extends Bird {
    // cannot fly ❌
}

Inheritance is wrong here.


---

I — Interface Segregation Principle (ISP)

> Don’t force clients to implement what they don’t need



❌ Bad:

interface Worker {
    work();
    eat();
}

Robot doesn’t eat.

✅ Good:

Workable
Eatable


---

D — Dependency Inversion Principle (DIP)

> Depend on abstractions, not concrete classes



❌ Bad:

new UpiPayment()

✅ Good:

PaymentProcessor processor;

Injected by Spring.


---

6️⃣ Real-world OOP in Service Layer (IMPORTANT)

Typical bad project mistake (honest answer)

> I violated SRP and DIP.



Example:

Service doing DB + API + validation

Using new instead of dependency injection

Big god classes



---

Interview question:

“Which SOLID principle you violated in past project?”

Best honest answer:

> I initially violated Single Responsibility and Dependency Inversion by putting too much logic in service classes and tightly coupling them to implementations. Later, we refactored using interfaces, smaller services, and dependency injection.



This sounds experienced, not weak.


---

Final summary (say this calmly)

> Composition is preferred over inheritance because it provides flexibility and avoids tight coupling. Inheritance should only be used for true IS-A relationships. SOLID principles help keep code maintainable, especially in service-layer design.




---

Next options (your call): 1️⃣ Spring Boot service-layer design with SOLID
2️⃣ System design example using composition
3️⃣ Mock interview (I grill you like PBC)

Tell me what you want next.
