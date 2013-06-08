package support

import org.openqa.selenium.Platform
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER_HOLDER = new ThreadLocal<>() {
        protected Object initialValue() {
            createNewDriver()
        }
    }

    static WebDriver get() {
        DRIVER_HOLDER.get()
    }

    private static WebDriver createNewDriver() {
        def url = System.getProperty("seleniumServerUrl")
        if (!url) {
            throw new IllegalStateException("No 'seleniumServerUrl' system property set")
        }

        String property = System.getProperty("driver", "chrome")
        def capabilities
        if (property == "chrome") {
            capabilities = DesiredCapabilities.chrome()
        } else if (property == "firefox") {
            capabilities = DesiredCapabilities.firefox()
            capabilities.setPlatform(Platform.MAC)
        } else if (property == "ie") {
            capabilities = DesiredCapabilities.internetExplorer()
        } else {
            throw new Exception("Unknown driver: $property")
        }
        def driver = new RemoteWebDriver(new URL(url), capabilities)

        Runtime.addShutdownHook {
            try {
                driver.quit()
            } catch (ignore) {
            }
        }
        driver
    }

}
