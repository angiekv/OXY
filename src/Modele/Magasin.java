/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author aberkane
 */
public class Magasin {
    private int num ;
    private String nom;
    private String description;
    private int idType;

    public Magasin(int num, String nom, String description, int idType) {
        this.num = num;
        this.nom = nom;
        this.description = description;
        this.idType = idType;
    }

    public int getNum() {
        return num;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getIdType() {
        return idType;
    }

    @Override
    public String toString() {
        return "Magasin{" + "num=" + num + ", nom=" + nom + ", description=" + description + ", idType=" + idType + '}';
    }
    
    
   

    
    
}
