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
    * compare-gradle-builds plugin
* Gradle Wrapper: reproducibility, consistency, ease of installation
    * see screencast: http://gradleware.com/registered/screencasts/the-gradle-wrapper

## Safe migration

* Gradle's flexibility enables iterative migration
    * first reproduce the build outputs, then tests, then...
* Developing new build without changing existing structure
* Maintaining old and new build for the migration period
    * side-by-side migration
* Planning the migration

## Migrating from maven

* using buildSetup plugin to automatically convert from maven
* incremental migration
    * shallow import
    * deep import

## Migrating incrementally from ant

* shallow import
* deep import
* ant task reuse

## Upgrading Gradle

* compare-gradle-builds plugin
* Upgrade propagation via Gradle Wrapper

## Q & A

questions?