package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.baseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext context) {
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss"); // To set
		 * date format and time Date dt = new Date(); // To get current date String
		 * CurrentDate = df.format(dt); // after getting current date need to combine
		 * with format
		 */

		String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()); // In single we can difine
																						// the date and time stamp

		repName = "Test_Report-" + timeStamp + ".html"; // This will set dynamic report name

		sparkreporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName); // Specific

		sparkreporter.config().setDocumentTitle("opencart Automation Report"); // Title of the report
		sparkreporter.config().setReportName("opencart Functional Testing"); // name of the report
		sparkreporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);

		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("subModule", "Customer");
		extent.setSystemInfo("UserName", System.getProperty("user.name")); // system user name not properties data
		extent.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os"); // To set dynamic OS name
		extent.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser"); // To set dynamic Browser name
		extent.setSystemInfo("Browser", browser);

		List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
		if (!groups.isEmpty()) {
			extent.setSystemInfo("Groups", groups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display groups in reports
		test.log(Status.PASS, result.getName() + " got sucessfully executed ");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, "Test got failed because " + result.getThrowable().getMessage());

		try {
			String imgPath = new baseClass().captureScreen(result.getName()); // new baseClass it is a base class object
			test.addScreenCaptureFromPath(imgPath);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
