## Before going further...

* Gradle build phases
    * configuration
    * execution
* there's more to it

## Configuration phase

* Traditionally, each Gradle invocation configures *all* projects
* It does not scale very well. Hence:
    * configuration on demand
    * the daemon

## Configuration on demand

* What does it mean if projects are 'coupled'?
* Configuration on demand requires decoupled projects
    * with the exception of root project
* What projects are configured in this mode?

## Slow configuration

* Configuration-time bottlenecks affect every Gradle invocation!
* Performance-inefficient custom plugins
    * no problem in a small project
    * are a pain in a large project

## Common traps

* no critical performance review of custom plugin code
* too much stuff going on during configuration time
    * push it to the execution phase
    * execution-time configuration (demo!)
* dependency resolution during configuration
* missing 'doLast' (demo!)
