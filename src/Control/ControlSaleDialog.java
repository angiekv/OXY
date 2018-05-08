/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Views.SaleDialog;
import Model.DAOSale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class ControlSaleDialog implements ActionListener {

    private SaleDialog view;
    private DAOSale dao;

    public ControlSaleDialog(SaleDialog vue) {
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

        // data input
        String pr = view.getPr().getText();
        int prSaisie = Integer.parseInt(pr);
        String qte = view.getQte().getText();
        int qteSaisie = Integer.parseInt(qte);
        String cl = view.getCl().getText();
        int clSaisie = Integer.parseInt(cl);

        

        try {
            // not update mode 
            if (view.isUpdateMode() == false) {
                view.getDao().addSale(prSaisie, qteSaisie, clSaisie );
            }


            // close dialog
            view.setVisible(false);
            view.dispose();
            // refresh the view  
            view.getProductView().getControl().refreshProduct();
            // message 
            JOptionPane.showMessageDialog(view.getProductView(),
                    "Achat réussi.",
                    "Commande passé",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    view.getProductView(),
                    "Erreur lors de l'achat! "
                    + exc.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
