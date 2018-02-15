/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.DAOMagasin;
import Modele.Magasin;
import Vues.MagasinDialog;
import Vues.MagasinView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Modele.MagasinTableModel;

/**
 *
 * @author OXY
 */
public class ControleurDialog implements ActionListener {

    private  MagasinDialog vue;
    private DAOMagasin DAOMagasin;


    public ControleurDialog(MagasinDialog vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vue.getSauvegarder() == e.getSource()) {
            sauvegarderMagasin();
        }

        if (vue.getAnnuler() == e.getSource()) {
            vue.setVisible(false);
            vue.dispose();

        }

    }

    protected void sauvegarderMagasin() {

        // récuperer les donnée saisie 
        String nomSaisie = vue.getDesignationTextField().getText();
        String descSaisie = vue.getDescriptionTextArea().getText();
        String IdType = vue.getIdTypeTextField().getText();
        int IdTypeSaisie = Integer.parseInt(IdType);
        Magasin magasinSelectionner = vue.getMagasinSelectionner();

        try {
            // not update mode 
            if (vue.isUpdateMode() == false) {
                DAOMagasin.AjouterMagasin(nomSaisie, descSaisie, IdTypeSaisie);
            } 
            //update mode 
            else {
                DAOMagasin.modifierMagasin(magasinSelectionner.getId(), nomSaisie, descSaisie, IdTypeSaisie);
            }

            // close dialog
            vue.setVisible(false);
            vue.dispose();
            // refresh the view  
            vue.getMagasinView().getControleur().rafraichirListMagasin();
            // message 
            JOptionPane.showMessageDialog(vue.getMagasinView(),
                    "Ajout magasin réussi.",
                    "Magasin ajouté",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    vue.getMagasinView(),
                    "Erreur lors de l'ajout magasin! "
                    + exc.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
