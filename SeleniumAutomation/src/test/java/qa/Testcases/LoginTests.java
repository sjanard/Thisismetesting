package qa.Testcases;

import org.testng.annotations.Test;

import qa.Base.TestBase;
import qa.ExtentReports.ExtentManager;
import qa.ExtentReports.ExtentReport;
import qa.Pages.LoginPage;
import qa.Pages.MyAccountPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

public class LoginTests extends TestBase {
	LoginPage lp;
	MyAccountPage myAcc;

	@BeforeMethod
	public void beforeMethod() {
		initialization();
		lp = new LoginPage();
	}

	@Test
	public void validLoginTest() {

		ExtentReport.createTest("Validate User Log in With Valid Creadentials");
		myAcc = lp.logIn(prop.getProperty("valid_Username"), prop.getProperty("valid_Password"));
		String welcomeMsg = myAcc.getWelcomeMessage();
		Assert.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.",
				welcomeMsg);
		lp.logout();

	}

	@Test
	public void inValidLoginTest() {
		ExtentReport.createTest("Validate User Log in With Invalid Creadentials");
		boolean pass = false;
		lp.logIn(prop.getProperty("invalid_Username"), prop.getProperty("invalid_Password"));
		String errMsg = lp.getErrorMessage();
		if (errMsg.contains("Authentication failed"))
			pass = true;
		Assert.assertTrue(pass,"Authentication Failed message is not displayed");
	}

	@Test
	public void validateLogout() {
		ExtentReport.createTest("Validate User Log Out");
		lp.logIn(prop.getProperty("valid_Username"), prop.getProperty("valid_Password"));
		lp.logout();
		Assert.assertTrue(lp.checkIfSignInIsPresent(),"User log out is unsuccesfull");
	}

	@Test
	public void validateMandatoryLoginFields() {
		ExtentReport.createTest("Validate Mandatory Fields required for Login");
		boolean pass = false;
		lp.clickSignInWithoutMail();
		String errMsg = lp.getErrorMessage();
		if (errMsg.contains("An email address required."))
			pass = true;
		Assert.assertTrue(pass,"Mandatory Fields validation message is not displayed");

	}
}
