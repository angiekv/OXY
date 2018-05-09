/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package path;

import Server.ConnectionPool;
import Server.DAOCustomer;
import Server.DAOStore;
import static Server.DAOCustomer.addCustomer;
import Server.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void createProfile(String NomProfil) throws SQLException {
        if (pool.getFreeConnection() > 0) {
            Connection c = pool.getConnection();
            PreparedStatement myStmt = null;
            myStmt = c.prepareStatement("insert into profile (profilename)" + "values (?)");
            myStmt.setString(1, NomProfil);
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
     *
     * @param designation
     * @param floor
     * @param localization
     * @param idType
     * @throws SQLException
     */
    public void createShops(String designation, int floor, String localization, List<Integer> ListidType) throws SQLException {
        if (pool.getFreeConnection() > 0) {
            Connection c = pool.getConnection();
            DAOStore dao = new DAOStore();
            dao.addShop(designation, "description", 5000, 200, floor, localization, ListidType, c);
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
//        autoGen.createProfile("multimedia");
//        autoGen.createProfile("mode");
//        autoGen.createProfile("sport");
//        autoGen.createProfile("restaurant");
//        autoGen.createProfile("mode luxe");
//        autoGen.createProfile("alimentaire");
//        autoGen.createProfile("jardin");
//        autoGen.createProfile("culture");
//        autoGen.createProfile("utilitaire");
//        autoGen.createProfile("sante");

//        SHOP TYPES
//        autoGen.createType("multimedia");
//        autoGen.createType("mode");
//        autoGen.createType("sport");
//        autoGen.createType("restaurant");
//        autoGen.createType("mode luxe");
//        autoGen.createType("alimentaire");
//        autoGen.createType("jardin");
//        autoGen.createType("culture");
//        autoGen.createType("utilitaire");
//        autoGen.createType("sante");

//        SHOPS
//        List<Integer> listType = new ArrayList<>();
//        int nbOfTypes = 13;
//        for (int i = 3; i < nbOfTypes; i++){
//            listType.add(i);
//        }
//        autoGen.createShops("Zara", 1, "entree/sortie", listType);
//        autoGen.createShops("H&M", 1, "entree/sortie", listType);
//        autoGen.createShops("Pull&Bear", 1, "indifferent", listType);
//        autoGen.createShops("Primark", 1, "indifferent", listType);
//        autoGen.createShops("Fnac", 1, "N8", entree/sortie);
//        autoGen.createShops("MacDonalds", 2, "entree/sortie", listType);
//        autoGen.createShops("DelArte", 2, "indifferent", listType);
//        autoGen.createShops("BurgerKing", 2, "entree/sortie", listType);
//        autoGen.createShops("Accessorize", 2, "indifferent", listType);
//        autoGen.createShops("Gucci", 2, "indifferent", listType);

    }
}
