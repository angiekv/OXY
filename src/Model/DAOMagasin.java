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
public class DAOMagasin {

    /**
     * This method selects all the shops and puts the shops into a list.
     *
     * @return the list of all the shops.
     * @throws SQLException
     */
    private ClientSocket c;

    public DAOMagasin(ClientSocket c) {
        this.c = c;
        c.startConnection();
    }

    public List<Magasin> chargeMagasin() throws SQLException {
        //List containing all the shops.
        List<Magasin> listMagasin = new ArrayList<>();
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("typeAction", "listeMagasin");

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(myMap);

//        String message = "listeMagasin";
        String reponse = c.sendAndRecieve(json);
        Type listType = new TypeToken<List<Magasin>>() {
        }.getType();
        Gson g = new Gson();
        listMagasin = g.fromJson(reponse, listType);
        return listMagasin;

    }

    /**
     * This method updates a shop.
     *
     * @param idMagasin
     * @param designation
     * @param description
     * @param idType
     * @throws SQLException
     */
    public void modifierMagasin(int idMagasin, String designation, String description, int idType) throws SQLException {
        Map<String, String> myMap = new HashMap<>();
        myMap.put("typeAction", "modifMagasin");
        myMap.put("idMagasin", Integer.toString(idMagasin));
        myMap.put("designation", designation);
        myMap.put("description", description);
        myMap.put("idType", Integer.toString(idType));
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(myMap);

        String reponse = c.sendAndRecieve(json);

    }

    /**
     * This method deletes a shop from the database.
     *
     * @param idMagasin
     * @throws SQLException
     */
    public void supprimerMagasin(int idMagasin) throws SQLException {
        //Connection to the database.
        System.out.println("okkkk");
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("typeAction", "supprimer");
        myMap.put("idMagasin", Integer.toString(idMagasin));

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(myMap);

//        String message = "listeMagasin";
        String repS = c.sendAndRecieve(json);
    }

    /**
     * This method adds a shop to the database
     *
     * @param designation
     * @param description
     * @param idType
     */
    public void AjouterMagasin(String designation, String description, int idType) {

        Map<String, String> myMap = new HashMap<>();
        myMap.put("typeAction", "add");
        myMap.put("designation", designation);
        myMap.put("description", description);
        myMap.put("idType", Integer.toString(idType));
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(myMap);

        String reponse = c.sendAndRecieve(json);

    }

}
