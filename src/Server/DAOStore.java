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
            List<TypeStore> list = new ArrayList<>();
            while (myRs2.next()) {
                int idtype = myRs2.getInt("idType");
                String designationType = myRs2.getString("designation");
                TypeStore T = new TypeStore(idtype, designationType);
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
    public synchronized static List<String> loadStoresByProfile(Connection c, String profil, int lim) throws SQLException {
        List<String> filteredShopList = new ArrayList<>();
        //The query which selects all the shops
        Statement myStmt = c.createStatement();
        //The query which selects all the shops matching the profile
        ResultSet myRs = myStmt.executeQuery("select magasin.* from magasin, magasin_has_type, type where magasin.idMagasin = magasin_has_type.magasin_idMagasin and magasin_has_type.type_idtype = type.idType and type.designation LIKE '" + profil +"%' limit "+ lim);
        //Loop which adds a shop to the list.
        while (myRs.next()) {
            int id = myRs.getInt("idMagasin");
            String designation = myRs.getString("designation");
            String description = myRs.getString("description");
            int rent = myRs.getInt("loyer");
            int surface = myRs.getInt("superficie");
            int floor = myRs.getInt("niveau");
            String localization = myRs.getString("localisation");
            //liste type 
//            Statement myStmt2 = c.createStatement();
//            //The query which selects all the shops.
//            ResultSet myRs2 = myStmt2.executeQuery("SELECT designation,idType from magasin_has_type,type where magasin_has_type.type_idType=type.idType and magasin_has_type.magasin_idMagasin=" + id);
//            List<TypeStore> list = new ArrayList<>();
//            while (myRs2.next()) {
//                int idtype = myRs2.getInt("idType");
//                String designationType = myRs2.getString("designation");
//                TypeStore T = new TypeStore(idtype, designationType);
//                list.add(T);
//            }
            filteredShopList.add(designation);
        }
        myStmt.close();
        return filteredShopList;
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
        Statement myStmt = c.createStatement();
        //The query which selects all the shops matching the profile
        ResultSet myRs = myStmt.executeQuery("select magasin.* from magasin, magasin_has_type, type where magasin.idMagasin = magasin_has_type.magasin_idMagasin and magasin_has_type.type_idtype = type.idType and type.designation LIKE " + profil);
        //Loop which adds a shop to the list.
        while (myRs.next()) {
            int id = myRs.getInt("idMagasin");
            String designation = myRs.getString("designation");
            String description = myRs.getString("description");
            int rent = myRs.getInt("loyer");
            int surface = myRs.getInt("superficie");
            int floor = myRs.getInt("niveau");
            String localization = myRs.getString("localisation");
            //liste type 
            Statement myStmt2 = c.createStatement();
            //The query which selects all the shops.
            ResultSet myRs2 = myStmt2.executeQuery("SELECT designation,idType from magasin_has_type,type where magasin_has_type.type_idType=type.idType and magasin_has_type.magasin_idMagasin=" + id);
            List<TypeStore> list = new ArrayList<>();
            while (myRs2.next()) {
                int idtype = myRs2.getInt("idType");
                String designationType = myRs2.getString("designation");
                TypeStore T = new TypeStore(idtype, designationType);
                list.add(T);
            }
            Store s = new Store(id, designation, description, rent, surface, floor, localization, list);
            filteredShopList.add(s);
        }
        myStmt.close();
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
    public synchronized static void updateShop(int idShop, String designation, String description, int loyer, int surface, int niveau, String localisation, List<Integer> ListidTypeOld, List<Integer> ListidTypeNew, Connection c) throws SQLException {
        //The query which selects all the shops.
        System.out.println("taille : " + ListidTypeOld.size());
        System.out.println(ListidTypeOld);
        System.out.println(ListidTypeNew);
        PreparedStatement myStmt2 = null;
        //The query which modifies one or more attributes of a shop.
        myStmt2 = c.prepareStatement("update magasin set designation=? ,description=?, loyer=? , superficie=? , niveau=? , localisation=? where idMagasin=? ");

        //The attributes' values are placed in the same order as "?".
        System.out.println(designation);
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
        PreparedStatement myStmt4 = null;
//        int sizeOld = ListidTypeOld.size();
//        int indexTabNew = 0;
//        int idType = 0;

        //on recupere les id des type 
        int oldType1 = ListidTypeOld.get(0);
        int oldType2 = ListidTypeOld.get(1);
        int newType1 = ListidTypeNew.get(0);
        int newType2 = ListidTypeNew.get(1);

        if (newType1 != oldType1 && newType1 != oldType2) {
            if (newType1 == 0) {
                myStmt3 = c.prepareStatement("delete From magasin_has_type where magasin_idMagasin=? and type_idType=?");
                myStmt3.setInt(1, idShop);
                myStmt3.setInt(2, oldType1);
                myStmt3.executeUpdate();

                // if we update a type 
            } else {
                myStmt3 = c.prepareStatement("update magasin_has_type set type_idType=? where magasin_idMagasin=? and type_idType= ?");
                myStmt3.setInt(1, newType1);
                myStmt3.setInt(2, idShop);
                myStmt3.setInt(3, oldType1);
                myStmt3.executeUpdate();
                ListidTypeOld.remove(0);
            }
        }
        if (newType2 != oldType2) {
            // if we delete a type
            if (newType2 == 0) {
                myStmt4 = c.prepareStatement("delete From magasin_has_type where magasin_idMagasin=? and type_idType=?");
                myStmt4.setInt(1, idShop);
                myStmt4.setInt(2, oldType2);
                myStmt4.executeUpdate();

            }
            // if we add a type 
            if (oldType2 == 0 && newType2 != 0) {
                System.out.println("oooo");
                myStmt4 = c.prepareStatement("insert into magasin_has_type values (?,?)");
                myStmt4.setInt(1, idShop);
                myStmt4.setInt(2, newType2);
                myStmt4.executeUpdate();
                // if we update a type 
            }
            if (oldType2 != 0 && newType2 != 0) {
                myStmt4 = c.prepareStatement("update magasin_has_type set type_idType=? where magasin_idMagasin=? and type_idType= ?");
                myStmt4.setInt(1, newType2);
                myStmt4.setInt(2, idShop);
                myStmt4.setInt(3, oldType2);
                myStmt4.executeUpdate();
                System.out.println(oldType2 + "" + newType2);
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
    public synchronized static void deleteStore(int idShop, Connection c) throws SQLException {

//        PreparedStatement myStmt = null;
//        //The query deletes type of the shop we are going to delete from the database.
//        myStmt = c.prepareStatement("delete From magasin_has_type where magasin_idMagasin=?");
//        myStmt.setInt(1, idShop);
//        myStmt.executeUpdate();
        PreparedStatement myStmt2 = null;

        //The query deletes a shop from the database.
        myStmt2 = c.prepareStatement("delete From magasin where idMagasin=?");
        myStmt2.setInt(1, idShop);
        myStmt2.executeUpdate();
    }

    /**
     * This method return a list of the store not affect to a location
     *
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
            List<TypeStore> list = new ArrayList<>();
            while (myRs2.next()) {
                int idtype = myRs2.getInt("idType");
                String designationType = myRs2.getString("designation");
                TypeStore T = new TypeStore(idtype, designationType);
                list.add(T);
            }
            Store M = new Store(id, designation, description, loyer, surface, niveau, localisation, list);

            listShop.add(M);
        }
        myStmt.close();
        return listShop;

    }

    public synchronized static void addShop(String designation, String description, int loyer, int surface, int niveau, String localisation, List<Integer> ListidType, Connection c) throws SQLException {
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
            if (idType != 0) {
                PreparedStatement myStmt3 = null;
                myStmt3 = c.prepareStatement("insert into magasin_has_type values (?,?)");
                myStmt3.setInt(1, lastid);
                myStmt3.setInt(2, idType);
                myStmt3.executeUpdate();
            }
        }
    }

    /*test */
//    public static void main(String[] args) throws Exception {
//        DAOStore dao = new DAOStore();
//        ConnectionPool pool = new ConnectionPool();
//        pool.initPool();
//        Connection c = pool.getConnection();
////        System.out.println(loadStores(c, "'mode'"));
////        System.out.println(loadStores(c));
//
////        System.out.println(dao.loadStores(c));
//        List<Integer> types = new ArrayList<>();
//        types.add(5);
//        dao.addShop("carrefour", "grande distribution", 20000, 110, 1, "indifférent", types, c);
////      Selects only clothing shops
////        System.out.println(loadStoresNotAffectToLocation(c));
//        pool.releaseConnection(c);
//    }
}
