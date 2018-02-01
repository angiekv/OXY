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

    JPanel contentPane;
    JTextField lastNameTextField;
    JButton btnSearch;
    JScrollPane scrollPane;
    JTable table;
    JButton ajouter = new JButton("Ajouter");
    JButton supprimer = new JButton("Supprimer");
    JButton modifier = new JButton("Modifier");
    MagasinTableModel modele;
    Controleur controleur;
    
    private DAOMagasin DAOMagasin;

   

    public MagasinView() {
        this.controleur = new Controleur(this,modele);
        try {
            try {
                DAOMagasin = new DAOMagasin();
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
            }
            setTitle("Magasins");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 450, 300);
            contentPane = new JPanel();
            contentPane.setLayout(new BorderLayout(0, 0));
            setContentPane(contentPane);
            JPanel panel = new JPanel();
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
            MagasinTableModel model = new MagasinTableModel(Magasins);
            table = new JTable();
            table.setModel(model);
            scrollPane = new JScrollPane();
            contentPane.add(scrollPane, BorderLayout.CENTER);
            scrollPane.setViewportView(table);
        } catch (SQLException ex) {
            Logger.getLogger(MagasinView.class.getName()).log(Level.SEVERE, null, ex);
        }
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
