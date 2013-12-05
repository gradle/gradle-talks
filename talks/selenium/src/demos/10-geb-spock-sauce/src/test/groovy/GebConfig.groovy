import geb.driver.SauceLabsDriverFactory

def sauceBrowser = System.getProperty("geb.sauce.browser")
if (sauceBrowser) {
    driver = {
        def username = System.getenv("GEB_SAUCE_LABS_USER")
        assert username
        def accessKey = System.getenv("GEB_SAUCE_LABS_ACCESS_PASSWORD")
        assert accessKey
        new SauceLabsDriverFactory().create(sauceBrowser, username, accessKey)
    }
}

reportsDir = "reports"