/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.JOptionPane;

/**
 * Pantalla que contiene la distribucion de los mesociclos y la distribucion de semanas
 * @author ldoar
 */
public class macrocicloFrm extends javax.swing.JFrame {
    /**
     * Pantalla anterior (pantalla de seleccion de semana de macrociclos
     */
    private crearMacrocicloFrm crearMacro;
    private int semanasTotales;
    
    /**
     * Constructor que inicializa la pantalla y sus componentes
     * @param crearMacro recibe una instancia de crearMacro para acceder las semanas
     */
    public macrocicloFrm(crearMacrocicloFrm crearMacro) {
        this.crearMacro = crearMacro;
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //Establece el total de semanas asi como la semana de inicio y fin 
        //obtenidas en la pantalla anterior
        this.ocultarTablaMeso();
        this.deshabilitarEtapas();
        semanasTotales = crearMacro.totalSemanas;
        this.jtxtSemanas.setText(Integer.toString(crearMacro.totalSemanas));
        this.jtxtSemanaInicio.setText(crearMacro.inicioPlan.toString());
        this.jtxtSemanaFin.setText(crearMacro.finPlan.toString());
        
    }
        
    public void ocultarTablaMeso(){
        this.jspTabla.setEnabled(false);
        this.jbtnValidar.setEnabled(false);
        this.tblMesociclos.setEnabled(false);
    }
    
    public void mostrarTablaMeso(){
        this.jspTabla.setEnabled(true);
        this.jbtnValidar.setEnabled(true);
        this.tblMesociclos.setEnabled(true);
    }
        
    public void deshabilitarEtapas(){
        this.jpPeriodoPreparativo.setVisible(false);
        this.jpPeriodoCompetitivo.setVisible(false);
        this.btnValidarSemanas.setVisible(false);
    }
    
    public void habilitarEtapas(){
        this.jpPeriodoPreparativo.setVisible(true);
        this.jpPeriodoCompetitivo.setVisible(true);
        this.btnValidarSemanas.setVisible(true);
    }
    
    public void validarEtapas(){
        
        int preparativa = Integer.valueOf(this.jtxtSemanasPreparativo.getText());
        int competitiva = Integer.valueOf(this.jtxtSemanasCompetitivo.getText());
        
        int etapaPreparacion,etapaEspecial,etapaPrecompetitiva,etapaCompetitiva,totalIngresado;
        int totalPreparativo,totalCompetitivo;
        
        String precompetitiva = this.jtxtSemanasEspecial.getText();
        
        if(precompetitiva.equalsIgnoreCase("")||precompetitiva.equalsIgnoreCase("0")){
        etapaPreparacion = Integer.valueOf(this.jtxtSemanasPreparacion.getText()); 
        etapaEspecial = Integer.valueOf(precompetitiva);
        etapaCompetitiva = Integer.valueOf(this.jtxtSemanasEtapaCompetitivo.getText());   
        
        totalIngresado = etapaPreparacion + etapaEspecial + etapaCompetitiva;
        totalPreparativo = etapaPreparacion + etapaEspecial;
        totalCompetitivo = etapaCompetitiva;
        }else{
        etapaPreparacion = Integer.valueOf(this.jtxtSemanasPreparacion.getText()); 
        etapaEspecial = Integer.valueOf(precompetitiva);
        etapaPrecompetitiva = Integer.valueOf(this.jtxtSemanasPrecompetitivo.getText()); 
        etapaCompetitiva = Integer.valueOf(this.jtxtSemanasEtapaCompetitivo.getText());  
        
        totalIngresado = etapaPreparacion + etapaEspecial + etapaPrecompetitiva + etapaCompetitiva;
        totalPreparativo = etapaPreparacion + etapaEspecial;
        totalCompetitivo = etapaCompetitiva + etapaPrecompetitiva;
        }
        
        //validaciones
        if(totalIngresado != semanasTotales){
            JOptionPane.showMessageDialog(this, "Error. La suma de semanas ingresada no es igual a la semanas totales establecidas"
                    + "para el macrociclo");
            return;
        }
        
        if(totalPreparativo > preparativa){
            JOptionPane.showMessageDialog(this, "Error. La suma de semanas de preparacion ingresada no es igual a la semanas totales establecidas");
            return;            
        }
 
        if(totalCompetitivo > competitiva){
            JOptionPane.showMessageDialog(this, "Error. La suma de semanas competitivas ingresada no es igual a la semanas totales establecidas");
            return;            
        }        
        
        
        //a
       
        this.mostrarTablaMeso();
    }
    
