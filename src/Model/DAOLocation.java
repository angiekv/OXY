/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michel
 */
public class DAOLocation {
    
    private ClientSocket c;
        public DAOLocation(ClientSocket c) {
        this.c = c;
        c.startConnection();
    }
    
   public List<Location> loadLocationAndStores() throws IOException {
        // list of Location
        List<Location> listLocation = new ArrayList<>();

        Map<String, String> MapLocation = new HashMap<String, String>();
        MapLocation.put("actionType", "listLocationAndStore");
        Json j = new Json();
        String json = j.serialization(MapLocation);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOLocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        Type listType = new TypeToken<List<Location>>() {
        }.getType();
        listLocation = j.deSerialization(answer, listType);
        return listLocation;
    }
    /**
     * This method affect all the store to a location 
     * @throws IOException 
     */
    public void affectStoreToLocation() throws IOException{
        Map<String, String> MapLocation = new HashMap<>();
        MapLocation.put("actionType", "affectStoreToLocation");
        Json j = new Json();
        String json = j.serialization(MapLocation);
        String answer= null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(answer);
    }
   
    public static void main(String[] args) throws IOException {
        ClientSocket client = new ClientSocket();
        DAOLocation dao = new DAOLocation(client);
//        dao.loadStores();
    }


}

