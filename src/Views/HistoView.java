/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Control.ControlHisto;
import Model.ClientSocket;
import Model.DAOHisto;
import Model.Histo;
import Model.HistoTable;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Michel
 */
public class HistoView extends JFrame {

    private int id;
    private JPanel contentPane;//Panel
    private JScrollPane scrollPane;//The scroolpane for scroll when there is a lot of data 
    private JTable table;//this is a table
    private HistoTable model;//this is the model of the table 
    private ControlHisto control;//this is the controler of this view 
    private DAOHisto dao;// this is the DAO 
    private ClientSocket client;

    public HistoView(int id, ClientSocket client) {
        this.id = id;
        this.client=client;
        setTitle("Historiques");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);//The position and the size of the frame
        contentPane = new JPanel();//
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        JPanel panel = new JPanel();//Add button
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        JLabel labelLstHisto = new JLabel("Historiques");
        panel.add(labelLstHisto);
        dao = new DAOHisto(client);
        List<Histo> histo = null;
        try {
            histo = dao.loadHistoProduct(id);
        } catch (IOException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
        //initialize the table (model) with the list of 'histo'
        model = new HistoTable(histo);
        table = new JTable();
        table.setModel(model);
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        //affect the table to the scrollPane
        scrollPane.setViewportView(table);
        this.control = new ControlHisto(this, model, id);

    }

    public JTable getTable() {
        return table;
    }

    public ClientSocket getClient() {
        return client;
    }

}
