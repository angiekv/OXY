/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Michel
 */
public class Histo {
    private int ida;
    private int idb ;
    private int idrc ;
    private int idrf ;
    private int idp ;
    private int qte ;
    private Date date;
    private String action; 

    public Histo(int ida, int idb, int idrc, int idrf, int idp, int qte, Date date, String action) {
        this.ida = ida;
        this.idb = idb;
        this.idrc = idrc;
        this.idrf = idrf;
        this.idp = idp;
        this.qte = qte;
        this.date = date;
        this.action = action;
    }

    public int getIda() {
        return ida;
    }

    public int getIdb() {
        return idb;
    }

    public int getIdrc() {
        return idrc;
    }

    public int getIdrf() {
        return idrf;
    }

    public int getIdp() {
        return idp;
    }

    public int getQte() {
        return qte;
    }

    public Date getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Histo{" + "ida=" + ida + ", idb=" + idb + ", idrc=" + idrc + ", idrf=" + idrf + ", idp=" + idp + ", qte=" + qte + ", date=" + date + ", action=" + action + '}';
    }

    
    
}
