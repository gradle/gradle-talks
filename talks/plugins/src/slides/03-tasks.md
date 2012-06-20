# Tasks

The unit of work

## Tasks

Tasks do stuff.

Different task implementations do different stuff.

They integrate functionality into the execution model and the build language.

    task copyStuff(type: Copy) {
        from "some-dir"
        into "another-dir"
    }

Ideally, they provide a domain specific declarative configuration interface.

## Custom Tasks

Gradle ships with many task types (e.g. `Copy`, `Exec`, `Delete`).

Adding your own is easy.

    task helloWorld(type: Echo) {
        message = "Hello World!"
    }

<!-- -->
    
    import org.gradle.api.tasks.*
    
    class Echo extends DefaultTask {
        String message
        @TaskAction void echo() {
            println message
        }
    }

# Demo

Custom Tasks

## Incremental Build

By declaring the inputs and outputs of tasks, Gradle can decide to skip them if they have been performed earlier.

They can be declared by annotating properties and getters in the task class.

    @Input // for non file inputs (e.g. settings)

    @InputFile
    @InputFiles
    @InputDirectory

    @OutputFile
    @OutputFiles
    @OutputDirectory

Demo: `"03-custom-task/build-incremental.gradle"`

## Task Dependency Inference

Objects in Gradle can be [`Buildable`](http://gradle.org/docs/current/javadoc/org/gradle/api/Buildable.html).

`Buildable` objects carry information about which tasks are needed to be executed to make the object “available”.

[`FileCollection`](http://gradle.org/docs/current/javadoc/org/gradle/api/file/FileCollection.html), the Gradle abstraction for a set of files, is `Buildable`.

Tasks expose their output files as a `FileCollection`.

    task copyIt(type: Copy) {
        from generationTask.output.files
        into "some-dir"
    }

Demo: `"03-custom-task/build-inference.gradle"`

## SourceTask

Gradle provides the `SourceTask` task superclass.

It has a convenient API for tasks that work on one or more files as “source”.

    class MyTask extends SourceTask {}
    
    task myTask(type: MyTask) {
        source "some/dir"
    }

The source is configured as input files.

Demo: `"03-custom-task/build-sourcetask.gradle"`

## Flexible Inputs

Consider being flexible and lazy with inputs, particularly `File` and `String`.

    class LazyTask extends DefaultTask {
        def inputFile, inputSetting

        @InputFile File getInputFile() {
            project.file(inputFile)
        }

        @Input String getInputSetting() {
            inputSetting.toString()
        }

        @TaskAction void doIt() {
            File inputFile = getInputFile() // …
        }
    }

## Flexible Inputs (cont.)

Usage…

    task lazy(type: LazyTask) {
        inputFile "foo.txt" // relative file path
        inputFile { someChangingVar } // deferred value
        inputSetting "${-> someChangingStringVar}" // lazy GString
    }

Future versions of Gradle will give this functionality to custom tasks for free.

## Thin Tasks

Tasks should be thin adapters.

Avoid locking up functionality behind the `Task` mechanism. It's inflexible and makes things more difficult to test.

Implement the functionality in POJO classes, and wrap them in task adapters. These adapters are optimised for the build language and execution model.

Demo: `"03-custom-task/build-thin.gradle"`