package org.gradle.plugins.markdown

import spock.lang.Specification
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

class MarkdownPluginTest extends Specification {

    Project project = ProjectBuilder.builder().build()

    def setup() {
        project.apply(plugin: MarkdownPlugin)
    }

    def "extension is installed"() {
        expect:
        project.extensions.getByName("markdown") instanceof MarkdownSettings
    }

    def "main markdown created automatically"() {
        expect:
        project.markdown.source.findByName("main")
    }

    def "task default wirings"() {
        given:
        def mdFile = project.file("src/markdown/custom.md")
        mdFile.parentFile.mkdirs()
        mdFile << "Foo"

        when:
        project.markdown.source {
            custom {}
        }

        then:
        project.tasks.findByName("markdownCustom")
        project.markdownCustom.markdown == mdFile
    }

    def "task custom wirings"() {
        given:
        def mdFile = project.file("custom.md")
        mdFile.parentFile.mkdirs()
        mdFile << "Foo"

        when:
        project.markdown.source {
            custom {
                markdown = "custom.md"
            }
        }

        then:
        project.tasks.findByName("markdownCustom")
        project.markdownCustom.markdown == mdFile
    }

    def "global encoding is used"() {
        expect:
        project.markdownMain instanceof Markdown

        and:
        project.markdownMain.encoding == project.markdown.encoding

        when:
        project.markdown.encoding = "utf16"

        then:
        project.markdownMain.encoding == "utf16"
    }


}
