# Existing Plugins 

## JavaScript Library Plugin

[https://launchpad.net/gradle-jslib](https://launchpad.net/gradle-jslib) - by Eric Berry

* Opinionated “framework”
* Functionality “out of the box”
* Advanced bundling
* Source filtering (e.g. remove console.log statements)
* Pluggable minification

Requires Gradle 0.9 (does not work with 1.x).

## CoffeeGradle

[https://github.com/huyderman/coffeegradle](https://github.com/huyderman/coffeegradle) - by Jo-Herman Haugholt

* Compiles CoffeeScript to JS.
* Incremental build support
* Task dependency inference support

Issues…

* Hardcoded CoffeeScript version
* Not in any public repo
* Not sure if it's being developed/supported

## Gradle Jasmine Plugin

[https://github.com/dzhaughnroth/jasmine-gradle-plugin](https://github.com/dzhaughnroth/jasmine-gradle-plugin) - by dzhaughnroth

* Complete, out of the box solution
* In Maven Central

Issues…

* Little documentation
* Hardcoded to use HtmlUnit for execution
* Hardcoded Jasmine version

## Gradle JS Plugin

[https://github.com/eriwen/gradle-js-plugin](https://github.com/eriwen/gradle-js-plugin) - by Eric Wendelin

* Toolkit collection (non opinionated)
* Bundling, minification, gzip, jshint, jsdoc, props2js
* Under active development
* In Maven Central

Issues…

* No incremental build support
* No task dependency inference
* No support for Gradle file abstractions (e.g. FileCollection, FileTree)

# DEMO

gradle-js-plugin

## 3rd Party Plugins

Writing a really good 3rd party Gradle plugin is difficult right now.

The “core” plugins have access to API that is not yet public and avoid distrubution issues.

This situation will improve as Gradle evolves.

