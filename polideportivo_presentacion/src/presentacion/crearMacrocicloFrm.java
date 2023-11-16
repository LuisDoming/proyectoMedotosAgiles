/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Pantalla que contiene la creacion de las fechas del macrociclo
 *
 * @author ldoar
 */
public class crearMacrocicloFrm extends javax.swing.JFrame {

    /**
     * Strings para el inicio y el fin del plan
     */
    String inicioPlan, finPlan;
    /**
     * Boolean que contiene el estado del macrociclo
     */
    boolean macrocicloValido = false;
    /**
     * Entero para guardar el total de semanas
     */
    int totalSemanas;

    /**
     * Constructor que inicializa la pantalla y sus componentes
     */
    public crearMacrocicloFrm() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //Establece el total de semanas a 0
        this.jtxtTotalSemanas.setText("0");
        //Establece el total de dias a 0
        this.jtxtTotalDias.setText("0");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtxtTotalSemanas1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jpPanel1 = new javax.swing.JPanel();
        jdcFechaInicio = new com.toedter.calendar.JDateChooser();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtTotalSemanas = new javax.swing.JTextField();
        jbtnCalcular = new javax.swing.JButton();
        jtxtTotalDias = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jbtnCrearMacrociclo = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jlbSalir = new javax.swing.JLabel();

        jtxtTotalSemanas1.setEditable(false);

        jLabel9.setText("Total de semanas: ");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Macrociclo");
        setResizable(false);

        jpPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jpPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jdcFechaInicio.setDateFormatString("dd/MM/yyyy");

        jdcFechaFin.setDateFormatString("dd/MM/yyyy");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Nuevo Macrociclo");

        jLabel2.setText("Ingrese fecha de inicio");

        jLabel3.setText("Ingrese fecha de fin");

        jLabel4.setText("Total de semanas: ");

