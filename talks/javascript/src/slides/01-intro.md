# Gradle

Expanding to the frontend

## About Me

* Principal Engineer @ [Gradleware](http://gradleware.com/)
* [luke.daley@gradleware.com](mailto:luke.daley@gradleware.com)
* [@ldaley](http://twitter.com/ldaley)
* Gradle core developer
* Former web app developer
* Not a JS ninja

## What we will talk about

* Some context
* Existing 3rd party plugins
* Gradle fundamentals
* **New** core functionality

## Some context

* JavaScript is a booming business
* Mo' JavaScript = mo' problems
* Weak history of tooling and build automation
* No build time dependency management conventions
* No well established structural/process conventions
* Ant, Make and Node.js most popular build environments

## More than scripts

* Bundling
* Minification
* Testing
* Code Analysis 
* Transpiling
* *Reuse*

We now create JavaScript libraries, not scripts. This has drastic implications.

## Dependency Challenges

### Buildtime 

* No public hosting infrastructure (e.g Maven Central)
* No descriptor format
* What is a dependency? (e.g. accompanying CSS and images?)

### Runtime 

* Managing visibility
* One namespace

There are several initiatives tackling this, based around the [Universal Module Definition](https://github.com/umdjs/umd). For example,  [require.js](http://requirejs.org/).

## Why bother?

Why is dependency management for JavaScript important?

### Buildtime 

As JavaScript code bases grow, manually answering the question “what are my dependencies?” becomes harder and error prone.

### Runtime

Versioning, performance, and protecting the global namespace.

With a dependency model, we can automate more.

## Project conventions?

* What conventions make sense for a JavaScript project?
* Embedded or standalone?
* Intra module dependencies?
* Framework specific conventions

There are not a lot of “best practice” guides out there for this… yet.
 