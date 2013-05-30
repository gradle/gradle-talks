package org.gradle.jstestdriver.process

interface JavaProcessFactory {

    Process create(List<String> jvmArgs, List<String> jsTestDriverArgs)

}
