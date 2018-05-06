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
    private int idprofile;
    private String profilname;
    private int Client_idClient;

    public Profile(int idprofile, String profilname, int Client_idClient) {
        this.idprofile = idprofile;
        this.profilname = profilname;
        this.Client_idClient = Client_idClient;
    }

    public int getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(int idprofile) {
        this.idprofile = idprofile;
    }

    public String getProfilname() {
        return profilname;
    }

    public void setProfilname(String profilname) {
        this.profilname = profilname;
    }

    public int getClient_idClient() {
        return Client_idClient;
    }

    public void setClient_idClient(int Client_idClient) {
        this.Client_idClient = Client_idClient;
    }

    @Override
    public String toString() {
        return "Profile{" + "idprofile=" + idprofile + ", profilname=" + profilname + ", Client_idClient=" + Client_idClient + '}';
    }
}
