/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affectLocation;

import Server.Database;
import Server.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Aberkane
 */
public class AffectLocation{

    public static int generateRandomInteger(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min argument must be less than max");
        }

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    /**
     * This function crate all the location in the shopping center 
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
    /**
     * 
     * @param superficieMag
     * @return return a list of emplacement filter by size of the location 
     * @throws SQLException 
     */
    public List<Location> locationsFilteredBySurface(int superficieMag) throws SQLException {
        List<Location> listLocationsBySurface = new ArrayList<>();

        Connection myConn = Database.getConnection();

        Statement myStmt = myConn.createStatement();
        //result of request
        ResultSet myRs = myStmt.executeQuery("select * from emplacement where superficie = " + superficieMag + " and idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin);");

        if (!myRs.isBeforeFirst()) {

            ResultSet myRsMin = myStmt.executeQuery("select min(superficie) as minsup from emplacement");
            myRsMin.next();
            int minSupp = myRsMin.getInt("minsup");

            System.out.println(minSupp);
            ResultSet myRs2 = null;
            if (superficieMag < minSupp) {
                superficieMag = minSupp;
                myRs2 = myStmt.executeQuery("select * from emplacement where superficie =" + superficieMag + " and idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin) ;");
            } else {
                myRs2 = myStmt.executeQuery("select * from emplacement where superficie <" + superficieMag + " and idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin) ;");

            }

            while (myRs2.next()) {
                int id = myRs2.getInt("idEmplacement");
                int superficie = myRs2.getInt("superficie");
                int loyerinit = myRs2.getInt("loyer_initial");
                String localization = myRs2.getString("localisation");
                int qualite = myRs2.getInt("qualite");
                int niveau = myRs2.getInt("niveau");
                int freqTherorique = myRs2.getInt("freqth");

                Location E = new Location(id, superficie, loyerinit, localization, qualite, niveau, freqTherorique);

                listLocationsBySurface.add(E);
            }

        } else {
            while (myRs.next()) {
                int id = myRs.getInt("idEmplacement");
                int superficie = myRs.getInt("superficie");
                int loyerinit = myRs.getInt("loyer_initial");
                String localization = myRs.getString("localisation");
                int qualite = myRs.getInt("qualite");
                int niveau = myRs.getInt("niveau");
                int freqTherorique = myRs.getInt("freqth");

                Location E = new Location(id, superficie, loyerinit, localization, qualite, niveau, freqTherorique);

                listLocationsBySurface.add(E);
            }
        }
        return listLocationsBySurface;
    }

    public List<Location> locationsFilteredByRent(List<Location> listeEmpSuperficie, int loyerMag) throws SQLException {
        List<Location> ListLocationByRent = new ArrayList<>();

        Connection myConn = Database.getConnection();

        Statement myStmt = myConn.createStatement();
        //result of request

        if (loyerMag >= listeEmpSuperficie.get(0).getLoyerInitial()) {
            ListLocationByRent = listeEmpSuperficie;
        } else {

            ResultSet myRsMin = myStmt.executeQuery("select min(loyer_initial) as minLoyer from emplacement");
            myRsMin.next();
            int minLoyer = myRsMin.getInt("minLoyer");

            System.out.println(minLoyer);
            ResultSet myRs = null;
            if (loyerMag < minLoyer) {
                loyerMag = minLoyer;
                myRs = myStmt.executeQuery("select * from emplacement where loyer_initial = " + loyerMag + " and idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin);");
            } else {
                myRs = myStmt.executeQuery("select * from emplacement where loyer_initial <= " + loyerMag + " and idEmplacement not in (select Emplacement_idEmplacement from emplacement_has_magasin);");

            }

            while (myRs.next()) {
                int id = myRs.getInt("idEmplacement");
                int superficie = myRs.getInt("superficie");
                int loyerinit = myRs.getInt("loyer_initial");
                String localization = myRs.getString("localisation");
                int qualite = myRs.getInt("qualite");
                int niveau = myRs.getInt("niveau");
                int freqTherorique = myRs.getInt("freqth");

                Location E = new Location(id, superficie, loyerinit, localization, qualite, niveau, freqTherorique);

                ListLocationByRent.add(E);

            }

        }
        return ListLocationByRent;
    }
    /**
     * 
     * @param listEmp_Loyer
     * @param localizationMag
     * @return retourne une liste d'emplacement en fonction de la localisation souhaité (proche d'une entrée/sortie ou pas)
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
        if (localizationMag == "Indiférent") {
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
        //si il y a seullement un emplacement dans la liste alors on donne l'emplacement actuel 
        if (ListeEmpNiveau.size() == 1) {
            bestLocation = ListeEmpNiveau.get(0);
        } else {
        // si il y a plusieurs emplacement on prend ceux avec la meilleur qualité  
            int max = 0;
            for (Location E : ListeEmpNiveau) {
                if (E.getQualite() >= max) {
                    max = E.getQualite();
                    TheBestLocations.add(E);
                }
            }
        }

        // on cherche le prix le plus haut car car il sera le plus proche de la superficie voulu par le magasin 
        int maxLoyer = 0;
        for (Location emp : TheBestLocations) {
            if (emp.getLoyerInitial() > maxLoyer) {
                maxLoyer = emp.getLoyerInitial();
                bestLocation = emp;
            }
        }

        return bestLocation;

    }
//    public void affecteMagasin(List<Magasin> L){
//        for(Magasin m : L){
//            filtre
//        }
//    }
    
    
    public static void main(String[] args) throws Exception {
        AffectLocation a = new AffectLocation();
//        a.createLocation();
        System.out.println("liste superficie ");
        List<Location> L = a.locationsFilteredBySurface(150);
        System.out.println(L.size());
        for (int i = 0; i < L.size(); i++) {
            System.out.println(L.get(i));
        }
        System.out.println("liste superficie filtrer par loyer ");
        List<Location> L2 = a.locationsFilteredByRent(L, 10000);
        for (int i = 0; i < L2.size(); i++) {
            System.out.println(L2.get(i));
        }
        System.out.println("liste loyer filtrer par localisation");
        List<Location> L3 = a.locationsFilteredByLocalization(L2, "Indiférent");
        for (int i = 0; i < L3.size(); i++) {
            System.out.println(L3.get(i));
        }
        System.out.println("liste localisation filter par niveau");
        List<Location> L4 = a.locationsFilteredByFloor(L3, 2);
        for (int i = 0; i < L4.size(); i++) {
            System.out.println(L4.get(i));
        }
        Location leMeilleur = a.bestLocation(L4);
        System.out.println("le meilleur emplacement par qualité et le par loyer (loyer plus proche de celui de départ)");
        System.out.println(leMeilleur);

    }
}

//    public void affecteMagasin(List<Magasin> L) {
//        for (Magasin m : L) {
//            on recupere toute les information nécessaire au placement du magasin 
//
//            le type de magasin 
//            int type = m.getIdType();
//
//            if()
//        }
/*test */
