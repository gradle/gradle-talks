# Repositories

## Maven & Ivy repositories

At the most basic level, configuration is identical:

    repositories {
        maven { 
            url "http://example.com" 
        }
        ivy { 
            url "http://example.com" 
        }
    }
    
URL can be 'http', 'https' or 'file' protocol.

## Maven repository

* Fixed layout
* Maps groupId from "`org.gradle.api`" to "`org/gradle/api`"
* Uses `maven-metadata.xml` to list versions
* Uses POM file for module metadata
    * A Maven build file **and** a module descriptor file
    * Includes dependencies for various scopes
    * Does not explicitly describe module artifacts (must use classifier)
* Special handling of *SNAPSHOT* versions
    * Automatically flagged as 'changing'
    * Uses `maven-metadata.xml` to define latest snapshot version

## Maven repository configuration

    maven {
        credentials {
            username 'user'
            password 'password'
        }
        // Search path for pom files and artifacts
        url "http://repo.mycompany.com/maven2"
        
        // Search path for artifacts only
        artifactUrls "http://repo.mycompany.com/jars" 
    }

Using `mavenRepo` is virtually identical to `maven`, except the result _is an_ Ivy Dependency Resolver.

## Ivy repository

* Flexible layout using patterns
* Uses HTTP directory listing to get versions (or filesystem listing)
* Uses ivy.xml metadata format
    * Multiple configurations can be declared, each with different metadata
    * Multiple artifacts can be declared per configuration
* Use `m2compatible` for maven-style directory mapping of organization to directories
* No special handling of *SNAPSHOT* versions

## Ivy repository configuration

    ivy {
      credentials {
        username 'user'
        password 'password'
      }
      // Base url for resolution
      url "http://repo.mycompany.com/maven2"
      
      layout "pattern", {
        // Search path for ivy files
        ivy "ivy/[organisation]/[module]/[revision]/ivy.xml"
        // Search path for artifacts
        artifact "artifacts/[organisation]/[module]/[revision]/[artifact]-[revision].[ext]"
        m2compatible true
      }
    }

## Native Ivy DependencyResolver

Sometimes required for use-cases not yet solved by built-in repositories.

* Resolving via SFTP/SSH/WebDav
* Enabling caching for a 'local' repository
* Custom resolution logic

<b></b>

     repositories {
        add(new org.apache.ivy.plugins.resolver.SFTPResolver()) {
            host = "${server.hostAddress}"
            port = ${server.port}
            user = "simple"
            userPassword = "password"
            addIvyPattern "..."
            addArtifactPattern "..."
        }
    }

## Plans for the future

* A means to compose a set of features into a single repository
    * e.g. ivy.xml metadata + maven-metadata.xml + SFTP transport ...
* Programmatic mapping of Module Id / Artifact Id to URL
* Better way to specify which modules are 'changing'