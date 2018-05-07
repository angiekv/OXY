/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OXY
 */
public class DAOCustomer {
    
    private ClientSocket c;
    
    public DAOCustomer(ClientSocket c){
        this.c = c;
        c.startConnection();
    }
    /**
     * This method selects all customers from database and puts the customers into a list
     * @return the list of all customers
     * @throws SQLException 
     */
    public List<Customer> loadCustomer() throws IOException{
        // list of customer
        List<Customer> listCustomer = new ArrayList<>();
        
        Map<String, String> MapCustomer = new HashMap<String,String>();
        MapCustomer.put("actionType", "listCustomer");
        Json j = new Json();
        String json = j.serialization(MapCustomer);
        String answer= null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Type listType = new TypeToken<List<Customer>>() {
        }.getType();
        listCustomer = j.deSerialization(answer, listType);
        return listCustomer;
    }
    
/**
 * updates customer in database
 * @param idClient
 * @param nom
 * @param prenom
 * @param adresse
 * @param cp
 * @param ville
 * @param mail
 * @param sexe
 * @throws SQLException 
 */
    public void updateCustomer(int idClient, String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe) throws IOException {
        Map<String, String> MapCustomer = new HashMap<>();
        MapCustomer.put("actionType", "updateCustomer");
        MapCustomer.put("idClient", Integer.toString(idClient));
        MapCustomer.put("nom", nom);
        MapCustomer.put("prenom", prenom);
        MapCustomer.put("adresse", adresse);
        MapCustomer.put("ville", ville);
        MapCustomer.put("cp", cp);
        MapCustomer.put("mail", mail);
        MapCustomer.put("sexe", sexe);

        Json j = new Json();
        String json = j.serialization(MapCustomer);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Deletes customer from database
     * @param idClient 
     */
    public void deleteCustomer(int idClient) throws IOException{
        //Connection to the database
        System.out.println("Ok");
        Map<String, String> MapCustomer = new HashMap<>();
        MapCustomer.put("actionType", "deleteCustomer");
        MapCustomer.put("idClient", Integer.toString(idClient));
        Json j = new Json();
        String json = j.serialization(MapCustomer);
        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
/**
 * Adds customer in database
 * @param designation
 * @param description
 * @param idType 
 */
    public void addCustomer(String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe) throws IOException{
        Map<String, String> MapCustomer = new HashMap<>();
        MapCustomer.put("actionType", "addCustomer");
        MapCustomer.put("nom", nom);
        MapCustomer.put("prenom", prenom);
        MapCustomer.put("adresse", adresse);
        MapCustomer.put("ville", ville);
        MapCustomer.put("cp", cp);
        MapCustomer.put("mail", mail);
        MapCustomer.put("sexe", sexe);

        Json j = new Json();
        String json = j.serialization(MapCustomer);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public List<String> loadProfileById(int clientIdClient) throws IOException {
        List<String> listbyid = new ArrayList<>();

        Map<String, String> MapCustomer = new HashMap<String, String>();
        MapCustomer.put("actionType", "listbyid");
        MapCustomer.put("idClient", Integer.toString(clientIdClient));
        Json j = new Json();
        String json = j.serialization(MapCustomer);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Type listType = new TypeToken<List>() {
        }.getType();
        listbyid = j.deSerialization(answer, listType);
        return listbyid;
    }
    
}

