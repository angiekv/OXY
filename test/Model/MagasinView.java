/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Views.*;
import Control.Controleur;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
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
import Model.Magasin;
import Model.MagasinTableModel;
import java.io.IOException;

/**
 *
 * @author aberkane
 */
public class MagasinView extends JFrame {

    private JPanel contentPane;//Panel
    private JScrollPane scrollPane;//The scroolpane for scroll when there is a lot of data 
    private JTable table;//this is a table
    //this are all the buttons 
    private JButton ajouter = new JButton("Ajouter");
    private JButton supprimer = new JButton("Supprimer");
    private JButton modifier = new JButton("Modifier");
    private MagasinTableModel modele;//this is the model of the table 
    private Controleur controleur;//this is the controler of this view 
    private DAOStore DAOMagasin;// this is the DAO 
    private ClientSocket client = new ClientSocket();

   

    public MagasinView() {
        try {
            //initialize the contoler with the view and the model of the table
            
            
            setTitle("Magasins");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 450, 300);//The position and the size of the frame 
            contentPane = new JPanel();//
            contentPane.setLayout(new BorderLayout());
            setContentPane(contentPane);
            JPanel panel = new JPanel();//Add button 
            FlowLayout flowLayout = (FlowLayout) panel.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            contentPane.add(panel, BorderLayout.NORTH);
            JLabel labelLstMagasin = new JLabel("Liste Magasin");
            panel.add(labelLstMagasin);
            panel.add(ajouter);
            panel.add(modifier);
            panel.add(supprimer);
            DAOMagasin= new DAOStore(client);
            List<Magasin> Magasins = null;
            try {
                Magasins = DAOMagasin.chargeMagasin();
            } catch (IOException ex) {
                Logger.getLogger(MagasinView.class.getName()).log(Level.SEVERE, null, ex);
            }
            //initialize the table (model) with the list of 'magasin'
            modele = new MagasinTableModel(Magasins);
            table = new JTable();
            table.setModel(modele);
            scrollPane = new JScrollPane();
            contentPane.add(scrollPane, BorderLayout.CENTER);
            //affect the table to the scrollPane
            scrollPane.setViewportView(table);
            this.controleur = new Controleur(this,modele,DAOMagasin);
            supprimer.addActionListener(controleur);
            ajouter.addActionListener(controleur);
            modifier.addActionListener(controleur);
        } catch (SQLException ex) {
            Logger.getLogger(MagasinView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 

    public DAOStore getDAOMagasin() {
        return DAOMagasin;
    }
     
    public JButton getAjouter() {
        return ajouter;
    }

    public JButton getSupprimer() {
        return supprimer;
    }

    public JButton getModifier() {
        return modifier;
    }

    public JTable getTable() {
        return table;
    }

    public Controleur getControleur() {
        return controleur;
    }

}
