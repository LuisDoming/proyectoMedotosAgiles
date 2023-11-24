/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import dao.DisciplinaDAO;
import entidades.Disciplina;
import entidades.Etapa;
import entidades.Macrociclo;
import entidades.VolumenEtapa;

/**
 *
 * @author rjsaa
 */
public class pruebaDisciplinaDao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DisciplinaDAO disdao = new DisciplinaDAO();
        
        VolumenEtapa vol = new VolumenEtapa();
        vol.setTipo(Etapa.PRECOM);
               
        Disciplina disciplina = new Disciplina("velocidad",vol,vol,vol,vol,20);
        
        disdao.agregarDisciplina(disciplina, "macronuevajiji");
        
    }
    
}
