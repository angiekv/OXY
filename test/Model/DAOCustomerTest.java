package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Server.DAOCustomer;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
/**
 *
 * @author OXY
 */
public class DAOCustomerTest {
    private static Connection con;
    
    public static Connection Con () throws SQLException{
        con = Database.getConnection();
        return con;
    }
    public DAOCustomerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of chargeMagasin method, of class DAOCustomer.
     * @param c
     * @throws java.lang.Exception
     */
    @Test
    public void testloadCustomer() throws Exception {
        List<Server.Customer> lesClients = DAOCustomer.loadCustomer(Con());
        assertNotNull(lesClients);
    }

    /**
     * Test of updateCustomer method, of class DAOCustomer.
     */
    @Test
    public void testupdateCustomer() throws Exception {
        System.out.println("updateCustomer");
        List<Server.Customer> lesClients = DAOCustomer.loadCustomer(Con());
        Server.Customer m = lesClients.get(2);
        DAOCustomer.updateCustomer(Con(),1, "Costa", "Rui","15 rue Paul","77290","Paris","ruicosta@hotmail.fr","M");
        List<Server.Customer> results = DAOCustomer.loadCustomer(Con());
        Server.Customer r = results.get(4);
        assertFalse(m.getPrenom() == r.getPrenom());
        
    }

    /**
     * Test of deleteCustomer method, of class DAOCustomer.
     */
    @Test
    public void testdeleteCustomer() throws Exception {
        List<Server.Customer> lesClients = DAOCustomer.loadCustomer(Con());
        DAOCustomer.deleteCustomer(Con(),1);
        List<Server.Customer> r = DAOCustomer.loadCustomer(Con());
        assertEquals(lesClients.size()-1 , r.size());
        
        
    }

    /**
     * Test of addCustomer method, of class DAOCustomer.
     */
    @Test
    public void testaddCustomer() throws Exception {
        System.out.println("addCustomer");
        List<Server.Customer> lesClients = DAOCustomer.loadCustomer(Con());
        DAOCustomer.addCustomer(Con(),"Costa", "Mario","15 rue Paul","77290","Paris","ruicosta@hotmail.fr","M");
        List<Server.Customer> r = DAOCustomer.loadCustomer(Con());
        assertEquals(lesClients.size()+1 , r.size());
    }
}