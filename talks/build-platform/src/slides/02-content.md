# Platform?

What are we talking about?

## Build Platform

* Portfolio of builds
* Significant commonalities
* Significant differences
* Desire for consistency
* Desire for control
* High potential for automation/productivity

## Assertions… 

### Very few real software projects are alike.

* Practices are evolving
* Tools are evolving

Everything is changing.

## Assertions…

### Modern automation needs to be…

* effective
* personal
* ambitious
* pervasive

Automate or die.

## Assertions…

### Convention over configuration… 

* let us stop inventing the wheel
* allowed greater collaboration
* is not the silver bullet

## Assertions…

### Rigid conventions inhibit innovation and efficiency.

* Abstraction Inversion leads to all kinds of problems.
* Conventions need to be scalable and malleable.

## Whose conventions?

Gradle embraces *your* conventions. 

**You might want to extend the conventions.**

* e.g. all projects use CheckStyle for static analysis

**You might want to replace general conventions with your own.**

* e.g. “main” source is meaningless to me

## Gradle is a toolkit

Gradle is a collection of tools, like Ant.

Unlike Ant, Gradle also contains tools for standardization, reuse and conventions.

* Plugins
* Plugin stacks
* Configuration Rules
* DSL Extensions
* Init Scripts

## How?

* Deep API
* Configuration Rules
* Plugins
* Init Scripts
* Custom Distributions

## Deep API

The Gradle API can be used for reflecting on the model.

**Example:** How do I enforce that no one can compile against `org.codehaus.plexus` dependencies?

**Example**: How do I verify the existence of a “`test`” task?

## Deep API

Gradle aims to give deep access to the entire model.

* Simple concrete outer DSL layer (*developers*)
* Increasingly abstract as you move inwards (*build & release engineers*)

Reading the Javadoc does not mean you are doing something wrong.

## Configuration Rules

Gradle provides many ways to configure speculatively.

You often want to configure things that you don't know about.

**Example:** How do configure all test tasks to be as parallel as they can be?

**Example:** How do I disallow `org.codehaus.plexus` dependencies at all (e.g. `compile`, `test`, `runtime`)?

## Configuration Rules

Based on [`DomainObjectCollection`](http://gradle.org/docs/current/javadoc/org/gradle/api/DomainObjectCollection.html) and [`NamedDomainObjectCollection`](http://gradle.org/docs/current/javadoc/org/gradle/api/NamedDomainObjectCollection.html).

Java Collections that support “liveness”.

    def numbers = new DefaultDomainObjectCollection(Number, [1, 2])

    def evenNumbers = numbers.matching { it % 2 == 0 }
    assert evenNumbers == [2]

    numbers << 4
    assert evenNumbers == [2, 4]

## Configuration Rules

Many objects you use in Gradle are live collections.

* `project.tasks` - [TaskContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/tasks/TaskContainer.html)
* `project.plugins` - [PluginContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/plugins/PluginContainer.html)
* `project.configurations` - [ConfigurationContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/artifacts/ConfigurationContainer.html)
* `project.repositories` - [RepositoryHandler](http://gradle.org/docs/current/javadoc/org/gradle/api/artifacts/dsl/RepositoryHandler.html)
* `project.sourceSets` - [SourceSetContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/tasks/SourceSetContainer.html)
* `project.«configuration».dependencies` - [DependencySet](http://gradle.org/docs/current/javadoc/org/gradle/api/artifacts/DependencySet.html)

## Configuration Rules

Run some code against all things that exist now and in the future:

    tasks.all { }

Do something when new things are added:

    repositories.whenObjectAdded { }

Do something when things are removed:

    repositories.whenObjectRemoved { }

## Dependency Management

This problem is not solved.

Many projects require from more sophisticated dependency management.

**Example:** implicitly replace a dependency globally.

**Example:** semantic version aware conflict resolution.

## Plugins

Plugins are just reusable build script code.

Low cost of entry.

* Add new capabilities/tools (often with defaults)
* Add new conventions
* tighten existing conventions

## Adding new capabilities

Adding the ability to perform some new function. 

For example, to compile CoffeeScript.

*That's a different talk.*

## Adding/Extending Conventions

May provide defaults, may enforce standards.

Example conventions:

* Use JUnit 4.10 for tests
* Integration tests will be their own source set and task
* Dependencies always come from our corporate repository
* Source code must be Java 1.5 compatible

## Plugin Types

Plugins can be packaged as either scripts or objects.

Script plugins are just `.gradle` files.

Object plugins are compiled and on the classpath.

## DSL Extensions

DSL extensions add new constructs to the build language.

The canonical example of an extension would be the source set container.

    sourceSets {
      integTest {}
    }

**Example:** provide a DSL for repo selection.

## Plugin Stacks

Sophisticated plugins, like the Java plugin, are comprised of stacks.

`BasePlugin` ← `JavaBasePlugin` ← `JavaPlugin`

* `BasePlugin` = general build features (language agnostic)
* `JavaBasePlugin` = sensible defaults for Java tools
* `JavaPlugin` = conventions for a Java project

## Your own 'java' plugin

Pick your own starting point.

    apply plugin: 'java-base'

Get the functionality *without the strong conventions*, if you need it.

## Init Scripts

Gradle [init scripts](http://gradle.org/docs/current/userguide/userguide_single.html#init_scripts) facilitate cross project configuration.

Looked for at:

* *$USER_HOME*/.gradle/init.gradle
* *$USER_HOME*/.gradle/init.d/*.gradle
* *$GRADLE_HOME*/init.d/*.gradle

Can also be specified with `-I` command line arg.

## Init Scripts (cont.)

What can build scripts do?

* Load plugins
* Specify plugin repositories
* Environmental configuration (CI server specific conf)
* Register listeners
* More…

Can also do _anything_ that you can do in a build script.

**Example:** pre load a plugin.

## The Gradle Wrapper

A self bootstrapping build system.

## The Gradle Wrapper

<p style="text-align: center">
  <a href="http://www.gradleware.com/video/using-the-gradle-wrapper">
    <img src="img/wrapper.gif" style="box-shadow: 0px 0px 6px #888;"/>
    Check out the Gradle Wrapper screencast!
  </a>
</p>

The wrapper can be obtained from an arbitrary HTTP URL.

## Custom Distributions

Embed your init script inside your Gradle distribution.

Enforce use of your Gradle distribution via the Wrapper.

**Example:** building a custom distribution.

## Roadmap

* Plugin development & lifecycle support
* Plugin discovery & use infrastructure
* Richer plugin collaboration constructs
* Simpler imposition of central & implied logic
