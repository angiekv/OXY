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
 * @author OXY
 */
public class DAOCustomer {
    /**
     * This method selects all registered customers and puts them into a list
     * @return the list
     * @throws SQLException 
     */
    
public static List<Customer> loadCustomer() throws SQLException {
        //list of customer
        List<Customer> listCustomer = new ArrayList<>();
        //connect to the bdd 
        Connection myConn = Database.getConnection();
         //request
        Statement myStmt = myConn.createStatement();
        //result of request
        ResultSet myRs = myStmt.executeQuery("select * from client");
        //loop for add customer
        while (myRs.next()) {
            int id = myRs.getInt("idClient");
            String nom = myRs.getString("nom");
            String prenom = myRs.getString("prenom");
            String adresse = myRs.getString("adresse");
            String cp = myRs.getString("cp");
            String ville = myRs.getString("ville");
            String mail = myRs.getString("mail");
            String sexe = myRs.getString("sexe");

            Customer C = new Customer(id, nom, prenom, adresse, cp, ville, mail, sexe);

            listCustomer.add(C);
}
        myStmt.close();
        // returns the list of customers
        return listCustomer;
        

    }

    public static void updateCustomer(int idClient, String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe) throws SQLException {
        // prepare request
        PreparedStatement myStmt=null;
        //connect to the bdd
        Connection myConn = Database.getConnection();
        //request
        myStmt = myConn.prepareStatement("update client set nom=? ,prenom=?, adresse=?, cp=? , ville=?, mail=?, sexe=? where idClient=? ");
        //value entered in the order of '?' in the request
        myStmt.setString(1, nom);
        myStmt.setString(2, prenom);
        myStmt.setString(3, adresse);
        myStmt.setString(4, cp);
        myStmt.setString(5, ville);
        myStmt.setString(6, mail);
        myStmt.setString(7, sexe);
        myStmt.setInt(8, idClient);
        myStmt.executeUpdate();
        myStmt.close();

    }
    
    public static void deleteCustomer(int idClient) throws SQLException {
        PreparedStatement myStmt=null;
        //connect to the bdd
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("delete From client where idClient=?");
        //request
        myStmt.setInt(1, idClient);
        myStmt.executeUpdate();
        myStmt.close();
    }
    
    public static void addCustomer(String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe) throws SQLException {
        PreparedStatement myStmt=null;
        //connect to the bdd
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert into client (nom,prenom,adresse,cp,ville,mail,sexe)"+ "values (?,?,?,?,?,?,?)");
        //request
        myStmt.setString(1, nom);
        myStmt.setString(2, prenom);
        myStmt.setString(3, adresse);
        myStmt.setString(4, cp);
        myStmt.setString(5, ville);
        myStmt.setString(6, mail);
        myStmt.setString(7, sexe);
        myStmt.executeUpdate();
        myStmt.close();

    }
    
    /*test */
    public static void main(String[] args) throws Exception {

        DAOCustomer dao = new DAOCustomer();
        System.out.println(dao.loadCustomer());
//        dao.addCustomer("Inge","1B","ESIPE","94000","Créteil","Esipe@gmail.com","N");
//        System.out.println(dao.loadCustomer());
//        dao.updateCustomer(1,"Inge","3B","ESIAG","94000","Créteil","Esiag@gmail.com","M");
//        System.out.println(dao.loadCustomer());
//        dao.deleteCustomer(1);
//        System.out.println(dao.loadCustomer());
    }
}

