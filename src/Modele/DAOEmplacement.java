package Modele;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOEmplacement {
	
	public static List<Emplacement> chargeEmplacement() throws SQLException {
		
		List<Emplacement> listEmplacement = new ArrayList<>();
		
		Connection myConn = Database.getConnection();
		
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from emplacement");
		
		while (myRs.next()) {
			int id = myRs.getInt("idEmplacement");
			int superficie = myRs.getInt("superficie");
			float loyerinit = myRs.getFloat("loyer_initial");
			String localisation = myRs.getString("localisation");
			int qualite = myRs.getInt("qualite");
			
			Emplacement E = new Emplacement(id,superficie,loyerinit,localisation,qualite);
			
			listEmplacement.add(E);
		}
		myStmt.close();
		
		return listEmplacement;
		
	}
	
	public static void modifierEmplacement (int idEmplacement,int superficie,float loyer_initial,String localisation,int qualite) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("update emplacement set superficie=?,loyer_initial=?,localisation=?,qualite=? where idEmplacement=?");
		
		
		myStmt.setInt(1, superficie);
		myStmt.setFloat(2, loyer_initial);
		myStmt.setString(3, localisation);
		myStmt.setInt(4, qualite);
                myStmt.setInt(5, idEmplacement);
		myStmt.executeUpdate();
		myStmt.close();
	}
	
	public static void supprimerEmplacement (int idEmplacement) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("delete from emplacement where idEmplacement=?");
		myStmt.setInt(1,idEmplacement);
		myStmt.executeUpdate();
		myStmt.close();
		
	}
	
	public static void AjouterEmplacement (int superficie,float loyer_initial,String localisation,int qualite) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("insert into emplacement(superficie,loyer_initial,localisation,qualite)"+"values(?,?,?,?)");
		myStmt.setInt(1,superficie);
		myStmt.setFloat(2,loyer_initial);
		myStmt.setString(3,localisation);
		myStmt.setInt(4,qualite);
		myStmt.executeUpdate();
		myStmt.close();
	}

        public static void main(String[] args) throws Exception {

        DAOEmplacement dao = new DAOEmplacement();
        System.out.println(dao.chargeEmplacement());
        dao.AjouterEmplacement(1000,5000,"B2",5);
        System.out.println(dao.chargeEmplacement());
        dao.modifierEmplacement(1,1200,5000,"B3",5);
        System.out.println(dao.chargeEmplacement());
        dao.supprimerEmplacement(1);
        System.out.println(dao.chargeEmplacement());

    }
}