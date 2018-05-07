/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class DAOSale {
    
    private ClientSocket c;

    public DAOSale(ClientSocket c) {
        this.c = c;
        c.startConnection();
    }
    
    public synchronized List<Sale> loadSale() throws IOException {
        // list of sales
        List<Sale> listSale = new ArrayList<>();

        Map<String, String> MapSale = new HashMap<String, String>();
        MapSale.put("actionType", "listSale");
        Json j = new Json();
        String json = j.serialization(MapSale);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.lang.reflect.Type listType = new TypeToken<List<Sale>>() {
        }.getType();
        listSale = j.deSerialization(answer, listType);
        return listSale;
    }
    
    public synchronized void addSale(int Produit_idProduit, int qte, int Client_idClient) throws IOException {
        Map<String, String> MapSale = new HashMap<>();
        MapSale.put("actionType", "addSale");
        MapSale.put("Produit_idProduit", Integer.toString(Produit_idProduit));
        MapSale.put("qte", Integer.toString(qte));
        MapSale.put("Client_idClient", Integer.toString(Client_idClient));

        Json j = new Json();
        String json = j.serialization(MapSale);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
