package qa.StepDefenitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeleteUser or @EditUser")
	public void addDefaultUser() throws IOException
	{
		StepDefention sd = new StepDefention();
		sd.setup_payload_with_details("Post", "101", "Ted", "Mosby", "Robin123");
		sd.createNewUser();
	}

}
