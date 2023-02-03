package qa.Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.Ordering;

import qa.Base.TestBase;
import qa.ExtentReports.ExtentManager;
import qa.ExtentReports.ExtentReport;

public class SearchResultsPage extends TestBase {
	
	private By resultsFound = By.xpath("//*[@id=\"center_column\"]/h1/span[2]");
	private By noResultsBanner = By.xpath("//*[@id=\"center_column\"]/p");
	private By sortDropDown = By.id("selectProductSort");
	private By listOfSearchResults = By.xpath("//*[@id=\"center_column\"]/ul/li");
	private By productImage1 = By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img");
	private By addoCart1 = By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]/span");
	private By continueShopping = By.xpath("//span[@title='Continue shopping']//span[1]");
	private By cartValue = By.xpath("//span[@class='ajax_cart_quantity unvisible']");
	private By viewMyCart = By.xpath("//a[@title='View my shopping cart']");
	private By checkOut = By.xpath("//*[@id=\"button_order_cart\"]/span");
	private By more1 = By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[2]/span");
	private By productName=By.xpath("//ul[@id='product_list']//div[@class='right-block']//a[@class='product-name']");
	
	private ArrayList<Float> prices= new ArrayList<Float>();
	private List<WebElement> products;
	private int searchItemsSize;
	
	
	public boolean verifyResultsFound()
	{
		String resultFound = driver.findElement(resultsFound).getText();
		if(resultFound.contains("results have been found")||resultFound.contains("result has been found"))
		
			return true;
		else
			return false;
	}
	
	public boolean verifyResultsFoundByName(String searchItem)
	{	
		boolean flag=false;
		List<WebElement> prodName=driver.findElements(productName);
		for(WebElement el : prodName)
		{
			if(el.getText().toLowerCase().contains(searchItem.toLowerCase()))
				flag=true;
			else
			{
				flag=false;
				break;
			}
		}
		return flag;
	}
	
	public boolean noResultsBanner()
	{
		String resultFound = driver.findElement(noResultsBanner).getText();
		if(resultFound.contains("No results were found for your search"))
			return true;
		else
			return false;
	}
	
	public void sortByPriceDesc()
	{
		products = driver.findElements(listOfSearchResults);
		searchItemsSize=products.size();
		Select sel = new Select(driver.findElement(sortDropDown));
		sel.selectByValue("price:desc");
		ExtentManager.getExtentTest().info("Sorted By Price in Descending Order");
	}
	
	public boolean validateSortPriceDesc()
	{
		WebElement element = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li["+1+"]/div/div[2]/div[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		for(int i=1;i<=searchItemsSize;i++)
		{
			WebElement el = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li["+i+"]/div/div[2]/div[1]"));
			String[] temp = el.getText().split(" ");
			String pricesTemp = temp[0].replace("$","");
			prices.add(Float.valueOf(pricesTemp));
		}
		System.out.println(prices);
		boolean flag=Ordering.natural().isOrdered(prices);
		return flag;
	}
	
	public void addProductsToCart()
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(productImage1)).build().perform();
		act.moveToElement(driver.findElement(addoCart1)).click().build().perform();
		ExtentManager.getExtentTest().info("Products Added To Cart");
		act.pause(Duration.ofSeconds(10));
		act.moveToElement(driver.findElement(continueShopping)).click().build().perform();
		ExtentManager.getExtentTest().info("Click on Continue shoping");
				
	}
	
	public ProductDetailsPage goToProductDetails()
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(productImage1)).build().perform();
		act.moveToElement(driver.findElement(more1)).click().build().perform();
		ExtentManager.getExtentTest().info("Click on More");
		act.pause(Duration.ofSeconds(10));
		return new ProductDetailsPage();
	}
	
	public boolean checkCartQuantity()
	{
		String qty = driver.findElement(cartValue).getText();
		if(qty.equalsIgnoreCase("1"))
			return true;
		else
			return false;
	}
	
	public MyCartPage checkOutProducts()
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(viewMyCart)).build().perform();
		driver.findElement(checkOut).click();
		ExtentManager.getExtentTest().info("Clicked on CheckOut");
		return new MyCartPage();
	}
	
}
