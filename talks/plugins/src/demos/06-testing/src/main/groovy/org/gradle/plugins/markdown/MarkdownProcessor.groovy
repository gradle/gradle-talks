package org.gradle.plugins.markdown

interface MarkdownProcessor {
    void markdown(Reader markdown, Writer html)
}