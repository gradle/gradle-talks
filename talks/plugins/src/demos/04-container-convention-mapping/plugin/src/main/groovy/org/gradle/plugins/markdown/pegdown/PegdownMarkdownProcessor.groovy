package org.gradle.plugins.markdown.pegdown

import org.pegdown.PegDownProcessor
import org.gradle.plugins.markdown.MarkdownProcessor

class PegdownMarkdownProcessor implements MarkdownProcessor {
    void markdown(Reader markdown, Writer html) {
        def processor = new PegDownProcessor()
        html << processor.markdownToHtml(markdown.text)
    }
}
