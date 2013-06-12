package tests

import support.FunctionalTestSupport

class FrontPageTest extends FunctionalTestSupport {

    void testFrontPage() {
        when:
        to FrontPage

        then:
        heading == "Welcome to the Gradle Summit!"
    }

}
