package qa.Testcases;

import org.testng.annotations.Test;
import qa.Base.TestBase;
import qa.ExtentReports.ExtentReport;
import qa.Pages.LoginPage;
import qa.Pages.MyAccountPage;
import qa.Pages.MyCartPage;
import qa.Pages.ProductDetailsPage;
import qa.Pages.SearchResultsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class SearchResultsTest extends TestBase {

	LoginPage lp;
	MyAccountPage myAcc;
	SearchResultsPage sp;
	ProductDetailsPage pd;
	MyCartPage myCart;
	

	@Test(dataProvider="searchData")
	public void ValidateSearchFunctionality(String strSearchItem) {
		ExtentReport.createTest("Validate If User is able to search for a Product");
		myAcc = lp.logIn(prop.getProperty("valid_Username"), prop.getProperty("valid_Password"));
		sp = myAcc.searchProduct(strSearchItem);
		Assert.assertTrue(sp.verifyResultsFound(),"Results Found message is not displayed");
		Assert.assertTrue(sp.verifyResultsFoundByName(strSearchItem),"Results show items that are not related to search keyword");
	}

	@Test
	public void ValidateNoResultsSearchFunctionality() {
		ExtentReport.createTest("Validate No Search Results Banner");
		myAcc = lp.logIn(prop.getProperty("valid_Username"), prop.getProperty("valid_Password"));
		sp = myAcc.searchProduct("!@#$%^&");
		Assert.assertTrue(sp.noResultsBanner(),"No Results Message is not displayed");
	}

	@Test
	public void ValidateSortByPriceDesc() {
		ExtentReport.createTest("Validate if the Search Results Sorted in Descending Order");
		myAcc = lp.logIn(prop.getProperty("valid_Username"), prop.getProperty("valid_Password"));
		sp = myAcc.searchProduct("Printed");
		sp.sortByPriceDesc();
		Assert.assertTrue(sp.validateSortPriceDesc(),"Items are incorrectly sorted");
	}

	@Test
	public void validateAddProductsToCart() {
		ExtentReport.createTest("Validate if the  Products can be added to cart from search results page");
		myAcc = lp.logIn(prop.getProperty("valid_Username"), prop.getProperty("valid_Password"));
		sp = myAcc.searchProduct("Printed");
		sp.addProductsToCart();
		Assert.assertTrue(sp.checkCartQuantity(),"Quantity in cart is not updated correctly");

	}
	
	@Test
	public void validateAddProductsToCartFromProdDetailsPage() {
		ExtentReport.createTest("Validate if the  Products can be added to cart from product details page");
		myAcc = lp.logIn(prop.getProperty("valid_Username"), prop.getProperty("valid_Password"));
		sp = myAcc.searchProduct("Summer");
		pd=sp.goToProductDetails();
		pd.updateQuantityAndAddtoCart();
		Assert.assertTrue(pd.checkCartQuantity(),"Quantity in cart is not updated correctly");

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
