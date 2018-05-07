/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlSaleDialog;
import Model.DAOSale;
import Model.Sale;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Michel
 */
public class SaleDialog extends JDialog {
    
   private JPanel pan = new JPanel();
    //Items displayed at the screen
    private JTextField pr;
    private JTextField qte;
    private JTextField cl;;
    

    //labels
    private JLabel p = new JLabel("Saisir le code barre: ");
    private JLabel q = new JLabel("Saisir la quantité: ");
    private JLabel c = new JLabel("Saisir id client : ");
    //buttons 
    private JButton save, back;
    //controler 
    private ControlSaleDialog control;
    private DAOSale dao;
    // the view of product (with the list of sale)
    private ProductView view;
    // the selected sale
    private boolean updateMode;
    private final ControlSaleDialog controlDialog;
    
        public SaleDialog(ProductView C, DAOSale dao, boolean updateMode) {
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
    private void insert(Sale A) {
        
		p.setText(Integer.toString(A.getIdp()));
                q.setText(Integer.toString(A.getQte()));
                c.setText(Integer.toString(A.getIdc()));

		
    }
    
        public SaleDialog() {
        //the contoler of the dialog 
        this.controlDialog = new ControlSaleDialog(this);
        setTitle("Effectuer une vente");
       
        setBounds(130, 130, 550, 450);
        
        pan= new JPanel(new GridBagLayout());
        getContentPane().add(pan);
        GridBagConstraints gcb = new GridBagConstraints();
        
        //space between items (the margin)
        gcb.insets = new Insets(10, 10, 10, 10);
         
        // product
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
        pan.add(q, gcb);
        gcb.gridx = 1;
        gcb.gridy = 1;
        qte = new JTextField();
        qte.setColumns(20);
        pan.add(qte, gcb);

        // customer
        gcb.gridx = 0;
        gcb.gridy = 2;
        pan.add(c,gcb);
        gcb.gridx = 1;
        gcb.gridy = 2;
        cl = new JTextField();
        cl.setColumns(20);
        pan.add(cl,gcb);
        
        

        //Pannel with the buttons
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        save = new JButton("sauvegarder");
        buttonPane.add(save);
        save.addActionListener(controlDialog);
        back = new JButton("annuler");
        back.addActionListener(controlDialog);
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
     
    public JTextField getCl() {
        return cl;
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
    
        public DAOSale getDao() {
        return dao;
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