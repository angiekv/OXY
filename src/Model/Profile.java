/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Mario
 */
public class Profile {
    private int idprofil;
    private String profilname;

    public Profile(int idprofile, String name) {
        this.idprofil = idprofil;
        this.profilname = profilname;
    }

    public int getIdprofil() {
        return idprofil;
    }

    public String getName() {
        return profilname;
    }

    @Override
    public String toString() {
        return "Profil{" + "idprofil=" + idprofil + ", profilname=" + profilname + '}';
    }
    
    
}
