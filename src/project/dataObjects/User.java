package project.dataObjects;

public class User 
{
	private int userID;
	private String  email;
	private String password;
	
	
	public int getID() {
		return userID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
