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
* Igor's story

## Use the daemon

* large builds inherently provide slower feedback
* use all possible ways to quicken the feedback
* daemon means snappier builds
* even snappier in the future