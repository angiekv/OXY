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
    
    public Path() throws SQLException{
        DAOStore daoShop = new DAOStore();
        ConnectionPool pool = new ConnectionPool();
        Connection c = pool.getConnection();
        this.shops = daoShop.loadStores(c, "vetement mixte");
        this.locations = locations;
        this.profiles = profiles;
        pool.releaseConnection(c);
    }

    public List<Store> getShops() {
        return shops;
    }

    public void setShops(List<Store> shops) {
        this.shops = shops;
    }

    public List<Location> getLocations() {
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

    public void generatePath(){
        
    }
    
//    public static void main (String [] args){
//        Magasin zara = new Magasin(1,"truc","truc",1);
//        Magasin hm = new Magasin(2, "vetement", "mixte", 1);
//        ArrayList<Magasin> magasins = new ArrayList<Magasin>();
//        magasins.add(zara);
//        magasins.add(hm);
//        
//        Profile vetement = new Profile(1,"vetement");
//        System.out.println("magasins" + magasins);
//
//    }
    
}
