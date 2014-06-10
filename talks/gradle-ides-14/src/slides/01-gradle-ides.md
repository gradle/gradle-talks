# Gradle and IDEs

Development setups with Gradle projects

## Agenda

* Intro
* IDEA
* NetBeans
* Eclipse
* Generating Project Files
* Tooling API

Demos!

# Intro

## About Me

* Radim Kubacki / radim@gradleware.com / @radimk
* core dev at Gradle
* previously NetBeans commiter and Eclipse and IDEA plugin developer
* lives in Prague, Czech Republic

## IDE integration

Two forms of IDE integration:

* Gradle-centric: Generate project files
* IDE-centric: Gradle plugin

# IDEA

## IDEA

* Significant improvements in IDEA 13

Capabilities:

* Import
* Sync
* Execute Gradle tasks
* Some editing support

## IDEA (cont'd)

Domain vocabularies

| Gradle | IDEA |
| --- | --- |
| Gradle build | IDEA project | 
| Gradle project | IDEA module |
| Configurations | Scopes (approx.) |
| Runs the build and tests | Repeats the build and launch configuration |

## IDEA (cont'd)

| Gradle plugin | IDEA integration |
| --- | --- |
| works consistently | sometimes broken |
| | better scopes mapping with Gradle <1.12 |
| | distinction between source and resource roots |
| | better support for buildscript editing |
| have to apply `idea` plugin | works out-of-the box |

Both read `idea` plugin customizations.

# Demo

IDEA in Action

## IDEA Demo

* Initial import
* Sync
* Execute Gradle tasks
* Editing support

# NetBeans

## NetBeans

* Separately released plugin, improving continuously

Capabilities:

* Import
* Sync
* Execute builds
* Gradle is the IDE's build engine (!)

## NetBeans (cont'd)

Domain vocabularies

| Gradle | NetBeans |
| --- | --- |
| Gradle build | | 
| Gradle project | NetBeans Project |
| Configurations | Classpath modeled using `IdeaModel` |
| Runs the build and tests | Delegates build/run/test to Gradle |

# Demo

NetBeans in Action

## NetBeans Demo

* Installation
* Initial import
* Executing builds
* Testing and debugging
* Gradle as the build engine

# Eclipse

## Eclipse

* Developed by STS team
* Standalone [eclipse-integration-gradle](https://github.com/spring-projects/eclipse-integration-gradle/) plugin available
* Supports Eclipse 3.7, 3.8, 4.2, 4.3

Capabilities:

* Import
* Sync
* Execute builds
* Some editing support
* Classpath container (optional)

## Eclipse (cont'd)

Domain vocabularies

| Gradle | Eclipse |
| --- | --- |
| Gradle build |  | 
| Gradle project | Eclipse project |
| Configurations | *no match*, one classpath |
| Runs the build and tests | Repeats the build and launch configuration |

**Beware:** `eclipse` vs. `eclipse-wtp`

# Demo

Eclipse in Action

## Eclipse Demo

* Installation
* Initial import
* Reimport with changed scope
* Sync after dependency change
* Container vs. libraries
* Executing Builds

# Generating Project Files

## Generating Project Files

* Gradle generates project files for use by IDE
* Baseline IDE support
* Very flexible

# Demo

Generating Project Files 

# Tooling API

## Tooling API

* Well-defined entry point for all tools
* Builds always run in Gradle Daemon
* Can also be used for testing plugins
* More in Get insight into your Gradle build with Tooling API talk tomorrow