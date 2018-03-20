package model;

public class UserBO 
{
	private String username;
	private String password;
	
	public UserBO()
	{
	}
	public UserBO(String pUsername, String pPassword)
	{
		this.username = pUsername;
		this.password = pPassword;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
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
