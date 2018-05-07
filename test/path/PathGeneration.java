/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package path;

import Server.ConnectionPool;
import Server.DAOCustomer;
import Server.DAOShop;
import static Server.DAOCustomer.addCustomer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Angelique Class PathGeneration is used to generate customers and
 * their profiles in order to generate paths To use in case the normal classes
 * to handle this such as Profile or Customers cannot be used
 */
public class PathGeneration {

    ConnectionPool pool = new ConnectionPool();

    public PathGeneration(ConnectionPool pool) {
        this.pool = new ConnectionPool();
    }

    public void init() {
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

    /**
     * This methods inserts in the Type table the shop types that correspond to
     * the different profiles
     *
     * @param idType
     * @param designation
     * @throws SQLException
     */
    public void createType(String designation) throws SQLException {
        if (pool.getFreeConnection() > 0) {
            Connection c = pool.getConnection();
            PreparedStatement myStmt = null;
            myStmt = c.prepareStatement("insert into type (designation)" + "values (?)");
            myStmt.setString(1, designation);
            myStmt.executeUpdate();
            myStmt.close();
            pool.releaseConnection(c);
        } else {
            System.out.println("All connections are used!");
        }
    }
/**
 * This methods inserts lines in the Shop table 
 * @param designation
 * @param floor
 * @param localization
 * @param idType
 * @throws SQLException 
 */
    public void createShops(String designation, int floor, String localization, int idType) throws SQLException {
        if (pool.getFreeConnection() > 0) {
            Connection c = pool.getConnection();
            DAOShop dao = new DAOShop();
            dao.addShop(designation, "description", 5000, 200 , floor , localization,  idType , c);
            pool.releaseConnection(c);
        } else {
            System.out.println("All connections are used!");
        }
    }

    public static void main(String[] args) throws SQLException {
        PathGeneration autoGen = new PathGeneration(new ConnectionPool());
        autoGen.init();
//        all lines below are aready in database
//        CUSTOMERS
//        autoGen.createCustomers(); 
//        CUSTOMERS' PROFILES
//        autoGen.createProfile(1, "multimédia", 19);
//        autoGen.createProfile(2, "vetement mixte", 4);
//        autoGen.createProfile(3, "sport", 9);
//        autoGen.createProfile(4, "vetement femme", 9);
//        autoGen.createProfile(5, "vetement homme", 13);
//        autoGen.createProfile(6, "vetement enfant", 13);
//        autoGen.createProfile(7, "accessoires", 6);
//        autoGen.createProfile(8, "restaurant", 5);
//        autoGen.createProfile(9, "fastfood", 13);
//        autoGen.createProfile(10, "vêtement luxe", 11);
//        autoGen.createProfile(11, "vêtement pas cher", 2);
//        autoGen.createProfile(12, "utilitaire", 17);
//        autoGen.createProfile(13, "fleurs", 20);
//        autoGen.createProfile(14, "culture", 14);
//        SHOP TYPES
//        autoGen.createType("multimédia");
//        autoGen.createType("vetement mixte");
//        autoGen.createType("sport");
//        autoGen.createType("vetement femme");
//        autoGen.createType("vetement homme");
//        autoGen.createType("vetement enfant");
//        autoGen.createType("accessoires");
//        autoGen.createType("restaurant");
//        autoGen.createType("fastfood");
//        autoGen.createType("vêtement luxe");
//        autoGen.createType("vêtement pas cher");
//        autoGen.createType("utilitaire");
//        autoGen.createType("fleurs");
//        autoGen.createType("culture");
//        SHOPS
//          autoGen.createShops("Zara", 1, "N1", 3);
//          autoGen.createShops("H&M", 1, "E24", 3);
//          autoGen.createShops("Pull&Bear", 1, "S40", 3);
//          autoGen.createShops("Primark", 1, "W63", 3);
//          autoGen.createShops("Fnac", 1, "N8", 2);
//          autoGen.createShops("MacDonalds", 2, "S107", 9);
//          autoGen.createShops("DelArte", 2, "S101", 8);
//          autoGen.createShops("BurgerKing", 2, "S120", 9);
//          autoGen.createShops("Accessorize", 2, "W133", 7);
//          autoGen.createShops("Gucci", 2, "E96", 10);

    }
}
