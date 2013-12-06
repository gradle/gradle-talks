# Polyglot Gradle

Building Java, Groovy, Scala, and beyond!

<div id="images" style="width: 900px;height: 200px;overflow: hidden;
white-space: nowrap;">
  <img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/java_logo.jpeg" style="display: inline-block">
  <img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/groovy_logo.svg" height=200 width=200 style="display: inline-block">
  <img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/scala_logo.png" style="display: inline-block">
</div>

## About Me

* Peter Niederwieser
* Principal Engineer at Gradleware
* (Former) Groovy Committer
* Creator of [Spock Framework](http://spockframework.org)
* Based in Linz/Austria
* <peter.niederwieser@gradleware.com>
* [@pniederw](https://twitter.com/pniederw)

# Building

<img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/java_logo.jpeg">

## JavaCompile Task

Interface to the Java compiler

Not usually declared directly, but sometimes configured directly

    task compileJava(type: JavaCompile) {
        classpath = configurations.compile
        destinationDir = file("$buildDir/classes/main")
        sourceCompatibility = "1.7"
        targetCompatibility = "1.7"
        
        options.with {
            // Java compile options (ctd.)
        }
    }

## JavaCompile Task (2)

    task compileJava(type: JavaCompile) {
        ...
        options.with {
            encoding = "utf-8" // defaults to platform encoding
            debug = true
            fork = true
            forkOptions.with {
                executable = file("ibm/jdk/bin/javac") 
                memoryMaximumSize = "512m"
                jvmArgs = []
            }
            useAnt = false
            compilerArgs = []
        }
    }

## Up-to-date Check

Only recompile if inputs or outputs have changed

Not the same as incremental compilation!

Inputs: Sources, compile class path, compiler settings

Outputs: Class files

    ...
    :compileJava UP-TO-DATE
    ...

## Which Java Compiler?

Defaults to Gradle JDK

Target older Java runtime (1): Run Gradle with older JDK

Target older Java runtime (2): Configure source/target compatibility + boot class path

Use different compiler: Set `forkOptions.executable`

## Java Base Plugin

Baseline Java support

Introduces concept of *source sets*

    sourceSets {
        main {
            java {
                srcDirs = ["src/main/java"]
                // exclude "**/jdk8/**"
                // srcDir "$buildDir/generated-sources"
            }
            
            classesDir = "$buildDir/classes/main"
            resourcesDir = "$buildDir/resources/main"
            // dir("$buildDir/generated-resources")
            
            compileClasspath = configurations.compile
            runtimeClasspath = output + configurations.runtime
        }
    }
   
## Java Base Plugin (2)
 
Does *not* add any source sets, but adds *configuration rules* for source sets

* Tasks (per source set): `compileJava`, `processResources`, `classes`
* Configurations (per source set): `compile`, `runtime`
* Lifecycle tasks: `check`, `build`, `buildNeeded`, `buildDependents`
* Properties: `sourceCompatibility`, `targetCompatibility`

## Java Plugin

Opinionated way to build a Jar

* Source sets: `main`, `test`
* Tasks: `jar`, `test`, `javadoc`

<!-- -->

    apply plugin: "java"
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        compile "org.springframework:spring-core:3.2.1.RELEASE"
    }
    
## Other Java Related Plugins

Other plugins build on top of Java plugin

* war
* ear
* java-library-distribution

## Compiler Daemon

External process for Java compilation

Reused across tasks

Significantly faster compilation

Also used for Groovy and Scala

# Demo

Java Compiler Daemon

<img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/java_logo.jpeg">

## Java Outlook

* New source set model
    * Maps source sets to binaries
    * Easily extends to other languages
* Reuse compiler daemon across builds
* Smarter up-to-date check
* Incremental compilation (JDK8, `ScalaCompile`)

# Building Groovy

<img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/groovy_logo.svg" width=300 height=300>

## GroovyCompile Task

Interface to the Groovy compiler

Can compile Groovy and Java (!)

    task compileGroovy(type: GroovyCompile) {
        classpath = configurations.compile
        // other settings (cf. JavaCompile)
                
        options.with {
            // Java compile options
        }
                
        groovyOptions.with {
            // Groovy compile options (ctd.)
        }
    }
    
## GroovyCompileTask (2)

    task compileGroovy(type: GroovyCompile) {
        ...
        groovyOptions.with {
            encoding = "utf-8" // defaults to platform encoding
            fork = true
            useAnt = false
            keepStubs = false
            fileExtensions = ["java", "groovy"]
            optimizationOptions.indy = true
        }
    }
    
## Up-to-date Check

Only recompile if inputs or outputs have changed

Not the same as incremental compilation!

Inputs: Sources, compile class path, compiler settings

Outputs: Class files

    ...
    :compileGroovy UP-TO-DATE
    ...
    
## Which Groovy Compiler?

Inferred from `GroovyCompile` task's compile class path

Works for `groovy`, `groovy-all`, `groovy(-all)@indy`

Manually configurable via `groovyClasspath`
  
    dependencies {
        compile "org.codehaus.groovy:groovy-all:2.2.1@indy"
    }
    
No more `groovy` configuration

## Groovy Base Plugin

Baseline Groovy support

Builds upon Java Base plugin

Adds `sourceSet.groovy` for all source sets

Adds `GroovyCompile` task for each source set

## Groovy Plugin

Builds upon Groovy Base and Java plugins

Adds `Groovydoc` task

    apply plugin: "groovy"
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        compile "org.codehaus.groovy:groovy-all:2.1.2"
    }
    
