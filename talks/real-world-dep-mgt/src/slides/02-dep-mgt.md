## Version conflicts

* version conflicts
    * default resolution
    * manual resolution

(Demo: car uses guava x and grocery guava y)
resolution via:
 - exclude
 - forced version
 - dependency resolve rule
 - force = true

use dependency report and dependencyInsight

## Library conflicts

* some jars just don't go together
    * guava VS google-collections
    * groovy VS groovy-all
* conflict resolution is based on group and name coordinates

(Demo: car uses guava x and grocery google collections)
resolution via:
 - exclude
 - dependency resolve rule (replace google collections with first version of guava)

## Avoiding bad versions

* replacing bad version with a blessed one
* validate resolved dependencies

(Demo: car uses guava x, grocery uses guava y)
- avoid version z via dependency resolve rules
- tweak versions

(Demo: use the dependency result api to implement validation)

## Imposing consistent versions

* some libraries are designed to be used together

(Demo: car has api and impl, api pulled in with different version)
- add validation that checks if libraries use consistent version
- use dependency resolve rules to impose consistent version

## Managing dependency versions in the organisation

- versions of libraries are imposed
- libraries outside of the catalogue are not allowed

(Demo: use wildcard '*' and load versions from external source)
- validate libraries must exist in the catalogue

Extra:

## Advanced transitive dependency validation

* don't allow to downgrade a transitive dependency
* don't allow to major-upgrade the transitive dependency

Demo, add the validation

## dynamic versions / changing modules

Demo