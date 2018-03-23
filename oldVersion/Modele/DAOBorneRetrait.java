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
public class DAOBorneRetrait {
    public static List<BorneRetrait> chargeBorneRetrait() throws SQLException {
		
		List<BorneRetrait> listBorneRetrait = new ArrayList<>();
		
		Connection myConn = Database.getConnection();
		
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from borne_retrait");
		
		while (myRs.next()) {
			int id = myRs.getInt("idBorne_retrait");
			String localisation = myRs.getString("localisation");
			
			BorneRetrait BR = new BorneRetrait(id,localisation);
			
			listBorneRetrait.add(BR);
		}
		myStmt.close();
		
		return listBorneRetrait;
		
	}
	
	public static void modifierBorneRetrait (int idBorne_retrait, String localisation) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("update borne_retrait set localisation=? where idBorne_retrait=?");
		
		myStmt.setString(1, localisation);
                myStmt.setInt(2, idBorne_retrait);
		myStmt.executeUpdate();
		myStmt.close();
	}
	
	public static void supprimerBorneRetrait (int idBorne_retrait) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("delete from borne_retrait where idBorne_retrait=?");
		myStmt.setInt(1,idBorne_retrait);
		myStmt.executeUpdate();
		myStmt.close();
		
	}
	
	public static void AjouterBorneRetrait (String localisation ) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("insert into borne_retrait(localisation)"+"values(?)");
		myStmt.setString(1,localisation);
		myStmt.executeUpdate();
		myStmt.close();
	}

        public static void main(String[] args) throws Exception {

        DAOBorneRetrait dao = new DAOBorneRetrait();
        System.out.println(dao.chargeBorneRetrait());
        dao.AjouterBorneRetrait("Porte Volga");
        System.out.println(dao.chargeBorneRetrait());
        dao.modifierBorneRetrait(1,"Porte Village");
        System.out.println(dao.chargeBorneRetrait());
        dao.supprimerBorneRetrait(1);
        System.out.println(dao.chargeBorneRetrait());

    }
}