## Gradle

Its role in automated web testing:

* Test execution
* Test configuration
* Reporting
* Provisioning of browsers
* Provisioning of application under test

## Basic concerns

* Selenium dependencies
* Driver creation
* Application-under-test location

# Demo

Simple Selenium Project

---

* Show build.gradle
* FrontPageTest
* Dig through to DriverFactory and RunningApplicationFactory

## Multi Browser

Changing browser implementation based on environment.

Considerations:

1. Development convenience
2. Browser isolation (debugging)
3. Reporting
4. Performance

# Demo 

Multi browser via TestNG parameterization

## TestNG Multi Browser

Pros:

* No extra build conf
* Simple to run all browsers in one run
* Reporting
* Efficient

Cons: 

* Test noise
* *Always* runs all browsers
* Incompatible with data parameterisation

# Demo

Per build parameterisation

## Parameterized test task

Pros:

* Browser isolation
* Simple setup
* Convenient for IDE development

Cons: 

* Can't run all browsers in one run
* No aggregate report
* Flakey (have to remember browser names)

# Demo 

Multiple test tasks

## Multiple tasks

Pros:

* No magic properties for users
* All browsers in one run
* Convenient for IDE development (simulate build config)

Cons: 

* More complex build config
* No aggregate report
* Parallelization challenges

# Error Reporting

## Error Reporting

Selenium can provide screenshots and HTML dumps on failures.

Failing functional tests are a fact of life.

Design for good diagnostics.

Log with the system streams.

# Demo

Screenshots and HTML dumps

# Remote Browsers

## Remote Browsers

You can decouple the test execution location from the browser location.

* Use multiple OSes
* Centralize browser management
* Support parallelism

## Browser farms

Selenium Server provides browser management infrastructure.

* Simple Java process
* Central hub for connections
* Work distribution engine

Provides a communication fabric, not automated provisioning/config.

# Demo

Remote Browsers

## Remote Browsers

Issues to be aware of:

* Managing a browser farm is difficult
    * Hygiene
    * Upgrades
    * Maintenance
* Can become a production class system
* Rarely resourced appropriately
* Difficult to size

Can be outsourced.

## Collaborators

We now have about 4 different machines in play:

1. Test execution (e.g. developer machine or CI)
2. App deployment (remote or local)
3. Selenium Server Hub
4. Selenium Server Node(s)

# Parallelization

Mo' cores, mo' problems

## Test Parallelization

There are different levels to potentially parallelize:

1. intra-JVM (TestNG managed parallelism)
2. Single suite, multi JVM (Gradle managed parallelism)
3. Multi suite, single/multi JVM (Gradle parallel project execution)
4. Multi build (parallel CI jobs)

## Potential Bottlenecks

1. Local CPU
2. Local Memory
3. Browser instances
4. Browser hub
5. Network
6. Server performance

## Parallel application behavior

Is your application testable in parallel?

Techniques:

1. Parallel deployments
2. Sharding/multi tenancy
3. Separating state sensitive tests

## Recommendation

Avoid intra JVM parallelisation. Threadsafe code is hard.

Use multiple JVMs.

Use Gradle test parallelism and project parallelism.

# Demo 

maxParallelForks

## Parallel Task Execution

Gradle can execute tasks in parallel.

Concurrency boundary is at the project level.

Some creativity required…

# Demo 

--parallel

## Reporting

Failures will be frequent.

Consider how failures will be debugged, particularly on the CI server.

## Reporting and multi-task

If we are executing the same tests multiple times in different contexts…

how do we identify the individual results?

# Demo

Reporting & Jenkins

## Tools

So far…

**TestNG** for test execution.

**Selenium** for browser automation.

## Groovier Tools

**Spock Framework** for test execution.

**Geb** for browser automation.

Groovy based, developed by Gradleware employees Peter Niederwieser and Luke Daley.

# Demo

Geb, Spock and Sauce Labs

## Functional testing

It's no free lunch.

The cost is high, and so is the value.

The primary cost is in maintenance.

## Functional testing

Architect to minimise the required level of coverage.

Constantly evaluate the test suite, and cull frequently.

More is not always better.

## Tooling 

Optimise for maintenance and debugging.

* Reporting
* Setup
* IDE execution

A CI only solution is no solution.

## Infrastructure

It's critical to manage CI and browser resources properly.

* Utilization metrics
* Insight into mechanics
* Config management

How do you roll out new browser and new Selenium versions?

How do you keep services up?

## Testability

You **have** to architect for testability.

You **have** to architect for parallel testability.

## More info…

* Selenium - [http://docs.seleniumhq.org/](http://docs.seleniumhq.org/)
* TestNG - [http://testng.org/doc/index.html](http://testng.org/doc/index.html)
* Geb - [http://www.gebish.org](http://www.gebish.org)
* Spock - [http://spockframework.org](http://spockframework.org)
* SauceLabs - [http://saucelabs.com](http://saucelabs.com)

Questions?