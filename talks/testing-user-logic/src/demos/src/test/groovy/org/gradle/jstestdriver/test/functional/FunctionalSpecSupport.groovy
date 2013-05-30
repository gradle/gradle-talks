package org.gradle.jstestdriver.test.functional

import org.gradle.testkit.functional.ExecutionResult
import org.gradle.testkit.functional.GradleRunner
import org.gradle.testkit.functional.GradleRunnerFactory
import org.gradle.testkit.functional.TestProject
import org.gradle.testkit.functional.TestProjectFactory
import org.gradle.tooling.BuildException
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

abstract class FunctionalSpecSupport extends Specification {

    @Rule TemporaryFolder tmp

    TestProject project
    GradleRunner runner

    ExecutionResult result

    def setup() {
        project = TestProjectFactory.create(tmp.root)
        runner = GradleRunnerFactory.create()
        runner.directory = project.directory
    }

    void buildScript(String text) {
        project.buildScript.text = text
    }

    void args(String... args) {
        runner.arguments = args.toList()
    }

    File file(String path) {
        new File(tmp.root, path)
    }

    boolean succeeds() {
        result = runner.run()
    }

    boolean fails() {
        try {
            succeeds()
            throw new AssertionError("Build was expected to fail but it passed")
        } catch (BuildException ignore) {
            true
        }
    }
}
