package Server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mario
 */
public class AffectProfile {

    public static void insertClientHasProfile(int clientIdClient, int idProfile) throws SQLException {
        PreparedStatement myStmt = null;
        Connection myConn = Database.getConnection();
        myStmt = myConn.prepareStatement("insert client_has_profile (client_idClient, Profile_idProfile) values (?,?)");
        myStmt.setInt(1, clientIdClient);
        myStmt.setInt(2, idProfile);
        myStmt.executeUpdate();
        myStmt.close();

    }

//    public synchronized static void insertClientHasProfile(Connection c, int clientIdclient, int idProfile) throws SQLException {
//        PreparedStatement myStmt = null;
//        myStmt = c.prepareStatement("insert client_has_profile (client_idClient, Profile_idProfile) values (?,?)");
//        //request
//        myStmt.setInt(1, clientIdclient);
//        myStmt.setInt(2, idProfile);
//        myStmt.executeUpdate();
//
//    }

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
//        public synchronized static Integer totalQteByIdClient(Connection c, int clientIdClient) throws SQLException {
//        Statement myStmt = c.createStatement();
//        ResultSet myRs = myStmt.executeQuery("select sum(achat.qte) as qtetotal from achat, produit, magasin, magasin_has_type, type where achat.produit_idProduit=produit.idProduit and produit.magasin_idMagasin=magasin.idMagasin and magasin.idmagasin=magasin_has_type.magasin_idMagasin and magasin_has_type.type_idType=type.idType and client_idClient = " + clientIdClient);
//        while (myRs.next()) {
//            int qteTotal = myRs.getInt("qtetotal");
//            return qteTotal;
//        }
//        return 0;
//    }

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
//        public synchronized static Map<Integer,Integer> qteByIdType(Connection c, int clientIdClient) throws SQLException {
//        HashMap<Integer, Integer> listQteId = new HashMap<Integer, Integer>();
//        Statement myStmt = c.createStatement();
//        ResultSet myRs = myStmt.executeQuery("select profilename from profile, client_has_profile where profile.idProfile = client_has_profile.Profile_idProfile and client_idClient = " + clientIdClient);
//        while (myRs.next()) {
//            int qte = myRs.getInt("qtebytype");
//            int idtype = myRs.getInt("idtype");
//            
//            listQteId.put(qte, idtype);
//        }
//        return listQteId;
//    }

    public void algo() throws SQLException {
        List<Customer> listC = DAOCustomer.loadCustomer(Database.getConnection());
        for (Customer customer : listC) {
            int somme = totalQteByIdClient(customer.getIdClient());
            System.out.println(somme);

            Map<Integer, Integer> M = QtebyIdType(customer.getIdClient());
            Map<Float, Integer> newMap = bestIdType(M, somme, customer.getIdClient());
            link(newMap, customer.getIdClient());
        }
    }

    public Map<Float, Integer> bestIdType(Map<Integer, Integer> M, int somme, int idClient) {
        Map<Float, Integer> listRatio = new HashMap<Float, Integer>();
        Map<Float, Integer> listValeur = new HashMap<Float, Integer>();
        float max = 0;
        float max2 = 0;
        Set set = M.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();

            String valeurstring = mentry.getKey().toString();
            String idtypestring = mentry.getValue().toString();

            int valeur = Integer.parseInt(valeurstring);
            int idtype = Integer.parseInt(idtypestring);

            float ratio = (float) (100 * valeur / somme);

            listRatio.put(ratio, idtype);
        }

        Set set2 = listRatio.entrySet();
        Iterator iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator2.next();
            System.out.print("key is: " + mentry.getKey() + " & Value is: " + mentry.getValue());

            String idtypestring2 = mentry.getValue().toString();
            float ratio = (float) mentry.getKey();
            
            int idtype = Integer.parseInt(idtypestring2);

            if (ratio != 100) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < M.size(); j++) {
                        if (ratio > max) {
                            max2 = max;
                            max = ratio;
                        } else if (ratio > max2) {
                            max2 = ratio;
                        }
                    }
                    listValeur.put(max, idtype);
                    listValeur.put(max2, idtype);
                }
            } else {
                max = ratio;
                listValeur.put(max, idtype);

            }

        }
        return listValeur;

    }

    public void link(Map<Float, Integer> newMap, int idClient) throws SQLException {
        System.out.println("je suis la");
        Set set = newMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("key is: " + mentry.getKey() + " & Value is: " + mentry.getValue());
            
            String idtypestring = mentry.getValue().toString();
            float max = (float) mentry.getKey();
            
            int idtype = Integer.parseInt(idtypestring);

            if (max > 10.0 && max < 50.0) {

                AffectProfile.insertClientHasProfile(idClient, idtype);
            }
            if (max > 50.0 && max < 80.0) {
                AffectProfile.insertClientHasProfile(idClient, idtype + 100); //ajouter ++
            }
            if (max > 80.0) {
                AffectProfile.insertClientHasProfile(idClient, idtype + 1000); //ajouter +++
            }

        }
    }

    public static void main(String[] args) throws Exception {
        AffectProfile a = new AffectProfile();
        a.algo();
//        System.out.println(a.totalQteByIdClient(2));
//        System.out.println(a.QtebyIdType(2));
    }
}