        jtxtTotalSemanas.setEditable(false);
        jtxtTotalSemanas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jbtnCalcular.setBackground(new java.awt.Color(204, 204, 255));
        jbtnCalcular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtnCalcular.setText("Calcular semanas");
        jbtnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCalcularActionPerformed(evt);
            }
        });

        jtxtTotalDias.setEditable(false);
        jtxtTotalDias.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setText("Total de dias: ");

        jbtnCrearMacrociclo.setBackground(new java.awt.Color(204, 255, 204));
        jbtnCrearMacrociclo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtnCrearMacrociclo.setText("Crear macrociclo");
        jbtnCrearMacrociclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCrearMacrocicloActionPerformed(evt);
            }
        });

        jbtnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        jbtnCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jlbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/salir.png"))); // NOI18N
        jlbSalir.setToolTipText("");
        jlbSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbSalirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpPanel1Layout = new javax.swing.GroupLayout(jpPanel1);
        jpPanel1.setLayout(jpPanel1Layout);
        jpPanel1Layout.setHorizontalGroup(
            jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(jpPanel1Layout.createSequentialGroup()
                        .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4))
                        .addGap(34, 34, 34)
                        .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtTotalSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtTotalDias, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jbtnCalcular))
                .addGap(50, 50, 50)
                .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpPanel1Layout.createSequentialGroup()
                        .addComponent(jbtnCrearMacrociclo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbSalir)
                .addGap(35, 35, 35))
        );
        jpPanel1Layout.setVerticalGroup(
            jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlbSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jtxtTotalDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jtxtTotalSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addGap(29, 29, 29)
                .addGroup(jpPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCrearMacrociclo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Manejador de eventos que escucha si se presiona el boton calcular
     *
     * @param evt
     */
    private void jbtnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCalcularActionPerformed
        //declara localdate una fecha de inicio y una fecha fin
        LocalDate fechaInicio = null, fechaFin = null;
        //obtiene la fecha de inicio ingresada por el usuario
        Date fechaElegidaInicio = this.jdcFechaInicio.getDate();
        //obtiene la fecha de finalizacion ingresada por el usuario
        Date fechaElegidaFin = this.jdcFechaFin.getDate();
        //se obtiene la fecha actual
        LocalDate fechaActual = LocalDate.now();
        if (fechaElegidaInicio == null) {
            //si la fecha elegida es nula entonces muestra un mensaje de error
            JOptionPane.showMessageDialog(this, "Error. Ingrese fecha de inicio.");
            jdcFechaInicio.setDate(null);
            jdcFechaFin.setDate(null);
            jtxtTotalDias.setText("0");
            jtxtTotalSemanas.setText("0");
            macrocicloValido = false;
        } else {
            //si la fecha elegida de inicio no es nula
            //Crea un date formatter con el patron especificado
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            //Convierte la fecha de inicio a un instante y a la zona actual del sistema
            fechaInicio = fechaElegidaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            //guarda la fecha de inicio ya con el formato
            inicioPlan = formato.format(fechaInicio);

            //imprime la fecha de inicio
            System.out.println(fechaInicio);
            System.out.println(inicioPlan);
        }

        if (fechaElegidaFin == null) {
            //si la fecha elegida es nula entonces muestra un mensaje de error
            JOptionPane.showMessageDialog(this, "Error. Ingrese fecha de fin.");
            jdcFechaInicio.setDate(null);
            jdcFechaFin.setDate(null);
            jtxtTotalDias.setText("0");
            jtxtTotalSemanas.setText("0");
            macrocicloValido = false;
        } else {
            //si la fecha elegida final no es nula
            //Crea un date formatter con el patron especificado
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            //Convierte la fecha final a un instante y a la zona actual del sistema
            fechaFin = fechaElegidaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            //guarda la fecha final ya con el formato
            finPlan = formato.format(fechaFin);

            //imprime la fecha de inicio
            System.out.println(fechaFin);
            System.out.println(finPlan);
        }

        if (fechaInicio != null && fechaFin != null) {
            //si la fecha de inicio y la fecha de finalizacion no son nulas
            //calcula los dias entre las fechas            
            long diasEntreFechas = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
            //establece al texto la cantidad de dias calculados entre las dos fechas
            this.jtxtTotalDias.setText(Long.toString(diasEntreFechas));

            if (fechaInicio.equals(fechaFin)) {
                //si la fecha de inicio es igual a la fecha de finalizacion
                //manda un mensaje de error y restablece los valores ingresados
                JOptionPane.showMessageDialog(this, "Error. La fecha de finalización es igual a la de inicio.");
                jdcFechaInicio.setDate(null);
                jdcFechaFin.setDate(null);
                jtxtTotalDias.setText("0");
                jtxtTotalSemanas.setText("0");
                macrocicloValido = false;

            }
            if (diasEntreFechas <= 0) {
                //si los dias entre las fechas son menores a iguales a 0
                //manda un mensaje de error y restablece los valores ingresados
                JOptionPane.showMessageDialog(this, "Error. Números totales del macroclico menores o iguales a 0.");
                jdcFechaInicio.setDate(null);
                jdcFechaFin.setDate(null);
                jtxtTotalDias.setText("0");
                jtxtTotalSemanas.setText("0");
                macrocicloValido = false;

            }
            if (diasEntreFechas % 7 != 0) {
                //si los dias totales no son semanas completas
                //muestra un mensaje de error y restablece los valores ingresados
                JOptionPane.showMessageDialog(this, "Error. Tienen que ser semanas completas.");
                jdcFechaInicio.setDate(null);
                jdcFechaFin.setDate(null);
                jtxtTotalDias.setText("0");
                jtxtTotalSemanas.setText("0");
                macrocicloValido = false;

            } else {
                //si pasa todas las validaciones
                //el macrociclo es valido

                macrocicloValido = true;
                //calcula el total de semanas
                long semanasEntreFechas = ChronoUnit.WEEKS.between(fechaInicio, fechaFin);

                //Si por alguna razon queda guardada una semana negativa, nuevamente va pa atras
                if (semanasEntreFechas <= 0) {
                    JOptionPane.showMessageDialog(this, "Error. la fecha de finalización no puede estar antes que la fecha de inicio");
                    jdcFechaInicio.setDate(null);
                    jdcFechaFin.setDate(null);
                    jtxtTotalDias.setText("0");
                    jtxtTotalSemanas.setText("0");
                    macrocicloValido = false;
                }
                //establece el total de semanas a las semanas calculadas 
                System.out.println("Semanas : " + semanasEntreFechas);
                this.jtxtTotalSemanas.setText(Long.toString(semanasEntreFechas));
                totalSemanas = (int) semanasEntreFechas;

            }
        }
    }//GEN-LAST:event_jbtnCalcularActionPerformed
    /**
     * Manejador de eventos cuando se presiona el boton crear macrociclos
     *
     * @param evt
     */
    private void jbtnCrearMacrocicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCrearMacrocicloActionPerformed
        //opciones al crear macrociclo
        String[] opciones = {"Aceptar", "Cancelar"};
        //si las fechas del macrociclo son validas
        if (macrocicloValido) {
            //obtiene el valor elegido
            int opcion = JOptionPane.showOptionDialog(this, "¿Quieres crear este macrociclo?", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    opciones, opciones[0]);
            if (opcion == 0) {
                //si se quiere crear el macrociclo
                //crea la pantalla para la creacion y se envia esta al constructor
                macrocicloFrm macroNuevo = new macrocicloFrm(this);
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error. Macrociclo no válido, se necesita calcular semanas primero.");
            jdcFechaInicio.setDate(null);
            jdcFechaFin.setDate(null);
            jtxtTotalDias.setText("0");
            jtxtTotalSemanas.setText("0");
            macrocicloValido = false;
        }

    }//GEN-LAST:event_jbtnCrearMacrocicloActionPerformed
    /**
     *
     * @param evt
     */
    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        //`opciones al cancelar
        String[] opciones = {"Aceptar", "Cancelar"};

        //obtiene la opcion elegida
        int opcion = JOptionPane.showOptionDialog(this, "¿Quieres cancelar la creación del macrociclo?", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                opciones, opciones[0]);

        if (opcion == 0) {

        } else if (opcion == 1) {
            // Código para la opción "Consultar"
            // Por ejemplo, abrir una ventana para consultar algo.
        }
    }//GEN-LAST:event_jbtnCancelarActionPerformed
    /**
     * Manejador de eventos cuando se presiona el boton salir
     *
     * @param evt
     */
    private void jlbSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbSalirMouseClicked
        //opciones a elegir
        String[] opciones = {"Aceptar", "Cancelar"};

        //obtiene la opcion elegida
        int opcion = JOptionPane.showOptionDialog(this, "¿Quieres regresal al menu principal?", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                opciones, opciones[0]);

        if (opcion == 0) {
            //si elige salir
            //vuelve a la pantalla de inicio
            inicioFrm inicio = new inicioFrm();
            this.dispose();
        } else if (opcion == 1) {

        }
    }//GEN-LAST:event_jlbSalirMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbtnCalcular;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnCrearMacrociclo;
    private com.toedter.calendar.JDateChooser jdcFechaFin;
    private com.toedter.calendar.JDateChooser jdcFechaInicio;
    private javax.swing.JLabel jlbSalir;
    private javax.swing.JPanel jpPanel1;
    private javax.swing.JTextField jtxtTotalDias;
    private javax.swing.JTextField jtxtTotalSemanas;
    private javax.swing.JTextField jtxtTotalSemanas1;
    // End of variables declaration//GEN-END:variables
}