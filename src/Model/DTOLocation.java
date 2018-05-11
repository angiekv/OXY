/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelique
 */
public class DTOLocation {
    
    private ClientSocket c;
    
    public DTOLocation(ClientSocket c){
        this.c = c;
    }
    /**
     * This method selects all locations from database and puts them into a list
     * @return the list of all locations
     * @throws SQLException 
     */
    public List<Location> loadLocations() throws IOException{
        // list of location
        List<Location> listLocation = new ArrayList<>();
        Map<String, String> MapLocation = new HashMap<>();
        MapLocation.put("actionType", "listLocation");
        Json j = new Json();
        String json = j.serialization(MapLocation);
        String answer= null;
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
    
    public static void main (String [] args) throws IOException{
        ClientSocket clientSocket = new ClientSocket();
        DTOLocation dtoLoc = new DTOLocation(clientSocket);
        dtoLoc.loadLocations();
    }
}