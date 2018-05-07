package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author BaDiakite
 */
public class Rent {
    private int idMag;
    private int idE;
    private int frequentationini;
    private int loyerini;
    private int freq;
    private Date date;
    private float redevance;

    public Rent(int idMag, int idE, int frequentationini, int loyerini, int freq, Date date) {
        this.idMag = idMag;
        this.idE = idE;
        this.frequentationini = frequentationini;
        this.loyerini = loyerini;
        this.freq = freq;
        this.date = date;
    }

    public int getIdMag() {
        return idMag;
    }

    public int getIdE() {
        return idE;
    }

    public int getFrequentationini() {
        return frequentationini;
    }

    public int getLoyerini() {
        return loyerini;
    }

    public int getFreq() {
        return freq;
    }

    public float getRedevance() {
        return redevance;
    }

    @Override
    public String toString() {
        return "Redevance{" + "idMag=" + idMag + ", idE=" + idE + ", frequentationini=" + frequentationini + ", loyerini=" + loyerini + ", freq=" + freq + ", redevance=" + redevance + '}';
    }
    
    
}
