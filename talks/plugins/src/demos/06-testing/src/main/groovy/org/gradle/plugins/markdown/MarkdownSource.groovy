package org.gradle.plugins.markdown

import org.gradle.api.file.FileCollection
import org.gradle.api.Named
import org.gradle.api.Project

class MarkdownSource implements Named {

    final String name

    private Object markdown
    private Object html

    private final Project project

    MarkdownSource(String name, Project project) {
        this.name = name
        this.project = project
    }

    void setMarkdown(Object markdown) {
        this.markdown = markdown
    }

    FileCollection getMarkdown() {
        project.files(markdown)
    }

    void setHtml(Object html) {
        this.html = html
    }

    FileCollection getHtml() {
        project.files(html)
    }

}
