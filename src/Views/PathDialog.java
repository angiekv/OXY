/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.ClientSocket;
import Model.Customer;
import Model.DTOPath;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Angelique
 */
public class PathDialog extends JDialog {
    private JPanel contentPane = new JPanel();
    private JList list;
    private DefaultListModel model = new DefaultListModel();
    private DTOPath dao;
    private CustomerView view;
    private Customer selected = null;
    private ClientSocket c;
    
    public PathDialog(ClientSocket clientSocket, CustomerView C, Customer selected) throws IOException {
        this.c = clientSocket;
        this.dao = new DTOPath(c);
        setTitle("Parcours personnalisé ");
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        List<String> storesPath = null;
        JList list ;

        try {
            storesPath = dao.generateAndShowPath(selected.getIdClient());
            
            
        } catch (IOException ex){
            Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null,ex);
        }
        for (String s : storesPath){
            model.addElement(s);
        }
        list = new JList(model);

//            for (String name : dao.loadProfileById(selected.getIdClient())) {
//            model.addElement(name);
//        }
        JScrollPane scrollPane = new JScrollPane();
//        this.control = new ControlCustomer(this, model, dao);
//        add(pane, BorderLayout.CENTER);
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        //We want to add a button to generate a path for given customer profiles
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        panel.add(list);
    }
    
//    public static void main (String [] args) throws IOException{
//        PathDialog p = new PathDialog(CustomerDialogProfile C, DAOCustomer dao, Customer selected);
//    }
}
