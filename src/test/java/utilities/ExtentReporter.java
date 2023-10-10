package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	static ExtentReports extent;

	public static ExtentReports getReporterObject() {

		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Reports\\Report.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Test Results");
		spark.config().setReportName("APEX Automation Exercise Report");
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Tester", "Hugo Chavez");

		return extent;
	}
}
