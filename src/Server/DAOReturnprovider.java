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
public class DAOReturnprovider {
    
    public static void addReturnprovider(int idp, int qte, int idf, Connection c) throws SQLException {
        PreparedStatement myStmt=null;
        //connect to the bdd
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert into retourfournisseur(produit_idProduit,qte,fournisseur_idFournisseur)" + "values (?,?,?)");
        //request
        myStmt.setInt(1, idp);
        myStmt.setInt(2, qte);
        myStmt.setInt(3, idf);
        myStmt.executeUpdate();
        myStmt.close();

    }
}
