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
public class DAOReturnprovider {
    
    private ClientSocket c;

    public DAOReturnprovider(ClientSocket c) {
        this.c = c;
    }
    
    public void addReturnprovider(int idp, int qte, int idf) throws IOException {
        Map<String, String> MapReturnprovider = new HashMap<>();
        MapReturnprovider.put("actionType", "addReturnprovider");
        MapReturnprovider.put("Produit_idProduit", Integer.toString(idp));
        MapReturnprovider.put("qte", Integer.toString(qte));
        MapReturnprovider.put("Fournisseur_idFournisseur", Integer.toString(idf));

        Json j = new Json();
        String json = j.serialization(MapReturnprovider);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
