package org.gradle.jstestdriver

import com.google.jstestdriver.JsTestDriverServer

class TestUtil {

    File getJsTestDriverJar() {
        findJarFile(JsTestDriverServer)
    }

    private File findJarFile(Class targetClass) {
        def absolutePath = targetClass.getResource('/' + targetClass.name.replace(".", "/") + ".class").path
        def jarPath = absolutePath.substring("file:".length(), absolutePath.lastIndexOf("!"))
        new File(jarPath)
    }

}
