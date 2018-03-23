/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Customer;
import Model.CustomerTable;
import Model.DAOCustomer;
import Views.CustomerDialog;
import Views.CustomerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class Control implements ActionListener{
    private CustomerView vue;
    private CustomerTable model;
    private DAOCustomer dao ;
//    private DAOCustomer DAOCustomer;

    public Control(CustomerView vue, CustomerTable modele, DAOCustomer dao) {
        this.vue = vue;
        this.model = modele;
        this.dao = dao;
    }
    //when we click
    @Override
    public void actionPerformed(ActionEvent e) {
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
                    vue, "Supprimer ce magasin ?", "Confirmer",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response != JOptionPane.YES_OPTION) {
                return;
            }
            //we get the object(customer) selected 
            Customer C = this.model.getAllCustomers(row);

            // delete the magasin
            dao.deleteCustomer(C.getIdClient());
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
            
            Customer C =this.model.getAllCustomers(row);
            CustomerDialog dialog = new CustomerDialog(vue, dao, C, true);

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
            this.model=model;
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(vue, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
