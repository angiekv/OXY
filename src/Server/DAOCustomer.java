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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OXY
 */
public class DAOCustomer {

    /**
     * This method selects all registered customers and puts them into a list
     *
     * @return the list
     * @throws SQLException
     */
    public synchronized static List<Customer> loadCustomer(Connection c) throws SQLException {
        //list of customer
        List<Customer> listCustomer = new ArrayList<>();
        //request
        Statement myStmt = c.createStatement();
        //result of request
        ResultSet myRs = myStmt.executeQuery("select * from client");
        // Loop to add all the customers
        while (myRs.next()) {
            int idClient = myRs.getInt("idClient");
            String nom = myRs.getString("nom");
            String prenom = myRs.getString("prenom");
            String adresse = myRs.getString("adresse");
            String cp = myRs.getString("cp");
            String ville = myRs.getString("ville");
            String mail = myRs.getString("mail");
            String sexe = myRs.getString("sexe");
            //We now want to populate table client_has_profile
            Statement myStmt2 = c.createStatement();
            //Query that matche customers with their profile(s)
            ResultSet myRs2 = myStmt2.executeQuery("select idProfile, profilename from client_has_profile, profile where client_has_profile.Profile_idProfile = profile.idProfile and client_has_profile.client_idClient = " + idClient);
            List<Profile> profileList = new ArrayList<>();
            while (myRs2.next()) {
                int idProfile = myRs2.getInt("idProfile");
                String profilename = myRs2.getString("profilename");
                profileList.add(new Profile(idProfile, profilename));
            }
            Customer C = new Customer(idClient, nom, prenom, adresse, cp, ville, mail, sexe, profileList);

            listCustomer.add(C);
        }
        myStmt.close();
        // returns the list of customers
        return listCustomer;

    }

    /**
     * We want to load only the customers which have a certain profile
     *
     * @param c
     * @param profil
     * @return
     * @throws SQLException
     */
    public synchronized static List<Customer> loadCustomer(Connection c, String p) throws SQLException {
        List<Customer> filteredClientList = new ArrayList<>();
        //The query which selects all the shops
        Statement myStmt = c.createStatement();
        //The query which selects all the shops matching the profile
        ResultSet myRs = myStmt.executeQuery("select client.* from client, client_has_profile, profile where client.idClient = client_has_profile.client_idClient and client_has_profile.Profile_idProfile = profile.idProfile and profilename LIKE " + p);
        //Loop which adds customers to the list
        while (myRs.next()) {
            int idClient = myRs.getInt("idClient");
            String nom = myRs.getString("nom");
            String prenom = myRs.getString("prenom");
            String adresse = myRs.getString("adresse");
            String cp = myRs.getString("cp");
            String ville = myRs.getString("ville");
            String mail = myRs.getString("mail");
            String sexe = myRs.getString("sexe");
            //liste type 
            Statement myStmt2 = c.createStatement();
            //The query which selects all the shops.
            ResultSet myRs2 = myStmt2.executeQuery("SELECT profile.* from client_has_profile, profile where client_has_profile.Profile_idProfile=profile.idProfile and client_has_profile.client_idClient=" + idClient);
            List<Profile> profileList = new ArrayList<>();
            while (myRs2.next()) {
                int idProfile = myRs2.getInt("idProfile");
                String profilename = myRs2.getString("profilename");
                Profile profiles = new Profile(idProfile, profilename);
                profileList.add(profiles);
            }
            Customer customer = new Customer(idClient, nom, prenom, adresse, cp, ville, mail, sexe, profileList);
            filteredClientList.add(customer);
        }
        myStmt.close();
        return filteredClientList;
    }

