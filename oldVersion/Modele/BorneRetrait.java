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
public class BorneRetrait {
    private int idBorneretrait;
    private String Localisation;

    public BorneRetrait(int idBorneretrait, String Localisation) {
        this.idBorneretrait = idBorneretrait;
        this.Localisation = Localisation;
    }

    public int getIdBorneretrait() {
        return idBorneretrait;
    }

    public String getLocalisation() {
        return Localisation;
    }

    @Override
    public String toString() {
        return "BorneRetrait{" + "idBorneretrait=" + idBorneretrait + ", Localisation=" + Localisation + '}';
    }
}
