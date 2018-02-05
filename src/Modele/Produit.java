package Modele;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mario
 */
public class Produit {
    private int idProduit;
    private String libelle;
    private float prix;
    private int quantite;
    private int idMagasin;

    public Produit(int idProduit, String libelle, float prix, int quantite, int idMagasin) {
        this.idProduit = idProduit;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.idMagasin = idMagasin;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public float getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProduit=" + idProduit + ", libelle=" + libelle + ", prix=" + prix + ", quantite=" + quantite + ", idMagasin=" + idMagasin + '}';
    }
    
}
