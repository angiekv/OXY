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
public class Frequentation {
    private Date date;
    private int idMagasin;
    private int idEmplacement;
    private int nbEntree;

    public Frequentation(Date date, int idMagasin, int idEmplacement, int nbEntree) {
        this.date = date;
        this.idMagasin = idMagasin;
        this.idEmplacement = idEmplacement;
        this.nbEntree = nbEntree;
    }

    public Date getDate() {
        return date;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public int getIdEmplacement() {
        return idEmplacement;
    }

    public int getNbEntree() {
        return nbEntree;
    }

    @Override
    public String toString() {
        return "Frequentation{" + "date=" + date + ", idMagasin=" + idMagasin + ", idEmplacement=" + idEmplacement + ", nbEntree=" + nbEntree + '}';
    }
}
