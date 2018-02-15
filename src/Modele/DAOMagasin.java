/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Modele.Database;
import Modele.Magasin;
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

public class DAOMagasin {
/**
 * This method selects all the shops and puts the shops into a list.
 * @return the list of all the shops.
 * @throws SQLException 
 */
    public static List<Magasin> chargeMagasin() throws SQLException {
        //List containing all the shops.
        List<Magasin> listMagasin = new ArrayList<>();
        //Connection to the database.
        Connection myConn = Database.getConnection();
         //The request.
        Statement myStmt = myConn.createStatement();
        //The query which selects all the shops.
        ResultSet myRs = myStmt.executeQuery("select * from magasin");
        //Loop which add a shop to the list.
        while (myRs.next()) {
            int id = myRs.getInt("idMagasin");
            String designation = myRs.getString("designation");
            String description = myRs.getString("description");
            int idtype = myRs.getInt("Type_idType");

            Magasin M = new Magasin(id, designation, description, idtype);

            listMagasin.add(M);
        }
        myStmt.close(); 
        return listMagasin;
        

    }
/**
 * This method updates a shop.
 * @param idMagasin
 * @param designation
 * @param description
 * @param idType
 * @throws SQLException 
 */
    public static void modifierMagasin(int idMagasin, String designation, String description, int idType) throws SQLException {
        
        PreparedStatement myStmt=null;
        //Connection to the database.
        Connection myConn = Database.getConnection();
        //The query which modifies one or more attributes of a shop.
        myStmt = myConn.prepareStatement("update magasin set designation=? ,description=?, Type_idType=? where idMagasin=? ");
        //The attributes' values are placed in the same order as "?".
        myStmt.setString(1, designation);
        myStmt.setString(2, description);
        myStmt.setInt(3, idType);
        myStmt.setInt(4, idMagasin);
        myStmt.executeUpdate();
        myStmt.close();

    }
/**
 * This method deletes a shop from the database.
 * @param idMagasin
 * @throws SQLException 
 */
    public static void supprimerMagasin(int idMagasin) throws SQLException {
        PreparedStatement myStmt=null;
        //Connection to the database 
        Connection myConn = Database.getConnection();
        //The query deletes a shop from the database.
        myStmt = myConn.prepareStatement("delete From magasin where idMagasin=?");
        myStmt.setInt(1, idMagasin);
        myStmt.executeUpdate();
        myStmt.close();
    }
/**
 * This method adds a shop to the database
 * @param designation
 * @param description
 * @param idType
 * @throws SQLException 
 */
    public static void AjouterMagasin(String designation, String description, int idType) throws SQLException {
        PreparedStatement myStmt=null;
        //Connection to the database
        Connection myConn = Database.getConnection();
        //The query adds a shop to the database.
        myStmt = myConn.prepareStatement("insert into magasin (designation,description,Type_idType)"+ "values (?,?,?)");
        myStmt.setString(1, designation);
        myStmt.setString(2, description);
        myStmt.setInt(3, idType);
        myStmt.executeUpdate();
        myStmt.close();

    }
    
    /*test */
    public static void main(String[] args) throws Exception {

        DAOMagasin dao = new DAOMagasin();
        System.out.println(dao.chargeMagasin());
        dao.AjouterMagasin("ADIDAS","Marque scécialisé dans le sport",1);
        System.out.println(dao.chargeMagasin());
        dao.modifierMagasin(1,"PUMA","Marque spécialisé dans le sport",1);
        System.out.println(dao.chargeMagasin());
        dao.supprimerMagasin(1);
        System.out.println(dao.chargeMagasin());
    }
}
