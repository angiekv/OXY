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
public class DAOHisto {
    
    private ClientSocket c;

    public DAOHisto(ClientSocket c) {
        this.c = c;
    }

    public List<Histo> loadHistoProduct(int idh) throws IOException {
        List<Histo> listHisto = new ArrayList<>();
        // list of histo
        Map<String, String> MapHisto = new HashMap<String, String>();
        MapHisto.put("idProduit", Integer.toString(idh));
        MapHisto.put("actionType", "listHisto");
        Json j = new Json();
        String json = j.serialization(MapHisto);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOHisto.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.lang.reflect.Type listType = new TypeToken<List<Histo>>() {
        }.getType();
        listHisto = j.deSerialization(answer, listType);
        return listHisto;
    }

}


