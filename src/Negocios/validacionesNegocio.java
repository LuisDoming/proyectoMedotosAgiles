/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocios;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author spide
 */
public class validacionesNegocio {
     /**
     * Método que valida las celdas donde se ingresan los campos de microciclos
     * dentro de la distribución de cada una de las etapas
     *
     * @return true si las celdas pasan todas las validaciones, false en caso
     * contrario
     */
    public boolean validarDistribucionEtapas(JFrame frame, JTable[] tablas, JTextField[] camposSemanas, boolean preparativaActiva, boolean especialActiva, boolean precompetitivasActiva, boolean competitivaActiva) {

        DefaultTableModel modeloTablaPreparacion = (DefaultTableModel) tablas[0].getModel();
        DefaultTableModel modeloTablaEspecial = (DefaultTableModel) tablas[1].getModel();
        DefaultTableModel modeloTablaPrecompetitivo = (DefaultTableModel) tablas[2].getModel();
        DefaultTableModel modeloTablaCompetitivo = (DefaultTableModel) tablas[3].getModel();

        String textoSemanaPreparacion = camposSemanas[0].getText();
        String textoSemanaEspecial = camposSemanas[1].getText();
        String textoSemanaPrecompetitiva = camposSemanas[2].getText();
        String textoSemanaCompetitiva = camposSemanas[3].getText();

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
        if (preparativaActiva) {
            for (int i = 0; i < modeloTablaPreparacion.getColumnCount(); i++) {

                //Checa que no haya nulos antes de hacer las pasadas por las columnas, si los encuentra, pone 0 tal cual
                if (modeloTablaPreparacion.getValueAt(0, i) == null) {
                    sumaPreparacion = sumaPreparacion + 0;
                } else {
                    try {

                        int valorCelda = Integer.parseInt(modeloTablaPreparacion.getValueAt(0, i).toString());
                        if (valorCelda <= 0) {
                            JOptionPane.showMessageDialog(frame, "Error. Se detecto un campo con 0 en la tabla de preparación");
                            return false;
                        }
                        sumaPreparacion = sumaPreparacion + valorCelda;

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(frame, "Error. Se detectó un campo que no es número entero positivo en la tabla de preparación");
                        return false;
                    }
                }

            }
        }

        //Sumatoria de especial
        if (especialActiva) {
            for (int i = 0; i < modeloTablaEspecial.getColumnCount(); i++) {

                if (modeloTablaEspecial.getValueAt(0, i) == null) {
                    sumaEspecial = sumaEspecial + 0;
                } else {
                    try {
                        int valorCelda = Integer.parseInt(modeloTablaEspecial.getValueAt(0, i).toString());

                        if (valorCelda <= 0) {
                            JOptionPane.showMessageDialog(frame, "Error. Se detecto un campo con 0 en la tabla de etapa especial");
                            return false;
                        }

                        sumaEspecial = sumaEspecial + valorCelda;
                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(frame, "Error. Se detectó un campo que no es número entero positivo en la tabla de etapa especial");
                        return false;
                    }
                }
            }
        }

        //Sumatoria de Precompetitivo
        if (precompetitivasActiva) {
            for (int i = 0; i < modeloTablaPrecompetitivo.getColumnCount(); i++) {

                if (modeloTablaPrecompetitivo.getValueAt(0, i) == null) {
                    sumaPrecompetitivo = sumaPrecompetitivo + 0;
                } else {
                    try {
                        int valorCelda = Integer.parseInt(modeloTablaPrecompetitivo.getValueAt(0, i).toString());

                        if (valorCelda <= 0) {
                            JOptionPane.showMessageDialog(frame, "Error. Se detecto un campo con 0 en la tabla de etapa precompetitiva");
                            return false;
                        }

                        sumaPrecompetitivo = sumaPrecompetitivo + valorCelda;
                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(frame, "Error. Se detectó un campo que no es número entero positivo en la tabla de etapa precompetitiva");

                    }
                }
            }
        }

        //Sumatoria de Competitivo
        if (competitivaActiva) {
            for (int i = 0; i < modeloTablaCompetitivo.getColumnCount(); i++) {

                if (modeloTablaCompetitivo.getValueAt(0, i) == null) {
                    sumaCompetitivo = sumaCompetitivo + 0;
                } else {
                    try {
                        int valorCelda = Integer.parseInt(modeloTablaCompetitivo.getValueAt(0, i).toString());

                        if (valorCelda <= 0) {
                            JOptionPane.showMessageDialog(frame, "Error. Se detecto un campo con 0 en la tabla de etapa competitiva");
                            return false;
                        }

                        sumaCompetitivo = sumaCompetitivo + valorCelda;
                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(frame, "Error. Se detectó un campo que no es número entero positivo en la tabla de etapa competitiva");

                    }
                }
            }
        }

        System.out.println(sumaPreparacion);
        System.out.println(sumaEspecial);
        System.out.println(sumaPrecompetitivo);
        System.out.println(sumaCompetitivo);

        if (sumaPreparacion == etapaPreparacion && sumaEspecial == etapaEspecial && sumaPrecompetitivo == etapaPrecompetitivo && sumaCompetitivo == etapaCompetitivo) {
            JOptionPane.showMessageDialog(frame, "La distribución en las etapas es válida");
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Error. La distribución de etapas no coincide con las semanas establecidas");
            return false;
        }
    }
}
