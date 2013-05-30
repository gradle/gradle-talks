package org.gradle.jstestdriver.test.integ

import org.gradle.internal.jvm.Jvm
import org.gradle.jstestdriver.TestUtil
import org.gradle.jstestdriver.browser.webdriver.ChromeBrowserFactory
import org.gradle.jstestdriver.process.JarFileJavaProcessFactory
import org.gradle.jstestdriver.process.JavaProcessFactory
import org.gradle.jstestdriver.server.JsTestDriverServer
import org.gradle.jstestdriver.server.JsTestDriverTestExecuter
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class JsTestDriverExecutionIntegTest extends Specification {

    @Rule
    TemporaryFolder tmp

    def "can execute tests successfully"() {
        given:
        def conf = tmp.newFile("conf.jstd")
        conf.text = """test:
  - test.js
"""
        def test = tmp.newFile("test.js")
        test.text = """
ExampleTest = TestCase("ExampleTest");

ExampleTest.prototype.testGreet = function() {
  assertEquals(true, true);
};
"""
        def output = tmp.newFolder("output")
        def executer = createExecuter()

        when:
        def pass = executer.execute(conf, output)

        then:
        pass

        and:
        output.listFiles().any { it.name.endsWith("ExampleTest.xml") }
    }


    JsTestDriverTestExecuter createExecuter() {
        def factory = new ChromeBrowserFactory()
        new JsTestDriverTestExecuter(createServer(), factory)
    }

    JsTestDriverServer createServer() {
        new JsTestDriverServer(createProcessFactory(), 1234)
    }

    JavaProcessFactory createProcessFactory() {
        new JarFileJavaProcessFactory(jsTestDriver, Jvm.current().javaExecutable)
    }

    File getJsTestDriver() {
        new TestUtil().jsTestDriverJar
    }

}
