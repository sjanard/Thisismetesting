package qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import qa.Base.TestBase;
import qa.ExtentReports.ExtentManager;

public class MyCartPage extends TestBase {
	
	private By checkOutButton = By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span");
	private By proccedCheckOut = By.xpath("//*[@id=\"center_column\"]/form/p/button/span");
	private By consentCheck = By.xpath("//input[@id='cgv']");
	private By proceedToPayment = By.xpath("//*[@id=\"form\"]/p/button/span");
	private By bankWire = By.xpath("//a[@title='Pay by bank wire']");
	private By confirmOrder = By.xpath("//*[@id=\"cart_navigation\"]/button/span");
	private By OrderSuccess = By.xpath("//*[@id=\"center_column\"]/div/p/strong");
	
	
	public void checkOutAndComplete()
	{
		driver.findElement(checkOutButton).click();
		ExtentManager.getExtentTest().info("Click Checkout");
		driver.findElement(proccedCheckOut).click();
		driver.findElement(consentCheck).click();
		driver.findElement(proceedToPayment).click();
		ExtentManager.getExtentTest().info("Click Checkout");
		driver.findElement(bankWire).click();
		ExtentManager.getExtentTest().info("Click Bankwire payment");
		driver.findElement(confirmOrder).click();		
		ExtentManager.getExtentTest().info("Click Confirm Order");
	}
	
	public boolean VerifyOrderSuccessMessage()
	{
		String msg=driver.findElement(OrderSuccess).getText();
		if(msg.contains("Your order on My Store is complete."))
			return true;
		else
			return false;
	}
	

}
