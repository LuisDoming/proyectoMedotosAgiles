/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rjsaa
 */
public class VolumenNegocio {

    public VolumenNegocio() {
    }
    
    public boolean validarTablas(ArrayList<DefaultTableModel> tablas){
        
        for (int i = 0; i < tablas.size(); i++) {
            for (int j = 0; j < tablas.get(i).getColumnCount() ; j++) {
                
            }
        }
        
        return true;
    }
    
    
    public boolean camposVacios(){
        return true;
    }
    
    public boolean validarCampos(){
        return true;
    }
}
