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
* Execute an action (run more builds, get more actions)

## Tooling API cross-version support

* Self-contained JAR used as a dependency
* Dynamically loads classes from targeted Gradle installations to establish connection to daemon
* Designed to work with older **and** newer versions
* Extensive test suite

# Case study

Dependencies visualisation

## Case study

* Create an application visualizing project dependencies
* Initial code at [https://github.com/radimk/gradle-toolingApi-demo](https://github.com/radimk/gradle-toolingApi-demo)

## Case study - beginnings

Project structure

* Custom tooling model builder
* Client side accessing the model
* Sample project used for testing

## Case study - beginnings

Project structure

* Custom tooling model builder
    - Define a plugin
    - Design model interface
    - Register [ToolingModelBuilder](http://www.gradle.org/docs/current/javadoc/org/gradle/tooling/provider/model/ToolingModelBuilder.html)
    - Add model implementation
* Publish the plugin and then run the client  
   `./gradlew :dependencyPlugin:publish :dependencyClient:run`

## Case study - cont'd

Wrap the client into a webapp.

## Case study - cont'd

More realistic model provided from plugin.

* Project -> subprojects
* Project -> configurations
* Configuration -> dependency resolution returned as [ResolutionResult](http://www.gradle.org/docs/current/javadoc/org/gradle/api/artifacts/result/ResolutionResult.html)
* Shortcut: internal class `RenderableModuleResult` used in `:dependencies` task (from `org.gradle.api.tasks.diagnostics.internal.graph.nodes` package)

## Case study - cont'd

Use init script to extend any Gradle build.

* No need for changes in project code.
* Classpath same for plugin and client
* Init script created on the fly and passed as an argument to [LongRunningOperation](http://www.gradle.org/docs/current/javadoc/org/gradle/tooling/LongRunningOperation.html)  

## Case study

![LightTable](img/lighttable.png)
[LightTable integration](https://twitter.com/mrundberget/status/473054214273642496/photo/1) by Magnus Rundberget

# Case study 2

Testing custom Gradle plugin

## Plugin testing

Example [https://github.com/radimk/gradle-nbm-plugin](https://github.com/radimk/gradle-nbm-plugin)

# Plans

## Plans

* Cancellation
* More information about builds  
    - Including test execution details ...
    - Possibly 2-way communication in the future
* Better models for IDE integration
* Improvements in current IDE plugins