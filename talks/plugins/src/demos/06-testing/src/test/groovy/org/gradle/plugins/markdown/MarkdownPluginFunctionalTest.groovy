package org.gradle.plugins.markdown

import org.gradle.plugins.markdown.util.FunctionalSpec

class MarkdownPluginFunctionalTest extends FunctionalSpec {

    private static final MARKDOWN_1 = "This is a **paragraph**"
    private static final HTML_1 = "<p>This is a <strong>paragraph</strong></p>"

    private static final MARKDOWN_2 = "This is a *different* **paragraph**"
    private static final HTML_2 = "<p>This is a <em>different</em> <strong>paragraph</strong></p>"

    def setup() {
        buildFile << applyPlugin(MarkdownPlugin)
    }

    def "can generate markdown by convention"() {
        given:
        createFile("src/markdown/main.md") << MARKDOWN_1

        when:
        run "markdownMain"

        then:
        wasExecuted ":markdownMain"

        and:
        file("build/markdown-html/main.html").text == HTML_1

        when:
        run "markdownMain"

        then:
        wasUpToDate ":markdownMain"

        when:
        file("src/markdown/main.md").text = MARKDOWN_2

        and:
        run "markdownMain"

        then:
        !wasUpToDate(":markdownMain")

        and:
        file("build/markdown-html/main.html").text == HTML_2
    }

    def "can infer task dependencies"() {
        given:
        buildFile << """
            task generateMd {
                ext.md = "\$buildDir/some.md"
                outputs.file md

                doLast {
                    def file = file(md)
                    file.parentFile.mkdirs()
                    file.text = "${MARKDOWN_1}"
                }
            }

            markdown {
                source {
                    generated {
                        markdown = generateMd
                    }
                }
            }
        """

        when:
        run "markdownGenerated"

        then:
        wasExecuted ":generateMd"
        wasExecuted ":markdownGenerated"

        and:
        file("build/markdown-html/generated.html").text == HTML_1
    }

}
