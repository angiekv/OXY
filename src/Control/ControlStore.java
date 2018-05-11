/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.DAOStore;
import Model.Store;
import Model.StoreTable;
import Views.AllocationView;
import Views.ProductView;
import Views.StoreDialog;
import Views.StoreView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class ControlStore implements ActionListener, WindowListener {

    private StoreView vue;
    private StoreTable model;
    private DAOStore DAOStore;

    public ControlStore(StoreView vue, StoreTable modele, DAOStore dao) {
        this.vue = vue;
        this.model = modele;
        this.DAOStore = dao;
    }

    //when we click
    @Override
    public void actionPerformed(ActionEvent e) {

        if (vue.getAff() == e.getSource()) {

            int row = vue.getTable().getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                return;
            }

            Store M = this.model.getAllStores(row);
            int id = M.getId();
            ProductView view = new ProductView(id, vue.getClient());

            view.setVisible(true);

        }
        // show affectation 
        if (vue.getAffectation() == e.getSource()) {

            AllocationView view = new AllocationView(vue.getClient());

            view.setVisible(true);

        }
        //click delete store

        if (vue.getDeleteStore() == e.getSource()) {
            int row = vue.getTable().getSelectedRow();
            // no selected row
            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                return;
            }
            // A row is selected 
            int response = JOptionPane.showConfirmDialog(
                    vue, "Supprimer ce Magasin ?", "Confirmer",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response != JOptionPane.YES_OPTION) {
                return;
            }
            //we get the object(customer) selected 
            Store M = this.model.getAllStores(row);
            int id = M.getId();

            try {
                // delete the magasin
                DAOStore.deleteStore(id);
            } catch (IOException ex) {
                Logger.getLogger(ControlStore.class.getName()).log(Level.SEVERE, null, ex);
            }

            //refresh the jtable 
            refreshStore();
//
            // show success message
            JOptionPane.showMessageDialog(vue,
                    "Suppresion Magasin réussi.", "Magasin supprimer",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        if (vue.getUpdateStore() == e.getSource()) {
            int row = vue.getTable().getSelectedRow();

            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                return;
            }

            Store M = this.model.getAllStores(row);
       
            try {

                StoreDialog view = new StoreDialog(vue, DAOStore, M, true, vue.getClient());

                view.setVisible(true);
                
            } catch (IOException ex) {
                Logger.getLogger(ControlStore.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (vue.getAddStore() == e.getSource()) {
            try {

                StoreDialog view = new StoreDialog(vue, DAOStore, null, false, vue.getClient());

                view.setVisible(true);
                
            } catch (IOException ex) {
                Logger.getLogger(ControlStore.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * this function will refresh the list of magasin
     */
    public void refreshStore() {

        try {
            List<Store> m = DAOStore.loadStores();

            // create the model and update the "table"
            StoreTable model = new StoreTable(m);

            vue.getTable().setModel(model);
            this.model = model;
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(vue, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Deconexion");
        vue.getClient().stopConnection();

    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

}
