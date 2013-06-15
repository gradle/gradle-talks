# Publishing

Your software to the world

## About Me

### Darrell DeBoer

* everyone calls me Daz
* Software Engineer at Gradleware

## About this talk

* Cover the _basics_ of publishing
    * <sub><sup>It's Friday afternoon, after all</sup></sub>
* Cover the original publishing support as well as the new publishing plugins
* Example driven
    * Live coding complete with <del>misteaks</del>mistakes!
* Not covered:
    * Signing artifacts (not yet available in new plugins)
    * Publishing to different types of repositorties

## Why Publish?

* Share with the world
* Integrate with other teams
* Modularise you build

## What gets published?

* Binary artifacts
* Documentation
* Source code
* **Module metadata**

## Where do you publish?

* File system
* HTTP repositories
* WebDav
* SFTP
* Nexus
* Artifactory
* Bintray

## Publishing with Gradle

The past - "publishing 1.0": 

    gradle uploadArchives

The future - "publishing 2.0": 

    gradle publish

The present: somewhere in between...

## Why a new model?

The original publishing support:

* Overly implicit, relying on 'configuration'
* Difficult to understand, control and extend
* Inconsistent DSL for Maven and Ivy
* Does not properly model the domain

The new model:

* Simpler, more declarative
* Closely models the publishing domain (Software Components, etc)
* Will be at the heart of inter-project interactions
* Is **@incubating** : Some features are missing, and things will change

## Publishing a simple Java Project

## Publishing 1.0

### 'gradle uploadArchives'

* Task automatically created by Base Plugin:
    * Creates **`archives`** configuration
    * Creates **`upload<Configuration>`** task for every configuration
* Upload task:
    * Generates metadata descriptor files: `ivy.xml` and `pom.xml`
    * Publishes artifacts added to configuration
    * Publishes to each defined repository

Example: Publishing a Java project with `gradle uploadArchives`

## Publishing 2.0

### 'gradle publish'

Nothing implicit, you need to:

* Apply the publishing plugin(s)
* Create and configure any publications
* Configure the repositories

Provides many tasks for added flexibility:

* "Lifecycle" task (`publish`)
* Task for generating each metadata file (eg `generatePomFileForMavenPublication`)
* Task for each publish action (eg `publishJavaPublicationToMavenRepository`)

Example: Publishing a Java project with `gradle publish`

## Software Components

* Model your software project outputs (gradle project != software component)
* Provides a set of artifacts and dependencies for publication
* Currently provide Java Library and Web Application components
    * More to come
    * Will model sources, documentation, api vs implementation, etc
* Roll your own components

## Customising the publication

When the Software Component isn't enough

## Adding a source-jar artifact

    task sourceJar(type: Jar) {
        from sourceSets.main.java
        classifier "source"
    }

* Publication is aware of AbstractArchiveTask (like Jar), so it "just works"
* Use a classifier to differentiate from main publication

## Publishing the source jar

### Publishing 1.0

    artifacts {
        archives sourceJar
    }

### Publishing 2.0

Artifact specified per-publication

    artifact sourceJar


Example: Publishing the source jar for a Java project

## Changing the artifact attributes

### Publishing 1.0

* "one-size-fits-all" approach.
* Set 'name', 'extension', 'classifier' & 'type' on artifact
* Many values derived from artifact file properties (but not always)

### Publishing 2.0

* Allows separate customisation for Maven & Ivy publications:
* Set 'extension' & 'classifier' for all artifacts
* Set 'name' & 'type' for Ivy publication artifacts
* Can customise 'configurations' in Ivy (not possible with Publishing 1.0)

Example: Customise the attributes of the published source jar

## Publishing a custom output file

For custom tasks, need to connect publication to the task that generates published artifacts.

### Publishing 1.0

    artifacts {
        archives(documentationFile) {
            builtBy genDocsTask
            ...

### Publishing 2.0

    ivy(IvyPublication) {
        artifact(documentationFile) {
            builtBy genDocsTask
            ...
            
## Customising the generated metadata

Publishing 2.0 brings new power and flexibility.

## Customising the POM

### Publishing 1.0

* Simple to add additional content using `MavenDeployer.pom.project`
* Not so simple to modify other attributes

### Publishing 2.0

* Powerful modifications possible using `MavenPublication.pom.withXml`
* Not quite as convenient, but more powerful

Example: Adding extra information to the generated POM

Example: Overriding the version of a dependency in the generated POM

## Customising the ivy.xml

Use case: replicate `ivy deliver`

### Publishing 1.0

* **Not possible**

### Publishing 2.0

* Powerful modifications possible with `IvyPublication.descriptor.withXml`

Example: Updating the `status` attribute of the published ivy.xml

Example: Modifying a configuration in the generated ivy.xml

## Generating the metadata files

It's often useful to generate the metatdata files without actually publishing

### Publishing 1.0

    task writePom << {
        uploadArchives.repositories.mavenDeployer
            .pom.writeTo("build/testpom.xml")
    }

    task writeIvyXML << {
        throw new UnsupportedOperationException()
    }

### Publishing 2.0

    gradle generatePomFileForMavenPublication
    gradle generateDescriptorFileForIvyPublication

## Modifying the publication coordinates

Default values are taken from your project properties: group | name | version

### Publishing 1.0

    mavenDeployer {
        repository(url: file("repo/maven").toURI())
        pom.groupId = "org.gradlesummit"
        pom.artifactId = "publishing-talk"
        pom.version = "3.1"
    }

Ivy publication: not possible.

## Modifying the publication coordinates (cont.)

### Publishing 2.0

With Gradle 1.7, you finally have full control.

    ivy(IvyPublication) {
        from components.java
        organisation "org.gradlesummit"
        module "publishing-talk"
        revision "3.1"
    }
    maven(MavenPublication) {
        from components.java
        groupId "org.gradlesummit"
        artifactId "publishing-talk"
        version "3.1"
    }

---

Example: Changing the coordinates of a Maven publication

Example: Changing the coordinates of an Ivy publication

## Multiple publications from a single project

Sometimes you don't want to add a new 'project', just to get another 'publication'.

### Publishing 1.0

* Tricky for Maven repositories
* Not possible for Ivy repositories

### Publishing 2.0

* Simple for both Maven and Ivy publications

Example: Publishing separate API and implementation jars

# Publishing 2.0

It's great, but...

It's not finished. Yet.

## What we've got

* Publish a java library or web application
* Customise artifacts for publication
* Customise the publication metadata
* Modify the publication coordinates
* Produce multiple publications

## Coming up

* Software Components (define the publication)
    * More built-in components: EAR, JVM Application, Native Shared Library, Gradle Plugin, ...
    * Include source, javadoc
    * Custom components
* Better DSL
    * Tweak dependencies
    * Model additional publication info: scm, licenses, mailingLists
    * Signing support
* More transports for publication (SFTP, WebDav)
* Tighter integration with Artifact Repositories

## And the Holy Grail

Integration with a decoupled project model

* Project dependencies target a publication or component
* Merge concept of project dependencies and external dependencies
    * Flexible multi-project builds
    * Interchangeable source and binary dependencies

# Thanks

# Questions?

