package qa.Resources;

import qa.Models.UserModel;

public class TestDataBuild {
	
	public UserModel setUpUserPayload(int id, String fname, String lname, String pswd)
	{
		UserModel user = new UserModel();
		user.setId(id);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setPassword(pswd);
		user.setEmailId(fname+lname+"@Abc.com");
		user.setUsername(fname+lname);
		user.setPhone("1122334455");
		user.setUserStatus(1);
		
		return user;
		
	}

	public UserModel setUpUpdateUserPayload(int id, String fname, String lname, String pswd) {
		// TODO Auto-generated method stub
		UserModel user = new UserModel();
		user.setId(id);
		user.setFirstName(fname+"Updated");
		user.setLastName(lname+"Updated");
		user.setPassword(pswd);
		user.setEmailId(fname+lname);
		user.setUsername(fname+lname);
		user.setPhone("1122334455");
		user.setUserStatus(1);
		
		return user;
	}

}
