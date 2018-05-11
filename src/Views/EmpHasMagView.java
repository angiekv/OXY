/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.EmpHasMagTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.ControlEmpHasMag;
import Model.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.List;
import javax.swing.JLabel;


/**
 *
 * @author aberkane
 */
public class EmpHasMagView extends JFrame {

    private JPanel contentPane;//Panel
    private JScrollPane scrollPane;//The scroolpane for scroll when there is a lot of data 
    private JTable table;//this is a table
    private JButton b = new JButton("Calcul Redevance");
    private ControlEmpHasMag control;
    //this are all the buttons 
    private EmpHasMagTableModel modele;//this is the model of the table 
    private DAOEmpHasMag DAOEmpHasMag;// this is the DAO 
    private DAORent daorent;
    private ClientSocket client ;

    public EmpHasMagView(ClientSocket c) throws IOException {
        //initialize the contoler with the view and the model of the table
        this.client = c;
        
        setTitle("Rents");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);//The position and the size of the frame
        contentPane = new JPanel();//
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        JPanel panel = new JPanel();//Add button
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        JLabel labelLstRent = new JLabel("Liste Rent");
        panel.add(labelLstRent);
        panel.add(b);
        DAOEmpHasMag = new DAOEmpHasMag(client);
        daorent = new DAORent(client);
        List<EmpHasMag> Emps = DAOEmpHasMag.loadEmpHasMag();

//        System.out.println(Emps);
//        //initialize the table (model) with the list of 'magasin'
        modele = new EmpHasMagTableModel(Emps);
        table = new JTable();
        control = new ControlEmpHasMag(this, modele);
        b.addActionListener(control);
        table.setModel(modele);
        scrollPane = new JScrollPane();
        
        
        contentPane.add(scrollPane, BorderLayout.CENTER);
////        //affect the table to the scrollPane
        scrollPane.setViewportView(table);

    }

    public DAOEmpHasMag getDAOEmpHasMag() {
        return DAOEmpHasMag;
    }

    public ControlEmpHasMag getControl() {
        return control;
    }

    public EmpHasMagTableModel getModele() {
        return modele;
    }

    public DAORent getDaorent() {
        return daorent;
    }

    public ClientSocket getClient() {
        return client;
    }
    
    public JTable getTable() {
        return table;
    }
    
    public JButton getB(){
        return b;
    }

    public static void main(String[] args) throws IOException {
//        EmpHasMagView r = new EmpHasMagView();
//        r.setVisible(true);
    }
    

}
