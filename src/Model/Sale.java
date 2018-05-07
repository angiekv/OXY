/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Michel
 */
public class Sale {
    
    private int ida;
    private int idp;
    private int qte;
    private int idc;

    public Sale(int ida, int idp, int qte, int idc) {
        this.ida = ida;
        this.idp = idp;
        this.qte = qte;
        this.idc = idc;
    }

    public int getIda() {
        return ida;
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
        return "Sale{" + "ida=" + ida + ", idp=" + idp + ", qte=" + qte + ", idc=" + idc + '}';
    }    
    
    
}
