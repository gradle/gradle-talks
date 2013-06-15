# Resolving

## Resolving a single dependency

<img src="img/depres.png" style="width:60%"/>

## Resolving dependency modules

* **Example:** Resolving a dependency with a static version selector
    * Checks for metadata file
    * If no metadata file found, searches for jar file
    * Searches declared repositories in order
    * Fails if not found in any repository
* **Example:** Resolving a dependency with a dynamic version selector
    * Ivy repository uses directory listing for available versions
    * Maven repository uses `maven-metadata.xml` for available versions
    * Searches all repositories and selected newest matching module version

---

* Example: Resolve static version (1.0)
* Example: Honors repository order: switch repository order (move Maven above Ivy)
* Example: Resolve static version only present in second repository (1.1)
* Example: Fails when static version not found in any repository (1.2)
* Example: Resolve static version with no metadata (delete 1.0 ivy.xml)
    * Prefers repository with metadata over repository without


* Example: Resolve dynamic version (1.+)
    * Ivy repository only
    * Maven repository only
    * Both repositories
    
## Resolving dependency artifacts

* Will only request artifact from repository where module was resolved
* Maven POM metadata
    * Does not define artifacts available
    * Generally assumes a single artifact file per module
    * Use 'classifier' to access additional artifacts
* Ivy metadata
    * Defines multiple configurations per module, multiple artifacts per configuration
    * All artifacts for the specified configuration are retrieved
    * Gradle defaults to resolving the 'default' configuration
