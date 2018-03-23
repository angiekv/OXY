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
public class Achat {
    private int idAchat;
    private int qte;
    private int idProduit;
    private int idClient;
    private Date date;

    public Achat(int idAchat, int qte, int idProduit, int idClient, Date date) {
        this.idAchat = idAchat;
        this.qte = qte;
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.date = date;
    }

    public int getIdAchat() {
        return idAchat;
    }

    public int getQte() {
        return qte;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public int getIdClient() {
        return idClient;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Achat{" + "idAchat=" + idAchat + ", qte=" + qte + ", idProduit=" + idProduit + ", idClient=" + idClient + ", date=" + date + '}';
    }
}
