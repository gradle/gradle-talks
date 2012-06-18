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

package org.gradle.plugins.lesscss;

import org.gradle.api.Action;
import org.gradle.api.Transformer;
import org.gradle.plugins.htmlpresentations.util.TransformingFilterReader;
import org.gradle.process.JavaExecSpec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.Reader;

public class LessCssFilterReader extends TransformingFilterReader {

    private File lessCssRhinoJs;
    private Iterable<File> rhinoClasspath;
    private Action<Action<JavaExecSpec>> javaExecRunner;
    private File workingDir;

    public LessCssFilterReader(Reader reader) {
        super(reader);

        transformer(new Transformer<String, String>() {
            public String transform(String less) {
                OutputStream outputStream = new ByteArrayOutputStream();

                javaExecRunner.execute(new Action<JavaExecSpec>() {
                    public void execute(JavaExecSpec javaExecSpec) {

                        /// Less rhino is hardcoded to read from a file

//                        javaExecSpec.setWorkingDir(workingDir);
//                        javaExecSpec.setMain("org.mozilla.javascript.tools.shell.Main");
//
//                        args getLessCssRhinoJs().singleFile.canonicalPath, fileSource.canonicalPath
//                        standardOutput = fileDestination.newOutputStream()
//                        errorOutput = System.err
//                        classpath rhinoClasspath


                    }
                });

                return "in progress";
            }
        });
    }

    public File getLessCssRhinoJs() {
        return lessCssRhinoJs;
    }

    public void setLessCssRhinoJs(File lessCssRhinoJs) {
        this.lessCssRhinoJs = lessCssRhinoJs;
    }

    public Iterable<File> getRhinoClasspath() {
        return rhinoClasspath;
    }

    public void setRhinoClasspath(Iterable<File> rhinoClasspath) {
        this.rhinoClasspath = rhinoClasspath;
    }

    public Action<Action<JavaExecSpec>> getJavaExecRunner() {
        return javaExecRunner;
    }

    public void setJavaExecRunner(Action<Action<JavaExecSpec>> javaExecRunner) {
        this.javaExecRunner = javaExecRunner;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }
}
