package Modele;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mario
 */
public class Entree {
    private Date date;
    private int qte;
    private int idProduit;

    public Entree(Date date, int qte, int idProduit) {
        this.date = date;
        this.qte = qte;
        this.idProduit = idProduit;
    }

    public Date getDate() {
        return date;
    }

    public int getQte() {
        return qte;
    }

    public int getIdProduit() {
        return idProduit;
    }

    @Override
    public String toString() {
        return "Entree{" + "date=" + date + ", qte=" + qte + ", idProduit=" + idProduit + '}';
    }
}
