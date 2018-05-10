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
 * @author Michel
 */
public class DAOType {
    
    private ClientSocket c;
        public DAOType(ClientSocket c) {
        this.c = c;
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    public List<TypeStore> loadTypeStore() throws IOException {
        // list of stores
        List<TypeStore> listTypeStore = new ArrayList<>();

        Map<String, String> MapType = new HashMap<String, String>();
        MapType.put("actionType", "listeTypeStore");
        Json j = new Json();
        String json = j.serialization(MapType);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOType.class.getName()).log(Level.SEVERE, null, ex);
        }
        Type listType = new TypeToken<List<TypeStore>>() {
        }.getType();
        listTypeStore = j.deSerialization(answer, listType);
        return listTypeStore;
    }


}

