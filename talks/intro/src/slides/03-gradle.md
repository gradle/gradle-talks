# Gradle Basics

## Tasks

Gradle has a directed acyclic graph based execution model (similar to Ant).

The “task” is the type of node in the graph, and is a unit of work.

    task helloWorld {
      dependsOn anotherTask
      def message = "Hello World"
      doFirst { println message }
    }

The user interface to Gradle is that you ask it to perform one or more tasks.

    $ gradle helloWorld

## Tasks (cont.)

Tasks in Gradle are a collection of one or more *actions*.

This makes it convenient to decorate existing tasks, and has other benefits.

    compileJava {
      doFirst {
        // do this before compilation 
      }
      
      doLast {
        // do this after compilation
      }
    }

## Plugins

Gradle plugins are just containers for build logic. There is no API.

Script plugins…

    apply from: "integration-testing.gradle"
    apply from: "http://my.org/gradle/integration-testing.gradle"

Binary plugins…

    apply plugin: "integration-testing"

Plugin contract…

    public interface Plugin<T> {
        void apply(T target);
    }

## Extensible

Gradle is an *extensible build language*, which allows **you** to abstract/hide **your personal** complexity.

    class ProductEditionsExtension {
      // Implementation of DSL
    }
    
    project.extensions.create("productEditions", ProductEditionsExtension)
    
    productEditions { 
      enterprise core, plugins, powerAddons 
      opensource core, plugins, openApi 
    }

You know your domain.

## Domain Modelling

Gradle uses strong modelling to manage complexity and empower malleable conventions.

    sourceSets {
      main {
        srcDir "src/main/java"
      }
    }

Conventions are driven from such declarative model elements, in an evaluated-on-demand fashion.

## Controlled Customisation

You can…

* Add new concepts
* Add new funtionality 
* Add new conventions
* Add new defaults
* Add new restrictions

And… it's all code, so you can test it, version it, refactor it, share it etc.

Build automation is just another kind of software endeavour. Gradle is a developer friendly toolkit.
