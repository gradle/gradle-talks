# Fundamentals

Existing tasks/types in Gradle for DIY

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

## Exec Task

The [Exec task](http://gradle.org/docs/current/dsl/org.gradle.api.tasks.Exec.html) gives the ability to launch arbitrary external processes.

    task launchPhantom(type: Exec) {
        executable "phantomjs"
        …
    }
    
Has an imperative [Project.exec(Closure)](http://gradle.org/docs/current/dsl/org.gradle.api.Project.html#org.gradle.api.Project:exec\(groovy.lang.Closure\)) cousin.

# DEMO

exec-phantomjs

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