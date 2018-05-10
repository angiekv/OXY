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
public class Order {
    private int idb;
    private int qte;
    private int idp;
    private int idf;

    public Order(int idb, int qte, int idp, int idf) {
        this.idb = idb;
        this.qte = qte;
        this.idp = idp;
        this.idf = idf;
    }

    public int getIdb() {
        return idb;
    }

    public int getQte() {
        return qte;
    }

    public int getIdp() {
        return idp;
    }

    public int getIdf() {
        return idf;
    }

    @Override
    public String toString() {
        return "Order{" + "idb=" + idb + ", qte=" + qte + ", idp=" + idp + ", idf=" + idf + '}';
    }
    
    
}
