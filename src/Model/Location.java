package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mario
 */
public class Location {
    private int idEmplacement;
    private int superficie;
    private int loyerInitial;
    private String localisation;
    private int qualite;
    private int niveau;
    private int freqTheorique;
    private String designationStoreAffected;
    /**
     * 
     * @param idEmplacement
     * @param superficie
     * @param loyerInitial
     * @param localisation
     * @param qualite
     * @param niveau
     * @param freqTheorique 
     */
    public Location(int idEmplacement, int superficie, int loyerInitial, String localisation, int qualite, int niveau, int freqTheorique) {
        this.idEmplacement = idEmplacement;
        this.superficie = superficie;
        this.loyerInitial = loyerInitial;
        this.localisation = localisation;
        this.qualite = qualite;
        this.niveau = niveau;
        this.freqTheorique = freqTheorique;
    }
    /**
     * 
     * @param idEmplacement
     * @param localisation
     * @param niveau
     * @param designationStoreAffected 
     */
    public Location(int idEmplacement,String localisation, int niveau, String designationStoreAffected) {
        this.idEmplacement = idEmplacement;
        this.localisation = localisation;
        this.niveau = niveau;
        this.designationStoreAffected = designationStoreAffected;
    }

    public int getIdEmplacement() {
        return idEmplacement;
    }

    public int getSuperficie() {
        return superficie;
    }

    public int getLoyerInitial() {
        return loyerInitial;
    }

    public String getLocalisation() {
        return localisation;
    }

    public int getQualite() {
        return qualite;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getFreqTheorique() {
        return freqTheorique;
    }

    public String getDesignationStoreAffected() {
        return designationStoreAffected;
    }

    @Override
    public String toString() {
        return "Location{" + "idEmplacement=" + idEmplacement + ", superficie=" + superficie + ", loyerInitial=" + loyerInitial + ", localisation=" + localisation + ", qualite=" + qualite + ", niveau=" + niveau + ", freqTheorique=" + freqTheorique + '}';
    }
    

   

    

    
}
