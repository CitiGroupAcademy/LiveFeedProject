package project.dataObjects;

public class User 
{
	private int userID;
	private String email;
	private String password;
	
	public void user(String email, String password)
	{
		this.email = email;
		this.password = password;
	}
	public int getUserID() 
	{
		return userID;
	}
	public void setUserID(int userID) 
	{
		this.userID = userID;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
}
