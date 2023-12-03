/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.agiles.polideportivo_negocio;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
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
public class VolumenNegocioTest {

    public VolumenNegocioTest() {
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
     * Test of validarTablaVolumen method, of class VolumenNegocio.
     */
    @Test
    public void testValidarTablaVolumen() {
        System.out.println("validarTablaVolumen");

        ///agregar la tabla
        DefaultTableModel tablaPrueba = new DefaultTableModel();

        tablaPrueba.addColumn("1");
        tablaPrueba.addColumn("2");
        tablaPrueba.addColumn("3");
        tablaPrueba.addColumn("4");
        tablaPrueba.addColumn("5");
        tablaPrueba.addColumn("6");
        tablaPrueba.addColumn("7");

        String[] datofilas;
        datofilas = new String[7];

        datofilas[0] = "1";
        datofilas[1] = "2";
        datofilas[2] = "3";
        datofilas[3] = "1";
        datofilas[4] = "1";
        datofilas[5] = "1";
        datofilas[6] = "1";

        tablaPrueba.addRow(datofilas);
        tablaPrueba.addRow(datofilas);

        VolumenNegocio instance = new VolumenNegocio();
        boolean result = instance.validarTablaVolumen(tablaPrueba, null, "preparativa");
        boolean expResult = true;
        
        if (expResult!=result) {
            fail("Tabla no válida");
        }
        
        assertEquals(expResult,result);
        
    }

    /**
     * Test of calcularVolumen method, of class VolumenNegocio.
     */
    @Test
    public void testCalcularVolumen() {
        System.out.println("calcularVolumen");

        DefaultTableModel tablaPrueba = new DefaultTableModel();

        tablaPrueba.addColumn("1");
        tablaPrueba.addColumn("2");
        tablaPrueba.addColumn("3");
        tablaPrueba.addColumn("4");
        tablaPrueba.addColumn("5");
        tablaPrueba.addColumn("6");
        tablaPrueba.addColumn("7");

        String[] datofilas;
        datofilas = new String[7];

        datofilas[0] = "1";
        datofilas[1] = "2";
        datofilas[2] = "3";
        datofilas[3] = "1";
        datofilas[4] = "1";
        datofilas[5] = "1";
        datofilas[6] = "1";

        tablaPrueba.addRow(datofilas);
        tablaPrueba.addRow(datofilas);

        int semana = 2;

        System.out.println(tablaPrueba.getColumnCount());
        System.out.println(tablaPrueba.getRowCount());

        VolumenNegocio instance = new VolumenNegocio();
        float totalVolEtapa = instance.calcularVolumen(tablaPrueba, semana);
        System.out.println(totalVolEtapa);
        if (totalVolEtapa == 0) {
            fail("no hay valores para calcular en la tabla");
        }

        assertEquals(5, totalVolEtapa, 4.5);

    }

    /**
     * Test of calcularTablaTotal method, of class VolumenNegocio.
     */
    @Test
    public void testCalcularTablaTotal() {
        System.out.println("calcularTablaTotal");

        DefaultTableModel tablaPrueba = new DefaultTableModel();
        DefaultTableModel tablaPrueba2 = new DefaultTableModel();

        tablaPrueba.addColumn("1");
        tablaPrueba.addColumn("2");
        tablaPrueba.addColumn("3");
        tablaPrueba.addColumn("4");
        tablaPrueba.addColumn("5");
        tablaPrueba.addColumn("6");
        tablaPrueba.addColumn("7");

        String[] datofilas;
        datofilas = new String[7];

        datofilas[0] = "1";
        datofilas[1] = "1";
        datofilas[2] = "1";
        datofilas[3] = "1";
        datofilas[4] = "1";
        datofilas[5] = "1";
        datofilas[6] = "1";

        tablaPrueba.addRow(datofilas);
        tablaPrueba.addRow(datofilas);
        tablaPrueba.addRow(datofilas);

        tablaPrueba2 = tablaPrueba;

        ArrayList<DefaultTableModel> arregloTablas = new ArrayList();

        arregloTablas.add(tablaPrueba);
        arregloTablas.add(tablaPrueba2);

        VolumenNegocio instance = new VolumenNegocio();

        boolean expResult = true;
        boolean result = instance.calcularTablaTotal(arregloTablas, tablaPrueba2);

        if (result != expResult) {
            fail("No se pudo calcular el total de las tablas");
        }

        assertEquals(result, expResult);

    }

    /**
     * Test of validarEntero method, of class VolumenNegocio.
     */
    @Test
    public void testValidarEntero() {
        System.out.println("validarEntero");
        String entrada = "1";
        VolumenNegocio instance = new VolumenNegocio();
        boolean result = instance.validarEntero(entrada);
        boolean expResult = true;

        if (expResult != result) {
            fail("El número no es entero");
        }

        assertEquals(expResult, result);

    }

    /**
     * Test of validarFlotante method, of class VolumenNegocio.
     */
    @Test
    public void testValidarFlotante() {
        System.out.println("validarFlotante");

        String valor = "1.2";

        VolumenNegocio instance = new VolumenNegocio();
        boolean result = instance.validarFlotante(valor);
        boolean expResult = true;

        if (expResult != result) {
            fail("El número no es entero");
        }

        assertEquals(expResult, result);

    }

}
