/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michel
 */
public class DAOOrder {
    
    
public synchronized static List<Order> loadOrder(int idb,Connection c) throws SQLException {
        //list of order
        List<Order> listOrder = new ArrayList<>();
        //connect to the bdd 
        Connection myConn = Database.getConnection();
         //request
        Statement myStmt = myConn.createStatement();
        //result of request
        ResultSet myRs = myStmt.executeQuery("select * from bonlivraison where idBonLivraison ="+idb);
        //loop for add order
        while (myRs.next()) {
            int id = myRs.getInt("idBonLivraison");
            int qte = myRs.getInt("qte");
            int idpro = myRs.getInt("Produit_idProduit");
            int idf= myRs.getInt("Fournisseur_idFournisseur");

            Order O = new Order(id, qte,idpro,idf);

            listOrder.add(O);
}
        myStmt.close();
        // return list of Order
        return listOrder;
        
    }
}
