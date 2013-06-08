package tests

import org.openqa.selenium.By
import org.testng.annotations.Test
import support.FunctionalTestSupport

class FrontPageTest extends FunctionalTestSupport {

    @Test
    void testFrontPage() {
        goToAppRoot()
        assert driver.findElement(By.cssSelector("h1")).text == "Welcome to the Summit!"
    }


}
