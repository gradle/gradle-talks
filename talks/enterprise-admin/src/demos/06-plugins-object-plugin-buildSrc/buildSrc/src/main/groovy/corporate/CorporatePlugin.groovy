package corporate

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.*
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.compile.Compile

class CorporatePlugin implements Plugin<Project> {
    void apply(Project project) {

        // ONLY USE COPORATE REPO FOR DEPENDENCIES
        def coporateRepoUrl = "http://repo.gradle.org/gradle/repo"
        project.repositories {
            all { ArtifactRepository repo ->
                if (!(repo instanceof MavenArtifactRepository) || repo.url.toString() != coporateRepoUrl) {
                    project.logger.warn "Repository ${repo.url} removed. Only $coporateRepoUrl is allowed"
                    remove repo
                }
            }

            maven { url coporateRepoUrl }
        }

        project.plugins.withType(JavaPlugin) {

            // USE JUNIT 4.10
            project.dependencies {
                testCompile "junit:junit:4.10"
            }

            // INTEG TEST SUPPORT
            project.sourceSets {
                integTest {}
            }

            project.configurations {
                integTestCompile.extendsFrom testCompile
                integTestRuntime.extendsFrom testRuntime
            }

            project.task("integTest", type: Test) {
                testClassesDir = project.sourceSets.integTest.output.classesDir
                classpath = project.sourceSets.integTest.runtimeClasspath
            }

            // MUST COMPILE AS JAVA 1.5
            project.sourceCompatibility = "1.5"
            project.tasks.withType(Compile) {
                doFirst {
                    assert sourceCompatibility == "1.5" : "Something changed the sourceCompatibility of $name"
                }
            }
        }

    }
}