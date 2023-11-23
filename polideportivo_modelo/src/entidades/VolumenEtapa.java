/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author rjsaa
 */
public class VolumenEtapa {
    private Etapa tipo;
    private float minimo,maximo,promedio,total;
    private int insitaciones,semanas;

    public VolumenEtapa() {
    }

    public VolumenEtapa(Etapa tipo, float minimo, float maximo, float promedio, float total, int insitaciones, int semanas) {
        this.tipo = tipo;
        this.minimo = minimo;
        this.maximo = maximo;
        this.promedio = promedio;
        this.total = total;
        this.insitaciones = insitaciones;
        this.semanas = semanas;
    }

    public Etapa getTipo() {
        return tipo;
    }

    public void setTipo(Etapa tipo) {
        this.tipo = tipo;
    }

    public float getMinimo() {
        return minimo;
    }

    public void setMinimo(float minimo) {
        this.minimo = minimo;
    }

    public float getMaximo() {
        return maximo;
    }

    public void setMaximo(float maximo) {
        this.maximo = maximo;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getInsitaciones() {
        return insitaciones;
    }

    public void setInsitaciones(int insitaciones) {
        this.insitaciones = insitaciones;
    }

    public int getSemanas() {
        return semanas;
    }

    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }

    @Override
    public String toString() {
        return "VolumenEtapa{" + "tipo=" + tipo + ", minimo=" + minimo + ", maximo=" + maximo + ", promedio=" + promedio + ", total=" + total + ", insitaciones=" + insitaciones + ", semanas=" + semanas + '}';
    }
    
    
    
}
