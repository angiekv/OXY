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
public class Returnprovider {
    
    private int idrf;
    private int idp;
    private int qte;
    private int idf;

    public Returnprovider(int idrc, int idp, int qte, int idf) {
        this.idrf = idrc;
        this.idp = idp;
        this.qte = qte;
        this.idf = idf;
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

    public int getIdc() {
        return idf;
    }

    @Override
    public String toString() {
        return "Returnprovider{" + "idrf=" + idrf + ", idp=" + idp + ", qte=" + qte + ", idf=" + idf + '}';
    }


    
    
}
