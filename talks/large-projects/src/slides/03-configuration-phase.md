## Gradle build phases

* configuration
* execution
* there are others, too

## Configuration phase

* Traditionally, each Gradle invocation configures *all* projects
* It does not scale very well. Hence:
    * configuration on demand
    * the daemon

## Configuration on demand

* When projects are considered 'coupled'
* Configuration on demand requires decoupled projects
* What projects are configured

## Slow configuration

* Configuration-time bottlenecks affect every Gradle invocation!
* Performance-inefficient custom plugins
    * no problem in a small project
    * are a pain in a large project

## Common traps

* no critical performance review of custom plugin code
* too much stuff going on during configuration time
    * push it to the execution phase
* dependency resolution during configuration
* missing 'doLast'
* task configured with logic that should be a part of execution
