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
