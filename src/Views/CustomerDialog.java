/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlDialog;
import Model.Customer;
import Model.DAOCustomer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
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
public class CustomerDialog extends JDialog{

    private JPanel pan = new JPanel();
    //Items displayed at the screen
    private JTextField nom;
    private JTextField prenom;
    private JTextField adresse;
    private JTextField cp;
    private JTextField ville;
    private JTextField mail;
    private JTextField sexe;
    //labels
    private JLabel fname = new JLabel("Saisir votre nom: ");
    private JLabel name = new JLabel("Saisir votre prenom: ");
    private JLabel ad = new JLabel("Saisir votre adresse : ");
    private JLabel code = new JLabel("Saisir votre code postal : ");
    private JLabel city = new JLabel("Saisir votre ville : ");
    private JLabel email = new JLabel("Saisir votre adresse mail : ");
    private JLabel sex = new JLabel("Indiquer votre sexe : ");
    //buttons 
    private JButton save, back;
    //controler 
    private ControlDialog control;
    private DAOCustomer dao;
    // the view of shops (with the list of shops)
    private CustomerView view;
    // the selected shop 
    private Customer selected = null;
    private boolean updateMode;
    private final ControlDialog controlDialog;
    
        public CustomerDialog(CustomerView C, DAOCustomer dao, Customer selected, boolean updateMode) {
        //call the constructor of the view dialog 
        this();
        view = C;
        dao = dao;
        this.selected = selected;

        this.updateMode = updateMode;

        if (updateMode) {
            setTitle("Modifier magasin");

            insert(selected);
     }

    }
    /**
     * This function will fill the field 
     * @param M the shop selected on the magasinView
     */
    private void insert(Customer C) {

		nom.setText(C.getNom());
		prenom.setText(C.getPrenom());
                adresse.setText(C.getAdresse());
                cp.setText(C.getCp());
                ville.setText(C.getVille());
                mail.setText(C.getMail());
                sexe.setText(C.getSexe());
		
    }
    
        public CustomerDialog() {
        //the contoler of the dialog 
        this.controlDialog = new ControlDialog(this);
        setTitle("Ajouter Client");
       
        setBounds(130, 130, 550, 450);
        
        pan= new JPanel(new GridBagLayout());
        getContentPane().add(pan);
        GridBagConstraints gcb = new GridBagConstraints();
        
        //space between items (the margin)
        gcb.insets = new Insets(10, 10, 10, 10);
         
        // first name
        gcb.gridx = 0;
        gcb.gridy = 0;     
        pan.add(fname, gcb);
        gcb.gridx = 1;
        gcb.gridy = 0;  
        nom = new JTextField();
        nom.setColumns(20);
        pan.add(nom, gcb);
        
        // name
        gcb.gridx = 0;
        gcb.gridy = 1;  
        pan.add(name, gcb);
        gcb.gridx = 1;
        gcb.gridy = 1;
        prenom = new JTextField();
        prenom.setColumns(20);
        pan.add(prenom, gcb);

        // ad
        gcb.gridx = 0;
        gcb.gridy = 2;
        pan.add(ad,gcb);
        gcb.gridx = 1;
        gcb.gridy = 2;
        adresse = new JTextField();
        adresse.setColumns(20);
        pan.add(adresse,gcb);
        
        // cp
        gcb.gridx = 0;
        gcb.gridy = 3;
        pan.add(code,gcb);
        gcb.gridx = 1;
        gcb.gridy = 3;
        cp = new JTextField();
        cp.setColumns(20);
        pan.add(cp,gcb);
        
        // ville
        gcb.gridx = 0;
        gcb.gridy = 4;
        pan.add(city,gcb);
        gcb.gridx = 1;
        gcb.gridy = 4;
        ville = new JTextField();
        ville.setColumns(20);
        pan.add(ville,gcb);
        
        // mail
        gcb.gridx = 0;
        gcb.gridy = 5;
        pan.add(email,gcb);
        gcb.gridx = 1;
        gcb.gridy = 5;
        mail = new JTextField();
        mail.setColumns(20);
        pan.add(mail,gcb);
        
        // sexe
        gcb.gridx = 0;
        gcb.gridy = 6;
        pan.add(sex,gcb);
        gcb.gridx = 1;
        gcb.gridy = 6;
        sexe = new JTextField();
        sexe.setColumns(20);
        pan.add(sexe,gcb);

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
    

    public JTextField getNom() {
        return nom;
    }
    /**
     * 
     * @return the value of the textfield nom
     */
    public JTextField getPrenom() {
        return prenom;
    }
    /**
     * 
     * @return the value of the textfield prenom
     */
    public JTextField getAdresse() {
        return adresse;
    }
     
    public JTextField getCp() {
        return cp;
    }
          
    public JTextField getVille() {
        return ville;
    }
               
    public JTextField getMail() {
        return mail;
    }
                    
    public JTextField getSexe() {
        return sexe;
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
     * @return the view MagasinView
     */
    public CustomerView getCustomerView() {
        return view;
    }
    /**
     * 
     * @return  the selected shop in magasinView 
     */
    public Customer getCustomerSelected() {
        return selected;
    }
    /**
     * 
     * @return true if modify magasin / false if we add magasin
     */
    public boolean isUpdateMode() {
        return updateMode;
    }
}

