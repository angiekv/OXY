/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Michel
 */
public class ProductOrderHisto {
    
    public static void order(int id,Connection c) throws SQLException{
        List<Order> ListOrder = DAOOrder.loadOrder(id, c);
        for(Order o:ListOrder){
            DAOProduct.updateProductQte(o.getIdp(), o.getQte(), c);
            DAOHisto.addHistoOrder(o.getIdb(), o.getIdp(), o.getQte(), "entrée", c);
        }
    }
    
}
