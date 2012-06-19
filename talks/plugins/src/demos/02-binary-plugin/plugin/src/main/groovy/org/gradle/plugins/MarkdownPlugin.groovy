package org.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.pegdown.PegDownProcessor

class MarkdownPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.task("markdown") << {
            PegDownProcessor processor = new PegDownProcessor()
            String markdown = project.file("src/some.md").text
            String html = processor.markdownToHtml(markdown)

            def htmlFile = project.file("$project.buildDir/some.html")
            htmlFile.parentFile.mkdirs()
            htmlFile.text = html
        }
    }

}