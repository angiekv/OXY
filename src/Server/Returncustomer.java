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
public class Returncustomer {
    
    private int idrc;
    private int idp;
    private int qte;
    private int idc;

    public Returncustomer(int idrc, int idp, int qte, int idc) {
        this.idrc = idrc;
        this.idp = idp;
        this.qte = qte;
        this.idc = idc;
    }

    public int getIdrc() {
        return idrc;
    }

    public int getIdp() {
        return idp;
    }

    public int getQte() {
        return qte;
    }

    public int getIdc() {
        return idc;
    }

    @Override
    public String toString() {
        return "Returncustomer{" + "idrc=" + idrc + ", idp=" + idp + ", qte=" + qte + ", idc=" + idc + '}';
    }
    
    
}
