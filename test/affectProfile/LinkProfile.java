/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affectProfile;

import Server.Customer;
import Server.DAOCustomer;
import Server.Database;
import Server.Purchase;
import Server.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mario
 */
public class LinkProfile {

//    public List<Sale> PurchasebyIdClient(int clientIdClient) throws SQLException {
//        List<Sale> listPurchasebyId = new ArrayList<>();
//        Connection myConn = Database.getConnection();
//        Statement myStmt = myConn.createStatement();
//        ResultSet myRs = myStmt.executeQuery("select * from achat where client_idClient = " + clientIdClient);
//        while (myRs.next()) {
//            int idAchat = myRs.getInt("idAchat");
//            int qte = myRs.getInt("qte");
//            Date date = myRs.getDate("date");
//            int idproduit = myRs.getInt("produit_idProduit");
//            int idClient = myRs.getInt("client_idClient");
//            Purchase P = new Purchase(idAchat, qte, date, idproduit, idClient);
//
//            listPurchasebyId.add(P);
//
//        }
//        return listPurchasebyId;
//    }
//    public List<Type> TypebyIdClient(int clientIdClient) throws SQLException {
//        List<Type> listTypebyId = new ArrayList<>();
//        Connection myConn = Database.getConnection();
//        Statement myStmt = myConn.createStatement();
//        ResultSet myRs = myStmt.executeQuery("select type.idtype, type.designation from achat, produit, magasin, magasin_has_type, type where achat.produit_idProduit=produit.idProduit and produit.magasin_idMagasin=magasin.idMagasin and magasin.idmagasin=magasin_has_type.magasin_idMagasin and magasin_has_type.type_idType=type.idType and client_idClient= " + clientIdClient);
//        while (myRs.next()) {
//            int idType = myRs.getInt("idType");
//            String designation = myRs.getString("designation");
//            Type T = new Type(idType, designation);
//
//            listTypebyId.add(T);
//
//        }
//        return listTypebyId;
//    }
        public static void insertClientHasProfile(int clientIdClient, int idProfile) throws SQLException {
        PreparedStatement myStmt = null;
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert client_has_profile (client_idClient, Profile_idProfile) values (?,?)");
        myStmt.setInt(1, clientIdClient);
        myStmt.setInt(2, idProfile);
        myStmt.executeUpdate();
        myStmt.close();

    }
        public synchronized static void insertClientHasProfile(Connection c, int clientIdclient, int idProfile) throws SQLException {
        PreparedStatement myStmt = null;
        myStmt = c.prepareStatement("insert client_has_profile (client_idClient, Profile_idProfile) values (?,?)");
        //request
        myStmt.setInt(1, clientIdclient);
        myStmt.setInt(2, idProfile);
        myStmt.executeUpdate();
        myStmt.close();

    }

    public int totalQteByIdClient(int clientIdClient) throws SQLException {
        Connection myConn = Database.getConnection();
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select sum(achat.qte) as qtetotal from achat, produit, magasin, magasin_has_type, type where achat.produit_idProduit=produit.idProduit and produit.magasin_idMagasin=magasin.idMagasin and magasin.idmagasin=magasin_has_type.magasin_idMagasin and magasin_has_type.type_idType=type.idType and client_idClient= " + clientIdClient);
        while (myRs.next()) {
            int qteTotal = myRs.getInt("qtetotal");
            return qteTotal;

        }
        return 0;
    }
    public Map<Integer, Integer> QtebyIdType(int clientIdClient) throws SQLException {
        HashMap<Integer, Integer> listQteId = new HashMap<Integer, Integer>();
        Connection myConn = Database.getConnection();
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select sum(achat.qte) as qtebytype , type.idtype from achat, produit, magasin, magasin_has_type, type where achat.produit_idProduit=produit.idProduit and produit.magasin_idMagasin=magasin.idMagasin and magasin.idmagasin=magasin_has_type.magasin_idMagasin and magasin_has_type.type_idType=type.idType and client_idClient= " + clientIdClient + " group by type.idType");
        while (myRs.next()) {
            int qte = myRs.getInt("qtebytype");
            int idtype = myRs.getInt("idtype");

            listQteId.put(qte, idtype);

        }
        return listQteId;
    }

    public int algo() throws SQLException {
        List<Customer> listc = DAOCustomer.loadCustomer(Database.getConnection());
        Map<Float, Integer> listRatio = new HashMap<Float, Integer>();
        Map<Float, Integer> listValeur = new HashMap<Float, Integer>();
        for (Customer customer : listc) {
            int somme = totalQteByIdClient(customer.getIdClient());
            System.out.println(somme);

            Map M = QtebyIdType(customer.getIdClient());
            Set set = M.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
                System.out.print("key is: " + mentry.getKey() + " & Value is: " + mentry.getValue());

                String valeurstring = mentry.getKey().toString();
                String idtypestring = mentry.getValue().toString();

                int valeur = Integer.parseInt(valeurstring);
                int idtype = Integer.parseInt(idtypestring);
                float ratio = (float) (100.0 * valeur / somme);
                System.out.println(ratio);
                listRatio.put(ratio, idtype);
                float max = 0;
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < listRatio.size(); j++) {
                        if (ratio > max) {
                            max = ratio;

                        }
                        listValeur.put(max, idtype);
                    }
                }
                if (max > 10.0 && max < 50.0) {

                    LinkProfile.insertClientHasProfile(customer.getIdClient(), idtype);
                }
                if (max > 50.0 && max < 80.0) {
                    LinkProfile.insertClientHasProfile(customer.getIdClient(), idtype + 100); //ajouter ++
                }
                if (max > 80.0) {
                    LinkProfile.insertClientHasProfile(customer.getIdClient(), idtype + 1000); //ajouter +++
                }

            }
        }
        return 0;
    }

//    public static void main(String[] args) throws Exception {
//        LinkProfile a = new LinkProfile();
//        System.out.println(a.algo());
//        System.out.println(a.totalQteByIdClient(2));
//        System.out.println(a.QtebyIdType(2));
//    }
}
