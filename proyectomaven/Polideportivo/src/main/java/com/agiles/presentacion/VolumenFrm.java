/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.agiles.presentacion;

import com.agiles.entidades.Disciplina;
import com.agiles.entidades.Etapa;
import com.agiles.entidades.VolumenEtapa;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.agiles.polideportivo_negocio.VolumenNegocio;
import com.agiles.polideportivo_negocio.disciplinasNegocio;
import org.bson.types.ObjectId;

/**
 *
 * @author ldoar
 */
public class VolumenFrm extends javax.swing.JFrame {
    
    public ArrayList<String> arregloDisciplinas;
    public String macrocicloRef;
    public String semanasPrep,semanasEspeciales,semanasPrecom,semanasCompe;

    /**
     * Creates new form VolumenFrm
     */
    public VolumenFrm(String semanasPrep,String semanasEspeciales,String semanasPrecom,String semanasCompe,String idMacro) {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.deshabilitarTablas();
        this.jbGuardarVolumen.setEnabled(false);
        arregloDisciplinas = new ArrayList<>();
        this.semanasPrep = semanasPrep;
        this.semanasEspeciales = semanasEspeciales;
        this.semanasPrecom = semanasPrecom;
        this.semanasCompe = semanasCompe;
        this.macrocicloRef = idMacro;
        
        
        System.out.println(this.macrocicloRef);
        System.out.println(this.semanasPrep + "  " + this.semanasEspeciales + "  " + this.semanasPrecom + "  " + this.semanasCompe + "  " );
    }
    
    /**
     * Método que hace visible las tablas de las etapas
     */
    public void habilitarTablas(){
        this.tablaVolumenGeneral.setEnabled(true);
        this.tablaEtapaCompetitiva.setEnabled(true);
        this.tablaEtapaPrecompetitiva.setEnabled(true);
        this.tablaEtapaEspecial.setEnabled(true);
        this.tablaEtapaGeneral.setEnabled(true);
        this.jbCalcularVolumen.setEnabled(true);
    }
    
    /**
     * Método que oculta las tablas de los periodos
     */
    public void deshabilitarTablas(){
        this.tablaVolumenGeneral.setEnabled(false);
        this.tablaEtapaCompetitiva.setEnabled(false);
        this.tablaEtapaPrecompetitiva.setEnabled(false);
        this.tablaEtapaEspecial.setEnabled(false);
        this.tablaEtapaGeneral.setEnabled(false);
        this.jbCalcularVolumen.setEnabled(false);
    }
    
    /**
     * Método que carga las diciplinas elegidas en las tablas de las etapas
     */
    public void cargarDisciplinas(){
        
        DefaultTableModel tableModelGeneral = (DefaultTableModel) this.tablaEtapaGeneral.getModel();
        
        DefaultTableModel tableModelEspecial = (DefaultTableModel) this.tablaEtapaEspecial.getModel();

        DefaultTableModel tableModelPrecom = (DefaultTableModel) this.tablaEtapaPrecompetitiva.getModel();

        DefaultTableModel tableModelCompetitiva = (DefaultTableModel) this.tablaEtapaCompetitiva.getModel();
        
        DefaultTableModel tableModelVolumen = (DefaultTableModel) this.tablaVolumenGeneral.getModel();
        
        for (int i = 0; i < this.arregloDisciplinas.size(); i++) {
            if(!(Integer.valueOf(this.semanasPrep) <= 0)){
              tableModelGeneral.insertRow(i, new Object[]{this.arregloDisciplinas.get(i).toString()});
              tableModelGeneral.setValueAt(this.semanasPrep, i, 5);
            }
            
            if(!(Integer.valueOf(this.semanasEspeciales) <= 0)){
              tableModelEspecial.insertRow(i, new Object[]{this.arregloDisciplinas.get(i).toString()});
              tableModelEspecial.setValueAt(this.semanasEspeciales, i, 5);
            }
            
            if(!(Integer.valueOf(this.semanasPrecom) <= 0)){
              tableModelPrecom.insertRow(i, new Object[]{this.arregloDisciplinas.get(i).toString()});
              tableModelPrecom.setValueAt(this.semanasPrecom, i, 5);
            }
            
            if(!(Integer.valueOf(this.semanasCompe) <= 0)){
              tableModelCompetitiva.insertRow(i, new Object[]{this.arregloDisciplinas.get(i).toString()});
              tableModelCompetitiva.setValueAt(this.semanasCompe, i, 5);
            } 
            
            tableModelVolumen.insertRow(i, new Object[]{this.arregloDisciplinas.get(i).toString()});
        }

        if(Integer.valueOf(this.semanasPrep) <= 0){
            this.tablaEtapaGeneral.setEnabled(true);
        }
        
        if(Integer.valueOf(this.semanasEspeciales) <= 0){
            this.tablaEtapaEspecial.setEnabled(true);
        }
        
        if(Integer.valueOf(this.semanasPrecom) <= 0){
            this.tablaEtapaPrecompetitiva.setEnabled(true);
        }
        
        if(Integer.valueOf(this.semanasCompe) <= 0){
            this.tablaEtapaCompetitiva.setEnabled(true);
        }        
    }
    
