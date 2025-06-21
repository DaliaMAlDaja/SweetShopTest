package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	static ExtentReports extent;

	public static ExtentReports getReportInstance() {
		if (extent == null) {
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
			htmlReporter.config().setDocumentTitle("Sweet Shop Test Report");
			htmlReporter.config().setReportName("Automation Execution Report");

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}
}
