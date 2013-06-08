package support

import org.openqa.selenium.WebDriver
import org.testng.annotations.BeforeMethod
import server.RunningApplication
import server.RunningApplicationFactory

abstract class FunctionalTestSupport {

    RunningApplication application

    @BeforeMethod
    void setupDriver() {
        application = RunningApplicationFactory.get()
    }

}
