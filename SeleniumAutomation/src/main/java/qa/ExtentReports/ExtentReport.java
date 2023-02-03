package qa.ExtentReports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	private ExtentReport() {
	}

	private static ExtentReports extent;

	public static void initReports() {

		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Test Automation Report.html");
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Test Report");

	}
	public static void flushReports(){
		if(Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
		/*
		 * try { Desktop.getDesktop().browse(new
		 * File("Test Automation Report.html").toURI()); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
	}
	
	public static void createTest(String testcasename) {
		ExtentManager.setExtentTest(extent.createTest(testcasename));
	}
}