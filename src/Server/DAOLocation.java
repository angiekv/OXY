/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import static Server.ManagePurchase.generateRandomInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelique
 */
public class DAOLocation {

    public synchronized static void addLocation(Connection c) throws SQLException {
//         createLocation(); impossible d'importer depuis la classe de test
//          methode d'Aberkane :
        int i = 1;// indice emplacement 
        String direction = null;
        int superficie;
        int loyer;
        int niveau = 1;
        String localization;
        int qualite = 5;
        int frequencetheorique = 0;
        //boucle sur le 120 MAGASIN ON NE MET PAS DE MAGASIN AU CENTRE pour l'instant 
        while (i <= 150) {
            // when we reach 75 Shop in the level 1  we fo to the level 2
            qualite = 5;
            if (i > 75) {
                niveau = 2;
            }

            // with this 4 CONDITION WE affecte the shop to a section of the shopping center (north , east,south ,west)
            if ((i >= 1 && i <= 13) || (i >= 64 && i <= 88) || (i >= 139 && i <= 150)) {
                direction = "N";
            }
            if ((i >= 14 && i <= 25) || (i >= 89 && i <= 100)) {
                direction = "E";

            }
            if ((i >= 26 && i <= 51) || (i >= 101 && i <= 126)) {
                direction = "S";

            }
            if ((i >= 52 && i <= 63) || (i >= 127 && i <= 138)) {
                direction = "W";
            }

            // localistion fait 
            localization = direction + i;
            //superficie fait 
            superficie = generateRandomInteger(40, 200);
            // loyer en fonction de la superficie fait 
            loyer = 200 * superficie;
            // hausse du loyer si proche des sortie 
            if (i == 1 || i == 75 || i == 19 || i == 20 || i == 57 || i == 58) {
                loyer = loyer + 150;
            }
            // gestion de la qualité en fonction de la superficie de basse la qualité est a 5
            if (superficie >= 170) {
                qualite = qualite + 3;
            }
            // a coté de l asortie qualité + 2 PAS DE SORTI AU SUD
            if (i == 1 || i == 75 || i == 19 || i == 20 || i == 57 || i == 58) {
                qualite = qualite + 2;
            }

            // A 3 MAGASIN DE LA SORTIE ALORS QUALITE + 1
            if ((i >= 2 && i <= 4) || i == 18 || i == 21 || i == 56 || i == 59 || (i >= 72 && i <= 74)) {
                qualite = qualite + 1;
            }
            // FREQUENCE THEORIQUE ENTRE 100000 ET 10000
            if (qualite == 10) {
                frequencetheorique = 100000;
            }

            if (qualite == 9) {
                frequencetheorique = 80000;
            }
            if (qualite == 8) {
                frequencetheorique = 70000;
            }
            if (qualite == 7) {
                frequencetheorique = 50000;
            }
            if (qualite == 6) {
                frequencetheorique = 40000;
            }
            if (qualite == 5) {
                frequencetheorique = 30000;
            }

            //Emplacement E = new Emplacement(superficie, loyer, localisation, qualite, niveau, frequencetheorique);
            //listLocationsBySurface.add(E);
            PreparedStatement myStmt = null;
            //Connection to the database.
            Connection myConn = Database.getConnection();
//               Connection myConn = Database.getConnection();
            //The query adds a shop to the database.
            myStmt = myConn.prepareStatement("insert into emplacement (`superficie`, `loyer_initial`, `localisation`, `qualite`, `niveau`, `freqth`) values (?,?,?,?,?,?)");
            myStmt.setInt(1, superficie);
            myStmt.setInt(2, loyer);
            myStmt.setString(3, localization);
            myStmt.setInt(4, qualite);
            myStmt.setInt(5, niveau);
            myStmt.setInt(6, frequencetheorique);
            myStmt.executeUpdate();
            myStmt.close();
            //on passe a l'emplacement suivant 
            i++;
        }
    }

    public synchronized void affectStoretoLocation(Connection c, int idEmplacement, int idMagasin, int redevance) throws SQLException{
        //Once all locations are created we want to populate table emplacement_has_magasin
//        Statement myStmt = c.createStatement();
//        ResultSet myRs = myStmt.executeQuery("select last_insert_id() as last_id from emplacement");
//        myRs.next();
//        int lastid = myRs.getInt("last_id");
        PreparedStatement myPrepStmt = null;
        myPrepStmt = c.prepareStatement("insert into emplacement_has_magasin values (?,?,?)");
        myPrepStmt.setInt(1, idEmplacement);
        myPrepStmt.setInt(2, idMagasin);
        myPrepStmt.setInt(3, redevance);
        myPrepStmt.executeUpdate();
    }

