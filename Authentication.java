Good question. Most people “use” authentication daily but can’t explain how it works internally. Let’s make this interview-ready.

We’ll cover:

1. Session + Cookies (traditional auth)


2. JWT (token based auth)


3. OAuth (authorization / social login)
→ how they work, where data is stored, and whether they go in every request.



Let’s go step by step.


---

⭐ First — What is Authentication vs Authorization

Authentication = Who are you?
Example: login with username/password.

Authorization = What can you access?
Example: admin vs normal user.

👉 All 3 methods below help verify identity after login.


---

✅ 1. Session + Cookies (Traditional Authentication)

This is the oldest and most widely used approach.
Used in: banking sites, classic web apps, Spring Security default login.


---

🔹 How it works (Flow)

Step 1 — Login request

User sends:

username + password → server

Server checks DB.


---

Step 2 — Server creates SESSION

If valid:

Server creates session object in memory/database

Example session data:

sessionId: ABC123
userId: 45
role: USER

👉 Stored on server side.


---

Step 3 — Server sends COOKIE

Server returns response:

Set-Cookie: sessionId=ABC123

Browser automatically stores it.

👉 Cookie stored in browser.


---

Step 4 — Every request includes cookie automatically

Next request:

GET /profile
Cookie: sessionId=ABC123

Browser attaches it automatically.

Server:

find session → verify user → allow access


---

🔹 Where things are stored

Item	Stored Where

User data	Server
Session object	Server memory / Redis / DB
Session ID	Browser cookie


👉 Important interview line:

Session auth is server-side stateful authentication.


---

🔹 Does it go in every request?

✅ YES

Browser automatically sends cookie in every request to that domain.


---

🔹 Advantages

Very secure (server controlled)

Easy logout

Easy revoke access



---

🔹 Problems

Server must store session → memory heavy

Not scalable for microservices

Hard for mobile apps

Requires sticky sessions or shared storage



---

🔥 Interview One-Line Definition

> Session authentication stores user data on server and uses cookies to identify the user on every request.




---

✅ 2. JWT (JSON Web Token — Token Based Authentication)

Modern apps use this.
Used in: microservices, REST APIs, mobile apps.

👉 Stateless authentication.


---

🔹 Core idea

Instead of storing session on server:

Server gives signed token to client.
Client sends token every request.
Server verifies signature.

No session storage.


---

🔹 JWT Structure

JWT = 3 parts:

HEADER.PAYLOAD.SIGNATURE

Example:

eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQ1fQ.sdfsdf23423


---

Payload contains user info:

{
  userId: 45,
  role: "USER",
  expiry: 2 hours
}

👉 Token itself contains identity.


---

🔹 How JWT works (Flow)

Step 1 — Login

username + password → server


---

Step 2 — Server generates JWT

JWT signed with secret key

Server sends:

token = JWT


---

Step 3 — Client stores token

Stored in:

LocalStorage

SessionStorage

Cookie (optional)



---

Step 4 — Every request sends token

Authorization: Bearer <JWT>

Server verifies signature → allow access.


---

🔹 Where stored

Item	Stored Where

User data	Inside token
Token	Browser localStorage / cookie
Session	Not stored


👉 Server stores nothing.


---

🔹 Does it go in every request?

✅ YES — manually sent via header.

Authorization: Bearer token


---

🔹 Advantages

Stateless → scalable

Perfect for microservices

No session storage

Fast



---

🔹 Problems

Hard to revoke token

Token size bigger

Security risk if stolen

Logout not straightforward



---

🔥 Interview One-Line Definition

> JWT is stateless authentication where user identity is stored in a signed token sent with every request.




---

✅ 3. OAuth (Authorization Framework — Social Login)

OAuth is NOT a login system directly.

👉 It allows apps to use third party identity.

Example:

Login with Google

Login with Facebook

Login with GitHub



---

🔹 Real World Example

You click:

Login with Google

Your app never sees your password.

Google authenticates you.


---

🔹 How OAuth Works (Simplified Flow)

Step 1

User clicks:

Login with Google


---

Step 2

Redirect to Google login.


---

Step 3

Google authenticates user.


---

Step 4

Google sends authorization token to your app.


---

Step 5

Your app creates session or JWT for user.


---

🔹 Where stored

Depends on implementation:

Usually OAuth → JWT or session after login

OAuth itself gives access token



---

🔹 Does token go in every request?

Depends.

OAuth access token → yes for API calls

After login → your app may use session/JWT



---

🔥 Interview One-Line Definition

> OAuth is an authorization framework that allows third party authentication without sharing user credentials.




---

⭐ Super Important Interview Comparison

Session vs JWT vs OAuth

Feature	Session	JWT	OAuth

Type	Authentication	Authentication	Authorization
Storage	Server	Client	External provider
Stateful	Yes	No	Depends
Scalable	No	Yes	Yes
Every request	Cookie auto	Header token	Token
Server memory needed	Yes	No	No
Use case	Traditional apps	APIs/microservices	Social login



---

⭐ When to Use What (Interview Gold)

Use Session when:

Monolithic app

High security

Traditional web app



---

Use JWT when:

Microservices

REST APIs

Mobile apps

Distributed systems



---

Use OAuth when:

Social login

Third party access

External identity provider



---

⭐ Simple Memory Trick

Session → server remembers you
JWT → you carry identity yourself
OAuth → someone else verifies you


---

If you want next level understanding, I can show:

✅ Spring Boot authentication flow internally
✅ How Spring Security uses session vs JWT
✅ Real production architecture
✅ How cookies vs localStorage security works
✅ Refresh tokens vs access tokens
✅ JWT vs session interview tricky questions

Just tell me.