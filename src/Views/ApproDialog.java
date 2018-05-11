/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlApproDialog;
import Control.ControlSaleDialog;
import Model.DAOOrder;
import Model.DAOSale;
import Model.Sale;
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
public class ApproDialog extends JDialog {
    
   private JPanel pan = new JPanel();
    //Items displayed at the screen
    private JTextField idb;
    

    //labels
    private JLabel b = new JLabel("Saisir le numéro de bon de commande: ");
    //buttons 
    private JButton save, back;
    //controler 
    private ControlApproDialog control;
    private DAOOrder dao;
    // the view of product (with the list of sale)
    private ProductView view;
    // the selected order
    private final ControlApproDialog controlDialog;
    
        public ApproDialog(ProductView C, DAOOrder dao) {
        //call the constructor of the view dialog 
        this();
        view = C;
        this.dao = dao;

    }
    
        public ApproDialog() {
        //the contoler of the dialog 
        this.controlDialog = new ControlApproDialog(this,dao);
        setTitle("Effectuer un approvisionnement");
       
        setBounds(100, 100, 550, 200);
        
        pan= new JPanel(new GridBagLayout());
        getContentPane().add(pan);
        GridBagConstraints gcb = new GridBagConstraints();
        
        //space between items (the margin)
        gcb.insets = new Insets(10, 10, 10, 10);
         
        // product
        gcb.gridx = 0;
        gcb.gridy = 0;     
        pan.add(b, gcb);
        gcb.gridx = 1;
        gcb.gridy = 0;  
        idb = new JTextField();
        idb.setColumns(20);
        pan.add(idb, gcb);   
        

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
    public JTextField getIdb() {
        return idb;
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
    
    public DAOOrder getDao() {
        return dao;
    }



}