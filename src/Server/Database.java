/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author OXY
 */
public class Database {
    // Database connection variables
    private static final String URL = "jdbc:mysql://localhost/oxy";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Here we chose mysql so this is the mysql driver
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
