package org.gradle.jstestdriver.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration
import org.gradle.api.internal.ConventionMapping
import org.gradle.api.plugins.BasePlugin
import org.gradle.jstestdriver.JsTestDriverExtension
import org.gradle.jstestdriver.tasks.JsTestDriver

class JsTestDriverPlugin implements Plugin<Project> {

    public static final int DEFAULT_PORT = 1234
    public static final String CONFIGURATION_NAME = "jsTestDriver"
    public static final String DEFAULT_VERSION = "1.3.5"

    @Override
    void apply(Project project) {
        project.plugins.apply(BasePlugin)
        def extension = project.extensions.create("jsTestDriver", JsTestDriverExtension)
        extension.port = DEFAULT_PORT
        extension.version = DEFAULT_VERSION

        Configuration jsTestDriverConfiguration = project.configurations.create(CONFIGURATION_NAME)

        project.tasks.withType(JsTestDriver) { Task task ->
            ConventionMapping conventionMapping = task.conventionMapping
            conventionMapping.with {
                map("port") {
                    extension.port
                }
                map("jsTestDriverJar") {
                    if (jsTestDriverConfiguration.dependencies.empty) {
                        def defaultDependency = project.dependencies.create("com.google.jstestdriver:jstestdriver:$extension.version")
                        jsTestDriverConfiguration.dependencies.add(defaultDependency)
                    }

                    jsTestDriverConfiguration
                }
                map("browserFactory") {
                    extension.browserFactory
                }
                map("outputDir") {
                    new File(project.buildDir, "test-results/$task.name")
                }
            }
        }
    }
}
