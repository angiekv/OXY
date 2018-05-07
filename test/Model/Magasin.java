/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
  
import Model.*;

/**
 *
 * @author OXY
 */
public class Magasin {
    private int id ;
    private String designation;
    private String description;
    private int idType;

    public Magasin(int id, String designation, String description, int idType) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.idType = idType;
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

    public int getIdType() {
        return idType;
    }

    @Override
    public String toString() {
        return "Magasin{" + "id=" + id + ", designation=" + designation + ", description=" + description + ", idType=" + idType + '}';
    }

    
    
    
   

    
    
}