    /**
     * Método que borra toto el contenido de las tablas de las etapas
     */
    public void limpiarTablas(){
        DefaultTableModel tableModelGeneral = (DefaultTableModel) this.tablaEtapaGeneral.getModel();
        
        DefaultTableModel tableModelEspecial = (DefaultTableModel) this.tablaEtapaEspecial.getModel();

        DefaultTableModel tableModelPrecom = (DefaultTableModel) this.tablaEtapaPrecompetitiva.getModel();

        DefaultTableModel tableModelCompetitiva = (DefaultTableModel) this.tablaEtapaCompetitiva.getModel();
        
        DefaultTableModel tableModelVolumen = (DefaultTableModel) this.tablaVolumenGeneral.getModel();
        
        System.out.println(this.arregloDisciplinas);
        
        for (int i = 0; i < this.arregloDisciplinas.size(); i++) {
       
            if(!(Integer.valueOf(this.semanasPrep) <= 0)){
               tableModelGeneral.removeRow(0);
            }
         
            if(!(Integer.valueOf(this.semanasEspeciales) <= 0)){
               tableModelEspecial.removeRow(0);
            }
         
            if(!(Integer.valueOf(this.semanasPrecom) <= 0)){
               tableModelPrecom.removeRow(0);
            }   
         
            if(!(Integer.valueOf(this.semanasCompe) <= 0)){
                tableModelCompetitiva.removeRow(0);
            }            
            
            tableModelVolumen.removeRow(0);
        }
        
        this.arregloDisciplinas.clear();
        
    }
    
    /**
     * Metodo que valida el contenido de las tablas
     * @return 
     */
    public boolean validarTablas(){
         VolumenNegocio volneg = new VolumenNegocio();
         
         DefaultTableModel volumenGeneral = (DefaultTableModel) this.tablaEtapaGeneral.getModel();
         DefaultTableModel volumenEspecial = (DefaultTableModel) this.tablaEtapaEspecial.getModel();
         DefaultTableModel volumenPrecom = (DefaultTableModel) this.tablaEtapaPrecompetitiva.getModel();
         DefaultTableModel volumenCompetitivo = (DefaultTableModel) this.tablaEtapaCompetitiva.getModel();
         
         if(!(Integer.valueOf(this.semanasPrep) <= 0)){
           if(!volneg.validarTablaVolumen(volumenGeneral,this,"preparativa")){
             return false;
           }
         }
         
         if(!(Integer.valueOf(this.semanasEspeciales) <= 0)){
            if(!volneg.validarTablaVolumen(volumenEspecial,this,"especial")){
               return false;
            }
         }
         
         if(!(Integer.valueOf(this.semanasPrecom) <= 0)){
           if(!volneg.validarTablaVolumen(volumenPrecom,this,"precompetitiva")){
              return false;
           }
         }   
         
         if(!(Integer.valueOf(this.semanasCompe) <= 0)){
            if(!volneg.validarTablaVolumen(volumenCompetitivo,this,"competitiva")){
               return false;
            }
         }    
         
         return true;
         
    }
    
