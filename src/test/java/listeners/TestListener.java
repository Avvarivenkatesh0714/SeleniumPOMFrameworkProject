package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.WebDriver;

import reports.ExtentManager;
import utilities.DriverFactory;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static final ExtentReports extentReports =
            ExtentManager.getExtentReports();

    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        System.out.println("========== Test Execution Started ==========");

    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extentReports.createTest(
                result.getMethod().getMethodName());

        extentTest.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().pass("Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = getOrCreateTest(result);
        test.fail(result.getThrowable());

        try {

            WebDriver currentDriver = DriverFactory.getDriver();

            if (currentDriver == null) {
                test.warning("Driver instance is not available to capture screenshot.");
                return;
            }

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            currentDriver,
                            result.getMethod().getMethodName());

            test
                    .addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {

            test.warning(
                    "Unable to capture screenshot : "
                            + e.getMessage());

        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        getOrCreateTest(result).skip("Test Skipped");

    }

    private ExtentTest getOrCreateTest(ITestResult result) {
        ExtentTest test = extentTest.get();
        if (test == null) {
            test = extentReports.createTest(result.getMethod().getMethodName());
            extentTest.set(test);
        }
        return test;
    }

    @Override
    public void onFinish(ITestContext context) {

        extentReports.flush();

        System.out.println("========== Test Execution Completed ==========");

    }

}
