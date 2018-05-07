/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author BaDiakite
 */
public class DAORent {

    public static List<Rent> loadRent(Connection c) throws SQLException {
        List<Rent> lesRents = new ArrayList<Rent>();
        Statement req;
        Statement req2 = null;
        Statement req3 = null;
        req = c.createStatement();

        int idE;
        int loyerini;
        int freqini;
        int idM;
        int freq;
        Date date;

        ResultSet myRs = req.executeQuery("select distinct idEmplacement, loyer_initial, frequentation_initiale "
                + "from emplacement");

        while (myRs.next()) {
            idE = myRs.getInt("idEmplamcement");
            loyerini = myRs.getInt("loyer_initial");
            freqini = myRs.getInt("frequentation_initial");
            
            ResultSet myRs2 = req2.executeQuery("select distinct Magasin_idMagasin from emplacement_has_magasin where "
                    + "Emplamcement_idEmplacement = " + idE);
            while (myRs2.next()) {
                idM = myRs2.getInt("Magasin_idMagasin");
                
                ResultSet myRs3 = req3.executeQuery("select distinct nb_entree, date from frequentation where Emplacement_has_Magasin_Emplacement_idEmplacement = " + idE + " and"
                        + "Emplacement_has_Magasin_Magasin_idMagasin = " + idM);
                while (myRs2.next()) {
                    freq = myRs3.getInt("n_entree");
                    date = myRs3.getDate("date");
                    lesRents.add(new Rent(idE, idM, freqini, loyerini, freq, date));
                }
            }
        }

        return lesRents;

    }
}
