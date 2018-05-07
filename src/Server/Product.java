/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Michel
 */
public class Product {
    
    private int idProduit;
    private String libelle;
    private Float prix;
    private int qte;
    private int Magasin_idMagasin;

    public Product(int idProduit, String libelle, Float prix, int qte, int Magasin_idMagasin) {
        this.idProduit = idProduit;
        this.libelle = libelle;
        this.prix = prix;
        this.qte = qte;
        this.Magasin_idMagasin = Magasin_idMagasin;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public Float getPrix() {
        return prix;
    }

    public int getQte() {
        return qte;
    }

    public int getMagasin_idMagasin() {
        return Magasin_idMagasin;
    }

    @Override
    public String toString() {
        return "Product{" + "idProduit=" + idProduit + ", libelle=" + libelle + ", prix=" + prix + ", qte=" + qte + ", Magasin_idMagasin=" + Magasin_idMagasin + '}';
    }
    
    
}
