/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Server.*;
import java.sql.Date;



/**
 *
 * @author BaDiakite
 */
public class EmpHasMag {
    
    private String nomMag;
    private String localisation;
    private Date date;
    private float redevance;

    public EmpHasMag(String nomMag, String localisation, Date date, int redevance) {
        this.nomMag = nomMag;
        this.localisation = localisation;
        this.date = date;
        this.redevance = redevance;
    }

    public String getNomMag() {
        return nomMag;
    }

    public String getLocalisation() {
        return localisation;
    }

    public Date getDate() {
        return date;
    }

    public float getRedevance() {
        return redevance;
    }

    @Override
    public String toString() {
        return "EmpHasMag{" + "nomMag=" + nomMag + ", localisation=" + localisation + ", date=" + date + ", redevance=" + redevance + '}';
    }
    
    
    
}
