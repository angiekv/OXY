/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlStore;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Model.ClientSocket;
import Model.DAOStore;
import Model.Store;
import Model.StoreTable;
import java.io.IOException;

/**
 *
 * @author aberkane
 */
public class StoreView extends JFrame {

    private JPanel contentPane;//Panel
    private JScrollPane scrollPane;//The scroolpane for scroll when there is a lot of data 
    private JTable table;//this is a table
    //this are all the buttons 
    private JButton addStore = new JButton("Ajouter");
    private JButton deleteStore = new JButton("deleteStore");
    private JButton updateStore = new JButton("Modifier");
    private JButton affectation = new JButton("Affection magasin");
    private JButton aff = new JButton("Afficher Produits");
    private StoreTable modele;//this is the model of the table 
    private ControlStore controleur;//this is the controler of this view 
    private DAOStore DAOStore;// this is the DAO 
    private ClientSocket client = new ClientSocket();
   

    public StoreView() {
        //initialize the contoler with the view and the model of the table
        client.startConnection();
        
        setTitle("Stores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);//The position and the size of the frame
        contentPane = new JPanel();//
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        JPanel panel = new JPanel();//Add button
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        JLabel labelLstStore = new JLabel("Liste Store");
        panel.add(labelLstStore);
        panel.add(aff);
        panel.add(addStore);
        panel.add(updateStore);
        panel.add(deleteStore);
        panel.add(affectation);
        DAOStore= new DAOStore(client);
        List<Store> Stores = null;
        try {
            Stores = DAOStore.loadStores();
        } catch (IOException ex) {
            Logger.getLogger(StoreView.class.getName()).log(Level.SEVERE, null, ex);
        }
        //initialize the table (model) with the list of 'magasin'
        modele = new StoreTable(Stores);
        table = new JTable();
        table.setModel(modele);
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        //affect the table to the scrollPane
        scrollPane.setViewportView(table);
        this.controleur = new ControlStore(this,modele,DAOStore);
        aff.addActionListener(controleur);
        deleteStore.addActionListener(controleur);
        addStore.addActionListener(controleur);
        updateStore.addActionListener(controleur);
        affectation.addActionListener(controleur);
        
    } 


    public JTable getTable() {
        return table;
    }
    
    public JButton getAff(){
        return aff;
    }
    
    public ClientSocket getClient() {
        return client;
    }
    public JButton getAffectation() {
        return affectation;
    }

    public JButton getAddStore() {
        return addStore;
    }

    public JButton getDeleteStore() {
        return deleteStore;
    }

    public JButton getUpdateStore() {
        return updateStore;
    }

    public ControlStore getControleur() {
        return controleur;
    }



}
