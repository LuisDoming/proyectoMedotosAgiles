/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agiles.presentacion;

import com.agiles.entidades.Macrociclo;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.agiles.polideportivo_negocio.macrocicloNegocio;

/**
 * Pantalla que contiene la distribucion de los mesociclos y la distribucion de
 * semanas
 *
 * @author ldoar
 */
public class macrocicloFrm extends javax.swing.JFrame {

    /**
     * Pantalla anterior (pantalla de seleccion de semana de macrociclos
     */
    private crearMacrocicloFrm crearMacro;
    private Macrociclo macrociclo;
    private int semanasTotales;
    private boolean precompetitivasActiva = false;
    private boolean preparativaActiva = false;
    private boolean competitivaActiva = false;
    private boolean especialActiva = false;
    private ArrayList<String> distribucionPreparativa, distribucionEspecial, distribucionPrecom, distribucionCompetitiva;
    private ArrayList<String> ciclicidadPreparativa, ciclicidadEspecial, ciclicidadPrecom, ciclicidadCompetitiva;

    /**
     * Constructor que inicializa la pantalla y sus componentes
     *
     * @param crearMacro recibe una instancia de crearMacro para acceder las
     * semanas
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
        ///Para que no se active antes de tiempo el botón de editar etapas
        this.jbtnEditarEtapas.setEnabled(false);

        iniciarTablas();
        bloquearBotonesMesociclos();
        bloqueoBtnValidacionTablas();
    }

    /**
     * Metodo que oculta deshabilita las tablas de mesociclos
     */
    public void ocultarTablaMeso() {
        this.jspEtapaPreparacion.setEnabled(false);
        this.tblEtapaPreparacion.setEnabled(false);

        this.jspEtapaCompetitivo.setEnabled(false);
        this.jspEtapaPrecompetitivo.setEnabled(false);
        this.jspEtapaEspecial.setEnabled(false);

        this.tblEtapaCompetitivo.setEnabled(false);
        this.tblEtapaPrecompetitivo.setEnabled(false);
        this.tblEtapaEspecial.setEnabled(false);

        this.txtAgregarCiclicidadPreparacion.setText("0,0");
        this.txtAgregarMicrocicloPreparacion.setText("0");
        this.txtAgregarCiclicidadPreparacion.setEnabled(false);
        this.txtAgregarMicrocicloPreparacion.setEnabled(false);

        this.txtAgregarCiclicidadEspecial.setText("0,0");
        this.txtAgregarMicrocicloEspecial.setText("0");
        this.txtAgregarCiclicidadEspecial.setEnabled(false);
        this.txtAgregarMicrocicloEspecial.setEnabled(false);

        this.txtAgregarCiclicidadPrecompetitiva.setText("0,0");
        this.txtAgregarMicrocicloPrecompetitiva.setText("0");
        this.txtAgregarCiclicidadPrecompetitiva.setEnabled(false);
        this.txtAgregarMicrocicloPrecompetitiva.setEnabled(false);

        this.txtAgregarCiclicidadCompetitiva.setText("0,0");
        this.txtAgregarMicrocicloCompetitiva.setText("0");
        this.txtAgregarCiclicidadCompetitiva.setEnabled(false);
        this.txtAgregarMicrocicloCompetitiva.setEnabled(false);
    }

    /**
     * Metodo que habilita las tablas de mesociclos
     */
    public void mostrarTablaMeso() {

        String textoSemanaPreparacion = this.jtxtSemanasPreparacion.getText();
        String textoSemanaEspecial = this.jtxtSemanasEspecial.getText();
        String textoSemanaPrecompetitiva = this.jtxtSemanasPrecompetitivo.getText();
        String textoSemanaCompetitiva = this.jtxtSemanasEtapaCompetitivo.getText();

        int etapaPreparacion = Integer.valueOf(textoSemanaPreparacion);
        int etapaEspecial = Integer.valueOf(textoSemanaEspecial);
        int etapaPrecompetitivo = Integer.valueOf(textoSemanaPrecompetitiva);
        int etapaCompetitivo = Integer.valueOf(textoSemanaCompetitiva);

        if (etapaPreparacion > 0) {
            this.jspEtapaPreparacion.setEnabled(true);
            //this.tblEtapaPreparacion.setEnabled(true);
            this.txtAgregarCiclicidadPreparacion.setEnabled(true);
            this.txtAgregarMicrocicloPreparacion.setEnabled(true);
        }

        if (etapaEspecial > 0) {
            this.jspEtapaEspecial.setEnabled(true);
            //this.tblEtapaEspecial.setEnabled(true);
            this.txtAgregarCiclicidadEspecial.setEnabled(true);
            this.txtAgregarMicrocicloEspecial.setEnabled(true);
        }

        if (etapaPrecompetitivo > 0) {
            this.jspEtapaPrecompetitivo.setEnabled(true);
            //this.tblEtapaPrecompetitivo.setEnabled(true);
            this.txtAgregarCiclicidadPrecompetitiva.setEnabled(true);
            this.txtAgregarMicrocicloPrecompetitiva.setEnabled(true);

        }

        if (etapaCompetitivo > 0) {
            this.jspEtapaCompetitivo.setEnabled(true);
            //this.tblEtapaCompetitivo.setEnabled(true);
            this.txtAgregarCiclicidadCompetitiva.setEnabled(true);
            this.txtAgregarMicrocicloCompetitiva.setEnabled(true);
        }

    }

    /**
     * metodo que deshabilita las tablas de desglose de semanas de las etapas
     */
    public void deshabilitarEtapas() {
        this.jpPeriodoPreparativo.setVisible(false);
        this.jpPeriodoCompetitivo.setVisible(false);
        this.btnValidarSemanas.setVisible(false);
    }

    /**
     * metodo que habilita las tablas de desglose de semanas de las etapas
     */
    public void habilitarEtapas() {
        this.jpPeriodoPreparativo.setVisible(true);
        this.jpPeriodoCompetitivo.setVisible(true);
        this.btnValidarSemanas.setVisible(true);
    }

    /**
     * valida las semanas del formulario detallado desglosado de las etapas
     */
    public boolean validarEtapas() {

        //obtiene los datos ingresados al formulario de periodo ingresado anteriormente
        int preparativa = Integer.valueOf(this.jtxtSemanasPreparativo.getText());
        int competitiva = Integer.valueOf(this.jtxtSemanasCompetitivo.getText());

        //crea una variable para cada uno de los campos del desglose
        int etapaPreparacion, etapaEspecial, etapaPrecompetitiva = 0, etapaCompetitiva, totalIngresado;
        //variables para el total de semanas ingresadas al periodo y al competitivo
        int totalPreparativo, totalCompetitivo;

        //obtiene el valor ingresado para la etapa precompetitiva
        String precompetitiva = this.jtxtSemanasPrecompetitivo.getText();

        if (precompetitiva.equalsIgnoreCase("") || precompetitiva.equalsIgnoreCase("0")) {
            //si se dejo el campo vacio o igual a 0
            //obtiene los datos ingresados en los campos excepto el precompetitivo
            etapaPreparacion = Integer.valueOf(this.jtxtSemanasPreparacion.getText());
            etapaEspecial = Integer.valueOf(this.jtxtSemanasEspecial.getText());
            etapaCompetitiva = Integer.valueOf(this.jtxtSemanasEtapaCompetitivo.getText());

            //calcula el total ingresado asi como el total preparativo y el total competitivo
            totalIngresado = etapaPreparacion + etapaEspecial + etapaCompetitiva;
            totalPreparativo = etapaPreparacion + etapaEspecial;
            totalCompetitivo = etapaCompetitiva;

            if (etapaPreparacion <= 0) {
                this.preparativaActiva = false;
            } else {
                this.preparativaActiva = true;
            }

            if (etapaEspecial <= 0) {
                this.especialActiva = false;
            } else {
                this.especialActiva = true;
            }

            if (etapaCompetitiva <= 0) {
                this.competitivaActiva = false;
            } else {
                this.competitivaActiva = true;
            }

            this.precompetitivasActiva = false;
        } else {
            //si se ingresa algun valor en la competitiva
            //obtiene los valores de todos los campos
            etapaPreparacion = Integer.valueOf(this.jtxtSemanasPreparacion.getText());
            etapaEspecial = Integer.valueOf(this.jtxtSemanasEspecial.getText());
            etapaPrecompetitiva = Integer.valueOf(this.jtxtSemanasPrecompetitivo.getText());
            etapaCompetitiva = Integer.valueOf(this.jtxtSemanasEtapaCompetitivo.getText());

            //calcula el total como el total preparativo y el competitivo
            totalIngresado = etapaPreparacion + etapaEspecial + etapaPrecompetitiva + etapaCompetitiva;
            totalPreparativo = etapaPreparacion + etapaEspecial;
            totalCompetitivo = etapaCompetitiva + etapaPrecompetitiva;

            if (etapaPreparacion <= 0) {
                this.preparativaActiva = false;
            } else {
                this.preparativaActiva = true;
            }

            if (etapaEspecial <= 0) {
                this.especialActiva = false;
            } else {
                this.especialActiva = true;
            }

            if (etapaCompetitiva <= 0) {
                this.competitivaActiva = false;
            } else {
                this.competitivaActiva = true;
            }

            if (etapaPrecompetitiva <= 0) {
                this.precompetitivasActiva = false;
            } else {
                this.precompetitivasActiva = true;
            }

        }

        //validaciones
        if (totalIngresado != semanasTotales) {
            //si el total ingresado no es igual a las semanas totales del macrociclo
            //muestra un mensaje de error
            JOptionPane.showMessageDialog(this, "Error. La suma de semanas ingresada no es igual a la semanas totales establecidas"
                    + " para el macrociclo");
            resetearPeriodos();
            return false;
        }

        if (totalPreparativo > preparativa) {
            //si el total de la etapa preparativa y especial
            //es mayor al valor del preparativo del perido
            //muestra mensaje de error
            JOptionPane.showMessageDialog(this, "Error. La suma de semanas de preparacion ingresada no es igual a la semanas totales establecidas");
            resetearPeriodos();
            return false;
        }

        if (totalCompetitivo > competitiva) {
            //si el total de la etapa precompetitiva y competitiva
            //es mayor al valor del competitivo del periodo
            //muestra mensaje de error            
            JOptionPane.showMessageDialog(this, "Error. La suma de semanas competitivas ingresada no es igual a la semanas totales establecidas");
            resetearPeriodos();
            return false;
        }

        boolean textosValidos = validarEntradasTexto();
        if (textosValidos) {
            //si los textos son validos no continua

        } else {
            return false;
        }

        //desactiva los campos de edicion
        this.desactivarCamposEdicion();
        this.jbtnCrearMacrociclo.setEnabled(false);
        //muestra la tabla de mesociclos
        this.mostrarTablaMeso();

        ///Carga las tablas con la info de semanas
        this.cargarSemanasPreparacion(etapaPreparacion);
        this.cargarSemanasEspecial(etapaEspecial);
        this.cargarSemanasPrecompetitiva(etapaPrecompetitiva);
        this.cargarSemanasCompetitiva(etapaCompetitiva);
        desbloqueoBtnValidacionTablas();
        return true;
    }

