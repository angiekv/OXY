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
 * @author Angelique
 */
public class ProfileTable extends AbstractTableModel{
    private static final int PROFILE = 0;
    private String[] columnName = {"PROFIL(S)"};
    private List<String> profiles;
    
    public ProfileTable(List<String> profiles){
        this.profiles = profiles;
    }

    @Override
    public int getRowCount() {
        return profiles.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String name = profiles.get(rowIndex);
        switch(columnIndex){
            case PROFILE:
                return name;
            default:
                return name;
        }
    }
    
    public String getAllProfiles(int row){
        String p = profiles.get(row);
        return p;
    }
    
    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }
}
