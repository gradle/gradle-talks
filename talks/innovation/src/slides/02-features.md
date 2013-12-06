# The new stuff…

## Clickable report URLs

Whenever a task that produces some kind of report fails, you get a URL to the report file.

This makes it much easier to open.

* Linux - directly clickable (most terminals)
* Mac OS - ⌘ + click to open
* Windows - copy/paste

Small feature, surprisingly useful.

## Build init support

Assistance for creating new Gradle builds.

    gradle init

Initial support for types…

    gradle init --type java-library

## Maven Import

Convert a Maven project to Gradle.

In a directory with a pom.xml:

    gradle init

* Doesn't handle exotic plugins
* Not a 100% solution
* Does a lot of the brainless work

## Test Output

* Detailed test information, in the console
* Stacktraces
* Events (e.g. skipped)
* Tunable and flexible
* Multiple logging levels

There's a [very detailed forum post](http://forums.gradle.org/gradle/topics/whats_new_in_gradle_1_1_test_logging) on this.

## New dependency report

* Indicates both *requested* and *selected* versions
* Much better insight into version conflicts

(previous version only showed *selected*)

## Dependency Insight

* Inverse of `dependencies` report
* Shows path **to** a dependency
* Explains how/why a dependency is in the graph

## Dependency Resolution APIs

New [`ResolutionResult`](http://www.gradle.org/docs/current/javadoc/org/gradle/api/artifacts/result/ResolutionResult.html) type.

* A model of the resolved dependency graph
* *requested* and *selected*
* Selection *reason*
* Fine grained conflict handling rules 

[`DependencyResolveDetails`](http://www.gradle.org/docs/current/javadoc/org/gradle/api/artifacts/DependencyResolveDetails.html)

* Mutate the dependency graph

## Performance

Lots of work on making Gradle faster.

* Compiler daemons
* Gradle Daemon improvements
* Faster test execution
* Much faster dependency resolution
* Much smarter file system locking

## Parallel execution

Multi project builds can now be executed in parallel.

    gradle build --parallel

* Incubating feature
* Respects dependencies between projects

## Configure on demand

Partially builds the Gradle object model. 

    gradle :libs:awesomeLib:build --configure-on-demand
    
* Incubating feature
* Useful for extremely large projects
* Reduces start up time and memory consumption

## Continue on failure

Don't stop when something fails. 

    gradle build --continue

* Respects failures in task dependencies    
* Almost always what you want for CI

Now with more fail!

## Scala support

Improved Scala plugin.

* Incremental compilation
* Better IDE support 

## C & C++

“Native” code support.

* Incubating feature
* Under very active development
* Portable building (not portable code)
* Ambitious goals

## Android

The new Android SDK is based on Gradle.

    apply plugin: 'android'

    android {
      target 'android-16'
      productFlavors {
        free { packageName = 'org.gradle.sample.free' }
        paid
      }
      buildTypes { custom }
    }

## Task Ordering

You can now influence the ordering of tasks, without requiring dependencies.

    integTest {
        mustRunAfter unitTest
    }

Ensures that integ tests run after unit tests.

Just the beginning of ordering optimizations.

## Task finalizers

Force some task to run after another, regardless of failure.

    task integTest {
        finalizedBy stopWebService
    }

Useful for cleaning up resources used by tasks, or mandatory post processing.

## More stuff…

* JaCoCo code coverage
* New publishing plugins
* Aggregate test reports
* Duplicate handling for copy/archive operations
* Bintray JCenter support
* Fine grained incremental tasks
* Build dashboard plugin
* Tooling API improvements

