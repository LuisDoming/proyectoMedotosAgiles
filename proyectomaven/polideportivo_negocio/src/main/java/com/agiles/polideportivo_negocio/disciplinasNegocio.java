/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agiles.polideportivo_negocio;

import com.agiles.dao.DisciplinaDAO;
import com.agiles.entidades.Disciplina;
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
    
    /**
     * Método que valida la longitud de la lista de disciplinas
     * @param disciplinas
     * @return 
     */
    public boolean validarDisciplina(ArrayList<Disciplina> disciplinas){
        if(disciplinas.isEmpty()){
            return false;
        }
        
        return true;
    }

    //no usar hasta hu5
    public void agregarDisciplina(){
        
    }
    
}
