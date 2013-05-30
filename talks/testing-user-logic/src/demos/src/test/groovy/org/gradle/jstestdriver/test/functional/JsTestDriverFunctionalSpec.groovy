package org.gradle.jstestdriver.test.functional

import org.gradle.jstestdriver.TestUtil

class JsTestDriverFunctionalSpec extends FunctionalSpecSupport {

    def "can test good js"() {
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

        and:
        buildScript """
            apply plugin: "jsTestDriver"

            import org.gradle.jstestdriver.tasks.JsTestDriver

            dependencies {
                jsTestDriver files("${new TestUtil().jsTestDriverJar}")
            }

            jsTestDriver {
                browserFactory = new org.gradle.jstestdriver.browser.webdriver.ChromeBrowserFactory()
            }

            task jsTest(type: JsTestDriver) {
                configFile = files("conf.jstd")
            }
        """

        when:
        args "jsTest"

        then:
        succeeds()

        and:
        file("build/test-results/jsTest").listFiles().find { it.name.endsWith("ExampleTest.xml") }
    }

    def "can test bad js"() {
        given:
        def conf = tmp.newFile("conf.jstd")
        conf.text = """test:
  - test.js
"""
        def test = tmp.newFile("test.js")
        test.text = """
ExampleTest = TestCase("ExampleTest");

ExampleTest.prototype.testGreet = function() {
  assertEquals(true, false);
};
"""

        and:
        buildScript """
            apply plugin: "jsTestDriver"

            import org.gradle.jstestdriver.tasks.JsTestDriver

            dependencies {
                jsTestDriver files("${new TestUtil().jsTestDriverJar}")
            }

            jsTestDriver {
                browserFactory = new org.gradle.jstestdriver.browser.webdriver.ChromeBrowserFactory()
            }

            task jsTest(type: JsTestDriver) {
                configFile = files("conf.jstd")
            }
        """

        when:
        args "jsTest"

        then:
        fails()

        and:
        file("build/test-results/jsTest").listFiles().find { it.name.endsWith("ExampleTest.xml") }
    }

}
