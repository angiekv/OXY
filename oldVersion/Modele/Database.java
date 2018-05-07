/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OXY
 */
public class Database {
    // variable de connexion a la base 
//    private static final String URL = "jdbc:mysql://10.16.1.254/oxy";
    private static final String URL = "jdbc:mysql://localhost/oxy";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Nom du pilote (driver). Dans ce cas, MySQL
    private static final String DRIVER_NAME= "com.mysql.jdbc.Driver";

    static {
// Chargement du pilote
        try {

            Class.forName(DRIVER_NAME).newInstance();
            System.out.println("*** Driver loaded.");
        } catch (ClassNotFoundException e) {
            System.err.println("*** ERROR: Driver " + DRIVER_NAME + "not found");
        } catch (InstantiationException e) {
            System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
            System.err.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
            System.err.println(e.getMessage());
        }
    }
    //recuperation de la connexion
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    

}
