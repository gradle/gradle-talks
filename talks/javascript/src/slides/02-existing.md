## Gradle Fundamentals

Gradle is a capable general automation platform.

It's a toolkit the empowers you to solve your own problems.

## Publishing

Gradle makes it easy to “publish” your JS to a binary repository.

Maven repository is most common.

* Nexus
* Artifactory

# Demo

demos/01-publishing

## Rhino

JavaScript on the JVM - https://developer.mozilla.org/en-US/docs/Rhino.

* An embeddable JavaScript runtime, on the JVM
* Driven by Mozilla
* Language spec compliant
* Stable, reliable

Great tool for running JS on the JVM.

## JavaExec task

The [JavaExec task](http://gradle.org/docs/current/dsl/org.gradle.api.tasks.JavaExec.html) gives the ability to launch external Java processes.

    task jsHint(type: JavaExec) {
    	classpath configurations.rhino
    	main "org.mozilla.javascript.tools.shell.Main"
    	args file("jshint.js"), file("some.js")
    }

Has an imperative [Project.javaexec(Closure)](http://gradle.org/docs/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:javaexec\(groovy.lang.Closure\)) cousin.

# DEMO

jshint-javaexec

## FileTree

Data structure for a collection of files, with hierarchy information.

    def myJsSource = fileTree("src/main/js") {
        include "**/*.js"
    }
    
    def myCoffeeScriptSource = fileTree("src/main/coffeescript") {
        include "**/*.coffee"
    }

`FileTree` and `FileCollection` are important Gradle types to get familar with.

## Task Chaining

Gradle task dependency inference makes modelling pipelines easy.
    
    task compileCoffeeScript(type: CoffeeScriptCompile) {
        source myCoffeeScriptSource
    }
    
    def allJs = myJsSource + files(compileCoffeeScript).asFileTree
    
    task zip(type: Zip) {
        from allJs
    }

Given the above, Gradle can infer that the `zip` task depends on the `compileCoffeeScript` task. 

# DEMO

filetrees-and-chaining

## Using Ant Tasks

Gradle can easily use any functionality packaged as an Ant task.

* Reuse tooling implemented as an Ant task

# DEMO

closure-ant

## PhantomJS

Very realistic headless browser.

* WebKit based
* Fast
* Controllable
* Native binary for different platforms

## Exec Task

The [Exec task](http://gradle.org/docs/current/dsl/org.gradle.api.tasks.Exec.html) gives the ability to launch arbitrary external processes.

    task launchPhantom(type: Exec) {
        executable "phantomjs"
        …
    }
    
Has an imperative [Project.exec(Closure)](http://gradle.org/docs/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:exec\(groovy.lang.Closure\)) cousin.

# DEMO

exec-phantomjs

## Jasmine

JavaScript unit testing tool.

* BDD style constructs
* Runs in a “browser” (DOM required)
* Reasonably popular

## EnvJS 

JavaScript implementation of a DOM.

* A headless browser like environment
* Works with Rhino
* Created by the jQuery team
* A reliable approximation of a real browser

Useful for automating testing.

# DEMO

unit-test

## Gradle JS Plugin

[https://github.com/eriwen/gradle-js-plugin](https://github.com/eriwen/gradle-js-plugin) - by Eric Wendelin

* Bundling, minification, gzip, jshint, jsdoc, props2js
* Under active development
* In Maven Central

# DEMO

gradle-js-plugin

## GruntJS

[GruntJS](http://gruntjs.com/) - A native JavaScript task runner.

* NodeJS based
* Support for lots of plugins/tools
* File watching support
* Increasingly popular

## GruntJS & Gradle 

GruntJS & Gradle are complimentary.

GruntJS does JavaScript well.

You can drive GruntJS from Gradle.
 
# DEMO

gradle-gruntjs

## gradle-gruntjs-plugin

https://github.com/srs/gradle-grunt-plugin

Dynamic tasks…

    gradle grunt_compile

Or…

    task gruntBuildWithOpts(type: GruntTask) {
       args = ["build", "arg1", "arg2"]
    }