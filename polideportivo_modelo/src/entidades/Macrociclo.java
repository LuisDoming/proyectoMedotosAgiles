/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rjsaa
 */
public class Macrociclo {
    private String fechaInicio,fechaFin;
    private String deporte,rama,jefeRama,preparadorFis,Metodologo;
    private int periodoPreparativo, periodoCompetitivo,totalSemanas;
    private int etapaPreparativa,etapaEspecial,etapaPrecompetitiva,etapaCompetitiva;
    private ArrayList<Integer> distribucionPreparativa,distribucionEspecial,distribucionPrecom,distribucionCompetitiva;
    private ArrayList<Integer> ciclicidadPreparativa,ciclicidadEspecial,ciclicidadPrecom,ciclicidadCompetitiva;

    public Macrociclo() {
    }

    public Macrociclo(String fechaInicio, String fechaFin, String deporte, String rama, String jefeRama, String preparadorFis, String Metodologo, int periodoPreparativo, int periodoCompetitivo, int totalSemanas, int etapaPreparativa, int etapaEspecial, int etapaPrecompetitiva, int etapaCompetitiva, ArrayList<Integer> distribucionPreparativa, ArrayList<Integer> distribucionEspecial, ArrayList<Integer> distribucionPrecom, ArrayList<Integer> distribucionCompetitiva, ArrayList<Integer> ciclicidadPreparativa, ArrayList<Integer> ciclicidadEspecial, ArrayList<Integer> ciclicidadPrecom, ArrayList<Integer> ciclicidadCompetitiva) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.deporte = deporte;
        this.rama = rama;
        this.jefeRama = jefeRama;
        this.preparadorFis = preparadorFis;
        this.Metodologo = Metodologo;
        this.periodoPreparativo = periodoPreparativo;
        this.periodoCompetitivo = periodoCompetitivo;
        this.totalSemanas = totalSemanas;
        this.etapaPreparativa = etapaPreparativa;
        this.etapaEspecial = etapaEspecial;
        this.etapaPrecompetitiva = etapaPrecompetitiva;
        this.etapaCompetitiva = etapaCompetitiva;
        this.distribucionPreparativa = distribucionPreparativa;
        this.distribucionEspecial = distribucionEspecial;
        this.distribucionPrecom = distribucionPrecom;
        this.distribucionCompetitiva = distribucionCompetitiva;
        this.ciclicidadPreparativa = ciclicidadPreparativa;
        this.ciclicidadEspecial = ciclicidadEspecial;
        this.ciclicidadPrecom = ciclicidadPrecom;
        this.ciclicidadCompetitiva = ciclicidadCompetitiva;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public String getJefeRama() {
        return jefeRama;
    }

    public void setJefeRama(String jefeRama) {
        this.jefeRama = jefeRama;
    }

    public String getPreparadorFis() {
        return preparadorFis;
    }

    public void setPreparadorFis(String preparadorFis) {
        this.preparadorFis = preparadorFis;
    }

    public String getMetodologo() {
        return Metodologo;
    }

    public void setMetodologo(String Metodologo) {
        this.Metodologo = Metodologo;
    }

    public int getPeriodoPreparativo() {
        return periodoPreparativo;
    }

    public void setPeriodoPreparativo(int periodoPreparativo) {
        this.periodoPreparativo = periodoPreparativo;
    }

    public int getPeriodoCompetitivo() {
        return periodoCompetitivo;
    }

    public void setPeriodoCompetitivo(int periodoCompetitivo) {
        this.periodoCompetitivo = periodoCompetitivo;
    }

    public int getTotalSemanas() {
        return totalSemanas;
    }

    public void setTotalSemanas(int totalSemanas) {
        this.totalSemanas = totalSemanas;
    }

    public int getEtapaPreparativa() {
        return etapaPreparativa;
    }

    public void setEtapaPreparativa(int etapaPreparativa) {
        this.etapaPreparativa = etapaPreparativa;
    }

    public int getEtapaEspecial() {
        return etapaEspecial;
    }

