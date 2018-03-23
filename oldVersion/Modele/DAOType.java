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
public class DAOType {
    public static List<Type> chargeType() throws SQLException {
		
		List<Type> listType = new ArrayList<>();
		
		Connection myConn = Database.getConnection();
		
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from type");
		
		while (myRs.next()) {
			int id = myRs.getInt("idType");
			String designation = myRs.getString("designation");
			
			Type T = new Type(id,designation);
			
			listType.add(T);
		}
		myStmt.close();
		
		return listType;
		
	}
	
	public static void modifierType (int idPanneaux_pub,String designation) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("update type set designation=? where idType=?");
		
		myStmt.setString(1, designation);
                myStmt.setInt(2, idPanneaux_pub);
		myStmt.executeUpdate();
		myStmt.close();
	}
	
	public static void supprimerType (int idPanneaux_pub) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("delete from type where idType=?");
		myStmt.setInt(1,idPanneaux_pub);
		myStmt.executeUpdate();
		myStmt.close();
		
	}
	
	public static void AjouterType (String designation ) throws SQLException {
		
		PreparedStatement myStmt=null;
		
		Connection myConn = Database.getConnection();
		
		myStmt = myConn.prepareStatement("insert into type(designation)"+"values(?)");
		myStmt.setString(1,designation);
		myStmt.executeUpdate();
		myStmt.close();
	}
        public static void main(String[] args) throws Exception {

        DAOType dao = new DAOType();
        System.out.println(dao.chargeType());
        dao.AjouterType("SPORT");
        System.out.println(dao.chargeType());
        dao.modifierType(1,"CLASSIQUE");
        System.out.println(dao.chargeType());
        dao.supprimerType(1);
        System.out.println(dao.chargeType());

    }
    
}
