# Background

What are we talking about?

## “Enterprise” Builds

* Portfolio of builds
* Significant commonalities
* Significant differences
* Desire for consistency
* Desire for control
* High potential for automation/productivity

## Automation is personal

* Only the simplest of software can be built in _near identical_ ways. 
* Convention over configuration let us stop inventing the wheel.
* Rigid conventions inhibit innovation and efficiency.
* Modern automation needs to be ambitious, effective and pervasive.

--- 

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

* *[demos/01-deep-api-dependency-rules]*

**Example**: How do I verify the existence of a “`test`” task?

* *[demos/02-deep-api-require-task]*

## Deep API (cont.)

A design philosophy of Gradle is to give you deep access to the entire model.

The API is often described as an Onion Skin API.

Reading the Gradle Javadoc does not mean you are doing something wrong.

## Configuration Rules

Gradle provides many ways to configure future objects.

You often want to configure things that you don't know about.

**Example:** How do configure all test tasks to be as parallel as they can be?

* *[demos/03-configuration-rule-parallel-tests]*

**Example:** How do I disallow Hibernate as a dependency at all (e.g. `compile`, `test`, `runtime`)?

* *[demos/04-configuration-rule-dependency-rules]*

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

* Add new capabilities/tools (often with defaults)
* Add new conventions or extend tighten existing conventions

Ideally, they do only one of those.

Combining these two inseparably leads to rigidity.

## Adding new capabilities

Adding the ability to perform some new function. For example, to compile CoffeeScript.

Out of scope for this session.

## Adding/Extending Conventions

Configures the project(s) to do useful work by adding/configuring tools and capabilities.

May provide defaults, may enforce standards.

Example conventions:

* Use JUnit 4.10 for tests
* Integration tests will be their own source set and task
* Dependencies always come from our corporate repository
* Source code must be Java 1.5 compatible

## Plugin Types

Plugins can be packaged as either scripts or objects.

Script plugins are `.gradle` files.

* *[05-plugins-script-plugin]*

Object plugins are compiled and on the classpath.

* *[06-plugins-object-plugin-buildSrc]*
* *[07-plugins-object-plugin-repo]*

## DSL Extensions

If you are creating conventions the are configurable or have options, consider wrapping them in [DSL extensions](http://gradle.org/docs/current/userguide/userguide_single.html#customPluginWithConvention).

DSL extensions add new constructs to the build language.

The canonical example of an extension would be the [SourceSetContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/tasks/SourceSetContainer.html).

* *[demos/08-dsl-extensions]*

## Plugin Stacks

Sophisticated plugins, like the Java plugin, are comprised of stacks.

`BasePlugin` ← `JavaBasePlugin` ← `JavaPlugin`

* `BasePlugin` = general build features (language agnostic)
* `JavaBasePlugin` = sensible defaults for Java tools
* `JavaPlugin` = conventions for a Java project

## Your own 'java' plugin

If those conventions don't suit, you can just apply the `java-base` plugin and build your own conventions.

    apply plugin: 'java-base'

However, not a lot of documentation on this at this time. 

The [source code](https://github.com/gradle/gradle/blob/master/subprojects/plugins/src/main/groovy/org/gradle/api/plugins/JavaBasePlugin.java) and the [Gradle Forums](http://forums.gradle.org/ "Community-powered support for Gradle") are your friends.

## Init Scripts

Gradle [init scripts](http://gradle.org/docs/current/userguide/userguide_single.html#init_scripts) facilitate cross project configuration.

Init scripts work with a [`Gradle`](http://gradle.org/docs/current/dsl/org.gradle.api.invocation.Gradle.html) instance, not a [`Project`](http://gradle.org/docs/current/dsl/org.gradle.api.Project.html).

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

* *[09-init-script-plugin-repo]*

## The Gradle Wrapper

A self bootstrapping build system.

* *[10-wrapper]*

## The Gradle Wrapper

<p style="text-align: center">
  <a href="http://gradleware.com/registered/screencasts/the-gradle-wrapper">
    <img src="img/wrapper.gif" style="box-shadow: 0px 0px 6px #888;"/>
    Check out the Gradle Wrapper screencast!
  </a>
</p>

The wrapper doesn't have to download Gradle from our servers, it can download it from yours.

**And the distribution may be customised.**

## Custom Distributions

You can build a custom distribution by packaging your init script inside the Gradle distribution.

This way, every build will implicitly use your own enterprise init script.

* *[11-custom-distribution]*

## Custom Distributions (cont.)

In the future, this will be simpler. Something like…

    task(type: Wrapper) {
      gradleVersion "1.0"
      initScript "http://com.com/init/corporate-plugin.gradle"
      initScript "http://com.com/init/other-init-script.gradle"
    }

Then the Gradle wrapper will be responsible for fetching and managing the init scripts.

## Future Directions

Make it easier to:

* Pre-seed plugins for all builds
* Auto apply plugins
* Pushable default behaviour (e.g. applying plugins)
* Test custom runtimes
* "Installable" custom runtimes
* Global wrapper