/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package path;

import Server.ConnectionPool;
import Server.DAOCustomer;
import static Server.DAOCustomer.addCustomer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Angelique Class ProfileGeneration is used to generate customers and
 * their profiles in order to generate paths To use in case the normal classes
 * to handle this such as Profile or Customers cannot be used
 */
public class ProfileGeneration {

    ConnectionPool pool = new ConnectionPool();


    public ProfileGeneration(ConnectionPool pool) {
        this.pool = new ConnectionPool();
    }

    public void init(){
        pool.initPool();
    }
    /**
     * createCustomers() creates 20 fictional PGM customers in the database
     * using the connection pool
     *
     * @throws SQLException
     */
    public void createCustomers() throws SQLException {
        if (pool.getFreeConnection() > 0) {
            for (int i = 0; i < 20; i++) {
                Connection c = pool.getConnection();
                addCustomer(c, "A", "A", "@ postale", "94000", "Creteil", "mymail@domain.com", "F");
                pool.releaseConnection(c);
            }
        } else {
            System.out.println("All connections are used!");
        }
    }

    /**
     * createProfiles() inserts basic profiles into the database using the
     * profile name and customer id as arguments
     */
    public void createProfile(int idProfile, String NomProfil, int idClient) throws SQLException {
        if (pool.getFreeConnection() > 0) {
            Connection c = pool.getConnection();
            PreparedStatement myStmt = null;
            myStmt = c.prepareStatement("insert into profile (idProfile, profilename, client_idClient)" + "values (?,?,?)");
            myStmt.setString(1, Integer.toString(idProfile));
            myStmt.setString(2, NomProfil);
            myStmt.setString(3, Integer.toString(idClient));
            myStmt.executeUpdate();
            myStmt.close();
            pool.releaseConnection(c);
        } else {
            System.out.println("All connections are used!");
        }
    }

    public static void main(String[] args) throws SQLException {
        ProfileGeneration autoGen = new ProfileGeneration(new ConnectionPool());
        autoGen.init();
        //autoGen.createCustomers(); //already in db
        autoGen.createProfile(1, "multimédia", 19);
        autoGen.createProfile(2, "vetement mixte", 4);
        autoGen.createProfile(3, "sport", 9);
        autoGen.createProfile(4, "vetement femme", 9);
        autoGen.createProfile(5, "vetement homme", 13);
        autoGen.createProfile(6, "vetement enfant", 13);
        autoGen.createProfile(7, "accessoires", 6);
        autoGen.createProfile(8, "restaurant", 5);
        autoGen.createProfile(9, "fastfood", 13);
        autoGen.createProfile(10, "vêtement luxe", 11);
        autoGen.createProfile(11, "vêtement pas cher", 2);
        autoGen.createProfile(12, "utilitaire", 17);
        autoGen.createProfile(13, "fleurs", 20);
        autoGen.createProfile(14, "culture", 14);
    }
}
