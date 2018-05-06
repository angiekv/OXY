/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Model.Customer;
import Model.Magasin;
import Model.Profile;
import java.util.ArrayList;
import java.util.HashMap;
import Server.Shop;
import Server.Location;
import Server.ConnectionPool;

/**
 *
 * @author Angelique
 */

public class Path {
    private ArrayList<Shop> shops;
    private ArrayList<Location> locations;
    private ArrayList<Profile> profiles;
    
    public Path(ArrayList<Shop> shops,ArrayList<Location> locations,ArrayList<Profile> profiles){
        this.shops = shops;
        this.locations = locations;
        this.profiles = profiles;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<Profile> profiles) {
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
