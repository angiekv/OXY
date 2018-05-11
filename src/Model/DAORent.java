/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BaDiakite
 */
public class DAORent {

    private ClientSocket c;

    public DAORent(ClientSocket c) {
        this.c = c;
    }
    
     public List<Rent> loadRent() throws IOException {
        // list of stores
        List<Rent> listRent = new ArrayList<>();

        Map<String, String> MapRent = new HashMap<String, String>();
        MapRent.put("actionType", "listRent");
        Json j = new Json();
        String json = j.serialization(MapRent);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAORent.class.getName()).log(Level.SEVERE, null, ex);
        }
        Type listType = new TypeToken<List<Rent>>() {
        }.getType();
        listRent = j.deSerialization(answer, listType);
        return listRent;
    }
     
     
     public void calcul() throws IOException{

        Map<String, String> MapRent = new HashMap<String, String>();
        MapRent.put("actionType", "calcul");
        Json j = new Json();
        String json = j.serialization(MapRent);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
