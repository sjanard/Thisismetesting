package qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.github.bonigarcia.wdm.WebDriverManager;
import qa.ExtentReports.ExtentManager;
import qa.ExtentReports.ExtentReport;
import qa.TestUtils.TestUtils;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public static void initialization()
	{
		String configFilePath = System.getProperty("user.dir")+"/src/main/java/qa/TestConfig/config.properties";
		FileInputStream fis;
		try {
			fis = new FileInputStream(configFilePath);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String browser=prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			//System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments("enable-automation");
			 * options.addArguments("--no-sandbox");
			 * options.addArguments("--disable-infobars");
			 * options.addArguments("--dns-prefetch-disable");
			 * options.addArguments("--disable-dev-shm-usage");
			 * options.addArguments("--disable-browser-side-navigation");
			 * options.addArguments("--disable-gpu");
			 */
			options.setHeadless(true);
			driver= new ChromeDriver(options);
						
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.get(prop.getProperty("url"));
		
	}
	
	  @BeforeSuite
		public void TestSetUp() {
			ExtentReport.initReports();
		}
	  
		@AfterMethod
		public void afterMethod(ITestResult result) {
			if(result.getStatus()==ITestResult.FAILURE)
		    {
				ExtentManager.getExtentTest().fail(result.getThrowable());
				ExtentManager.getExtentTest().fail("Failed",MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtils.getBase64Image()).build());
		    }
			else if(result.getStatus()==ITestResult.SUCCESS)
			{
				ExtentManager.getExtentTest().pass("Test Passed");
			}
			driver.quit();
		}
		

		@AfterSuite
		public void TestTearDown() {
			ExtentReport.flushReports();
		}
		

}