    /**
     * valida el primer formulario de desglose de semanas (preparativo y
     * competitivo)
     */
    public void validarPeriodos() {
        //obtiene los datos introducidos
        int etapaPreparativa = Integer.valueOf(this.jtxtSemanasPreparativo.getText());
        int etapaCompetitiva = Integer.valueOf(this.jtxtSemanasCompetitivo.getText());

        //calcula la sumatoria de estos campos
        int totalIngresado = etapaPreparativa + etapaCompetitiva;

        if (totalIngresado != semanasTotales) {
            //si excede el total de semanas del macrociclo muestra un mensaje de error
            JOptionPane.showMessageDialog(this, "Error. La suma de semanas ingresada no es igual a la semanas totales establecidas"
                    + " para el macrociclo");

            this.resetearCamposSemanas();
            return;
        }

        if (etapaPreparativa <= etapaCompetitiva) {
            //si la etapa preparativa es menor o igual a la competitiva entonces
            //muestra un mensaje de error
            JOptionPane.showMessageDialog(this, "Error. la semana preparativa debe contenes mas semanas que la semana competitiva");

            this.resetearCamposSemanas();
            return;
        }

        //habilita las siguientes tablas
        this.habilitarEtapas();

        //Bloquea el calcular, desbloquea el editar
        this.btnCalcularEtapas.setEnabled(false);
        this.jbtnEditarEtapas.setEnabled(true);
        this.jtxtSemanasPreparativo.setEnabled(false);
        this.jtxtSemanasCompetitivo.setEnabled(false);

    }

    /**
     * Calcula el porcentaje de los periodos
     */
    public void calcularPorcentajePeriodos() {
        //obtiene los valores introducidos
        int etapaPreparativa = Integer.valueOf(this.jtxtSemanasPreparativo.getText());
        int etapaCompetitiva = Integer.valueOf(this.jtxtSemanasCompetitivo.getText());

        //obtiene los porcentajes
        int porcentajePreparativa = (etapaPreparativa * 100) / semanasTotales;
        int porcentajeCompetitiva = (etapaCompetitiva * 100) / semanasTotales;

        //asigna los porcentajes a los campos de texto correspondiente
        this.jtxtPorcentajePreparativo.setText(String.valueOf(porcentajePreparativa));
        this.jtxtPorcentajeCompetitivo.setText(String.valueOf(porcentajeCompetitiva));

    }

    /**
     * Valida las entradas de texto del formulario de datos del macrociclo
     *
     * @return True si las entradas son correctas
     */
    public boolean validarEntradasTexto() {

        //obtiene las cadenas ingresadas en los campos
        String txtDeporte = this.cbDeporte.getSelectedItem().toString();
        String txtRama = this.cbRama.getSelectedItem().toString();
        String txtEntAux = this.txtEntAux.getText();
        String txtMetodologo = this.txtMetodologo.getText();
        String txtJefeRama = this.txtJefeRama.getText();

        //si esta vacia regresa falso mostrando errores
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

        //si algunos de los textos es mayor a 50
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

        //regresa verdadero
        return true;

    }

    /**
     * Método para activar todos los campos para editar informacion
     */
    public void activarCamposEdicion() {

        //habilita todos los campos
        this.cbDeporte.setEnabled(true);
        this.cbRama.setEnabled(true);
        this.txtEntAux.setEnabled(true);
        this.txtJefeRama.setEnabled(true);
        this.txtMetodologo.setEnabled(true);

        //this.jtxtSemanaFin.setEnabled(true);
        //this.jtxtSemanaInicio.setEnabled(true);
        //this.jtxtSemanas.setEnabled(true);
        this.jtxtSemanasCompetitivo.setEnabled(true);
        this.jtxtSemanasEspecial.setEnabled(true);
        this.jtxtSemanasEtapaCompetitivo.setEnabled(true);
        this.jtxtSemanasPrecompetitivo.setEnabled(true);
        this.jtxtSemanasPreparacion.setEnabled(true);
        this.jtxtSemanasPreparativo.setEnabled(true);

        //descativar el boton de hacer un macrociclo nuevo mientras el usuario se encuentre editando los campos
        this.jbtnCrearMacrociclo.setEnabled(false);
        this.jbtnEditarMacrociclo.setEnabled(false);
        this.jLabel21.setVisible(false);
        this.jLabel22.setVisible(false);

        //Activar el boton de validar semanas y editar etapas
        this.jbtnEditarEtapas.setEnabled(true);
        this.btnValidarSemanas.setEnabled(true);

        this.jbtnAgregarCompetitiva.setEnabled(false);
        this.jbtnAgregarEspecial.setEnabled(false);
        this.jbtnAgregarPrecompetitiva.setEnabled(false);
        this.jbtnAgregarPreparacion.setEnabled(false);

        this.jbtnEliminarCompetitivo.setEnabled(false);
        this.jbtnEliminarEspecial.setEnabled(false);
        this.jbtnEliminarPrecompetitivo.setEnabled(false);
        this.jbtnEliminarPreparacion.setEnabled(false);

    }

    /**
     * Método para desactivar todos los campos
     */
    public void desactivarCamposEdicion() {

        //desactiva todos los campos
        this.cbDeporte.setEnabled(false);
        this.cbRama.setEnabled(false);
        this.txtEntAux.setEnabled(false);
        this.txtJefeRama.setEnabled(false);
        this.txtMetodologo.setEnabled(false);

        //this.jtxtSemanaFin.setEnabled(false);
        //this.jtxtSemanaInicio.setEnabled(false);
        //this.jtxtSemanas.setEnabled(false);
        this.jtxtSemanasCompetitivo.setEnabled(false);
        this.jtxtSemanasEspecial.setEnabled(false);
        this.jtxtSemanasEtapaCompetitivo.setEnabled(false);
        this.jtxtSemanasPrecompetitivo.setEnabled(false);
        this.jtxtSemanasPreparacion.setEnabled(false);
        this.jtxtSemanasPreparativo.setEnabled(false);

        //activar el botón de hacer un macrociclo nuevo
        this.jbtnCrearMacrociclo.setEnabled(true);
        this.jbtnEditarMacrociclo.setEnabled(true);

    }

    /**
     * Método que inicializa las tablas, agregandole una columna que guarde el
     * valor de 0
     */
    public void iniciarTablas() {

        DefaultTableModel modeloTablaPreparacion = (DefaultTableModel) this.tblEtapaPreparacion.getModel();
        DefaultTableModel modeloTablaEspecial = (DefaultTableModel) this.tblEtapaEspecial.getModel();
        DefaultTableModel modeloTablaPrecompetitivo = (DefaultTableModel) this.tblEtapaPrecompetitivo.getModel();
        DefaultTableModel modeloTablaCompetitivo = (DefaultTableModel) this.tblEtapaCompetitivo.getModel();

        // Crear un nuevo vector de identificadores de columna con nombres actualizados
        Vector<Object> columnaNueva = new Vector<>();

        for (int columna = 0; columna < modeloTablaPreparacion.getColumnCount(); columna++) {
            columnaNueva.add(0);
        }
        //Inicializa las tablas en 0
        modeloTablaPreparacion.setColumnIdentifiers(columnaNueva);
        modeloTablaEspecial.setColumnIdentifiers(columnaNueva);
        modeloTablaPrecompetitivo.setColumnIdentifiers(columnaNueva);
        modeloTablaCompetitivo.setColumnIdentifiers(columnaNueva);

    }

