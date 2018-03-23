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
import Model.DAOMagasin;
/**
 *
 * @author BaDiakite
 */
public class DAOMagasinTest {
    
    public DAOMagasinTest() {
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
     * Test of chargeMagasin method, of class DAOMagasin.
     */
    @Test
    public void testChargeMagasin() throws Exception {
        List<Magasin> lesMags = DAOMagasin.chargeMagasin();
        assertNotNull(lesMags);
    }

    /**
     * Test of modifierMagasin method, of class DAOMagasin.
     */
    @Test
    public void testModifierMagasin() throws Exception {
        System.out.println("modifierMagasin");
        List<Magasin> lesMags = DAOMagasin.chargeMagasin();
        Magasin m = lesMags.get(4);
        DAOMagasin.modifierMagasin(7, "Micromania", "jeux vidéo", 1);
        List<Magasin> results = DAOMagasin.chargeMagasin();
        Magasin r = results.get(4);
        assertFalse(m.getDesignation() == r.getDesignation());
        
    }

    /**
     * Test of supprimerMagasin method, of class DAOMagasin.
     */
    @Test
    public void testSupprimerMagasin() throws Exception {
        List<Magasin> lesMags = DAOMagasin.chargeMagasin();
        DAOMagasin.supprimerMagasin(9);
        List<Magasin> r = DAOMagasin.chargeMagasin();
        assertEquals(lesMags.size()-1 , r.size());
        
        
    }

    /**
     * Test of AjouterMagasin method, of class DAOMagasin.
     */
    @Test
    public void testAjouterMagasin() throws Exception {
        System.out.println("AjouterMagasin");
        List<Magasin> lesMags = DAOMagasin.chargeMagasin();
        DAOMagasin.AjouterMagasin("boulanger", "switch", 1);
        List<Magasin> r = DAOMagasin.chargeMagasin();
        assertEquals(lesMags.size()+1 , r.size());
    }

    /**
     * Test of main method, of class DAOMagasin.
     */
    @Test
    public void testMain() throws Exception {
//        System.out.println("main");
//        String[] args = null;
//        DAOMagasin.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
