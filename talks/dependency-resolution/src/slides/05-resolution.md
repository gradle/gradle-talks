# Controlling Resolution

## Resolution recap

<img src="img/depres.png" style="width:60%"/>

## Transitive Dependencies

Naturally, Gradle does transitive dependency resolution. 

This can be tweaked with `exclude`.

    // Exclude module everywhere (you almost always want this):
    configurations.all {
        exclude group:'org.junit', module: 'junit'
    }
    
    // Exclude module just for a particular dependency:
    dependencies {
        compile("org.gradle.test.excludes:api:1.0") {
            exclude group:'org.junit', module: 'junit'
        }
    }

## Force versions

Force a particular version of a dependency to be used:

    // If Hibernate is used, force version 3.1
    configurations.all {
        resolutionStrategy {
            force 'org.hibernate:hibernate:3.1'
        }
    }
    
    // Use Hibernate, and force version 3.1
    dependencies {
        compile('org.hibernate:hibernate:3.1') {
            force = true
        }
    }


## Dependency Resolve Rules

* Modifies the Module Version Selector before it is used for resolution
* More powerful mechanism than basic force versions
    * Can modify version, or replace entire module selector

<b></b>

    configurations.all {
      resolutionStrategy {
        eachDependency { DependencyResolveDetails details ->
          if (details.requested.name == 'groovy-all') {
            // Prefer 'groovy' over 'groovy-all':
            details.useTarget group: details.requested.group,
                              name: 'groovy',
                              version: details.requested.version
          }
        }
      }


## Client Module Dependencies

* Declare the module metadata in your build
    * Real metadata file (if it exists) will be downloaded, parsed and ignored
* Can use remote repository artifacts when remote metadata is not suitable

<b></b>

    dependencies {
      runtime module("org.codehaus.groovy:groovy-all:2.0.5") {
        // A simple dependency
        dependency("commons-cli:commons-cli:1.0")

        // A nested client module
        module('org.apache.ant:ant:1.8.4') {
          dependencies 'org.apache.ant:ant-junit:1.8.4'
        }
      }
    }

## Ivy Dynamic Resolve Mode

* Useful for ivy modules published with 'revConstraint'
    * Uses 'revConstraint' value for dependency version if it exists

<b></b>

    <dependency org="acme" name="foo" 
                rev="2.1" revConstraint="2.+"/>
    
    repositories {
        ivy {
            url "http://repo.mycompany.com/repo"
            resolve.dynamicMode = true
        }
    }

# Conflict Resolution

## How it works now

* For a single dependency, a distinct version is chosen
    * Choose newest across all repositories
* With multiple dependencies, multiple different versions might result
    * Conflict resolution will apply
    * By default "newest" version is chosen
    * Can also configure Gradle to `fail` on conflict
    
## A limitation with the current model
    
We resolve each "selector" to a single version before resolving conflicts
    
<b></b>

    "junit:4.1" + "junit:4.+"
    ============================
    : "junit:4.1" => [junit:4.1]
    : "junit:4.+" => [junit:4.3]
    ============================
    Conflict resolution chooses [junit:4.3] 
    (junit:4.1 would be better)
    
## Ideas for the future

* Conflict resolution uses set of selectors plus all available versions to resolve.
    * Find the best candidate that matches all selectors, fail if none suits
* Allow module metadata to describe dependencies in a richer way, e.g:
    * Tested with [1.1, 1.2]
    * Should work with [1.+]
    * Does not work with [1.0]
* Allow rules to participate in conflict resolution
