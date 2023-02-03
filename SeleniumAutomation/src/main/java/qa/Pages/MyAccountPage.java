package qa.Pages;

import org.openqa.selenium.By;

import qa.Base.TestBase;
import qa.ExtentReports.ExtentManager;

public class MyAccountPage extends TestBase {
	
	private By welcomeMessage = By.xpath("//div[@id='center_column']//p");
	private By searchField = By.id("search_query_top");
	private By searchButton = By.name("submit_search");
	
	public String getWelcomeMessage()
	{
		return driver.findElement(welcomeMessage).getText();
	}
	
	public SearchResultsPage searchProduct(String searchItem) {
	
		driver.findElement(searchField).sendKeys(searchItem);
		ExtentManager.getExtentTest().info("Search Item Entered : "+searchItem);
		driver.findElement(searchButton).click();
		ExtentManager.getExtentTest().info("Search Button Clicked");
		return new SearchResultsPage();
	
	}

}
