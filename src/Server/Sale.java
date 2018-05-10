/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Date;

/**
 *
 * @author Michel
 */
public class Sale {
    
    private int ida;
    private int idp;
    private Date date;
    private int qte;
    private int idc;

    public Sale(int ida, int idp, Date date, int qte, int idc) {
        this.ida = ida;
        this.idp = idp;
        this.date = date;
        this.qte = qte;
        this.idc = idc;
    }
    public Sale(int ida, int idp, int qte, int idc) {
        this.ida = ida;
        this.idp = idp;
        this.qte = qte;
        this.idc = idc;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    @Override
    public String toString() {
        return "Sale{" + "ida=" + ida + ", idp=" + idp + ", date=" + date + ", qte=" + qte + ", idc=" + idc + '}';
    }
}
