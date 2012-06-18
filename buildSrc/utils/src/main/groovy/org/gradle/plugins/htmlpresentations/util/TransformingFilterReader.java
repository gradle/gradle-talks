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

package org.gradle.plugins.htmlpresentations.util;

import groovy.lang.Closure;
import org.apache.commons.io.IOUtils;
import org.gradle.api.Transformer;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class TransformingFilterReader extends FilterReader {

    public TransformingFilterReader(Reader reader) {
        super(new DeferringTransformingReader(reader));
    }

    public void transformer(Transformer<String, String> transformer) {
        ((DeferringTransformingReader) super.in).setTransformer(transformer);
    }

    public void transformer(Closure<?> closure) {
        Closure<String> toStringClosure = new ResultTransformingClosureDecorator<String, Object>(new ToStringTransformer(), closure);
        Transformer<String, String> transformerClosure = new TransformerClosure<String, String>(toStringClosure);
        transformer(transformerClosure);
    }

    private static class DeferringTransformingReader extends Reader {
        private final Reader source;
        private Reader delegate;

        private Transformer<String, String> transformer = null;

        public DeferringTransformingReader(Reader source) {
            this.source = source;
        }

        public void setTransformer(Transformer<String, String> transformer) {
            this.transformer = transformer;
        }

        public int read(char[] cbuf, int off, int len) throws IOException {
            if (delegate == null) {
                if (transformer == null) {
                    throw new IllegalStateException("No transformer has been set");
                }
                delegate = new StringReader(transformer.transform(IOUtils.toString(source)));
            }

            return delegate.read(cbuf, off, len);
        }

        public void close() {}
    }

}
