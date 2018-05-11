/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BaDiakite
 */
public class DAOEmpHasMag {
    
        public static List<EmpHasMag> loadEmpHasMag(Connection c) throws SQLException, IOException {
        List<EmpHasMag> lesEmps = new ArrayList<>();
        Statement req;
        req = c.createStatement();
        String nomMag;
        String localisation;
        int redevance;
        Date date;

        ResultSet myRs = req.executeQuery("SELECT magasin.designation,  emplacement.localisation, redevance, date FROM emplacement_has_magasin\n"
                + "join magasin on idMagasin = Magasin_idMagasin\n"
                + "join emplacement on idEmplacement = Emplacement_idEmplacement\n"
                + " ;");
        
        while (myRs.next()){
            nomMag = myRs.getString("designation");
            localisation = myRs.getString("localisation");
            redevance = myRs.getInt("redevance");
            date = myRs.getDate("date");
            EmpHasMag M =new EmpHasMag(nomMag, localisation, date, redevance);
            lesEmps.add(M);
        }
//        Loyer.facture();
        return lesEmps;

    }
        
    public static void main(String [] args ) throws SQLException, IOException {
//        DAOEmpHasMag d = new DAOEmpHasMag(Database.getConnection());
//        List<EmpHasMag> Emps =loadEmpHasMag(Database.getConnection());
//        System.out.println(Emps);

    }
}
