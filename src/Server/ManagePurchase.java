/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Mario
 */
public class ManagePurchase {

    public static int generateRandomInteger(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min argument must be less than max");
        }

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void generatePurchase() throws SQLException {

        int i = 1;
        int id = 1;
        int idAchat;
        int qte;
        Date date;
        int produit;
        int client;

        while (i <= 100) {
            idAchat = id;
            qte = generateRandomInteger(1, 2);
            produit = generateRandomInteger(1, 5);
            client = generateRandomInteger(1, 5);

            PreparedStatement myStmt = null;
            Connection myConn = Database.getConnection();
            myStmt = myConn.prepareStatement("insert into achat (`idAchat`, `qte`, `date`, `produit_idProduit`,`Client_idClient`) values (?,?,?,?,?)");
            myStmt.setInt(1, idAchat);
            myStmt.setInt(2, qte);
            myStmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            myStmt.setInt(4, produit);
            myStmt.setInt(5, client);
            myStmt.executeUpdate();
            myStmt.close();
            //increment the loop
            id++;
            i++;
        }
    }

    public static void deletePurchase() throws SQLException {
        PreparedStatement myStmt = null;
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("delete from achat");
        myStmt.executeUpdate();
        myStmt.close();
    }

//    public static void main(String[] args) throws Exception {
//        ManagePurchase a = new ManagePurchase();
//        a.generatePurchase();
//        a.deletePurchase();
//    }
}
