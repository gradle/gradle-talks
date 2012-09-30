package org.gradle.api.plugins.cobertura.tasks

import org.gradle.api.file.FileCollection
import org.gradle.api.internal.project.IsolatedAntBuilder
import org.gradle.api.tasks.*

class CoberturaTask extends SourceTask {

    @Input
    String format

    @OutputDirectory
    File reportDir

    @InputFile
    File serFile

    @InputFiles
    FileCollection coberturaClasspath

    @TaskAction
    def run() {
        def source = getSource()
        if (!source.empty) {
            def ant = getServices().get(IsolatedAntBuilder).withClasspath(getCoberturaClasspath())
            ant.execute {
                taskdef(name: 'cobertura-report', classname: "net.sourceforge.cobertura.ant.ReportTask")
                'cobertura-report'(format: getFormat(), destdir: getReportDir(), datafile: getSerFile()) {
                    getSource().addToAntBuilder(delegate, "fileset", FileCollection.AntType.FileSet)
                }
            }
        } else {
            logger.warn 'Cobertura cannot run becuase no source directories were found.'
        }
    }
}
