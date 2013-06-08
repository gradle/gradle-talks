# Extensions

Custom Build Language

## Extensions

Gradle allows new objects to be attached to existing domain objects.

The most common target for extensions is the `Project` object.

    class Thing {
        String name
        Thing(String name) { 
            this.name = name 
        }
    }

    project.extensions.create("thing", Thing, "foo")

See [ExtensionAware](http://gradle.org/docs/current/dsl/org.gradle.api.plugins.ExtensionAware.html) and [ExtensionContainer](http://gradle.org/docs/current/javadoc/org/gradle/api/plugins/ExtensionContainer.html).

## Extensions (cont.)

Extensions are available as a property and via a configuration method.

    project.extensions.create("thing", Thing, "foo")

    // extensions are available as mixed in properties
    assert thing instanceof Thing
    assert thing.name == "foo"

    // and closure configure methods
    thing {
        name = "bar"
    }

    assert thing.name == "bar"

## Nested Extensions

Extensions can have extensions.

    project.extensions.create("thing", Thing, "foo")
    thing.extensions.create("nestedThing", Thing, "bar")
    
    thing {
        nestedThing {
            assert name == "bar"
        }
    }