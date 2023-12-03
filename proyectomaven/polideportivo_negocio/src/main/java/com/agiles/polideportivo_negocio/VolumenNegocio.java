/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agiles.polideportivo_negocio;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rjsaa
 */
public class VolumenNegocio {

    public VolumenNegocio() {
    }
    
    public boolean validarTablaVolumen(DefaultTableModel tabla,JFrame frame,String etapa){
        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            float minimo = 0,maximo = 0;
            int insitaciones = 0;
            
            String valorCeldaMinimo = tabla.getValueAt(i, 1).toString();
                    
            if(!this.validarFlotante(valorCeldaMinimo)){
                 JOptionPane.showMessageDialog(frame,"Error. El minimo de la disciplina: "+tabla.getValueAt(i, 0).toString()
                         +" de la tabla: "+etapa+", no es un numero decimal");
                 return false;
            }
                    
            minimo = Float.valueOf(valorCeldaMinimo);   
            
            
            String valorCeldaMaximo = tabla.getValueAt(i, 2).toString();
                    
            if(!this.validarFlotante(valorCeldaMaximo)){
                 JOptionPane.showMessageDialog(frame,"Error. El maximo de la disciplina: "+tabla.getValueAt(i, 0).toString()
                         +" de la tabla: "+etapa+", no es un numero decimal");
                 return false;
            }
                    
            maximo = Float.valueOf(valorCeldaMaximo); 
            
              
            String valorCeldaIns = tabla.getValueAt(i, 4).toString();
                    
            if(!this.validarEntero(valorCeldaIns)){
                 JOptionPane.showMessageDialog(frame,"Error. Las insitaciones de la disciplina: "+tabla.getValueAt(i, 0).toString()
                         +" de la tabla: "+etapa+", no es un numero entero");
                 return false;
            }
                    
            insitaciones = Integer.valueOf(valorCeldaIns);                         
                       
            if(minimo > maximo){
                JOptionPane.showMessageDialog(frame,"Error. El minimo de la disciplina: "+tabla.getValueAt(i, 0).toString()
                        +" de la tabla: "+etapa+", no puede ser mayor que el valor maximo");
                return false;                
            }
            
            if(insitaciones > 7){
                JOptionPane.showMessageDialog(frame,"Error. Las insitaciones de la disciplina: "+tabla.getValueAt(i, 0).toString()
                       +" de la tabla: "+etapa+", no deben ser mayores a 7 veces por semanas");
                return false; 
            }
            
            if(insitaciones < 0){
                JOptionPane.showMessageDialog(frame,"Error. Las insitaciones de la disciplina: "+tabla.getValueAt(i, 0).toString()
                       +" de la tabla: "+etapa+", no deben ser menores a 0 veces por semanas");
                return false; 
            }
          
        }            
        
        return true;
    }
    
    public void calcularVolumen(DefaultTableModel tabla,int semana){
        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            float minimo = 0,maximo = 0;
            int insitaciones = 0;
            int semanas = semana;   
            
            String valorCeldaMinimo = tabla.getValueAt(i, 1).toString();      
            minimo = Float.valueOf(valorCeldaMinimo);     
            
            String valorCeldaMaximo = tabla.getValueAt(i, 2).toString();   
            maximo = Float.valueOf(valorCeldaMaximo);         
              
            String valorCeldaIns = tabla.getValueAt(i, 4).toString();   
            insitaciones = Integer.valueOf(valorCeldaIns);             
            
            float promedio = (maximo-minimo)/2;
            
            tabla.setValueAt(promedio, i, 3);
            
            float totalVolEtapa = promedio * insitaciones * semanas;
           
            tabla.setValueAt(totalVolEtapa, i, 6);
            
                       
        }
    }
    
    public boolean calcularTablaTotal(ArrayList<DefaultTableModel> tablas,DefaultTableModel tabla){
        
        float[] total = new float[tablas.get(0).getRowCount()];
        
        for (DefaultTableModel tabla1 : tablas) {
            for (int i = 0; i < tabla1.getRowCount(); i++) {
                String valorCeldaTotal = tabla1.getValueAt(i, 6).toString();      
                total[i] += Float.valueOf(valorCeldaTotal);
            }
        }
        
        for (int i = 0; i < total.length; i++) {
            tabla.setValueAt(total[i], i , 1);
        }
        
        return true;
    }    
    
    public boolean validarEntero(String valor){
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valor);
        
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }        
    }
    
    public boolean validarFlotante(String valor){
        String regex = "\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valor);
        
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
}
