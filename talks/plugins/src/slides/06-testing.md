# Testing

## Unit Tests

Tests for POJO model elements. Traditional unit tests.

By keeping as much functionality as possible away from the Gradle “machinery”, less testing support is needed.

Avoid making everything a Gradle type object. Use POJOs where it makes sense (which is usually more often than you think).

## Configuration Tests

Like unit tests, but testing the “wiring” performed by plugins, extensions, tasks etc.

Not the actual execution of a build.

Use [`ProjectBuilder`](http://gradle.org/docs/current/javadoc/org/gradle/testfixtures/ProjectBuilder.html) to create test `Project` instances to add plugins, extensions, tasks etc. to.

## Functional Tests

Testing that running a build does what is expected (sometimes considered integration tests).

Involves creating a build on the filesystem, invoking Gradle on it, and verifying the result. 

This means scraping the output or checking the created files.

Use the [`GradleLauncher`](http://gradle.org/docs/current/javadoc/org/gradle/GradleLauncher.html) to launch the build.

## Future Functionality

Over the coming releases, a new testing kit will be developed to make testing easier.

Particularly for functional tests.

# Demo

Testing