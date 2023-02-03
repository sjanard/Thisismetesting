package qa.StepDefenitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import qa.ApiResources.ApiResources;
import qa.Resources.TestDataBuild;
import qa.Resources.Utils;

public class StepDefention extends Utils {

	private RequestSpecification reqst;
	private Response res;
	private TestDataBuild td = new TestDataBuild();
	private ApiResources api = new ApiResources();

	@Given("Setup {string} payload with {string} {string} {string} {string} details")
	public void setup_payload_with_details(String reqMethod,String id, String fname, String lname, String pswd) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		reqst = getValidReqSpec();
		if(reqMethod.equalsIgnoreCase("Post"))
		reqst.body(td.setUpUserPayload(Integer.valueOf(id),fname,lname,pswd));
		else if(reqMethod.equalsIgnoreCase("Put"))
		reqst.body(td.setUpUpdateUserPayload(Integer.valueOf(id),fname,lname,pswd));
	}
	

	@When("New user Creation Request is made")
	public void createNewUser() throws IOException {
		res = reqst.when().post(api.getAddUserAPI());
		// Write code here that turns the phrase above into concrete actions
	}
	
	@When("Update user request is made for {string}")
	public void updateUser(String userName) throws IOException {
		res = reqst.pathParam("username", userName).when().put(api.getUserActionAPI());
		// Write code here that turns the phrase above into concrete actions
	}
	
	@When("Delete user request is made for {string}")
	public void deleteUser(String userName) throws IOException {
		reqst = getValidReqSpec();
		res = reqst.pathParam("username", userName).when().delete(api.getUserActionAPI());
		// Write code here that turns the phrase above into concrete actions
	}

	@Then("Validate that the status code is {string}")
	public void validate_that_the_status_code_is(String statCode) {
		// Write code here that turns the phrase above into concrete actions
		int statuscode = res.getStatusCode();
		res.then().log().all();
		Assert.assertTrue(statuscode == Integer.valueOf(statCode));
	}


	@Then("Validate that Response is not null")
	public void validate_that_response_is_not_null() {
		JsonPath jp = res.jsonPath();
		res.then().log().all();
		String code = String.valueOf(jp.getInt("code"));
		String message = jp.getString("message");
		String type = jp.getString("type");
		Assert.assertTrue(!code.isEmpty());
		Assert.assertTrue(!message.isEmpty());
		Assert.assertTrue(!type.isEmpty());
	}

	@Then("Validate that the details are updated for {string}")
	public void validate_that_the_details_are_updated(String userName) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		try {
			Thread.sleep(10000);
			res = reqst.pathParam("username", "TedMosby").when().get(api.getUserActionAPI());
			res.then().log().all();
			JsonPath jp = res.jsonPath();
			String fname = jp.getString("firstName");
			String lname = jp.getString("lastName");
			Assert.assertEquals("TedUpdated", fname);
			Assert.assertEquals("MosbyUpdated", lname);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Then("Validate that the response time is optimum")
	public void validate_that_the_response_time_is_optimum() {
		// Write code here that turns the phrase above into concrete actions
		long responseTime = res.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println(responseTime+"-----------------------");
		Assert.assertTrue(responseTime <= 1500);
	}

	@Then("Validate that the response has the user name {string}")
	public void validate_that_the_response_has_username(String userName) {
		res.then().log().all();
		JsonPath jp = res.jsonPath();
		String name = jp.getString("message");
		Assert.assertEquals(userName, name);
	}
}
