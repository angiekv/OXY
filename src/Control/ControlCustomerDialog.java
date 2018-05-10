/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Customer;
import Model.DAOCustomer;
import Views.CustomerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
    public class ControlCustomerDialog implements ActionListener {

        private CustomerDialog view;
        private DAOCustomer dao;

        public ControlCustomerDialog(CustomerDialog vue) {
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
            String userActionSentence1=null;
            String userActionSentence2= null;
            String nomPopup= null;
            if (nomSaisie.isEmpty() || prenomSaisie.isEmpty()|| adresseSaisie.isEmpty()|| cpSaisie.isEmpty()|| villeSaisie.isEmpty() || mailSaisie.isEmpty() ||  sexeSaisie.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs");
                return;
            }

            try {
                // not update mode 
                
                if (view.isUpdateMode() == false) {
                    userActionSentence1 ="Ajout";
                    userActionSentence2= "l'ajout";
                    nomPopup= "Client ajouté";
                    view.getCustomerView().getDao().addCustomer(nomSaisie, prenomSaisie, adresseSaisie, cpSaisie, villeSaisie, mailSaisie, sexeSaisie);
                } //update mode 
                else {
                    userActionSentence1 ="Modification";
                    userActionSentence2 ="la modification";
                    nomPopup= "Client Modifié";
                    view.getCustomerView().getDao().updateCustomer(select.getIdClient(), nomSaisie, prenomSaisie, adresseSaisie, cpSaisie, villeSaisie, mailSaisie, sexeSaisie);
                }

                // close dialog
                view.setVisible(false);
                view.dispose();
                // refresh the view  
                view.getCustomerView().getControl().refreshCustomer();
                // message 
                JOptionPane.showMessageDialog(view.getCustomerView(),
                        userActionSentence1 + " client réussi.",
                        nomPopup,
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(
                        view.getCustomerView(),
                        "Erreur lors de "+userActionSentence2+" client! "
                        + exc.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

