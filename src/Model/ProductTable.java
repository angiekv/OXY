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
public class ProductTable extends AbstractTableModel {
    
    private static final int ID_COL = 0;
    private static final int LIBELLE_COL = 1;
    private static final int PRIX_COL = 2; 
    private static final int QTE_COL = 3;
    private static final int MAGASIN_IDMAGASIN_COL = 4;
    private String[] columnNames = {"CODE BARRE", "LIBELLE", "PRIX","QTE","IDMAGASIN"};
    private List<Product> Products;

    public ProductTable(List<Product> theProducts) {
        Products = theProducts;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return Products.size();
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {

        Product P = Products.get(row);

        switch (col) {
            case ID_COL:
                return P.getIdProduit();
            case LIBELLE_COL:
                return P.getLibelle();
            case PRIX_COL:
                return P.getPrix();
            case QTE_COL:
                return P.getQte();
            case MAGASIN_IDMAGASIN_COL:
                return P.getMagasin_idMagasin();
            default:
                return P.getIdProduit();   
        }
    }
     
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    public Product getAllProducts(int row){
        Product P = Products.get(row);
        return P;
    }

}
