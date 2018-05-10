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

    public void deleteStore(int idStore) throws IOException {
        Map<String, String> MapStore = new HashMap<>();
        MapStore.put("actionType", "deleteStore");
        MapStore.put("idStore", Integer.toString(idStore));
        Json j = new Json();
        String json = j.serialization(MapStore);
        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStore(int idShop, String designation, String description, int loyer, int surface, int niveau, String localisation, List<Integer> ListidTypeOld, List<String> ListidTypeNew) throws IOException {
        Map<String, String> MapStore = new HashMap<>();
        MapStore.put("actionType", "updateStore");
        MapStore.put("idShop", Integer.toString(idShop));
        MapStore.put("designation", designation);
        MapStore.put("niveau", Integer.toString(niveau));
        MapStore.put("description", description);
        MapStore.put("loyer", Integer.toString(loyer));
        MapStore.put("surface", Integer.toString(surface));
        MapStore.put("localisation", localisation);
        MapStore.put("oldType1", Integer.toString(ListidTypeOld.get(0)));
        System.out.println(Integer.toString(ListidTypeOld.get(0)));
        MapStore.put("oldType2", Integer.toString(ListidTypeOld.get(1)));
        MapStore.put("newType1", ListidTypeNew.get(0));
        MapStore.put("newType2", ListidTypeNew.get(1));
        
        Json j = new Json();
        String json = j.serialization(MapStore);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void addStore(String designation, String description, int loyer, int surface, int niveau, String localisation,List<String> ListType) throws IOException {
        Map<String, String> MapStore = new HashMap<>();
        MapStore.put("actionType", "addStore");
        MapStore.put("designation", designation);
        MapStore.put("niveau", Integer.toString(niveau));
        MapStore.put("description", description);
        MapStore.put("loyer", Integer.toString(loyer));
        MapStore.put("surface", Integer.toString(surface));
        MapStore.put("localisation", localisation);
        MapStore.put("newType1", ListType.get(0));
        MapStore.put("newType2", ListType.get(1));
        
        Json j = new Json();
        String json = j.serialization(MapStore);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
