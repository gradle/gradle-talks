package org.gradle.jstestdriver.test.unit

import org.gradle.jstestdriver.process.JavaProcessFactory
import org.gradle.jstestdriver.server.JsTestDriverServer
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class JsTestDriverServerTest extends Specification {

    def port = 5050

    @Rule
    TemporaryFolder tmp

    def "can't start server twice"() {
        given:
        def process = GroovyMock(Process) {
            1 * consumeProcessOutput()
        }
        def factory = Mock(JavaProcessFactory) {
            1 * create(_, _) >> process
        }
        def server = new JsTestDriverServer(factory, port)

        when:
        server.start()

        then:
        noExceptionThrown()

        when:
        server.start()

        then:
        thrown IllegalStateException
    }

    def "tries to start on right port"() {
        given:
        def factory = Mock(JavaProcessFactory) {
            1 * create(JsTestDriverServer.JVM_ARGS, ["--port", "5050", "--runnerMode", "INFO"]) >> GroovyMock(Process)
            0 * create(_, _)
        }
        def server = new JsTestDriverServer(factory, port)

        when:
        server.start()

        then:
        noExceptionThrown()
    }

    def "can execute tests"() {
        def configFile = tmp.newFile("conf")
        def outputDir = tmp.newFolder("output")

        def serverProcess = GroovyMock(Process)
        def testProcess = GroovyMock(Process)

        def processFactory = Mock(JavaProcessFactory) {
            1 * create(_, { it.contains "--port" }) >> serverProcess
            1 * create(JsTestDriverServer.JVM_ARGS, { List<String> args ->
                def string = args.join(" ")
                string.contains("--config $configFile.absolutePath") &&
                string.contains("--testOutput $outputDir.absolutePath") &&
                string.contains("--server http://localhost:$port")
            }) >> testProcess
            0 * create(_, _)
        }

        1 * testProcess.waitFor()
        1 * testProcess.exitValue() >> 0

        def server = new JsTestDriverServer(processFactory, port)

        when:
        server.start()

        then:
        server.executeTests(configFile, outputDir)
    }

}
