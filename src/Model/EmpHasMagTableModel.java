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
 * @author BaDiakite
 */
public class EmpHasMagTableModel extends AbstractTableModel{

    
    private static final int DESIGNATION_COL = 0;
    private static final int LOCALISATION_COL = 1;
    private static final int REDEVANCE_COL = 2;
    private static final int DATE_COL = 3; 
    private String[] columnNames = {"NOM", "LOCALISATION", "REDEVANCE", "DATE"};
    private List<EmpHasMag> lesEmps;

    public EmpHasMagTableModel(List<EmpHasMag> theEmps) {
        lesEmps = theEmps;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return lesEmps.size();
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {

        EmpHasMag M = lesEmps.get(row);

        switch (col) {
            case DESIGNATION_COL:
                return M.getNomMag();
            case LOCALISATION_COL:
                return M.getLocalisation();
            case REDEVANCE_COL:
                return M.getRedevance();
            case DATE_COL:
                return M.getDate();
            default:
                return M.getNomMag();   
        }
    }
     
    
    
    public EmpHasMag getAllStores(int row){
        EmpHasMag M = lesEmps.get(row);
        return M;
    }

    
}

    

