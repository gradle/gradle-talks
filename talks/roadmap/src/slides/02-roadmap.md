## Evolution

Gradle 1.0 is (steep) evolution

- Combines the best of Ant & Maven
- A lot of innovation on top
- Classical build domain

## Continuous Improvement 

* Release + 4 weeks => new RC

## Safe Migrations

* Migration Plugin in progress: gradle/subprojects/docs/src/design-docs/build-migration-verification.md
* Migration Paths:
	* Gradle x -> Gradle y
	* Gradle Build Env x -> Gradle Build Env y
	* Ant -> Gradle
	* Maven -> Gradle
	* Custom Build -> Gradle
* What is compared:
	* Compare Build Outputs
	* Compare Performance (later)
	* Compare Memory (later)

## Improvement Buckets (1)

* Bugs
* Pain
* Joy

## Improvement Buckets (2)

* Performance
* IDE Integration
* Usability
* Core Features
* Error Reporting
* Dependency Management
* Reporting

## Examples

* High level build avoidance (Performance, Joy)
* Test Logging DSL (Joy)
* C-native client (Usability, Joy)
* Site Plugin (Reporting)
* Full programmatic cache control (Dependency Management, Joy)
* Full programmatic resolve control (Dependency Management, Joy)
* CI Management (Joy)
* Plugin Testing (Pain)
* Content Assist in IDE (Pain)
* SBT Scala Integration (Pain)

## Big new build features

* Full C++ Support
* Full JavaScript Support
* iOS
* Android

## Good Enough?

* Excellent _build_ tool + existing ecosystem == good enough?

## Mission Critical

Problems for large builds

* The whole learning/collaboration/promotion process
* Feedback Time (including local builds)
* Exploratory Learning
* Cross Team Collaboration
* Continuous Feature Flow to Downstream Teams
* Strong Gates on all levels
* Platform Testing

Status Quo

* No current tool chain solves those issues conveniently
* Should be solved by a rich language based model

## Focus
* commons-logging __vs.__ 500 dev legacy build

## Improve Configuration Time
* Raw performance improvements
* Incremental configuration
* Parallel configuration

## Fluid multi-module builds
* Not every project need to be checked out
* Quick switching between binary and source dependencies
* Global project registry
* Cached source dependencies

## Parallel and Distributed Builds
- Performance
- Multi-Platform

## Variant aware dependency management 
- C++ (Platform, 32 or 64 bit)
- JavaScript (minified vs. non-minified)
- Scala (binary backward incompatibilities)
- Java (JDK 6 or 7)

## Deep Maven Import
- Similar to deep Ant import
- Accept pom.xml as native build descriptor
- Enrich Maven builds via Gradle
- Reuse Maven plugins

## Plugin Portal & Development
- Something better than a Wiki page :)
- Community Features (voting, comments)
- Automated Testing
- Easier plugin Testing
- Easier usage of external plugins
- Easier publishing of external plugins

## Paid R&D

## Recommendations/Wishes?






