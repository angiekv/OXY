/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlStoreDialog;
import Model.ClientSocket;
import Model.DAOStore;
import Model.DAOType;
import Model.TypeStore;
import Model.Store;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author oxy
 */
public class StoreDialog extends JDialog {

    private JPanel panel = new JPanel();
    //Items displayed at the screen
    private JTextField designationTextField;
    private TextArea descriptionTextArea;
    private JTextField rentTextField;
    private JTextField floorTextField;
    private JTextField surfaceTextField;
    private ButtonGroup localisationGroup = new ButtonGroup();
    private JRadioButton sortieEntreeRadio = new JRadioButton("Entrée ou Sortie");
    private JRadioButton indiferrentRadio = new JRadioButton("Indifferent");
    //labels
    private JLabel labelDesignation = new JLabel("Entrer nom magasin: ");
    private JLabel labelDescription = new JLabel("Entrer description: ");
    private JLabel labelRent = new JLabel("Saisir le loyer souhaiter : ");
    private JLabel labelSurface = new JLabel("Saisir le numero du type : ");
    private JLabel labelFloor = new JLabel("saisir un niveau : ");
    private JLabel labelType = new JLabel("choisir un type : ");
    private JLabel labelType2 = new JLabel("choisir un second type(facultatif) : ");
    private JLabel labelLocalisation = new JLabel(" Localisation souhaiter : ");
    private JComboBox lstType = new JComboBox();
    private JComboBox lstType2 = new JComboBox();
//    private JLabel labelType = new JLabel("Saisir le numero du type : ");
//    private JLabel labelType = new JLabel("Saisir le numero du type : ");

    //buttons 
    private JButton save, cancel;
    //controler 
    private ControlStoreDialog controlStoreDialog;
    private DAOStore daoStore;
    // the view of shops (with the list of shops)
    private StoreView storeView;
    // the selected shop 
    private Store storeSelected = null;
    private boolean updateMode;
    private ClientSocket client;

    public StoreDialog(StoreView S, DAOStore DAO, Store storeSelected, boolean updateMode, ClientSocket c) throws IOException {
        //call the constructor of the view dialog 
        this();
        storeView = S;
        this.daoStore = DAO;
        this.storeSelected = storeSelected;
        this.client = c;

        this.updateMode = updateMode;

        if (updateMode) {
            setTitle("Modifier magasin");

            remplirGui(storeSelected);
        }else{
            remplirLstType();
        }

    }

    /**
     * This function will fill the field
     *
     * @param M the shop selected on the magasinView
     */
    private void remplirLstType() throws IOException{
        DAOType D = new DAOType(client);
       
        List<TypeStore> L = D.loadTypeStore();
        for (TypeStore T : L) {
            
            lstType.addItem(T.getDesignation());
            
        }
        lstType2.addItem("");
        for (TypeStore T : L) {
            lstType2.addItem(T.getDesignation());
            
            
        }
    }
    private void remplirGui(Store M) throws IOException {
        setTitle("Ajouter magasin");
        designationTextField.setText(M.getDesignation());
        descriptionTextArea.setText(M.getDescription());
        rentTextField.setText(Integer.toString(M.getLoyer()));
        floorTextField.setText(Integer.toString(M.getNiveau()));
        surfaceTextField.setText(Integer.toString(M.getSuperficie()));
        DAOType D = new DAOType(client);
        List<TypeStore> L = D.loadTypeStore();
        lstType.addItem("");
        for (TypeStore T : L) {
            lstType.addItem(T.getDesignation());
            if (M.getListIdType().get(0).getIdType() == T.getIdType()) {
                lstType.setSelectedItem(T.getDesignation());
            }
        }
        lstType2.addItem("");
        System.out.println(M.getListIdType().toString());
        for (TypeStore T : L) {
            lstType2.addItem(T.getDesignation());
            if (M.getListIdType().size()> 1) {
                if (M.getListIdType().get(1).getIdType() == T.getIdType()) {
                    lstType2.setSelectedItem(T.getDesignation());
                }
            }
        }
//        System.out.println(M.getLocalisation());
        if (M.getLocalisation().equals("Indifferent")) {
            localisationGroup.setSelected(indiferrentRadio.getModel(), true);
        }else{
            localisationGroup.setSelected(sortieEntreeRadio.getModel(), true);
        }

    }

