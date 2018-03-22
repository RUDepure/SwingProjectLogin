package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import view.LoginView;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserHomeDB implements UserHome 
{
    UserBO user = null;
    
    public UserBO validate(String pUsername, String pPassword)
    {
        Transaction tx = null;
        Session session = LoginView.factory.openSession();

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
                    
                    return user;
                }
            }
            tx.commit();

        }
        catch (HibernateException e)
        {
            if (tx != null) 
            {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
            System.out.println("Cierra session");
        }
        return null;

    }
//    public UserBO changePassword(String pOldPassword, String pNewPassword)
//    {
//        Session session = LoginView.factory.openSession();
//        Transaction tx = null;
//      
//        try 
//        {
//            tx = session.beginTransaction();
//            UserBO employee = (UserBO)session.get(UserBO.class, EmployeeID); 
//            employee.setPassword( pNewPassword );
//            session.update(employee); 
//            tx.commit();
//        } 
//        catch (HibernateException e) 
//        {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace(); 
//        } 
//        finally 
//        {
//          session.close(); 
//        }
//        
//        return null;
//    }
}
