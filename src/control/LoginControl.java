package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import model.UserBO;
import model.UserHome;
import model.UserHomeDB;
import model.UserHomeFile;

public class LoginControl 
{
	UserHomeDB usuarioDB =  new UserHomeDB();
	UserHomeFile usuarioFile = new UserHomeFile();
	
	public boolean validateUser(String pUsername, String pPassword)
	{
		Properties prop = new Properties();
		InputStream input = null;
		try 
		{

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);
			
			String validateClass = prop.getProperty("validate.class");
			
			try 
			{
				
				Class userHomeClass = Class.forName(validateClass);
				UserHome userHome = (UserHome)userHomeClass.newInstance();
				UserBO userBO = userHome.validate(pUsername, pPassword);
				
				if (userBO != null)
					return true;
				else
					return false;
			} 
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (InstantiationException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// get the property value and print it out
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));

		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			if (input != null) 
			{
				try 
				{
					input.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		if (usuarioDB.validate(pUsername, pPassword) != null)
			return true;
		else
			return false;
	}
	
	public boolean validateUserDB(String pUsername, String pPassword)
	{
		if (usuarioDB.validate(pUsername, pPassword) != null)
			return true;
		else
			return false;
	}
	
	public boolean validateUserFile(String pUsername, String pPassword)
	{
		if (usuarioFile.validate(pUsername, pPassword) != null)
			return true;
		else
			return false;
	}
}
