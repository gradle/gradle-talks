# Good Practices

Some things to be mindful of.

## Wrapping Ant tasks

Don't be scared to simply wrap an Ant task. This can work well.

Examples in Gradle core:

1. [Checkstyle](https://github.com/gradle/gradle/blob/master/subprojects/code-quality/src/main/groovy/org/gradle/api/plugins/quality/Checkstyle.groovy)
2. [CodeNarc](https://github.com/gradle/gradle/blob/master/subprojects/code-quality/src/main/groovy/org/gradle/api/plugins/quality/CodeNarc.groovy)
3. [JDepend](https://github.com/gradle/gradle/blob/master/subprojects/code-quality/src/main/groovy/org/gradle/api/plugins/quality/JDepend.groovy)

These tools offer capable Ant tasks. No need to reinvent the wheel. Simply create a Gradle friendly adapter.

## Tool versions

Avoid locking down the versions of external tools. That is, allow the user to specify the version they want. 

    apply plugin: "findbugs"
    findbugs {
        toolVersion = "2.0.6"
    }

Or…

    apply plugin: "findbugs"
    dependencies {
        findbugsPlugins "org.custom:findbugs:1.9"
    }

See [FindBugsPlugin](https://github.com/gradle/gradle/blob/master/subprojects/code-quality/src/main/groovy/org/gradle/api/plugins/quality/FindBugsPlugin.groovy) for a how-to example.

## API style

Gradle build scripts are written in a DSL style, optimised for readability and clarity.

    configurations { cobertura }
    dependencies {
        cobertura 'net.sourceforge.cobertura:cobertura:1.9.4.1
    }

When writing plugins and tasks, prefer the API style. It's more verbose, but more strongly typed.

    Configuration coberturaClasspath = project.configurations.add("cobertura")
    Dependency coberturaDependency = project.dependencies.create('net.sourceforge.cobertura:cobertura:1.9.4.1')
    coberturaClasspath.dependencies.add(coberturaDependency)

You'll get more IDE assistance.

## Don't use `ext.`

The `ext.` space is for user properties extensions. Your plugins should _never_ use this. 

    ext.myProp1 = 1
    ext.myProp2 = 2

Use first class extensions instead, so they are namespaced and more maintainable.

    project.extensions.create("myPlugin", MyPluginExtension)
    myPlugin {
        myProp1 = 1
        myProp2 = 2
    }

**Never** impose `ext.` properties in a plugin.

## Almost all objects are extensible

You can extend all kinds of things…

    sourceSets.all { 
        it.extensions.create("myPlugin", MyPluginSourceSetExtension)
    }
    sourceSets.main.myPlugin.foo = "bar"
    
    tasks.withType(Test) {
        it.extensions.create("myPlugin", MyPluginTestTaskExtension)
    }
    test.myPlugin.foo = "bar"

## Simple objects are easier to test

Don't make monolithic objects. 

Not everything has to be a `Task` or `Plugin`. 

POJOs are fine.

## Capability vs. Convention

Don't couple capability and convention (defaults).

In other words, don't impose opinions that the user can't escape. 

Break your plugin up into a plugin stack with conventions on top of capabilities.

## Self contained tasks

Don't reach out from tasks. Keep them self contained.

Configure tasks from the outside via plugins.

* task = work unit
* plugin = wiring/configuration

Use configuration rules on the task container.

## It's just code

Remember that all the rules of good programming still apply.

It can be a little harder to see the patterns in this domain, but they are still there.
