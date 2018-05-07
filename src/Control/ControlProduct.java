/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

//import Model.DAOSale;
import Model.DAOProduct;
//import Model.DAOReturncustomer;
//import Model.DAOReturnprovider;
import Model.Store;
import Model.Product;
import Model.ProductTable;
//import View.SaleDialog;
//import View.HistoView;
import Views.ProductDialog;
import Views.ProductView;
//import View.ReturncustomertDialog;
//import View.ReturnproviderDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class ControlProduct implements ActionListener {
     
    private int idMag;
    private ProductView vue;
    private ProductTable model;
    private DAOProduct DAOProduct;
//    private DAOSale DAOAchat;
//    private DAOReturncustomer DAORetourclient;
//    private DAOReturnprovider DAORetourfournisseur;

    public ControlProduct(ProductView vue, ProductTable modele,int idMag) {
        this.vue = vue;
        this.model = modele;
        this.idMag = idMag;
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
                    vue, "Supprimer ce produit ?", "Confirmer",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response != JOptionPane.YES_OPTION) {
                return;
            }
            //we get the object(product) selected 
            Product P = this.model.getAllProducts(row);

            try {
                // delete the product
                DAOProduct.deleteProduct(P.getIdProduit());
            } catch (IOException ex) {
                Logger.getLogger(ControlProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
            //refresh the jtable 
            refreshProduct();
//
            // show success message
            JOptionPane.showMessageDialog(vue,
                    "Suppresion produit réussi.", "Produit supprimer",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        // click on "ajouter"
        if (vue.getAdd() == e.getSource()) {
            ProductDialog dialog = new ProductDialog(vue, DAOProduct, null, false);
            // show dialog
            dialog.setVisible(true);

        }
//        //click on "vente"
//        if (vue.getVente() == e.getSource()) {
//            SaleDialog dialog = new SaleDialog(vue,DAOAchat,false);
////             show dialog
//            dialog.setVisible(true);
//
//        }
//        
//        //click on "retour client"
//        if (vue.getRtc() == e.getSource()) {
//            ReturncustomertDialog dialog = new ReturncustomertDialog(vue,DAORetourclient,false);
////             show dialog
//            dialog.setVisible(true);
//
//        }
//        
//        //click on "retour fournisseur"
//        if (vue.getRtf() == e.getSource()) {
//            ReturnproviderDialog dialog = new ReturnproviderDialog(vue,DAORetourfournisseur,false);
////             show dialog
//            dialog.setVisible(true);
//
//        }
        
        //click on "modifier"
        if (vue.getUpdate() == e.getSource()) {

            int row = vue.getTable().getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
                return;
            }

            Product P = this.model.getAllProducts(row);
            ProductDialog dialog = new ProductDialog(vue, DAOProduct, P, true);

            dialog.setVisible(true);
        }
        
//        if (vue.getAff() == e.getSource()) {
//
//            int row = vue.getTable().getSelectedRow();
//            if (row < 0) {
//                JOptionPane.showMessageDialog(vue, "selectionner une ligne");
//                return;
//            }
//
//            Product P = this.model.getAllProducts(row);
//            int id = P.getIdProduit();
//            HistoView view = new HistoView(id);
//
//            view.setVisible(true);
//
//        }

    }

    /**
     * this function will refresh the list of product
     */
    public void refreshProduct() {

        try {
            List<Product> p = DAOProduct.loadProductStore(idMag);

            // create the model and update the "table"
            ProductTable model = new ProductTable(p);

            vue.getTable().setModel(model);
            this.model = model;
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(vue, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
