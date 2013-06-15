# Gradle and IDEs

Getting the most out of Gradle and your IDE

## Agenda

* Intro
* IDEA
* Netbeans
* Eclipse
* Generating Project Files
* Tooling API

Agile manifesto: Demos over slides!

# Intro

## IDE integration

Two forms of IDE integration:

* Gradle-centric: Generate project files
* IDE-centric: Gradle plugin

Will mostly focus on latter.

## Demo App

Spring Petclinic

* Spring based Java web application
* Modded: multi-project, multi-IDE

# IDEA

## IDEA

* Initial Gradle support in IDEA 12
* Completely overhauled for IDEA 13

Capabilities:

* Import
* Sync
* Execute Gradle tasks
* Some editing support

# Demo

IDEA in Action

## IDEA Demo

* Initial import
* Sync
* Execute Gradle tasks
* Editing support

# Netbeans

## Netbeans

* Separately release plugin, improving continuously

Capabilities:

* Import
* Sync
* Execute builds
* Gradle is the IDE's build engine (!)

# Demo

NetBeans in Action

## Netbeans Demo

* Installation
* Initial import
* Reimport with changed scope
* Executing builds
* Testing and debugging
* Gradle as the build engine

# Eclipse

## Eclipse

* Developed by STS team
* Standalone plugin available
* Supports Eclipse 3.6*, 3.7, 4.2, 4.3

Capabilities:

* Import
* Sync
* Execute builds
* Some editing support

# Demo

Eclipse in Action

## Eclipse Demo

* Installation
* Initial import
* Reimport with changed scope
* Sync after dependency change
* Executing Builds
* Tool configurations
* Editing support
* Dealing with generated sources
* Dealing with generated resources

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

## Q&A

* Thanks for attending!
* Questions?
* Feedback?
* http://gradle.org
* http://gradleware.com
