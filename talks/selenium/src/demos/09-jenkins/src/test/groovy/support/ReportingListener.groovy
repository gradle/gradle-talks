package support

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.Augmenter
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult

class ReportingListener implements ITestListener {

    void onTestFailure(ITestResult result) {
        if (result.success) {
            return
        }

        if (!result.instance instanceof FunctionalTestSupport) {
            return
        }

        FunctionalTestSupport testInstance = result.instance
        WebDriver driver = testInstance.driver

        TakesScreenshot takesScreenshot = toTakesScreenshot(driver)
        if (takesScreenshot) {
            def pngFile = reportFile(result, "png")
            def bytes = takesScreenshot.getScreenshotAs(OutputType.BYTES)
            pngFile.bytes = bytes
            log("screenshot", pngFile)
        }

        log("html dump", reportFile(result, "txt")).text = driver.pageSource
    }

    private static File reportFile(ITestResult result, extension) {
        mkdirsParent(new File(getReportsDir(), reportBaseName(result) + "." + extension))
    }

    private static File mkdirsParent(File file) {
        File parent = file.absoluteFile.parentFile
        assert parent.mkdirs() || parent.exists()
        file
    }

    private static String reportBaseName(ITestResult result) {
        "${result.testClass.name}-${result.method.methodName}-${result.method.currentInvocationCount}"
    }

    private static TakesScreenshot toTakesScreenshot(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            driver as TakesScreenshot
        } else {
            def augmented = new Augmenter().augment(driver)
            if (augmented instanceof TakesScreenshot) {
                augmented
            } else {
                null
            }
        }
    }

    private static File getReportsDir() {
        new File(System.getProperty("reports", "reports"))
    }

    private static File log(String name, File file) {
        System.err.println "[[ATTACHMENT|$file.absolutePath]]"
        file
    }

    @Override
    void onTestStart(ITestResult result) {
    }

    @Override
    void onTestSuccess(ITestResult result) {
    }

    @Override
    void onTestSkipped(ITestResult result) {
    }

    @Override
    void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    void onStart(ITestContext context) {
    }

    @Override
    void onFinish(ITestContext context) {
    }
}
