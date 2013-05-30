package org.gradle.jstestdriver.browser.webdriver

import org.openqa.selenium.WebDriver
import spock.lang.Specification

class WebDriverBackedBrowserFactoryTest extends Specification {

    def "closing browser quits driver"() {
        given:
        def url = "foo"
        def driver = Mock(WebDriver) {
            1 * get(url)
            1 * quit()
            0 * _(*_)
        }

        when:
        def factory = new WebDriverBackedBrowserFactory({ driver })

        then:
        factory.open(url).close()
    }

}
