/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Model.Customer;
import Server.Store;
import Model.Profile;
import Server.DAOStore;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public List<Store> getShops() {
        return shops;
    }

    public void setShops(List<Store> shops) {
        this.shops = shops;
    }

    public List<Location> getLocations() throws SQLException {
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
    
    public static void main(String[] args) {
        List<Store> shops = new ArrayList<>();
        Store s1 = new Store("Zara");
        shops.add(s1);
        
        List<Location> locations = new ArrayList<>();
        List<Profile> profiles = new ArrayList<>();
        Path p1 = new Path(shops, locations, profiles);
        
        
    }
}
