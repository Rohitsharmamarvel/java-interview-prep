
---

1. Spread Operator ( ... )

Meaning: Spread expands an array or object into individual elements.

Think of it like opening a box and throwing everything out.

A) Spread with Arrays

const nums = [1,2,3];
const newArr = [...nums, 4, 5];

console.log(newArr); // [1,2,3,4,5]

B) Spread with Objects

const user = { name: 'Rohit', age: 25 };
const updated = { ...user, age: 26 };

console.log(updated); // {name: "Rohit", age: 26}

This is VERY common in React when updating state.

C) Spread for Cloning

const copy = [...nums];


---

2. Rest Operator ( ... )

Meaning: Rest collects multiple values into a single array.

Think of it like packing items back into a box.

A) Rest in Function Parameters

function sum(...nums) {
  return nums.reduce((a,b) => a + b, 0);
}

sum(1,2,3,4); // 10

B) Rest in Object Destructuring

const user = { name: "Malaik", age: 25, city: "Delhi" };
const { name, ...info } = user;

console.log(name); // "Malaik"
console.log(info); // {age:25, city:"Delhi"}


---

Spread vs Rest (Easy Comparison)

Operator	What it does	Example

Spread	Expands / breaks apart	[...arr]
Rest	Collects / groups together	(...args)



---

3. Practical Uses in Frontend Applications (Very Important)

These are the things interviewers expect.


---

A) Updating React State

React state is immutable, so you must create a copy using spread.

Updating array state:

setItems([...items, newItem]);

Updating object state:

setUser({...user, name: "Malaik"});

Without spread, you accidentally mutate the object → React won’t re-render.


---

B) Passing Unlimited Props in Components

Rest helps you collect props.

function Button({label, ...props}) {
  return <button {...props}>{label}</button>;
}

Now you can write:

<Button label="Click" onClick={handleClick} disabled />


---

C) Merging API responses

Spread helps when combining data.

const finalData = { ...userDetails, ...accountDetails };


---

D) Removing a value from a list

const removeId = 3;
const newList = list.filter(item => item.id !== removeId);

Or update with spread:

const updated = [...list.slice(0, index), ...list.slice(index+1)];


---

E) Copying objects before modifying

This ensures immutability.

const updated = { ...obj, status: "active" };


---

F) Passing Variable Arguments

Rest allows unlimited parameters.

function logAll(...values) {
  console.log(values);
}


---

G) Context / Redux reducers often use spread

return {
  ...state,
  user: action.payload
}


---

4. Real-life React Example (Very Common Interview Question)

Updating form fields:

function handleChange(e) {
  setForm({
    ...form,
    [e.target.name]: e.target.value
  });
}

Spread is the hero here.


---

5. Real-life API Data Merge

const response = await fetch("/user");
const user = await response.json();

const finalUser = {
  ...user,
  isLoggedIn: true
};


---

If you want, I can explain next:

Destructuring

this keyword

Promises / async

Event loop

Closures

React basics


Tell me what you want next.
