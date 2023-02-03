package qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import qa.Base.TestBase;
import qa.ExtentReports.ExtentManager;
import qa.ExtentReports.ExtentReport;

public class LoginPage extends TestBase {
	
	private By signInLink = By.xpath("//a[contains(text(),'Sign in')]");
	private By emailInput = By.id("email");
	private By passWordInput = By.id("passwd");
	private By signInButton = By.id("SubmitLogin");
	private By errorMessage = By.cssSelector("#center_column>.alert.alert-danger");
	private By signOutLink = By.className("logout");
	
	public MyAccountPage logIn(String userName, String pwd)
	{
		driver.findElement(signInLink).click();
		ExtentManager.getExtentTest().info("Sign In Link Clicked");
		driver.findElement(emailInput).sendKeys(userName);
		ExtentManager.getExtentTest().info("User name Entered : "+userName);
		driver.findElement(passWordInput).sendKeys(pwd);
		ExtentManager.getExtentTest().info("Pasword Entered");
		driver.findElement(signInButton).click();
		ExtentManager.getExtentTest().info("Sign In Button Cicked");
		return new MyAccountPage();
	}
	
		
	public String getErrorMessage()
	{
		return driver.findElement(errorMessage).getText();
	}
	
	public void logout()
	{
		driver.findElement(signOutLink).click();
		ExtentManager.getExtentTest().info("Sign Out Link Clicked");
	}
	
	public boolean checkIfSignInIsPresent()
	{
		if(driver.findElement(signInLink).isDisplayed())
			return true;
		else
			return false;
	}

	public void clickSignInWithoutMail() {
		driver.findElement(signInLink).click();
		ExtentManager.getExtentTest().pass("Sign In Link Clicked");
		driver.findElement(signInButton).click();
		ExtentManager.getExtentTest().pass("Sign In Buton Clicked");
		
	}

}
