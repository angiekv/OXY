/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Michel
 */
public class StoreTable extends AbstractTableModel {
    
    private static final int ID_COL = 0;
    private static final int DESIGNATION_COL = 1;
    private static final int DESCRIPTION_COL = 2; 
    private String[] columnNames = {"IDMAGASIN", "DESIGNATION", "DESCRIPTION"};
    private List<Store> Stores;

    public StoreTable(List<Store> theStores) {
        Stores = theStores;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return Stores.size();
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {

        Store M = Stores.get(row);

        switch (col) {
            case ID_COL:
                return M.getId();
            case DESIGNATION_COL:
                return M.getDesignation();
            case DESCRIPTION_COL:
                return M.getDescription();
            default:
                return M.getId();   
        }
    }
     
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    public Store getAllStores(int row){
        Store M = Stores.get(row);
        return M;
    }

}
