/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motorTraduccion;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luisito
 */
public class IdentificadorEstructuraTest {
    
    public IdentificadorEstructuraTest() {
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
     * Test of identificadorGenericoPalabras method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorGenericoPalabras() throws Exception {
        System.out.println("identificadorGenericoPalabras");
        String[] palabras = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        instance.identificadorGenericoPalabras(palabras,0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ProcesoTiposPalabras method, of class IdentificadorEstructura.
     */
    @Test
    public void testProcesoTiposPalabras() throws Exception {
        System.out.println("ProcesoTiposPalabras");
        String[] palabras = null;
        ArrayList indices = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        String[][] expResult = null;
        String[][] result = instance.ProcesoTiposPalabras(palabras, indices,0);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of identificadorNombresI method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorNombresI() {
        System.out.println("identificadorNombresI");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.identificadorNombresI(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of identificadorNombresE method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorNombresE() {
        System.out.println("identificadorNombresE");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.identificadorNombresE(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IdentificadorUbicacionesI method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorUbicacionesI() {
        System.out.println("IdentificadorUbicacionesI");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.IdentificadorUbicacionesI(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IdentificadorUbicacionesE method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorUbicacionesE() {
        System.out.println("IdentificadorUbicacionesE");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.IdentificadorUbicacionesE(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IdentificadorFechasI method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorFechasI() {
        System.out.println("IdentificadorFechasI");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.IdentificadorFechasI(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IdentificadorOrganizacionesE method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorOrganizacionesE() {
        System.out.println("IdentificadorOrganizacionesE");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.IdentificadorOrganizacionesE(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IdentificadorOrganizacionesI method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorOrganizacionesI() {
        System.out.println("IdentificadorOrganizacionesI");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.IdentificadorOrganizacionesI(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IdentificadorNombresEspecialesE method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorNombresEspecialesE() {
        System.out.println("IdentificadorNombresEspecialesE");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.IdentificadorNombresEspecialesE(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IdentificadorMonetario method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorMonetario() {
        System.out.println("IdentificadorMonetario");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.IdentificadorMonetario(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IdentificadorTiempo method, of class IdentificadorEstructura.
     */
    @Test
    public void testIdentificadorTiempo() {
        System.out.println("IdentificadorTiempo");
        String[] tokens = null;
        IdentificadorEstructura instance = new IdentificadorEstructura();
        ArrayList expResult = null;
        ArrayList result = instance.IdentificadorTiempo(tokens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
