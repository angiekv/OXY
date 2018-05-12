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

    public void algo(Connection c) throws SQLException {
        List<Customer> listC = DAOCustomer.loadCustomer(c);
        for (Customer customer : listC) {
            int somme = DAOCustomer.totalQteByIdClient(c,customer.getIdClient());
            System.out.println(somme);

            Map<Integer, Integer> M = DAOCustomer.qteByIdType(c,customer.getIdClient());
            Map<Float, Integer> newMap = bestIdType(c,M, somme, customer.getIdClient());
            link(c,newMap, customer.getIdClient());
        }
    }

    public Map<Float, Integer> bestIdType(Connection c,Map<Integer, Integer> M, int somme, int idClient) {
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

    public void link(Connection c, Map<Float, Integer> newMap, int idClient) throws SQLException {
        Set set = newMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("key is: " + mentry.getKey() + " & Value is: " + mentry.getValue());
            
            String idtypestring = mentry.getValue().toString();
            float max = (float) mentry.getKey();
            
            int idtype = Integer.parseInt(idtypestring);

            if (max > 10.0 && max < 50.0) {
                DAOCustomer.insertClientHasProfile(c,idClient, idtype);
            }
            if (max >= 50.0 && max < 80.0) {
                DAOCustomer.insertClientHasProfile(c,idClient, idtype + 100); //ajouter ++
            }
            if (max > 80.0) {
                DAOCustomer.insertClientHasProfile(c,idClient, idtype + 1000); //ajouter +++
            }

        }
    }

//    public static void main(String[] args) throws Exception {
//        AffectProfile a = new AffectProfile();
//        ConnectionPool pool = new ConnectionPool();
//        pool.initPool();
//        Connection c = pool.getConnection();
//        a.algo(c);
//    }
//}
}
