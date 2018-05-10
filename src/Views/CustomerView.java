/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlCustomer;
import Model.ClientSocket;
import Model.Customer;
import Model.CustomerTable;
import Model.DAOCustomer;
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
 * @author Michel
 */
public class CustomerView extends JFrame {

    private JPanel contentPane;//Panel
    private JScrollPane scrollPane;//The scroolpane for scroll when there is a lot of data 
    private JTable table;//this is a table
    //this are all the buttons 
    private JButton add = new JButton("Ajouter");
    private JButton delete = new JButton("Supprimer");
    private JButton update = new JButton("Modifier");
    private JButton afficher = new JButton("Afficher");
    private JButton link = new JButton("Associer profils");
    private CustomerTable model;//this is the model of the table 
    private ControlCustomer control;//this is the controler of this view 
    private DAOCustomer dao;// this is the DAO 
    private ClientSocket client = new ClientSocket();

    public CustomerView() {
        setTitle("Customers");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);//The position and the size of the frame
        contentPane = new JPanel();//
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        JPanel panel = new JPanel();//Add button
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        JLabel labelLstCustomer = new JLabel("List Customers");
        panel.add(labelLstCustomer);
        panel.add(add);
        panel.add(update);
        panel.add(delete);
        panel.add(afficher);
        panel.add(link);
        dao = new DAOCustomer(client);
        List<Customer> customer = null;
        try {
            customer = dao.loadCustomer();
        } catch (IOException ex) {
            Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex);
        }
        //initialize the table (model) with the list of 'customer'
        model = new CustomerTable(customer);
        table = new JTable();
        table.setModel(model);
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        //affect the table to the scrollPane
        scrollPane.setViewportView(table);
        this.control = new ControlCustomer(this, model, dao);
        this.addWindowListener(control);
        delete.addActionListener(control);
        add.addActionListener(control);
        update.addActionListener(control);
        afficher.addActionListener(control);
        link.addActionListener(control);

    }

    public JButton getAdd() {
        return add;
    }

    public JButton getDelete() {
        return delete;
    }

    public JButton getUpdate() {
        return update;
    }

    public JTable getTable() {
        return table;
    }

    public ControlCustomer getControl() {
        return control;
    }

    public DAOCustomer getDao() {
        return dao;
    }

    public ClientSocket getClient() {
        return client;
    }
        public JButton getAfficher() {
        return afficher;
    }
        public JButton getLink(){
            return link;
        }
    
}
