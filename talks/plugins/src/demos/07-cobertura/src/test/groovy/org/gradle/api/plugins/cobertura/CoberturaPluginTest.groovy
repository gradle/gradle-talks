package org.gradle.api.plugins.cobertura

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification
import org.gradle.api.plugins.JavaPlugin
import org.junit.Rule
import org.gradle.api.plugins.cobertura.util.TemporaryFolder

class CoberturaPluginTest extends Specification {

    @Rule TemporaryFolder temporaryFolder = new TemporaryFolder()
    Project project

    def setup() {
        project = ProjectBuilder.builder().withProjectDir(temporaryFolder.dir).build()
        project.apply(plugin: CoberturaPlugin)
    }

    def applyJavaPlugin() {
        project.apply(plugin: JavaPlugin)
    }

    def "extensions are installed"() {
        expect:
        project.extensions.getByName("cobertura") instanceof CoberturaPluginExtension
    }

    def "when java plugin is applied test task is extended"() {
        when:
        applyJavaPlugin()

        then:
        project.tasks.test.extensions.getByName("cobertura") instanceof CoberturaTestTaskExtension
    }

    def "when java plugin is applied the main source set is configured for coverage"() {
        when:
        applyJavaPlugin()

        then:
        project.sourceSets.main.extensions.getByName("cobertura") instanceof CoberturaSourceSetExtension
    }

    def "classpath is wired"() {
        given:
        applyJavaPlugin()
        def file = temporaryFolder.newFile("cobertura.jar")

        when:
        project.cobertura {
            classpath = project.files("cobertura.jar")
        }

        then:
        project.sourceSets.main.cobertura.coberturaClasspath.singleFile == file
        project.tasks.testCoberturaReport.coberturaClasspath.singleFile == file
    }
}
