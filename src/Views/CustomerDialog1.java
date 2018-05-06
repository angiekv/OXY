/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.Customer;
import Model.DAOCustomer;
import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;

/**
 *
 * @author Michel
 */
public class CustomerDialog1 extends JDialog {

    private JList list;
    private DefaultListModel model;
    private DAOCustomer dao;
    private CustomerView view;
    private Customer selected = null;

    public CustomerDialog1(CustomerView C, DAOCustomer dao, Customer selected) throws IOException {
        setTitle("Profils du client " + selected.getIdClient());
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        model = new DefaultListModel();
        list = new JList(model);
        JScrollPane pane = new JScrollPane(list);
        for (String name : dao.loadProfileById(selected.getIdClient())) {

            model.addElement(name);
        }
        add(pane, BorderLayout.CENTER);
    }

}
