package org.gradle.plugins.markdown

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.pegdown.PegDownProcessor
import org.gradle.api.NamedDomainObjectContainer

class MarkdownBasePlugin implements Plugin<Project> {

    void apply(Project project) {
        NamedDomainObjectContainer<MarkdownSource> source = project.container(MarkdownSource) { String name ->
            new MarkdownSource(name, project)
        }

        MarkdownSettings settings = project.extensions.create("markdown", MarkdownSettings, project, source)

        project.tasks.withType(Markdown) { Markdown task ->
            task.conventionMapping.map("encoding") { settings.encoding }
        }

        source.all { MarkdownSource sourceItem ->
            sourceItem.markdown = project.files("src/markdown/${sourceItem.name}.md")

            project.task("markdown${sourceItem.name.capitalize()}", type: Markdown) { Markdown task ->
                task.conventionMapping.map("source") { sourceItem.markdown.asFileTree }
                task.conventionMapping.map("html") { project.file("$project.buildDir/markdown-html/${sourceItem.name}.html") }
                sourceItem.html = task.outputs.files
            }
        }
    }

}