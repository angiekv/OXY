/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlHome;
import Control.ControlStore;
import Model.ClientSocket;
import Model.DAOStore;
import Model.Store;
import Model.StoreTable;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Aberkane
 */
public class HomeView extends JFrame {
    private JPanel contentPane;//Panel
    private JButton store = new JButton("Afficher les magasin");
    private JButton customer = new JButton("Afficher les client");;
    private JLabel home = new JLabel("Accueil");
    private ClientSocket client = new ClientSocket();
    private ControlHome control;
    public HomeView() {
        client.startConnection();
        setTitle("Accueil OXY ");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);//The position and the size of the frame
        contentPane = new JPanel();//
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        JPanel panel = new JPanel();//Add button
        contentPane.add(panel, BorderLayout.CENTER);
        
        panel.add(home);
        panel.add(store);
        panel.add(customer);

        //initialize the table (model) with the list of 'magasin'
        //affect the table to the scrollPane
        
        this.control = new ControlHome(this);
        this.addWindowListener(control);
        store.addActionListener(control);
        customer.addActionListener(control);
    }

    public JButton getStore() {
        return store;
    }

    public JButton getCustomer() {
        return customer;
    }

    public ClientSocket getClient() {
        return client;
    }
    
    
    
    
}
