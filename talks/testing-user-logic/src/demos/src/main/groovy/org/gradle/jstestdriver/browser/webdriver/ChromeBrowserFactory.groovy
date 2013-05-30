package org.gradle.jstestdriver.browser.webdriver

import org.openqa.selenium.chrome.ChromeDriver

class ChromeBrowserFactory extends WebDriverBackedBrowserFactory {

    ChromeBrowserFactory() {
        super({ new ChromeDriver() })
    }
}
