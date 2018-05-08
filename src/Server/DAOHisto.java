/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Model.*;
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
public class DAOHisto {
    
        public synchronized static List<Histo> loadHistoProduct(int idh,Connection c) throws SQLException {
        //list of histo
        List<Histo> listHisto = new ArrayList<>();
        //connect to the bdd 
        Connection myConn = Database.getConnection();
        //request
        Statement myStmt = myConn.createStatement();
        //result of request
        ResultSet myRs = myStmt.executeQuery("select * from historique where Produit_idProduit ="+idh);
        //loop for add histo
        while (myRs.next()) {
            int ida = myRs.getInt("achat_idAchat");
            int idb = myRs.getInt("BonLivraison_idBonLivraison");
            int idrc = myRs.getInt("retourClient_idretourc");
            int idrf = myRs.getInt("retourFournisseur_idretourf");
            int idp = myRs.getInt("Produit_idProduit");
            int qte = myRs.getInt("qte");
            Date date = myRs.getDate("date");
            String action = myRs.getString("action");

            Histo H = new Histo(ida, idb,idrc,idrf, idp, qte, date, action);

            listHisto.add(H);
        }
        myStmt.close();
        // return list of histo
        return listHisto;

    }

}
