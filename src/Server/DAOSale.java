/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.Date;
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
public class DAOSale {
    
public static List<Sale> loadSale(Connection c) throws SQLException {
        //list of sale
        List<Sale> listSale = new ArrayList<>();
        //connect to the bdd 
        Connection myConn = Database.getConnection();
         //request
        Statement myStmt = myConn.createStatement();
        //result of request
        ResultSet myRs = myStmt.executeQuery("select * from achat");
        //loop for add sale
        while (myRs.next()) {
            int ida = myRs.getInt("idachat");
            int idp = myRs.getInt("produit_idProduit");
            int qte = myRs.getInt("qte");
            int idc = myRs.getInt("client_idClient");

            Sale A = new Sale(ida, idp, qte, idc);

            listSale.add(A);
}
        myStmt.close();
        // return list of sale
        return listSale;
        

    }
    
    public static void addSale(int idp, int qte, int idc,Connection c) throws SQLException {
        PreparedStatement myStmt=null;
        //connect to the bdd
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert into achat (produit_idProduit,qte,client_idClient)" + "values (?,?,?)");
        //request
        myStmt.setInt(1, idp);
        myStmt.setInt(2, qte);
        myStmt.setInt(3, idc);
        myStmt.executeUpdate();
        myStmt.close();

    }
}
