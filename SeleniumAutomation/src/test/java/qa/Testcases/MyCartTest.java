package qa.Testcases;

import org.testng.annotations.Test;

import qa.Base.TestBase;
import qa.ExtentReports.ExtentManager;
import qa.ExtentReports.ExtentReport;
import qa.Pages.LoginPage;
import qa.Pages.MyAccountPage;
import qa.Pages.MyCartPage;
import qa.Pages.SearchResultsPage;
import qa.TestUtils.TestUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

public class MyCartTest extends TestBase{
	
	LoginPage lp;
	MyAccountPage myAcc;
	SearchResultsPage sp;
	MyCartPage cart;
	String sheetName = "searchItems";
	
  @Test(dataProvider="searchData")
  public void validateCheckOut(String searchItem) {
	  ExtentReport.createTest("Validate If User is Able to check Out product succesfully");
	  myAcc = lp.logIn(prop.getProperty("valid_Username"), prop.getProperty("valid_Password"));
	  sp = myAcc.searchProduct(searchItem);
	  sp.addProductsToCart();
	  cart=sp.checkOutProducts();
	  cart.checkOutAndComplete();
	  Assert.assertTrue(cart.VerifyOrderSuccessMessage(),"Order Success Message is not displayed");
	  
  }
  
	 @DataProvider
		public String[] searchData(){
			return new String[]{"Dress","summer","Blouse"};
		}
	
  
  @BeforeMethod
  public void beforeMethod() {
	  	initialization();
		lp = new LoginPage();
  }

}
