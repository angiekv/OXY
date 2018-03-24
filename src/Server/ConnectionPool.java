package Server;

import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool implements IPool {
	
	private List<Connection>availableConnections = new ArrayList<Connection>();
	private List<Connection>usedConnections = new ArrayList<Connection>();
	
	private final int MAX_CONNECTIONS = 5;
	
	public void InitPool() {
		
		for(int i = 0; i < MAX_CONNECTIONS; i++) {
                    try {
                        availableConnections.add(this.createConnection());
                    } catch (SQLException ex) {
                        Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
	}

	private Connection createConnection() throws SQLException {
		return Database.getConnection();
	}
	
/** Public function, used by us to get connection from Pool **/
	
	public Connection getConnection() {
		if (availableConnections.size() == 0) {
			System.out.println("All connections are Used !!");
			return null;
		} else {
			Connection con = 
			availableConnections.remove(
				availableConnections.size() - 1);
			usedConnections.add(con);
			return con;
		}
	}

/** Public function, to return connection back to the Pool **/
	public boolean releaseConnection(Connection con) {
		if (con != null) {
			usedConnections.remove(con);
			availableConnections.add(con);
			return true;
		}
		return false;
	}
	
/**Public function, to count the available connections**/	
	public int getFreeConnection() {
		return availableConnections.size();
		}
}