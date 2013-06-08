package support

import org.openqa.selenium.WebDriver
import org.testng.annotations.BeforeMethod
import server.RunningApplication
import server.RunningApplicationFactory

abstract class FunctionalTestSupport {

    WebDriver driver
    RunningApplication application

    @BeforeMethod
    void setupDriver() {
        driver = DriverFactory.get()
        application = RunningApplicationFactory.get()
    }

}
