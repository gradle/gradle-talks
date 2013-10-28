# Plugins

## Plugins as wiring

Tasks add new functionality, extensions add new build language elements.

One of the things that plugins often do is *wire* these things together. 

A common pattern is to use the DSL extension as a kind of defaults registry.

## Containers

Gradle introduces some new collection types with useful behaviour.

You can create your own domain object containers, via [`project.container()`](http://gradle.org/docs/current/javadoc/org/gradle/api/Project.html#container\(java.lang.Class\)).

    class Thing {
        String name
        Thing(name) { this.name = name }
    }
    
    extensions.add("things", project.container(Thing))
    
    things {
        main {}
        other {}
    }

Objects are created on demand in the container closure. This powers many parts of the Gradle DSL.

## Container Item Configuration

Gradle collections allow configuration to be specified for current and *future* elements. 

The [`TaskContainer`](http://gradle.org/docs/current/javadoc/org/gradle/api/tasks/TaskContainer.html) is a good candidate for this.

    tasks.all { property = "value" }
    tasks.withType(SomeTaskType) { property = "value" }
    tasks.matching { it.name.startsWith("foo") }.all { 
        property = "value" 
    }

See [`NamedDomainObjectContainer`](http://gradle.org/docs/current/javadoc/org/gradle/api/NamedDomainObjectContainer.html).

## Convention Mapping

The convention mapping mechanism allows for dynamically derived values.

    someModelElement {
        someSetting = "someValue"
    }

    tasks.withType(SomeTask).all { task ->
        task.conventionMapping.map("someSetting") { 
            project.someModelElement.someSetting 
        }
    }

# Demo

Containers and Convention Mapping

## Plugin Stacks

Plugins can build on other plugins.

    class MyPlugin implements Plugin<Project> {
        void apply(Project project) {
            project.apply plugin: OtherPlugin
        }
    }

Avoid having plugins do too much. 

Consider breaking them up.

## Tookit vs. Framework

Plugins should either provide capabilities, or conventions. Not both.

* **Toolkit** plugins add new task types, model elements and wire up sensible defaults (e.g. `java-base` plugin).
* **Framework** plugins are opinionated and pre-configure the project to work a certain way (e.g. `java` plugin).

Framework plugins stack on top of related capability plugins.

Users can escape the opinions of your framework by using the toolkit layer and implementing their own framework.

# Demo

Toolkit vs. Framework
