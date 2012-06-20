package org.gradle.plugins.markdown.pegdown

import spock.lang.Specification

class PegdownMarkdownProcessorTest extends Specification {

    PegdownMarkdownProcessor processor = new PegdownMarkdownProcessor()

    String markdown = ""

    def "can markdown"() {
        when:
        markdown = "This is a **paragraph**"

        then:
        html == "<p>This is a <strong>paragraph</strong></p>"
    }

    String getHtml() {
        Writer writer = new StringWriter()
        processor.markdown(new StringReader(markdown), writer)
        writer.toString()
    }
}
