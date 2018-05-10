/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OXY
 */
public class DAOType {

    /**
     * This method selects all the TYPE and puts the TYPE into a list.
     *
     * @return the list of all the type.
     * @throws SQLException
     */
    public synchronized static List<TypeStore> loadType(Connection c) throws SQLException {
        //list of Type
        List<TypeStore> listTypeStore = new ArrayList<>();
        Statement myStmt = c.createStatement();
        //The query which selects all the Type.
        ResultSet myRs = myStmt.executeQuery("select * from Type");
        //Loop which add a Type to the list.
        while (myRs.next()) {
            int id = myRs.getInt("idType");
            String designation = myRs.getString("designation");
            TypeStore T = new TypeStore(id, designation);

            listTypeStore.add(T);
        }
        myStmt.close();
        return listTypeStore;

    }
    
    public synchronized static int getIdType(Connection c,String desingnation) throws SQLException {
        //list of Type
        System.out.println(desingnation);
        Statement myStmt = c.createStatement();
        //The query which selects all the Type.
        ResultSet myRs = myStmt.executeQuery("SELECT idType FROM `type` WHERE designation='"+desingnation+"'");
        //Loop which add a Type to the list.
        myRs.next();
            int id = myRs.getInt("idType");
        return id;

    }
    



/*test */
    public static void main(String[] args) throws Exception {
        DAOType dao = new DAOType();
        ConnectionPool pool = new ConnectionPool();
        pool.initPool();
        Connection c = pool.getConnection();

////      dao.addShop("hetm", "vetemeent", 20000, 110, 1, "SORTIE", 1, c);
////      Selects only clothing shops
//        System.out.println(loadStoresNotAffectToLocation(c));
//        pool.releaseConnection(c);
    }

}