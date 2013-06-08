package tests

import org.openqa.selenium.By
import org.testng.annotations.Test
import support.FunctionalTestSupport

class FrontPageTest5 extends FunctionalTestSupport {

    @Test
    void testFrontPage() {
        goToAppRoot()
        assert driver.findElement(By.cssSelector("h1")).text == "Welcome to the Gradle Summit!"
        sleep 1000
    }


}