    /**
     * Funcion que inicializa la tabla de los mesociclos de preparación deja la
     * tabla con una sola columna con un valor.
     *
     * @param numSemanas el numero de semanas para comprobar que si se usara la
     * tabla
     */
    public void cargarSemanasPreparacion(int numSemanas) {

        if (numSemanas > 0) {
            this.jlbSemanasPrep.setText(numSemanas + " semanas");
            this.jbtnAgregarPreparacion.setEnabled(true);
            DefaultTableModel modeloTablaPreparacion = (DefaultTableModel) this.tblEtapaPreparacion.getModel();
            Vector<Object> columnaNueva = new Vector<>();

            columnaNueva.add("1");
            modeloTablaPreparacion.setColumnIdentifiers(columnaNueva);

            //Inicializa la fila de microciclos
            Vector<Object> filaMacrociclo = new Vector<>();

            filaMacrociclo.add("0");

            modeloTablaPreparacion.setRowCount(1);
            modeloTablaPreparacion.addRow(filaMacrociclo);
            modeloTablaPreparacion.setValueAt("0", 0, 0);

        } else {
            ///Si se mandó un 0 o negativo pues no se cambia la tabla
        }

    }

    /**
     * Funcion que inicializa la tabla de los mesociclos de etapa especial deja
     * la tabla con una sola columna con un valor.
     *
     * @param numSemanas el numero de semanas para comprobar que si se usara la
     * tabla
     */
    public void cargarSemanasEspecial(int numSemanas) {

        if (numSemanas > 0) {
            this.jlbSemanasEsp.setText(numSemanas + " semanas");
            this.jbtnAgregarEspecial.setEnabled(true);
            DefaultTableModel modeloTablaEspecial = (DefaultTableModel) this.tblEtapaEspecial.getModel();
            Vector<Object> columnaNueva = new Vector<>();

            columnaNueva.add("1");
            modeloTablaEspecial.setColumnIdentifiers(columnaNueva);

            //Inicializa la fila de microciclos
            Vector<Object> filaMacrociclo = new Vector<>();

            filaMacrociclo.add("0");

            modeloTablaEspecial.setRowCount(1);
            modeloTablaEspecial.addRow(filaMacrociclo);
            modeloTablaEspecial.setValueAt("0", 0, 0);

        } else {
            ///Si se mandó un 0 o negativo pues no se cambia la tabla

        }

    }

    /**
     * Funcion que inicializa la tabla de los mesociclos de etapa precompetitiva
     * deja la tabla con una sola columna con un valor.
     *
     * @param numSemanas el numero de semanas para comprobar que si se usara la
     * tabla
     */
    public void cargarSemanasPrecompetitiva(int numSemanas) {

        if (numSemanas > 0) {
            this.jlbSemanasPrecom.setText(numSemanas + " semanas");
            this.jbtnAgregarPrecompetitiva.setEnabled(true);
            DefaultTableModel modeloTablaPrecompetitivo = (DefaultTableModel) this.tblEtapaPrecompetitivo.getModel();
            Vector<Object> columnaNueva = new Vector<>();

            columnaNueva.add("1");
            modeloTablaPrecompetitivo.setColumnIdentifiers(columnaNueva);

            //Inicializa la fila de microciclos
            Vector<Object> filaMacrociclo = new Vector<>();

            filaMacrociclo.add("0");

            modeloTablaPrecompetitivo.setRowCount(1);
            modeloTablaPrecompetitivo.addRow(filaMacrociclo);
            modeloTablaPrecompetitivo.setValueAt("0", 0, 0);

        } else {
            ///Si se mandó un 0 o negativo pues no se cambia la tabla
        }

    }

    /**
     * Funcion que inicializa la tabla de los mesociclos de etapa competitiva
     * deja la tabla con una sola columna con un valor.
     *
     * @param numSemanas el numero de semanas para comprobar que si se usara la
     * tabla
     */
    public void cargarSemanasCompetitiva(int numSemanas) {

        if (numSemanas > 0) {
            this.jlbSemanasComp.setText(numSemanas + " semanas");
            this.jbtnAgregarCompetitiva.setEnabled(true);
            DefaultTableModel modeloTablaCompetitivo = (DefaultTableModel) this.tblEtapaCompetitivo.getModel();
            Vector<Object> columnaNueva = new Vector<>();

            columnaNueva.add("1");
            modeloTablaCompetitivo.setColumnIdentifiers(columnaNueva);

            //Inicializa la fila de microciclos
            Vector<Object> filaMacrociclo = new Vector<>();

            filaMacrociclo.add("0");

            modeloTablaCompetitivo.setRowCount(1);
            modeloTablaCompetitivo.addRow(filaMacrociclo);
            modeloTablaCompetitivo.setValueAt("0", 0, 0);

        } else {
            ///Si se mandó un 0 o negativo pues no se cambia la tabla
        }

    }

    /**
     * Función que elimina los valores ingresados en los campos de las semanas
     */
    public void resetearCamposSemanas() {

        this.jtxtSemanasPreparativo.setText("");
        this.jtxtSemanasCompetitivo.setText("");
        this.jtxtSemanasPreparacion.setText("");
        this.jtxtSemanasEspecial.setText("");
        this.jtxtSemanasEtapaCompetitivo.setText("");
        this.jtxtSemanasPrecompetitivo.setText("");

    }

    /**
     * Función que elimina los valores ingresados en los campos de periodos
     */
    public void resetearPeriodos() {
        this.jtxtSemanasPreparacion.setText("");
        this.jtxtSemanasEspecial.setText("");
        this.jtxtSemanasEtapaCompetitivo.setText("");
        this.jtxtSemanasPrecompetitivo.setText("");
    }

    /**
     * Función que bloquea los botones para agregar/eliminar columnas en los
     * mesociclos
     */
    public void bloquearBotonesMesociclos() {

        this.jbtnAgregarCompetitiva.setEnabled(false);
        this.jbtnAgregarEspecial.setEnabled(false);
        this.jbtnAgregarPrecompetitiva.setEnabled(false);
        this.jbtnAgregarPreparacion.setEnabled(false);

        this.jbtnEliminarCompetitivo.setEnabled(false);
        this.jbtnEliminarEspecial.setEnabled(false);
        this.jbtnEliminarPrecompetitivo.setEnabled(false);
        this.jbtnEliminarPreparacion.setEnabled(false);

    }