    public synchronized static List<Location> loadLocations(Connection c) throws SQLException {
        List<Location> locationList = new ArrayList<>();
        //The query which selects all locations
        try (Statement myStmt = c.createStatement()) {
            //The query which selects all the shops matching the profile
            ResultSet myRs = myStmt.executeQuery("select * from emplacement");
            //Loop which adds a shop to the list.
            while (myRs.next()) {
                int idEmplacement = myRs.getInt("idEmplacement");
                int superficie = myRs.getInt("superficie");
                int loyer_initial = myRs.getInt("loyer_initial");
                String localisation = myRs.getString("localisation");
                int qualite = myRs.getInt("qualite");
                int niveau = myRs.getInt("niveau");
                int freqth = myRs.getInt("freqth");
                Location l = new Location(idEmplacement, superficie, loyer_initial, localisation, qualite, niveau, freqth);
                locationList.add(l);
            }
        }
        return locationList;
    }

    public synchronized List<Location> loadLocationAccordToStore(Connection c, int storeId) throws SQLException {
        List<Location> locationList = new ArrayList<>();
        //The query which selects all locations
        try (Statement myStmt = c.createStatement()) {
            //The query which selects all the shops matching the profile
            ResultSet myRs = myStmt.executeQuery("select emplacement.idEmplacement, emplacement.superficie, emplacement.loyer_initial, emplacement.localisation, emplacement.qualite, emplacement.niveau, emplacement.freqth from emplacement, emplacement_has_magasin where emplacement.idEmplacement = emplacement_has_magasin.Emplacement_idEmplacement and emplacement_has_magasin.Magasin_idMagasin = " + storeId);
            //Loop which adds a shop to the list.
            while (myRs.next()) {
                int idEmplacement = myRs.getInt("idEmplacement");
                int superficie = myRs.getInt("superficie");
                int loyer_initial = myRs.getInt("loyer_initial");
                String localisation = myRs.getString("localisation");
                int qualite = myRs.getInt("qualite");
                int niveau = myRs.getInt("niveau");
                int freqth = myRs.getInt("freqth");
                Location l = new Location(idEmplacement, superficie, loyer_initial, localisation, qualite, niveau, freqth);
                locationList.add(l);
            }
        }
        return locationList;
    }
    /**
     * This methode return a list of location with the store affected (we only pick the name of the store )
     * @param c
     * @return
     * @throws SQLException 
     */
    public static List<Location> loadStoreAndAffectation(Connection c) throws SQLException {
        List<Location> list = new ArrayList<>();
        Statement myStmt = c.createStatement();
        //The query which selects all the shops.
        ResultSet myRs = myStmt.executeQuery("SELECT magasin.designation,emplacement.localisation,emplacement.niveau,emplacement.idEmplacement FROM Magasin LEFT JOIN emplacement_has_magasin ON magasin.idMagasin = emplacement_has_magasin.Magasin_idMagasin "
                + "Left join emplacement on emplacement_has_magasin.Emplacement_idEmplacement = emplacement.idEmplacement");

        while (myRs.next()) {
            String designationStore = myRs.getString("designation");
            String localization = myRs.getString("localisation");
            int floor = myRs.getInt("niveau");
            int idLocation = myRs.getInt("idEmplacement");

            Location L = new Location(idLocation, localization, floor, designationStore);
            list.add(L);
        }

        return list;

    }
    /**
     * This method return the list of the Locations which have the exact surface desired by a store
     * @param c
     * @param surface
     * @return
     * @throws SQLException 
     */
    public synchronized static List<Location> getLocationsEqualToSurface(Connection c, int surface) throws SQLException {
        List<Location> locationsList = new ArrayList<>();
        Statement myStmt = c.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from emplacement "
                + "where superficie = " + surface + " and idEmplacement not between 101 AND 120 "
                + "and idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin);");
        while (myRs.next()) {
            int id = myRs.getInt("idEmplacement");
            int superficie = myRs.getInt("superficie");
            int loyerinit = myRs.getInt("loyer_initial");
            String localization = myRs.getString("localisation");
            int qualite = myRs.getInt("qualite");
            int niveau = myRs.getInt("niveau");
            int freqTherorique = myRs.getInt("freqth");

            Location E = new Location(id, superficie, loyerinit, localization, qualite, niveau, freqTherorique);

            locationsList.add(E);
        }
        return locationsList;
    }
    /**
     * This method return the list of the Locations with the smallest surface
     * @param c
     * @param minSurface
     * @return
     * @throws SQLException 
     */
    public synchronized static List<Location> getLocationsEqualToMinSurface(Connection c, int minSurface) throws SQLException {
        List<Location> locationsList = new ArrayList<>();
        Statement myStmt = c.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from emplacement "
                + "where superficie = " + minSurface + " and idEmplacement not between 101 AND 120 "
                + "and idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin);");
        while (myRs.next()) {
            int id = myRs.getInt("idEmplacement");
            int superficie = myRs.getInt("superficie");
            int loyerinit = myRs.getInt("loyer_initial");
            String localization = myRs.getString("localisation");
            int qualite = myRs.getInt("qualite");
            int niveau = myRs.getInt("niveau");
            int freqTherorique = myRs.getInt("freqth");

            Location E = new Location(id, superficie, loyerinit, localization, qualite, niveau, freqTherorique);

            locationsList.add(E);
        }
        return locationsList;
    }
    /**
     * This method return the list of the Locations wich have a surface less than the surface desired by a store
     * @param c
     * @param desiredSurface
     * @return
     * @throws SQLException 
     */
    public synchronized static List<Location> getLocationsInferiorToDesiredSurface(Connection c, int desiredSurface) throws SQLException {
        List<Location> locationsList = new ArrayList<>();
        Statement myStmt = c.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from emplacement where superficie < " + desiredSurface + " "
                + "and idEmplacement not between 101 AND 120 "
                + "and idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin) ;");
        while (myRs.next()) {
            int id = myRs.getInt("idEmplacement");
            int superficie = myRs.getInt("superficie");
            int loyerinit = myRs.getInt("loyer_initial");
            String localization = myRs.getString("localisation");
            int qualite = myRs.getInt("qualite");
            int niveau = myRs.getInt("niveau");
            int freqTherorique = myRs.getInt("freqth");

            Location E = new Location(id, superficie, loyerinit, localization, qualite, niveau, freqTherorique);

            locationsList.add(E);
        }

        return locationsList;
    }
    /**
     * This method return the list of the Locations for the restaurant 
     * @param c
     * @return
     * @throws SQLException 
     */
    public synchronized static List<Location> getLocationsForRestaurant(Connection c) throws SQLException {
        List<Location> locationsList = new ArrayList<>();
        Statement myStmt = c.createStatement();
        // we select all the location where for the restaurant 

        ResultSet myRs = myStmt.executeQuery("select * from emplacement where idEmplacement between 101 and 120 and idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin) ");
        while (myRs.next()) {
            int id = myRs.getInt("idEmplacement");
            int superficie = myRs.getInt("superficie");
            int loyerinit = myRs.getInt("loyer_initial");
            String localization = myRs.getString("localisation");
            int qualite = myRs.getInt("qualite");
            int niveau = myRs.getInt("niveau");
            int freqTherorique = myRs.getInt("freqth");

            Location E = new Location(id, superficie, loyerinit, localization, qualite, niveau, freqTherorique);

            locationsList.add(E);

        }
        return locationsList;
    }
    /**
     * This method return the smallest surface of the location
     * @param c
     * @return
     * @throws SQLException 
     */
    public synchronized static Integer getMinSurface(Connection c) throws SQLException {
        Statement myStmt = c.createStatement();
        ResultSet myRsMin = myStmt.executeQuery("select min(superficie) as minsup from emplacement where idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin) ");
        myRsMin.next();
        int minSurface = myRsMin.getInt("minsup");

        return minSurface;
    }

    public synchronized static void insertMagHasEmplacement(Connection c, int idStore, int idLocation) throws SQLException {
        PreparedStatement myStmt = null;
        myStmt = c.prepareStatement("insert emplacement_has_magasin (Emplacement_idEmplacement, Magasin_idMagasin) values (?,?)");
        //request
        myStmt.setInt(1, idLocation);
        myStmt.setInt(2, idStore);

        myStmt.executeUpdate();
        myStmt.close();

    }

    public static void main(String[] args) {
//        DAOLocation dao = new DAOLocation();
//        dao.init();
//        Connection c = dao.getPool().getConnection();
//        try {
////            System.out.println(loadLocations(c));
////            for (int i = 2; i < 10; i++) {
////                dao.affectStoretoLocation(c, i, i+1, 4000);
////            }
//            System.out.println(dao.loadLocationAccordToStore(c, 3));
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOLocation.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            dao.getPool().releaseConnection(c);
//        }
    }
}
