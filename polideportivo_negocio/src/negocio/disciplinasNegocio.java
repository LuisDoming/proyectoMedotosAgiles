/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dao.DisciplinaDAO;
import entidades.Disciplina;
import java.util.ArrayList;

/**
 *
 * @author rjsaa
 */
public class disciplinasNegocio {

    private DisciplinaDAO disdao;
    
    public disciplinasNegocio() {
        this.disdao = new DisciplinaDAO();
    }
    
    //
    public boolean validarDisciplina(ArrayList<Disciplina> disciplinas){
        if(!disciplinas.isEmpty()){
            return false;
        }
        
        return true;
    }

    //no usar hasta hu5
    public void agregarDisciplina(){
        
    }
    
}
