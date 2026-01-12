---

HashMap: internal working (clear + practical)

1. Basic idea (interviewer warm-up)

HashMap stores data as key–value pairs.
Internally, it’s an array of buckets.

Each bucket can hold:

a LinkedList (Java 7 and early Java 8)

or a Red-Black Tree (Java 8+ when collisions increase)



---

2. Put operation – what actually happens

map.put("A", 10);

Step by step:

1. hashCode()

HashMap calls key.hashCode()

Then applies bit manipulation to spread bits



2. Index calculation



index = (n - 1) & hash

Why?

Faster than %

Works only when capacity is power of 2


3. Bucket check



If bucket empty → insert directly

If bucket not empty → collision handling


4. Collision handling



equals() is used to check if key already exists

If same key → value replaced

If different key → added to list / tree



---

3. Collision → LinkedList → Tree (Java 8+)

When too many elements land in one bucket:

Condition	Action

> 8 nodes	Convert LinkedList → Red-Black Tree
< 6 nodes	Tree → LinkedList


Why tree?

Lookup improves from O(n) to O(log n)



---

4. Resize & Rehash (important for performance)

Default capacity = 16

Load factor = 0.75


Resize happens when:

size > capacity * loadFactor

During resize:

Capacity doubles

Existing entries are rehashed

Buckets redistributed


Interview insight:
Resize is expensive → causes latency spikes in production.


---

5. Get operation

map.get(key)

Flow:

1. hashCode()


2. index calculation


3. traverse bucket


4. equals() match


5. return value




---

Time complexity (interview must-know)

Operation	Avg	Worst

put	O(1)	O(n) → O(log n) (Java 8)
get	O(1)	O(n) → O(log n)
remove	O(1)	O(n)



---

Equals & HashCode (very important)

Contract:

If equals() is true → hashCode() must be same

Same hashCode ≠ same object


Bad implementation = performance disaster.

Real-world example: If you use a custom object as key and forget to override hashCode, HashMap becomes slow.


---

Null handling (tricky question)

One null key allowed

Multiple null values allowed


Why only one null key?

All null keys map to same bucket (index 0)



---

HashMap vs ConcurrentHashMap (4-year level)

HashMap	ConcurrentHashMap

Not thread-safe	Thread-safe
Faster	Slightly slower
Fail-fast iterator	Fail-safe
Null key allowed	No null key/value



---

Interview questions they WILL ask you

1. Why capacity is power of 2?

Faster index calculation using bitwise AND

Better hash distribution



---

2. Why load factor 0.75?

Balance between memory usage and performance

Fewer collisions without wasting space



---

3. What happens if many keys have same hashCode?

Bucket grows

LinkedList → Tree

Lookup degrades



---

4. Can two different keys have same hashCode?

Yes. That’s called a collision.


---

5. Why HashMap is not thread-safe?

Concurrent resize can cause infinite loop (Java 7 issue)

Data inconsistency



---

6. When would you use TreeMap over HashMap?

When sorted order is required

O(log n) operations guaranteed



---

Real production insight (this separates seniors)

Pre-size HashMap if size is known:


new HashMap<>(1000);

Avoid mutable objects as keys

Poor hashCode = CPU spike

Resize during peak traffic = latency issues



---

One-liner you can say in interview

> “HashMap gives O(1) average performance using hashing, but real performance depends heavily on good hashCode implementation, load factor, and collision behavior.”



If you want next:

ConcurrentHashMap internal working

Why HashMap caused infinite loop in Java 7

How HashMap behaves in multi-threaded Spring Boot apps



......
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

Step 0: HashMap ko ek cupboard samjho

Socho ek cupboard hai

Usme 16 drawers hain (default capacity = 16)

Har drawer ka ek number hai: 0–15

Har drawer me multiple cheezein aa sakti hain


Drawer = bucket


---

Step 1: Key dalte waqt kya hota hai

map.put("India", 100);

1️⃣ hashCode nikalta hai

Java "India".hashCode() nikalta hai
Maan lo:

hashCode = 234567

Tumhe ye number yaad rakhna nahi hai. Bas samjho ek number mil gaya.


---

2️⃣ Drawer number kaise decide hota hai

Capacity = 16
Formula:

index = (16 - 1) & hash
index = 15 & 234567

Result maan lo:

index = 7

👉 Matlab:

> "India" drawer number 7 me jayega




---

Step 2: Drawer khali hai ya nahi?

Case 1: Drawer 7 khali hai

Direct entry:

[7] → ("India", 100)

Done.


---

Case 2: Drawer 7 me already kuch pada hai

Maan lo pehle se:

[7] → ("USA", 200)

Ab HashMap bolega:

> “Are key same hai kya?”



Ye check karta hai:

key.equals(existingKey)

Agar equals true

Value replace ho jaati hai


Agar equals false

Collision

Dono same drawer me rahenge



---

Step 3: Collision ko kaise rakhta hai?

Java 7 / early Java 8

[7] → ("USA", 200) → ("India", 100)

LinkedList ban jati hai


---

Java 8+ (important)

Agar ek hi drawer me 8 se zyada entries ho jayein:

👉 LinkedList → Red Black Tree

Kyun?

List me search = slow

Tree me search = fast



---

Step 4: get() call karte waqt kya hota hai

map.get("India");

Flow:

1. "India".hashCode()


2. Index nikala (7)


3. Drawer 7 me gaya


4. equals() check


5. Value return



Bas. Itna hi.


---

Step 5: Resize kya hota hai (ye confusing part hota hai)

Default:

Capacity = 16

Load factor = 0.75


Matlab:

16 × 0.75 = 12

Jab entries 12 se zyada ho jaati hain: 👉 HashMap double ho jaata hai

16 → 32

Important baat

Sab purani entries dubara distribute hoti hain

Ye operation mehenga hota hai


Isliye production me:

new HashMap<>(1000);

use karte hain


---

Step 6: equals() aur hashCode() ka real reason

Socho:

hashCode → drawer decide karta hai

equals → exact key match karta hai


Agar tumne hashCode galat likha:

Sab keys same drawer me

Performance gir jaati hai



---

Step 7: Null key confusion

HashMap me sirf 1 null key

Kyun?
Kyunki null ka hashCode nahi hota
Java usko drawer 0 me daal deta hai



---

Interview me simple language me bolna

Agar interviewer bole:

> “Explain HashMap internal working”



Tum bolo:

> “HashMap internally array of buckets use karta hai. Key ka hashCode nikal ke bucket index decide hota hai. Collision hone par pehle LinkedList banti hai aur Java 8 se zyada collisions par Red-Black Tree ban jata hai. Average complexity O(1) hoti hai, but performance hashCode quality par depend karti hai.”



Bas. Enough.


---

Ab ek check:

Ye batao:

1. Collision kya hota hai?


2. equals() kahan use hota hai?


3. Java 8 me LinkedList kab Tree banti hai?



Answer do — fir main next level pe le jaunga
(ConcurrentHashMap ya real interview traps).