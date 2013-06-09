## Gradle in very large projects

* small project: <10 subprojects
* medium project: <50 subrpojects
* large project: <200 subprojects
* very large project: 200+ subprojects
* gigantic project: 2000 subprojects (real!)

## Dealing with large projects

* avoid
* don't
* shun

## Use the wrapper

* larger builds take longer to troubleshoot
* consistent environment is less error prone

(tell Igor's story)

## Use the daemon

* large builds inherently provide slower feedback
* use all possible ways to quicken the feedback
* daemon means snappier builds
* even snappier in the future

## Configuration time

* Traditionally, each Gradle invocation configures *all* projects
* It does not scale very well. Hence:
    * configuration on demand
    * the daemon

## Configuration time

* Performance-inefficient custom plugins
    * no problem in a small project
    * are a pain in a large project
* Bottleneck during configuration time affect every Gradle invocation!


