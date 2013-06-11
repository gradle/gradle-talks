# Development

Automating the day-to-day

## IDE/Editor

* Automate the “import” process
* Prerequisites are java & IDE but no Gradle (thanks to Wrapper)
* Provide a consistent setup
* Make it possible to run frequent tasks via the IDE

## Testing

* Unit
    * we are fond of tests, TDD and high coverage
    * groovy tests (spock) for java code

## Integration testing

* every integration test can be ran in different modes:
    * embedded (speed, fast dev cycles, easy debugging)
    * forking (slower but more realistic)
    * daemon (excellent stress test and coverage test for the daemon)
    * parallel (excellent stress test and coverage test for parallel feature)

## Integration testing

* cross version integration tests (backward compatibility)
    * run test against a set of different versions of a 3rd party tool (e.g. groovy compilation)
    * run test against a set of Gradle versions
    * full suite VS recent version only
* tooling api cross version tests (backward and forward compatibility)
    * consumer & provider

## Testing

* Cross platform
* Distribution
* Performance

## Testable documentation

* Automatically tested documentation
    * samples (in user guide or in distribution)
        * evaluation
        * running arbitrary tasks and comparison of output
        * using samples content for integration tests
    * build script snippets in the javadoc - evaluation (dsl reference, javadocs)
    * java code snippets in the javadoc - compilation attempt (javadocs)
    * slideware automation

## Documentation

* Automatically deployed documentation
    * User guide
    * DSL reference
    * API reference
    * Release notes

## Code Quality

* Ensure public API is documented
* License headers
* Cyclical package dependencies
* Static analysis (Checkstyle & Codenarc)