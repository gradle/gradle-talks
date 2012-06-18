# Core Support

The new Gradle core JS support

## New JS Subproject

Very new and EXPERIMENTAL (No API guarantees).

The goal is to be the foundation for other JS plugins, by providing reusable infrastructure.

Over time, this will expand to dependency management and opinionated conventions.

Will be part of Gradle 1.1.

## What does it do?

* Provde repository definitions
    * [http://repo.gradle.org/gradle/javascript-public/](http://repo.gradle.org/gradle/javascript-public/)
    * [https://ajax.googleapis.com/ajax/libs/](https://ajax.googleapis.com/ajax/libs/)
* Deep [RhinoJS](http://www.mozilla.org/rhino/) integration
* CoffeeScript compilation
* JsHint support
* EnvJs support (JavaScript based headless browser)

## Rhino Integration

Provides support for running Rhino code in forked JVMs with arbitrary versions of Rhino.

    public 
    interface 
    RhinoWorker<R extends Serializable, P extends Serializable> {

        R process(P payload);

        Exception convertException(RhinoException rhinoException);

    }

## Why?

* Not tied to a Rhino version (via forked JVM)
* Allows access to the JavaScript runtime
* Richer datatypes than String

Designed to make it cheap to support JavaScript based tooling in a forward compatible way.

## Browser Evaluation

Abstractions for “executing” a document inside a scriptable browser. Will allow specific testing plugins to decouple from the execution environment.

    public interface BrowserEvaluator {
        void evaluate(String url, Writer writer);
    }
    
    // Gather together test files
    task buildTestSuite(type: Copy) {}
    
    task jasmineExec(type: BrowserEvaluate) {
        content buildTestSuite
        resource "index.html"
        result "$buildDir/jasmineResults.html"
        evaluator (isCi() ? envJsEvaluator : firefoxEvaluator)
    }

## BrowserEvaluate

* Starts a HTTP server, serving «content»
* Evaluates «content»/«resource»
* Captures the DOM as HTML after render complete, writing to «result»
* «evaluator» provides the runtime

<!-- -->

    task jasmineExec(type: BrowserEvaluate) {
        content "build/js/testsuite"
        resource "index.html"
        result "$buildDir/jasmineResults.html"
        evaluator (isCi() ? envJsEvaluator : firefoxEvaluator)
    }

# DEMO

coffeescript-core

# DEMO

jshint-core

# DEMO

jasmine-core