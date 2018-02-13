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
 * @author Michel
 */
public class DAOProduit {
    
public static List<Produit> chargeProduit() throws SQLException {
        //liste contenant tout les produits
        List<Produit> listProduit = new ArrayList<>();
        //connection base de donnée 
        Connection myConn = Database.getConnection();
         //notre requete
        Statement myStmt = myConn.createStatement();
        //notre résultat requer 
        ResultSet myRs = myStmt.executeQuery("select * from produit");
        //boucle pour ajouter notre ligne de produit a la list
        while (myRs.next()) {
            int id = myRs.getInt("idProduit");
            String libelle = myRs.getString("libelle");
            float prix = myRs.getFloat("prix");
            int qte = myRs.getInt("qte");
            int idmag = myRs.getInt("Magasin_idMagasin");

            Produit P = new Produit(id, libelle, prix, qte, idmag);

            listProduit.add(P);
}
        myStmt.close();
        // on retourne la liste des produits
        return listProduit;
        

    }

    public static void modifierProduit(int idProduit, String libelle, float prix,int qte, int idmag) throws SQLException {
        // preparation requete 
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        //notre requete
        myStmt = myConn.prepareStatement("update produit set libelle=? ,prix=?, qte=?, Magasin_idMagasin=?  where idProduit=? ");
        //valeur saisie dans l'ordre des "?" dans la requete 
        myStmt.setString(1, libelle);
        myStmt.setFloat(2, prix);
        myStmt.setInt(3, qte);
        myStmt.setInt(4, idmag);
        myStmt.setInt(5, idProduit);
        myStmt.executeUpdate();
        myStmt.close();

    }
    
    public static void supprimerProduit(int idProduit) throws SQLException {
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("delete From produit where idProduit=?");
        //notre requete
        myStmt.setInt(1, idProduit);

        myStmt.executeUpdate();
        myStmt.close();
    }
    
    public static void AjouterProduit(String libelle, float prix,int qte, int idmag) throws SQLException {
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert into produit (libelle,prix,qte,Magasin_idMagasin)"+ "values (?,?,?,?)");
        //notre requete
        myStmt.setString(1, libelle);
        myStmt.setFloat(2, prix);
        myStmt.setInt(3, qte);
        myStmt.setInt(4, idmag);
        myStmt.executeUpdate();
        myStmt.close();

    }
    
    /*test */
    public static void main(String[] args) throws Exception {

        DAOProduit dao = new DAOProduit();
        System.out.println(dao.chargeProduit());
        dao.AjouterProduit("Chassure",80,100,1);
        System.out.println(dao.chargeProduit());
        dao.modifierProduit(1,"PUMA",90,10,1);
        System.out.println(dao.chargeProduit());
        dao.supprimerProduit(1);
        System.out.println(dao.chargeProduit());
    }
}
