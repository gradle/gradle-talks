package support

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

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
