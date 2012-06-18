# Wrapping up…

## Existing Support

There are 3rd party JavaScript plugins out there that do some useful stuff.

* [https://launchpad.net/gradle-jslib](https://launchpad.net/gradle-jslib)
* [https://github.com/huyderman/coffeegradle](https://github.com/huyderman/coffeegradle)
* [https://github.com/dzhaughnroth/jasmine-gradle-plugin](https://github.com/dzhaughnroth/jasmine-gradle-plugin)
* [https://github.com/eriwen/gradle-js-plugin](https://github.com/eriwen/gradle-js-plugin)

And others.

These may do what you need, check them out.

## DIY 

Gradles core tools/utilities make it easy to build your own support for JS tools.

* FileTree / FileCollection
* Exec
* JavaExec
* Ant integration

## New Core Support

* Deep Rhino integration
* Browser/Scriptable DOM runtime abstractions
* 100% portable headless EnvJs browser

Right now, focus on supporting infrastructure to enable JavaScript based tooling. Also, to allow different JS plugins to compose together well.

Over time, will expand to encompass Dependency Management and project conventions.

## New Core Support

**Try it!**

Will be in Gradle 1.1. Already in the nightly ([http://gradle.org/nightly](http://gradle.org/nightly)). 

It's EXPERIMENTAL. Any of the API may change without notice at this time.

Needs input and feedback. 

Post ideas/suggestions to [forums.gradle.org](http://forums.gradle.org). 