    /**
     * Método que calcula el volumen total de una etapa
     */
    public void calcularVolumenEtapa(){
         VolumenNegocio volneg = new VolumenNegocio();
         
         DefaultTableModel volumenGeneral = (DefaultTableModel) this.tablaEtapaGeneral.getModel();
         DefaultTableModel volumenEspecial = (DefaultTableModel) this.tablaEtapaEspecial.getModel();
         DefaultTableModel volumenPrecom = (DefaultTableModel) this.tablaEtapaPrecompetitiva.getModel();
         DefaultTableModel volumenCompetitivo = (DefaultTableModel) this.tablaEtapaCompetitiva.getModel();   
         
         if(!(Integer.valueOf(this.semanasPrep) <= 0)){
             volneg.calcularVolumen(volumenGeneral, Integer.valueOf(this.semanasPrep));
         }
         
         if(!(Integer.valueOf(this.semanasEspeciales) <= 0)){
             volneg.calcularVolumen(volumenEspecial, Integer.valueOf(this.semanasEspeciales));
         }

         if(!(Integer.valueOf(this.semanasPrecom) <= 0)){
             volneg.calcularVolumen(volumenPrecom, Integer.valueOf(this.semanasPrecom)); 
         }
         
         if(!(Integer.valueOf(this.semanasCompe) <= 0)){
             volneg.calcularVolumen(volumenCompetitivo, Integer.valueOf(this.semanasCompe)); 
         }
                 
    }
    
    /**
     * Método que calcula el volumen total de las etapas
     */
    public void calcularTotal(){
         VolumenNegocio volneg = new VolumenNegocio();
         
         ArrayList<DefaultTableModel> lista = new ArrayList<>();
         DefaultTableModel volumenGeneral = (DefaultTableModel) this.tablaEtapaGeneral.getModel();
         DefaultTableModel volumenEspecial = (DefaultTableModel) this.tablaEtapaEspecial.getModel();
         DefaultTableModel volumenPrecom = (DefaultTableModel) this.tablaEtapaPrecompetitiva.getModel();
         DefaultTableModel volumenCompetitivo = (DefaultTableModel) this.tablaEtapaCompetitiva.getModel(); 
         DefaultTableModel volumenTotal = (DefaultTableModel) this.tablaVolumenGeneral.getModel(); 
         
         if(!(Integer.valueOf(this.semanasPrep) <= 0)){
             lista.add(volumenGeneral);      
         }
         
         if(!(Integer.valueOf(this.semanasEspeciales) <= 0)){
             lista.add(volumenEspecial);
         }
         
         if(!(Integer.valueOf(this.semanasPrecom) <= 0)){
             lista.add(volumenPrecom);
         }
         
         if(!(Integer.valueOf(this.semanasCompe) <= 0)){
             lista.add(volumenCompetitivo);
         }
         
         volneg.calcularTablaTotal(lista, volumenTotal);
         
         this.jbGuardarVolumen.setEnabled(true);
         
    }
    
