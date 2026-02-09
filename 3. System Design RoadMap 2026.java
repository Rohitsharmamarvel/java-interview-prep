

SYSTEM DESIGN ROADMAP

Timeline: 4–5 months (you can compress or stretch)

Think of this as leveling up from engineer → system thinker.


---

PHASE 0 — HOW TO THINK (Week 0)

Before touching topics, lock this in:

For every concept, ask:

1. What problem existed?


2. How was it solved earlier?


3. Why did that break at scale?


4. What do we use now?


5. What trade-offs did we accept?



If you don’t frame answers this way, system design becomes memorization.


---

PHASE 1 — FOUNDATIONS (Weeks 1–2)

1. How distributed systems behave

Focus on failure, not success.

Latency vs throughput

Network is unreliable

Partial failures

Timeouts

Retries and retry storms

Backpressure


Key idea:

> A system can be “up” and still be unusable.




---

2. Consistency basics

You must speak confidently here.

Strong vs eventual consistency

CAP theorem (trade-offs, not theory)

Read-after-write consistency

Quorums (R + W > N)


Interview signal: “I choose availability over consistency here because…”


---

PHASE 2 — DATABASES & STORAGE (Weeks 3–6)

3. Databases: evolution, not types

Start here before sharding.

Earlier

Single RDBMS

Vertical scaling

ACID everywhere


Problems

Scale limits

Cost

Write bottlenecks


Then

Read replicas

Leader–follower

Caching


Now

Sharding + replication

Eventual consistency

Polyglot persistence



---

4. Sharding (VERY IMPORTANT)

Study in this exact order:

a. What is sharding

Horizontal partitioning

Why vertical scaling fails


b. Sharding strategies

Range-based

Hash-based

Directory-based


c. Problems sharding introduces

Cross-shard queries

Transactions

Hot keys

Rebalancing


d. Modern solutions

Consistent hashing

Virtual nodes

Application-level routing


Interview line: “Sharding solves scale but increases operational complexity.”


---

5. Replication & durability

Sync vs async replication

Leader election

Failover

Data loss trade-offs



---

PHASE 3 — CACHING & PERFORMANCE (Weeks 7–8)

6. Caching (deep, not surface)

Earlier:

In-memory cache


Now:

Redis

CDN

Multi-level cache


Learn:

Cache-aside

Write-through

Write-back

TTL strategies

Cache invalidation


Always ask: “What happens when cache is wrong?”


---

PHASE 4 — TRAFFIC & COMMUNICATION (Weeks 9–10)

7. Load balancing

L4 vs L7

Round-robin vs least connections

Health checks

Sticky sessions


8. Reverse proxy

Nginx, Envoy

TLS termination

Rate limiting

Auth

Observability


Manager thinking: “This is where we protect the system.”


---

PHASE 5 — ASYNC, EVENTS & SCALE (Weeks 11–12)

9. Messaging systems

Earlier:

Direct API calls


Now:

Queues and streams


Learn:

Kafka vs SQS vs RabbitMQ

At-least-once vs exactly-once

Idempotency

Consumer groups


Interview signal: “I use async to isolate failures.”


---

PHASE 6 — CONSISTENCY & CONCURRENCY (Weeks 13–14)

10. Distributed coordination

Distributed locks

Leader election

Why two-phase commit is painful


11. Collaboration systems (your example)

Operational Transformation

CRDTs

Centralized vs decentralized control


Key insight: “There’s no perfect consistency without cost.”


---

PHASE 7 — RELIABILITY & RESILIENCE (Weeks 15–16)

12. Making systems survive chaos

Earlier:

Restart server


Now:

Circuit breakers

Rate limiting

Bulkheads

Timeouts + retries


Always answer: “How does the system degrade?”


---

PHASE 8 — OBSERVABILITY & OPS (Weeks 17–18)

13. Visibility

Logs

Metrics

Traces

SLIs and SLOs


Manager question: “How fast can we detect and recover?”


---

PHASE 9 — SYSTEM DESIGN PRACTICE (ONGOING)

Design these systems repeatedly:

URL shortener

Google Docs

Instagram feed

Payment system

Notification system


Each time:

Start simple

Add scale

Add failure

Add trade-offs



---

HOW TO PREPARE FOR INTERVIEWS

When answering:

1. Clarify requirements


2. Design basic version


3. Identify bottlenecks


4. Scale step-by-step


5. Discuss trade-offs



Never jump straight to tech names.


---

