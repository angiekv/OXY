package Modele;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mario
 */
public class BornePub {
    private int idBornepub;
    private String Localisation;

    public BornePub(int idBornepub, String Localisation) {
        this.idBornepub = idBornepub;
        this.Localisation = Localisation;
    }

    public int getIdBornepub() {
        return idBornepub;
    }

    public String getLocalisation() {
        return Localisation;
    }

    @Override
    public String toString() {
        return "BornePub{" + "idBornepub=" + idBornepub + ", Localisation=" + Localisation + '}';
    }
}
