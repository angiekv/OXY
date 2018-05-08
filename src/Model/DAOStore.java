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
public class DAOStore {
    
    private ClientSocket c;
        public DAOStore(ClientSocket c) {
        this.c = c;
    }
    
   public List<Store> loadStores() throws IOException {
        // list of stores
        List<Store> listStore = new ArrayList<>();

        Map<String, String> MapStore = new HashMap<String, String>();
        MapStore.put("actionType", "listMagasin");
        Json j = new Json();
        String json = j.serialization(MapStore);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        Type listType = new TypeToken<List<Store>>() {
        }.getType();
        listStore = j.deSerialization(answer, listType);
        return listStore;
    }


}

