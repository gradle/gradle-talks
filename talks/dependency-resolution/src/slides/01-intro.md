# Gradle

The future of Dependency Management

## About Me

* Darrell DeBoer (most people call me Daz)
* Principal Engineer @ [Gradleware](http://gradleware.com/)
* [darrell.deboer@gradleware.com](mailto:darrell.deboer@gradleware.com)

## Szczepan's talk

# Dependency Management (10 mins)

Fundamental to every build system (pretty much)

## Why understand the nuts and bolts of dependency resolution?

* Make big impacts on your build time
* Understanding when things go wrong
* Recognising things to avoid (eg mavenLocal())

## Why we're moving away from Ivy

"Doesn't Gradle just use Ivy under the covers?"
Or 
"When Ivy moved from being a booster to a millstone"

## Ivy proved difficult for us to work with and extend

* Not actively developed
* Static state
* Painful to extend in fundamental ways (use HttpClient for downloading, replace caching implementation)
* Caching and performance optimisation were the main drivers
    
## Too many extension/configuration points!

* eg: pluggable version matchers, custom ivy statuses
* Not modelled as part of the Gradle domain
* Untested functionality
* Hard to evolve without breaking builds

## Ivy is still involved, at least until 2.0

* Some Ivy API have leaked into the Gradle API (eg DependencyResolver)
    * We can't change this without breaking compatibility
* For certain use cases, it's still necessary to revert to a native Ivy DependencyResolver
    * <s>ivy.xml metadata with Maven-style directory structure (m2Compatible)</s>
    * <del>Ivy Dynamic Resolve Mode</del>
    * SFTP / SSH support

# Basic Dependency Resolution

20 mins

## The parts

* Configuration
* Dependency
* Repository
* When you resolve a configuration, the dependencies for that configuration get resolved.

## Repositories: logical interactions

<< Diagram >>

* Dependency
    * "org.junit:junit:4.+"
* Module Version Selector
    * [junit] + "4.+"
* List of matching Module Versions
    * [junit], [4.1, 4.2, 4.4]
* Selected Module Version
    * [junit:4.1]
* Module Version Metadata
    * [junit:4.1]<dependencies, artifacts>
* Artifacts
    * 
    
## 

## Repositories: the current implementation

* Dependency
    * "org.junit:junit:4.+"
* Module Version Selector
    * [junit] + "4.+"
* Module Version Metadata
    * [junit:4.1]<dependencies, artifacts>
* Artifacts
    * 
    
* The metadata file is always resolved and parsed if it exists

## Repository: physical interactions

**Example:** Remote interactions when resolving a static version selector

**Example:** Remove interactions when resolving a dynamic version selector

* *[demos/01-basic-resolution]*

**Example:** Can resolve jar file with no meta-data in ivy/maven repository

## Repository configurations

|| Type || Metadata || Missing Metadata || Layout || Listing || 
| Maven | pom.xml | true | fixed | maven-metadata.xml |
| Ivy | ivy.xml | true | flexible | directory listing |

At the most basic level, configuration is identical:

    repositories {
      maven { url "http://example.com" }
      ivy { url "http://example.com" }
    }

## Maven repository

    maven {
        credentials {
            username 'user'
            password 'password'
        }
        url "http://repo.mycompany.com/maven2"
        artifactUrls "http://repo.mycompany.com/jars" // Additional
    }
    
**Example:** Network interactions with additional artifactUrls


## Ivy repository

    ivy {
        credentials {
            username 'user'
            password 'password'
        }
        url "http://repo.mycompany.com/maven2"
        layout "pattern", {
            ivy "ivy-files/[organisation]/[module]/[revision]/ivy.xml"
            artifact "company-artifacts/[organisation]/[module]/[revision]/[artifact]-[revision].[ext]"
            m2compatible true
        }
    }

**Example:** Network interactions with additional artifactUrls

## Ivy Dynamic Resolve Mode

## Local filesystem repositories

* Take priority over remote repositories
* ivy.xml / pom.xml not required (presence of jar is enough)

**Example:** Local repo takes precedence over remote

## Repositories with no metadata

**Example:** Module with metadata takes precedence over one without
**Example:** Handling a nested directory structure with jars only

## Flatdir repositories

????

## Using an Ivy DependencyResolver directly

## Artifact resolution

* Maven metadata does not specify multiple artifacts for a module, so 1 dependency =  1 artifact.
  * You can use 'classifier' to access additional artifacts if you know the exist
* Ivy metadata allows multiple configurations per module, and multiple artifacts per configuration
  * Gradle defaults to the 'default' configuration of the ivy module

# Deeper into Dependency Resolution

20 mins
## Dependency Resolve Rules

## Transitive Dependencies

## Transitive Dependency Excludes

    configurations {
        compile.exclude module: 'commons' // Commons will not be included
    }

    dependencies {
        compile("org.gradle.test.excludes:api:1.0") {
            exclude module: 'commons' // Commons can still be pulled in via a different transitive dependency
        }
    }

**Example:**

## Force versions

## Client Modules

* Declare the module metadata (transitive dependencies) on the client-side. Real metadata (if it exists) will be parsed and ignored.
* Use this for bad or missing metadata in the repository

    dependencies {
        runtime module("org.codehaus.groovy:groovy-all:2.0.5") {
            dependency("commons-cli:commons-cli:1.0") {
                transitive = false
            }
            module(group: 'org.apache.ant', name: 'ant', version: '1.8.4') {
                dependencies "org.apache.ant:ant-launcher:1.8.4@jar", "org.apache.ant:ant-junit:1.8.4"
            }
        }
    }
  
## Artifact-only notation

* Contrary to documentation, metadata will be downloaded and parsed if it exists.

## Specific configurations of ivy modules

## Changing modules

* Only affects caching

## API and reports

* See Szczepan's talk

# Elements of Dependency Caching
20 mins

## The Gradle Dependency Cache

* Concurrent read/write safe (multi process)
* Source location aware
* Rich metadata (checksums, ETags, modification dates)
* Local artifact reuse (old gradle versions, maven local)
* Fetching is optimised for HTTP

2 primary components:

* Transparent file store
* Binary meta-data cache
* In-memory cache

## The File Store

* Contains all downloaded files: stored in a standard repository layout that includes SHA1 of the file itself
* Allows for multiple different artifacts with the same identifier
* Layout has remained constant since Gradle 1.0
   * Unfortunately, this is still stored in a location that changes whenever the binary store format changes.
   
## The meta-data cache

Everything is cached _per-repository_: repositories identified by a hash of their attributes.

Repository caches
| dynamic-revisions.bin | Cache of Module Version Selector ("1.+") to Module Version (1.0) |
| module-metadata.bin | Cache of Module Version to Module Version Metadata (including artifacts and transitive dependencies) |
| artifact-at-repository.bin | Cache of artifactId to actual artifact file |

Auxiliary cache
| artifact-at-url.bin | Download cache of URL to artifact file + information about Content Length, ETag, etc when downloaded |

Usage of the first 3 is straightforward: 4th one is used for 'artifact re-use', refresh-dependencies, etc.

## Module and artifact caching

* Per repository caching
* Missing is cached
* No expiry for non-changing modules (can override with --refresh-dependencies)

**Example:** caching of modules and artifacts (no expiry)
**Example:** Per-repository caching of modules and artifacts (works on my machine problem)
**Example:** Missing is cached
**Example:** Recovers from bad repository (will retry missing if not found anywhere)

## Changing module caching (SNAPSHOT)

**Example:** Using cacheChangingModulesFor

## Dynamic Version caching
**Example:** controlling by cacheDynamicVersionsFor
**Example:** using --refresh-dependencies to override

## --offline and --refresh-dependencies

* Offline: need to find dynamic version, module and artifacts in cache. Doesn't use by-url cache.
   - Will never attempt to refresh cached version of changing module, SNAPSHOT, or dynamic version (even with cacheDynamicVersionsFor xxx)
* Refresh: Bypasses main caches, but uses auxiliary by-url cache to attempt to avoid re-download

**Example:** 

## Artifact reuse

# Conflict Resolution

10 mins

## How it works

* For a particular dependency, resolve to a single distinct version (newest across all repositories)
* If a module is brought in via multiple different transitive dependency chains, then conflict resolution will apply
* Will either choose the newest from candidate, or fail.

## Limitations of the current model

* Do not have the entire set of versions available: each repository only provides a single (newest) version.
* Do not honour more specific versions when they fit in a range:
    * "1.0" + "1.+"
    * "1.0" -> 1.0 & "1.+" -> 1.2: 1.0,1.2 -> 1.2 (but 1.0 is a better match)
    
## A brighter future

* Repositories list all versions that match selector (or perhaps just list all available versions, and selector is applied outside of repository)
* Conflict resolution uses set of selectors plus all available versions to resolve.
    * Find the best candidate that matches all selectors, fail if none suits
* Allow a 'selector' to be much richer:
   * works with [1.1, 1.2, 1.3] - as in, tested with
   * should work with [1.0, 2.0)
   * something like Maven 'pinned versions'
   
## Szczepan's talk

# Q&A
