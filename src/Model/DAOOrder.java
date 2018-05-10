/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
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
public class DAOOrder {
    
    private ClientSocket c;

    public DAOOrder(ClientSocket c) {
        this.c = c;
    }
    
    public void appro(int idb) throws IOException {
        // map of order
        Map<String, String> MapOrder = new HashMap<String, String>();
        MapOrder.put("idBon", Integer.toString(idb));
        MapOrder.put("actionType", "appro");
        Json j = new Json();
        String json = j.serialization(MapOrder);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
