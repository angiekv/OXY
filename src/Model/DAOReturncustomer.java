/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michel
 */
public class DAOReturncustomer {
    
    private ClientSocket c;

    public DAOReturncustomer(ClientSocket c) {
        this.c = c;
    }
    
    public void addReturncustomer(int idp, int qte, int idc) throws IOException {
        Map<String, String> MapReturncustomer = new HashMap<>();
        MapReturncustomer.put("actionType", "addReturncustomer");
        MapReturncustomer.put("Produit_idProduit", Integer.toString(idp));
        MapReturncustomer.put("qte", Integer.toString(qte));
        MapReturncustomer.put("Client_idClient", Integer.toString(idc));

        Json j = new Json();
        String json = j.serialization(MapReturncustomer);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
