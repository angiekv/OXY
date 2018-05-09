/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OXY
 */
public class DAOStore {

    /**
     * This method selects all the shops and puts the shops into a list.
     *
     * @return the list of all the shops.
     * @throws SQLException
     */

    public synchronized static List<Store> loadStores(Connection c) throws SQLException {
        //list of shop
        List<Store> listShop = new ArrayList<>();
        Statement myStmt = c.createStatement();
        //The query which selects all the shops.
        ResultSet myRs = myStmt.executeQuery("select * from magasin");
        //Loop which add a shop to the list.
        while (myRs.next()) {
            int id = myRs.getInt("idMagasin");
            String designation = myRs.getString("designation");
            String description = myRs.getString("description");
            int loyer = myRs.getInt("loyer");
            int surface = myRs.getInt("superficie");
            int niveau = myRs.getInt("niveau");
            String localisation = myRs.getString("localisation");
            //liste type 
            Statement myStmt2 = c.createStatement();
            //The query which selects all the shops.
            ResultSet myRs2 = myStmt2.executeQuery("SELECT designation,idType from magasin_has_type,type where magasin_has_type.type_idType=type.idType and magasin_has_type.magasin_idMagasin=" + id);
            List<Type> list = new ArrayList<>();
            while (myRs2.next()) {
                int idtype = myRs2.getInt("idType");
                String designationType = myRs2.getString("designation");
                Type T = new Type(idtype, designationType);
                list.add(T);
            }
            Store M = new Store(id, designation, description, loyer, surface, niveau, localisation, list);

            listShop.add(M);
        }
        myStmt.close();
        return listShop;

    }

    /**
     * This method returns only the shops which types matches a customer's
     * profile
     *
     * @param c
     * @param profil
     * @param idType
     * @return
     * @throws java.sql.SQLException
     */
    public synchronized static List<Store> loadStores(Connection c, String profil) throws SQLException {
        List<Store> filteredShopList = new ArrayList<>();
        //The query which selects all the shops
        try (Statement myStmt = c.createStatement()) {
            //The query which selects all the shops matching the profile
            ResultSet myRs = myStmt.executeQuery("select magasin.designation, magasin.localisation from magasin, magasin_has_type, type where magasin.idMagasin = magasin_has_type.magasin_idMagasin and magasin_has_type.type_idtype = type.idType and type.designation LIKE " + profil);
            //Loop which adds a shop to the list.
            while (myRs.next()) {
//                int id = myRs.getInt("idMagasin");
                String designation = myRs.getString("designation");
//                String description = myRs.getString("description");
//                int rent = myRs.getInt("loyer");
//                int surface = myRs.getInt("superficie");
//                int floor = myRs.getInt("niveau");
                String localization = myRs.getString("localisation");
                Store s = new Store(designation, localization);
                filteredShopList.add(s);
            }
        }
        return filteredShopList;
    }

    /**
     * This method updates a shop.
     *
     * @param idShop
     * @param designation
     * @param description
     * @param rent
     * @param surface
     * @param floor
     * @param localization
     * @param idType
     * @param c
     * @throws SQLException
     */
    public synchronized void updateShop(int idShop, String designation, String description, int loyer, int surface, int niveau, String localisation, List<Integer> ListidTypeOld, List<Integer> ListidTypeNew, Connection c) throws SQLException {
        //The query which selects all the shops.

        PreparedStatement myStmt2 = null;
        //The query which modifies one or more attributes of a shop.
        myStmt2 = c.prepareStatement("update magasin set designation=? ,description=?, loyer=? , superficie=? , niveau=? , localisation=? where idMagasin=? ");

        //The attributes' values are placed in the same order as "?".
        myStmt2.setString(1, designation);
        myStmt2.setString(2, description);
        myStmt2.setInt(3, loyer);
        myStmt2.setInt(4, surface);
        myStmt2.setInt(5, niveau);
        myStmt2.setString(6, localisation);
        myStmt2.setInt(7, idShop);
        myStmt2.executeUpdate();
        //This request update the type of the shop ;
        PreparedStatement myStmt3 = null;
        int sizeOld = ListidTypeOld.size();
        int indexTabNew = 0;
        for (int i = 0; i < sizeOld; i++) {//  loop for type of old with index i
            int idType = ListidTypeOld.get(i);
            if (ListidTypeNew.contains(indexTabNew)) {
                for (int j = 0; j < ListidTypeNew.size(); j++) {// boulce for delete the type if this type is in both list (old and anew ) index j 
                    if (ListidTypeNew.get(j) == idType) {
                        ListidTypeNew.remove(j);

                        break;
                    }
                }
                ListidTypeOld.remove(i);
                indexTabNew++;
            }

        }
        if (!ListidTypeNew.isEmpty()) {
            for (int newIdType : ListidTypeNew) {

                int idTypeOld = ListidTypeOld.get(0);
                myStmt3 = c.prepareStatement("update magasin_has_type set type_idType=? where magasin_idMagasin=? and type_idType= ?");
                myStmt3.setInt(1, newIdType);
                myStmt3.setInt(2, idShop);
                myStmt3.setInt(3, idTypeOld);
                myStmt3.executeUpdate();
                ListidTypeOld.remove(0);

            }
        }

    }