## Groovy Joint Compilation

Default: Groovy can depend on Java, Java *cannot* depend on Groovy

Joint compilation: Arbitrary bidirectional dependencies

JavaBase.java:

    public class JavaBase {}

GroovyClass.groovy:

    class GroovyClass extends JavaBase {}

JavaDerived.java:

    public class JavaDerived extends GroovyClass {}

## Groovy Joint Compilation (2)

All Java and Groovy code passed to `GroovyCompile` is joint compiled

* Option 1: Put Java code into `src/main/groovy`
* Option 2: Reconfigure source directory sets

<!-- -->

    sourceSets.main.java.srcDirs = []
    sourceSets.main.groovy.srcDirs = 
        ["src/main/java", "src/main/groovy"]

## Groovy Joint Compilation (3)

Joint compilation algorithm (grooyvc):

1. Generate Java stubs from Groovy code
1. Compile Java sources, put stubs on source path (!)
1. Compile Groovy sources
    
# Demo

Groovy Java joint compilation

<img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/groovy_logo.svg" width=300 height=300>

## Groovy Outlook

* Support Groovy-Eclipse compiler
* Groovy REPL/Console tasks

# Building Scala

<img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/scala_logo.png">

## ScalaCompile Task

Interface to the Scala compiler

Can compile Scala and Java (!)

Uses Zinc Scala compiler (default) or Ant task

    task compileScala(type: ScalaCompile) {
        classpath = configurations.compile
        // other settings (cf. JavaCompile)
        
        options.with {
            // Java compile options
        }
        
        scalaCompileOptions.with {
            // Scala compile options (ctd.)
        }
    }
    
## ScalaCompile Task (2)

    task compileScala(type: ScalaCompile) {
        ...
        scalaCompileOptions.with {
            encoding = "utf-8" // defaults to platform encoding
            fork = true
            incrementalOptions.with {
                analysisFile = file("$buildDir/tmp/scala/compilerAnalysis/compileScala.analysis")    
                publishedCode = jar.archivePath
            }
            additionalParameters = []
            useAnt = false
        }
    }

## Up-to-date Check

Only recompile if inputs or outputs have changed

Not the same as incremental compilation!

Inputs: Sources, compile class path, compiler settings

Outputs: Class files

    ...
    :compileScala UP-TO-DATE
    ...
    
## Which Scala Compiler?

Inferred from `ScalaCompile` task's compile class path

Manually configurable via `scalaClasspath`
  
    dependencies {
        compile "org.scala-lang:scala-library:2.10.1"
    }
    
No more `scala` configuration

## Scala Base plugin

Baseline Scala support

Builds upon Java Base plugin

Adds `sourceSet.scala` for all source sets

Adds `ScalaCompile` task for each source set

## Scala Plugin

Builds upon Scala Base and Java plugins

Adds `ScalaDoc` task

    apply plugin: "scala"
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        compile "org.scala-lang:scala-library:2.10.1"
    }

## Scala Joint Compilation

Default: Scala can depend on Java, Java *cannot* depend on Scala

Joint compilation: Arbitrary bidirectional dependencies

JavaBase.java:

    public class JavaBase {}

ScalaClass.scala:
    
    class ScalaClass extends JavaBase

JavaDerived.java:
    
    public class JavaDerived extends ScalaClass {}
 
## Scala Joint Compilation (2)
   
All Java and Scala code passed to `ScalaCompile` is joint compiled

* Option 1: Put Java code into `src/main/scala`
* Option 2: Reconfigure source directory sets

<!-- -->

    sourceSets.main.java.srcDirs = []
    sourceSets.main.scala.srcDirs = 
        ["src/main/java", "src/main/scala"]
    
## Scala Joint Compilation (3)

Joint compilation algorithm (scalac):

1. Parse Java source code
1. Compile Scala sources
1. Compile Java sources
    
## Targeting multiple Scala versions

Major Scala versions aren't binary backwards-compatible

As a library provider, need to publish separate Jar per Scala version

Wish:

    scala {
        targetVersions "2.8.1", "2.9.2", "2.10.1"
    } 

    dependencies {
        compile "ch.qos.logback:logback-core:1.0.13"
        compile "org.scalaz:scalaz-core_?:6.0.4"
        scala2_9_2Compile "org.scala-stm:scala-stm_2.9.2:0.7"
    }
    
# Demo

Targeting multiple Scala versions

<img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/scala_logo.png">
    
## Incremental Scala Compilation

Powerful type system => slow compiler

Incremental compilation to the rescue

Zinc compiles Scala (and Java!) incrementally

Works across projects in multi-project build

# Demo

Incremental Scala compilation

<img src="/swd/prj/gradle-talks/talks/polyglot-gradle/src/scala_logo.png">

## Scala Outlook

* Cache Scala compiler across builds (persistent compiler daemon)
* Scala REPL tasks

# Other Languages

## Other Languages

Out-of-the-box: C/C++, (Javascript, Coffeescript)

Third-party plugins: Android, Clojure, Kotlin, Flex, IKVM, R, AspectJ, GWT

Leverage existing Ant task

Execute command-line compiler

# Summary

## Summary

Gradle 1.0: Java/Groovy/Scala via Ant tasks

Gradle 1.6: "Native" Java/Groovy/Scala integration, compiler daemon, Zinc support

Gradle 1.7/8/9: New source set model, mature C/C++ support

Beyond 1.9: Accurate compile class paths, incremental Java compilation, official Javascript support

.



