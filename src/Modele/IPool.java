/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;

/**
 * interface which will be implemetend by the class ConnectionPool
 * @author oxy
 */
public interface IPool {
    /**
     *This function initializes an arraylist which contains all the connections. 
     */
    public void InitPool();
    /**
     * This function get a connection from the pool.
     * @return Connection the got connection.
     */
    public Connection getConnection();
    /**
     * This function returns the connection to the pool.
     * @param con The connection used
     * @return true if the connection is returned to the pool, false if it failed.
     */
    public boolean releaseConnection(Connection con);
    /**
     * This function returns the number of the available connections of the availableConnections arraylist.
     * @return int the number of connections not used.
     */
    public int getFreeConnection();
    
}
