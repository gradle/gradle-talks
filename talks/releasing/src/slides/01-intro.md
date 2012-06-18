# Gradle

Building/Releasing Software

## About Me

* Principal Engineer @ [Gradleware](http://gradleware.com/)
* [luke.daley@gradleware.com](mailto:luke.daley@gradleware.com)
* [@ldaley](http://twitter.com/ldaley)

## Agenda

1. Show how to build & test, then deploy **your** Groovy open source project to Maven Central.
2. Show how to build Grails plugins in a Gradle build.
3. Q&A

## Sonatype OSS

The simplest way to get in to Maven Central is via the Sonatype OSS Repo.

The detailed Usage Guide is [here](https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide).

## Prequirements

### A group id

Preferably a domain name that you own. Think about this before your first release. Changing is painful for you and your users.

### Source code online

It is an open source repository.

## Registration

Sign up for a Sonatype OSS account (creating a JIRA ticket)

This involves creating a JIRA ticket @ [https://issues.sonatype.org/browse/OSSRH](https://issues.sonatype.org/browse/OSSRH)

* Summary - a brief introduction of your project
* groupId - the group id coordinates
* Project URL - location of the project website
* SCM URL - location of source control system
* Nexus Username - the JIRA Username you just signed up, one or more
* Already Sync To Central - does this project use the old rsync mechanism?
* Description - any other information you think we need to know

## Build requirements

* POM file
* Main jar
* Sources jar
* Javadoc jar
* Digital signatures

# Let's do it

Deploying to Maven Central

## After `gradle uploadArchives`

Snapshots are available in the repo immediately.

Final releases go insto a “staging” area. After you've verified they are correct, you can promote.

## Producing mutiple artifacts

Maven restricts you to one artifact per build.

You don't have the same restriction with Gradle.

## Grails Apps/Plugins

Gradle can build Grails applications.

[https://github.com/grails/grails-gradle-plugin](https://github.com/grails/grails-gradle-plugin)

Why?

* Orchestration (i.e. larger build)
* Automation (multi version testing)
* Gradle dependency management

Similar to the Grails Maven support.

## Examples

* Geb
* Spock

# Summary

## Maven Central Deployment

It's easy to build and deploy projects to Maven Central with Gradle, and it's completely free.

There's a little bit of boilerplate to add. We will add a plugin for this.

There's a good Gradle guide [online](http://jedicoder.blogspot.dk/2011/11/automated-gradle-project-deployment-to.html)

## Multiple Artifacts

It's possible with Gradle to publish multiple artifacts from the one project.

You'll have to do some configuration to specify how this should work.

We will provide plugins for common patterns (e.g. api & impl)

## Grails Projects

It's possible today to build & release Grails projects with Gradle.

This will get better very soon.

# Q&A

Yes, Gradle 1.0 will be out very soon.
