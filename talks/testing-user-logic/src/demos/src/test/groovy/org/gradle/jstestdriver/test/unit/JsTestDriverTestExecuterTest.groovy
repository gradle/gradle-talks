package org.gradle.jstestdriver.test.unit

import org.gradle.jstestdriver.browser.BrowserFactory
import org.gradle.jstestdriver.server.JsTestDriverServer
import org.gradle.jstestdriver.server.JsTestDriverTestExecuter
import spock.lang.Specification

class JsTestDriverTestExecuterTest extends Specification {

    def "creates and closes the browser on failure"() {
        given:
        def configFile = Mock(File)
        def outputDir = Mock(File)

        def serverUrl = "foo"

        def server = Mock(JsTestDriverServer) {
            1 * getCaptureUrl() >> serverUrl
        }

        def browser = Mock(Closeable)
        def browserFactory = Mock(BrowserFactory)

        def executer = new JsTestDriverTestExecuter(server, browserFactory)

        when:
        executer.execute(configFile, outputDir)

        then:
        1 * server.start()
        1 * browserFactory.open(serverUrl) >> browser

        then:
        1 * server.executeTests(configFile, outputDir) >> { throw new Exception() }
        1 * browser.close()

        then:
        1 * server.stop()

        then:
        thrown Exception
    }

}