    /**
     * Método que guarda el volumen total en la BD
     */
    public void guardarVolumen(){
         disciplinasNegocio disneg = new disciplinasNegocio(); 
        
         DefaultTableModel volumenGeneral = (DefaultTableModel) this.tablaEtapaGeneral.getModel();
         DefaultTableModel volumenEspecial = (DefaultTableModel) this.tablaEtapaEspecial.getModel();
         DefaultTableModel volumenPrecom = (DefaultTableModel) this.tablaEtapaPrecompetitiva.getModel();
         DefaultTableModel volumenCompetitivo = (DefaultTableModel) this.tablaEtapaCompetitiva.getModel();
         DefaultTableModel volumenTotal = (DefaultTableModel) this.tablaVolumenGeneral.getModel();

         
         for (int i = 0; i < volumenTotal.getRowCount(); i++) {
            Disciplina disciplina = new Disciplina();
            
            disciplina.setNombre(volumenTotal.getValueAt(i, 0).toString());
            disciplina.setTotal(Float.valueOf(volumenTotal.getValueAt(i, 1).toString()));
            
            if(!(Integer.valueOf(this.semanasPrep) <= 0)){
                VolumenEtapa volPreparativo = new VolumenEtapa();
                
                volPreparativo.setTipo(Etapa.PREPARATIVA);
                
                volPreparativo.setMinimo(Float.valueOf(volumenGeneral.getValueAt(i, 1).toString()));
                volPreparativo.setMaximo(Float.valueOf(volumenGeneral.getValueAt(i, 2).toString()));
                volPreparativo.setPromedio(Float.valueOf(volumenGeneral.getValueAt(i, 3).toString()));
                volPreparativo.setTotal(Float.valueOf(volumenGeneral.getValueAt(i, 6).toString()));
                
                volPreparativo.setInsitaciones(Integer.valueOf(volumenGeneral.getValueAt(i, 4).toString()));
                volPreparativo.setSemanas(Integer.valueOf(volumenGeneral.getValueAt(i, 5).toString()));
                
                disciplina.setPreparativa(volPreparativo);
            }else{
                VolumenEtapa volPreparativo = new VolumenEtapa();
                volPreparativo.setTipo(Etapa.PREPARATIVA);
                disciplina.setEspecial(volPreparativo);
            }
         
            if(!(Integer.valueOf(this.semanasEspeciales) <= 0)){
                VolumenEtapa volEspecial = new VolumenEtapa();
                
                volEspecial.setTipo(Etapa.ESPECIAL);
                
                volEspecial.setMinimo(Float.valueOf(volumenEspecial.getValueAt(i, 1).toString()));
                volEspecial.setMaximo(Float.valueOf(volumenEspecial.getValueAt(i, 2).toString()));
                volEspecial.setPromedio(Float.valueOf(volumenEspecial.getValueAt(i, 3).toString()));
                volEspecial.setTotal(Float.valueOf(volumenEspecial.getValueAt(i, 6).toString()));
                
                volEspecial.setInsitaciones(Integer.valueOf(volumenEspecial.getValueAt(i, 4).toString()));
                volEspecial.setSemanas(Integer.valueOf(volumenEspecial.getValueAt(i, 5).toString()));
                
                disciplina.setEspecial(volEspecial);             
            }else{
                VolumenEtapa volEspecial = new VolumenEtapa();
                volEspecial.setTipo(Etapa.ESPECIAL);
                disciplina.setEspecial(volEspecial);
            }
         
            if(!(Integer.valueOf(this.semanasPrecom) <= 0)){
                VolumenEtapa volPrecom = new VolumenEtapa();
                
                volPrecom.setTipo(Etapa.PRECOM);
                
                volPrecom.setMinimo(Float.valueOf(volumenPrecom.getValueAt(i, 1).toString()));
                volPrecom.setMaximo(Float.valueOf(volumenPrecom.getValueAt(i, 2).toString()));
                volPrecom.setPromedio(Float.valueOf(volumenPrecom.getValueAt(i, 3).toString()));
                volPrecom.setTotal(Float.valueOf(volumenPrecom.getValueAt(i, 6).toString()));
                
                volPrecom.setInsitaciones(Integer.valueOf(volumenPrecom.getValueAt(i, 4).toString()));
                volPrecom.setSemanas(Integer.valueOf(volumenPrecom.getValueAt(i, 5).toString()));
                
                disciplina.setPrecom(volPrecom);              
            }else{
                VolumenEtapa volPrecom = new VolumenEtapa();
                volPrecom.setTipo(Etapa.PRECOM);
                disciplina.setPrecom(volPrecom);  
            }
         
            if(!(Integer.valueOf(this.semanasCompe) <= 0)){
                VolumenEtapa volCom = new VolumenEtapa();
                
                volCom.setTipo(Etapa.COMPETITIVA);
                
                volCom.setMinimo(Float.valueOf(volumenCompetitivo.getValueAt(i, 1).toString()));
                volCom.setMaximo(Float.valueOf(volumenCompetitivo.getValueAt(i, 2).toString()));
                volCom.setPromedio(Float.valueOf(volumenCompetitivo.getValueAt(i, 3).toString()));
                volCom.setTotal(Float.valueOf(volumenCompetitivo.getValueAt(i, 6).toString()));
                
                volCom.setInsitaciones(Integer.valueOf(volumenCompetitivo.getValueAt(i, 4).toString()));
                volCom.setSemanas(Integer.valueOf(volumenCompetitivo.getValueAt(i, 5).toString()));
                
                disciplina.setCompetitiva(volCom);              
            }else{
                VolumenEtapa volCom = new VolumenEtapa();
                volCom.setTipo(Etapa.COMPETITIVA);
                disciplina.setCompetitiva(volCom);  
            }

            disneg.agregarDisciplina(disciplina, this.macrocicloRef);
         }
         
         

    }

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablaEtapaGeneral1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jpVolumen = new javax.swing.JPanel();
        jcbDisciplinas = new javax.swing.JComboBox<>();
        bntSeleccionar = new javax.swing.JButton();
        bntEliminar = new javax.swing.JButton();
        jcbDisciplinasSeleccionadas = new javax.swing.JComboBox<>();
        jlbSalir = new javax.swing.JLabel();
        jbGuardar = new javax.swing.JButton();
        jspVolumenEtapaGeneral = new javax.swing.JScrollPane();
        tablaEtapaGeneral = new javax.swing.JTable();
        jlbEtapaGeneral = new javax.swing.JLabel();
        jspVolumenEtapaEspecial = new javax.swing.JScrollPane();
        tablaEtapaEspecial = new javax.swing.JTable();
        jspVolumenEtapaCompetitiva = new javax.swing.JScrollPane();
        tablaEtapaCompetitiva = new javax.swing.JTable();
        jspVolumenEtapaPrecompetitiva = new javax.swing.JScrollPane();
        tablaEtapaPrecompetitiva = new javax.swing.JTable();
        jlbEtapaEspecial = new javax.swing.JLabel();
        jlbEtapaPrecompetitiva = new javax.swing.JLabel();
        jlbEtapaCompetitiva = new javax.swing.JLabel();
        jspVolumenGeneral = new javax.swing.JScrollPane();
        tablaVolumenGeneral = new javax.swing.JTable();
        jlbVolumenTotal = new javax.swing.JLabel();
        jbGuardarVolumen = new javax.swing.JButton();
        jbCalcularVolumen = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();

