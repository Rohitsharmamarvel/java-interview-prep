Alright Malaik, let’s go straight into JavaScript and cover every topic interviewers love to grill on — basics to advanced — in a clean, simple way so you can recall fast during the interview.

I’ll keep it crisp, clear, and example-driven.


---

1. Execution Context

This is the foundation of JS.

When JS runs your code, it creates:

Global Execution Context

Function Execution Context

Eval Execution Context


Each context has:

1. Memory (variable environment)


2. Code execution phase



What this really means is:
JS first stores all variables and functions, then runs the code.


---

2. Hoisting

Variables and functions are moved to top of their scope during memory creation.

Example:

console.log(a); // undefined
var a = 10;

Functions:

sayHello(); // works
function sayHello() {}


---

3. var vs let vs const

var

Function scoped

Hoisted

Can re-declare


let

Block scoped

Hoisted but in TDZ

Cannot re-declare


const

Block scoped

Must be initialized

Reference cannot change



---

4. Temporal Dead Zone (TDZ)

Zone between hoisting and initialization.

console.log(a); // error
let a = 5;


---

5. Data Types

JS has:

Number

String

Boolean

Undefined

Null

Object

Symbol

BigInt


typeof null → object (interview favorite)


---

6. Primitive vs Reference Types

Primitives stored by value
Objects stored by reference

let x = {a: 1};
let y = x;
y.a = 2;
console.log(x.a); // 2


---

7. this Keyword

this refers to different things based on context:

Global → window

Object method → that object

Function → undefined in strict mode

Arrow function → does NOT have its own this



---

8. Arrow Function

Why it's asked? Because behavior changes.

No own this

No own arguments

Cannot be used with new

Short syntax


const sum = (a,b) => a + b;


---

9. Higher Order Functions

Function that takes or returns another function.

function greet(fn) {
  fn();
}

Used heavily in React.


---

10. Callback & Callback Hell

setTimeout(() => {
  console.log("first");
  setTimeout(() => {
    console.log("second");
  }, 1000);
}, 1000);


---

11. Promises

Promise represents a future value.

const p = new Promise((resolve, reject) => {
  resolve("done");
});


---

12. async / await

Cleaner async code.

async function getData() {
  const res = await fetch(url);
  return res.json();
}


---

13. Event Loop

JS is single-threaded.
Event loop handles async callbacks.

Order:

Call Stack

Web APIs

Callback Queue

Microtask Queue (Promises)



---

14. Closures

Function remembers outer scope even after execution finishes.

function outer() {
  let x = 10;
  return function inner() {
    console.log(x);
  };
}

inner() still has access to x.


---

15. Prototypal Inheritance

Every JS object has a hidden property __proto__.

const person = {talk(){console.log("hi");}};
const me = Object.create(person);
me.talk(); // hi


---

16. Constructor Function & new

function Car(name){
  this.name = name;
}

const c = new Car("BMW");


---

17. Classes in JS

Syntactic sugar over prototypes.

class Animal {
  speak() {
    console.log("hello");
  }
}


---

18. Spread & Rest Operator

Spread → expand
Rest → collect

const arr = [...one, ...two];

function sum(...nums) { }


---

19. Destructuring

Extract values easily.

const {name, age} = person;
const [a,b] = arr;


---

20. Map, Filter, Reduce

map

arr.map(x => x * 2)

filter

arr.filter(x => x > 10)

reduce

arr.reduce((a,b) => a + b, 0)


---

21. Debouncing & Throttling

Debounce

Run only after user stops typing.

Throttle

Run at fixed interval.

Common in search boxes, scroll events.


---

22. Deep Copy vs Shallow Copy

shallow:

let b = {...a}

deep:

JSON.parse(JSON.stringify(obj))


---

23. Event Bubbling & Capturing

Bubbling → child → parent
Capturing → parent → child

Use e.stopPropagation() to stop flow.


---

24. Event Delegation

Instead of adding event to each child, add on parent.

parent.addEventListener('click', (e)=> {
  if(e.target.matches('.btn')) {...}
});


---

25. Strict Mode

"use strict";

Catches silent errors.


---

26. Modules

export function add(){}
import {add} from './file.js'


---

27. JSON, Parsing & Stringifying

let str = JSON.stringify(obj);
let obj2 = JSON.parse(str);


---

28. Local Storage / Session Storage

localStorage → permanent
sessionStorage → tab life


---

29. Fetch API

fetch(url)
  .then(res => res.json())


---

30. Error Handling

try { }
catch(e) { }


---

If you want, I can now:

Create 80+ practice interview questions for JS

Make you a JS revision cheat sheet

Start React next, or

Take mock interview for JS


Just tell me what you want next.
  ........................................................
  .........................................................







  Got you Malaik.
Let’s restart JavaScript from absolute basics and then build up to advanced concepts.
Every topic will have simple explanation + clean example so you understand it instantly.

We’ll go step by step.


---

1. What is JavaScript?

JavaScript is a single-threaded, dynamic, interpreted language used to make web pages interactive.

It runs in the browser and has access to:

DOM

Events

