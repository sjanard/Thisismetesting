package qa.ApiResources;

public class ApiResources {
	
	private String addUserAPI = "/user";
	private String userActionAPI = "/user/{username}";

	public String getUserActionAPI() {
		return userActionAPI;
	}

	public String getAddUserAPI() {
		return addUserAPI;
	}
	

}
