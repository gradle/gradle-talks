# Groovy For Gradle Users

The language that powers Gradle builds.

## About Me

* Peter Niederwieser
* Principal Engineer at Gradleware
* (Former) Groovy Committer
* Creator of [Spock Framework](http://spockframework.org)
* Based in Linz/Austria
* <peter.niederwieser@gradleware.com>
* [@pniederw](https://twitter.com/pniederw)

## Agenda

* **What** is Groovy?
* **How** is Groovy used in Gradle?
* **Why** should I study Groovy?
* **Which** are Groovy's essential features?

<img src="img/Groovy-logo.svg" />

# What

is Groovy?

## What Groovy is

* Dynamic scripting language for the JVM
* Tightly integrated with the Java platform
* Inspired by Java, Ruby, Python, etc.
* Compiles to byte code
* Close to Java in terms of syntax, semantics, and APIs
* Easy to learn for Java developers (by design)

# How

is Groovy used in Gradle?

## How Groovy is used in Gradle

Gradle build language is *internal domain-specific language* based on *Groovy*.

Gradle build scripts are valid Groovy programs.

    apply plugin: "java"
    
    version = 1.0
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        compile "org.springframework:spring-core:3.2.1.RELEASE"
    }

## Gradle Build Scripts

* Must conform to Groovy syntax
* Can’t be executed by plain Groovy runtime
* Delegate to an [`org.gradle.api.Project`](http://www.gradle.org/docs/current/dsl/org.gradle.api.Project.html) object
* Populate the *Gradle object model*

<!-- -->

    // invalid Groovy syntax
    println 'Gradle

    // unknown property
    println zipCode

    // ?
    println name

# Why

should I study Groovy?

## Why to study Groovy

* Little or no Groovy skills:
    * Use Gradle
    * Tweak existing build scripts
    * Author trivial Gradle builds
    
* Working knowledge of Groovy:
    * Author non-trivial Gradle builds
    * Truly understand Gradle
    * Develop custom tasks and plugins (in Groovy)
    * Push automation to the next level
    * Use Groovy in other areas (testing, embedded DSLs, etc.)

