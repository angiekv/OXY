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
public class LocationStoreTable extends AbstractTableModel {
    
    private static final int DESIGNATION_COL = 0;
    private static final int LOCALISATION_COL = 1;
    private static final int NIVEAU_COL = 2;
    private String[] columnNames = {"DESIGNATION", "LOCALISATION_COL"};
    private List<Location> Locations;

    public LocationStoreTable(List theLocations) {
        Locations = theLocations;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return Locations.size();
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {

        Location L = Locations.get(row);

        switch (col) {
            case DESIGNATION_COL:
                return L.getDesignationStoreAffected();
            case LOCALISATION_COL: if(L.getLocalisation()!=null){
                return L.getLocalisation();
            }else{
                return "NON AFFECTEE";
            }
            case NIVEAU_COL:
                return L.getNiveau();
            default:
                return L.getIdEmplacement();   
        }
    }
     
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    public Location getAllLocations(int row){
        Location M = Locations.get(row);
        return M;
    }

}