    public void setEtapaEspecial(int etapaEspecial) {
        this.etapaEspecial = etapaEspecial;
    }

    public int getEtapaPrecompetitiva() {
        return etapaPrecompetitiva;
    }

    public void setEtapaPrecompetitiva(int etapaPrecompetitiva) {
        this.etapaPrecompetitiva = etapaPrecompetitiva;
    }

    public int getEtapaCompetitiva() {
        return etapaCompetitiva;
    }

    public void setEtapaCompetitiva(int etapaCompetitiva) {
        this.etapaCompetitiva = etapaCompetitiva;
    }

    public ArrayList<Integer> getDistribucionPreparativa() {
        return distribucionPreparativa;
    }

    public void setDistribucionPreparativa(ArrayList<Integer> distribucionPreparativa) {
        this.distribucionPreparativa = distribucionPreparativa;
    }

    public ArrayList<Integer> getDistribucionEspecial() {
        return distribucionEspecial;
    }

    public void setDistribucionEspecial(ArrayList<Integer> distribucionEspecial) {
        this.distribucionEspecial = distribucionEspecial;
    }

    public ArrayList<Integer> getDistribucionPrecom() {
        return distribucionPrecom;
    }

    public void setDistribucionPrecom(ArrayList<Integer> distribucionPrecom) {
        this.distribucionPrecom = distribucionPrecom;
    }

    public ArrayList<Integer> getDistribucionCompetitiva() {
        return distribucionCompetitiva;
    }

    public void setDistribucionCompetitiva(ArrayList<Integer> distribucionCompetitiva) {
        this.distribucionCompetitiva = distribucionCompetitiva;
    }

    public ArrayList<Integer> getCiclicidadPreparativa() {
        return ciclicidadPreparativa;
    }

    public void setCiclicidadPreparativa(ArrayList<Integer> ciclicidadPreparativa) {
        this.ciclicidadPreparativa = ciclicidadPreparativa;
    }

    public ArrayList<Integer> getCiclicidadEspecial() {
        return ciclicidadEspecial;
    }

    public void setCiclicidadEspecial(ArrayList<Integer> ciclicidadEspecial) {
        this.ciclicidadEspecial = ciclicidadEspecial;
    }

    public ArrayList<Integer> getCiclicidadPrecom() {
        return ciclicidadPrecom;
    }

    public void setCiclicidadPrecom(ArrayList<Integer> ciclicidadPrecom) {
        this.ciclicidadPrecom = ciclicidadPrecom;
    }

    public ArrayList<Integer> getCiclicidadCompetitiva() {
        return ciclicidadCompetitiva;
    }

    public void setCiclicidadCompetitiva(ArrayList<Integer> ciclicidadCompetitiva) {
        this.ciclicidadCompetitiva = ciclicidadCompetitiva;
    }

    @Override
    public String toString() {
        return "macrociclo{" + "fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", deporte=" + deporte + ", rama=" + rama + ", jefeRama=" + jefeRama + ", preparadorFis=" + preparadorFis + ", Metodologo=" + Metodologo + ", periodoPreparativo=" + periodoPreparativo + ", periodoCompetitivo=" + periodoCompetitivo + ", totalSemanas=" + totalSemanas + ", etapaPreparativa=" + etapaPreparativa + ", etapaEspecial=" + etapaEspecial + ", etapaPrecompetitiva=" + etapaPrecompetitiva + ", etapaCompetitiva=" + etapaCompetitiva + ", distribucionPreparativa=" + distribucionPreparativa + ", distribucionEspecial=" + distribucionEspecial + ", distribucionPrecom=" + distribucionPrecom + ", distribucionCompetitiva=" + distribucionCompetitiva + ", ciclicidadPreparativa=" + ciclicidadPreparativa + ", ciclicidadEspecial=" + ciclicidadEspecial + ", ciclicidadPrecom=" + ciclicidadPrecom + ", ciclicidadCompetitiva=" + ciclicidadCompetitiva + '}';
    }
    
    
}
