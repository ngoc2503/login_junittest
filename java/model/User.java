package model;

public class User {
	private String userId;
	private String userPassword;
	private int countLoginFail;
	private boolean state;
	
	public User() {
		
	}
	public User(String userId, String userPassword) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.countLoginFail = 0;
		this.state = false;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserPassword(String password) {
		userPassword = password;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setCountLoginFail(int countLoginFail) {
		this.countLoginFail = countLoginFail;
	}
	
	public int getCountLoginFail() {
		return countLoginFail;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public boolean getState() {
		return state;
	}
	
	public String toString() {
		return getUserId() + " " + getUserPassword() +" "
				+ getCountLoginFail() + " "
				+ getState() + "\n";
	}
}
