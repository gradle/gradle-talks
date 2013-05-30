package org.gradle.testkit.functional.internal.classpath;

import org.gradle.api.Transformer;
import org.gradle.internal.UncheckedException;
import org.gradle.testkit.functional.internal.GradleHandle;
import org.gradle.testkit.functional.internal.GradleHandleFactory;
import org.gradle.util.ClasspathSource;
import org.gradle.util.CollectionUtils;
import org.gradle.util.GFileUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClasspathInjectingGradleHandleFactory implements GradleHandleFactory {

    private final ClasspathSource classpathSource;
    private final GradleHandleFactory delegateFactory;

    public ClasspathInjectingGradleHandleFactory(ClasspathSource classpathSource, GradleHandleFactory delegateFactory) {
        this.classpathSource = classpathSource;
        this.delegateFactory = delegateFactory;
    }

    public GradleHandle start(File directory, List<String> arguments) {
        File testKitDir = new File(directory, ".gradle-test-kit");
        if (!testKitDir.exists()) {
            GFileUtils.mkdirs(testKitDir);
        }

        File initScript = new File(testKitDir, "init.gradle");
        new ClasspathAddingInitScriptBuilder().build(initScript, getClasspathAsFiles());

        List<String> ammendedArguments = new ArrayList<String>(arguments.size() + 2);
        ammendedArguments.add("-I");
        ammendedArguments.add(initScript.getAbsolutePath());
        ammendedArguments.addAll(arguments);
        return delegateFactory.start(directory, ammendedArguments);
    }

    private List<File> getClasspathAsFiles() {
        List<URL> classpathUrls = new LinkedList<URL>();
        classpathSource.collectClasspath(classpathUrls);
        return CollectionUtils.collect(classpathUrls, new ArrayList<File>(classpathUrls.size()), new Transformer<File, URL>() {
            public File transform(URL url) {
                try {
                    return new File(url.toURI());
                } catch (URISyntaxException e) {
                    throw UncheckedException.throwAsUncheckedException(e);
                }
            }
        });
    }

}
