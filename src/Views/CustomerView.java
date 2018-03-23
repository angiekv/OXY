/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.Control;
import Model.ClientSocket;
import Model.Customer;
import Model.CustomerTable;
import Model.DAOCustomer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
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
    private CustomerTable model;//this is the model of the table 
    private Control control;//this is the controler of this view 
    private DAOCustomer dao;// this is the DAO 
    private ClientSocket client = new ClientSocket();

    public CustomerView() {
        setTitle("Customers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        dao = new DAOCustomer(client);
        List<Customer> customer;
        customer = dao.loadCustomer();
        //initialize the table (model) with the list of 'customer'
        model = new CustomerTable(customer);
        table = new JTable();
        table.setModel(model);
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        //affect the table to the scrollPane
        scrollPane.setViewportView(table);
        this.control = new Control(this, model, dao);
        delete.addActionListener(control);
        add.addActionListener(control);
        update.addActionListener(control);

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

    public Control getControl() {
        return control;
    }
}
