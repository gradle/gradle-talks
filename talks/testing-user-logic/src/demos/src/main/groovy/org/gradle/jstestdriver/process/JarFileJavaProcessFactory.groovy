package org.gradle.jstestdriver.process

class JarFileJavaProcessFactory implements JavaProcessFactory {

    private final File jarFile
    private final File javaCommand

    JarFileJavaProcessFactory(File jarFile, File javaCommand) {
        this.jarFile = jarFile
        this.javaCommand = javaCommand
    }

    @Override
    Process create(List<String> jvmArgs, List<String> jsTestDriverArgs) {
        List<String> args = []
        args.add(javaCommand.absolutePath)
        args.addAll(jvmArgs*.toString())
        args.add("-jar")
        args.add(jarFile.absolutePath)
        args.addAll(jsTestDriverArgs*.toString())
        new ProcessBuilder(args).redirectErrorStream(true).start()
    }
}

