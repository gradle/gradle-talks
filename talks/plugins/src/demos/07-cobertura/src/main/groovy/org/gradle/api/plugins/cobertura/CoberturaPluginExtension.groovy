package org.gradle.api.plugins.cobertura

import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.gradle.api.internal.ConventionMapping
import org.gradle.api.plugins.cobertura.tasks.CoberturaTask
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.testing.Test

class CoberturaPluginExtension {
    public static final NAME = "cobertura"

    FileCollection classpath

    File reportsDir

    String format = 'html'
    List<String> includes = ['**/*.java', '**/*.groovy', '**/*.scala']
    List<String> excludes = ['**/*Test.java', '**/*Test.groovy', '**/*Test.scala']

    List<String> ignores = ['org.apache.tools.*', 'net.sourceforge.cobertura.*']

    private final Project project

    CoberturaPluginExtension(Project project) {
        this.project = project
    }

    void addCoverage(Test testTask, SourceSet sourceSet) {
        CoberturaSourceSetExtension sourceSetExtension = sourceSet.cobertura

        def taskExtension = testTask.extensions.create("cobertura", CoberturaTestTaskExtension)
        ConventionMapping taskExtensionConventionMapping = taskExtension.conventionMapping
        taskExtensionConventionMapping.with {
            map("inputSerFile") { sourceSetExtension.serFile }
            map("outputSerFile") { new File(sourceSetExtension.serFile.absolutePath[0..-5]  + "-test.ser") }
        }

        def configureTask = project.tasks.add("coberturaConfigure${testTask.name.capitalize()}")
        configureTask.doFirst {
            testTask.systemProperty('net.sourceforge.cobertura.datafile', taskExtension.getOutputSerFile().absolutePath)
        }
        testTask.dependsOn(configureTask)

        FileCollection originalClasspath = testTask.classpath
        testTask.conventionMapping.with {
            map("classpath") {
                // Remove the uninstrumented classfiles
                def minusUninstrumented = originalClasspath - sourceSet.output
                def plusInstrumented = minusUninstrumented + sourceSetExtension.output

                // Add the uninstrumented class files (after) to cover class files that weren't instrumented (e.g. interfaces)
                def plusInstrumentedThenUninstrumented = plusInstrumented + sourceSet.output

                plusInstrumentedThenUninstrumented + sourceSetExtension.coberturaClasspath
            }
        }

        def coberturaTask = project.tasks.add("${testTask.name}CoberturaReport", CoberturaTask)
        coberturaTask.conventionMapping.with {
            map("source") { sourceSet.allSource }
            map("includes") { getIncludes() as Set }
            map("excludes") { getExcludes() as Set }
            map("format") { getFormat() }
            map("reportDir") { new File(getReportsDir(), sourceSet.name) }
            map("serFile") { taskExtension.getOutputSerFile() }
            map("coberturaClasspath") { sourceSetExtension.getCoberturaClasspath() }
        }

        testTask.inputs.files({ taskExtension.getInputSerFile() })
        testTask.outputs.files({ taskExtension.getOutputSerFile() })
        testTask.doFirst {
            testTask.project.copy {
                from taskExtension.getInputSerFile()
                into taskExtension.getOutputSerFile().parentFile
                rename ".*", taskExtension.getOutputSerFile().name
            }
        }
    }



}
