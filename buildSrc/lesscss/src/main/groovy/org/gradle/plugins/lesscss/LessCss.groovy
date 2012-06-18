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

package org.gradle.plugins.lesscss

import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction

class LessCss extends SourceTask {

    private destination

    @OutputDirectory
    File getDestination() {
        project.file(destination)
    }

    void setDestination(destination) {
        this.destination = destination
    }

    @InputFiles
    FileCollection lessCssRhinoJs

    @InputFiles
    FileCollection rhinoClasspath

    @TaskAction
    void doLessCss() {
        getSource().files.each { File fileSource ->
            def fileDestination = new File(getDestination(), "${fileSource.name}.css")
            project.javaexec {
                systemProperty "file.encoding", "utf-8"
                main = "org.mozilla.javascript.tools.shell.Main"
                args getLessCssRhinoJs().singleFile.canonicalPath, fileSource.canonicalPath
                standardOutput = fileDestination.newOutputStream()
                errorOutput = System.err
                classpath rhinoClasspath
            }
        }
    }
}
