## Clickable report URLs

Whenever a task that produces some kind of report fails, you know get a URL to the report file.

This makes it much easier to open.

* Linux - directly clickable (most terminals)
* Mac OS - ⌘ + click to open
* Windows - copy/paste

Small feature, surprisingly useful.

## Upgrade Assistance

Gradle has a frequent release cycle, we want to help you keep current.

The “build comparison” support facilitates _testing_ your build with different versions.

Verify that your _outputs_ are the same with the new Gradle version.

FUTURE: Build Migration

## Maven conversion

Should you want to migrate your build from Maven to Gradle, you can now generate a `build.gradle` from your `pom.xml`.

Intended to give you a headstart in your migration.

FUTURE: Maven import

## Test Output

Gradle now gives you detailed information on test failures and output… right in the console.

This is very tunable and very flexible.

There's a [very detailed forum post](http://forums.gradle.org/gradle/topics/whats_new_in_gradle_1_1_test_logging) on this.

## New dependency report

The improved dependency report gives you information on the dependency versions you *requested* and what was *selected*.

The previous version only gave the *selected* version number, hiding conflicts.

## Dependency Insight

The new “dependency insight” report visualises the path to a dependency.

Useful for finding out what is bringing in a dependency.

## Dependency resolution API

The new dependency resolution result API gives you full access to the resolved dependency graph data.

It includes the *requested* and *selected* versions, and the reason why something was selected.

You can use this for custom reporting, or even sophisticated dependendency conflict handling.

This is the basis for the `dependencies` and `dependencyInsight` tasks.

## Parallel execution

* --parallel 
* --parallel-threads=«num»
* Incubating!

---

This is not a stable feature, and has structural requirements.

# Performance & Memory

Relevance depends on scenarios

---

A lot of potential
Step-by-step improvements
Spend a lot of effort to measure (dedicated performance machines)
Many different aspects (responsiveness (what tasks, dependencies, debugging), average build time, clean build time, configuration time)
Means: Build Avoidance, Profiling & Low level optimisation, apply more resources

# Compiler Daemons

For Java, Groovy, Scala

---

Compile processes = submodules * 2
Should be forked (leaks, clean classpath, health checks)
Groovy is always forked.
Particularly relevant if warm up phase is expensive

# Incremental Scala Compile

Zinc (SBT) integration

---

Scala first class platform 
Cooperation with Typesafe

# Native integration

A platform wonderland

---

* Works with Java 5
* dynamic console output
* native client
* local/remote filesystem
* permissions
* keystores
* proxy settings
* events
* native client
* dog food for Gradle C++/JNI support.

# Continue on failure

## Android


