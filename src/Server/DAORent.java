/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
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
 * @author BaDiakite
 */
public class DAORent {

    public synchronized static List<Rent> loadRent(Connection c) throws SQLException {
        List<Rent> lesRents = new ArrayList<Rent>();
        Statement req;
        Statement req2;
        Statement req3;
        req = c.createStatement();
        req2 = c.createStatement();
        req3 = c.createStatement();

        int idE;
        int loyerini;
        int freqini;
        int idM;
        int freq;
        Date date;

        ResultSet myRs = req.executeQuery("select distinct idEmplacement, loyer_initial, freqth "
                + "from emplacement");

        while (myRs.next()) {
            idE = myRs.getInt("idEmplacement");
            loyerini = myRs.getInt("loyer_initial");
            freqini = myRs.getInt("freqth");

            ResultSet myRs2 = req2.executeQuery("select distinct Magasin_idMagasin from emplacement_has_magasin where "
                    + "Emplacement_idEmplacement = " + idE);
            while (myRs2.next()) {
                idM = myRs2.getInt("Magasin_idMagasin");

                ResultSet myRs3 = req3.executeQuery("select distinct nb_entree, date from frequentation where Emplacement_has_Magasin_Emplacement_idEmplacement = " + idE + " and "
                        + "Emplacement_has_Magasin_Magasin_idMagasin = " + idM);
                while (myRs3.next()) {
                    freq = myRs3.getInt("nb_entree");
                    date = myRs3.getDate("date");
                    lesRents.add(new Rent(idM, idE, freqini, loyerini, freq, date));
                }
            }

        }

        return lesRents;

    }
    
    public static void update(int id, float redevance, Connection c) throws SQLException{
        PreparedStatement myRs = c.prepareStatement("update emplacement_has_magasin set redevance = ? "
                    + " , date = NOW() where Magasin_idMagasin = ?");
            myRs.setFloat(1, redevance);
            myRs.setInt(2, id);
            myRs.executeUpdate();
            myRs.close();
    }
    

    public static void calcul(List<Rent> lesRents, Connection c) throws SQLException {
        float redevance;
        float coef;
        int id;
        for (Rent r : lesRents) {
            coef = r.getFreq() / r.getFrequentationini();
            if (coef > 1) {
                r.setRedevance(r.getLoyerini() * coef);
                redevance = r.getLoyerini() * coef;
            } else {
                r.setRedevance(r.getLoyerini());
                redevance = r.getLoyerini();
            }
            id = r.getIdMag();
            redevance = (float) (redevance * (1.2)) ;
            update(id, redevance, c);
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        List<Rent> l = DAORent.loadRent(Database.getConnection());
        System.out.println(l.size());
        calcul(l, Database.getConnection());
    }

}
