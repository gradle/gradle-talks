# DSL Magic

## Class Decoration

Gradle generates dynamic subclasses of domain object types at runtime.

Features:

* Extensibility
* DSL syntax support
* Type coercion
* …

## APIs

Implication is that Gradle has to control instantiation.

* tasks.create()
* extensions.create()
* project.container()

## “set methods”

Gradle generates “set methods” for properties.

    class SomeTask {
      String foo
    }
    
    task myTask(type: SomeTask) {
      foo "bar"
    }

More natural than using assignment.

## Closure coercion

No need to use `groovy.lang.Closure` in your APIs.

    class SomeTask {
      Thing thing
      void thing(Action action) {
        action.execute(thing)
      }
    }
    
    task myTask(type: SomeTask) {
      thing {
        // configure thing
      }
    }
    