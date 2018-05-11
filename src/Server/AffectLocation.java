/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import Server.ConnectionPool;
import Server.DAOLocation;
import static Server.DAOLocation.insertMagHasEmplacement;
import Server.DAOStore;
import Server.Database;
import Server.Location;
import Server.Store;
import Server.TypeStore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Aberkane
 */
public class AffectLocation {

    public static int generateRandomInteger(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min argument must be less than max");
        }

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    public void affectLocation(Store S,Connection c) throws SQLException{
        List<Location> L = locationsFilteredBySurface(S.getSuperficie(), S.getListIdType(),c);
        
        List<Location> L2 = locationsFilteredByRent(L,S.getLoyer());
        
        List<Location> L3 = locationsFilteredByLocalization(L2, S.getLocalisation());
        List<Location> L4 = locationsFilteredByFloor(L3, S.getNiveau());
        Location theBestLocation = bestLocation(L4);
        int idStore=S.getId();
        int idLocation= theBestLocation.getIdEmplacement();
        insertMagHasEmplacement(c ,idStore,idLocation);
    }

    /**
     * This function crate all the location in the shopping center
     *
     * @throws SQLException
     */
    public static void createLocation() throws SQLException {

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

    /**
     *
     * @param superficieMag
     * @return return a list of emplacement filter by size of the location
     * @throws SQLException
     */
    
    public List<Location> locationsFilteredBySurface(int superficieMag, List<TypeStore> listType,Connection c) throws SQLException {

        List<Location> listLocationsBySurface = new ArrayList<>();
        DAOLocation d = new DAOLocation();
        boolean isRestaurant = false;
        for(TypeStore t :listType){
            if ("restaurant".equals(t.getDesignation().toLowerCase())){
                isRestaurant= true;
                System.out.println(t.getDesignation());
                break;
            }
        
        }
        System.out.println(isRestaurant);
        if (!isRestaurant) {
            //result of request
            // we select all the location where the surface is equals to the surface that the shop want
            listLocationsBySurface = d.getLocationsEqualToSurface(c, superficieMag);
            // if the request give no result 
            if (listLocationsBySurface.isEmpty()) {
                // we search the smallest surface in the location
                int minSurface = d.getMinSurface(c);
                // condition if the users chose a surface inferior to the smallest location 
                if (superficieMag < minSurface) {
                    listLocationsBySurface = d.getLocationsEqualToMinSurface(c, minSurface);
                } else {
                    listLocationsBySurface = d.getLocationsInferiorToDesiredSurface(c, superficieMag);
                }
                // we put all the location in a list ;
            }
            // if there is a location with the desired surface 

        } else {

            listLocationsBySurface = d.getLocationsForRestaurant(c);
        }
        System.out.println(listLocationsBySurface);
        return listLocationsBySurface;
        
    }
    // if the list of location for restaureant is not empty 

    public List<Location> locationsFilteredByRent(List<Location> listeEmpSuperficie, int loyerMag) {
        List<Location> ListLocationByRent = new ArrayList<>();

        //We  search the lowest rent in the list of Location
        int lowestRent = 1000000000;

        for (Location L : listeEmpSuperficie) {
            if (L.getLoyerInitial() < lowestRent) {
                lowestRent = L.getLoyerInitial();
            }
        }
        //We  search the biggest rent in the list of Location
        int biggestRent = 0;
        for (Location L : listeEmpSuperficie) {
            if (L.getLoyerInitial() > biggestRent) {
                biggestRent = L.getLoyerInitial();
            }
        }
        System.out.println(biggestRent);
        // if a store want a rent which is not in the list of shop 
        if (loyerMag < lowestRent) {
            for (Location L : listeEmpSuperficie) {
                if (L.getLoyerInitial() == lowestRent) {
                    ListLocationByRent.add(L);
                }
            }
        } else if (loyerMag > biggestRent) {
            for (Location L : listeEmpSuperficie) {
                if (L.getLoyerInitial() <= biggestRent) {
                    ListLocationByRent.add(L);
                    System.out.println("ok");
                }
            }
            // the rent desired is in the list of shop 
        } else {
            for (Location L : listeEmpSuperficie) {
                if (L.getLoyerInitial() <= loyerMag) {
                    ListLocationByRent.add(L);
                }

            }
        }

        return ListLocationByRent;
    }

    /**
     *
     * @param listEmp_Loyer
     * @param localizationMag
     * @return return a list of location according to the location desire by the Location
     *  (close to the enter/exit or not)
     */
    public List<Location> locationsFilteredByLocalization(List<Location> listEmp_Loyer, String localizationMag) {
        List<Location> ListLocationByLocalization = new ArrayList<>();
        // TABLEAU AVEC LES SORTIE DU CENTRE COMMERCIAL 
        ArrayList tabSortie = new ArrayList();
        tabSortie.add(1);
        tabSortie.add(75);
        tabSortie.add(19);
        tabSortie.add(20);
        tabSortie.add(57);
        tabSortie.add(58);
        if (localizationMag == "Aucun") {
            ListLocationByLocalization = listEmp_Loyer;
        } else {

            //on boucle pour voir si il existe un emplacment proche de la sortie/entréé 
            for (Location E : listEmp_Loyer) {
                int numEmp = Integer.parseInt(E.getLocalisation().substring(1));
                if (tabSortie.contains(numEmp)) {
                    ListLocationByLocalization.add(E);
                }
            }
            //si la liste est vide alors on laisse la liste comme avant 
            if (ListLocationByLocalization.isEmpty()) {
                ListLocationByLocalization = listEmp_Loyer;
            }

        }

        return ListLocationByLocalization;
    }

    /**
     *
     * @param ListLocationByLocalization
     * @param niveauMag
     * @return return a list of place filter by level
     */
    public List<Location> locationsFilteredByFloor(List<Location> ListLocationByLocalization, int niveauMag) {
        List<Location> ListeEmpNiveau = new ArrayList<>();
        for (Location E : ListLocationByLocalization) {

            if (E.getNiveau() == niveauMag) {
                ListeEmpNiveau.add(E);
            }

        }
        if (ListeEmpNiveau.isEmpty()) {
            ListeEmpNiveau = ListLocationByLocalization;
        }
        return ListeEmpNiveau;
    }

    public Location bestLocation(List<Location> ListeEmpNiveau) {
        Location bestLocation = null;
        List<Location> TheBestLocations = new ArrayList<>();
        //if there is only one location un the list we affect thi location to the best location 
        if (ListeEmpNiveau.size() == 1) {
            bestLocation = ListeEmpNiveau.get(0);
        } else {
            //if there is several location we chose the location with the best quality 
            int max = 0;
            for (Location E : ListeEmpNiveau) {
                if (E.getQualite() >= max) {
                    max = E.getQualite();
                    TheBestLocations.add(E);
                }
            }
        }

        // we search the highest price because the if the price is hight we are going to have the location with the bigest surface
        int maxLoyer = 0;
        for (Location emp : TheBestLocations) {
            if (emp.getLoyerInitial() > maxLoyer) {
                maxLoyer = emp.getLoyerInitial();
                bestLocation = emp;
            }
        }

        return bestLocation;

    }


//    public static void main(String[] args) throws Exception {
//        AffectLocation a = new AffectLocation();
//        a.createLocation();
////        DAOStore d = new DAOStore();
////        ConnectionPool pool = new ConnectionPool();
////        pool.initPool();
////        Connection c = pool.getConnection();
////        List<Store> listOfstore = d.loadStoresNotAffectToLocation(c);
////        
////        for (Store S: listOfstore ){
////            a.affectLocation(S, c);
//        }
////        System.out.println(dao.loadShops(c));
//        listOfshop = d.loadShops(c);
//
//        System.out.println(a.locationForRestaurant(listOfshop));
//        System.out.println("liste superficie ");
//        List<Location> L = a.locationsFilteredBySurface(2000, "restaurant" ,Database.getConnection());
//        System.out.println(L.size());
//        for (int i = 0; i < L.size(); i++) {
//            System.out.println(L.get(i));
//        }
//        System.out.println("liste superficie filtrer par loyer ");
//        List<Location> L2 = a.locationsFilteredByRent(L, 1000);
//        for (int i = 0; i < L2.size(); i++) {
//            System.out.println(L2.get(i));
//        }
//        System.out.println("liste loyer filtrer par localisation");
//        List<Location> L3 = a.locationsFilteredByLocalization(L2, "Indiférent");
//        for (int i = 0; i < L3.size(); i++) {
//            System.out.println(L3.get(i));
//        }
//        System.out.println("liste localisation filter par niveau");
//        List<Location> L4 = a.locationsFilteredByFloor(L3, 2);
//        for (int i = 0; i < L4.size(); i++) {
//            System.out.println(L4.get(i));
//        }
//        Location leMeilleur = a.bestLocation(L4);
//        System.out.println("le meilleur emplacement par qualité et le par loyer (loyer plus proche de celui de départ)");
//        System.out.println(leMeilleur);

    }

/*test */
