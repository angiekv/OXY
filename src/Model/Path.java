/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Server.*;
import Model.Customer;
import Server.Store;
import Model.Profile;
import Server.DAOStore;
import static Server.DAOStore.loadStores;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Angelique
 */

public class Path {
    private List<Store> shops;
    private List<Location> locations;
    private List<Profile> profiles;
    
    public Path(List<Store> shops, List<Location> locations, List<Profile> profiles){
        this.shops = shops;
        this.locations = locations;
        this.profiles = profiles;
    }
//    public Path() throws SQLException{
//        DAOStore daoShop = new DAOStore();
//        ConnectionPool pool = new ConnectionPool();
//        Connection c = pool.getConnection();
//        this.shops = daoShop.loadStores(c, "mode");
//        this.locations = locations;
//        this.profiles = profiles;
//        pool.releaseConnection(c);
//    }

    public List<Store> getShops() throws SQLException {
        DAOStore daoShop = new DAOStore();
        ConnectionPool pool = new ConnectionPool();
        Connection c = pool.getConnection();
        this.shops = loadStores(c, "mode");
        pool.releaseConnection(c);
        return shops;
    }

    public void setShops(List<Store> shops) {
        this.shops = shops;
    }

    public List<Location> getLocations() {
        ConnectionPool pool = new ConnectionPool();
        Connection c = pool.getConnection();
        //               List<Model.Store> listStore = new ArrayList<>();
//
//        Map<String, String> MapStore = new HashMap<String, String>();
//        MapStore.put("actionType", "listMagasin");
//        Model.Json j = new Model.Json();
//        String json = j.serialization(MapStore);
//        String answer = null;
//        try {
//            answer = c.sendAndRecieve(json);
//        } catch (IOException ex) {
//            Logger.getLogger(Model.DAOStore.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        java.lang.reflect.Type listType = new TypeToken<List<Model.Store>>() {
//        }.getType();
//        listStore = j.deSerialization(answer, listType);
//        return listStore; 
        pool.releaseConnection(c);
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    } 
}
