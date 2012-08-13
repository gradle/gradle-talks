# Groovy

The DSL layer.

## Groovy

Gradle build files are written in Groovy.

    apply plugin: 'java'

    group = "org.my"
    version = '1.0'

    repositories {
      mavenCentral()
    }

    dependencies {
      compile 'commons-collections:commons-collections:3.2'
      testCompile 'junit:junit:4.+'
    }

## Groovy (cont.)

Groovy is…

* Compiled, never interpreted
* Dynamic, optionally typed
* 99% Java syntax compatible
* Concise, clear and pragmattic
* Great for DSLs

Typically, users write their plugins/extensions in Groovy. 

Some use Java.

## Closures

Gradle uses Groovy's closures extensively.

    repositories {
      mavenCentral()
    }

This is (in pseudocode)…

    void repositories(groovy.lang.Closure closure) {
      closure.delegate = new RepositoryDsl()
      closure.call()
    }

In Groovy, the “delegate” of a closure can be set at runtime.

## The Groovy-JDK (GDK)

Groovy adds many convenient methods to core Java types.

    [1,2,3].findAll { it < 3 } == [1,2]
    [1,2,3].collect { it * 2 } == [2,4,6]
    
    new File("some.txt").text = "New content!"
    
    def map = [:].withDefault { "default" }

This convenience and pragmatism is very useful in “build” code.

## Groovy - why?

A good fit for Gradle because of…

* Dynamism
* Closures
* Java syntax compatibility
* Conciseness
* Pragmatism
* Groovy JDK convenience
* A JVM based build tool makes sense

Theoretically possible to write a frontend in another language (e.g. Scala).
