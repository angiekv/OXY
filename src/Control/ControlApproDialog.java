/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.DAOOrder;
import Views.ApproDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class ControlApproDialog implements ActionListener  {
    
    private ApproDialog view;
    private DAOOrder dao;

    public ControlApproDialog(ApproDialog vue,DAOOrder dao) {
        this.view = vue;
        this.dao=dao;
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

        // data input
        String idb = view.getIdb().getText();
        int idbSaisie = Integer.parseInt(idb);
        
        try {
            view.getDao().appro(idbSaisie);
        } catch (IOException ex) {
            Logger.getLogger(ControlApproDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
     view.getProductView().getControl().refreshProduct();
      JOptionPane.showMessageDialog(view.getProductView(),
                    "Approvisionnement réussi.",
                    "Commande entrée",
                    JOptionPane.INFORMATION_MESSAGE);
        
    }
    
}
