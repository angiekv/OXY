/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Server.*;
import java.util.List;

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
    private List<Type> listIdType;
   

    public Shop(int id, String designation, String description, int loyer, int superficie, int niveau, String localisation, List<Type> listIdType) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.loyer = loyer;
        this.superficie = superficie;
        this.niveau = niveau;
        this.localisation = localisation;
        this.listIdType = listIdType;
    }
    
    public Shop(String designation, String localization) {
        this.designation = designation;
        this.localisation = localization;
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

    public int getNiveau() {
        return niveau;
    }

    public String getLocalisation() {
        return localisation;
    }

    public List<Type> getListIdType() {
        return listIdType;
    }

    @Override
    public String toString() {
        return "Shop{" + "id=" + id + ", designation=" + designation + ", description=" + description + ", loyer=" + loyer + ", superficie=" + superficie + ", niveau=" + niveau + ", localisation=" + localisation + ", listIdType=" + listIdType + '}';
    }


}
