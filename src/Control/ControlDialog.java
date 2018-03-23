/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Customer;
import Server.DAOCustomer;
import Views.CustomerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
    public class ControlDialog implements ActionListener {

        private CustomerDialog view;
        private DAOCustomer dao;

        public ControlDialog(CustomerDialog vue) {
            this.view = vue;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getSave() == e.getSource()) {
                save();
            }

            if (view.getBack() == e.getSource()) {
                view.setVisible(false);
                view.dispose();

            }

        }

        protected void save() {

            // récuperer les donnée saisie 
            String nomSaisie = view.getNom().getText();
            String prenomSaisie = view.getPrenom().getText();
            String adresseSaisie = view.getAdresse().getText();
            String cpSaisie = view.getCp().getText();
            String villeSaisie = view.getVille().getText();
            String mailSaisie = view.getMail().getText();
            String sexeSaisie = view.getSexe().getText();

            Customer select = view.getCustomerSelected();

            try {
                // not update mode 
                if (view.isUpdateMode() == false) {
                    DAOCustomer.addCustomer(nomSaisie, prenomSaisie, adresseSaisie, cpSaisie, villeSaisie, mailSaisie, sexeSaisie);
                } //update mode 
                else {
                    DAOCustomer.updateCustomer(select.getIdClient(), nomSaisie, prenomSaisie, adresseSaisie, cpSaisie, villeSaisie, mailSaisie, sexeSaisie);
                }

                // close dialog
                view.setVisible(false);
                view.dispose();
                // refresh the view  
                view.getCustomerView().getControl().refreshCustomer();
                // message 
                JOptionPane.showMessageDialog(view.getCustomerView(),
                        "Ajout client réussi.",
                        "Client ajouté",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(
                        view.getCustomerView(),
                        "Erreur lors de l'ajout client! "
                        + exc.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

