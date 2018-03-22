package model;

import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class UserHomeFile implements UserHome
{
//	List<String> users = new ArrayList<String>();
//    List<String> passwds = new ArrayList<String>();
    String[] ar;
    UserBO user = null;
    
	public UserBO validate(String pUsername, String pPassword) 
	{
		try 
		{
            BufferedReader in = new BufferedReader(new FileReader("accounts.txt"));
            String str;
            str = in.readLine();
            ar=str.split(",");
            if (pUsername.equals(ar[0]) && pPassword.equals(ar[1]))
            {
            	user = new UserBO();
          	  	user.setUsername(ar[0]);
          	  	user.setPassword(ar[1]);
          	  	return user;
            }
            
            while ((str = in.readLine()) != null) 
            {
                ar=str.split(",");
                if (pUsername.equals(ar[0]) && pPassword.equals(ar[1]))
                {
                	user = new UserBO();
              	  	user.setUsername(ar[0]);
              	  	user.setPassword(ar[1]);
              	  	return user;
                }
            }
            in.close();
            
        } 
		catch (IOException e) 
		{
            System.out.println("File Read Error");
        }
		
		return null;
	}
        
    public UserBO changePassword(String pOldPassword, String pNewPassword)
    {
        
        return null;
    }

}
