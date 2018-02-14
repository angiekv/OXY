/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Controleur.ControleurDialog;
import Modele.DAOMagasin;
import Modele.Magasin;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Romain (stagiaire)
 */
public class MagasinDialog extends JDialog {

    private JPanel panneau = new JPanel();
    //element
    private JTextField designationTextField;
     private JTextField idTypeTextField;
    private TextArea descriptionTextArea;
    // label 
    private JLabel label = new JLabel("Entrer nom magasin: ");
    private JLabel labelDescription = new JLabel("Entrer description: ");
    private JLabel labelType = new JLabel("choisir un type : ");
    // the button 
    private JButton sauvegarder, annuler;
    //the controleur 
    private ControleurDialog controleurDialog;
    private DAOMagasin DAOMagasin;
    // the view magasin (with the list of magasin)
    private MagasinView magasinView;
    // the selected magasin 
    private Magasin magasinSelectionne = null;
    private boolean updateMode;

    public MagasinDialog(MagasinView M, DAOMagasin DAO, Magasin magasinSelectionne, boolean updateMode) {
        //call the constructor of the view dialog 
        this();
        magasinView = M;
        DAOMagasin = DAO;
        this.magasinSelectionne = magasinSelectionne;

        this.updateMode = updateMode;

        if (updateMode) {
            setTitle("Modifier magasin");

            remplirGui(magasinSelectionne);
        }

    }
    /**
     * This function will fill the fill 
     * @param M the magasin selected on the magasinView
     */
    private void remplirGui(Magasin M) {

		designationTextField.setText(M.getDesignation());
		descriptionTextArea.setText(M.getDescription());
		idTypeTextField.setText(Integer.toString(M.getIdType()));		
    }
    /**
     * Create the dialog 
     */
    public MagasinDialog() {
        //the contoleur of the dialog 
        this.controleurDialog = new ControleurDialog(this);
        setTitle("Ajouter Magasin");
       
        setBounds(130, 130, 450, 350);
        
        panneau= new JPanel(new GridBagLayout());
        getContentPane().add(panneau);
        GridBagConstraints gcb = new GridBagConstraints();
        
        //space beteween element (the marge)
        gcb.insets = new Insets(10, 10, 10, 10);
         
        // designation magaisn 
        gcb.gridx = 0;
        gcb.gridy = 0;     
        panneau.add(label, gcb);
 
        gcb.gridx = 1;
        gcb.gridy = 0;  
        designationTextField = new JTextField();
        designationTextField.setColumns(20);
        panneau.add(designationTextField, gcb);
        // decriiption
        gcb.gridx = 0;
        gcb.gridy = 1;  
        panneau.add(labelDescription, gcb);
        
        gcb.gridx = 1;
        gcb.gridy = 1;
        descriptionTextArea = new TextArea(4, 20);
        panneau.add(descriptionTextArea,gcb);
        
        // type a modifier en liste 
        gcb.gridx = 0;
        gcb.gridy = 2;
        panneau.add(labelType,gcb);
//        
        gcb.gridx = 1;
        gcb.gridy = 2;
        idTypeTextField = new JTextField();
        idTypeTextField.setColumns(20);
        panneau.add(idTypeTextField,gcb);

                //panneau avec boutton 
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        sauvegarder = new JButton("sauvegarder");
        buttonPane.add(sauvegarder);
        sauvegarder.addActionListener(controleurDialog);
        annuler = new JButton("annuler");
        annuler.addActionListener(controleurDialog);
        buttonPane.add(annuler);

    }
    /**
     * 
     * @return the value of the textfield nom
     */
    public JTextField getDesignationTextField() {
        return designationTextField;
    }
    /**
     * 
     * @return the value of the textfield id TYPE
     */
    public JTextField getIdTypeTextField() {
        return idTypeTextField;
    }
    /**
     * 
     * @return the value of the textArea description 
     */
    public TextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }
    /**
     * 
     * @return the jbutton sauvegarder
     */
    public JButton getSauvegarder() {
        return sauvegarder;
    }
    /**
     * 
     * @return the jbutton annuler
     */
    public JButton getAnnuler() {
        return annuler;
    }
    /**
     * 
     * @return the view MagasinView
     */
    public MagasinView getMagasinView() {
        return magasinView;
    }
    /**
     * 
     * @return  the selected magasin in magasin view 
     */
    public Magasin getMagasinSelectionner() {
        return magasinSelectionne;
    }
    /**
     * 
     * @return true if modify magasin / false if we add magasin
     */
    public boolean isUpdateMode() {
        return updateMode;
    }

}
