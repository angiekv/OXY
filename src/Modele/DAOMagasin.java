/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OXY
 */
public class DAOMagasin {

    public static List<Magasin> chargeMagasin() throws SQLException {
        //list contenant tout les magasin 
        List<Magasin> listMagasin = new ArrayList<>();
        //connection base de donnée 
        Connection myConn = Database.getConnection();
         //notre requete
        Statement myStmt = myConn.createStatement();
        //notre résultat requer 
        ResultSet myRs = myStmt.executeQuery("select * from magasin");
        //boucle pour ajouter notre ligne de magasin a la list
        while (myRs.next()) {
            int id = myRs.getInt("idMagasin");
            String designation = myRs.getString("designation");
            String description = myRs.getString("description");
            int idtype = myRs.getInt("Type_idType");

            Magasin M = new Magasin(id, designation, description, idtype);

            listMagasin.add(M);
        }
        myStmt.close();
        // on retourne la liste des magasins 
        return listMagasin;
        

    }

    public static void modifierMagasin(int idMagasin, String designation, String description, int idType) throws SQLException {
        // preparation requete 
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        //notre requete
        myStmt = myConn.prepareStatement("update magasin set designation=? ,description=?, Type_idType=? where idMagasin=? ");
        //valeur saisie dans l'ordre des "?" dans la requete 
        myStmt.setString(1, designation);
        myStmt.setString(2, description);
        myStmt.setInt(3, idType);
        myStmt.setInt(4, idMagasin);
        myStmt.executeUpdate();
        myStmt.close();

    }
    
    public static void supprimerMagasin(int idMagasin) throws SQLException {
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("delete From magasin where idMagasin=?");
        //notre requete
        myStmt.setInt(1, idMagasin);

        myStmt.executeUpdate();
        myStmt.close();
    }
    
    public static void AjouterMagasin(String designation, String description, int idType) throws SQLException {
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert into magasin (designation,description,Type_idType)"+ "values (?,?,?)");
        //notre requete
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
