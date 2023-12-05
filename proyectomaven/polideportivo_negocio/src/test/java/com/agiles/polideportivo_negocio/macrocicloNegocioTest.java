/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.agiles.polideportivo_negocio;

import com.agiles.entidades.Macrociclo;
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
public class macrocicloNegocioTest {

    public macrocicloNegocioTest() {
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
     * Test of guardarMacrociclo method, of class macrocicloNegocio.
     */
    @Test
    public void testGuardarMacrociclo() {

        System.out.println("guardarMacrociclo");
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

        macrocicloNegocio instance = new macrocicloNegocio();
        Object result = instance.guardarMacrociclo(macrociclo);
        if (result == null) {
            fail("No se pudo guardar el macrociclo.");

        }

        assertNotNull(result);
    }

    @Test
    public void testConvertirJSON() {
        System.out.println("guardarMacrociclo");
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

        macrocicloNegocio instance = new macrocicloNegocio();
        Object result = instance.convertirJSON(macrociclo);
                if (result == null) {
            fail("No se pudo convertir a JSON");

        }

        assertNotNull(result);
    }

}
