package utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.liverpool.testNGbase.TestNGBase;

public class Listeners extends TestNGBase implements ITestListener {

	Utils utils = new Utils();
	ExtentReports extent = ExtentReporter.getReporterObject();
	ExtentTest test;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, result.getMethod().getMethodName() + " Passed");

	}

	public void onTestFailure(ITestResult result) {

		// Screenshot code
		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			extentTest.get().addScreenCaptureFromPath(utils.getScreenshotPath(driver, result.getMethod().getMethodName()),
					result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {

		extent.flush();

	}

}

//test = extent.createTest(new Object() {
//}.getClass().getEnclosingMethod().getName());
//test.log(Status.PASS, "Initializing Scenario 1 solution 1");