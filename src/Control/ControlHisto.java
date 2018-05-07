/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.HistoTable;
import Views.HistoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Michel
 */
public class ControlHisto implements ActionListener {
    
    private int idP;
    private HistoView vue;
    private HistoTable model;

    public ControlHisto(HistoView vue, HistoTable modele, int idP) {
        this.vue = vue;
        this.model = modele;
        this.idP=idP;
    }

    //when we click
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
