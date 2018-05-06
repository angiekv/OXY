/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author OXY
 */
public class Shop {
    private int id ;
    private String designation;
    private String description;
    private int loyer;
    private int superficie; 
    private int niveau;
    private String localisation;
   
    
    public Shop(int id, String designation, String description, int loyer, int superficie,int niveau ,String localisation) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.loyer = loyer;
        this.superficie = superficie;
        this.niveau = niveau; 
        this.localisation = localisation;
    }

    public int getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDescription() {
        return description;
    }

    public int getLoyer() {
        return loyer;
    }

    public int getSuperficie() {
        return superficie;
    }

    public String getLocalisation() {
        return localisation;
    }

    public int getNiveau() {
        return niveau;
    }

    @Override
    public String toString() {
        return "Magasin{" + "id=" + id + ", designation=" + designation + ", description=" + description + ", loyer=" + loyer + ", superficie=" + superficie + ", localisation=" + localisation + ", niveau=" + niveau + '}';
    }

  


}
