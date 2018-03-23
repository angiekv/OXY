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
public class Parcours {
    private int idParcours;
    private int idClient;

    public Parcours(int idParcours, int idClient) {
        this.idParcours = idParcours;
        this.idClient = idClient;
    }

    public int getIdParcours() {
        return idParcours;
    }

    public int getIdClient() {
        return idClient;
    }

    @Override
    public String toString() {
        return "Parcours{" + "idParcours=" + idParcours + ", idClient=" + idClient + '}';
    }
}
