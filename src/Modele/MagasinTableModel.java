/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Modele.Magasin;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author aberkane
 */
public class MagasinTableModel extends AbstractTableModel {

    public static final int OBJECT_COL = -1;
    private static final int NUM_COL = 0;
    private static final int NOM_COL = 1;
    private static final int DESCRIPTION_COL = 2;
    private static final int ID_TYPE_COL = 3;
    private String[] columnNames = {"ID", "NOM", "DESCRIPTION","ID TYPE"};
    private List<Magasin> Magasins;

    public MagasinTableModel(List<Magasin> lesMagasin) {
        Magasins = lesMagasin;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return Magasins.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Magasin M = Magasins.get(row);

        switch (col) {
            case OBJECT_COL:
                return M;
            case NUM_COL:
                return M.getNum();
            case NOM_COL:
                return M.getNom();
            case DESCRIPTION_COL:
                return M.getDescription();
            case ID_TYPE_COL:
                return M.getIdType();
            default:
                return M.getNum();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}
