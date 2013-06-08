package org.gradle.plugins.markdown

import org.gradle.api.Project
import org.gradle.api.Plugin

class MarkdownPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.apply plugin: MarkdownBasePlugin

        // Pre-create the “main” markdown
        project.extensions.getByType(MarkdownSettings).source.create("main")
    }
}
