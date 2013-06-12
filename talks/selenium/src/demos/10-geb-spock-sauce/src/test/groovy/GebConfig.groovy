import org.openqa.selenium.Platform
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

def sauceBrowser = System.getProperty("browser")
driver = {
    def username = "gebish"
    def accessKey = "4d767d16-b109-4cf7-a2d1-f5dd175a0ed6"

    def url = new URL("http://$username:$accessKey@ondemand.saucelabs.com:80/wd/hub")

    def parts = sauceBrowser.split(":", 3)
    def name = parts[0]
    def platform = parts.size() > 1 ? parts[1] : null
    def version = parts.size() > 2 ? parts[2] : null

    DesiredCapabilities browser = DesiredCapabilities."$name"();
    if (platform) {
        try {
            platform = Platform."${platform.toUpperCase()}"
        } catch (MissingPropertyException ignore) {

        }
        browser.setCapability("platform", platform)
    }
    if (version != null) {
        browser.setCapability("version", version.toString())
    }

    new RemoteWebDriver(url, browser)
}

reportsDir = "reports"