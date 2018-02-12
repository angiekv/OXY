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
public class PanneauxPub {
    private int idPanneauxPub;
    private String Localisation;

    public PanneauxPub(int idPanneauxPub, String Localisation) {
        this.idPanneauxPub = idPanneauxPub;
        this.Localisation = Localisation;
    }

    public int getIdPanneauxPub() {
        return idPanneauxPub;
    }

    public String getLocalisation() {
        return Localisation;
    }

    @Override
    public String toString() {
        return "PanneauxPub{" + "idPanneauxPub=" + idPanneauxPub + ", Localisation=" + Localisation + '}';
    }
}
