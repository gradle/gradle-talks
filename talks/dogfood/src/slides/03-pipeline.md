## Build pipeline

* TeamCity
* Binary reuse
* Continuous Delivery
* Promote passed builds
* 1 click release promotion

## Release time

1. Rebuild with final version number
2. Quick smoke test
3. Upload binaries (Artifactory and Amazon S3)
4. Checkout website repo, update, push
5. Trigger doc pull
6. Smoke test released binaries
7. Send email with announcement instructions
