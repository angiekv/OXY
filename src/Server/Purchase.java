/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.Date;

/**
 *
 * @author Mario
 */
public class Purchase {
   private int idAchat;
   private int qte;
   private Date date;
   private int idproduit;
   private int idclient;

    public Purchase(int idAchat, int qte, Date date, int idproduit, int idclient) {
        this.idAchat = idAchat;
        this.qte = qte;
        this.date = date;
        this.idproduit = idproduit;
        this.idclient = idclient;
    }

    public int getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(int idAchat) {
        this.idAchat = idAchat;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    @Override
    public String toString() {
        return "Purchase{" + "idAchat=" + idAchat + ", qte=" + qte + ", date=" + date + ", idproduit=" + idproduit + ", idclient=" + idclient + '}';
    }
}
