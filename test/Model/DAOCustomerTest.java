/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Server.DAOCustomer;
import java.sql.Connection;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
/**
 *
 * @author OXY
 */
public class DAOCustomerTest {
    
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
    public void testloadCustomer(Connection c) throws Exception {
        List<Server.Customer> lesClients = DAOCustomer.loadCustomer(c);
        assertNotNull(lesClients);
    }

//    /**
//     * Test of updateCustomer method, of class DAOCustomer.
//     */
//    @Test
//    public void testupdateCustomer(Connection c) throws Exception {
//        System.out.println("updateCustomer");
//        List<Server.Customer> lesClients = DAOCustomer.loadCustomer(c);
//        Server.Customer m = lesClients.get(2);
//        DAOCustomer.updateCustomer(c,1, "Costa", "Rui","15 rue Paul","77290","Paris","ruicosta@hotmail.fr","M");
//        List<Server.Customer> results = DAOCustomer.loadCustomer(c);
//        Server.Customer r = results.get(4);
//        assertFalse(m.getPrenom() == r.getPrenom());
//        
//    }
//
//    /**
//     * Test of deleteCustomer method, of class DAOCustomer.
//     */
//    @Test
//    public void testdeleteCustomer(Connection c) throws Exception {
//        List<Server.Customer> lesClients = DAOCustomer.loadCustomer(c);
//        DAOCustomer.deleteCustomer(c,1);
//        List<Server.Customer> r = DAOCustomer.loadCustomer(c);
//        assertEquals(lesClients.size()-1 , r.size());
//        
//        
//    }
//
//    /**
//     * Test of addCustomer method, of class DAOCustomer.
//     */
//    @Test
//    public void testaddCustomer(Connection c) throws Exception {
//        System.out.println("addCustomer");
//        List<Server.Customer> lesClients = DAOCustomer.loadCustomer(c);
//        DAOCustomer.addCustomer(c,"Costa", "Mario","15 rue Paul","77290","Paris","ruicosta@hotmail.fr","M");
//        List<Server.Customer> r = DAOCustomer.loadCustomer(c);
//        assertEquals(lesClients.size()+1 , r.size());
//    }
}