# Background

What are we talking about?

## Automation is personal

Only the simplest of software can be built in _near identical_ ways. However, “convention over configuration” let us stop inventing the wheel.

On the other hand, rigid conventions very quickly inhibit innovation and efficiency. Building real world software is very often not a generally prescriptable affair, with custom functionality or compliance requirements.

In the modern software world of continuous delivery and frequent releases, automation needs to be ambitious, effective and pervasive.

## Whose conventions?

Conventions are great, and Gradle embraces them. However, whose conventions are these? 

**You might want to extend the conventions.**

* e.g. all projects use CheckStyle for static analysis

**You might want to replace general conventions with your own.**

* e.g. “main” source is meaningless to me

## Gradle is a toolkit

Gradle is a collection of tools, like Ant.

Unlike Ant, Gradle also contains tools for standardisation, reuse and conventions. 

* Plugins
* Plugin stacks
* Configuration Rules
* DSL Extensions
* Init Scripts

## Overview

* Deep API
* Configuration Rules
* Plugins
* Init Scripts
* Custom Distributions

## Deep API

Many aspects of Gradle are configurable. It pays to dig through the API doc.

**Example:** How do I enforce that no one can compile against Hibernate?

*[demos/01-deep-api-dependency-rules]*

**Example**: How do I verify the existence of a “`test`” task?

*[demos/02-deep-api-require-task]*

## Deep API (cont.)

A design philosophy of Gradle is to give you deep access to the entire model.

The API is often described as an Onion Skin API.

Reading the Gradle Javadoc does not mean you are doing something wrong.

## Configuration Rules

Gradle provides many ways to configure future objects.

You often want to configure things that you don't know about.

**Example:** How do configure all test tasks to be as parallel as they can be?

*[demos/03-configuration-rule-parallel-tests]*

**Example:** How do I disallow Hibernate as a first level dependency at all (e.g. `compile`, `test`, `runtime`)?

*[demos/04-configuration-rule-dependency-rules]*

## Configuration Rules (cont.)

Based on [`DomainObjectCollection`](http://gradle.org/docs/current/javadoc/org/gradle/api/DomainObjectCollection.html) and [`NamedDomainObjectCollection`](http://gradle.org/docs/current/javadoc/org/gradle/api/NamedDomainObjectCollection.html).

Java Collections that support “liveness.”

    def numbers = new DefaultDomainObjectCollection(Number, [1, 2])
    
    def evenNumbers = numbers.matching { it % 2 == 0 }
    assert evenNumbers == [2]
    
    numbers << 4
    assert evenNumbers == [2, 4]

## Configuration Rules (cont.)

Many objects you use in Gradle are live collections.

* `project.tasks` - [TaskContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/tasks/TaskContainer.html)
* `project.plugins` - [PluginContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/plugins/PluginContainer.html)
* `project.configurations` - [ConfigurationContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/artifacts/ConfigurationContainer.html)
* `project.repositories` - [RepositoryHandler](http://gradle.org/docs/current/javadoc/org/gradle/api/artifacts/dsl/RepositoryHandler.html)
* `project.sourceSets` - [SourceSetContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/tasks/SourceSetContainer.html)
* `project.«configuration».dependencies` - [DependencySet](http://gradle.org/docs/current/javadoc/org/gradle/api/artifacts/DependencySet.html)

## Configuration Rules (cont.)

Some common use cases…

Run some code against all things that exist now and in the future:

    tasks.all { }

Do something when new things are added:

    repositories.whenObjectAdded { }
    
Do something when things are removed:

    repositories.whenObjectRemoved { }

## Plugins

Plugins are just reusable build script code. That is, there is no functionality that only plugins can perform.

Plugins can:

* Add new capabilitiies/tools
* Add new conventions or extend tighten existing conventions

Ideally, they do only one of those.

## Adding new capabilities

Adding the ability to perform some new function. For example, to compile CoffeeScript.

Out of scope for this Webinar.

Keep an eye on [http://gradle.org/news](http://gradle.org/news) for announcements on a Webinar focussed on this topic.

## Adding/Extending Conventions

Configures the project(s) to do useful work by adding/configuring tools and capabilities.

May provide defaults, may enforce standards.

Example conventions:

* Integration tests will be their own source set and task
* Dependencies always come from our corporate repository
* Source code must be Java 1.5 compatible

## Plugin Types

Plugins can be packaged as either scripts or objects.

Script plugins are `.gradle` files.

[05-plugins-script-plugin]
  