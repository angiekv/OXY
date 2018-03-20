/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Customer;
import Model.Customer;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Michel
 */
public class CustomerTable extends AbstractTableModel {

// This is the model of the table which will be displayed in the GUI.
    private static final int ID_COL = 0;
    private static final int NOM_COL = 1;
    private static final int PRENOM_COL = 2; 
    private static final int ADRESSE_COL = 3;
    private static final int CP_COL = 4;
    private static final int VILLE_COL = 5;
    private static final int MAIL_COL = 6;
    private static final int SEXE_COL = 7;
    private String[] columnNames = {"IDCLIENT", "NOM", "PRENOM","ADRESSE","CP","VILLE","MAIL","SEXE"};
    private List<Customer> Customers;

    public CustomerTable(List<Customer> theCustomers) {
        Customers = theCustomers;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return Customers.size();
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {

        Customer C = Customers.get(row);

        switch (col) {
            case ID_COL:
                return C.getIdClient();
            case NOM_COL:
                return C.getNom();
            case PRENOM_COL:
                return C.getPrenom();
            case ADRESSE_COL:
                return C.getAdresse();
            case CP_COL:
                return C.getCp(); 
            case VILLE_COL:
                return C.getVille();  
            case MAIL_COL:
                return C.getMail();  
            case SEXE_COL:
                return C.getSexe();    
            default:
                return C.getIdClient();
        }
    }
     
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    public Customer getAllCustomers(int row){
        Customer C = Customers.get(row);
        return C;
    }

}

