package org.gradle.jstestdriver.server

import org.gradle.jstestdriver.process.JavaProcessFactory

class JsTestDriverServer {

    public static final ArrayList<String> JVM_ARGS = ["-Dfile.encoding=utf8"].asImmutable()
    private final JavaProcessFactory processFactory
    private final int port

    Process process

    JsTestDriverServer(JavaProcessFactory processFactory, int port) {
        this.processFactory = processFactory
        this.port = port
    }

    String getCaptureUrl() {
        "$serverUrl/capture"
    }

    private String getServerUrl() {
        if (process == null) {
            throw new IllegalStateException("Server is not started")
        }

        "http://localhost:$port"
    }

    void start() {
        if (process != null) {
            throw new IllegalStateException("Server is already started")
        } else {
            process = processFactory.create(JVM_ARGS, [
                    "--port", port.toString(),
                    "--runnerMode", "INFO"
            ])
            process.consumeProcessOutput()
        }
    }

    void stop() {
        if (process != null) {
            process.destroy()
        } else {
            throw new IllegalStateException("Server is not started")
        }
    }

    boolean executeTests(File configFile, File outputDir) {
        def testProcess = processFactory.create(JVM_ARGS, [
                "--config", configFile.absolutePath,
                "--testOutput", outputDir.absolutePath,
                "--raiseOnFailure", "true",
                "--server", getServerUrl().toString(),
                "--tests",  "all",
                "--verbose",
        ])

        testProcess.consumeProcessOutput(System.out, System.err)
        testProcess.waitFor()
        testProcess.exitValue() == 0
    }

}
