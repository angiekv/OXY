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
 * @author Michel
 */
public class DAOProduct {
    
public synchronized static List<Product> loadProduct(Connection c) throws SQLException {
        //list of product
        List<Product> listProduct = new ArrayList<>();
        //connect to the bdd 
        Connection myConn = Database.getConnection();
         //request
        Statement myStmt = myConn.createStatement();
        //result of request
        ResultSet myRs = myStmt.executeQuery("select * from produit");
        //loop for add product
        while (myRs.next()) {
            int id = myRs.getInt("idProduit");
            String libelle = myRs.getString("libelle");
            Float prix = myRs.getFloat("prix");
            int qte = myRs.getInt("qte");
            int idMag = myRs.getInt("Magasin_idMagasin");

            Product P = new Product(id, libelle, prix, qte, idMag);

            listProduct.add(P);
}
        myStmt.close();
        // return list of product
        return listProduct;
        

    }

public synchronized static List<Product> loadProductStore(int idm,Connection c) throws SQLException {
        //list of product
        List<Product> listProduct = new ArrayList<>();
        //connect to the bdd 
        Connection myConn = Database.getConnection();
         //request
        Statement myStmt = myConn.createStatement();
        //result of request
        ResultSet myRs = myStmt.executeQuery("select * from produit where Magasin_idMagasin ="+idm);
        //loop for add product
        while (myRs.next()) {
            int id = myRs.getInt("idProduit");
            String libelle = myRs.getString("designation");
            Float prix = myRs.getFloat("prix");
            int qte = myRs.getInt("qte");
            int idMag = myRs.getInt("Magasin_idMagasin");

            Product P = new Product(id, libelle, prix, qte, idMag);

            listProduct.add(P);
}
        myStmt.close();
        // return list of product
        return listProduct;
        

    }

    public synchronized static void updateProduct(int idProduit, String libelle, float prix, int qte, int Magasin_idMagasin,Connection c) throws SQLException {
        // prepare request
        PreparedStatement myStmt=null;
        //connect to the bdd
        Connection myConn = Database.getConnection();
        //request
        myStmt = myConn.prepareStatement("update produit set designation=? ,prix=?, qte=?, Magasin_idMagasin=?  where idProduit=? ");
        //value entered in the order of '?' in the request
        myStmt.setString(1, libelle);
        myStmt.setFloat(2, prix);
        myStmt.setInt(3, qte);
        myStmt.setInt(4, Magasin_idMagasin);
        myStmt.setInt(5, idProduit);
        myStmt.executeUpdate();
        myStmt.close();

    }
    
    public synchronized static void deleteProduct(int idProduit,Connection c) throws SQLException {
        PreparedStatement myStmt=null;
        //connect to the bdd
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("delete From produit where idProduit=?");
        //request
        myStmt.setInt(1, idProduit);

        myStmt.executeUpdate();
        myStmt.close();
    }
    
    public synchronized static void addProduct(String libelle, float prix, int qte, int Magasin_idMagasin,Connection c) throws SQLException {
        PreparedStatement myStmt=null;
        //connect to the bdd
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert into produit (designation,prix,qte,Magasin_idMagasin)"+ "values (?,?,?,?)");
        //request
        myStmt.setString(1, libelle);
        myStmt.setFloat(2, prix);
        myStmt.setInt(3, qte);
        myStmt.setInt(4, Magasin_idMagasin);
        myStmt.executeUpdate();
        myStmt.close();

    }
}
