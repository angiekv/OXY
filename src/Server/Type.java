/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.List;

/**
 *
 * @author Aberkane
 */
public class Type {
    private int idType;
    private String designation;

    public Type(int idType, String designation) {
        this.idType = idType;
        this.designation = designation;
    }

    public int getIdType() {
        return idType;
    }

    public String getDesignation() {
        return designation;
    }
    
}
