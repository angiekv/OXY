/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mario
 */
public class DAOPanneauxPub {
    public static List<PanneauxPub> chargePanneauxPub() throws SQLException {
		
		List<PanneauxPub> listPanneauxPub = new ArrayList<>();
		
		Connection myConn = Database.getConnection();
		
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from panneaux_pub");
		
		while (myRs.next()) {
			int id = myRs.getInt("idPanneaux_pub");
			String localisation = myRs.getString("localisation");
			
			PanneauxPub P = new PanneauxPub(id,localisation);
			
			listPanneauxPub.add(P);
		}
		myStmt.close();
		
		return listPanneauxPub;
		
	}
	
	public static void modifierPanneauxPub (int idPanneaux_pub,String localisation) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("update panneaux_pub set localisation=? where idPanneaux_pub=?");
		
		myStmt.setString(1, localisation);
                myStmt.setInt(2, idPanneaux_pub);
		myStmt.executeUpdate();
		myStmt.close();
	}
	
	public static void supprimerPanneauxPub (int idPanneaux_pub) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("delete from panneaux_pub where idPanneaux_pub=?");
		myStmt.setInt(1,idPanneaux_pub);
		myStmt.executeUpdate();
		myStmt.close();
		
	}
	
	public static void AjouterPanneauxPub (String localisation ) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("insert into panneaux_pub(localisation)"+"values(?)");
		myStmt.setString(1,localisation);
		myStmt.executeUpdate();
		myStmt.close();
	}
        public static void main(String[] args) throws Exception {

        DAOPanneauxPub dao = new DAOPanneauxPub();
        System.out.println(dao.chargePanneauxPub());
        dao.AjouterPanneauxPub("Porte Volga");
        System.out.println(dao.chargePanneauxPub());
        dao.modifierPanneauxPub(1,"Porte Village");
        System.out.println(dao.chargePanneauxPub());
        dao.supprimerPanneauxPub(1);
        System.out.println(dao.chargePanneauxPub());

    }
    
}
