## Development Process

* Some design up front
* Every commit is reviewed (http://code-review.gradle.org)
* No feature branches (spikes ok)
* Regular small commits
* Adhoc pairing (remote)
* Test/document as you go (incl. release notes)

## Development tooling

* Gradle
* Git (GitHub)
* IntelliJ IDEA
* Spock Test Framework
* TeamCity
* Markdown & Docbook
* HipChat

# Development

Automating the day-to-day

## IDE/Editor

* Automate the “import” process
* Provide a consistent setup
* Make it possible to run frequent tasks via the IDE

---

* IDE metadata generation/import (i.e. run task)
* gradle/idea.gradle
* run configurations
* runners in IDEA

## Local testing

* Running tests must be trivially easy
* Sacrifice accuracy for convenience (dev builds)
* Build in testability - often not free

## Testing types

* Unit
* Integration
    * Functional
    * Execution modes (in process, forking, daemon)
    * Cross version
* Distribution
* Performance

## Documentation

* API ref
* DSL ref
* Userguide
* Samples
* Release notes

## Code Quality

* Ensure public API is documented
* License headers
* Cyclical package dependencies
* Static analysis (Checkstyle & Codenarc)
