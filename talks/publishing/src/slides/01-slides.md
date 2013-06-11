# Publishing

Your software to the world

## About Me

* Darrell DeBoer (everyone calls me Daz)
* Software Engineer at Gradleware
* Long time Open Source gypsy: Apache James, Ant, Selenium, Gradle
* Living in Kimberley, BC
* Canadian born, Australian raised

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

* FileSystem
* Http repositories
* WebDav
* SFTP
* Artifactory
* Nexus

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

## 'gradle uploadArchives'

* Task automatically created by Base Plugin:
    * Creates **`archives`** configuration
    * Creates **`upload<Configuration>`** task for every configuration
* Upload task:
    * Generates metadata descriptor files: `ivy.xml` and `pom.xml`
    * Publishes artifacts added to configuration
    * Publishes to each defined repository

Example: Publishing a Java project with `gradle uploadArchives`

## 'gradle publish'

Nothing implicit, you need to:

* Apply the publishing plugin(s)
* Create and configure any publications
* Configure the repositories

Provides many tasks for added flexibility:

* "Lifecycle" task (`publish`)
* Task for generating each metadata file (eg `generatePomFileForMavenPublication`)
* Task for each publish action (eg `publishJavaPublicationToMavenRepository`)

Example: Publishing a Java project with `gradle publish`

## Customising the publication

## Adding a source-jar artifact

    task sourceJar(type: Jar) {
        from sourceSets.main.java
        classifier "source"
    }

* Publication is aware of AbstractArchiveTask (like Jar), so it "just works"
* Use a classifier to differentiate from main publication

Example: Publishing the source jar for a Java project

## Changing the artifact attributes

Original support has "one-size-fits-all" approach.

   * Set 'name', 'extension', 'classifier' & 'type' on artifact
   * Many values derived from artifact file properties (but not always)

New model allows separate customisation for Maven & Ivy publications:
   
   * Set 'extension' & 'classifier' for all artifacts
   * Also set 'name' & 'type' for Ivy publication artifacts
   * Can customise 'configurations' in Ivy (not possible with `uploadArchives`)

Example: Customise the attributes of the published source jar

## Publishing a custom output file

For custom tasks, need to connect publication to the task that generates published artifacts.

For `gradle uploadArchives`:

    artifacts {
        archives(genDocsTask.outputFile) {
            builtBy genDocsTask
            ...

For `gradle publish`:
 
    ivy(IvyPublication) {
        artifact(genDocsTask.outputFile) {
            builtBy genDocsTask
            ...
            
## Customising the generated metadata

Publishing 2.0 brings new power and flexibility.

## Customising the generated POM

* Publishing 1.0
    * Simple to add additional content using `MavenDeployer.pom.project`
    * Not so simple to modify other attributes
* Publishing 2.0
    * Powerful modifications possible using `MavenPublication.pom.withXml`
    * Not quite as convenient, but more powerful
    
Example: Adding extra information to the generated POM

Example: Overriding the version of a dependency in the generated POM

## Customising the generated ivy.xml

* Publishing 1.0
    * **Not possible**
* Publishing 2.0
    * Powerful modifications possible with `IvyPublication.descriptor.withXml`
    
Example: Updating the `status` attribute of the published ivy.xml

Example: Modifying a configuration in the generated ivy.xml

## Generating the metadata files

It's often useful to generate the metatdata files without actually publishing

Example: Generating a Maven POM without publishing

Example: Generating an Ivy descriptor file without publishing

## Controlling how your project gets published

## Modifying the publication coordinates

Values are taken from your project properties: group | name | version

* For Maven repository: groupId | artifactId | version
* For Ivy repository: organisation | module | revision

With publishing 2.0, you finally have full control.

Example: Changing the coordinates of a Maven publication

Example: Changing the coordinates of an Ivy publication

## Producing multiple publications from a single project

Sometimes you don't want to add a new 'project', just to get another 'publication'.

* In "publishing 1.0"
    * Tricky for Maven repositories
    * Not possible for Ivy repositories
* In "publishing 2.0"
    * Simple and powerful for both Maven and Ivy

Example: Publishing separate API and implementation jars

# Publishing 2.0

It's great, but...

It's not finished. Yet.

# Thanks

# Questions?

