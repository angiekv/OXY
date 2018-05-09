/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Michel
 */
public class HistoTable extends AbstractTableModel {
    
    private static final int IDP_COL = 0;
    private static final int QTE_COL = 1;
    private static final int DATE_COL = 2;
    private static final int ACTION_COL = 3; 
    private String[] columnNames = {"CODE BARRE","QTE","DATE","MOUVEMENT"};
    private List<Histo> Histos;

    public HistoTable(List<Histo> theHistos) {
        Histos = theHistos;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return Histos.size();
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {

        Histo H = Histos.get(row);

        switch (col) {  
            case IDP_COL:
                return H.getIdp();
            case QTE_COL:
                return H.getQte();
            case DATE_COL:
                return H.getDate(); 
            case ACTION_COL:
                return H.getAction();    
            default:
                return H.getIda();   
        }
    }
     
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    public Histo getAllHistos(int row){
        Histo H = Histos.get(row);
        return H;
    }

}