    /**
     * This method deletes a shop from the database.
     *
     * @param idShop
     * @param c
     * @throws SQLException
     */
    public synchronized void deleteShop(int idShop, Connection c) throws SQLException {

        PreparedStatement myStmt = null;
        //The query deletes type of the shop we are going to delete from the database.
        myStmt = c.prepareStatement("delete From magasin_has_type where magasin_idMagasin=?");
        myStmt.setInt(1, idShop);
        myStmt.executeUpdate();

        PreparedStatement myStmt2 = null;

        //The query deletes a shop from the database.
        myStmt2 = c.prepareStatement("delete From magasin where idMagasin=?");
        myStmt2.setInt(1, idShop);
        myStmt2.executeUpdate();;
    }
    /**
     * This method return a list of the store not affect to a location
     * @param c
     * @return
     * @throws SQLException 
     */
    public synchronized static List<Store> loadStoresNotAffectToLocation(Connection c) throws SQLException {
        //list of shop
        List<Store> listShop = new ArrayList<>();
        Statement myStmt = c.createStatement();
        //The query which selects all the shops.
        ResultSet myRs = myStmt.executeQuery("select * from magasin where idMagasin not in (select Magasin_idMagasin from emplacement_has_magasin)");
        //Loop which add a shop to the list.
        while (myRs.next()) {
            int id = myRs.getInt("idMagasin");
            String designation = myRs.getString("designation");
            String description = myRs.getString("description");
            int loyer = myRs.getInt("loyer");
            int surface = myRs.getInt("superficie");
            int niveau = myRs.getInt("niveau");
            String localisation = myRs.getString("localisation");
            //liste type 
            Statement myStmt2 = c.createStatement();
            //The query which selects all the shops.
            ResultSet myRs2 = myStmt2.executeQuery("SELECT designation,idType from magasin_has_type,type where magasin_has_type.type_idType=type.idType and magasin_has_type.magasin_idMagasin=" + id);
            List<Type> list = new ArrayList<>();
            while (myRs2.next()) {
                int idtype = myRs2.getInt("idType");
                String designationType = myRs2.getString("designation");
                Type T = new Type(idtype, designationType);
                list.add(T);
            }
            Store M = new Store(id, designation, description, loyer, surface, niveau, localisation, list);

            listShop.add(M);
        }
        myStmt.close();
        return listShop;

    }

    public synchronized void addShop(String designation, String description, int loyer, int surface, int niveau, String localisation, List<Integer> ListidType, Connection c) throws SQLException {
        //The query adds a shop to the database.
        PreparedStatement myStmt = null;
        myStmt = c.prepareStatement("insert into magasin (designation,description,loyer,superficie,niveau,localisation) values (?,?,?,?,?,?) ");

        //The attributes' values are placed in the same order as "?".
        myStmt.setString(1, designation);
        myStmt.setString(2, description);
        myStmt.setInt(3, loyer);
        myStmt.setInt(4, surface);
        myStmt.setInt(5, niveau);
        myStmt.setString(6, localisation);
        myStmt.executeUpdate();
        Statement myStmt2 = c.createStatement();
        //The query which selects all the shops.
        ResultSet myRs = myStmt2.executeQuery("select last_insert_id() as last_id from magasin");
        myRs.next();
        int lastid = myRs.getInt("last_id");
        //This request insert the type of the shop in database;
        for (int idType : ListidType) {
            PreparedStatement myStmt3 = null;
            myStmt3 = c.prepareStatement("insert into magasin_has_type values (?,?)");
            myStmt3.setInt(1, lastid);
            myStmt3.setInt(2, idType);
            myStmt3.executeUpdate();
        }

    }

    /*test */
//    public static void main(String[] args) throws Exception {
////        DAOStore dao = new DAOStore();
//        ConnectionPool pool = new ConnectionPool();
//        pool.initPool();
//        Connection c = pool.getConnection();
////        System.out.println(dao.loadStores(c));
////      dao.addShop("hetm", "vetemeent", 20000, 110, 1, "SORTIE", 1, c);
////      Selects only clothing shops
//        System.out.println(loadStoresNotAffectToLocation(c));
//        pool.releaseConnection(c);
//    }
}
