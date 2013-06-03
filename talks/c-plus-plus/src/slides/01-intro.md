# Gradle

Creating a world-class C++ Build System

## About Me

### Adam Murdoch

* Co-founder Gradle
* Gradle development lead
* adam.murdoch@gradleware.com

## What

* Current state of Gradle C/C++ support
* Why would you use Gradle to build C/C++?
* How to build binaries
* Roadmap

## Gradle C/C++ support

Current support is _incubating_.

Lots of good stuff coming in the next 3 - 4 months.

Here's what you can do now:

* Build executables, shared librares and static libraries
* Windows, Linux, OS X
* Visual C++, GCC and MinGW
* Some support for publishing and sharing binaries in a repository
* Eclipse CDT integration

# Why Gradle for C/C++?

## High level description of the software

* Components, binaries and variants
    * Heirarchy
    * A graph of things
* Building an executable
    * Conventional layout
    * Conventional output
    * Customise compile and link settings.
* Building a static library
* Building a shared library
    * Demo: installation and dealing with `soname`

## Building for multiple platforms

* Portable build logic
* Doesn't help with portable code
    * Demo: building an executable and shared library on different platforms
    * Demo: customisation

## Incremental build

* Demo incremental build
    * Change executable source file comment
    * Change executable source file code
    * Change source file of shared library
    * Change header file of shared library
    * Change compile setting, linker setting
    * Rename file

## Variants

## Extensibility and customisation

## Dependency management

* Multi-project

## Standardisation through plugins and wrapper

## Other interesting stuff

* Mixing Java and C++: A JNI library
* Integration with other tools

## Testing

## IDE integration

* Eclipse CDT integration

# The roadmap

## Challenges

... and how we plan to tackle them

* Building for multiple operating systems, architectures, etc
* Variant aware dependency management
* Artifact reuse
* Incremental compilation
* Objective-C
* Visual Studio integration
* Integrate with the application plugin and extend to installers and RPMs

# Q&A
