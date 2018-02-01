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
 * @author aberkane
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
        ResultSet myRs = myStmt.executeQuery("select * from magasins");
        //boucle pour ajouter notre ligne de magasin a la list
        while (myRs.next()) {
            int id = myRs.getInt("num");
            String nom = myRs.getString("nom");
            String description = myRs.getString("description");
            int idtype = myRs.getInt("idType");

            Magasin M = new Magasin(id, nom, description, idtype);

            listMagasin.add(M);
        }
        myStmt.close();
        // on retourne la liste des magasins 
        return listMagasin;
        

    }

    public static void modifierMagasin(int num, String nom, String description, int idType) throws SQLException {
        // preparation requete 
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        //notre requete
        myStmt = myConn.prepareStatement("update magasins set nom=? ,description=?, idType=? where num=? ");
        //valeur saisie dans l'ordre des "?" dans la requete 
        myStmt.setString(1, nom);
        myStmt.setString(2, description);
        myStmt.setInt(3, idType);
        myStmt.setInt(4, num);
        myStmt.executeUpdate();
        myStmt.close();

    }
    
    public static void supprimerMagasin(int num) throws SQLException {
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("delete From magasins where num=?");
        //notre requete
        myStmt.setInt(1, num);

        myStmt.executeUpdate();
        myStmt.close();
    }
    
    public static void AjouterMagasin(String nom, String description, int idType) throws SQLException {
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert into magasins (nom,description,idType)"
                + "values (?,?,?)");
        //notre requete
        myStmt.setString(1, nom);
        myStmt.setString(2, description);
        myStmt.setInt(3, idType);
        myStmt.executeUpdate();
        myStmt.close();

    }
    
    /*test */
    public static void main(String[] args) throws Exception {

        DAOMagasin dao = new DAOMagasin();
        System.out.println(dao.chargeMagasin());
        dao.modifierMagasin(1,"hetm","test",1);
        System.out.println(dao.chargeMagasin());

    }
}
