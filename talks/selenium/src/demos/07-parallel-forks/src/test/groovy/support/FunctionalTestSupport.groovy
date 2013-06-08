package support

import org.openqa.selenium.WebDriver
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import server.RunningApplication
import server.RunningApplicationFactory

@Listeners([ReportingListener])
abstract class FunctionalTestSupport {

    WebDriver driver
    RunningApplication application

    @BeforeMethod
    void setupDriver() {
        driver = DriverFactory.get()
        application = RunningApplicationFactory.get()
    }

    void goToAppRoot() {
        println "going to $application.baseUrl"
        driver.get(application.baseUrl)
    }

}
