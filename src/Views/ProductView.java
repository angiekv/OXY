/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlProduct;
import Model.ClientSocket;
import Model.DAOProduct;
import Model.Product;
import Model.ProductTable;
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
public class ProductView extends JFrame {

    
    private int id;
    private JPanel contentPane;//Panel
    private JScrollPane scrollPane;//The scroolpane for scroll when there is a lot of data 
    private JTable table;//this is a table
    //this are all the buttons 
    private JButton add = new JButton("Ajouter");
    private JButton delete = new JButton("Supprimer");
    private JButton update = new JButton("Modifier");
    private JButton vente = new JButton("Vente");
    private JButton rtc = new JButton("Retour du client");
    private JButton rtf = new JButton("Retour au fournisseur");
    private ProductTable model;//this is the model of the table 
    private ControlProduct control;//this is the controler of this view 
    private DAOProduct dao;// this is the DAO 
    private JButton aff = new JButton("Afficher");
    private ClientSocket client;

    public ProductView(int id,ClientSocket client) {
        this.id=id;
        this.client=client;
            setTitle("Produits");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(200, 200, 900, 300);//The position and the size of the frame 
            contentPane = new JPanel();//
            contentPane.setLayout(new BorderLayout());
            setContentPane(contentPane);
            JPanel panel = new JPanel();//Add button 
            FlowLayout flowLayout = (FlowLayout) panel.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            contentPane.add(panel, BorderLayout.NORTH);
            JLabel labelLstProduct = new JLabel("Liste Produits");
            panel.add(labelLstProduct);
            panel.add(add);
            panel.add(update);
            panel.add(delete);
            panel.add(vente);
            panel.add(rtc);
            panel.add(rtf);
            panel.add(aff);
        dao = new DAOProduct(client);
        List<Product> product = null;
        try {
            product = dao.loadProductStore(id);
        } catch (IOException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
            //initialize the table (model) with the list of 'produit'
            model = new ProductTable(product);
            table = new JTable();
            table.setModel(model);
            scrollPane = new JScrollPane();
            contentPane.add(scrollPane, BorderLayout.CENTER);
            //affect the table to the scrollPane
            scrollPane.setViewportView(table);
            this.control = new ControlProduct(this, model,id,dao);
            delete.addActionListener(control);
            add.addActionListener(control);
            update.addActionListener(control);
            vente.addActionListener(control);
            rtc.addActionListener(control);
            rtf.addActionListener(control);
            aff.addActionListener(control);

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
    
    public JButton getVente(){
        return vente;
    }
    
    public JButton getRtc(){
        return rtc;
    }
    
    public JButton getRtf(){
        return rtf;
    }
    
    public JButton getAff(){
        return aff;
    }
    
    public DAOProduct getDao() {
        return dao;
    }

    public JTable getTable() {
        return table;
    }
    
    public ClientSocket getClient() {
        return client;
    }

    public ControlProduct getControl() {
        return control;
    }
}