        tablaEtapaGeneral1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Min", "Max", "Prom", "Ins.", "Semanas", "V. etapa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });

        jLabel3.setText("Etapa general");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Volumen");

        jpVolumen.setBackground(new java.awt.Color(255, 255, 255));

        jcbDisciplinas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RAG (KM)", "RAE (MIN)", "VEL. GENERAL (MTROS)", "VEL. ESPECIAL (SEG)", "RES. VEL. GEN (MTROS)", "RES. VEL. ESP (MIN)", "FUERZA. GEN (REP)", "FUERZA. ESP (REP)", "COORDINACIÓN TÉCNICA (REP)", "FLEXIBILIDAD (MIN)", " " }));

        bntSeleccionar.setText("Seleccionar");
        bntSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSeleccionarActionPerformed(evt);
            }
        });

        bntEliminar.setText("Eliminar selección");
        bntEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEliminarActionPerformed(evt);
            }
        });

        jlbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salir.png"))); // NOI18N

        jbGuardar.setText("Guardar");
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });

        tablaEtapaGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Min", "Max", "Prom", "Ins.", "Semanas", "V. etapa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspVolumenEtapaGeneral.setViewportView(tablaEtapaGeneral);
        if (tablaEtapaGeneral.getColumnModel().getColumnCount() > 0) {
            tablaEtapaGeneral.getColumnModel().getColumn(0).setResizable(false);
            tablaEtapaGeneral.getColumnModel().getColumn(1).setResizable(false);
            tablaEtapaGeneral.getColumnModel().getColumn(2).setResizable(false);
            tablaEtapaGeneral.getColumnModel().getColumn(3).setResizable(false);
            tablaEtapaGeneral.getColumnModel().getColumn(4).setResizable(false);
            tablaEtapaGeneral.getColumnModel().getColumn(5).setResizable(false);
            tablaEtapaGeneral.getColumnModel().getColumn(6).setResizable(false);
        }

        jlbEtapaGeneral.setText("Etapa general");

        tablaEtapaEspecial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Min", "Max", "Prom", "Ins.", "Semanas", "V. etapa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspVolumenEtapaEspecial.setViewportView(tablaEtapaEspecial);
        if (tablaEtapaEspecial.getColumnModel().getColumnCount() > 0) {
            tablaEtapaEspecial.getColumnModel().getColumn(0).setResizable(false);
            tablaEtapaEspecial.getColumnModel().getColumn(1).setResizable(false);
            tablaEtapaEspecial.getColumnModel().getColumn(2).setResizable(false);
            tablaEtapaEspecial.getColumnModel().getColumn(3).setResizable(false);
            tablaEtapaEspecial.getColumnModel().getColumn(4).setResizable(false);
            tablaEtapaEspecial.getColumnModel().getColumn(5).setResizable(false);
            tablaEtapaEspecial.getColumnModel().getColumn(6).setResizable(false);
        }

        tablaEtapaCompetitiva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Min", "Max", "Prom", "Ins.", "Semanas", "V. etapa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspVolumenEtapaCompetitiva.setViewportView(tablaEtapaCompetitiva);
        if (tablaEtapaCompetitiva.getColumnModel().getColumnCount() > 0) {
            tablaEtapaCompetitiva.getColumnModel().getColumn(0).setResizable(false);
            tablaEtapaCompetitiva.getColumnModel().getColumn(1).setResizable(false);
            tablaEtapaCompetitiva.getColumnModel().getColumn(2).setResizable(false);
            tablaEtapaCompetitiva.getColumnModel().getColumn(3).setResizable(false);
            tablaEtapaCompetitiva.getColumnModel().getColumn(4).setResizable(false);
            tablaEtapaCompetitiva.getColumnModel().getColumn(5).setResizable(false);
            tablaEtapaCompetitiva.getColumnModel().getColumn(6).setResizable(false);
        }

        tablaEtapaPrecompetitiva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Min", "Max", "Prom", "Ins.", "Semanas", "V. etapa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspVolumenEtapaPrecompetitiva.setViewportView(tablaEtapaPrecompetitiva);
        if (tablaEtapaPrecompetitiva.getColumnModel().getColumnCount() > 0) {
            tablaEtapaPrecompetitiva.getColumnModel().getColumn(0).setResizable(false);
            tablaEtapaPrecompetitiva.getColumnModel().getColumn(1).setResizable(false);
            tablaEtapaPrecompetitiva.getColumnModel().getColumn(2).setResizable(false);
            tablaEtapaPrecompetitiva.getColumnModel().getColumn(3).setResizable(false);
            tablaEtapaPrecompetitiva.getColumnModel().getColumn(4).setResizable(false);
            tablaEtapaPrecompetitiva.getColumnModel().getColumn(5).setResizable(false);
            tablaEtapaPrecompetitiva.getColumnModel().getColumn(6).setResizable(false);
        }

        jlbEtapaEspecial.setText("Etapa especial");

        jlbEtapaPrecompetitiva.setText("Etapa precompetitiva");

        jlbEtapaCompetitiva.setText("Etapa competitiva");

        tablaVolumenGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Volumen total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspVolumenGeneral.setViewportView(tablaVolumenGeneral);
        if (tablaVolumenGeneral.getColumnModel().getColumnCount() > 0) {
            tablaVolumenGeneral.getColumnModel().getColumn(0).setResizable(false);
            tablaVolumenGeneral.getColumnModel().getColumn(1).setResizable(false);
        }

        jlbVolumenTotal.setText("Macrociclo");

        jbGuardarVolumen.setBackground(new java.awt.Color(102, 255, 102));
        jbGuardarVolumen.setText("Guardar volumen ");
        jbGuardarVolumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarVolumenActionPerformed(evt);
            }
        });

        jbCalcularVolumen.setBackground(new java.awt.Color(51, 153, 255));
        jbCalcularVolumen.setText("Calcular");
        jbCalcularVolumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCalcularVolumenActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpVolumenLayout = new javax.swing.GroupLayout(jpVolumen);
        jpVolumen.setLayout(jpVolumenLayout);
        jpVolumenLayout.setHorizontalGroup(
            jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVolumenLayout.createSequentialGroup()
                .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVolumenLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspVolumenEtapaPrecompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbEtapaPrecompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspVolumenEtapaCompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbEtapaCompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVolumenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpVolumenLayout.createSequentialGroup()
                                .addComponent(jlbEtapaGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(333, 333, 333)
                                .addComponent(jlbEtapaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpVolumenLayout.createSequentialGroup()
                                .addComponent(jspVolumenEtapaGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jspVolumenEtapaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpVolumenLayout.createSequentialGroup()
                                .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bntSeleccionar)
                                    .addComponent(jcbDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bntEliminar)
                                    .addGroup(jpVolumenLayout.createSequentialGroup()
                                        .addComponent(jcbDisciplinasSeleccionadas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVolumenLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlbSalir)
                            .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbVolumenTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jspVolumenGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpVolumenLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbCalcularVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbGuardarVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jpVolumenLayout.setVerticalGroup(
            jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVolumenLayout.createSequentialGroup()
                .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVolumenLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jcbDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bntSeleccionar))
                    .addGroup(jpVolumenLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbDisciplinasSeleccionadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbGuardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bntEliminar))
                    .addGroup(jpVolumenLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jlbSalir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpVolumenLayout.createSequentialGroup()
                        .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbEtapaGeneral)
                            .addComponent(jlbEtapaEspecial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jspVolumenEtapaEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jspVolumenEtapaGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jpVolumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpVolumenLayout.createSequentialGroup()
                                .addComponent(jlbEtapaCompetitiva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspVolumenEtapaCompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVolumenLayout.createSequentialGroup()
                                .addComponent(jlbEtapaPrecompetitiva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jspVolumenEtapaPrecompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpVolumenLayout.createSequentialGroup()
                        .addComponent(jlbVolumenTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspVolumenGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbCalcularVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbGuardarVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpVolumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que agrega a un nuevo combobox las disciplinas seleccionadas del
     * combobox de origen.
     *
     * @param evt
     */
    private void bntSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSeleccionarActionPerformed
        if (this.jcbDisciplinas.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Error. Seleccione disciplinas");
        } else{
        String[] opciones = {"Aceptar", "Cancelar"};
        //obtiene la opcion seleccionada por el usuario
        int opcion = JOptionPane.showOptionDialog(this, "¿Quieres agregar esa disciplina?:", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                opciones, opciones[0]);

        if (opcion == 0) {
            
            String datoSeleccionado = (String) this.jcbDisciplinas.getSelectedItem();
            if(!datoSeleccionado.equalsIgnoreCase("")){
                 this.jcbDisciplinasSeleccionadas.addItem(datoSeleccionado);

                 // Oculta el dato seleccionado en el comboBoxOrigen
                 int index = this.jcbDisciplinas.getSelectedIndex();
                 if (index != -1) {
                     this.jcbDisciplinas.removeItemAt(index);
                 }                
            }

        }
        }
    }//GEN-LAST:event_bntSeleccionarActionPerformed

    /**
     * Método que elimina una discplina del combobox nuevo
     * @param evt 
     */
    private void bntEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEliminarActionPerformed
        if (this.jcbDisciplinasSeleccionadas.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Error. Seleccione disciplinas");
        } //Al ser valido el combobox de disciplinas seleccionadas se realizara la eliminacion del campo seleccionado
        else {
            String[] opciones = {"Aceptar", "Cancelar"};
            //obtiene la opcion seleccionada por el usuario
            int opcion = JOptionPane.showOptionDialog(this, "¿Quieres eliminar esa disciplina?:", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    opciones, opciones[0]);

            if (opcion == 0) {
                String datoSeleccionado = (String) this.jcbDisciplinasSeleccionadas.getSelectedItem();
                
                if(!datoSeleccionado.equalsIgnoreCase("")){
                     this.jcbDisciplinas.addItem(datoSeleccionado);

                     // Elimina el dato seleccionado en el comboBoxDestino
                     int index = this.jcbDisciplinasSeleccionadas.getSelectedIndex();
                     if (index != -1) {
                         this.jcbDisciplinasSeleccionadas.removeItemAt(index);
                     }                    
                }

            }
        }
    }//GEN-LAST:event_bntEliminarActionPerformed

    /**
     * Método que guarda el contenido del comobox nuevo
     * @param evt 
     */
    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        if (this.jcbDisciplinasSeleccionadas.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Error. Seleccione disciplinas");
        } //Al ser valido el combobox de disciplinas seleccionadas se habilitan las tablas para calcular y distribuir el volumen
        else {

            
            
            String[] opciones = {"Aceptar", "Cancelar"};
            //obtiene la opcion seleccionada por el usuario
            int opcion = JOptionPane.showOptionDialog(this, "¿Guardar estas disciplinas?:", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    opciones, opciones[0]);

            if (opcion == 0) {
                this.limpiarTablas();
                
                this.habilitarTablas();
                
                for (int i = 0; i < jcbDisciplinasSeleccionadas.getItemCount(); i++) {
                   this.arregloDisciplinas.add(jcbDisciplinasSeleccionadas.getItemAt(i).toString());
                }
            
                System.out.println(this.arregloDisciplinas);
                this.jbEditar.setEnabled(false);
                this.cargarDisciplinas();
                
            }


            
            
        }
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbCalcularVolumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCalcularVolumenActionPerformed
        // TODO add your handling code here:
       
        if(this.validarTablas()){
            this.calcularVolumenEtapa();
            this.calcularTotal();
            this.deshabilitarTablas();
            this.jbGuardarVolumen.setEnabled(true);
            this.jbEditar.setEnabled(true);
            System.out.println("calculo exitoso");
        }else{
            System.out.println("calculo erroneo");
        }
        
    }//GEN-LAST:event_jbCalcularVolumenActionPerformed

    private void jbGuardarVolumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarVolumenActionPerformed
        // TODO add your handling code here:
         this.guardarVolumen();
    }//GEN-LAST:event_jbGuardarVolumenActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        // TODO add your handling code here:
        this.habilitarTablas();
        this.jbEditar.setEnabled(false);
        this.jbGuardarVolumen.setEnabled(false);
        
    }//GEN-LAST:event_jbEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntEliminar;
    private javax.swing.JButton bntSeleccionar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbCalcularVolumen;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbGuardarVolumen;
    private javax.swing.JComboBox<String> jcbDisciplinas;
    private javax.swing.JComboBox<String> jcbDisciplinasSeleccionadas;
    private javax.swing.JLabel jlbEtapaCompetitiva;
    private javax.swing.JLabel jlbEtapaEspecial;
    private javax.swing.JLabel jlbEtapaGeneral;
    private javax.swing.JLabel jlbEtapaPrecompetitiva;
    private javax.swing.JLabel jlbSalir;
    private javax.swing.JLabel jlbVolumenTotal;
    private javax.swing.JPanel jpVolumen;
    private javax.swing.JScrollPane jspVolumenEtapaCompetitiva;
    private javax.swing.JScrollPane jspVolumenEtapaEspecial;
    private javax.swing.JScrollPane jspVolumenEtapaGeneral;
    private javax.swing.JScrollPane jspVolumenEtapaPrecompetitiva;
    private javax.swing.JScrollPane jspVolumenGeneral;
    private javax.swing.JTable tablaEtapaCompetitiva;
    private javax.swing.JTable tablaEtapaEspecial;
    private javax.swing.JTable tablaEtapaGeneral;
    private javax.swing.JTable tablaEtapaGeneral1;
    private javax.swing.JTable tablaEtapaPrecompetitiva;
    private javax.swing.JTable tablaVolumenGeneral;
    // End of variables declaration//GEN-END:variables
}
