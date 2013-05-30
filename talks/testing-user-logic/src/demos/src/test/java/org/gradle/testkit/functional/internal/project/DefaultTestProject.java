package org.gradle.testkit.functional.internal.project;

import org.gradle.testkit.functional.TestProject;
import org.gradle.util.GFileUtils;

import java.io.File;

public class DefaultTestProject implements TestProject {

    private final File directory;

    public DefaultTestProject(File directory) {
        this.directory = directory;
        GFileUtils.mkdirs(directory);
    }

    public File getDirectory() {
        return directory;
    }

    public File getBuildScript() {
        return new File(directory, "build.gradle");
    }
}
