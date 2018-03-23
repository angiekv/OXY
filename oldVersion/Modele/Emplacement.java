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
public class Emplacement {
    private int idEmplacement;
    private int superficie;
    private float loyer_initial;
    private String localisation;
    private int qualite;

    public Emplacement(int idEmplacement, int superficie, float loyer_initial, String localisation, int qualite) {
        this.idEmplacement = idEmplacement;
        this.superficie = superficie;
        this.loyer_initial = loyer_initial;
        this.localisation = localisation;
        this.qualite = qualite;
    }

    public int getIdEmplacement() {
        return idEmplacement;
    }

    public int getSuperficie() {
        return superficie;
    }

    public float getLoyer_initial() {
        return loyer_initial;
    }

    public String getLocalisation() {
        return localisation;
    }

    public int getQualite() {
        return qualite;
    }

    @Override
    public String toString() {
        return "Emplacement{" + "idEmplacement=" + idEmplacement + ", superficie=" + superficie + ", loyer_initial=" + loyer_initial + ", localisation=" + localisation + ", qualite=" + qualite + '}';
    }
    
}
