# Looking forward…

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
