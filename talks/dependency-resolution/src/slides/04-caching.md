# Caching

## What's cool about the Gradle dependency cache

* Repository aware: helps ensure reproducible builds
* Rich metadata (missing modules, checksums, ETags, modification dates)
* Local artifact reuse (different repositories, old gradle versions, maven local)
* Concurrent read/write safe (multi process)
* Efficient
* Good offline support

## Basic module and artifact caching

* Caches regular (non-changing) modules forever
    * Assumes that published metadata is correct
* Caches absence of module in repository
    * Will re-check before failing if module not found anywhere
* Always honors the order of defined repositories, regardless of caching
* Cache records are per-repository
    * Helps prevent the "works on my machine" problem

---

* Example: Resolve static version (aa:1.0)
* Example: Honors repository order: switch repository order
* Example: Resolve static version only present in second repository (aa:1.1)
* Example: Recovers from case where static version not found in any repository (aa:1.2)

## Caching of dynamic version selectors

* Caches the mapping of **module version selector** to **module version**
* By default caches for 24hrs: prevents excessive checks
* Can override refresh rate with **resolutionStrategy.cacheDynamicVersionsFor**

---

* Example: use `cacheDynamicVersionsFor` to detect newly published version (bb:1.3)

## The physical cache

3 primary components:

* Transparent file store
* Binary meta-data cache files
* In-memory module cache

## The file store

* Contains **all** downloaded files
* Stored in a standard repository layout
    * Artifact ID encoded in the path
    * Includes SHA1 of the file itself
    * Allows for multiple different artifacts with the same identifier
* Layout has remained constant since Gradle 1.0
    * Unfortunately, is co-located with the binary store

## The binary caches

* Binary storage format used for efficiency and speed of access
* Most cache keys include _repository id_: identified by a hash of their attributes.

<dl>
  <dt>dynamic-revisions.bin</dt><dd>Cache of Module Version Selector ("junit:1.+") to Module Version ([junit:1.0])</dd>
  <dt>module-metadata.bin</dt><dd>Cache of Module Version ID to Module Version Metadata</dd>
  <dt>artifact-at-repository.bin</dt><dd>Cache of Artifact ID to actual artifact file</dd>
</dl>

<dl>
  <dt>artifact-at-url.bin</dt><dd>Cache of URL to artifact file + download info (eg content length, ETag)</dd>
</dl>

## Changing modules

* Module content can change for same exact module version
* Must tell Gradle if a module is 'changing'
    * Specify `changing = true` on dependency
    * For a Maven repository, `SNAPSHOT` versions are always considered changing
* Only behavioral difference with changing modules is in caching
    * By default check for new version every 24 hours
* Can override refresh rate with **resolutionStrategy.cacheChangingModulesFor**

---

* Example: single repository, resolve changing module, change and re-resolve (aa:1.1)

## Reducing artifact downloads

Gradle does some clever stuff to try to avoid re-downloading a file that was previously downloaded (even from a different URL)

* Finds a set of candidates that might work (based on Module/Artifact ID)
    * From current cache with same URL
    * From current cache with same Module/Artifact ID
    * From cache of different Gradle version
    * From `.m2/repository`
* Performs HTTP HEAD to inspect Date, Content-Length, ETag, etc
    * Compares against metadata stored for URL
* Downloads SHA1 hash if present
    * Compares against hash of candidate files
* If no candidates match, downloads the actual file

---

Example: Resolve same changing (Maven) module with and without re-publish (uses HEAD vs SHA1)

Example: Resolve same module with different repositories: artifact SHA1 matches (aa:1.0)

## Refresh dependencies

`--refresh-dependencies` is equivalent to:

* Every module is 'changing'
* cacheChangingModulesFor '0', 'seconds'
* cacheDynamicVersionsFor '0', 'seconds'

Very useful:

* Recovers from bad repository data
* Verifies remote repository content, but reuses artifacts where possible
* Use sparingly! Better to mark particular dependencies as 'changing'

---

Example: --refresh-dependencies

## Offline

`--offline` is equivalent to:

* cacheChangingModulesFor 'a very long time'
* cacheDynamicVersionsFor 'a very long time'
* Prevent any remote repository access

Works well to allow disconnected development.

---

Example: Add a changing module and cacheChangingModulesFor '0', 'seconds': use --offline to prevent remote requests

## Difficulties with the current caching implementation

* Not a transparent repository like `.m2/repository`
     * Layout is considered private and subject to change
     * This is by design, but limits some use cases (i.e. for manual IDE setup)
     * We are investigating solutions to this use case
* No tooling to clean up stale version, so cache continues to grow
* FileStore and binary store are co-located, meaning FileStore is copied every time binary cache format changes

