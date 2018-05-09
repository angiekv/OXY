/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlAffectation;
import Model.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Aberkane
 */
public class AllocationView extends JFrame {
    private JPanel contentPane;//Panel
    private JScrollPane scrollPane;//The scroolpane for scroll when there is a lot of data 
    private JTable table;
    
    //this are all the buttons 
    private JButton affect = new JButton("Affecter Emplacement");
    
    private LocationStoreTable modele;//this is the model of the table 
    private ControlAffectation controleur;//this is the controler of this view 
    private DAOLocation DAOLocation;// this is the DAO 
    private ClientSocket client = new ClientSocket();
    public AllocationView () {
        setTitle("Allocation store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);//The position and the size of the frame
        contentPane = new JPanel();//
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        JPanel panel = new JPanel();//Add button
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        panel.add(affect);
        DAOLocation= new DAOLocation(client);
        List<Location> LocationStore = null;
        try {
            LocationStore = DAOLocation.loadLocationAndStores();
        } catch (IOException ex) {
            Logger.getLogger(AllocationView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //initialize the table (model) with the list of 'magasin'
        modele = new LocationStoreTable(LocationStore);
        table = new JTable();
        table.setModel(modele);
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        //affect the table to the scrollPane
        scrollPane.setViewportView(table);
        this.controleur = new ControlAffectation(this,modele,DAOLocation);
        affect.addActionListener(controleur);
        
    }

    public JTable getTable() {
        return table;
    }

    public JButton getAffect() {
        return affect;
    }

    public LocationStoreTable getModele() {
        return modele;
    }

    public DAOLocation getDAOLocation() {
        return DAOLocation;
    }

    public ClientSocket getClient() {
        return client;
    }
    
    
    
}
