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
        println "Hello world!"
    }

`"build.gradle"`:

    apply from: "plugins/helloWorld.gradle"

Like an include mechanism.

# Demo

Script Plugins

## Script Plugin Classes

Any classes defined in a script plugin are not accessible to the applying script. They must be exported as variables.

`"plugins/customClass.gradle"`:

    class HelloWorlder {
        void sayIt() { println "Hello World!" }
    }

    ext.HelloWorlder = HelloWorlder

`"build.gradle"`:

    apply from: "plugins/customClass.gradle"
    task helloWorld << {
        HelloWorlder.newInstance().sayIt()
    }

## Script Plugin Distribution

Distribute them via:

* Check them in (build local)
* VCS “externals” (e.g. git submodules)
* Shared filesystem
* HTTP

<!-- -->

    apply from: "http://my.org/build-plugins/awesome-1.0.gradle"

## Binary Plugins

They come in an object wrapper.

    package org.gradle.api

    public interface Plugin<T> {
        void apply(T thing);
    }

Just a class loaded from the classpath.

## Binary Plugins (cont.)

Applied with a different syntax.

    apply plugin: "awesome" // identifier
    apply plugin: org.my.plugins.AwesomePlugin // or Class object

The identifier is resolved by looking for a certain properties file on the classpath.

`META-INF/gradle-plugins/awesome.properties`:

    implementation-class=org.my.plugins.AwesomePlugin

## Binary Plugin Distribution

Binary plugins need to be on the build script classpath.

Distribution:

* buildSrc (build local)
* “externalised” buildSrc (e.g. git submodules)
* jar dependency

<!-- -->

    buildscript {
        repositories { mavenCentral() }
        dependencies { classpath "org.my.plugins:awesome:1.0" }
    }

# Demo

Binary Plugins
