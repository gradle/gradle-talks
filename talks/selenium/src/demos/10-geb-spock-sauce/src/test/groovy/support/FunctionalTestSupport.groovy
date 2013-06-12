package support

import geb.spock.GebReportingSpec
import server.RunningApplication
import server.RunningApplicationFactory

abstract class FunctionalTestSupport extends GebReportingSpec {

    RunningApplication application = RunningApplicationFactory.get()

    void setup() {
        config.baseUrl = application.baseUrl
    }

    void cleanup() {
        sleep 1000
    }
}
