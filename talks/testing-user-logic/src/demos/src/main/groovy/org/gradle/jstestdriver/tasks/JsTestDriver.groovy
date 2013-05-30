package org.gradle.jstestdriver.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.jvm.Jvm
import org.gradle.jstestdriver.browser.BrowserFactory
import org.gradle.jstestdriver.process.JarFileJavaProcessFactory
import org.gradle.jstestdriver.server.JsTestDriverServer
import org.gradle.jstestdriver.server.JsTestDriverTestExecuter

class JsTestDriver extends DefaultTask {

    @InputFiles
    FileCollection jsTestDriverJar

    @InputFiles
    FileCollection javaCommand = project.files(Jvm.current().javaExecutable)

    @InputFiles
    FileCollection configFile

    @OutputDirectory
    File outputDir

    BrowserFactory browserFactory

    int port = 1234

    @TaskAction
    void execTests() {
        def processFactory = new JarFileJavaProcessFactory(getJsTestDriverJar().singleFile, getJavaCommand().singleFile)
        def server = new JsTestDriverServer(processFactory, getPort())
        def testExecution = new JsTestDriverTestExecuter(server, getBrowserFactory())

        boolean success = testExecution.execute(getConfigFile().singleFile, getOutputDir())

        if (!success) {
            throw new GradleException("There were failing JsTestDriver tests (reports: ${getOutputDir().absolutePath})")
        }
    }

}