    public synchronized static void updateCustomer(Connection c, int idClient, String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe) throws SQLException {
        // prepare request
        PreparedStatement myStmt = null;
        //request
        myStmt = c.prepareStatement("update client set nom=? ,prenom=?, adresse=?, cp=? , ville=?, mail=?, sexe=? where idClient=? ");
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

    public synchronized static void deleteCustomer(Connection c, int idClient) throws SQLException {
        PreparedStatement myStmt = null;
        myStmt = c.prepareStatement("delete From client where idClient=?");
        //request
        myStmt.setInt(1, idClient);
        myStmt.executeUpdate();
        myStmt.close();
    }

    public synchronized static void addCustomerProfile(Connection c, String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe, List<Integer> idProfiles) throws SQLException {
        PreparedStatement myStmt = null;
        myStmt = c.prepareStatement("insert into client (nom,prenom,adresse,cp,ville,mail,sexe)" + "values (?,?,?,?,?,?,?)");
        //request
        myStmt.setString(1, nom);
        myStmt.setString(2, prenom);
        myStmt.setString(3, adresse);
        myStmt.setString(4, cp);
        myStmt.setString(5, ville);
        myStmt.setString(6, mail);
        myStmt.setString(7, sexe);
        myStmt.executeUpdate();
        Statement myStmt2 = c.createStatement();
        //The query which selects all the shops.
        ResultSet myRs = myStmt2.executeQuery("select last_insert_id() as last_id from client");
        myRs.next();
        int lastid = myRs.getInt("last_id");
        //This request insert the type of the shop in database;
        for (int idProfile : idProfiles) {
            PreparedStatement myStmt3 = null;
            myStmt3 = c.prepareStatement("insert into client_has_profile values (?,?)");
            myStmt3.setInt(1, lastid);
            myStmt3.setInt(2, idProfile);
            myStmt3.executeUpdate();
            myStmt.close();
        }
    }

    public synchronized static void addCustomer(Connection c, String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe) throws SQLException {
        PreparedStatement myStmt = null;
        myStmt = c.prepareStatement("insert into client (nom,prenom,adresse,cp,ville,mail,sexe)" + "values (?,?,?,?,?,?,?)");
        //request
        myStmt.setString(1, nom);
        myStmt.setString(2, prenom);
        myStmt.setString(3, adresse);
        myStmt.setString(4, cp);
        myStmt.setString(5, ville);
        myStmt.setString(6, mail);
        myStmt.setString(7, sexe);
        myStmt.executeUpdate();
    }

    public synchronized static List<String> loadProfileById(Connection c, int clientIdClient) throws SQLException {
        List<String> listProfileById = new ArrayList<>();
        Statement myStmt = c.createStatement();
        ResultSet myRs = myStmt.executeQuery("select profilename from profile, client_has_profile where profile.idProfile = client_has_profile.Profile_idProfile and client_idClient = " + clientIdClient);
        while (myRs.next()) {
            String name = myRs.getString("profilename");
            listProfileById.add(name);
        }
        return listProfileById;
    }

    public synchronized static void insertClientHasProfile(Connection c, int clientIdclient, int idProfile) throws SQLException {
        PreparedStatement myStmt = null;
        myStmt = c.prepareStatement("insert client_has_profile (client_idClient, Profile_idProfile) values (?,?)");
        //request
        myStmt.setInt(1, clientIdclient);
        myStmt.setInt(2, idProfile);
        myStmt.executeUpdate();

    }

    public synchronized static Integer totalQteByIdClient(Connection c, int clientIdClient) throws SQLException {
        Statement myStmt = c.createStatement();
        ResultSet myRs = myStmt.executeQuery("select sum(achat.qte) as qtetotal from achat, produit, magasin, magasin_has_type, type where achat.produit_idProduit=produit.idProduit and produit.magasin_idMagasin=magasin.idMagasin and magasin.idmagasin=magasin_has_type.magasin_idMagasin and magasin_has_type.type_idType=type.idType and client_idClient = " + clientIdClient);
        while (myRs.next()) {
            int qteTotal = myRs.getInt("qtetotal");
            return qteTotal;
        }
        return 0;
    }

    public synchronized static Map<Integer,Integer> qteByIdType(Connection c, int clientIdClient) throws SQLException {
        HashMap<Integer, Integer> listQteId = new HashMap<Integer, Integer>();
        Statement myStmt = c.createStatement();
        ResultSet myRs = myStmt.executeQuery("select sum(achat.qte) as qtebytype , type.idtype from achat, produit, magasin, magasin_has_type, type where achat.produit_idProduit=produit.idProduit and produit.magasin_idMagasin=magasin.idMagasin and magasin.idmagasin=magasin_has_type.magasin_idMagasin and magasin_has_type.type_idType=type.idType and client_idClient= " + clientIdClient + " group by type.idType");
        while (myRs.next()) {
            int qte = myRs.getInt("qtebytype");
            int idtype = myRs.getInt("idtype");
            
            listQteId.put(qte, idtype);
        }
        return listQteId;
    }

    /*test */
//    public static void main(String[] args) throws Exception {
//        ConnectionPool pool = new ConnectionPool();
//        pool.initPool();
//        Connection c = pool.getConnection();
//        System.out.println(totalQteByIdClient(c,2));
//        System.out.println(qteByIdType(c,2));
//        System.out.println(pool.getFreeConnection());
//        List<Integer> idProfiles = new ArrayList<>();
//        idProfiles.add(5);
//        idProfiles.add(7);
//        addCustomerProfile(c, "jean", "jn", "@", "35530", "Rns", "@", "F", idProfiles);
//        System.out.println(loadCustomer(c, "'mode%'"));
//        pool.releaseConnection(c);
//        System.out.println(loadCustomer(c));

//        System.out.println(dao.loadCustomer());
//        dao.addCustomer("Inge","1B","ESIPE","94000","Créteil","Esipe@gmail.com","N");
//        System.out.println(dao.loadCustomer());
//        dao.updateCustomer(1,"Inge","3B","ESIAG","94000","Créteil","Esiag@gmail.com","M");
//        System.out.println(dao.loadCustomer());
//        dao.deleteCustomer(1);
//        System.out.println(dao.loadCustomer());
//    }
//        /*test */
//    public static void main(String[] args) throws Exception {
//
//        DAOCustomer dao = new DAOCustomer();
//        System.out.println(dao.loadProfileid(2));
    }
