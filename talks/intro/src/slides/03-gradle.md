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

## Domain Modelling

Gradle uses strong modelling to manage complexity and empower malleable conventions.

    sourceSets {
      main {
        srcDir "src/main/java"
      }
    }

Conventions are driven from such declarative model elements, in an evaluated-on-demand fashion.

    compileJavaTask.conventionMapping.map("source") { 
      sourceSets.main.java 
    }

## Abstraction

Tasks are often not “exposed” to build users. Instead, they are lower level building blocks that drive the execution.

Build masters can model their domain and expose DSLs and model elements that create & configure tasks for the user to program against.

    apply plugin: 'editions' 

    productEditions { 
      enterprise core, plugins, powerAddons 
      opensource core, plugins, openApi 
    }

(better than template Ant scripts attached to a wiki)

## Extensible

Gradle is an *extensible build language*, which allows **you** to abstract/hide **your personal** complexity.

    project.extensions.create(ProductEditionsExtension)
    
    class ProductEditionsExtension {
      // Implementation of DSL
    }

You know your domain.