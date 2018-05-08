/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.DAOStore;
import Model.Store;
import Model.StoreTable;
import Views.ProductView;
import Views.StoreView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class ControlStore implements ActionListener,WindowListener{

    private StoreView vue;
    private StoreTable model;
    private DAOStore dao;
    private DAOStore DAOStore;

    public ControlStore(StoreView vue, StoreTable modele,DAOStore dao) {
        this.vue = vue;
        this.model = modele;
        this.dao=dao;
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
            ProductView view = new ProductView(id,vue.getClient());

            view.setVisible(true);

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
    public void windowOpened(WindowEvent we) {}

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
    public void windowIconified(WindowEvent we) {}

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {}

    @Override
    public void windowDeactivated(WindowEvent we) {}

}