    public void validarPeriodos(){
        int etapaPreparativa = Integer.valueOf(this.jtxtSemanasPreparativo.getText());
        int etapaCompetitiva = Integer.valueOf(this.jtxtSemanasCompetitivo.getText());
        
        int totalIngresado = etapaPreparativa + etapaCompetitiva;
        
        if(totalIngresado != semanasTotales){
            JOptionPane.showMessageDialog(this, "Error. La suma de semanas ingresada no es igual a la semanas totales establecidas"
                    + "para el macrociclo");
            return;            
        }
        
        if(etapaPreparativa <= etapaCompetitiva){
            JOptionPane.showMessageDialog(this, "Error. la semana preparativa debe contenes mas semanas que la semana competitiva");
            return;                        
        }
        

        this.habilitarEtapas();

    }
    
    public void calcularPorcentajePeriodos(){
        int etapaPreparativa = Integer.valueOf(this.jtxtSemanasPreparativo.getText());
        int etapaCompetitiva = Integer.valueOf(this.jtxtSemanasCompetitivo.getText());
        
        int porcentajePreparativa = (etapaPreparativa * 100) / semanasTotales;
        int porcentajeCompetitiva = (etapaCompetitiva * 100) / semanasTotales;
        
        this.jtxtPorcentajePreparativo.setText(String.valueOf(porcentajePreparativa));
        this.jtxtPorcentajeCompetitivo.setText(String.valueOf(porcentajeCompetitiva));
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpMacrociclo = new javax.swing.JPanel();
        jspTabla = new javax.swing.JScrollPane();
        tblMesociclos = new javax.swing.JTable();
        jLabelAcento = new javax.swing.JLabel();
        jLabelMicro = new javax.swing.JLabel();
        jLabelCicli = new javax.swing.JLabel();
        jLabelMeso = new javax.swing.JLabel();
        jtxtSemanas = new javax.swing.JTextField();
        jlbSalir = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelDistribucion = new javax.swing.JLabel();
        jpPeriodos = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtPorcentajePreparativo = new javax.swing.JTextField();
        jtxtSemanasPreparativo = new javax.swing.JTextField();
        jtxtPorcentajeCompetitivo = new javax.swing.JTextField();
        jtxtSemanasCompetitivo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jpPeriodoCompetitivo = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtxtPorcentajeEtapaPrecompetitivo = new javax.swing.JTextField();
        jtxtSemanasPrecompetitivo = new javax.swing.JTextField();
        jtxtPorcentajeEtapaCompetitivo = new javax.swing.JTextField();
        jtxtSemanasEtapaCompetitivo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jpPeriodoPreparativo = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jtxtPorcentajeEtapaPreparacion = new javax.swing.JTextField();
        jtxtSemanasPreparacion = new javax.swing.JTextField();
        jtxtPorcentajeEtapaEspecial = new javax.swing.JTextField();
        jtxtSemanasEspecial = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jtxtSemanaInicio = new javax.swing.JTextField();
        jtxtSemanaFin = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jbtnCrearMacrociclo = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jbtnValidar = new javax.swing.JButton();
        txtDeporte = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        btnCalcularEtapas = new javax.swing.JButton();
        btnValidarSemanas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de macrociclo");

        jpMacrociclo.setBackground(new java.awt.Color(255, 255, 255));

        tblMesociclos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"
            }
        ));
        tblMesociclos.setDragEnabled(true);
        tblMesociclos.setDropMode(javax.swing.DropMode.INSERT);
        tblMesociclos.getTableHeader().setResizingAllowed(false);
        tblMesociclos.getTableHeader().setReorderingAllowed(false);
        jspTabla.setViewportView(tblMesociclos);

        jLabelAcento.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelAcento.setText("Acentos");

        jLabelMicro.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMicro.setText("Microciclo");

        jLabelCicli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelCicli.setText("Ciclicidad");

        jLabelMeso.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMeso.setText("Mesociclos");

        jtxtSemanas.setEditable(false);
        jtxtSemanas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jtxtSemanas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jlbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/salir.png"))); // NOI18N
        jlbSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbSalirMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Total de semanas del macrociclo:");

        jLabelDistribucion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelDistribucion.setText("Distribución por periodos / etapas");

        jpPeriodos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Periodo competitivo");

        jLabel8.setText("Periodo preparativo ");

        jtxtPorcentajePreparativo.setEnabled(false);

        jtxtPorcentajeCompetitivo.setEnabled(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("%");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Semanas");

        javax.swing.GroupLayout jpPeriodosLayout = new javax.swing.GroupLayout(jpPeriodos);
        jpPeriodos.setLayout(jpPeriodosLayout);
        jpPeriodosLayout.setHorizontalGroup(
            jpPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPeriodosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpPeriodosLayout.createSequentialGroup()
                        .addGroup(jpPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jpPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPeriodosLayout.createSequentialGroup()
                                .addComponent(jtxtPorcentajePreparativo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jtxtSemanasPreparativo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpPeriodosLayout.createSequentialGroup()
                                .addComponent(jtxtPorcentajeCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jtxtSemanasCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))
                    .addGroup(jpPeriodosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jpPeriodosLayout.setVerticalGroup(
            jpPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPeriodosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtxtPorcentajePreparativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSemanasPreparativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtxtPorcentajeCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSemanasCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpPeriodoCompetitivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Periodo competitivo");

        jLabel12.setText("Precompetitivo");

        jtxtPorcentajeEtapaPrecompetitivo.setEnabled(false);

        jtxtPorcentajeEtapaCompetitivo.setEnabled(false);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("%");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Semanas");

        jLabel19.setText("Competitivo");

        javax.swing.GroupLayout jpPeriodoCompetitivoLayout = new javax.swing.GroupLayout(jpPeriodoCompetitivo);
        jpPeriodoCompetitivo.setLayout(jpPeriodoCompetitivoLayout);
        jpPeriodoCompetitivoLayout.setHorizontalGroup(
            jpPeriodoCompetitivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPeriodoCompetitivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPeriodoCompetitivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jpPeriodoCompetitivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPeriodoCompetitivoLayout.createSequentialGroup()
                        .addGroup(jpPeriodoCompetitivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPeriodoCompetitivoLayout.createSequentialGroup()
                                .addComponent(jtxtPorcentajeEtapaPrecompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jtxtSemanasPrecompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpPeriodoCompetitivoLayout.createSequentialGroup()
                                .addComponent(jtxtPorcentajeEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jtxtSemanasEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPeriodoCompetitivoLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jpPeriodoCompetitivoLayout.setVerticalGroup(
            jpPeriodoCompetitivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPeriodoCompetitivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPeriodoCompetitivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpPeriodoCompetitivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtPorcentajeEtapaPrecompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSemanasPrecompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jpPeriodoCompetitivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtPorcentajeEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSemanasEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap())
        );

        jpPeriodoPreparativo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setText("Etapa especial");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel16.setText("Periodo preparativo ");

        jtxtPorcentajeEtapaPreparacion.setEnabled(false);

        jtxtPorcentajeEtapaEspecial.setEnabled(false);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("%");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Semanas");

        jLabel20.setText("Etapa preparación");

        javax.swing.GroupLayout jpPeriodoPreparativoLayout = new javax.swing.GroupLayout(jpPeriodoPreparativo);
        jpPeriodoPreparativo.setLayout(jpPeriodoPreparativoLayout);
        jpPeriodoPreparativoLayout.setHorizontalGroup(
            jpPeriodoPreparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPeriodoPreparativoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPeriodoPreparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpPeriodoPreparativoLayout.createSequentialGroup()
                        .addGroup(jpPeriodoPreparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpPeriodoPreparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPeriodoPreparativoLayout.createSequentialGroup()
                                .addComponent(jtxtPorcentajeEtapaPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jtxtSemanasPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpPeriodoPreparativoLayout.createSequentialGroup()
                                .addComponent(jtxtPorcentajeEtapaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jtxtSemanasEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))
                    .addGroup(jpPeriodoPreparativoLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jpPeriodoPreparativoLayout.setVerticalGroup(
            jpPeriodoPreparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPeriodoPreparativoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPeriodoPreparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpPeriodoPreparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtPorcentajeEtapaPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSemanasPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jpPeriodoPreparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jtxtPorcentajeEtapaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSemanasEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel21.setText("Status del plan: ");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel22.setText("Aprobado");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Inicio del plan: ");

        jtxtSemanaInicio.setEditable(false);
        jtxtSemanaInicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jtxtSemanaInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jtxtSemanaFin.setEditable(false);
        jtxtSemanaFin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jtxtSemanaFin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Fin del plan: ");

        jbtnCrearMacrociclo.setBackground(new java.awt.Color(204, 255, 204));
        jbtnCrearMacrociclo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtnCrearMacrociclo.setText("Crear macrociclo");
        jbtnCrearMacrociclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCrearMacrocicloActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Deporte");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Rama");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Jefe de rama");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Ent. Aux. / Prep. Fis.");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Metodólogo");

        jbtnValidar.setBackground(new java.awt.Color(204, 255, 255));
        jbtnValidar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtnValidar.setText("Validar detallado");
        jbtnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnValidarActionPerformed(evt);
            }
        });

        txtDeporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeporteActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        btnCalcularEtapas.setText("Calcular Etapas");
        btnCalcularEtapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularEtapasActionPerformed(evt);
            }
        });

        btnValidarSemanas.setText("Validar Semanas");
        btnValidarSemanas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarSemanasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMacrocicloLayout = new javax.swing.GroupLayout(jpMacrociclo);
        jpMacrociclo.setLayout(jpMacrocicloLayout);
        jpMacrocicloLayout.setHorizontalGroup(
            jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnCrearMacrociclo)
                .addGap(151, 151, 151))
            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCalcularEtapas)
                                    .addComponent(jpPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addComponent(jpPeriodoPreparativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jpPeriodoCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnValidarSemanas)))
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelMeso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelMicro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelCicli, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelAcento, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel26))
                                .addGap(18, 18, 18)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnValidar)
                                    .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelDistribucion)
                                        .addComponent(jspTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 1609, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                            .addComponent(jLabel21)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGap(346, 346, 346)
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel23)
                                                    .addComponent(jLabel24))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jtxtSemanaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jtxtSemanaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(997, 997, 997))
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGap(681, 681, 681)
                                                .addComponent(jLabel2)
                                                .addGap(37, 37, 37)
                                                .addComponent(jtxtSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jlbSalir)
                                                .addGap(85, 85, 85)))))))
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1313, Short.MAX_VALUE))))
        );
        jpMacrocicloLayout.setVerticalGroup(
            jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel23)
                                    .addComponent(jtxtSemanaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jtxtSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jlbSalir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jtxtSemanaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcularEtapas)
                    .addComponent(btnValidarSemanas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpPeriodoCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpPeriodos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jpPeriodoPreparativo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(jLabelDistribucion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addComponent(jLabelMeso)
                        .addGap(2, 2, 2)
                        .addComponent(jLabelMicro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCicli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelAcento)))
                .addGap(18, 18, 18)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(31, 31, 31)
                .addComponent(jbtnValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jbtnCrearMacrociclo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMacrociclo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMacrociclo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jlbSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbSalirMouseClicked
        String[] opciones = {"Aceptar", "Cancelar"};

        int opcion = JOptionPane.showOptionDialog(this, "¿Quieres regresar al menu principal?", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                opciones, opciones[0]);

        if (opcion == 0) {
            inicioFrm inicio = new inicioFrm();
            this.dispose();
        } else if (opcion == 1) {
            
        }
    }//GEN-LAST:event_jlbSalirMouseClicked

    private void jbtnCrearMacrocicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCrearMacrocicloActionPerformed
        //opciones al crear macrociclo
        String[] opciones = {"Aceptar", "Cancelar"};
        //si las fechas del macrociclo son validas
            //obtiene el valor elegido
            int opcion = JOptionPane.showOptionDialog(this, "¿Quieres crear este macrociclo?", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                opciones, opciones[0]);
            if (opcion == 0) {
                //si se quiere crear el macrociclo
                //crea la pantalla para la creacion y se envia esta al constructor
                this.dispose();
            }
    }//GEN-LAST:event_jbtnCrearMacrocicloActionPerformed

    private void jbtnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnValidarActionPerformed
        // TODO add your handling code here:
        try{
            this.validarEtapas();
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Error. No se introdujo un numero entero en los periodos");
        }
        
    }//GEN-LAST:event_jbtnValidarActionPerformed

    private void txtDeporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeporteActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void btnCalcularEtapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularEtapasActionPerformed
        // TODO add your handling code here:
        try{
            this.validarPeriodos();
            this.calcularPorcentajePeriodos();
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Error. No se introdujo un numero entero en los periodos");
        }        
    }//GEN-LAST:event_btnCalcularEtapasActionPerformed

    private void btnValidarSemanasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarSemanasActionPerformed
        // TODO add your handling code here:
        try{
            this.validarEtapas();
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Error. No se introdujo un numero entero en los periodos");
        }        
    }//GEN-LAST:event_btnValidarSemanasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcularEtapas;
    private javax.swing.JButton btnValidarSemanas;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAcento;
    private javax.swing.JLabel jLabelCicli;
    private javax.swing.JLabel jLabelDistribucion;
    private javax.swing.JLabel jLabelMeso;
    private javax.swing.JLabel jLabelMicro;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton jbtnCrearMacrociclo;
    private javax.swing.JButton jbtnValidar;
    private javax.swing.JLabel jlbSalir;
    private javax.swing.JPanel jpMacrociclo;
    private javax.swing.JPanel jpPeriodoCompetitivo;
    private javax.swing.JPanel jpPeriodoPreparativo;
    private javax.swing.JPanel jpPeriodos;
    private javax.swing.JScrollPane jspTabla;
    private javax.swing.JTextField jtxtPorcentajeCompetitivo;
    private javax.swing.JTextField jtxtPorcentajeEtapaCompetitivo;
    private javax.swing.JTextField jtxtPorcentajeEtapaEspecial;
    private javax.swing.JTextField jtxtPorcentajeEtapaPrecompetitivo;
    private javax.swing.JTextField jtxtPorcentajeEtapaPreparacion;
    private javax.swing.JTextField jtxtPorcentajePreparativo;
    private javax.swing.JTextField jtxtSemanaFin;
    private javax.swing.JTextField jtxtSemanaInicio;
    private javax.swing.JTextField jtxtSemanas;
    private javax.swing.JTextField jtxtSemanasCompetitivo;
    private javax.swing.JTextField jtxtSemanasEspecial;
    private javax.swing.JTextField jtxtSemanasEtapaCompetitivo;
    private javax.swing.JTextField jtxtSemanasPrecompetitivo;
    private javax.swing.JTextField jtxtSemanasPreparacion;
    private javax.swing.JTextField jtxtSemanasPreparativo;
    private javax.swing.JTable tblMesociclos;
    private javax.swing.JTextField txtDeporte;
    // End of variables declaration//GEN-END:variables
}
