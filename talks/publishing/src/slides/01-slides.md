# Gradle

Transforming Software Engineering, one build at a time

## About Me

* Darrell DeBoer (everyone calls me Daz)
* Software Engineer at Gradleware
* Long time Open Source gypsy: Apache James, Ant, Selenium, Gradle
* Living in Kimberley, BC

## About this talk

* Cover the _basics_ of publishing
    * <sub><sup>It's Friday afternoon, after all</sup></sub>
* Cover the original publishing support as well as the new publishing plugins
* Example driven
    * Live coding complete with <del>misteaks</del>mistakes!

# Publishing
(using words)

## Why Publish?

* Modularise you build
* Integrate with other teams
* Share with the world

## Publishing with Gradle

The past: 

    gradle uploadArchives

The future: 

    gradle publish

The present: somewhere in between...

## Why a new model?

The original support:

* Overly implicit, relying on 'configuration'
* Difficult to understand, control and extend
* Inconsistent DSL
* Does not properly model the domain

The new model:

* Simpler, more declarative
* Closely models the publishing domain (Software Components, etc)
* Will be at the heart of inter-project interactions
* Is **@incubating** : Some features are missing, and things will change

# Publishing a simple Java Project

## Using 'gradle uploadArchives'

* BasePlugin:
    * Creates **`archives`** configuration
    * Creates **`upload<Configuration>`** task for every configuration
* Upload task:
    * Generates metadata descriptor files: `ivy.xml` and `pom.xml`
    * Publishes artifacts added to configuration
    * Publishes to each defined repository

Example: publishing a Java project with `gradle uploadArchives`

## Using 'gradle publish'

Nothing implicit, you need to:

* Apply the plugins
* Create and configure the publications
* Configure the repositories

Provides many tasks for added flexibility:

* "Lifecycle" task
* Task for generating each metadata file
* Task for each publish action

Example: publishing a Java project with `gradle publish`

## Adding a source jar publication

    task sourceJar(type: Jar) {
        from sourceSets.main.java
        classifier "source"
    }

* Publication is aware of AbstractArchiveTask (like Jar), so it "just works"
* Use a classifier to differentiate from main publication

Example: publishing a source jar for a Java project

## Customising artifact attributes

Original support has "one-size-fits-all" approach.

   * Set 'name', 'extension', 'classifier' & 'type' on artifact
   * Many values derived from artifact file properties (but not always)

New model allows separate customisation for Maven & Ivy publications:
   
   * Set 'extension' & 'classifier' for all artifacts
   * Also set 'name' & 'type' for Ivy publication artifacts
   * Can customise 'configurations' in Ivy (not possible with `uploadArchives`)

Example: customise the attributes of a published source jar

## Publishing a custom output file

* `Jar` tasks works because it's an AbstractArchiveTask
* For other output files, need to wire in the task dependency:

Example: publishing a custom documentation file

## Customising the generated POM

* With `gradle uploadArchives`
    * Simple to add additional content using `MavenDeployer.pom.project`
    * Not so simple to modify other attributes
* With `gradle publish`
    * Very powerful modifications possible using `MavenPublication.pom.withXml`
    * Not quite as convenient, but more powerful
    
Example: Adding a description element to the generated POM

Example: Adding a nested organization element to the generated POM

## Customising the generated ivy.xml

* With `gradle uploadArchives`
   * **Not possible**
*With `gradle publish`
    * Powerful modification mechanism using `IvyPublication.pom.withXml`
    
Example: Updating the `status` attribute of the generated ivy.xml

Example: Modifying a configuration in the generated ivy.xml

## Generating the metadata files

Example: Generating a Maven POM without publishing

Example: Generating an Ivy descriptor file without publishing

## Controlling how your project gets published

Values are taken from your project properties: group | name | version

* For Maven repository: groupId | artifactId | version
* For Ivy repository: organisation | module | revision

Until now, tweaking these has NOT been easy to do!

Example: Changing the coordinates of a Maven publication

Example: Changing the coordinates of an Ivy publication

## 1 project, multiple publications

Sometimes, you don't want to add a new 'project', just to get another 'publication'.

Until now, was tricky with Maven and not possible with Ivy.

Example: Publishing the API jar and 'impl' jar separately

# Fanstastic!!!

# But...

Not everything works. Yet.

## Signing artifacts

## Repositories with different protocols

# Thanks

# Questions?

