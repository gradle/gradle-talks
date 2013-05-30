package org.gradle.jstestdriver.browser

interface BrowserFactory {

    Closeable open(String url)

}
