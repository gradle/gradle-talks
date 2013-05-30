package org.gradle.testkit.functional;

import java.io.File;

public interface TestProject {

    File getDirectory();

    File getBuildScript();

}
