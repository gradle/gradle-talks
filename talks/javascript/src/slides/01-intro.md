# Gradle

Expanding to the frontend

## About Me

* Principal Engineer @ [Gradleware](http://gradleware.com/)
* [luke.daley@gradleware.com](mailto:luke.daley@gradleware.com)
* [@ldaley](http://twitter.com/ldaley)
* Gradle core developer
* Former web app developer
* Not a JS ninja

## Gradle

Gradle is build automation evolved. 

Gradle can automate the building, testing, publishing, deployment and more of software packages or other types of projects such as generated static websites, generated documentation or indeed anything else.

* JVM based
* Implemented in Java, Groovy outer layer
* 100% Free Open Source - Apache Standard License 2.0

![Gradle Logo](img/gradle.png)
<p style="text-align: center">[http://www.gradle.org](http://www.gradle.org)</p>

## Gradleware

The company behind Gradle.

* Employs full time engineers
* Gradle consulting, support, development services etc.
* Training: online, public and in-house
* General build automation services

<p style="text-align: center">Germany, Australia, UK, Poland, Austria, Canada and the US.</p>
![Gradleware Logo](img/gradleware.png)
<p style="text-align: center">[http://www.gradleware.com](http://www.gradleware.com)</p> 

## Upcoming Webinars 

To help new and experienced users alike, we've also produced several new webinars [http://gradle.org/events](http://gradle.org/events):

 * [A Gentle Introduction to Gradle](http://gradleware.com/news/83) by [Tim Berglund](http://www.gradleware.com/team#tim-berglund)
 * [Gradle Power Features](http://gradleware.com/news/89) by [Szczepan Faber](http://www.gradleware.com/team#szczepan-faber)
 * [Gradle Roadmap](http://gradleware.com/news/62) by [Hans Dockter](http://www.gradleware.com/team#hans-dockter)
 * [Migrating and Upgrading to Gradle](http://gradleware.com/news/100) by [Szczepan Faber](http://www.gradleware.com/team#szczepan-faber)
 * [Administering Gradle in the Enterprise](http://gradleware.com/news/92) by [Luke Daley](http://www.gradleware.com/team#luke-daley)

### The Gradle Book

Free HTML version of the first O'Reilly Gradle book: [http://gradleware.com/resources](http://gradleware.com/resources)

# Gradle 1.0

Released on June 11!

## Upcoming Trainings

* [Gradle Fundamentals, Online, Jul 23-23](http://gradleware.com/training) by [Tim Berglund](http://www.gradleware.com/team#tim-berglund)
* [Extending Gradle, Online, Jul 24-25](http://gradleware.com/training) by [Szczepan Faber](http://www.gradleware.com/team#szczepan-faber)
* [Gradle In-Depth, Chicago, Oct 9-11](http://gradleware.com/training) by [Gradle Core Developer](http://www.gradleware.com/team)

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
 