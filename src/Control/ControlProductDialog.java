/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.DAOProduct;
import Model.Product;
import Views.ProductDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class ControlProductDialog implements ActionListener {

    private ProductDialog view;
    private DAOProduct dao;

    public ControlProductDialog(ProductDialog vue) {
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
        String libelleSaisie = view.getLibelle().getText();
        String prix = view.getPrix().getText();
        Float prixSaisie = Float.parseFloat(prix);
        String qte = view.getQte().getText();
        int qteSaisie = Integer.parseInt(qte);
        String idm = view.getMagasin_idMagasin().getText();
        int idmSaisie = Integer.parseInt(idm);

        Product select = view.getProductSelected();

        try {
            // not update mode 
            if (view.isUpdateMode() == false) {
                view.getProductView().getDao().addProduct(libelleSaisie, prixSaisie, qteSaisie, idmSaisie);
            } //update mode 
            else {
                view.getProductView().getDao().updateProduct(select.getIdProduit(), libelleSaisie, prixSaisie, qteSaisie, idmSaisie);
            }

            // close dialog
            view.setVisible(false);
            view.dispose();
            // refresh the view  
            view.getProductView().getControl().refreshProduct();
            // message 
            JOptionPane.showMessageDialog(view.getProductView(),
                    "Ajout produit réussi.",
                    "Produit ajouté",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    view.getProductView(),
                    "Erreur lors de l'ajout du produit! "
                    + exc.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
