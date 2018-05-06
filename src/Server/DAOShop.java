/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OXY
 */

public class DAOShop {
/**
 * This method selects all the shops and puts the shops into a list.
 * @return the list of all the shops.
 * @throws SQLException 
 */
    
    public synchronized static List<Shop> loadShops(Connection c) throws SQLException {
        //list of customer
        List<Shop> listShop = new ArrayList<>();
        Statement myStmt = c.createStatement();
        //The query which selects all the shops.
        ResultSet myRs = myStmt.executeQuery("select * from magasin");
        //Loop which add a shop to the list.
        while (myRs.next()) {
            int id = myRs.getInt("idMagasin");
            String designation = myRs.getString("designation");
            String description = myRs.getString("description");
            int rent = myRs.getInt("loyer");
            int surface = myRs.getInt("superficie");
            int floor = myRs.getInt("niveau");
            String localization = myRs.getString("localisation");
            Shop M = new Shop(id, designation, description, rent,surface,floor,localization);

            listShop.add(M);
        }
        myStmt.close(); 
        return listShop;
        

    }
    
/**
 * This method updates a shop.
 * @param idShop
 * @param designation
 * @param description
 * @param rent
 * @param surface
 * @param floor
 * @param localization
 * @param idType
 * @param c
 * @throws SQLException 
 */
    public synchronized void updateShop(int idShop, String designation, String description,int rent,int surface ,int floor ,String localization, int idType ,Connection c) throws SQLException {
        
        PreparedStatement myStmt=null;
        //The query which modifies one or more attributes of a shop.
        myStmt = c.prepareStatement("update magasin set designation=? ,description=?, loyer=? , superficie=? , niveau=? , localisation=?, where idMagasin=? ");
        
        
        //The attributes' values are placed in the same order as "?".
        myStmt.setString(1, designation);
        myStmt.setString(2, description);
        myStmt.setInt(3, rent);
        myStmt.setInt(4, surface);
        myStmt.setInt(5, floor);
        myStmt.setString(6, localization);
        myStmt.setInt(7, idShop);
        myStmt.executeUpdate();
        //This request update the type of the shop ;
        PreparedStatement myStmt2=null;
        myStmt2= c.prepareStatement("update magasin_has_type set type_idType=? where magasin_idMagasin=?");
        myStmt2.setInt(1, idType);
        myStmt2.setInt(2, idShop);
        myStmt.executeUpdate();
    }
/**
 * This method deletes a shop from the database.
 * @param idShop
     * @param c
 * @throws SQLException 
 */
    public synchronized void deleteShop(int idShop,Connection c) throws SQLException {
        
        PreparedStatement myStmt=null;
        //The query deletes type of the shop we are going to delete from the database.
        myStmt = c.prepareStatement("delete From magasin_has_type where magasin_idMagasin=?");
        myStmt.setInt(1, idShop);
        myStmt.executeUpdate();
        
        PreparedStatement myStmt2=null;
        
        //The query deletes a shop from the database.
        myStmt2 = c.prepareStatement("delete From magasin where idMagasin=?");
        myStmt2.setInt(1, idShop);
        myStmt2.executeUpdate();;
    }
/**
 * This method adds a shop to the database
 * @param designation
 * @param description
 * @param idType
 * @throws SQLException 
 */
    public synchronized void addShop(String designation, String description,int rent,int surface ,int floor ,String localization, int idType ,Connection c) throws SQLException {
        //The query adds a shop to the database.
         PreparedStatement myStmt=null;
        myStmt = c.prepareStatement("insert into magasin (designation,description,loyer,superficie,niveau,localisation) values (?,?,?,?,?,?) ");
        
        //The attributes' values are placed in the same order as "?".
        myStmt.setString(1, designation);
        myStmt.setString(2, description);
        myStmt.setInt(3, rent);
        myStmt.setInt(4, surface);
        myStmt.setInt(5, floor);
        myStmt.setString(6, localization);
        myStmt.executeUpdate();
        Statement myStmt2 = c.createStatement();
        //The query which selects all the shops.
        ResultSet myRs = myStmt2.executeQuery("select last_insert_id() as last_id from magasin");
        myRs.next();
        int lastid = myRs.getInt("last_id");
        //This request insert the type of the shop in database;
        PreparedStatement myStmt3=null;
        myStmt3= c.prepareStatement("insert into magasin_has_type values (?,?)");
        myStmt3.setInt(1, lastid);
        myStmt3.setInt(2, idType);
        myStmt3.executeUpdate();

    }
    
    /*test */
//    public static void main(String[] args) throws Exception {
//
//        DAOShop dao = new DAOShop();
//        System.out.println(dao.loadShops(Database.getConnection()));
//        dao.deleteShop(2, Database.getConnection());
//        dao.addShop("hetm", "vetemeent", 20000, 110, 1, "SORTIE", 1, Database.getConnection());
//         System.out.println(dao.loadShops(Database.getConnection()));
//    }
}
