package org.gradle.plugins.markdown

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

import java.nio.charset.Charset

class MarkdownSettings {

    String encoding = Charset.defaultCharset()

    final NamedDomainObjectContainer<MarkdownSource> source

    private final Project project

    MarkdownSettings(Project project, NamedDomainObjectContainer<MarkdownSource> source) {
        this.project = project
        this.source = source
    }

    void source(Closure configure) {
        source.configure(configure)
    }

}
