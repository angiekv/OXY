/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
    
    public void addSale(int idp, int qte, int idc) throws IOException {
        Map<String, String> MapSale = new HashMap<>();
        MapSale.put("actionType", "addSale");
        MapSale.put("produit_idProduit", Integer.toString(idp));
        MapSale.put("qte", Integer.toString(qte));
        MapSale.put("client_idClient", Integer.toString(idc));

        Json j = new Json();
        String json = j.serialization(MapSale);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
