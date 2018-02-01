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
        if (vue.getSupprimer() == e.getSource()) {
            int row = vue.getTable().getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                return;
            }
            int response = JOptionPane.showConfirmDialog(
                    vue, "supprimer ce magasin ?", "Confirmer",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response != JOptionPane.YES_OPTION) {
                return;
            }

            Magasin M = (Magasin) vue.getTable().getValueAt(row, MagasinTableModel.OBJECT_COL);

            try {
                // delete the employee
                DAOMagasin.supprimerMagasin(M.getNum());
            } catch (SQLException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            rafraichirListMagasin();
//
            // show success message
            JOptionPane.showMessageDialog(vue,
                    "Suppresion magasin réussi.", "Magasin supprimer",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        if (vue.getAjouter() == e.getSource()) {
            MagasinDialog dialog = new MagasinDialog(vue,DAOMagasin,null,false);
            // show dialog
            dialog.setVisible(true);

        }
         if (vue.getModifier() == e.getSource()) {

            int row = vue.getTable().getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                return;
            }
            Magasin M = (Magasin) vue.getTable().getValueAt(row, MagasinTableModel.OBJECT_COL);
            MagasinDialog dialog = new MagasinDialog(vue,DAOMagasin,M,true);
            
            dialog.setVisible(true);
        }

    }

    public void rafraichirListMagasin() {

        try {
            List<Magasin> employees = DAOMagasin.chargeMagasin();

            // create the model and update the "table"
            MagasinTableModel model = new MagasinTableModel(employees);

            vue.getTable().setModel(model);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(vue, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
//        btnDeleteEmployee.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                try {
//                    // get the selected row
//                    int row = table.getSelectedRow();
//
//                    // make sure a row is selected
//                    if (row < 0) {
//                        JOptionPane.showMessageDialog(EmployeeSearchApp.this,
//                                "You must select an employee", "Error", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//
//                    // prompt the user
//                    int response = JOptionPane.showConfirmDialog(
//                            EmployeeSearchApp.this, "Delete this employee?", "Confirm",
//                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//
//                    if (response != JOptionPane.YES_OPTION) {
//                        return;
//                    }
//
//                    // get the current employee
//                    Employee tempEmployee = (Employee) table.getValueAt(row, EmployeeTableModel.OBJECT_COL);
//
//                    // delete the employee
//                    employeeDAO.deleteEmployee(tempEmployee.getId());
//
//                    // refresh GUI
//                    refreshEmployeesView();
//
//                    // show success message
//                    JOptionPane.showMessageDialog(EmployeeSearchApp.this,
//                            "Employee deleted succesfully.", "Employee Deleted",
//                            JOptionPane.INFORMATION_MESSAGE);
//
//                } catch (Exception exc) {
//                    JOptionPane.showMessageDialog(EmployeeSearchApp.this,
//                            "Error deleting employee: " + exc.getMessage(), "Error",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//

//        });
//    Magasin modele;
//    MagasinView vue;
//
//    Controleur(Magasin modele, MagasinView vue) {
//        this.modele = modele;
//        this.vue = vue;
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == vue.aj) {
//           
//        } else if (e.getSource() == vue.efface) {
//            modele.setExiste(false);
//        }
//    }
////
////    @Override
////    public void actionPerformed(ActionEvent e) {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

