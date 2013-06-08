package tests

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.testng.annotations.Test
import support.DriverFactory
import support.FunctionalTestSupport

class FrontPageTest extends FunctionalTestSupport {

    @Test(dataProvider = "drivers", dataProviderClass = DriverFactory.class)
    void testFrontPage(WebDriver driver) {
        driver.get(application.baseUrl)
        assert driver.findElement(By.cssSelector("h1")).text == "Welcome to the Gradle Summit!"
    }

}
