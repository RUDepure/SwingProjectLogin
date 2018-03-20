package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserHomeDB implements UserHome 
{
	private static SessionFactory factory;

	public UserBO validate(String pUsername, String pPassword) 
	{
		UserBO user = null;

		try 
		{
			factory = new Configuration().configure().buildSessionFactory();
			System.out.println("You made it, take control your database now!");
		} 
		catch (Throwable ex) 
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
				
		Session session = factory.openSession();
	    Transaction tx = null;

		try 
		{
			tx = session.beginTransaction();
			System.out.println("Empieza Query...");
			Query query = session.createQuery("FROM UserBO WHERE usuario = :user");
			query.setParameter("user", pUsername);
			System.out.println("Entra Query...");
	        List usuarios = query.list(); 
	        for (Iterator iterator = usuarios.iterator(); iterator.hasNext();)
	        {
	           System.out.println("Enters for...");
	           user = (UserBO) iterator.next();
	           String username = user.getUsername();
	           String password = user.getPassword();
	           System.out.print("Usuario: " + username); 
	           System.out.print("Password: " + password); 
	           if (password.equals(pPassword)) 
	           {
	        	   user = new UserBO();
	        	   user.setUsername(username);
	        	   user.setPassword(password);
	        	   return user;
				}
	        }
	        tx.commit();
			
//			System.out.println("Creating statement...");
//			String sql = "SELECT usuario, contrasena FROM usuarios WHERE usuario = ?";
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, pUsername);
//			System.out.println("Sending statement...");
//			ResultSet rs = preparedStatement.executeQuery();
//			System.out.println("Recieved statement...");

//			while (rs.next()) 
//			{
//				System.out.println("Enters while...");
//				String username = rs.getString("usuario");
//				String password = rs.getString("contrasena");
//				System.out.println("Username: " + username + "Password: "
//						+ password);
//
//				if (password.equals(pPassword)) 
//				{
//					user = new UserBO();
//					user.setUsername(username);
//					user.setPassword(password);
//					return user;
//				}
//			}
		} 
		catch (HibernateException e) 
	    {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    } 
	    finally 
	    {
	       session.close(); 
	    }

		return null;

	}
}
