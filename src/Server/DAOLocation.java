/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angelique
 */
public class DAOLocation {

    private ConnectionPool pool;

    public DAOLocation() {
        this.pool = new ConnectionPool();
    }
    
    public void init() {
        this.pool.initPool();
    }
    
    public List<Location> getLocations() throws SQLException {
        Connection c = pool.getConnection();
        List<Location> locationList = new ArrayList<>();
        //The query which selects all locations
        try (Statement myStmt = c.createStatement()) {
            //The query which selects all the shops matching the profile
            ResultSet myRs = myStmt.executeQuery("select * from emplacement");
            //Loop which adds a shop to the list.
            while (myRs.next()) {
                int idEmplacement = myRs.getInt("idEmplacement");
                int superficie = myRs.getInt("superficie");
                int loyer_initial = myRs.getInt("loyer_initial");
                String localisation = myRs.getString("localisation");
                int qualite = myRs.getInt("qualite");
                int niveau = myRs.getInt("niveau");
                int freqth = myRs.getInt("freqth");
                Location l = new Location(idEmplacement,superficie,loyer_initial,localisation,qualite,niveau,freqth);
                locationList.add(l);
            }
        }
        return locationList;
    }
}
