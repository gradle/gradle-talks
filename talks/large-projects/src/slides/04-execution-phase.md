## Fast execution

* fast configuration
* parallel build (demo!)
* daemon
    * up-to-date checks
    * jvm optimisation
* profiling the build with --profile (demo!)

## Parallel builds

* require decoupled projects (execution time only)
* works best if DAG does not have too many edges
* parallelizes accross projects only
    * thread safety
* safe parallel execution
    * avoid mutable system properties
    * or any mutable static state
