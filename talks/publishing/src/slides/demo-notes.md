
* Add standard .gitignore
* 'gradle setupBuild'
* Uncomment default implementation, add idea plugin and run 'gradle idea'
* Open in IDEA and tweak
* Create repo directories

## Publishing the old way
* 'gradle uploadArchives'
  * Ivy file generated
  * Nothing published
* Ivy file has empty organisation and 'unspecified' for version, so update project properties
* 'gradle uploadArchives'
  * Better ivy file generated
* Add ivy repository to uploadArchives configuration: 'gradle uploadArchives'
  * PUBLISHED!
* Add maven publishing config: plugin, mavenDeployer
  * Can joke about how it _should_ work : repositories { maven { url file('repo/maven') }}
  * But requires: repositories { mavenDeployer { repository(url: file('repo/maven).toURI())}}
  
* Downsides: implicit, inconsistent, poor names, ...

## Publishing the new way
* Add configuration for empty ivy publication
  * Generates a nice clean ivy.xml, and publishes it. No jar file published.
* Add java component to publication
  * publishes nicely
  * Generated ivy.xml file is very simple: based on component
  * Don't generate a lot of default configurations: this is up to you (was hard to change previously)
  * Does not include test compile dependencies: they aren't part of your publication!
  * Longer term, we will make 'sourceJar' a standard part of the component, so can be automatically published
* Duplication ivy configuration with maven:
  * 'gradle publish' -> fails because plugin hasn't been added
  * Add plugin: works and publishes to repo/maven-publish
  
* More configuration, but less published! But you are in control...
* Demonstrate changing name of repository and publication
  * Can publish just that component to a particular repository
  
## Publishing a source jar
* Add source jar to project (without classifier)
   * Overwrites the primary jar
   * Add classifier to differentiate
* Added to archives configuration to publish the old way
   * Source jar is published
   * References in ivy.xml
* Added to publications to publish the new way
   * Source jar is published
   * Only in default configuration
   
## Customising the published artifact

* Try setting classifier & extension in artifact
  * Maven artifact doesn't pick up extension, type is wrong in ivy.xml
* Add 'type' value: now maven extension is wrong and ivy type is right
* Setting 'name' value breaks the maven publication
* Customise the new way:
  * Can specify different values for ivy vs maven publication
  * Ivy: name, classifier, extension, type
  * Maven: classifier & extension only
* Ivy: specify configuration for artifact. Can't do this with the old stuff.

## Publishing a custom artifact
* Add a gendocs task, that creates a single output docs file
  * Add to archives configuration (with classifier)
  * Run 'gradle uploadArchives' : fails because file doesn't exist
* Add "builtBy": now succeeds
* Add to ivy publication: can specify name/extension/type
* Add custom configuration for docs in ivy

## RESET - remove the custom artifact code for clarity, and delete the repo

## Customise the generated POM
* Use the pom property on MavenDeployer
  * Add a project description and view the pom
  * Add organisation { name "Gradleware", url "http://gradleware.com" }
* Do the same for maven-publish, using withXml
  * First show asNode().appendNode("description", "the project"
  * Then show asNode().version + {
      resolveStrategy = DELEGATE_LAST
      ....
  }
  
## Customise the generated ivy.xml
* Not possible with the old publishing support
* Set status='release', and transitive = false on one of the configurations
* Demonstrate asString().reverse()

## Selectively publishing or generating descriptor files
* Execute `gradle publish` and note the different task names:
  * Publish only the maven publication
  * Publish only the ivy publication
  * Generate the pom file
  * Generate the ivy descriptor
* 

## RESET




  