    /**
     * Función que permite agregar una columna (mesociclo) a la tabla que haya
     * sido ingresada como parámetro
     *
     * @param tabla La tabla a la que se le agregará la columna
     * @param numTabla el número de la tabla a activarle sus botones (1: TABLA
     * PREPARACION 2: TABLA ESPECIAL 3: TABLA PRECOMPETITIVA 4: TABLA
     * COMPETITIVA)
     */
    public void agregarMesociclo(JTable tabla, int numTabla, String microciclo, String ciclicidad) {

        //ver si esta en la primera columna
        int totalColumnas = tabla.getColumnCount();

        //NUM 1, TABLA PREPARACION -  NUM 2, TABLA ESPECIAL - NUM 3, TABLA PRECOMPETITIVA - NUM 4, TABLA COMPETITIVA
        if (totalColumnas >= 1) {
            switch (numTabla) {
                case 1:
                    this.jbtnEliminarPreparacion.setEnabled(true);
                    break;
                case 2:
                    this.jbtnEliminarEspecial.setEnabled(true);
                    break;
                case 3:
                    this.jbtnEliminarPrecompetitivo.setEnabled(true);
                    break;
                case 4:
                    this.jbtnEliminarCompetitivo.setEnabled(true);
                    break;
                default:
                    break;
            }
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();

        String primerCiclicidad = "";
        String primerMicro = "";

        try {
            primerCiclicidad = modeloTabla.getValueAt(1, 0).toString();

            primerMicro = modeloTabla.getValueAt(0, 0).toString();
        } catch (Exception e) {

        }

        //Si nomas hay una columna, que edite los que hay
        if (!(microciclo == null && ciclicidad == null)) {

            if (totalColumnas == 1) {

                if (primerMicro.equals("0") || primerCiclicidad.equals("0")) {

                    modeloTabla.setValueAt(microciclo, 0, 0);
                    modeloTabla.setValueAt(ciclicidad, 1, 0);

                    switch (numTabla) {
                        case 1:
                            this.jbtnEliminarPreparacion.setEnabled(false);
                            break;
                        case 2:
                            this.jbtnEliminarEspecial.setEnabled(false);
                            break;
                        case 3:
                            this.jbtnEliminarPrecompetitivo.setEnabled(false);
                            break;
                        case 4:
                            this.jbtnEliminarCompetitivo.setEnabled(false);
                            break;
                        default:
                            break;
                    }

                } else {
                    //Si hay una columna, la siguiente sera la 2.
                    //Si hay 4 columnas, la siguiente sera la 5
                    int numInicial = modeloTabla.getColumnCount();
                    int nuevoNum = numInicial + 1;

                    //Agrego la columna
                    modeloTabla.addColumn(nuevoNum);

                    // Obtener el índice de la nueva columna
                    int nuevaColumnaIndex = modeloTabla.getColumnCount() - 1;

                    // Establecer el valor 0 en la nueva columna 
                    modeloTabla.setValueAt(microciclo, 0, nuevaColumnaIndex);
                    modeloTabla.setValueAt(ciclicidad, 1, nuevaColumnaIndex);
                }

            } else {
                //Si hay una columna, la siguiente sera la 2.
                //Si hay 4 columnas, la siguiente sera la 5
                int numInicial = modeloTabla.getColumnCount();
                int nuevoNum = numInicial + 1;

                //Agrego la columna
                modeloTabla.addColumn(nuevoNum);

                // Obtener el índice de la nueva columna
                int nuevaColumnaIndex = modeloTabla.getColumnCount() - 1;

                // Establecer el valor 0 en la nueva columna 
                modeloTabla.setValueAt(microciclo, 0, nuevaColumnaIndex);
                modeloTabla.setValueAt(ciclicidad, 1, nuevaColumnaIndex);
            }

        }
    }

    /**
     * Función que permite eliminar la última columna (mesociclo) a la tabla que
     * haya sido ingresada como parámetro
     *
     * @param tabla La tabla a la que se le eliminará una columna
     * @param numTabla el número de la tabla a activarle sus botones (1: TABLA
     * PREPARACION 2: TABLA ESPECIAL 3: TABLA PRECOMPETITIVA 4: TABLA
     * COMPETITIVA)
     */
    public void eliminarMesociclo(JTable tabla, int numTabla) {

        DefaultTableModel modeloTablaPreparacion = (DefaultTableModel) tabla.getModel();
        int totalColumnas = modeloTablaPreparacion.getColumnCount();

        if (totalColumnas >= 2) {
            modeloTablaPreparacion.setColumnCount(totalColumnas - 1);

        } else {
            modeloTablaPreparacion.setColumnCount(totalColumnas - 1);
            switch (numTabla) {
                case 1:
                    this.jbtnEliminarPreparacion.setEnabled(false);
                    break;
                case 2:
                    this.jbtnEliminarEspecial.setEnabled(false);
                    break;
                case 3:
                    this.jbtnEliminarPrecompetitivo.setEnabled(false);
                    break;
                case 4:
                    this.jbtnEliminarCompetitivo.setEnabled(false);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * Método que valida las celdas donde se ingresan los campos de microciclos
     * dentro de la distribución de cada una de las etapas
     *
     * @return true si las celdas pasan todas las validaciones, false en caso
     * contrario
     */
    public boolean validarDistribucionEtapas() {

        DefaultTableModel modeloTablaPreparacion = (DefaultTableModel) this.tblEtapaPreparacion.getModel();
        DefaultTableModel modeloTablaEspecial = (DefaultTableModel) this.tblEtapaEspecial.getModel();
        DefaultTableModel modeloTablaPrecompetitivo = (DefaultTableModel) this.tblEtapaPrecompetitivo.getModel();
        DefaultTableModel modeloTablaCompetitivo = (DefaultTableModel) this.tblEtapaCompetitivo.getModel();

        String textoSemanaPreparacion = this.jtxtSemanasPreparacion.getText();
        String textoSemanaEspecial = this.jtxtSemanasEspecial.getText();
        String textoSemanaPrecompetitiva = this.jtxtSemanasPrecompetitivo.getText();
        String textoSemanaCompetitiva = this.jtxtSemanasEtapaCompetitivo.getText();

        int etapaPreparacion = 0, etapaEspecial = 0, etapaPrecompetitivo = 0, etapaCompetitivo = 0;

        try {
            etapaPreparacion = Integer.valueOf(textoSemanaPreparacion);
        } catch (Exception e) {
        }
        try {
            etapaEspecial = Integer.valueOf(textoSemanaEspecial);
        } catch (Exception e) {
        }
        try {
            etapaPrecompetitivo = Integer.valueOf(textoSemanaPrecompetitiva);

        } catch (Exception e) {
        }
        try {
            etapaCompetitivo = Integer.valueOf(textoSemanaCompetitiva);
        } catch (Exception e) {
        }

        //
        int sumaPreparacion = 0;
        int sumaEspecial = 0;
        int sumaPrecompetitivo = 0;
        int sumaCompetitivo = 0;

        //Sumatoria de preparacion
        if (this.preparativaActiva) {
            for (int i = 0; i < modeloTablaPreparacion.getColumnCount(); i++) {

                //Checa que no haya nulos antes de hacer las pasadas por las columnas, si los encuentra, pone 0 tal cual
                if (modeloTablaPreparacion.getValueAt(0, i) == null) {
                    sumaPreparacion = sumaPreparacion + 0;
                } else {
                    try {

                        int valorCelda = Integer.parseInt(modeloTablaPreparacion.getValueAt(0, i).toString());
                        if (valorCelda <= 0) {
                            JOptionPane.showMessageDialog(this, "Error. Se detecto un campo con 0 en la tabla de preparación");
                            return false;
                        }
                        sumaPreparacion = sumaPreparacion + valorCelda;

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Error. Se detectó un campo que no es número entero positivo en la tabla de preparación");
                        return false;
                    }
                }

            }
        }

        //Sumatoria de especial
        if (this.especialActiva) {
            for (int i = 0; i < modeloTablaEspecial.getColumnCount(); i++) {

                if (modeloTablaEspecial.getValueAt(0, i) == null) {
                    sumaEspecial = sumaEspecial + 0;
                } else {
                    try {
                        int valorCelda = Integer.parseInt(modeloTablaEspecial.getValueAt(0, i).toString());

                        if (valorCelda <= 0) {
                            JOptionPane.showMessageDialog(this, "Error. Se detecto un campo con 0 en la tabla de etapa especial");
                            return false;
                        }

                        sumaEspecial = sumaEspecial + valorCelda;
                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Error. Se detectó un campo que no es número entero positivo en la tabla de etapa especial");
                        return false;
                    }
                }
            }
        }

        //Sumatoria de Precompetitivo
        if (this.precompetitivasActiva) {
            for (int i = 0; i < modeloTablaPrecompetitivo.getColumnCount(); i++) {

                if (modeloTablaPrecompetitivo.getValueAt(0, i) == null) {
                    sumaPrecompetitivo = sumaPrecompetitivo + 0;
                } else {
                    try {
                        int valorCelda = Integer.parseInt(modeloTablaPrecompetitivo.getValueAt(0, i).toString());

                        if (valorCelda <= 0) {
                            JOptionPane.showMessageDialog(this, "Error. Se detecto un campo con 0 en la tabla de etapa precompetitiva");
                            return false;
                        }

                        sumaPrecompetitivo = sumaPrecompetitivo + valorCelda;
                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Error. Se detectó un campo que no es número entero positivo en la tabla de etapa precompetitiva");

                    }
                }
            }
        }

        //Sumatoria de Competitivo
        if (this.competitivaActiva) {
            for (int i = 0; i < modeloTablaCompetitivo.getColumnCount(); i++) {

                if (modeloTablaCompetitivo.getValueAt(0, i) == null) {
                    sumaCompetitivo = sumaCompetitivo + 0;
                } else {
                    try {
                        int valorCelda = Integer.parseInt(modeloTablaCompetitivo.getValueAt(0, i).toString());

                        if (valorCelda <= 0) {
                            JOptionPane.showMessageDialog(this, "Error. Se detecto un campo con 0 en la tabla de etapa competitiva");
                            return false;
                        }

                        sumaCompetitivo = sumaCompetitivo + valorCelda;
                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Error. Se detectó un campo que no es número entero positivo en la tabla de etapa competitiva");

                    }
                }
            }
        }

        System.out.println(sumaPreparacion);
        System.out.println(sumaEspecial);
        System.out.println(sumaPrecompetitivo);
        System.out.println(sumaCompetitivo);

        if (sumaPreparacion == etapaPreparacion && sumaEspecial == etapaEspecial && sumaPrecompetitivo == etapaPrecompetitivo && sumaCompetitivo == etapaCompetitivo) {
            JOptionPane.showMessageDialog(this, "La distribución en las etapas es válida");
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Error. La distribución de etapas no coincide con las semanas establecidas");
            return false;
        }
    }

    /**
     * Función que se encarga de validar el formato de ciclicidad ingresada como
     * parámetro de manera que dentro de las celdas de ciclicidad se ingresen
     * datos en el formato "n,n" donde la primera n representa las semanas de
     * trabajo y la segunda n las semanas de descanso
     *
     * @param ciclicidad el valor que se encuentra dentro de la celda de
     * ciclisidad
     * @param semanasMeso el valor de las semanas que se encuentra ingresado en
     * un mesociclo
     * @return
     */
    public boolean validarFormatoCiclicidad(String ciclicidad, String semanasMeso) {
        String regex = "\\d+,\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ciclicidad);

        //si los dos numeros introducicos son numeros enteros y tienen una coma en medio
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Error: Tabla , Introduzca la ciclicidad en el siguiente formato: 5,1 (trabajo/descanso)");
            return false;
        }

        //divide los numeros
        String numeros[] = ciclicidad.split(",");

        //obtiene los dos numeros convirtiendolos 
        int semanastrabajo = Integer.parseInt(numeros[0]);
        int semanasDescanso = Integer.parseInt(numeros[1]);
        //obtiene las semanas totales de la columna
        int semanastotales = Integer.parseInt(semanasMeso);
        //obtiene la suma de los dos numeros de ciclicidad
        int semanassuma = semanastrabajo + semanasDescanso;

        System.out.println(semanastrabajo);
        System.out.println(semanasDescanso);
        System.out.println(semanastotales);
        System.out.println(semanassuma);

        //si las semanas totales de la suma y las semanas totales de la columna no son iguales da un mensaje de error
        if (semanastotales != semanassuma) {
            JOptionPane.showMessageDialog(this, "Error: Tabla , las semanas no corresponden (trabajo/descanso)");
            return false;
        }

        //si las semanas de trabajo son menores o iguales a las de descanso muestra un mensaje de error
        if (semanastrabajo <= semanasDescanso) {
            JOptionPane.showMessageDialog(this, "Error: Tabla , semanas de trabajo son menores a las de descanso (trabajo/descanso)");
            return false;
        }

        return true;

    }

    /**
     * Método para validar que las semanas ingresadas dentro del mesociclo sean
     * números enteros
     *
     * @param semanas la cadena de texto a la que se le comprobará si contiene
     * solo un número entero
     * @return true si la cadena para la validación, false en caso contrario
     */
    public boolean validarSemanasMeso(String semanas) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(semanas);

        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Error: Tabla , El numero de semanas no es un numero entero");
            return false;
        }

        return true;
    }

    /**
     * Método para validar los campos de ciclicidad ingresados en sus
     * respectivas celdas, para cada una de las posibles 4 tablas a las que se
     * les pueden ingresar valores se pasan una serie de validaciones para
     * corroborar que las ciclicidades cumplan con su formato específico y su
     * distribución cumpla con las reglas.
     *
     * @return true si se pasan todas las validaciones de las 4 tablas, false en
     * caso contrario
     */
    public boolean validarCiclicidad() {
        DefaultTableModel modeloTablaPreparacion = (DefaultTableModel) this.tblEtapaPreparacion.getModel();
        DefaultTableModel modeloTablaEspecial = (DefaultTableModel) this.tblEtapaEspecial.getModel();
        DefaultTableModel modeloTablaPrecompetitivo = (DefaultTableModel) this.tblEtapaPrecompetitivo.getModel();
        DefaultTableModel modeloTablaCompetitivo = (DefaultTableModel) this.tblEtapaCompetitivo.getModel();

        ArrayList<String> ciclicidadPreparacion = new ArrayList<>();
        ArrayList<String> semanasPreparacion = new ArrayList<>();

        ArrayList<String> ciclicidadEspecial = new ArrayList<>();
        ArrayList<String> semanasEspecial = new ArrayList<>();

        ArrayList<String> ciclicidadPrecompetitiva = new ArrayList<>();
        ArrayList<String> semanasPrecompetitiva = new ArrayList<>();

        ArrayList<String> ciclicidadCompetitiva = new ArrayList<>();
        ArrayList<String> semanasCompetitiva = new ArrayList<>();

        if (this.preparativaActiva) {

            for (int i = 0; i < modeloTablaPreparacion.getColumnCount(); i++) {
                //Checa que no haya nulos antes de hacer las pasadas por las columnas, si los encuentra, muestra mensaje de error al usuario
                String ciclicidad = "";
                String semanasMeso = "";

                try {
                    ciclicidad = modeloTablaPreparacion.getValueAt(1, i).toString();
                    semanasMeso = modeloTablaPreparacion.getValueAt(0, i).toString();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: Tabla , se dejo vacío semanas microciclo o ciclicidad en tabla preparación");
                    return false;
                }

                //valida que las semanas del microciclo sean un numero entero
                if (!this.validarSemanasMeso(semanasMeso)) {
                    return false;
                }

                //convierte el string obtenido a int
                int semanasEntero = Integer.parseInt(semanasMeso);

                if (semanasEntero == 1 || semanasEntero == 2) {
                    //si la semana es 1 o 0 asigna automaticamente el valor de la ciclicidad a 1,0 o 2,0 dependiento el caso
                    ciclicidad = semanasMeso + ",0";

                    ciclicidadPreparacion.add(ciclicidad);
                    semanasPreparacion.add(semanasMeso);

                } else {
                    //si es cualquier otro valor valida si tienen el formato correcto
                    if (!validarFormatoCiclicidad(ciclicidad, semanasMeso)) {
                        return false;
                    }

                    ciclicidadPreparacion.add(ciclicidad);
                    semanasPreparacion.add(semanasMeso);
                }
            }

            //this.distribucionPreparativa = semanasPreparacion;
        }

        if (this.especialActiva) {

            for (int i = 0; i < modeloTablaEspecial.getColumnCount(); i++) {
                //Checa que no haya nulos antes de hacer las pasadas por las columnas, si los encuentra, pone 0 tal cual
                String ciclicidad = "";
                String semanasMeso = "";

                try {
                    ciclicidad = modeloTablaEspecial.getValueAt(1, i).toString();
                    semanasMeso = modeloTablaEspecial.getValueAt(0, i).toString();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: Tabla , se dejo vacío semanas microciclo o ciclicidad en tabla especial");
                    return false;
                }

                if (!this.validarSemanasMeso(semanasMeso)) {
                    return false;
                }

                int semanasEntero = Integer.parseInt(semanasMeso);

                if (semanasEntero == 1 || semanasEntero == 2) {

                    ciclicidad = semanasMeso + ",0";

                    ciclicidadEspecial.add(ciclicidad);
                    semanasEspecial.add(semanasMeso);

                } else {
                    if (!validarFormatoCiclicidad(ciclicidad, semanasMeso)) {
                        return false;
                    }

                    ciclicidadEspecial.add(ciclicidad);
                    semanasEspecial.add(semanasMeso);
                }
            }
        }

        if (this.precompetitivasActiva) {
            for (int i = 0; i < modeloTablaPrecompetitivo.getColumnCount(); i++) {
                //Checa que no haya nulos antes de hacer las pasadas por las columnas, si los encuentra, pone 0 tal cual
                String ciclicidad = "";
                String semanasMeso = "";

                try {
                    ciclicidad = modeloTablaPrecompetitivo.getValueAt(1, i).toString();
                    semanasMeso = modeloTablaPrecompetitivo.getValueAt(0, i).toString();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: Tabla , se dejo vacío semanas microciclo o ciclicidad en tabla precompetitiva");
                    return false;
                }

                if (!this.validarSemanasMeso(semanasMeso)) {
                    return false;
                }

                int semanasEntero = Integer.parseInt(semanasMeso);

                if (semanasEntero == 1 || semanasEntero == 2) {

                    ciclicidad = semanasMeso + ",0";

                    ciclicidadPrecompetitiva.add(ciclicidad);
                    semanasPrecompetitiva.add(semanasMeso);

                } else {
                    if (!validarFormatoCiclicidad(ciclicidad, semanasMeso)) {
                        return false;
                    }

                    ciclicidadPrecompetitiva.add(ciclicidad);
                    semanasPrecompetitiva.add(semanasMeso);
                }
            }
        }

        if (this.competitivaActiva) {

            for (int i = 0; i < modeloTablaCompetitivo.getColumnCount(); i++) {
                //Checa que no haya nulos antes de hacer las pasadas por las columnas, si los encuentra, pone 0 tal cual
                String ciclicidad = "";
                String semanasMeso = "";

                try {
                    ciclicidad = modeloTablaCompetitivo.getValueAt(1, i).toString();
                    semanasMeso = modeloTablaCompetitivo.getValueAt(0, i).toString();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error: Tabla , se dejo vacío semanas microciclo o ciclicidad en tabla competitiva");
                    return false;
                }

                if (!this.validarSemanasMeso(semanasMeso)) {
                    return false;
                }

                int semanasEntero = Integer.parseInt(semanasMeso);

                if (semanasEntero == 1 || semanasEntero == 2) {

                    ciclicidad = semanasMeso + ",0";

                    ciclicidadCompetitiva.add(ciclicidad);
                    semanasCompetitiva.add(semanasMeso);

                } else {
                    if (!validarFormatoCiclicidad(ciclicidad, semanasMeso)) {
                        return false;
                    }

                    ciclicidadCompetitiva.add(ciclicidad);
                    semanasCompetitiva.add(semanasMeso);
                }
            }
        }

        this.distribucionPreparativa = semanasPreparacion;
        this.distribucionEspecial = semanasEspecial;
        this.distribucionPrecom = semanasPrecompetitiva;
        this.distribucionCompetitiva = semanasCompetitiva;

        this.ciclicidadPreparativa = ciclicidadPreparacion;
        this.ciclicidadEspecial = ciclicidadEspecial;
        this.ciclicidadPrecom = ciclicidadPrecompetitiva;
        this.ciclicidadCompetitiva = ciclicidadCompetitiva;

        JOptionPane.showMessageDialog(this, "La distribución de ciclicidad es válida");
        return true;
    }

    /**
     * Método para bloquear los botones de validación de distribucion de etapas
     * y ciclicidad al inicio de la ventana
     */
    public void bloqueoBtnValidacionTablas() {
        //this.btnValidarCiclicidad.setEnabled(false);
        this.jbtnValidarMesociclos.setEnabled(false);
    }

    /**
     * Método para desbloquear el botón de distribucion de etapas
     */
    public void desbloqueoBtnValidacionTablas() {
        //this.btnValidarCiclicidad.setEnabled(true);
        this.jbtnValidarMesociclos.setEnabled(true);
    }

    public void crearMacrociclo() {
        this.macrociclo = new Macrociclo();
        macrociclo.setFechaInicio(crearMacro.inicioPlan.toString());
        macrociclo.setFechaFin(crearMacro.finPlan.toString());
        macrociclo.setTotalSemanas(semanasTotales);
        macrociclo.setDeporte(this.cbDeporte.getSelectedItem().toString());
        macrociclo.setRama(this.cbRama.getSelectedItem().toString());
        macrociclo.setJefeRama(this.txtJefeRama.getText().toString());
        macrociclo.setPreparadorFis(this.txtEntAux.getText().toString());
        macrociclo.setMetodologo(this.txtMetodologo.getText().toString());
        macrociclo.setPeriodoPreparativo(Integer.valueOf(this.jtxtSemanasPreparativo.getText().toString()));
        macrociclo.setPeriodoCompetitivo(Integer.valueOf(this.jtxtSemanasCompetitivo.getText().toString()));
        macrociclo.setEtapaPreparativa(Integer.valueOf(this.jtxtSemanasPreparacion.getText().toString()));
        macrociclo.setEtapaEspecial(Integer.valueOf(this.jtxtSemanasEspecial.getText().toString()));
        macrociclo.setEtapaPrecompetitiva(Integer.valueOf(this.jtxtSemanasPrecompetitivo.getText().toString()));
        macrociclo.setEtapaCompetitiva(Integer.valueOf(this.jtxtSemanasEtapaCompetitivo.getText().toString()));

        if (this.preparativaActiva) {
            macrociclo.setDistribucionPreparativa(distribucionPreparativa);
            macrociclo.setCiclicidadPreparativa(ciclicidadPreparativa);
        }

        if (this.especialActiva) {
            macrociclo.setDistribucionEspecial(distribucionEspecial);
            macrociclo.setCiclicidadEspecial(ciclicidadEspecial);
        }

        if (this.precompetitivasActiva) {
            macrociclo.setDistribucionPrecom(distribucionPrecom);
            macrociclo.setCiclicidadPrecom(ciclicidadPrecom);
        }

        if (this.competitivaActiva) {
            macrociclo.setDistribucionCompetitiva(distribucionCompetitiva);
            macrociclo.setCiclicidadCompetitiva(ciclicidadCompetitiva);
        }

        macrocicloNegocio negocio = new macrocicloNegocio();
        Object id = negocio.guardarMacrociclo(macrociclo);
        if (!id.equals(null)) {
            
            JOptionPane.showMessageDialog(this, "Macrociclo se ha guardado con exito");
            VolumenFrm volumen = 
                    new VolumenFrm(this.jtxtSemanasPreparacion.toString(),this.jtxtSemanasEspecial.toString(),this.jtxtSemanasPrecompetitivo.toString(),this.jtxtSemanasEtapaCompetitivo.toString(),id.toString());
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "Error: Hubo un error al guardar el Macrociclo ");
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

        jpMacrociclo = new javax.swing.JPanel();
        jspEtapaPreparacion = new javax.swing.JScrollPane();
        tblEtapaPreparacion = new javax.swing.JTable();
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
        txtJefeRama = new javax.swing.JTextField();
        txtEntAux = new javax.swing.JTextField();
        txtMetodologo = new javax.swing.JTextField();
        btnCalcularEtapas = new javax.swing.JButton();
        btnValidarSemanas = new javax.swing.JButton();
        jspEtapaPrecompetitivo = new javax.swing.JScrollPane();
        tblEtapaPrecompetitivo = new javax.swing.JTable();
        jLabelDistribucion1 = new javax.swing.JLabel();
        jLabelMeso1 = new javax.swing.JLabel();
        jLabelMicro1 = new javax.swing.JLabel();
        jLabelCicli1 = new javax.swing.JLabel();
        jspEtapaEspecial = new javax.swing.JScrollPane();
        tblEtapaEspecial = new javax.swing.JTable();
        jLabelDistribucion2 = new javax.swing.JLabel();
        jLabelMeso2 = new javax.swing.JLabel();
        jLabelMicro2 = new javax.swing.JLabel();
        jLabelCicli2 = new javax.swing.JLabel();
        jLabelDistribucion3 = new javax.swing.JLabel();
        jspEtapaCompetitivo = new javax.swing.JScrollPane();
        tblEtapaCompetitivo = new javax.swing.JTable();
        jLabelMeso3 = new javax.swing.JLabel();
        jLabelMicro3 = new javax.swing.JLabel();
        jLabelCicli3 = new javax.swing.JLabel();
        jbtnEditarMacrociclo = new javax.swing.JButton();
        jbtnEditarEtapas = new javax.swing.JButton();
        jbtnAgregarPreparacion = new javax.swing.JButton();
        jbtnEliminarPreparacion = new javax.swing.JButton();
        jbtnEliminarEspecial = new javax.swing.JButton();
        jbtnAgregarEspecial = new javax.swing.JButton();
        jbtnEliminarCompetitivo = new javax.swing.JButton();
        jbtnAgregarCompetitiva = new javax.swing.JButton();
        jbtnAgregarPrecompetitiva = new javax.swing.JButton();
        jbtnEliminarPrecompetitivo = new javax.swing.JButton();
        jbtnValidarMesociclos = new javax.swing.JButton();
        cbRama = new javax.swing.JComboBox<>();
        cbDeporte = new javax.swing.JComboBox<>();
        jlbSemanasPrep = new javax.swing.JLabel();
        jlbSemanasEsp = new javax.swing.JLabel();
        jlbSemanasPrecom = new javax.swing.JLabel();
        jlbSemanasComp = new javax.swing.JLabel();
        txtAgregarMicrocicloPreparacion = new javax.swing.JTextField();
        txtAgregarCiclicidadPreparacion = new javax.swing.JTextField();
        txtAgregarMicrocicloEspecial = new javax.swing.JTextField();
        txtAgregarCiclicidadEspecial = new javax.swing.JTextField();
        txtAgregarCiclicidadPrecompetitiva = new javax.swing.JTextField();
        txtAgregarMicrocicloPrecompetitiva = new javax.swing.JTextField();
        txtAgregarCiclicidadCompetitiva = new javax.swing.JTextField();
        txtAgregarMicrocicloCompetitiva = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de macrociclo");
        setResizable(false);

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

        jLabelMicro.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMicro.setText("Microciclo");

        jLabelCicli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelCicli.setText("Ciclicidad");

        jLabelMeso.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMeso.setText("Mesociclos");

        jtxtSemanas.setEditable(false);
        jtxtSemanas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jtxtSemanas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jlbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salir.png"))); // NOI18N
        jlbSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbSalirMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Total de semanas del macrociclo:");

        jLabelDistribucion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDistribucion.setText("Distribución por etapa de preparación");

        jpPeriodos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Periodo competitivo");

        jLabel8.setText("Periodo preparativo ");

        jtxtPorcentajePreparativo.setEnabled(false);

        jtxtSemanasPreparativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtSemanasPreparativoActionPerformed(evt);
            }
        });

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
                                .addGap(18, 18, 18)
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

        jLabelDistribucion1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDistribucion1.setText("Distribución por etapa precompetitiva");

        jLabelMeso1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMeso1.setText("Mesociclos");

        jLabelMicro1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMicro1.setText("Microciclo");

        jLabelCicli1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelCicli1.setText("Ciclicidad");

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

        jLabelDistribucion2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDistribucion2.setText("Distribución por etapa especial");

        jLabelMeso2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMeso2.setText("Mesociclos");

        jLabelMicro2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMicro2.setText("Microciclo");

        jLabelCicli2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelCicli2.setText("Ciclicidad");

        jLabelDistribucion3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDistribucion3.setText("Distribución por etapa competitiva");

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

        jbtnEditarMacrociclo.setBackground(new java.awt.Color(153, 204, 255));
        jbtnEditarMacrociclo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtnEditarMacrociclo.setText("Editar macrociclo");
        jbtnEditarMacrociclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarMacrocicloActionPerformed(evt);
            }
        });

        jbtnEditarEtapas.setText("Editar Etapas");
        jbtnEditarEtapas.setEnabled(false);
        jbtnEditarEtapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarEtapasActionPerformed(evt);
            }
        });

        jbtnAgregarPreparacion.setText("Agregar");
        jbtnAgregarPreparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregarPreparacionActionPerformed(evt);
            }
        });

        jbtnEliminarPreparacion.setText("Borrar uno");
        jbtnEliminarPreparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarPreparacionActionPerformed(evt);
            }
        });

        jbtnEliminarEspecial.setText("Borrar uno");
        jbtnEliminarEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarEspecialActionPerformed(evt);
            }
        });

        jbtnAgregarEspecial.setText("Agregar");
        jbtnAgregarEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregarEspecialActionPerformed(evt);
            }
        });

        jbtnEliminarCompetitivo.setText("Borrar uno");
        jbtnEliminarCompetitivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarCompetitivoActionPerformed(evt);
            }
        });

        jbtnAgregarCompetitiva.setText("Agregar");
        jbtnAgregarCompetitiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregarCompetitivaActionPerformed(evt);
            }
        });

        jbtnAgregarPrecompetitiva.setText("Agregar");
        jbtnAgregarPrecompetitiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregarPrecompetitivaActionPerformed(evt);
            }
        });

        jbtnEliminarPrecompetitivo.setText("Borrar uno");
        jbtnEliminarPrecompetitivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarPrecompetitivoActionPerformed(evt);
            }
        });

        jbtnValidarMesociclos.setText("Validar mesociclos");
        jbtnValidarMesociclos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnValidarMesociclosActionPerformed(evt);
            }
        });

        cbRama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Varonil", "Femenil" }));
        cbRama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRamaActionPerformed(evt);
            }
        });

        cbDeporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Atletismo", "Basquetbol", "Beisbol", "Futbol asociación", "Futbol bardas", "Gimnasia aeróbica", "Halterofilia", "Handball", "Judo", "Karate do", "Tae kwon do", "Tenis", "Tenis de mesa", "Tiro con arco", "Triatlón", "Voleibol playa", "Voleibol sala" }));

        jlbSemanasPrep.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbSemanasPrep.setText("n semanas");

        jlbSemanasEsp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbSemanasEsp.setText("n semanas");

        jlbSemanasPrecom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbSemanasPrecom.setText("n semanas");

        jlbSemanasComp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbSemanasComp.setText("n semanas");

        txtAgregarMicrocicloPreparacion.setText("0");

        txtAgregarCiclicidadPreparacion.setText("0,0");

        txtAgregarMicrocicloEspecial.setText("0");

        txtAgregarCiclicidadEspecial.setText("0,0");

        txtAgregarCiclicidadPrecompetitiva.setText("0,0");

        txtAgregarMicrocicloPrecompetitiva.setText("0");

        txtAgregarCiclicidadCompetitiva.setText("0,0");

        txtAgregarMicrocicloCompetitiva.setText("0");

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
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addComponent(jbtnEditarEtapas)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnCalcularEtapas))
                                            .addComponent(jpPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabelMeso1)
                                                    .addComponent(jLabelMicro1)
                                                    .addComponent(jLabelCicli1))
                                                .addGap(18, 18, 18)
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                        .addComponent(jLabelDistribucion1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jlbSemanasPrecom)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtAgregarMicrocicloPrecompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtAgregarCiclicidadPrecompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jspEtapaPrecompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                        .addComponent(jpPeriodoPreparativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(30, 30, 30)
                                                        .addComponent(jpPeriodoCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(btnValidarSemanas)))))
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelMeso3)
                                            .addComponent(jLabelCicli3)
                                            .addComponent(jLabelMicro3))
                                        .addGap(18, 18, 18)
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addComponent(jLabelDistribucion3)
                                                .addGap(14, 14, 14)
                                                .addComponent(jlbSemanasComp)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtAgregarMicrocicloCompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtAgregarCiclicidadCompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jspEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbtnEliminarPrecompetitivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnAgregarPrecompetitiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnEliminarCompetitivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnAgregarCompetitiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(194, 194, 194))
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel25)
                                                    .addComponent(jLabel26)
                                                    .addComponent(jLabel27)
                                                    .addComponent(jLabel29)
                                                    .addComponent(jLabel28))
                                                .addGap(18, 18, 18)
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtMetodologo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtJefeRama, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtEntAux, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(cbDeporte, javax.swing.GroupLayout.Alignment.LEADING, 0, 143, Short.MAX_VALUE)
                                                        .addComponent(cbRama, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                        .addGap(7, 7, 7)
                                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabelCicli)
                                                            .addComponent(jLabelCicli2))
                                                        .addGap(7, 7, 7))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                                                        .addComponent(jLabelMicro2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                                                        .addComponent(jLabelMicro)
                                                        .addGap(5, 5, 5))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                                                        .addComponent(jLabelMeso)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jbtnValidarMesociclos)
                                                    .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jspEtapaEspecial)
                                                        .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                            .addComponent(jLabelDistribucion2)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jlbSemanasEsp)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(txtAgregarMicrocicloEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(txtAgregarCiclicidadEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jspEtapaPreparacion, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                            .addComponent(jLabelDistribucion)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jlbSemanasPrep)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(txtAgregarMicrocicloPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(txtAgregarCiclicidadPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addComponent(jLabelMeso2))
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel23))
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jbtnEliminarPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jbtnAgregarEspecial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jbtnAgregarPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jbtnEliminarEspecial, javax.swing.GroupLayout.Alignment.LEADING)))))
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtSemanaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addComponent(jtxtSemanaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtxtSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(267, 267, 267)
                                .addComponent(jlbSalir))
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addComponent(jbtnEditarMacrociclo)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnCrearMacrociclo)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jpMacrocicloLayout.setVerticalGroup(
            jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbSalir)
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jtxtSemanaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jtxtSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jtxtSemanaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(cbDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(cbRama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJefeRama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(14, 14, 14)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEntAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(16, 16, 16)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMetodologo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcularEtapas)
                    .addComponent(btnValidarSemanas)
                    .addComponent(jbtnEditarEtapas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpPeriodoCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jpPeriodos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jpPeriodoPreparativo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDistribucion)
                            .addComponent(jlbSemanasPrep)
                            .addComponent(txtAgregarMicrocicloPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAgregarCiclicidadPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabelMeso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelMicro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCicli))
                            .addComponent(jspEtapaPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDistribucion1)
                            .addComponent(txtAgregarMicrocicloPrecompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAgregarCiclicidadPrecompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbSemanasPrecom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspEtapaPrecompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabelMeso1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelMicro1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCicli1))
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addComponent(jbtnAgregarPreparacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnEliminarPreparacion))
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addComponent(jbtnAgregarPrecompetitiva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnEliminarPrecompetitivo)))))
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelDistribucion3)
                                    .addComponent(jlbSemanasComp)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMacrocicloLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAgregarMicrocicloCompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAgregarCiclicidadCompetitiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabelMeso3)
                                .addGap(10, 10, 10)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelMicro3)
                                    .addComponent(jbtnEliminarEspecial))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelCicli3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addComponent(jbtnAgregarCompetitiva)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtnEliminarCompetitivo)
                                        .addGap(42, 42, 42))
                                    .addComponent(jspEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDistribucion2)
                            .addComponent(jlbSemanasEsp)
                            .addComponent(txtAgregarMicrocicloEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAgregarCiclicidadEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspEtapaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabelMeso2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelMicro2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelCicli2))
                            .addComponent(jbtnAgregarEspecial))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(4, 4, 4)
                .addComponent(jbtnValidarMesociclos)
                .addGap(3, 3, 3)
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCrearMacrociclo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEditarMacrociclo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMacrociclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMacrociclo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnValidarMesociclosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnValidarMesociclosActionPerformed
        boolean distribucionValida = validarDistribucionEtapas();
        boolean ciclicidadValida = validarCiclicidad();

        if (distribucionValida && ciclicidadValida) {
            this.jbtnCrearMacrociclo.setEnabled(true);
            
            this.txtAgregarCiclicidadCompetitiva.setEnabled(false);
            this.txtAgregarCiclicidadPrecompetitiva.setEnabled(false);
            this.txtAgregarCiclicidadEspecial.setEnabled(false);
            this.txtAgregarCiclicidadPreparacion.setEnabled(false);
            
            this.txtAgregarMicrocicloCompetitiva.setEnabled(false);
            this.txtAgregarMicrocicloEspecial.setEnabled(false);
            this.txtAgregarMicrocicloPrecompetitiva.setEnabled(false);
            this.txtAgregarMicrocicloPreparacion.setEnabled(false);

            this.jLabel21.setVisible(true);
            this.jLabel22.setVisible(true);
        }
    }//GEN-LAST:event_jbtnValidarMesociclosActionPerformed

    private void jbtnEliminarPrecompetitivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarPrecompetitivoActionPerformed
        eliminarMesociclo(this.tblEtapaPrecompetitivo, 3);
    }//GEN-LAST:event_jbtnEliminarPrecompetitivoActionPerformed

    private void jbtnAgregarPrecompetitivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarPrecompetitivaActionPerformed
        String ciclicidad = this.txtAgregarCiclicidadPrecompetitiva.getText();
        String microciclo = this.txtAgregarMicrocicloPrecompetitiva.getText();
        if (ciclicidad.equals("") || microciclo.equals("")) {

        } else {
            agregarMesociclo(this.tblEtapaPrecompetitivo, 3, microciclo, ciclicidad);
            this.txtAgregarMicrocicloPrecompetitiva.setText("0");
            this.txtAgregarCiclicidadPrecompetitiva.setText("0,0");
        }


    }//GEN-LAST:event_jbtnAgregarPrecompetitivaActionPerformed

    private void jbtnAgregarCompetitivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarCompetitivaActionPerformed
        String ciclicidad = this.txtAgregarCiclicidadCompetitiva.getText();
        String microciclo = this.txtAgregarMicrocicloCompetitiva.getText();
        if ((ciclicidad.equals("") || microciclo.equals("")) || (ciclicidad.equals("0") || microciclo.equals("0,0"))) {

        } else {
            agregarMesociclo(this.tblEtapaCompetitivo, 4, microciclo, ciclicidad);
            this.txtAgregarMicrocicloCompetitiva.setText("0");
            this.txtAgregarCiclicidadCompetitiva.setText("0,0");
        }

    }//GEN-LAST:event_jbtnAgregarCompetitivaActionPerformed

    private void jbtnEliminarCompetitivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarCompetitivoActionPerformed
        eliminarMesociclo(this.tblEtapaCompetitivo, 4);
    }//GEN-LAST:event_jbtnEliminarCompetitivoActionPerformed

    private void jbtnAgregarEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarEspecialActionPerformed
        String ciclicidad = this.txtAgregarCiclicidadEspecial.getText();
        String microciclo = this.txtAgregarMicrocicloEspecial.getText();
        if ((ciclicidad.equals("") || microciclo.equals("")) || (ciclicidad.equals("0") || microciclo.equals("0,0"))) {

        } else {
            agregarMesociclo(this.tblEtapaEspecial, 2, microciclo, ciclicidad);
            this.txtAgregarMicrocicloEspecial.setText("0");
            this.txtAgregarCiclicidadEspecial.setText("0,0");

        }
    }//GEN-LAST:event_jbtnAgregarEspecialActionPerformed

    private void jbtnEliminarEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarEspecialActionPerformed
        eliminarMesociclo(this.tblEtapaEspecial, 2);
    }//GEN-LAST:event_jbtnEliminarEspecialActionPerformed

    private void jbtnEliminarPreparacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarPreparacionActionPerformed
        eliminarMesociclo(this.tblEtapaPreparacion, 1);
    }//GEN-LAST:event_jbtnEliminarPreparacionActionPerformed

    private void jbtnAgregarPreparacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarPreparacionActionPerformed
        String ciclicidad = this.txtAgregarCiclicidadPreparacion.getText();
        String microciclo = this.txtAgregarMicrocicloPreparacion.getText();
        if ((ciclicidad.equals("") || microciclo.equals(""))) {

        } else if ((ciclicidad.equals("0") || microciclo.equals("0,0"))) {

        } else {
            if (microciclo != null && ciclicidad != null) {
                agregarMesociclo(this.tblEtapaPreparacion, 1, microciclo, ciclicidad);
                this.txtAgregarMicrocicloPreparacion.setText("0");
                this.txtAgregarCiclicidadPreparacion.setText("0,0");
            }

        }
    }//GEN-LAST:event_jbtnAgregarPreparacionActionPerformed

    private void jbtnEditarEtapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarEtapasActionPerformed
        //Activa el boton para calcular de las etapas nuevamente
        this.btnCalcularEtapas.setEnabled(true);

        //Resetea los campos de las etapas
        this.jtxtSemanasPreparacion.setText("");
        this.jtxtSemanasEspecial.setText("");
        this.jtxtSemanasEtapaCompetitivo.setText("");
        this.jtxtSemanasPrecompetitivo.setText("");

        this.deshabilitarEtapas();

        //Activa los campos a editar
        this.jtxtSemanasPreparativo.setEnabled(true);
        this.jtxtSemanasCompetitivo.setEnabled(true);

        //Se desactiva a si mismo
        this.jbtnEditarEtapas.setEnabled(false);
    }//GEN-LAST:event_jbtnEditarEtapasActionPerformed

    private void jbtnEditarMacrocicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarMacrocicloActionPerformed
        //activa los todos los campos
        this.activarCamposEdicion();
        this.jtxtSemanasPreparativo.setEnabled(false);
        this.jtxtSemanasCompetitivo.setEnabled(false);
        this.cargarSemanasCompetitiva(0);
        this.cargarSemanasEspecial(0);
        this.cargarSemanasPrecompetitiva(0);
        this.cargarSemanasPreparacion(0);
        this.ocultarTablaMeso();
    }//GEN-LAST:event_jbtnEditarMacrocicloActionPerformed

    private void btnValidarSemanasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarSemanasActionPerformed
        // TODO add your handling code here:
        boolean esValido;

        String preparativa = this.jtxtSemanasPreparacion.getText();
        String especial = this.jtxtSemanasEspecial.getText();
        String precompetitiva = this.jtxtSemanasPrecompetitivo.getText();
        String competitiva = this.jtxtSemanasEtapaCompetitivo.getText();

        if (preparativa.equals("")) {
            this.jtxtSemanasPreparacion.setText("0");
        }

        if (especial.equals("")) {
            this.jtxtSemanasEspecial.setText("0");
        }

        if (precompetitiva.equals("")) {
            this.jtxtSemanasPrecompetitivo.setText("0");
        }

        if (competitiva.equals("")) {
            this.jtxtSemanasEtapaCompetitivo.setText("0");
        }

        try {
            //valida las etapas
            esValido = this.validarEtapas();
            if (esValido) {
                this.btnValidarSemanas.setEnabled(false);
                this.jbtnEditarEtapas.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            //muestra mensaje de error en caso de que se introduce un numero no entero
            JOptionPane.showMessageDialog(this, "Error. No se introdujo un numero entero en los periodos");
            this.activarCamposEdicion();
            this.jtxtSemanasPreparativo.setEnabled(false);
            this.jtxtSemanasCompetitivo.setEnabled(false);

            this.btnValidarSemanas.setEnabled(true);
            this.jbtnEditarEtapas.setEnabled(true);
            resetearPeriodos();
        }
    }//GEN-LAST:event_btnValidarSemanasActionPerformed

    private void btnCalcularEtapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularEtapasActionPerformed
        // TODO add your handling code here:
        try {
            //valida periodos y calcula el porcentaje
            this.validarPeriodos();
            this.calcularPorcentajePeriodos();
        } catch (NumberFormatException ex) {
            //muestra mensaje de error encaso de que un numero no sea entero
            JOptionPane.showMessageDialog(this, "Error. No se introdujo un numero entero en los periodos");
            this.resetearCamposSemanas();
        }
    }//GEN-LAST:event_btnCalcularEtapasActionPerformed

    private void txtEntAuxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntAuxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntAuxActionPerformed

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
            //this.dispose();
            this.crearMacrociclo();
            
        }
    }//GEN-LAST:event_jbtnCrearMacrocicloActionPerformed

    private void cbRamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRamaActionPerformed

    private void jlbSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbSalirMouseClicked
        //opciones para salir de la pantalla actual
        String[] opciones = {"Aceptar", "Cancelar"};

        //se muestra mensaje de confirmacion
        int opcion = JOptionPane.showOptionDialog(this, "¿Quieres regresar al menu principal?", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                opciones, opciones[0]);

        if (opcion == 0) {
            //si elige aceptar regresa a la ventana anterior
            inicioFrm inicio = new inicioFrm();
            this.dispose();
        } else if (opcion == 1) {
            //cancela
        }
    }//GEN-LAST:event_jlbSalirMouseClicked

    private void jtxtSemanasPreparativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtSemanasPreparativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtSemanasPreparativoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcularEtapas;
    private javax.swing.JButton btnValidarSemanas;
    private javax.swing.JComboBox<String> cbDeporte;
    private javax.swing.JComboBox<String> cbRama;
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
    private javax.swing.JButton jbtnAgregarCompetitiva;
    private javax.swing.JButton jbtnAgregarEspecial;
    private javax.swing.JButton jbtnAgregarPrecompetitiva;
    private javax.swing.JButton jbtnAgregarPreparacion;
    private javax.swing.JButton jbtnCrearMacrociclo;
    private javax.swing.JButton jbtnEditarEtapas;
    private javax.swing.JButton jbtnEditarMacrociclo;
    private javax.swing.JButton jbtnEliminarCompetitivo;
    private javax.swing.JButton jbtnEliminarEspecial;
    private javax.swing.JButton jbtnEliminarPrecompetitivo;
    private javax.swing.JButton jbtnEliminarPreparacion;
    private javax.swing.JButton jbtnValidarMesociclos;
    private javax.swing.JLabel jlbSalir;
    private javax.swing.JLabel jlbSemanasComp;
    private javax.swing.JLabel jlbSemanasEsp;
    private javax.swing.JLabel jlbSemanasPrecom;
    private javax.swing.JLabel jlbSemanasPrep;
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
    private javax.swing.JTextField txtAgregarCiclicidadCompetitiva;
    private javax.swing.JTextField txtAgregarCiclicidadEspecial;
    private javax.swing.JTextField txtAgregarCiclicidadPrecompetitiva;
    private javax.swing.JTextField txtAgregarCiclicidadPreparacion;
    private javax.swing.JTextField txtAgregarMicrocicloCompetitiva;
    private javax.swing.JTextField txtAgregarMicrocicloEspecial;
    private javax.swing.JTextField txtAgregarMicrocicloPrecompetitiva;
    private javax.swing.JTextField txtAgregarMicrocicloPreparacion;
    private javax.swing.JTextField txtEntAux;
    private javax.swing.JTextField txtJefeRama;
    private javax.swing.JTextField txtMetodologo;
    // End of variables declaration//GEN-END:variables
}
