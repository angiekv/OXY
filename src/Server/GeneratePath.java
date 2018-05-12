/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import static Server.DAOCustomer.loadProfileById;
import static Server.DAOStore.loadStoresByProfile;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Angelique
 */
public class GeneratePath {
    
    public List<String> generatePath(Connection c, int idClient, List<String> profils) throws SQLException{
        List<String> stores = new ArrayList<>();
        for(String p : profils){
            if (p.endsWith("+++")){                
                stores.addAll(DAOStore.loadStoresByProfile(c, p, 4));
                return stores;
            } else if (p.endsWith("++")) {
                stores.addAll(DAOStore.loadStoresByProfile(c, p, 3));
            } else if (p.endsWith("+")){
                stores.addAll(DAOStore.loadStoresByProfile(c, p, 2));
            } else stores.addAll(DAOStore.loadStoresByProfile(c, p, 1));
        }
        return stores;
    }
    
    public static void main (String [] args) throws SQLException{
        GeneratePath genPath = new GeneratePath();
        Connection c = Database.getConnection();
//        List<String> profils = loadProfileById(c, 1);
        System.out.println(loadProfileById(c, 24));
        System.out.println(genPath.generatePath(c, 24, loadProfileById(c, 24)));
    }
}
