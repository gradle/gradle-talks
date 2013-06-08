package support

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
        String property = System.getProperty("driver", "chrome")
        def driver
        if (property == "chrome") {
            driver = new ChromeDriver()
        } else if (property == "firefox") {
            driver = new FirefoxDriver()
        } else if (property == "ie") {
            def capabilities = DesiredCapabilities.internetExplorer()
            def url = System.getProperty("seleniumServerUrl")
            if (!url) {
                throw new IllegalStateException("No 'seleniumServerUrl' system property set")
            }
            driver = new RemoteWebDriver(new URL(url), capabilities)
        }

        Runtime.addShutdownHook {
            try {
                driver.quit()
            } catch (ignore) {
            }
        }
        driver
    }

}
