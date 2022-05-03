package dbadapter;

import datatypes.UserData;


public class User {
	
	private UserData userData;
	
	
	public User(UserData userData) {
		this.userData = userData;
	}
	

	
	public UserData getUserData() {
		return userData;
	}

	
}
