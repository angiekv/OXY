/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Customer> loadCustomer(){
        //list of customer
        List<Customer> listCustomer = new ArrayList<>();
        
        Map<String, String> MapCustomer = new HashMap<String,String>();
        MapCustomer.put("actionType", "listCustomer");
        
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(MapCustomer);
        
        String answer = c.sendAndRecieve(json);
        Type listType = new TypeToken<List<Customer>>() {
        }.getType();
        Gson g = new Gson();
        listCustomer = g.fromJson(answer, listType);
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
    public void updateCustomer(int idClient, String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe) {
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

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(MapCustomer);

        String answer = c.sendAndRecieve(json);
    }
    
    /**
     * Deletes customer from database
     * @param idClient 
     */
    public void deleteCustomer(int idClient){
        //Connection to the database
        System.out.println("Ok");
        Map<String, String> MapCustomer = new HashMap<>();
        MapCustomer.put("actionType", "deleteCustomer");
        MapCustomer.put("id", Integer.toString(idClient));
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(MapCustomer);
        String answer = c.sendAndRecieve(json);
    }
    
/**
 * Adds customer in database
 * @param designation
 * @param description
 * @param idType 
 */
    public void addCustomer(String nom, String prenom, String adresse, String cp, String ville, String mail, String sexe){
        Map<String, String> MapCustomer = new HashMap<>();
        MapCustomer.put("actionType", "addCustomer");
        MapCustomer.put("nom", nom);
        MapCustomer.put("prenom", prenom);
        MapCustomer.put("adresse", adresse);
        MapCustomer.put("ville", ville);
        MapCustomer.put("cp", cp);
        MapCustomer.put("mail", mail);
        MapCustomer.put("sexe", sexe);

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(MapCustomer);

        String answer = c.sendAndRecieve(json);
    }
    
    public static void main(String[] args) {
        ClientSocket client = new ClientSocket();
        DAOCustomer d = new DAOCustomer(client);
        List<Customer > l =d.loadCustomer();
        System.out.println(l.size());
    }
}

