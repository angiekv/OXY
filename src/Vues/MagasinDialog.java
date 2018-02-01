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

    private Magasin magasinSelectionner = null;
    private boolean updateMode;

    public MagasinDialog(MagasinView M, DAOMagasin DAO, Magasin magasinSelectionner, boolean updateMode) {
        this();
        magasinView = M;
        DAOMagasin = DAO;
        this.magasinSelectionner = magasinSelectionner;

        this.updateMode = updateMode;

        if (updateMode) {
            setTitle("Modifier magasin");

            remplirGui(magasinSelectionner);
        }

    }
    private void remplirGui(Magasin M) {

		nomTextField.setText(M.getNom());
		descriptionTextArea.setText(M.getDescription());
		idTypeTextField.setText(Integer.toString(M.getIdType()));		
	}
    /**
     * Create the dialog.
     */
    public MagasinDialog() {
        this.controleurDialog = new ControleurDialog(this);
        setTitle("Ajouter Magasin");
        setBounds(100, 100, 450, 234);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panneau);
        panneau.add(new JLabel("Nom:"));
        nomTextField = new JTextField();
        panneau.add(nomTextField);
        panneau.add(new JLabel("Description:"));
        descriptionTextArea = new TextArea();
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

    public JTextField getNomTextField() {
        return nomTextField;
    }

    public JTextField getIdTypeTextField() {
        return idTypeTextField;
    }

    public TextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public JButton getSauvegarder() {
        return sauvegarder;
    }

    public JButton getAnnuler() {
        return annuler;
    }

    public MagasinView getMagasinView() {
        return magasinView;
    }

    public Magasin getMagasinSelectionner() {
        return magasinSelectionner;
    }

    public boolean isUpdateMode() {
        return updateMode;
    }

}
