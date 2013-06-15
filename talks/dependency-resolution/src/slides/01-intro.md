# Dependency Resolution

Deep dive

## About Me

### Darrell DeBoer
* Everyone calls me Daz
* Principal Software Engineer at Gradleware
* Long time Open Source advocate: Ant, Selenium, Gradle
* Living in Kimberley, BC

## About this talk

Help you understand what's happening when Gradle turns this:

    repositories {
      mavenCentral()
    }
    dependencies {
      compile "org.apache.httpcomponents:httpclient:4.2.2"
    }
    
into this:

    /lib
    |-- httpclient-4.2.2.jar
    |-- commons-codec-1.6.jar
    |-- ...

## What we'll cover

* The evolution of dependency resolution in Gradle
* Repository types and configurations
* **How repositories work to resolve dependencies**
* **The Gradle dependency cache**
* Controlling dependency resolution
* **Conflict resolution**

More coverage in Szczepan's talk: **Dependency management in the real world**

## Why understand the nuts and bolts of dependency resolution?

* Aids diagnosis when things go wrong
* Can have a big impact on your build time
* Recognize patterns and anti-patterns

# A bit of history

"Doesn't Gradle just use Ivy under the covers?"

## Why we're re-engineering dependency management

* Dependency resolution is one of the foundations of a build system
    * Need for continual improvement
    * Changes driven by real-world requirements: reproducibility, performance, usability
* Ivy proved difficult for us to build on
    * Static state, implicit configuration
    * Hard to extend in fundamental ways (e.g. use HttpClient for downloading, replace caching algorithm)
* Many, many extension and configuration points
    * Untested, undocumented functionality
    * Difficult to refactor implementation and maintain compatibility

## Where we've got to so far

* Completely re-engineered dependency resolution engine
* Core repository types refactored/rewritten
* New caching engine and algorithm

Ivy remains embedded, at least until 2.0:

* Ivy APIs have leaked into the Gradle API (e.g. DependencyResolver)
* Certain use cases still require a native Ivy DependencyResolver
    * <del>ivy.xml metadata with Maven-style directory structure (m2Compatible)</del>
    * <del>Ivy Dynamic Resolve Mode</del>
    * Caching + "local" filesystem resolver
    * SFTP / SSH support
