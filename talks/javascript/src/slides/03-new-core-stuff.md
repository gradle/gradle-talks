# Core Support

The new Gradle core JS support

## New JS Subproject

Very new and still incubating (No API guarantees).

* reusable infrastructure.
* eventually opinionated conventions.

## What does it do?

* Provide repository definitions
    * [http://repo.gradle.org/gradle/javascript-public/](http://repo.gradle.org/gradle/javascript-public/)
    * [https://ajax.googleapis.com/ajax/libs/](https://ajax.googleapis.com/ajax/libs/)
* Deep [RhinoJS](http://www.mozilla.org/rhino/) integration
* CoffeeScript compilation
* JsHint support
* EnvJs support (JavaScript based headless browser)

## New Repos

    repositories {
        javaScript.gradle()
        javaScript.googleApis()
    }
    
    dependencies {
        jquery "jquery:jquery.min:1.7.2@js" // from google repo
    }
    
    war {
        from configurations.jquery
        into "scripts"
    }

## Rhino Integration

* An “embedded” JavaScript runtime
* Rich data exchange

Supports JavaScript based tooling in a forward compatible way.

## CoffeeScript

Alternative JavaScript language.

Plugin configures everything out of the box, allows user to override CoffeeScript impl/version.

# Demo

CoffeeScript compilation

## JsHint

Static analysis for JavaScript.

Example of the benefits of deep integration with the JS runtime.

# Demo

JsHint

## EnvJs

A JavaScript DOM implementation.

A completely portable headless browser environment, 100% JVM.

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

## Swappable “evaluators”

`BrowserEvaluator` objects are responsible for “executing” a document inside a scriptable browser. 

Will allow specific testing plugins to decouple from the execution environment.

    public interface BrowserEvaluator {
        void evaluate(String url, Writer writer);
    }
    
    task jasmineExec(type: BrowserEvaluate) {
        // …
        evaluator (isCi() ? envJsEvaluator : firefoxEvaluator)
    }

# Demo

Automated Jasmine tests