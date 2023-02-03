package qa.Pages;

import org.openqa.selenium.By;

import qa.Base.TestBase;
import qa.ExtentReports.ExtentManager;

public class ProductDetailsPage extends TestBase{
	
	private By qnty = By.id("quantity_wanted");
	private By addtoCart = By.name("Submit");
	private By ItemsInCart = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/h2/span[1]");
		
	
	public void updateQuantityAndAddtoCart()
	{
		driver.findElement(qnty).clear();
		ExtentManager.getExtentTest().info("Quantity Field Cleared");
		driver.findElement(qnty).sendKeys("3");
		ExtentManager.getExtentTest().info("Updated Quantity");
		driver.findElement(addtoCart).click();
		ExtentManager.getExtentTest().info("Add to Cart Clicked");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public boolean checkCartQuantity() {
		String msg=driver.findElement(ItemsInCart).getText();
		System.out.print(msg);
		String quantity = msg.split(" ")[2];
		if(quantity.equalsIgnoreCase("3"))
			return true;
		else
			return false;
	}

}
