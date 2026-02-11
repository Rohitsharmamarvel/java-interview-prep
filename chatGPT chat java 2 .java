
1. Big picture


2. Internal structure


3. Memory areas


4. Object lifecycle


5. Pass-by-value truth


6. Memory leaks


7. GC deeply


8. Latency impact



Let’s start.


---

1️⃣ JVM vs JRE vs JDK (Clear mental model)

JDK

> Developer toolkit
Contains:



JRE

Compiler (javac)

Debug tools

Javadoc

JConsole etc.


👉 Tum code likhne ke liye JDK use karte ho.


---

JRE

> Runtime environment
Contains:



JVM

Core libraries


👉 End user program run karta hai JRE se.


---

JVM

> Engine that runs bytecode



Java code → compiled to bytecode → JVM execute karta hai.

Important:

> JVM is platform-dependent
Bytecode is platform-independent



That’s why Java is portable.


---

2️⃣ Class Loading (High Level)

When you run a Java program:

1. ClassLoader loads .class file


2. Bytecode verification


3. Memory allocation for class metadata


4. Static variables initialized


5. Static blocks executed



Class loaders types:

Bootstrap

Extension

Application


Interview line

> JVM loads classes dynamically at runtime using class loaders and performs linking and initialization before execution.




---

3️⃣ Stack vs Heap (VERY IMPORTANT)

This is foundation.


---

Stack Memory

Each thread has its own stack.

Stores:

Local variables

Method calls

Primitive values

Reference variables


Fast. Automatically cleaned after method ends.


---

Heap Memory

Shared among all threads.

Stores:

Objects

Instance variables

Arrays


Managed by GC.


---

Mental model:

Stack → who is pointing
Heap  → actual objects


---

Example:

Person p = new Person();

Stack:

p → reference

Heap:

Person object


---

Interview line

> Stack stores method-level data and references, while heap stores actual objects shared across threads.




---

4️⃣ Object Lifecycle (Deep clarity)

1. Class loaded


2. Object created (new)


3. Constructor runs


4. Object lives in heap


5. No reference left


6. Eligible for GC


7. GC removes it



Important:

> GC does NOT destroy object immediately
Only when unreachable




---

5️⃣ Pass by Value (CRITICAL & TRICKY)

Java is ALWAYS pass by value.

Always.

Even for objects.


---

Case 1: Primitive

int a = 10;
change(a);

Copy of value passed.


---

Case 2: Object

Person p = new Person();
modify(p);

Copy of reference passed.

You can modify object
But cannot change original reference.


---

Example:

void modify(Person p) {
    p.name = "X";      // works
    p = new Person();  // does NOT affect caller
}


---

Interview killer line

> Java is strictly pass-by-value. For objects, the value being passed is the reference, not the object itself.




---

6️⃣ Memory Leaks in Java

People think:

> Java me GC hai → memory leak nahi hoga



Wrong.

Memory leak means:

> Object is not used but still referenced




---

Common causes:

Static collections

ThreadLocal not cleared

Listeners not removed

Cache without eviction


Example:

static List<Object> list = new ArrayList<>();

Objects never GCed.


---

7️⃣ Garbage Collection (Deep but simple)

GC removes unreachable objects.

Heap divided into:

Young Generation

Old Generation



---

Young Generation

New objects

Minor GC


Most objects die young.


---

Old Generation

Long-living objects

Major GC



---

GC Types (Important for interview)

Parallel GC

Throughput focused

Stop-the-world

Multiple threads

High throughput, higher pause



---

G1 GC (Modern default)

Divides heap into regions

Predictable pauses

Low latency focused

Good for large heaps



---

Interview difference

Feature	Parallel	G1

Focus	Throughput	Low latency
Pause	Longer	Shorter
Heap size	Small-medium	Large heaps



---

8️⃣ GC Pauses & Latency Impact

When GC runs:

Application threads pause (Stop-The-World)

No request processing

Latency spike


In high traffic systems:

200ms pause = bad

2 sec pause = outage


That’s why:

Low latency systems prefer G1

Some use ZGC / Shenandoah (ultra low pause)



---

Real production impact

GC pause → API response slow
Too frequent GC → CPU waste
Too rare GC → memory pressure

Balance is key.


---

9️⃣ Advanced JVM Depth (Interview Level)

❓ Why do most objects die young?

Because temporary objects:

DTOs

Request objects

String builders


Are short lived.


---

❓ Why heap tuning matters?

Improper heap size:

Too small → frequent GC

Too large → long pause



---

❓ What causes Full GC?

Old gen full

System.gc()

Metaspace overflow



---

Final 60-second interview summary (Memorize)

> JVM executes bytecode and manages memory using stack and heap. Stack stores method-level data, heap stores objects. Java is strictly pass-by-value, even for objects. Objects become eligible for garbage collection when unreachable. GC operates across generations, with Parallel GC focusing on throughput and G1 focusing on low latency. Improper memory management and lingering references can cause memory leaks, and GC pauses directly impact application latency in high-traffic systems.




..
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

