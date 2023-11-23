/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package negocio;

import entidades.Disciplina;
import entidades.Etapa;
import entidades.VolumenEtapa;
import java.util.ArrayList;
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
public class disciplinasNegocioTest {

    public disciplinasNegocioTest() {
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
     * Test of validarDisciplina method, of class disciplinasNegocio.
     */
    @Test
    public void testValidarDisciplina() {
        System.out.println("validarDisciplina");
        ArrayList<Disciplina> disciplinas = new ArrayList();

        ///Creamos una disciplina completa
        VolumenEtapa volumenPreparativa = new VolumenEtapa(Etapa.PREPARATIVA, 1, 2, 1.5f, 1, 1, 1);
        VolumenEtapa volumenEspecial = new VolumenEtapa(Etapa.ESPECIAL, 1, 2, 1.5f, 1, 1, 1);
        VolumenEtapa volumenPrecompetitiva = new VolumenEtapa(Etapa.PRECOM, 1, 2, 1.5f, 1, 1, 1);
        VolumenEtapa volumenCompetitiva = new VolumenEtapa(Etapa.COMPETITIVA, 1, 2, 1.5f, 1, 1, 1);

        Disciplina disciplina = new Disciplina();
        disciplina.setPreparativa(volumenPreparativa);
        disciplina.setEspecial(volumenEspecial);
        disciplina.setPrecom(volumenPrecompetitiva);
        disciplina.setCompetitiva(volumenCompetitiva);
        disciplina.setNombre("prueba");
        disciplina.setTotal(4);
        //Disciplina de prueba
        disciplinas.add(disciplina);

        disciplinasNegocio instance = new disciplinasNegocio();
        //Se espera que regrese true
        boolean expResult = true;
        boolean result = instance.validarDisciplina(disciplinas);
        
        if (result != expResult) {
            fail("Las disciplinas son nulas");
        }
        
        assertEquals(expResult, result);

    }

}
