package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> CHROME_HOLDER = new ThreadLocal<WebDriver>() {
        protected WebDriver initialValue() {
            return quitOnShutdown(new ChromeDriver());
        }
    };

    private static final ThreadLocal<WebDriver> FIREFOX_HOLDER = new ThreadLocal<WebDriver>() {
        protected WebDriver initialValue() {
            return quitOnShutdown(new FirefoxDriver());
        }
    };

    private static WebDriver quitOnShutdown(final WebDriver driver) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                driver.quit();
            }
        });
        return driver;
    }

    @DataProvider(name = "drivers")
    public static Object[][] drivers() {
        return new Object[][]{
                new Object[]{CHROME_HOLDER.get()},
                new Object[]{FIREFOX_HOLDER.get()}
        };
    }

}
