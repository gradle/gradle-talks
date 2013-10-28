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

Task API becomes part of the DSL.

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

Don't do redundant work.

Tasks annotate their properties to indicate inputs/outputs.

    @Input // for non file inputs (e.g. settings)

    @InputFile
    @InputFiles
    @InputDirectory

    @OutputFile
    @OutputFiles
    @OutputDirectory

Demo: `"03-custom-task/build-incremental.gradle"`

## Task Dependency Inference

Gradle has a *rich* model.

[`Buildable`](http://gradle.org/docs/current/javadoc/org/gradle/api/Buildable.html) objects effectively specify their producer.

Task outputs are buildable. Using the outputs of one task as an input to another is an inferred dependency.

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

Avoid locking up functionality behind the `Task` mechanism.

Implement the functionality in POJO classes, and wrap them in task adapters.

Demo: `"03-custom-task/build-thin.gradle"`