/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.agiles.dao;

import com.agiles.conexion.Conexion;
import com.agiles.entidades.Disciplina;
import com.agiles.entidades.VolumenEtapa;
import com.agiles.entidades.Etapa;
import com.mongodb.MongoClient;
import org.bson.Document;
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
public class DisciplinaDAOTest {
    
    public DisciplinaDAOTest() {
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
     * Test of agregarDisciplina method, of class DisciplinaDAO.
     */
    @Test
    public void testAgregarDisciplina() {
        System.out.println("agregarDisciplina");

        VolumenEtapa volumenPreparativa = new VolumenEtapa(Etapa.PREPARATIVA, 1, 2, 1.5f, 1, 1, 1);
        VolumenEtapa volumenEspecial = new VolumenEtapa(Etapa.ESPECIAL, 1, 2, 1.5f, 1, 1, 1);
        VolumenEtapa volumenPrecompetitiva = new VolumenEtapa(Etapa.PRECOM, 1,  2, 1.5f, 1, 1, 1);
        VolumenEtapa volumenCompetitiva = new VolumenEtapa(Etapa.COMPETITIVA, 1, 2, 1.5f, 1, 1, 1);

        Disciplina disciplina = new Disciplina();
        disciplina.setPreparativa(volumenPreparativa);
        disciplina.setEspecial(volumenEspecial);
        disciplina.setPrecom(volumenPrecompetitiva);
        disciplina.setCompetitiva(volumenCompetitiva);
        disciplina.setNombre("prueba");
        disciplina.setTotal(4);

        String idMacro = "1";
        DisciplinaDAO instance = new DisciplinaDAO();
        
        boolean expResult = true;
        boolean result = instance.agregarDisciplina(disciplina, idMacro);
        
        if (result==false) {
            fail("Error al agregar objeto a la base de datos");
        } else if (result == true){
            System.out.println("Se agregó disciplina");
        }
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    
}
