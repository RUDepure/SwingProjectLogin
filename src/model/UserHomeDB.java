package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserHomeDB implements UserHome
{
	public UserBO validate(String pUsername, String pPassword)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UserBO user = null;
	    
		try 
		{

            connection = DriverManager.getConnection("jdbc:oracle:thin:@10.166.51.12:1521:rmsdev01", "rms", "rms");
    		if (connection != null) 
    		{
    			System.out.println("You made it, take control your database now!");

              try 
              {
            	  System.out.println("Creating statement...");
            	  String sql = "SELECT usuario, contrasena FROM cuentas_java WHERE usuario = ?";
            	  preparedStatement = connection.prepareStatement(sql);
            	  preparedStatement.setString(1, pUsername);
                  ResultSet rs = preparedStatement.executeQuery();
                  
                  while(rs.next())
                  {
                	  String username = rs.getString("usuario");
                	  String password = rs.getString("contrasena");
                	  
                	  if(password.equals(pPassword))
                	  {
                    	  user = new UserBO();
                    	  user.setUsername(username);
                    	  user.setPassword(password);
                    	  return user;
                	  }
                  }
              } 
              catch (SQLException ex) 
              {
                  System.out.println("No se obtuvo statement");
                  ex.printStackTrace();
              }
    		} 
    		else 
    		{
    			System.out.println("Failed to make connection!");
    		}
        } 
		catch (SQLException e)
		{
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return null;

	}
}