    /**
     * Create the dialog
     */
    public StoreDialog() {
        //the contoler of the dialog 
        this.controlStoreDialog = new ControlStoreDialog(this, daoStore);
        setTitle("Ajouter Magasin");

        setBounds(130, 130, 550, 450);

        panel = new JPanel(new GridBagLayout());
        getContentPane().add(panel);
        GridBagConstraints gcb = new GridBagConstraints();

        //space between items (the margin)
        gcb.insets = new Insets(10, 10, 10, 10);

        // Designation
        gcb.gridx = 0;
        gcb.gridy = 0;
        panel.add(labelDesignation, gcb);
        gcb.gridx = 1;
        gcb.gridy = 0;
        designationTextField = new JTextField();
        designationTextField.setColumns(20);
        panel.add(designationTextField, gcb);

        // description
        gcb.gridx = 0;
        gcb.gridy = 1;
        panel.add(labelDescription, gcb);
        gcb.gridx = 1;
        gcb.gridy = 1;
        descriptionTextArea = new TextArea(4, 20);
        descriptionTextArea.setColumns(20);
        panel.add(descriptionTextArea, gcb);

        // rent
        gcb.gridx = 0;
        gcb.gridy = 2;
        panel.add(labelRent, gcb);
        gcb.gridx = 1;
        gcb.gridy = 2;
        rentTextField = new JTextField();
        rentTextField.setColumns(20);
        panel.add(rentTextField, gcb);

        // surface
        gcb.gridx = 0;
        gcb.gridy = 3;
        panel.add(labelSurface, gcb);
        gcb.gridx = 1;
        gcb.gridy = 3;
        surfaceTextField = new JTextField();
        surfaceTextField.setColumns(20);
        panel.add(surfaceTextField, gcb);

//        // floor
        gcb.gridx = 0;
        gcb.gridy = 4;
        panel.add(labelFloor, gcb);
        gcb.gridx = 1;
        gcb.gridy = 4;
        floorTextField = new JTextField();
        floorTextField.setColumns(20);
        panel.add(floorTextField, gcb);
//        
//        // mail
        gcb.gridx = 0;
        gcb.gridy = 5;
        panel.add(labelType, gcb);
        gcb.gridx = 1;
        gcb.gridy = 5;
//        mail = new JTextField();
//        mail.setColumns(20);
        panel.add(lstType, gcb);
//        
        gcb.gridx = 0;
        gcb.gridy = 6;
        panel.add(labelType2, gcb);
        gcb.gridx = 1;
        gcb.gridy = 6;
//        mail = new JTextField();
//        mail.setColumns(20);
        panel.add(lstType2, gcb);
//        // sexe
        gcb.gridx = 0;
        gcb.gridy = 7;
        panel.add(labelLocalisation, gcb);
        gcb.gridx = 1;
        gcb.gridy = 7;


        panel.add(indiferrentRadio, gcb);
        gcb.gridx = 2;
        gcb.gridy = 7;
        panel.add(sortieEntreeRadio, gcb);
        localisationGroup.add(sortieEntreeRadio);
        localisationGroup.add(indiferrentRadio);
        //Pannel with the buttons
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        save = new JButton("sauvegarder");
        buttonPane.add(save);
        save.addActionListener(controlStoreDialog);
        cancel = new JButton("annuler");
        cancel.addActionListener(controlStoreDialog);
        buttonPane.add(cancel);

    }

    /**
     *
     * @return the value of the textfield nom
     */
    public JTextField getDesignationTextField() {
        return designationTextField;
    }

    public JTextField getRentTextField() {
        return rentTextField;
    }

    public JTextField getFloorTextField() {
        return floorTextField;
    }

    public JTextField getSurfaceTextField() {
        return surfaceTextField;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getCancel() {
        return cancel;
    }

    public Store getStoreSelected() {
        return storeSelected;
    }

    /**
     *
     * @return the value of the textArea description
     */
    public TextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public StoreView getStoreView() {
        return storeView;
    }

    public JComboBox getLstType() {
        return lstType;
    }

    public JComboBox getLstType2() {
        return lstType2;
    }

    /**
     *
     * @return true if modify magasin / false if we add magasin
     */
    public boolean isUpdateMode() {
        return updateMode;
    }

    public ButtonGroup getLocalisationGroup() {
        return localisationGroup;
    }

    public DAOStore getDaoStore() {
        return daoStore;
    }

    public JRadioButton getSortieEntreeRadio() {
        return sortieEntreeRadio;
    }

    public JRadioButton getIndiferrentRadio() {
        return indiferrentRadio;
    }

    
    
}
