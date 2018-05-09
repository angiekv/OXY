/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.DAOLocation;
import Model.Location;
import Model.LocationStoreTable;
import Views.AllocationView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aberkane
 */
public class ControlAffectation implements ActionListener {

    private AllocationView vue;
    private LocationStoreTable model;
    private DAOLocation daoLocation;

    public ControlAffectation(AllocationView vue, LocationStoreTable modele, DAOLocation dao) {
        this.vue = vue;
        this.model = modele;
        this.daoLocation = dao;
    }

    //when we click
    @Override
    public void actionPerformed(ActionEvent e) {

        if (vue.getAffect() == e.getSource()) {

            try {
                    daoLocation.affectStoreToLocation();
                    refreshLocationStore();
                    JOptionPane.showMessageDialog(vue,
                            " Tous les magasin ont éte affecter a un emplacement .",
                            "Affectation",
                            JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(ControlAffectation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * this function will refresh the list of magasin
     */
    public void refreshLocationStore() {

        try {
            List<Location> m = daoLocation.loadLocationAndStores();

            // create the model and update the "table"
            LocationStoreTable model = new LocationStoreTable(m);

            vue.getTable().setModel(model);
            this.model = model;
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(vue, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

}
