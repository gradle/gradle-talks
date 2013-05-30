package org.gradle.testkit.functional;

import org.gradle.testkit.functional.internal.project.DefaultTestProject;

import java.io.File;

public abstract class TestProjectFactory {

    public static TestProject create(File dir) {
        return new DefaultTestProject(dir);
    }

}
