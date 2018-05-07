/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
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
public class DAOProduct {

    private ClientSocket c;

    public DAOProduct(ClientSocket c) {
        this.c = c;
        c.startConnection();
    }

    public List<Product> loadProduct() throws IOException {
        // list of product
        List<Product> listProduct = new ArrayList<>();

        Map<String, String> MapProduct = new HashMap<String, String>();
        MapProduct.put("actionType", "listProduct1");
        Json j = new Json();
        String json = j.serialization(MapProduct);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.lang.reflect.Type listType = new TypeToken<List<Product>>() {
        }.getType();
        listProduct = j.deSerialization(answer, listType);
        return listProduct;
    }

    public List<Product> loadProductStore(int idm) throws IOException {
        List<Product> listProduct = new ArrayList<>();
        // list of product
        Map<String, String> MapProduct = new HashMap<String, String>();
        MapProduct.put("idProduit", Integer.toString(idm));
        MapProduct.put("actionType", "listProduct");
        Json j = new Json();
        String json = j.serialization(MapProduct);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.lang.reflect.Type listType = new TypeToken<List<Product>>() {
        }.getType();
        listProduct = j.deSerialization(answer, listType);
        return listProduct;
    }

    public void updateProduct(int idProduit, String libelle, float prix, int qte, int Magasin_idMagasin) throws IOException {
        Map<String, String> MapProduct = new HashMap<>();
        MapProduct.put("actionType", "updateProduct");
        MapProduct.put("idProduit", Integer.toString(idProduit));
        MapProduct.put("libelle", libelle);
        MapProduct.put("prix", Float.toString(prix));
        MapProduct.put("qte", Integer.toString(qte));
        MapProduct.put("Magasin_idMagasin", Integer.toString(Magasin_idMagasin));

        Json j = new Json();
        String json = j.serialization(MapProduct);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProduct(int idProduit) throws IOException {
        //Connection to the database
        System.out.println("Ok");
        Map<String, String> MapProduct = new HashMap<>();
        MapProduct.put("actionType", "deleteProduct");
        MapProduct.put("idProduit", Integer.toString(idProduit));
        Json j = new Json();
        String json = j.serialization(MapProduct);
        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addProduct( String libelle, float prix, int qte, int Magasin_idMagasin) throws IOException {
        Map<String, String> MapProduct = new HashMap<>();
        MapProduct.put("actionType", "addProduct");
        MapProduct.put("libelle", libelle);
        MapProduct.put("prix", Float.toString(prix));
        MapProduct.put("qte", Integer.toString(qte));
        MapProduct.put("Magasin_idMagasin", Integer.toString(Magasin_idMagasin));

        Json j = new Json();
        String json = j.serialization(MapProduct);

        try {
            String answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
