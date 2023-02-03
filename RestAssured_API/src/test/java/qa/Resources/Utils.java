package qa.Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	private RequestSpecification req = RestAssured.given();
	public RequestSpecification getValidReqSpec() throws IOException
	{
		//PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		req.baseUri(getValueFromConfig("baseURL")).header("Content-Type", "application/json").header("Accept", "application/json").log().all();
		return req;
	}
			
	public static String getValueFromConfig(String key) throws IOException
	{
		Properties prop =new Properties();
		String configPath = System.getProperty("user.dir")+"\\src\\test\\java\\qa\\Resources\\Config.properties";
		FileInputStream fis =new FileInputStream(configPath);
		prop.load(fis);
		return prop.getProperty(key);
	
		
		
	}

}
