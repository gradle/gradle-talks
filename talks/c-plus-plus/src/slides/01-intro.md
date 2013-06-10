# Gradle

Creating a world-class C++ Build System

## About Me

### Adam Murdoch

* Co-founder Gradle and GradleWare
* Gradle development lead
* adam.murdoch@gradleware.com

## Agenda

* Current state of C/C++ support in Gradle
* Why should you use Gradle to build C/C++ projects?
* Roadmap

# Gradle and C/C++

## A work in progress

Current support is _incubating_

Lots of good stuff coming in the next 3 - 4 months

Demos are from master and these features will be included in Gradle 1.7

## Current C/C++ support

Here's what Gradle can do now:

* Build executables, shared librares and static libraries
* Windows, Linux, OS X
* Visual C++, GCC and MinGW
* Some support for publishing binaries to a repository
* Eclipse CDT integration

## Building an executable

    apply plugin: 'cpp-exe'

Build by convention:

* C++ source files under `src/main/cpp`
* Headers under `src/main/headers`
* Builds an executable binary from these
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

* C++ source files under `src/main/cpp`
* Headers under `src/main/headers`
* Builds a shared library or static library binary from these
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

A C++ project is made up of _libraries_ and _executables_. These are known as _components_.

A component is a high-level description of the software.

A library or executable is made up of one or more _binaries_. A binary is bound to a particular target environment.

![components](img/components.svg)

## The model

C++ source and headers are arranged into _source sets_.

A component or binary can take source sets, libraries and binaries as input.

![source sets](img/sourcesets.svg)

## Cpp Plugin

General purpose `cpp` plugin provides all these concepts:

    apply plugin: `cpp`

    cpp {
        sourceSets {
            ...
        }
    }

    libraries {
        ...
    }

    executables {
        ...
    }

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
* Cleans up stale object files, executables, debug files

What's missing:

* Recompile individual changed source files
* Track header file dependencies

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
* Pre- or post- profiling
* Test or production

## Variants

Coming soon

* Declare which dimensions are relevant
* Infer those that are not
* Wire up binaries and dependencies based on this

## Dependency management

Each C++ source depends on zero or more libraries or binaries.

Each binary takes zero or more libraries or binaries as input.

Dependencies can come from the same project or another project.

## Dependency management

Coming soon:

* Other kinds of libraries: installed, built by another tool, checked into source
* Publish to a binary repository
* Resolve from a binary repository
* Usage aware dependency resolution: compile-time vs link-time vs runtime vs debug
* Variant aware dependency resolution: select the 'best' compatible binary
* 'Must use' conflict resolution: must use same version of headers and binary at compile and runtime.

## Standardization

Gradle has plenty for standardization across the enterprise:

* Configuration injection for multi-project builds
* Package up your conventions in a plugin and share this across builds
* Shared lifecycle for JVM and native projects
* Use the wrapper for reproducible and zero-admin builds

## Testing

Most toolkits link tests and production code into a test executable

Wire up a test executable with production code as input

Things Gradle can do to help:

* Convention for the test executable and source sets
* Run tests for each variant
* Generate a test driver
* Integration into the Gradle test reports and events
* Incremental build for test execution

---

* Demo: cunit execution

## IDE integration

Eclipse CDT integration

Visual C++ coming soon ...

# Why Gradle for C/C++

## Summary

Here's some of the reasons we've covered:

* High level description of the software
* Portable build logic
* Customisation and flexible
* Accurate incremental build
* Dependency management
* Standardization
* Building a mix of native and JVM based projects
* Testing
* IDE integration

# The roadmap

## The roadmap

Some things we want to do:

* Building for multiple operating systems, architectures, tool chains, etc
* Variant aware dependency management
* Incremental compilation
* Visual Studio integration
* Other languages: C, assembler, Objective-C, C#
* Custom tool chains
* Integrate with the application plugin and extend to installers and RPMs
* Remote builds using CI infrastructure
* Publish and resolve native bundles such as NuGet and RPMs

# Questions

# Thank you
