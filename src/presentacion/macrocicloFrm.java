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
        this.activarCamposEdicion();
        
    }
        
    public void ocultarTablaMeso(){
        this.jspEtapaPreparacion.setEnabled(false);
//        this.jbtnValidar.setEnabled(false);
        this.tblEtapaPreparacion.setEnabled(false);
    }
    
    public void mostrarTablaMeso(){
        this.jspEtapaPreparacion.setEnabled(true);
  //      this.jbtnValidar.setEnabled(true);
        this.tblEtapaPreparacion.setEnabled(true);
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
        
       
        boolean textosValidos = validarEntradasTexto();
        
        if (textosValidos) {
            
        } else{
            return;
        }
        
        //a
        this.desactivarCamposEdicion();
        this.mostrarTablaMeso();
        
        ///ojo
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
    
    
    
      public boolean validarEntradasTexto() {

        String txtDeporte = this.txtDeporte.getText();
        String txtEntAux = this.txtEntAux.getText();
        String txtMetodologo = this.txtMetodologo.getText();
        String txtRama = this.txtRama.getText();
        String txtJefeRama = this.txtJefeRama.getText();

        if (txtDeporte.equals("")) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada de deporte no puede estar vacío");
            return false;
        } else if (txtRama.equals("")) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada de la rama no puede estar vacío");
            return false;
        } else if (txtJefeRama.equals("")) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada del jefe de rama no puede estar vacío");
            return false;
        } else if (txtEntAux.equals("")) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada de entidad auxiliar/preparación física no puede estar vacío");
            return false;
        } else if (txtMetodologo.equals("")) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada del metodólogo no puede estar vacío");
            return false;
        }

        if (txtDeporte.length() > 50) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada de deporte sobrepasa los 50 caracteres");
            return false;
        } else if (txtEntAux.length() > 50) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada de entidad auxiliar/preparación física sobrepasa los 50 caracteres");
            return false;
        } else if (txtMetodologo.length() > 50) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada del metodólogo sobrepasa los 50 caracteres");
            return false;
        } else if (txtRama.length() > 50) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada de la rama sobrepasa los 50 caracteres");
            return false;
        } else if (txtJefeRama.length() > 50) {
            JOptionPane.showMessageDialog(this, "Error. El campo de entrada del jefe de rama sobrepasa los 50 caracteres");
            return false;
        }

        return true;

    }

    public void activarCamposEdicion() {
        
        
        this.txtDeporte.setEnabled(true);
        this.txtEntAux.setEnabled(true);
        this.txtJefeRama.setEnabled(true);
        this.txtMetodologo.setEnabled(true);
        this.txtRama.setEnabled(true);

        this.jtxtSemanaFin.setEnabled(true);
        this.jtxtSemanaInicio.setEnabled(true);
        this.jtxtSemanas.setEnabled(true);
        this.jtxtSemanasCompetitivo.setEnabled(true);
        this.jtxtSemanasEspecial.setEnabled(true);
        this.jtxtSemanasEtapaCompetitivo.setEnabled(true);
        this.jtxtSemanasPrecompetitivo.setEnabled(true);
        this.jtxtSemanasPreparacion.setEnabled(true);
        this.jtxtSemanasPreparativo.setEnabled(true);

        //Pa descativar el boton de hacer un macrociclo nuevo mientras el usuario se encuentre editando los campos
        this.jbtnCrearMacrociclo.setEnabled(false);
        this.jbtnEditarMacrociclo.setEnabled(false);
        this.jLabel21.setVisible(false);
        this.jLabel22.setVisible(false);

    }

    public void desactivarCamposEdicion() {
        
        this.txtDeporte.setEnabled(false);
        this.txtEntAux.setEnabled(false);
        this.txtJefeRama.setEnabled(false);
        this.txtMetodologo.setEnabled(false);
        this.txtRama.setEnabled(false);

        this.jtxtSemanaFin.setEnabled(false);
        this.jtxtSemanaInicio.setEnabled(false);
        this.jtxtSemanas.setEnabled(false);
        this.jtxtSemanasCompetitivo.setEnabled(false);
        this.jtxtSemanasEspecial.setEnabled(false);
        this.jtxtSemanasEtapaCompetitivo.setEnabled(false);
        this.jtxtSemanasPrecompetitivo.setEnabled(false);
        this.jtxtSemanasPreparacion.setEnabled(false);
        this.jtxtSemanasPreparativo.setEnabled(false);

        //Pa activar el botón de hacer un macrociclo nuevo
        this.jbtnCrearMacrociclo.setEnabled(true);
        this.jbtnEditarMacrociclo.setEnabled(true);
        this.jLabel21.setVisible(true);
        this.jLabel22.setVisible(true);
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
        jspEtapaPreparacion = new javax.swing.JScrollPane();
        tblEtapaPreparacion = new javax.swing.JTable();
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
        txtDeporte = new javax.swing.JTextField();
        txtRama = new javax.swing.JTextField();
        txtJefeRama = new javax.swing.JTextField();
        txtEntAux = new javax.swing.JTextField();
        txtMetodologo = new javax.swing.JTextField();
        btnCalcularEtapas = new javax.swing.JButton();
        btnValidarSemanas = new javax.swing.JButton();
        jspEtapaEspecial = new javax.swing.JScrollPane();
        tblEtapaEspecial = new javax.swing.JTable();
        jLabelDistribucion1 = new javax.swing.JLabel();
        jLabelMeso1 = new javax.swing.JLabel();
        jLabelMicro1 = new javax.swing.JLabel();
        jLabelCicli1 = new javax.swing.JLabel();
        jLabelAcento1 = new javax.swing.JLabel();
        jspEtapaPrecompetitivo = new javax.swing.JScrollPane();
        tblEtapaPrecompetitivo = new javax.swing.JTable();
        jLabelDistribucion2 = new javax.swing.JLabel();
        jLabelMeso2 = new javax.swing.JLabel();
        jLabelMicro2 = new javax.swing.JLabel();
        jLabelCicli2 = new javax.swing.JLabel();
        jLabelAcento2 = new javax.swing.JLabel();
        jLabelDistribucion3 = new javax.swing.JLabel();
        jspEtapaCompetitivo = new javax.swing.JScrollPane();
        tblEtapaCompetitivo = new javax.swing.JTable();
        jLabelMeso3 = new javax.swing.JLabel();
        jLabelMicro3 = new javax.swing.JLabel();
        jLabelCicli3 = new javax.swing.JLabel();
        jLabelAcento3 = new javax.swing.JLabel();
        jbtnEditarMacrociclo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de macrociclo");

        jpMacrociclo.setBackground(new java.awt.Color(255, 255, 255));

        jspEtapaPreparacion.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspEtapaPreparacion.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblEtapaPreparacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblEtapaPreparacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4", "5"
            }
        ));
        tblEtapaPreparacion.setDragEnabled(true);
        tblEtapaPreparacion.setDropMode(javax.swing.DropMode.INSERT);
        tblEtapaPreparacion.getTableHeader().setResizingAllowed(false);
        tblEtapaPreparacion.getTableHeader().setReorderingAllowed(false);
        jspEtapaPreparacion.setViewportView(tblEtapaPreparacion);

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
        jLabelDistribucion.setText("Distribución por etapa de preparación");

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

        txtDeporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeporteActionPerformed(evt);
            }
        });

        txtEntAux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEntAuxActionPerformed(evt);
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

        jspEtapaEspecial.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspEtapaEspecial.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblEtapaEspecial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblEtapaEspecial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4", "5"
            }
        ));
        tblEtapaEspecial.setDragEnabled(true);
        tblEtapaEspecial.setDropMode(javax.swing.DropMode.INSERT);
        tblEtapaEspecial.getTableHeader().setResizingAllowed(false);
        tblEtapaEspecial.getTableHeader().setReorderingAllowed(false);
        jspEtapaEspecial.setViewportView(tblEtapaEspecial);

        jLabelDistribucion1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelDistribucion1.setText("Distribución por etapa especial");

        jLabelMeso1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMeso1.setText("Mesociclos");

        jLabelMicro1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMicro1.setText("Microciclo");

        jLabelCicli1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelCicli1.setText("Ciclicidad");

        jLabelAcento1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelAcento1.setText("Acentos");

        jspEtapaPrecompetitivo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspEtapaPrecompetitivo.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblEtapaPrecompetitivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblEtapaPrecompetitivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4", "5"
            }
        ));
        tblEtapaPrecompetitivo.setDragEnabled(true);
        tblEtapaPrecompetitivo.setDropMode(javax.swing.DropMode.INSERT);
        tblEtapaPrecompetitivo.getTableHeader().setResizingAllowed(false);
        tblEtapaPrecompetitivo.getTableHeader().setReorderingAllowed(false);
        jspEtapaPrecompetitivo.setViewportView(tblEtapaPrecompetitivo);

        jLabelDistribucion2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelDistribucion2.setText("Distribución por etapa especial");

        jLabelMeso2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMeso2.setText("Mesociclos");

        jLabelMicro2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMicro2.setText("Microciclo");

        jLabelCicli2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelCicli2.setText("Ciclicidad");

        jLabelAcento2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelAcento2.setText("Acentos");

        jLabelDistribucion3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelDistribucion3.setText("Distribución por etapa especial");

        jspEtapaCompetitivo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspEtapaCompetitivo.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblEtapaCompetitivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblEtapaCompetitivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4", "5"
            }
        ));
        tblEtapaCompetitivo.setDragEnabled(true);
        tblEtapaCompetitivo.setDropMode(javax.swing.DropMode.INSERT);
        tblEtapaCompetitivo.getTableHeader().setResizingAllowed(false);
        tblEtapaCompetitivo.getTableHeader().setReorderingAllowed(false);
        jspEtapaCompetitivo.setViewportView(tblEtapaCompetitivo);

        jLabelMeso3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMeso3.setText("Mesociclos");

        jLabelMicro3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMicro3.setText("Microciclo");

        jLabelCicli3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelCicli3.setText("Ciclicidad");

        jLabelAcento3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelAcento3.setText("Acentos");

        jbtnEditarMacrociclo.setBackground(new java.awt.Color(153, 204, 255));
        jbtnEditarMacrociclo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtnEditarMacrociclo.setForeground(new java.awt.Color(0, 0, 0));
        jbtnEditarMacrociclo.setText("Editar macrociclo");
        jbtnEditarMacrociclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarMacrocicloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMacrocicloLayout = new javax.swing.GroupLayout(jpMacrociclo);
        jpMacrociclo.setLayout(jpMacrocicloLayout);
        jpMacrocicloLayout.setHorizontalGroup(
            jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCalcularEtapas)
                                    .addComponent(jpPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addComponent(jpPeriodoPreparativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jpPeriodoCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnValidarSemanas)
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabelMeso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelMicro1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelCicli1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAcento1, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelDistribucion1)
                                            .addComponent(jspEtapaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabelMeso3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelMicro3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelCicli3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAcento3, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelDistribucion3)
                                            .addComponent(jspEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(297, 297, 297))
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel25)
                                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(jLabelMeso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabelMicro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabelCicli, javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabelAcento, javax.swing.GroupLayout.Alignment.LEADING))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabelDistribucion)
                                                            .addComponent(jspEtapaPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabelDistribucion2))))
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel23))
                                            .addComponent(jLabel26))
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGap(116, 116, 116)
                                                .addComponent(jtxtSemanaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jtxtSemanaFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtxtSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addGap(162, 162, 162)
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addComponent(txtRama, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(152, 152, 152)
                                                .addComponent(jLabel24))
                                            .addComponent(txtDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel29))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtMetodologo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtJefeRama, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtEntAux, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(859, 859, 859)))
                                .addGap(167, 167, 167)
                                .addComponent(jlbSalir))
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addComponent(jbtnEditarMacrociclo)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnCrearMacrociclo)))
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpMacrocicloLayout.createSequentialGroup()
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelMeso2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelMicro2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCicli2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAcento2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jspEtapaPrecompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jpMacrocicloLayout.setVerticalGroup(
            jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbSalir)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel23)
                                    .addComponent(jtxtSemanaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(txtRama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtJefeRama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtEntAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtMetodologo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jtxtSemanaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcularEtapas)
                    .addComponent(btnValidarSemanas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpPeriodoCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jpPeriodos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jpPeriodoPreparativo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addComponent(jLabelDistribucion)
                        .addGap(18, 18, 18)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addComponent(jLabelMeso)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelMicro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCicli)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAcento))
                            .addComponent(jspEtapaPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                        .addComponent(jLabelDistribucion1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addComponent(jLabelMeso1)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelMicro1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCicli1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAcento1))
                            .addComponent(jspEtapaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addComponent(jLabelDistribucion2)
                        .addGap(11, 11, 11)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspEtapaPrecompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addComponent(jLabelMeso2)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelMicro2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCicli2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAcento2))))
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addComponent(jLabelDistribucion3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addComponent(jLabelMeso3)
                                .addGap(2, 2, 2)
                                .addComponent(jLabelMicro3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCicli3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAcento3))
                            .addComponent(jspEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(30, 30, 30)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCrearMacrociclo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditarMacrociclo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpMacrociclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void txtDeporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeporteActionPerformed

    private void txtEntAuxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntAuxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntAuxActionPerformed

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

    private void jbtnEditarMacrocicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarMacrocicloActionPerformed
                this.activarCamposEdicion();
    }//GEN-LAST:event_jbtnEditarMacrocicloActionPerformed

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
    private javax.swing.JLabel jLabelAcento1;
    private javax.swing.JLabel jLabelAcento2;
    private javax.swing.JLabel jLabelAcento3;
    private javax.swing.JLabel jLabelCicli;
    private javax.swing.JLabel jLabelCicli1;
    private javax.swing.JLabel jLabelCicli2;
    private javax.swing.JLabel jLabelCicli3;
    private javax.swing.JLabel jLabelDistribucion;
    private javax.swing.JLabel jLabelDistribucion1;
    private javax.swing.JLabel jLabelDistribucion2;
    private javax.swing.JLabel jLabelDistribucion3;
    private javax.swing.JLabel jLabelMeso;
    private javax.swing.JLabel jLabelMeso1;
    private javax.swing.JLabel jLabelMeso2;
    private javax.swing.JLabel jLabelMeso3;
    private javax.swing.JLabel jLabelMicro;
    private javax.swing.JLabel jLabelMicro1;
    private javax.swing.JLabel jLabelMicro2;
    private javax.swing.JLabel jLabelMicro3;
    private javax.swing.JButton jbtnCrearMacrociclo;
    private javax.swing.JButton jbtnEditarMacrociclo;
    private javax.swing.JLabel jlbSalir;
    private javax.swing.JPanel jpMacrociclo;
    private javax.swing.JPanel jpPeriodoCompetitivo;
    private javax.swing.JPanel jpPeriodoPreparativo;
    private javax.swing.JPanel jpPeriodos;
    private javax.swing.JScrollPane jspEtapaCompetitivo;
    private javax.swing.JScrollPane jspEtapaEspecial;
    private javax.swing.JScrollPane jspEtapaPrecompetitivo;
    private javax.swing.JScrollPane jspEtapaPreparacion;
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
    private javax.swing.JTable tblEtapaCompetitivo;
    private javax.swing.JTable tblEtapaEspecial;
    private javax.swing.JTable tblEtapaPrecompetitivo;
    private javax.swing.JTable tblEtapaPreparacion;
    private javax.swing.JTextField txtDeporte;
    private javax.swing.JTextField txtEntAux;
    private javax.swing.JTextField txtJefeRama;
    private javax.swing.JTextField txtMetodologo;
    private javax.swing.JTextField txtRama;
    // End of variables declaration//GEN-END:variables
}
