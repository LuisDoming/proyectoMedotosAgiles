/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agiles.polideportivo_negocio;

import com.agiles.dao.macrocicloDAO;
import com.agiles.entidades.Macrociclo;
import com.google.gson.*;

/**
 *
 * @author rjsaa
 */
public class macrocicloNegocio {

    private macrocicloDAO macrodao = new macrocicloDAO();
    
    public macrocicloNegocio() {
        
    }
 
    /**
     * Método que válida el contenido del objeto Macrociclo
     * @param macrociclo
     * @return 
     */
    public Object guardarMacrociclo(Macrociclo macrociclo){
        if(!macrociclo.equals(null)){
            Object id = macrodao.guardarMacrociclo(macrociclo);
            if(!id.equals(null)){
                return id ;
            }else{
                return null;
            }
        }
        
        return false;
    }
    
    /**
     * Método que convierte el objeto Macrocilo a gson para su futuro almacenamiento en BD
     * @param macrociclo
     * @return 
     */
    public String convertirJSON(Macrociclo macrociclo){
       
        Gson gson = new Gson();
        
        String jsonMacro = gson.toJson(macrociclo);
        
        return jsonMacro;
    }
}
