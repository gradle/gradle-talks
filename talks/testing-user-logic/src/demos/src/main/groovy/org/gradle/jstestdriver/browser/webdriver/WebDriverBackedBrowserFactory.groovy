package org.gradle.jstestdriver.browser.webdriver


import org.gradle.jstestdriver.browser.BrowserFactory
import org.openqa.selenium.WebDriver

import java.util.concurrent.Callable

class WebDriverBackedBrowserFactory implements BrowserFactory {

    private final Callable<? extends WebDriver> factory

    WebDriverBackedBrowserFactory(Callable<? extends WebDriver> factory) {
        this.factory = factory
    }

    Closeable open(String url) {
        WebDriver webDriver = factory.call()
        webDriver.get(url)
        return new Closeable() {
            void close() {
                webDriver.quit()
            }
        }
    }

}