Timers

Web APIs



---

2. How JavaScript Executes Code

JS engine follows two steps:

Step 1: Memory Creation Phase

All variables and functions are stored in memory.

Step 2: Execution Phase

Lines run one-by-one.

Example:

console.log(a);
var a = 10;

Output → undefined (because JS stored a: undefined in memory first)


---

3. Hoisting

JS moves declarations to the top of their scope.

Example:

console.log(num); 
var num = 5;

Output: undefined

Because behind the scenes:

var num;   // hoisted
console.log(num);
num = 5;


---

4. var vs let vs const

var

Function scoped

Can re-declare

Hoisted → initialized as undefined


Example:

var x = 10;
var x = 20; // allowed

let

Block scoped

Cannot re-declare

Hoisted but NOT initialized → TDZ


let a = 10;
a = 20; // ok
let a = 30; // error

const

Block scoped

Must be initialized

Cannot change value


const pi = 3.14;
pi = 3.15; // error


---

5. Data Types

Two categories:

Primitive

Stored by value in stack

number

string

boolean

undefined

null

symbol

bigint


Example:

let a = 10;
let b = a;
b = 20;

console.log(a); // 10

Reference (Objects)

Stored by reference in heap

object

array

function


Example:

let p = { age: 25 };
let q = p;
q.age = 30;

console.log(p.age); // 30


---

6. Functions

Two types:

a) Normal function

function add(a, b) {
  return a + b;
}

b) Arrow function

const add = (a, b) => a + b;

Arrow functions do NOT have:

their own this

arguments keyword

prototype



---

7. this Keyword

this = depends on HOW a function is called.

Example 1: Inside Object

const user = {
  name: "Malaik",
  show() {
    console.log(this.name);
  }
};

user.show(); // "Malaik"

this → refers to object

Example 2: Normal Function (strict mode)

function f() {
  console.log(this);
}
f(); // undefined

Example 3: Arrow Function

const obj = {
  name: "Malaik",
  fn: () => console.log(this)
}

obj.fn(); // window

Arrow takes this from outer scope.


---

8. Arrays

Most used methods:

const arr = [1,2,3];

map

arr.map(x => x * 2); // [2,4,6]

filter

arr.filter(x => x > 1); // [2,3]

reduce

arr.reduce((sum, val) => sum + val, 0); // 6

forEach

arr.forEach(x => console.log(x));


---

9. Objects

const person = {
  name: "Rohit",
  age: 25
};

Access:

person.name
person['age']


---

10. Spread & Rest Operator

Spread → Expands

const a = [1,2];
const b = [...a, 3,4];

Rest → Collects

function sum(...nums) {
  return nums.reduce((a,b)=>a+b);
}


---

11. Destructuring

Array:

const [a,b] = [10,20];

Object:

const {name, age} = person;


---

12. Call, Apply, Bind

Used to manually set this.

call

function hello() {
  console.log(this.name);
}

hello.call({name: "Malaik"});

apply

Same as call but arguments in array.

bind

Returns new function with fixed this.


---

13. Closures

Inner function remembers outer variable.

function outer() {
  let x = 10;
  return function inner() {
    console.log(x);
  }
}

const fn = outer();
fn(); // 10

Closures are used in:

private variables

debouncing

caching



---

14. Asynchronous JS

JS is single-threaded.
Async tasks are handled using:

Callbacks

Promises

async/await



---

15. Promises

const p = new Promise((res, rej) => {
  res("done");
});

p.then(value => console.log(value));


---

16. async / await

Cleaner async code.

async function getData() {
  const res = await fetch(url);
  const data = await res.json();
}


---

17. Event Loop

Order:

1. Call Stack


2. Web APIs


3. Callback Queue


4. Microtask Queue (Promises)



Example:

console.log("A");

setTimeout(() => console.log("B"), 0);

Promise.resolve().then(() => console.log("C"));

console.log("D");

Output: A
D
C
B

Because microtasks (Promises) run before callbacks.


---

18. Event Bubbling & Capturing

Bubbling → child → parent
Capturing → parent → child

element.addEventListener('click', fn, true);  // capturing
element.addEventListener('click', fn, false); // bubbling


---

19. Event Delegation

Instead of adding event to every child, add to parent.

document.getElementById("list").addEventListener("click", (e)=>{
  if(e.target.tagName === "LI") {
    console.log("Item clicked:", e.target.textContent);
  }
});


---

20. LocalStorage / SessionStorage

localStorage.setItem("name", "Malaik");
localStorage.getItem("name");


---

21. JSON

JSON.stringify(obj)
JSON.parse(str)


---

22. Deep Copy vs Shallow Copy

Shallow:

let b = {...a};

Deep:

JSON.parse(JSON.stringify(a));


---

23. DOM Manipulation

document.getElementById("box").innerText = "Hello";


---

24. Modules (import / export)

export const add = () => {};
import { add } from './main.js';


---

If you want, I’ll now take you through:

✔️ React next

or

✔️ JavaScript interview questions (50+ with answers)

or

✔️ Mock interview

Just tell me what you want first.
