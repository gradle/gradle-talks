## Specific build logic

The majority of logic in `build.gradle` files can be considered "specific".

Examples:

* Adding dependencies
* Creating tasks
* Compiler configuration
* Upload URL

How specific builds work.

## Specific build logic

"general" to "specific" is a spectrum.

Gradle provides many facilities for applying "specific" configuration in "general" ways.

    tasks.withType(Test) {
      maxParallelForks = 4
    }

Rule of thumb: is the logic _deductive_ or _literal_?

## Testing specific logic

Testing specific build logic can be challenging:

* Hard to isolate
* Lots of setup required
* What to test for?
* What does it mean to test it?

## Testing specific logic

General strategy: test your _outputs_ and exercise your _functions_.

**Outputs**: does the build build the correct thing?

* Archives
* Documentation

**Functions**: do the development/release functions work?

* IDE integration
* Dev time app bootstrap
* Integrate upstream changes (if not automatic)

## Testing outputs

This is automatically done by your users.

* downstream projects for libs
* real users for apps

What if this is too late?

## Testing outputs before users

Some strategies:

* Use production like outputs during downstream testing
    * avoid rebuilds
* Build the same way for the final release
    * minimise contextual variations
* Write explicit tests for non functional characteristics

# Example 

Gradle distribution tests

---

* Show the distribution tests in the IDE
* Show the CI integ tests using production binaries

## Testing functions

Generally hard to comprehensively test.

Examples:

* IDE provisioning
* Release process
* Development environment setup

## Testing functions

General strategy: exercise often and smoke test.

Examples:

* Nightly build simulating release process
* CI build to run `idea` or `eclipse` tasks
    * Try actually opening the project in the IDE 
    * GUI testing: http://www.sikuli.org/
* Build a dev environment and test it "starts"

Not always easy and clean like unit tests.

# Example 

Testing Gradle releases

---

* Show the nightly build
    * Discuss it doing nearly the same thing as a full release
* Show how the nightly build smoke tests post release
* Show how the website tests test that the website is in good shape

## Testing "specific" logic

Always do cost/benefit analysis.

These kinds of tests tend to be brittle and produce false failures.

Don't overestimate the cost, and don't underestimate the benefits.
