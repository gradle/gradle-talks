## "general" code

Gradle gives you tools and constructs to _generalise_ logic.

Important innovation over earlier generation build tools.

Useful for achieving reuse, but also for testability.

## "general" code tools

The following are examples of Gradle characteristics that support generalisation:

* Plugins (as classes)
* Configuration rules
* Configuration actions
* Domain modelling

## The Gradle way

Use such constructs to turn your build into more of a “program” than a spaghetti “script”.

We have better tools and techniques for testing programs.

## Specific to general

Making code more general makes it more testable.

* Controlled dependencies
* Focussed, tighter, contracts between collaborators
* Smaller scope
* More isolated from external change

Generalisation doesn't have to be for reuse.

## Types of tests

We can divide them into three groups:

* Unit
* Integration
* Functional

## Types of tests

Purpose…

* Unit
    * Test isolated logic
* Integration
    * Test boundary assumptions
* Functional
    * Does the whole thing work when put together?

## Types of tests

Characteristics…

* Unit
    * Cheap, easy to setup - fast to run
* Integration
    * More real collaborators, slower
* Functional
    * Full stack, slow, expensive and awkward

# Case Study

JsTestDriver integration

## JsTestDriver

By Google.

* Java friendly JS unit testing tool
* Requires coordinating several processes
* Requires coordinating a browser
* Requires some Gradle like conventions

# Demo

JsTestDriver plugin

## Types of tests

In the plugin context…

* Unit tests
    * Is each class internally coherent?
    * Are we doing what we expect to be to the collaborators?
* Integration tests
    * Can we actually correctly drive JsTestDriver?
    * Are we integrating with the Gradle model correctly?
* Functional
    * Does this do the right thing for the user?
    
# Demo 

The tests

---

* Show the structure of the code
    * Show the encapsulation and SRP
* Show the unit tests
    * Each part in isolation
* Show the integration tests
    * Actually exercising the code
    * Doesn't require Gradle machinery
* Show the functional tests
    * Explain that its using spike code
    * Explain how it's effectively black box

## Gradle unit tests

Laser focus on small code units.

* Not Gradle specific
* All traditional tools/techniques
* Easiest to write/debug/execute

Must be designed for.

Better the design … easier to unit test.

## Gradle integration tests

Testing the boundaries.

* Integrate with other APIs
* Verify collaborator behaviour
* Really exercise the code (as possible)

Typically requires some real moving parts.

Easier to write/debug than functional tests.

## Gradle functional tests

Fundamental question: Can a user use this?

* Does it work? (happy day)
* Does it fail properly? (sad day)
* Is the feedback good? (e.g. error messages)
* Does it integrate with Gradle properly?
    * Incremental build
    * Integration with other plugins
    * etc.

Can be easy to write simple cases.

Hard to test corner cases.

Hard to debug.

## Strategies

The testing pyramid:

* A few functional tests on top
* Good integration test coverage in the middle
* A solid base of unit tests

Avoid the temptation to invert the pyramid.

## Strategies

Some guiding ideas:

* Design for testability
    * Single Responsibility Principle
    * Use OO modelling
    * Don't couple to the Gradle API
* You don't need functional tests for everything
* Don't over use inline closures (`Action` is more testable)
* Keep the real work out of the task (unless it's trivial)

# Looking forward

## Improved Support

We will be working on better testing support.

Design spec: [https://github.com/gradle/gradle/blob/master/design-docs/testing-user-build-logic.md](https://github.com/gradle/gradle/blob/master/design-docs/testing-user-build-logic.md)

* Unit tests
    * ??
* Integration tests
    * Better factories for test doubles
    * Dependency injection hijacking
* Functional tests
    * An execution framework
    * Cross version support
    * IDE compatible

# Questions?

# Thank you
