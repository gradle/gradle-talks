# Gradle

Creating a world-class C++ Build System

## About Me

### Adam Murdoch

* Co-founder Gradle and Gradleware
* Gradle development lead
* adam.murdoch@gradleware.com

## About this talk

* Current state of C/C++ support in Gradle
* Why should I use Gradle to build C/C++ projects?
* Roadmap

# Gradle and C/C++

## Current C/C++ support

Here's what Gradle can do now:

* Build executables, shared libraries and static libraries
* Compile C++, C and assembler
* Windows, Linux, OS X
* Visual C++, GCC (plus Cygwin and MinGW), Clang
* Some support for publishing binaries to a repository
* Eclipse CDT integration

## Current C/C++ support

Current support is _incubating_. It's a work in progress

Lots of changes over the last 3 releases, expect more in the next few releases.

Demos are from master and these features will be included in Gradle 1.10

## Building an executable

    apply plugin: 'cpp'

    executables {
        helloWorld
    }

Build by convention:

* C++ source files under `src/helloWorld/cpp`
* Headers under `src/helloWorld/headers`
* Builds an executable binary from these
* Uses whichever toolchain is available in the `PATH`

## Building an executable

Customisation:

* Source directory layout
* Compiler and linker settings
* Which toolchain(s) to use

---

* Demo: build and run executable
* Demo: customisation of compiler args
* Demo: customisation of source layout
* Demo: incremental build

## Building a library

    apply plugin: 'cpp'

    libraries {
        helloWorld
    }

Build by convention:

* C++ source files under `src/helloWorld/cpp`
* Headers under `src/helloWorld/headers`
* Builds a shared library or static library binary from these
* Uses whichever toolchain is available in the `PATH`

---

* Demo: build shared and static variants
* Demo: test application
* Demo: installation and dealing with `soname`

## The model

A C++ project is made up of _libraries_ and _executables_. These are known as _components_.

A component is a high-level description of the software.

A library or executable can produce one or more _binaries_. A binary is bound to a particular target environment.

![components](img/components.svg)

## The model

C++ source and headers are arranged into _source sets_.

A component or binary can take source sets, libraries and binaries as input.

![source sets](img/sourcesets.svg)

## Multiple platforms

Portable build logic handles differences across

* Operating system
* Tool chain

Can customise across all platforms or for a specific platform

Doesn't help with portable code

---

* Demo: building an executable and shared library on different platforms
* Demo: customisation

## Incremental build

Don't use `gradle clean`!

Incremental build takes care of:

* Changes to source and header files
* Changes to compile and link settings
* Changes to dependencies
* Change of platform and toolchain
* Tracking header file dependencies

When something changes:

* Recompile individual changed source files
* Clean up stale object files, executables, debug files

---

* Demo: change executable source file comment
* Demo: change executable source file code
* Demo: change source file of shared library
* Demo: change header file of shared library
* Demo: change compile setting, linker setting
* Demo: rename file

## Variants

Usually more than one output is produced for a C/C++ component. Each such output is a _variant_

Variants may differ in any of:

* Operating system
* Architecture
* Tool chain
* Environment: static or shared, hosted or standalone, device driver
* Build type: release or debug or something in between
* Optional features
* Pre- or post- profiling
* And ...

## Dependency management

Each binary takes source sets, libraries or binaries as input

Each source set takes zero or more libraries or binaries as dependencies

Dependencies can come from:

* The same project
* Another project in the same build

What's missing:

* System libraries
* Dependencies from binary repository

## Dependency management

Coming soon:

* Other types of libraries: installed on the system, built by another tool, checked into source
* Publish to a binary repository
* Resolve from a binary repository
* Variant aware dependency resolution: select the 'best' compatible binary
* Usage aware dependency resolution: compile-time vs link-time vs runtime vs debug
* 'Must use' dependencies: must use same version of headers and binary at compile and runtime.

# Why Gradle for C/C++?

## Why Gradle?

Here are some of the reasons we've seen:

* High level description of the software
* Portable build logic
* Customisation and flexible
* Accurate incremental build
* Build variants
* Dependency management

---

Plus:
* Standardization
* IDE integration

# The roadmap

## The roadmap

Some things we want to do:

* Parallel compilation
* Publish and resolve native packages
* Variant aware dependency management
* Visual Studio integration
* Testing
* Other languages: Objective-C, C#
* Integrate with the application plugin and extend to installers, native packages
* Remote builds using CI infrastructure

And plenty more...

# Questions

# Thank you
