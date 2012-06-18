/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.plugins.xhtmlrenderer

import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction
import org.xhtmlrenderer.pdf.ITextRenderer
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional

class XhtmlToPdf extends SourceTask {

    File getXhtml() {
        source.empty ? null : source.singleFile
    }

    @OutputFile
    File pdf

    @InputFiles @Optional
    FileCollection fonts

    @TaskAction
    void render() {
        def renderer = new ITextRenderer()
        renderer.setDocument(xhtml)
        fonts.each {
            renderer.fontResolver.addFont(it.absolutePath, true)
        }
        renderer.layout()
        pdf.withOutputStream {
            renderer.createPDF(it)
        }
    }

}
