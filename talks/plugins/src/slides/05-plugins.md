# Plugins

## Plugins as wiring

Tasks add new functionality, extensions add new build language elements.

Plugins often *wire* these two things together.

## Containers

Gradle provides smart collections that allow configuration to be specified for current and *future* elements. 

The [`TaskContainer`](http://gradle.org/docs/current/javadoc/org/gradle/api/tasks/TaskContainer.html) is one example of this.

    tasks.all { «config» }
    tasks.withType(SomeTaskType) { «config» }
    tasks.matching { it.name.startsWith("foo") }.all { «config» }

See [`NamedDomainObjectContainer`](http://gradle.org/docs/current/javadoc/org/gradle/api/NamedDomainObjectContainer.html).

## Custom Containers

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

## Convention Mapping

The convention mapping mechanism allows for *dynamic* defaults.

    someModelElement {
        someSetting = "someValue"
    }

    tasks.withType(SomeTask).all { task ->
        task.conventionMapping.map("someSetting") { 
            project.someModelElement.someSetting 
        }
    }

The above code sets `someSetting` of all `SomeTask` tasks to whatever value `someModelElement.someSetting` is set to.

Even if the user changes it later.

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
