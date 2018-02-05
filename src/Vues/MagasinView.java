/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import Modele.DAOMagasin;
import Modele.Magasin;
import Modele.MagasinTableModel;

/**
 *
 * @author aberkane
 */
public class MagasinView extends JFrame {

    private JPanel contentPane;//The panel
    private JScrollPane scrollPane;//The scroolpane for scroll when there is a lot of data 
    private JTable table;//this is the table
    //this are all the button 
    private JButton ajouter = new JButton("Ajouter");
    private JButton supprimer = new JButton("Supprimer");
    private JButton modifier = new JButton("Modifier");
    private MagasinTableModel modele;//this is the modele of the table 
    private Controleur controleur;//this is the controleur of this view 
    private DAOMagasin DAOMagasin;// this is the DAO 

   

    public MagasinView() {
        try {
            //here we initialize the contoleur whith the view ad the modele of the table
            this.controleur = new Controleur(this,modele);
            
            setTitle("Magasins");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 450, 300);//The position and the size of the frame 
            contentPane = new JPanel();//
            contentPane.setLayout(new BorderLayout());
            setContentPane(contentPane);
            JPanel panel = new JPanel();//panel with button
            FlowLayout flowLayout = (FlowLayout) panel.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            contentPane.add(panel, BorderLayout.NORTH);
            JLabel labelLstMagasin = new JLabel("Liste Magasin");
            panel.add(labelLstMagasin);
            panel.add(ajouter);
            panel.add(modifier);
            panel.add(supprimer);
            supprimer.addActionListener(controleur);
            ajouter.addActionListener(controleur);
            modifier.addActionListener(controleur);
            List<Magasin> Magasins = DAOMagasin.chargeMagasin();
            //we initialize the table (model) with the list of 'magasin'
            MagasinTableModel model = new MagasinTableModel(Magasins);
            table = new JTable();
            table.setModel(model);
            scrollPane = new JScrollPane();
            contentPane.add(scrollPane, BorderLayout.CENTER);
            //affect the table to the scrollPane
            scrollPane.setViewportView(table);
        } catch (SQLException ex) {
            Logger.getLogger(MagasinView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //here there is the getter 
    
    
    
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
