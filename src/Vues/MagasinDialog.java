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
import java.awt.GridLayout;
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

    private final JPanel panneau = new JPanel(new GridLayout(0, 1));
    private JTextField nomTextField, idTypeTextField;
    private TextArea descriptionTextArea;
    private JButton sauvegarder, annuler;
    private ControleurDialog controleurDialog;
    private DAOMagasin DAOMagasin;
    private MagasinView magasinView;

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

		nomTextField.setText(M.getNom());
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
        setBounds(100, 100, 450, 234);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panneau);
        panneau.add(new JLabel("Nom:"));
        nomTextField = new JTextField();
        panneau.add(nomTextField);
        panneau.add(new JLabel("Description:"));
        descriptionTextArea = new TextArea(5, 20);
        panneau.add(descriptionTextArea);
        panneau.add(new JLabel("idTYPE"));
        idTypeTextField = new JTextField();
        panneau.add(idTypeTextField);
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
    public JTextField getNomTextField() {
        return nomTextField;
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
