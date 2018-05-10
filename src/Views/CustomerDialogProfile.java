/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlCustomer;
import Model.Customer;
import Model.DAOCustomer;
import Model.Profile;
import Model.ProfileTable;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Michel
 */
public class CustomerDialogProfile extends JDialog {
    
    private JPanel contentPane = new JPanel();
    //table will contain all profile lines on the gui
    private JTable table = new JTable();
    private JList list;
    private ProfileTable model;
    private DAOCustomer dao;
    private CustomerView view;
    private Customer selected = null;

    public CustomerDialogProfile(CustomerView C, DAOCustomer dao, Customer selected) throws IOException {
        setTitle("Profils du client " + selected.getIdClient());
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
//        list = new JList(model);
//        JScrollPane pane = new JScrollPane(list);
        List<String> nameProfiles = null;
        try {
            nameProfiles = dao.loadProfileById(selected.getIdClient());
        } catch (IOException ex){
            Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null,ex);
        }
//            for (String name : dao.loadProfileById(selected.getIdClient())) {
//            model.addElement(name);
//        }
        model = new ProfileTable(nameProfiles);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
//        this.control = new ControlCustomer(this, model, dao);
//        add(pane, BorderLayout.CENTER);
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        //We want to add a button to generate a path for given customer profiles
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(table, BorderLayout.CENTER);
        panel.add(new JButton("Générer un parcours"));
    }

}
