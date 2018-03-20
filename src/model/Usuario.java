/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ruben Hernandez
 */
public class Usuario {

    List<String> users = new ArrayList<String>();
    List<String> passwds = new ArrayList<String>();

    public void initUsuarios() {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

//        System.out.println("Oracle JDBC Driver Registered!");
        Connection connection = null;
        Statement stmt = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.166.51.12:1521:rmsdev01", "rms", "rms");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
//            System.out.println("You made it, take control your database now!");

            try {
//            System.out.println("Creating statement...");
                stmt = connection.createStatement();
                String sql;
                sql = "select * from cuentas_java";
                ResultSet rs = stmt.executeQuery(sql);

                //STEP 5: Extract data from result set
//        int i =0;
                while (rs.next()) {
                    //Retrieve by column name
                    users.add(rs.getString("usuario"));
                    passwds.add(rs.getString("contrasena"));

//         //Display values
//         System.out.print(", usuario: " + users.get(i));
//         System.out.println(", contraseña: " + passwds.get(i));
//         i++;
                }

            } catch (SQLException ex) {
                System.out.println("No se obtuvo statement");
            }

        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public int getUserIndex(String user) {
        return users.indexOf(user);
    }

    public boolean validacion(int index, String contrasena) {
        if(index >= 0)
        {
            if(contrasena.equals(passwds.get(index)))
            {
                return true;
            }
            else
                return false;
        }
        return false;
    }

}
