package control;

import model.UserHomeDB;

public class LoginControl 
{
	UserHomeDB usuario =  new UserHomeDB();
	
	public boolean validateUser(String pUsername, String pPassword)
	{
		if (usuario.validate(pUsername, pPassword) != null)
			return true;
		else
			return false;
	}
}
