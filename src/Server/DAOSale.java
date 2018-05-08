/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Michel
 */
public class DAOSale {
    
    
    public static void addSale(int idp, int qte, int idc, Connection c) throws SQLException {
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
