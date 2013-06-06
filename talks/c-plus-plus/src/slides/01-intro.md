# Gradle

Creating a world-class C++ Build System

## About Me

### Adam Murdoch

* Co-founder Gradle
* Gradle development lead
* adam.murdoch@gradleware.com

## Agenda

* Current state of Gradle C/C++ support
* Why would you use Gradle to build C/C++?
* Roadmap

# Building C/C++ project with Gradle

## Current C/C++ support

Current support is _incubating_

Lots of good stuff coming in the next 3 - 4 months

Demos are from master and these features will be included in Gradle 1.7

## Current C/C++ support

Here's what you can do now:

* Build executables, shared librares and static libraries
* Windows, Linux, OS X
* Visual C++, GCC and MinGW
* Some support for publishing and sharing binaries in a repository
* Eclipse CDT integration

## Building an executable

    apply plugin: 'cpp-exe'

Build by convention:

* Looks for C++ source files under `src/main/cpp`
* Looks for headers under `src/main/headers`
* Builds a single executable binary from these
* Uses whichever toolchain is available in the `PATH`
* Wired into the standard lifecycle

Customisation:

* Source directory layout
* Compiler and linker settings

---

* Demo: build and run executable
* Demo: customisation of compiler args
* Demo: customisation of source layout
* Demo: incremental build

## Building a library

    apply plugin: 'cpp-lib'

Build by convention:

* Looks for C++ source files under `src/main/cpp`
* Looks for headers under `src/main/headers`
* Can build a shared library or static library binary from these
* Uses whichever toolchain is available in the `PATH`
* Wired into the standard lifecycle

Customisation:

* Source directory layout
* Compiler and linker settings, per binary

---

* Demo: build shared and static variants
* Demo: test application
* Demo: installation and dealing with `soname`

## The model

* Components and binaries and tasks
    * Heirarchy
    * A graph of things

## Building for multiple platforms

Portable build logic handles differences across

* Operating system
* Tool chain

Can customise across all platforms or for a specific platform

Doesn't help with portable code

---

* Demo: building an executable and shared library on different platforms
* Demo: customisation

## Incremental build

Don't use `gradle clean`. Incremental build takes care of dealing with changes

What's missing:

* Recompile individual changed source files
* Track header file dependencies
* Rebuild on change of platform and toolchain

---

* Demo: change executable source file comment
* Demo: change executable source file code
* Demo: change source file of shared library
* Demo: change header file of shared library
* Demo: change compile setting, linker setting
* Demo: rename file

## Variants

Usually more than one output is produced for a C/C++ component. Each such output is a _variant_

Variants may differ on any or all of:

* Operating system
* Architecture
* Tool chain
* Environment: static or shared, hosted or standalone, device driver
* Build type: release or debug or something in between
* Optional features

Coming soon ...

## Extensibility and customisation

## Dealing with dependencies

* Same project
* Multi-project
* Publish to a repository

## Standardization

* Configuration injection in multi-project
* Package up your conventions in a plugin and shared this across builds

## Testing

* Most toolkits link tests and production code into a test executable
* Demo with CUnit

Things Gradle can do to help:

* Convention for the test executable and source sets
* Run tests for each variant
* Reuse the main object files
* Generate a test driver
* Integration into the Gradle test reports
* Integration into the Gradle test eventing
* Incremental build for test execution

---

* Demo: cunit execution

## Other interesting stuff

* Mixing Java and C++: A JNI library
* Integration with other tools

## IDE integration

* Eclipse CDT integration

# Why Gradle for C/C++

## Recap

* High level description of the software
* Portable build logic
* Customisation and flexible
* Standardization
* Accurate incremental build
* Dependency management

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
* Other languages: C, assembler, Objective-C, C#
* Publish and resolve native bundles such as NuGet and RPMs.

# Questions

# Thank you
