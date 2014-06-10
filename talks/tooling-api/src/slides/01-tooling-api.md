# Gradle Tooling API

Get insight into your Gradle build with Tooling API

What is Tooling API and how you can use it?

## Agenda

* Intro
* Case study - show project dependencies
* Q&As

# Intro

## About

Szczepan Faber

* core dev at Gradle
* lead at Mockito
* lives in Krakow/Poland


Radim Kubacki

* core dev at Gradle
* previously NetBeans commiter and Eclipse and IDEA plugin developer
* lives in Prague, Czech Republic

# Tooling API

## Tooling API

Start with [user guide](http://www.gradle.org/docs/current/userguide/embedding.html)

* Way how to collaborate with / embed Gradle
* Well-defined entry point for all tools + extensibility
* Builds always run in Gradle Daemon
* Can also be used for testing plugins

## Tooling API offerings

* Run a build
* Get a model
    - Predefined models exposing project structure: Eclipse and IDEA support, project structure, publications  
    - Custom models registered by users code
* Run an action (run more builds, get more actions)

## Tooling API cross-version support

* Self-contained JAR used as a dependency
* Dynamically loads classes from targeted Gradle installations to establish connection to daemon
* Designed to work with older **and** newer versions
* Extensive test suite

# Case study

Dependencies visualisation

## Case study

* Create an application visualizing project dependencies
* Initial code at https://github.com/radimk/gradle-toolingApi-demo

## Case study - beginnings

Project structure

* Custom tooling model builder
* Client side accessing the model
* Sample project used for testing

## Case study - cont'd

Wrap the client into a webapp.

## Case study - cont'd

More realistic model provided from plugin.

## Case study - cont'd

Use init script to extend any Gradle build.

# Case study 2

Testing custom Gradle plugin

## Plugin testing

https://github.com/radimk/gradle-nbm-plugin

# Plans

## Plans

* Cancellation
* More information about builds  
    - Including test execution details ...
    - Possibly 2-way communication in the future
* Better models for IDE integration
* Improvements in current IDE plugins