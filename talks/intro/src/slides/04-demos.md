# Demo time

## hello-world.jar

Simple single jar, Java project.

* Compile
* Test
* Analyze
* Deploy

## Incremental build

Gradle allows *any* unit of work to be made “incremental”.

If neither the inputs or outputs of a task have changed, there is no need to do it again.

Inputs can be files and/or properties (i.e. settings). 

Outputs are expressed as files.

## Autowiring

Using Gradle's rich model, task dependencies can be inferred. 

This avoids global properties (e.g. path properties) and duplication (who creates what).

Very useful for large builds where it's difficult to track which files are created by exactly which task.

## Multi Project Build

Gradle builds can be composed of several “projects”.

* Dynamic & flexible structure
* Project dependencies are first class citizens
* Partial builds (downstream and upstream)
* Configuration injection