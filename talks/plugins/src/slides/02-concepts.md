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

The concept of a “set of source code” and everything it encompasses (e.g. classpath) is *added* to Gradle by a plugin.

These *declarative* model elements (combined with other techniques) promote flexibility and build clarity.

## Imperative vs. Declarative

Gradle is mixed imperative & declarative.

The goal is:

* the build script is **declarative** (i.e. this is what I want).
* the **imperative** logic (i.e. this is how things work) is provided by plugins (and assocatied objects)

That is, plugins inject a DSL interface into the build script.

## Declarative DSLs… why?

Why bother with declarativeness, DSLs, plugins etc?

1. Audience (what the build does usually more important than the how)
2. Integrity (naturally resists ball of mud syndrome)
3. Reuse (no copy/paste)
4. Maintenance (decoupled code is easier to maintain)
5. Understandability (small components are easier to understand)

## Gradle Plugins

**Physically**, a plugin is *just* a reusable fragment of build logic. That is, there is no special plugin functionality.

This is valid plugin code…

    task helloWorld << {
        println "Hello world!"
    }

**Conceptually**, plugins can add a new *class* of functionality to a build.

This functionality may be one or more *capabilities* (e.g. the ability to compile Java source) and/or one or more *conventions* (java source lives in `src/main/java`).

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

Currently, it's downloaded (but not recompiled) each time. Future versions of Gradle will be smart about caching.

## Object Plugins

They come in an object wrapper.

    package org.gradle.api

    public interface Plugin<Project> {
        void apply(Project project);
    }

Just a class loaded from the classpath.

## Object Plugins (cont.)

Applied with a different syntax.

    apply plugin: "java" // identifier
    apply plugin: org.gradle.api.plugins.JavaPlugin // or Class

The identifier is resolved by looking for a certain properties file on the classpath.

`META-INF/gradle-plugins/java.properties`:

    implementation-class=org.gradle.api.plugins.JavaPlugin

## Object Plugin Distribution

Object plugins need to be on the build script classpath.

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

Object Plugins
