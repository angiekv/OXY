/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlProductDialog;
import Model.DAOProduct;
import Model.Product;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Michel
 */
public class ProductDialog extends JDialog {
    
   private JPanel pan = new JPanel();
    //Items displayed at the screen
    private JTextField libelle;
    private JTextField prix;
    private JTextField qte;
    private JTextField Magasin_idMagasin;

    //labels
    private JLabel l = new JLabel("Saisir le libelle: ");
    private JLabel p = new JLabel("Saisir le prix: ");
    private JLabel q = new JLabel("Saisir la quantité: ");
    private JLabel i = new JLabel("Saisir id magasin : ");
    //buttons 
    private JButton save, back;
    //controler 
    private ControlProductDialog control;
    private DAOProduct dao;
    // the view of product (with the list of product)
    private ProductView view;
    // the selected product 
    private Product selected = null;
    private boolean updateMode;
    private final ControlProductDialog controlProductDialog;
    
        public ProductDialog(ProductView C, DAOProduct dao, Product selected, boolean updateMode) {
        //call the constructor of the view dialog 
        this();
        view = C;
        dao = dao;
        this.selected = selected;

        this.updateMode = updateMode;

        if (updateMode) {
            setTitle("Modifier un produit");

            insert(selected);
     }

    }
    /**
     * This function will fill the field 
     * @param M the shop selected on the ProductView
     */
    private void insert(Product P) {

		libelle.setText(P.getLibelle());
		prix.setText(Float.toString(P.getPrix()));
                qte.setText(Integer.toString(P.getQte()));
                Magasin_idMagasin.setText(Integer.toString(P.getMagasin_idMagasin()));
		
    }
    
        public ProductDialog() {
        //the contoler of the dialog 
        this.controlProductDialog = new ControlProductDialog(this);
        setTitle("Ajouter Produit");
       
        setBounds(130, 130, 550, 450);
        
        pan= new JPanel(new GridBagLayout());
        getContentPane().add(pan);
        GridBagConstraints gcb = new GridBagConstraints();
        
        //space between items (the margin)
        gcb.insets = new Insets(10, 10, 10, 10);
         
        // first name
        gcb.gridx = 0;
        gcb.gridy = 0;     
        pan.add(l, gcb);
        gcb.gridx = 1;
        gcb.gridy = 0;  
        libelle = new JTextField();
        libelle.setColumns(20);
        pan.add(libelle, gcb);
        
        // name
        gcb.gridx = 0;
        gcb.gridy = 1;  
        pan.add(p, gcb);
        gcb.gridx = 1;
        gcb.gridy = 1;
        prix = new JTextField();
        prix.setColumns(20);
        pan.add(prix, gcb);

        // ad
        gcb.gridx = 0;
        gcb.gridy = 2;
        pan.add(q,gcb);
        gcb.gridx = 1;
        gcb.gridy = 2;
        qte = new JTextField();
        qte.setColumns(20);
        pan.add(qte,gcb);
        
        // Magasin_idMagasin
        gcb.gridx = 0;
        gcb.gridy = 3;
        pan.add(i,gcb);
        gcb.gridx = 1;
        gcb.gridy = 3;
        Magasin_idMagasin = new JTextField();
        Magasin_idMagasin.setColumns(20);
        pan.add(Magasin_idMagasin,gcb);
        

        //Pannel with the buttons
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        save = new JButton("sauvegarder");
        buttonPane.add(save);
        save.addActionListener(controlProductDialog);
        back = new JButton("annuler");
        back.addActionListener(controlProductDialog);
        buttonPane.add(back);

    }
    

    public JTextField getLibelle() {
        return libelle;
    }
    /**
     * 
     * @return the value of the textfield nom
     */
    public JTextField getPrix() {
        return prix;
    }
    /**
     * 
     * @return the value of the textfield prenom
     */
    public JTextField getQte() {
        return qte;
    }
     
    public JTextField getMagasin_idMagasin() {
        return Magasin_idMagasin;
    }

    /**
     * 
     * @return the jbutton sauvegarder
     */
    public JButton getSave() {
        return save;
    }
    /**
     * 
     * @return the jbutton annuler
     */
    public JButton getBack() {
        return back;
    }
    /**
     * 
     * @return the view ProductView
     */
    public ProductView getProductView() {
        return view;
    }
    /**
     * 
     * @return  the selected shop in productView 
     */
    public Product getProductSelected() {
        return selected;
    }
    /**
     * 
     * @return true if modify product / false if we add product
     */
    public boolean isUpdateMode() {
        return updateMode;
    }



}