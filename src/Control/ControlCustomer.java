/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.ClientSocket;
import Model.Customer;
import Model.CustomerTable;
import Model.DAOCustomer;
import Model.DTOPath;
import Server.Server;
import Views.CustomerDialog;
import Views.CustomerDialogProfile;
import Views.CustomerView;
import Views.PathDialog;
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
public class ControlCustomer implements ActionListener {

    private CustomerView vue;
    private CustomerTable model;
    private DAOCustomer dao;
    private DTOPath daoPath = new DTOPath(new ClientSocket());
//    private DAOCustomer DAOCustomer;

    public ControlCustomer(CustomerView vue, CustomerTable modele, DAOCustomer dao) {
        this.vue = vue;
        this.model = modele;
        this.dao = dao;
    }

    //when we click
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //when we click on dissocier profils
                if (vue.getDissociation()== e.getSource()) {
            try {
                dao.unlinkProfile();
            } catch (IOException ex) {
                Logger.getLogger(ControlAffectation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //when we click on associer profils
        if (vue.getAffectation() == e.getSource()) {
            try {
                dao.affectClientToProfile();
                JOptionPane.showMessageDialog(vue,
                        "Les clients sont affectés à un ou deux profils",
                        "Affectation",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(ControlAffectation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //when we click on delete
        if (vue.getDelete() == e.getSource()) {
            int row = vue.getTable().getSelectedRow();
            // no selected row
            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                return;
            }
            // A row is selected 
            int response = JOptionPane.showConfirmDialog(
                    vue, "Supprimer ce client ?", "Confirmer",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response != JOptionPane.YES_OPTION) {
                return;
            }
            //we get the object(customer) selected 
            Customer C = this.model.getAllCustomers(row);

            try {
                // delete the magasin
                dao.deleteCustomer(C.getIdClient());
            } catch (IOException ex) {
                Logger.getLogger(ControlCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
            //refresh the jtable 
            refreshCustomer();
//
            // show success message
            JOptionPane.showMessageDialog(vue,
                    "Suppresion client réussi.", "Client supprimer",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        // click on "ajouter"
        if (vue.getAdd() == e.getSource()) {
            CustomerDialog dialog = new CustomerDialog(vue, dao, null, false);
            // show dialog
            dialog.setVisible(true);

        }
        //click on "modifier"
        if (vue.getUpdate() == e.getSource()) {

            int row = vue.getTable().getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                return;
            }

            Customer C = this.model.getAllCustomers(row);
            CustomerDialog dialog = new CustomerDialog(vue, dao, C, true);

            dialog.setVisible(true);
        }
        //When we click on afficher
        if (vue.getAfficher() == e.getSource()) {
            try {
                int row = vue.getTable().getSelectedRow();
                // no selected row
                if (row < 0) {
                    JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                    return;
                }

                Customer C = this.model.getAllCustomers(row);
                CustomerDialogProfile dialog = new CustomerDialogProfile(vue, dao, C);
                dialog.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ControlCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (vue.getPath() == e.getSource()) {
            int row = vue.getTable().getSelectedRow();
            // no selected row
            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner un client");
                return;
            }
            Customer C = this.model.getAllCustomers(row);
            PathDialog dialog = null;
            try {
                dialog = new PathDialog(vue.getClient(), vue, C);
            } catch (IOException ex) {
                Logger.getLogger(ControlCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
            dialog.setVisible(true);
        }
    }

    /**
     * this function will refresh the list of magasin
     */
    public void refreshCustomer() {

        try {
            List<Customer> c = dao.loadCustomer();

            // create the model and update the "table"
            CustomerTable model = new CustomerTable(c);

            vue.getTable().setModel(model);
            this.model = model;
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(vue, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

}
