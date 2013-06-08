package support

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities

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
            DesiredCapabilities capabilities = DesiredCapabilities.chrome()
            ChromeOptions options = new ChromeOptions()
            options.addArguments("-silent")
            capabilities.setCapability(ChromeOptions.CAPABILITY, options)
            driver = new ChromeDriver(capabilities)
        } else if (property == "firefox") {
            driver = new FirefoxDriver()
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
