# Gradle

The Enterprise Automation Tool

## About Me

* Principal Engineer @ [Gradleware](http://gradleware.com/)
* [luke.daley@gradleware.com](mailto:luke.daley@gradleware.com)
* [@ldaley](http://twitter.com/ldaley)

## What we will talk about

* Intro to Gradle
* Enterprise - a word people like to say
* Gradle “Enterprise” features
* Looking forward…

## Gradle

> Gradle is build automation *evolved*. Gradle can automate the building, testing, publishing, deployment and more of software packages or other types of projects such as generated static websites, generated documentation or indeed anything else.

» [http://www.gradle.org](http://www.gradle.org), 2012

## Gradle

Fundamentally…

* JVM based, general purpose automation tool
* Java core, Groovy outer layers (user code is Groovy)
* Mixed declarative/imperative
* Conventional, yet flexible
* Simple is simple, nearly anything is possible

## Gradle 

You cannot completely avoid complexity in automation, so you better embrace it.

Gradle is an *extensible build language*, which allows **you** to abstract/hide **your personal** complexity.

Example…

    apply plugin: 'editions' 

    productEditions { 
      enterprise core, plugins, powerAddons 
      opensource core, plugins, openApi 
    }

## Gradle 

Automation paralysis **will** kill you.

* Time to market is a competitive advantage
* Lean, Agile and Continuous Delivery tell us this
* Entropy happens
* Automation is **critical**

Gradle's goal is to be the toolkit that allows you to keep your automation efficient, **tailored** and well organised.

On top of this, we provide out-of-the-box solutions for conventional cases.

## Gradle

Gradle has innovative approaches to enabling **malleable** defaults/conventions.

* Lazy evaluation (e.g. closures, factories)
* Declarative model elements (e.g. `sourceSets`)
* Convention mapping


# Enterprise

a word people like to say

## Enterprise?

Usually means…

* Scale
    * Team size
    * Number of components
* Heterogeneous
* Legacy
* Complexity
* Conformance
* Productivity == $$$

## Enterprise?

Change is costly.

You often need to automate what you have, not what you want.

# Features!

Gradle Enterprisey Stuff

# Incremental Build

Less is more

# Autowiring

global properties are global properties

# Dependency Management

## The Cache

* Concurrent read/write safe (multi process)
* Source location aware
* Rich metadata (checksums, ETags, modification dates)
* Local artefact reuse (old gradle versions, maven local)
* Fetching is optimised for HTTP

**Local cache state does not affect reproducibility.**

## Conflict Resolution

Force a failure if there is any conflict.

    configurations.compile {
      resolutionStrategy {
        failOnVersionConflict()
      }
    }
    
Force a module, if it happens to appear.

    configurations.compile {
      resolutionStrategy {
        force 'org.someorg:somelib:1.0'
      }
    }

## Dynamic/Changing Versions

Tune the **changing** module TTL (e.g. `'someorg:somelib:1.0-SNAPSHOT'`)

    configurations.compile {
      resolutionStrategy {
        cacheChangingModulesFor 0, 'seconds'
      }
    }

Tune the **dynamic** module TTL (e.g. `'someorg:somelib:1.+'`)

    configurations.compile {
      resolutionStrategy {
        cacheDynamicVersionsFor 10, 'minutes'
      }
    }

# Custom Build Language

## Standardization

Gradle distributions can be customised with enterprise specific init scripts. Gradle will load all `$GRADLE_HOME/init.d/*` files. 

You can have the **Gradle Wrapper** download your own modified distribution.

    task wrapper(type: Wrapper) {
        distributionUrl = "http://myorg.com/custom-gradle-1.0.zip"
    }

Init scripts can:

* Apply plugins
* Restrict things (e.g. usage of certain repositories)
* Establish environment parameters

# Looking Forward

## Modular Software

![modules](img/modules.png)

## Modular Software

* Integrate with HEAD/trunk
* Shield from bad snapshots
* Testing dirty working copy
* Exploration

Process needs to map to organisational structure and culture.

There is no one-size-fits-all here.

## Dependency Descriptors

What we have today is inadequate.

* Variants? (e.g. groovy-1.7 vs. groovy-1.8)
* API vs. impl?
* Build vs. Use

We need a new way to think about this.

## Scalability

The only way to scale is to do less.

* Partial configuration
* Distributed artifact reuse
* Dynamic project structures

# STS Plugin

Peter Niederwieser

# Q&A

Yes, Gradle 1.0 will be released very soon.