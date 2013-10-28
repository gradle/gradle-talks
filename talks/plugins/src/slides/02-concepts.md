# Concepts

## Build Scripts

Gradle builds are Groovy scripts. In these scripts, you program against the [`Project`](http://gradle.org/docs/current/dsl/org.gradle.api.Project.html) object.

    package org.gradle.api

    interface Project {
        Object getVersion()
        void setVersion(Object version)
    }

    // In a build script

    version = 1.0
    println "project.version: " + version

The project is the “mother object”.

## Build Scripts (cont.)

In a build script, we add one or more *tasks* which we can then have Gradle execute.

build script…

    task helloWorld << {
        println "Hello world!"
    }

command line…

    $ gradle helloWorld
    Hello World!

## Build Language

While still Groovy code, the best build scripts are a Domain Specific Language.

    apply plugin: "java"

    sourceSets {
        main {
            java {
                srcDir "myjava"
            }
        }
    }

    compileJava {
        options.encoding = "utf16"
    }

Where the domain may be *your* domain.

## Domain Modelling

Gradle allows you to add new elements and concepts to the build language.

    task copyLibs(type: Copy) {
        from sourceSets.main.compileClasspath
        into "libs"
    }

`sourceSets` is a declarative model element.

## Imperative vs. Declarative

Gradle is mixed imperative & declarative.

The goal is:

* the build script is **declarative** (i.e. this is what I want).
* the **imperative** logic (i.e. this is how things work) is _demoted_.

That is, plugins inject a DSL interface into the build script.

## Declarative DSLs… why?

Why bother with declarativeness, DSLs, plugins etc?

1. Audience
1. Extensibility
1. Maintainability
1. Comprehensibility

## Gradle Plugins

*Just* a reusable fragment of build logic. No special plugin API.

This is valid plugin code…

    task helloWorld << {
        println "Hello world!"
    }

**Conceptually**, plugins can add new functionality to a build.

## Script plugins

Literally fragments of build script.

`"plugins/helloWorld.gradle"`:

    task helloWorld << {
        println "Hello world! (from $project.name)"
    }

`"build.gradle"`:

    allprojects {
        apply from: "plugins/helloWorld.gradle"
    }

# Demo

Script Plugins

## Script Plugin Distribution

Distribute them via:

* Check them in (build local)
* VCS “externals” (e.g. git submodules)
* Shared filesystem
* HTTP

<!-- -->

    apply from: "http://my.org/build-plugins/hello-world-1.0.gradle"

Currently, it's downloaded (but not recompiled) each time. 

Future versions of Gradle will be smart about caching.

## Binary Plugins

Logic packaged as an object.

    package org.gradle.api;

    public interface Plugin<T> {
        void apply(T item);
    }

## Binary Plugins (cont.)

Applied with a different syntax.

    apply plugin: "java" // identifier
    apply plugin: org.gradle.api.plugins.JavaPlugin // or Class

The identifier is resolved by looking for a certain properties file on the classpath.

`META-INF/gradle-plugins/java.properties`:

    implementation-class=org.gradle.api.plugins.JavaPlugin

## Binary Plugin Distribution

Binary plugins need to be on the build script classpath.

Distribution:

* buildSrc (build local)
* “externalised” buildSrc (e.g. git submodules)
* jar dependency

<!-- -->

    buildscript {
        repositories { mavenCentral() }
        dependencies { classpath "org.my.plugins:hello-world:1.0" }
    }

# Demo

Binary Plugins
