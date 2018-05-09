/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlReturnproviderDialog;
import Model.DAOReturncustomer;
import Model.DAOReturnprovider;
import Model.Returnprovider;
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
public class ReturnproviderDialog extends JDialog {
    
   private JPanel pan = new JPanel();
    //Items displayed at the screen
    private JTextField pr;
    private JTextField qte;
    private JTextField fo;
    

    //labels
    private JLabel p = new JLabel("Saisir le code barre: ");
    private JLabel q = new JLabel("Saisir la quantité: ");
    private JLabel f = new JLabel("Saisir id fournisseur : ");
    //buttons 
    private JButton save, back;
    //controler 
    private ControlReturnproviderDialog control;
    private DAOReturnprovider dao;
    // the view of product (with the list of rtfo)
    private ProductView view;
    // the selected rtfo
    private boolean updateMode;
    private final ControlReturnproviderDialog controlDialog;
    
        public ReturnproviderDialog(ProductView C, DAOReturnprovider dao, boolean updateMode) {
        //call the constructor of the view dialog 
        this();
        view = C;
        dao = dao;

        this.updateMode = updateMode;


    }
    /**
     * This function will fill the field 
     * @param M the shop selected on the magasinView
     */
    private void insert(Returnprovider RF) {
        
		p.setText(Integer.toString(RF.getIdp()));
                q.setText(Integer.toString(RF.getQte()));
                f.setText(Integer.toString(RF.getIdc()));

		
    }
    
        public ReturnproviderDialog() {
        //the contoler of the dialog 
        this.controlDialog = new ControlReturnproviderDialog(this,dao);
        setTitle("Retour fournisseur");
       
        setBounds(130, 130, 550, 450);
        
        pan= new JPanel(new GridBagLayout());
        getContentPane().add(pan);
        GridBagConstraints gcb = new GridBagConstraints();
        
        //space between items (the margin)
        gcb.insets = new Insets(10, 10, 10, 10);
         

        
        // produit
        gcb.gridx = 0;
        gcb.gridy = 0;  
        pan.add(p, gcb);
        gcb.gridx = 1;
        gcb.gridy = 0;
        pr = new JTextField();
        pr.setColumns(20);
        pan.add(pr, gcb);

        // qte
        gcb.gridx = 0;
        gcb.gridy = 1;
        pan.add(q,gcb);
        gcb.gridx = 1;
        gcb.gridy = 1;
        qte = new JTextField();
        qte.setColumns(20);
        pan.add(qte,gcb);
        
        // client
        gcb.gridx = 0;
        gcb.gridy = 2;
        pan.add(f,gcb);
        gcb.gridx = 1;
        gcb.gridy = 2;
        fo = new JTextField();
        fo.setColumns(20);
        pan.add(fo,gcb);
        

        //Pannel with the buttons
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        save = new JButton("sauvegarder");
        buttonPane.add(save);
        save.addActionListener((ActionListener) controlDialog);
        back = new JButton("annuler");
        back.addActionListener((ActionListener) controlDialog);
        buttonPane.add(back);

    }
    
    /**
     * 
     * @return the value of the textfield product
     */
    public JTextField getPr() {
        return pr;
    }
    /**
     * 
     * @return the value of the textfield qte
     */
    public JTextField getQte() {
        return qte;
    }
     
    public JTextField getFo() {
        return fo;
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
     * @return true if modify product / false if we add product
     */
    public boolean isUpdateMode() {
        return updateMode;
    }



}