## Build pipeline

* Runs on every push 
* Identical on both branches (master & release)
* TeamCity

## Pipeline structure

* Static analysis
* Quick, less accurate tests (small number of platforms)
* Build production like distributions
* Platform tests
* Execution mode tests
* Performance test
* Promote

## Reusing binaries

* Production like binaries built at start of pipeline
* CI server pushes binaries to downstream builds

## Release cycle

A cycle is ~ 6 weeks.

* Move to release branch after ~ 4 weeks
* Plan new release ~ 4 weeks
* Promote RC ~ 5 weeks
* Promote final (end)
* Merge release branch back to master (final)

## Promotion

* Separate “build” for promotion
* Automatically checks out specific revision
* Programatically rebuilds Gradle
* Imposes the version number
* Triggered by 1 click @ CI server

## Delivery

* Build, smoke test
* Decorate docs with Google Analytics JS
* Upload to `repo.gradle.org` (some bits)
* Upload dists and decorated docs to Amazon S3
* Checkout website repo, update data, push
* Trigger pull new docs
* Smoke test delivered distributions
* Send notification email to team
* Finish with manual processes