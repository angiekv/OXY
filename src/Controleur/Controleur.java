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

public class Controleur implements ActionListener {

    private MagasinView vue;
    private MagasinTableModel modele;
    private DAOMagasin DAOMagasin;

    public Controleur(MagasinView vue, MagasinTableModel modele) {
        this.vue = vue;
        this.modele = modele;
    }

    //clic sur l'action
    @Override
    public void actionPerformed(ActionEvent e) {
        //on click supprimer
        if (vue.getSupprimer() == e.getSource()) {
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
            //we get the object(magasin) selected 
            Magasin M = (Magasin) vue.getTable().getValueAt(row, MagasinTableModel.OBJECT_COL);

            try {
                // delete the magasin 
                DAOMagasin.supprimerMagasin(M.getNum());
            } catch (SQLException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            //refresh the jtable 
            rafraichirListMagasin();
//
            // show success message
            JOptionPane.showMessageDialog(vue,
                    "Suppresion magasin réussi.", "Magasin supprimer",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        // click on "ajouter"
        if (vue.getAjouter() == e.getSource()) {
            MagasinDialog dialog = new MagasinDialog(vue, DAOMagasin, null, false);
            // show dialog
            dialog.setVisible(true);

        }
        //click on "modifier"
        if (vue.getModifier() == e.getSource()) {

            int row = vue.getTable().getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                return;
            }
            Magasin M = (Magasin) vue.getTable().getValueAt(row, MagasinTableModel.OBJECT_COL);
            MagasinDialog dialog = new MagasinDialog(vue, DAOMagasin, M, true);

            dialog.setVisible(true);
        }

    }
    /**
     * this function will refresh the list of magasin 
     */
    public void rafraichirListMagasin() {

        try {
            List<Magasin> magasins = DAOMagasin.chargeMagasin();

            // create the model and update the "table"
            MagasinTableModel model = new MagasinTableModel(magasins);

            vue.getTable().setModel(model);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(vue, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
