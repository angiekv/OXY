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
public class DAOClient {
    
public static List<Client> chargeClient() throws SQLException {
        //liste contenant tout les clients
        List<Client> listClient = new ArrayList<>();
        //connection base de donnée 
        Connection myConn = Database.getConnection();
         //notre requete
        Statement myStmt = myConn.createStatement();
        //notre résultat requer 
        ResultSet myRs = myStmt.executeQuery("select * from produit");
        //boucle pour ajouter notre ligne de client a la list
        while (myRs.next()) {
            int id = myRs.getInt("idClient");
            String nom = myRs.getString("nom");
            String prenom = myRs.getString("prenom");
            String adresse = myRs.getString("adresse");
            String cp = myRs.getString("cp");
            String ville = myRs.getString("ville");
            String mail = myRs.getString("mail");
            String sexe = myRs.getString("sexe");

            Client C = new Client(id, nom, prenom, adresse, cp, ville, mail, sexe);

            listClient.add(C);
}
        myStmt.close();
        // on retourne la liste des clients
        return listClient;
        

    }

    public static void modifierClient(int idClient, String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe) throws SQLException {
        // preparation requete 
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        //notre requete
        myStmt = myConn.prepareStatement("update client set nom=? ,prenom=?, adresse=?, cp=? , ville=?, mail=?, sexe=? where idClient=? ");
        //valeur saisie dans l'ordre des "?" dans la requete 
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
    
    public static void supprimerClient(int idClient) throws SQLException {
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("delete From client where idClient=?");
        //notre requete
        myStmt.setInt(1, idClient);

        myStmt.executeUpdate();
        myStmt.close();
    }
    
    public static void AjouterClient(String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe) throws SQLException {
        PreparedStatement myStmt=null;
        //connection base de donnée 
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert into client (nom,prenom,adresse,cp,ville,mail,sexe)"+ "values (?,?,?,?,?,?,?)");
        //notre requete
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

        DAOClient dao = new DAOClient();
        System.out.println(dao.chargeClient());
        dao.AjouterClient("Inge","1B","ESIPE","94000","Créteil","Esipe@gmail.com","N");
        System.out.println(dao.chargeClient());
        dao.modifierClient(1,"Inge","3B","ESIAG","94000","Créteil","Esiag@gmail.com","M");
        System.out.println(dao.chargeClient());
        dao.supprimerClient(1);
        System.out.println(dao.chargeClient());
    }
}
