package qa.TestUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import qa.Base.TestBase;

public class TestUtils extends TestBase {
	
	
	public static String getBase64Image() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
}
