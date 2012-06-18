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
import org.gradle.api.Transformer;

public class ResultTransformingClosureDecorator<R, T> extends Closure<R> {

    private Transformer<R, T> transformer;
    private final Closure<? extends T> delegateClosure;

    public ResultTransformingClosureDecorator(Transformer<R, T> transformer, Closure<? extends T> delegateClosure) {
        super(delegateClosure.getOwner(), delegateClosure.getThisObject());
        this.delegateClosure = delegateClosure;
        this.transformer = transformer;
    }

    public void setDelegate(Object delegate) {
        delegateClosure.setDelegate(delegate);
    }

    public Object getDelegate() {
        return delegateClosure.getDelegate();
    }

    public void setResolveStrategy(int resolveStrategy) {
        delegateClosure.setResolveStrategy(resolveStrategy);
    }

    public int getResolveStrategy() {
        return delegateClosure.getResolveStrategy();
    }

    public Object clone() {
        return new ResultTransformingClosureDecorator<R, T>(transformer, delegateClosure);
    }

    public Class[] getParameterTypes() {
        return delegateClosure.getParameterTypes();
    }

    public Object doCall(Object... args) {
        return call(args);
    }

    @Override
    public R call(Object... args) {
        T result = delegateClosure.call(args);
        return transformer.transform(result);
    }

}
