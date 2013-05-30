package org.gradle.jstestdriver.server


import org.gradle.jstestdriver.browser.BrowserFactory

class JsTestDriverTestExecuter {

    private final JsTestDriverServer server
    private final BrowserFactory browserFactory

    JsTestDriverTestExecuter(JsTestDriverServer server, BrowserFactory browserFactory) {
        this.server = server
        this.browserFactory = browserFactory
    }

    boolean execute(File configFile, File outputDir) {
        server.start()

        Closeable browser = browserFactory.open(server.captureUrl)

        try {
            server.executeTests(configFile, outputDir)
        } finally {
            browser.close()
            server.stop()
        }
    }

}
