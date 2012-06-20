package org.gradle.plugins.markdown

import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.SourceTask
import org.gradle.plugins.markdown.pegdown.PegdownMarkdownProcessor

class Markdown extends SourceTask {
    @OutputFile File html

    @Input String encoding

    MarkdownProcessor processor = new PegdownMarkdownProcessor()

    File getMarkdown() {
        getSource().singleFile
    }

    @TaskAction
    void markdown() {
        getMarkdown().withReader(getEncoding()) { markdownReader ->
            getHtml().withWriter(getEncoding()) { htmlWriter ->
                processor.markdown(markdownReader, htmlWriter)
            }
        }
    }

}