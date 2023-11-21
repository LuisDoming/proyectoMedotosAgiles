/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import com.google.gson.Gson;
import dao.macrocicloDAO;
import entidades.Macrociclo;

/**
 *
 * @author rjsaa
 */
public class macrocicloNegocio {

    private macrocicloDAO macrodao = new macrocicloDAO();
    
    public macrocicloNegocio() {
        
    }
 
    public boolean guardarMacrociclo(Macrociclo macrociclo){
        if(!macrociclo.equals(null)){
            if(macrodao.guardarMacrociclo(macrociclo)){
                return true;
            }else{
                return false;
            }
        }
        
        return false;
    }
    
    public String convertirJSON(Macrociclo macrociclo){
       
        Gson gson = new Gson();
        
        String jsonMacro = gson.toJson(macrociclo);
        
        return jsonMacro;
    }
}
