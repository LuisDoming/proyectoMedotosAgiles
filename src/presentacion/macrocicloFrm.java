/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
    private int semanasTotales;
    private boolean precompetitivasActiva = false;

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
            this.tblEtapaPreparacion.setEnabled(true);
        }

        if (etapaEspecial > 0) {
            this.jspEtapaEspecial.setEnabled(true);
            this.tblEtapaEspecial.setEnabled(true);
        }

        if (etapaPrecompetitivo > 0) {
            this.jspEtapaPrecompetitivo.setEnabled(true);
            this.tblEtapaPrecompetitivo.setEnabled(true);

        }

        if (etapaCompetitivo > 0) {
            this.jspEtapaCompetitivo.setEnabled(true);
            this.tblEtapaCompetitivo.setEnabled(true);
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
            this.precompetitivasActiva = true;
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
        String txtDeporte = this.txtDeporte.getText();
        String txtEntAux = this.txtEntAux.getText();
        String txtMetodologo = this.txtMetodologo.getText();
        String txtRama = this.txtRama.getText();
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

    //activa todos los campos para editar informacion 
    public void activarCamposEdicion() {

        //habilita todos los campos
        this.txtDeporte.setEnabled(true);
        this.txtEntAux.setEnabled(true);
        this.txtJefeRama.setEnabled(true);
        this.txtMetodologo.setEnabled(true);
        this.txtRama.setEnabled(true);

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

    //desactiva todos los campos
    public void desactivarCamposEdicion() {

        //desactiva todos los campos
        this.txtDeporte.setEnabled(false);
        this.txtEntAux.setEnabled(false);
        this.txtJefeRama.setEnabled(false);
        this.txtMetodologo.setEnabled(false);
        this.txtRama.setEnabled(false);

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
        this.jLabel21.setVisible(true);
        this.jLabel22.setVisible(true);
        



    }

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

        } else {
            ///Si se mandó un 0 o negativo pues no se cambia la tabla
        }

    }

    public void cargarSemanasEspecial(int numSemanas) {

        if (numSemanas > 0) {
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

        } else {
            ///Si se mandó un 0 o negativo pues no se cambia la tabla

        }

    }

    public void cargarSemanasPrecompetitiva(int numSemanas) {

        if (numSemanas > 0) {
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

        } else {
            ///Si se mandó un 0 o negativo pues no se cambia la tabla
        }

    }

    public void cargarSemanasCompetitiva(int numSemanas) {

        if (numSemanas > 0) {
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

        } else {
            ///Si se mandó un 0 o negativo pues no se cambia la tabla
        }

    }

    public void resetearCamposSemanas() {

        this.jtxtSemanasPreparativo.setText("");
        this.jtxtSemanasCompetitivo.setText("");
        this.jtxtSemanasPreparacion.setText("");
        this.jtxtSemanasEspecial.setText("");
        this.jtxtSemanasEtapaCompetitivo.setText("");
        this.jtxtSemanasPrecompetitivo.setText("");

    }

    public void resetearPeriodos() {
        this.jtxtSemanasPreparacion.setText("");
        this.jtxtSemanasEspecial.setText("");
        this.jtxtSemanasEtapaCompetitivo.setText("");
        this.jtxtSemanasPrecompetitivo.setText("");
    }

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

    //para agregar una columna
    public void agregarMesociclo(JTable tabla, int numTabla) {
        //NUM 1, TABLA PREPARACION -  NUM 2, TABLA ESPECIAL - NUM 3, TABLA PRECOMPETITIVA - NUM 4, TABLA COMPETITIVA
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

        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();

        //Si hay una columna, la siguiente sera la 2.
        //Si hay 4 columnas, la siguiente sera la 5
        int numInicial = modeloTabla.getColumnCount();
        int nuevoNum = numInicial + 1;

        //Agrego la columna
        modeloTabla.addColumn(nuevoNum);

        // Obtener el índice de la nueva columna
        int nuevaColumnaIndex = modeloTabla.getColumnCount() - 1;

        // Establecer el valor 0 en la nueva columna 
        modeloTabla.setValueAt(0, 0, nuevaColumnaIndex);

    }

    //para eliminar una columna
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

        //Sumatoria de especial
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

        //Sumatoria de Precompetitivo
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

        //Sumatoria de Competitivo
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

    public boolean validarCiclicidad() {
        DefaultTableModel modeloTablaPreparacion = (DefaultTableModel) this.tblEtapaPreparacion.getModel();
        DefaultTableModel modeloTablaEspecial = (DefaultTableModel) this.tblEtapaEspecial.getModel();
        DefaultTableModel modeloTablaPrecompetitivo = (DefaultTableModel) this.tblEtapaPrecompetitivo.getModel();
        DefaultTableModel modeloTablaCompetitivo = (DefaultTableModel) this.tblEtapaCompetitivo.getModel();

        ArrayList<String> ciclicidadPreparacion = new ArrayList<>();
        ArrayList<String> semanasPreparacion = new ArrayList<>();

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

        ArrayList<String> ciclicidadEspecial = new ArrayList<>();
        ArrayList<String> semanasEspecial = new ArrayList<>();

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

        if (this.precompetitivasActiva) {
            ArrayList<String> ciclicidadPrecompetitiva = new ArrayList<>();
            ArrayList<String> semanasPrecompetitiva = new ArrayList<>();

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

        ArrayList<String> ciclicidadCompetitiva = new ArrayList<>();
        ArrayList<String> semanasCompetitiva = new ArrayList<>();

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

    public void desbloqueoBtnValidacionTablas() {
        //this.btnValidarCiclicidad.setEnabled(true);
        this.jbtnValidarMesociclos.setEnabled(true);
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
        txtDeporte = new javax.swing.JTextField();
        txtRama = new javax.swing.JTextField();
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

        jLabelDistribucion1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLabelDistribucion2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelDistribucion2.setText("Distribución por etapa especial");

        jLabelMeso2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMeso2.setText("Mesociclos");

        jLabelMicro2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelMicro2.setText("Microciclo");

        jLabelCicli2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabelCicli2.setText("Ciclicidad");

        jLabelDistribucion3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jbtnEliminarPreparacion.setText("Eliminar uno");
        jbtnEliminarPreparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarPreparacionActionPerformed(evt);
            }
        });

        jbtnEliminarEspecial.setText("Eliminar uno");
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

        jbtnEliminarCompetitivo.setText("Eliminar uno");
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

        jbtnEliminarPrecompetitivo.setText("Eliminar uno");
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
                                        .addComponent(jbtnEditarEtapas)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCalcularEtapas))
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
                                            .addComponent(jLabelCicli1, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addComponent(jLabelDistribucion1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbtnEliminarPrecompetitivo)
                                                .addGap(18, 18, 18)
                                                .addComponent(jbtnAgregarPrecompetitiva))
                                            .addComponent(jspEtapaPrecompetitivo)))
                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabelMeso3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelMicro3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelCicli3, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addComponent(jLabelDistribucion3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbtnEliminarCompetitivo)
                                                .addGap(18, 18, 18)
                                                .addComponent(jbtnAgregarCompetitiva))
                                            .addComponent(jspEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(297, 297, 297))
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
                                                    .addComponent(txtRama, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtMetodologo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtJefeRama, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtEntAux, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(jLabelMeso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabelMicro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabelCicli, javax.swing.GroupLayout.Alignment.LEADING))
                                                        .addComponent(jLabelCicli2))
                                                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                        .addComponent(jLabelMicro2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(7, 7, 7)))
                                                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jbtnValidarMesociclos)
                                                    .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jspEtapaEspecial)
                                                        .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                            .addComponent(jLabelDistribucion2)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jbtnEliminarEspecial)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jbtnAgregarEspecial)
                                                            .addGap(1, 1, 1))
                                                        .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                                            .addComponent(jLabelDistribucion)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jbtnEliminarPreparacion)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jbtnAgregarPreparacion))
                                                        .addComponent(jspEtapaPreparacion, javax.swing.GroupLayout.Alignment.TRAILING))))
                                            .addComponent(jLabelMeso2))
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel23))
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
                        .addGap(0, 28, Short.MAX_VALUE))
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
                .addGap(14, 14, 14)
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
                            .addComponent(txtDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
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
                        .addGap(29, 29, 29)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDistribucion)
                            .addComponent(jbtnAgregarPreparacion)
                            .addComponent(jbtnEliminarPreparacion))
                        .addGap(18, 18, 18)
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
                            .addComponent(jbtnAgregarPrecompetitiva)
                            .addComponent(jbtnEliminarPrecompetitivo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabelMeso1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelMicro1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCicli1))
                            .addComponent(jspEtapaPrecompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDistribucion3)
                            .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbtnAgregarCompetitiva)
                                .addComponent(jbtnEliminarCompetitivo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabelMeso3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelMicro3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelCicli3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jspEtapaCompetitivo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpMacrocicloLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDistribucion2)
                            .addComponent(jbtnAgregarEspecial)
                            .addComponent(jbtnEliminarEspecial))
                        .addGap(8, 8, 8)
                        .addGroup(jpMacrocicloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspEtapaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpMacrocicloLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabelMeso2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelMicro2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelCicli2)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
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

    private void btnValidarSemanasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarSemanasActionPerformed
        // TODO add your handling code here:
        boolean esValido;
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
            this.btnValidarSemanas.setEnabled(true);
            this.jbtnEditarEtapas.setEnabled(true);
            resetearPeriodos();
        }
    }//GEN-LAST:event_btnValidarSemanasActionPerformed

    private void jbtnEditarMacrocicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarMacrocicloActionPerformed
        //activa los todos los campos        
        this.activarCamposEdicion();
        this.ocultarTablaMeso();
    }//GEN-LAST:event_jbtnEditarMacrocicloActionPerformed

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

    private void jbtnAgregarPreparacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarPreparacionActionPerformed
        agregarMesociclo(this.tblEtapaPreparacion, 1);
    }//GEN-LAST:event_jbtnAgregarPreparacionActionPerformed

    private void jbtnEliminarPreparacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarPreparacionActionPerformed
        eliminarMesociclo(this.tblEtapaPreparacion, 1);
    }//GEN-LAST:event_jbtnEliminarPreparacionActionPerformed

    private void jbtnEliminarEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarEspecialActionPerformed
        eliminarMesociclo(this.tblEtapaEspecial, 2);
    }//GEN-LAST:event_jbtnEliminarEspecialActionPerformed

    private void jbtnAgregarEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarEspecialActionPerformed
        agregarMesociclo(this.tblEtapaEspecial, 2);
    }//GEN-LAST:event_jbtnAgregarEspecialActionPerformed

    private void jbtnEliminarCompetitivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarCompetitivoActionPerformed
        eliminarMesociclo(this.tblEtapaCompetitivo, 4);
    }//GEN-LAST:event_jbtnEliminarCompetitivoActionPerformed

    private void jbtnAgregarCompetitivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarCompetitivaActionPerformed
        agregarMesociclo(this.tblEtapaCompetitivo, 4);
    }//GEN-LAST:event_jbtnAgregarCompetitivaActionPerformed

    private void jbtnAgregarPrecompetitivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarPrecompetitivaActionPerformed
        agregarMesociclo(this.tblEtapaPrecompetitivo, 3);
    }//GEN-LAST:event_jbtnAgregarPrecompetitivaActionPerformed

    private void jbtnEliminarPrecompetitivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarPrecompetitivoActionPerformed
        eliminarMesociclo(this.tblEtapaPrecompetitivo, 3);
    }//GEN-LAST:event_jbtnEliminarPrecompetitivoActionPerformed

    private void jbtnValidarMesociclosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnValidarMesociclosActionPerformed
        boolean distribucionValida = validarDistribucionEtapas();
        boolean ciclicidadValida = validarCiclicidad();

        if (distribucionValida && ciclicidadValida) {
            this.jbtnCrearMacrociclo.setEnabled(true);
        }


    }//GEN-LAST:event_jbtnValidarMesociclosActionPerformed

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
