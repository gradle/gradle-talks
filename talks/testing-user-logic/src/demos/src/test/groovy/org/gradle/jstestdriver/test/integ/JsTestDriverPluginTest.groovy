package org.gradle.jstestdriver.test.integ

import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.jstestdriver.JsTestDriverExtension
import org.gradle.jstestdriver.browser.webdriver.ChromeBrowserFactory
import org.gradle.jstestdriver.plugins.JsTestDriverPlugin
import org.gradle.jstestdriver.tasks.JsTestDriver
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

import static org.gradle.jstestdriver.plugins.JsTestDriverPlugin.CONFIGURATION_NAME

class JsTestDriverPluginTest extends Specification {

    Project project

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    def "adds extension and sets port"() {
        when:
        applyPlugin()

        then:
        extension.port == 1234
    }

    def "adds but does not populate configuration"() {
        when:
        applyPlugin()

        then:
        project.configurations[CONFIGURATION_NAME] != null
        project.configurations[CONFIGURATION_NAME].dependencies.empty
    }

    def "created tasks get defaults"() {
        when:
        applyPlugin()

        and:
        def task = createTask()

        then:
        task.port == extension.port
        task.browserFactory == extension.browserFactory

        when:
        extension.browserFactory = new ChromeBrowserFactory()

        then:
        task.browserFactory instanceof ChromeBrowserFactory
    }

    def JsTestDriver createTask() {
        project.tasks.create("jsTest", JsTestDriver)
    }

    def "creates default jstestdriver dep"() {
        when:
        applyPlugin()
        def task = createTask()

        then:
        task.jsTestDriverJar instanceof Configuration
        Configuration configuration = task.jsTestDriverJar
        configuration.dependencies.size() == 1
        def dependency = configuration.dependencies.toList().first()
        dependency.name == "jstestdriver"
        dependency.version == JsTestDriverPlugin.DEFAULT_VERSION
    }

    def "can change dep version"() {
        when:
        applyPlugin()
        def task = createTask()

        and:
        extension.version = "1.2"

        then:
        task.jsTestDriverJar instanceof Configuration
        Configuration configuration = task.jsTestDriverJar
        configuration.dependencies.size() == 1
        def dependency = configuration.dependencies.toList().first()
        dependency.name == "jstestdriver"
        dependency.version == "1.2"
    }

    JsTestDriverExtension getExtension() {
        project.jsTestDriver
    }

    def applyPlugin() {
        project.plugins.apply(JsTestDriverPlugin)
    }

}
