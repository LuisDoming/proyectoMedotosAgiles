/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dao;

import entidades.Macrociclo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author spide
 */
public class macrocicloDAOTest {

    public macrocicloDAOTest() {
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
     * Test of guardarMacrocicloD method, of class macrocicloDAO.
     */
    @Test
    public void testGuardarMacrocicloD() {
        System.out.println("guardarMacrocicloD");
        Macrociclo macrociclo = new Macrociclo();

        macrociclo.setFechaInicio("12/12/2023");
        macrociclo.setFechaFin("12/04/2024");
        macrociclo.setTotalSemanas(13);
        macrociclo.setDeporte("judo");
        macrociclo.setRama("varonil");
        macrociclo.setJefeRama("Jorge Miguel Utra Reyna");
        macrociclo.setPreparadorFis("prueba");
        macrociclo.setMetodologo("prueba");
        macrociclo.setPeriodoPreparativo(10);
        macrociclo.setPeriodoCompetitivo(3);
        macrociclo.setEtapaPreparativa(6);
        macrociclo.setEtapaEspecial(4);
        macrociclo.setEtapaPrecompetitiva(2);
        macrociclo.setEtapaCompetitiva(1);

        macrocicloDAO instance = new macrocicloDAO();
        boolean expResult = true;
        boolean result = instance.guardarMacrocicloD(macrociclo);
        if (result != true) {
            fail("No se pudo guardar el macrociclo.");

        }
        assertEquals(expResult, result);
    }

}
