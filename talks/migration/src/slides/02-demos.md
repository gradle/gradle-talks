# Migrating and Upgrading with Gradle

## Migrating and Upgrading with Gradle

* migration best practices
* from maven (migration helper, shallow import)
* from ant (deep and shallow import, task reuse)
* upgrading Gradle

## Migration best practices

* the decision to migrate
    * Hibernate story: https://community.jboss.org/wiki/GradleWhy
* prepare the team (training, offline webinars, etc.)
* build output comparison
    * false positives may happen
    * migration plugin
* Gradle Wrapper: reproducibility, consistency, ease of installation
    * see screencast: http://gradleware.com/registered/screencasts/the-gradle-wrapper

questions?

## Incremental and background migration

* Gradle's flexibility enables iterative migration
    * first reproduce the build outputs, then tests, then...
* Developing new build without changing existing structure
* Maintaining old and new build for the migration period
* Planning the migration

questions?

## Migrating from maven

* maven conversion utility
    * future plans - bootstrap plugin
* incremental migration
    * shallow import
    * deep import

questions?

## Migrating incrementally from ant

* shallow import
* deep import
* ant task reuse

questions?

## Upgrading Gradle

* Upgrade propagation via Gradle Wrapper
* Gradle built-in Migration plugin

questions?

## Q & A

questions?