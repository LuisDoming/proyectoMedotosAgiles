/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agiles.entidades;

/**
 *
 * @author rjsaa
 */
public class Disciplina {
    private int id;
    private String nombre;
    private VolumenEtapa preparativa,especial,precom,competitiva;
    private int total;

    public Disciplina() {
    }

    public Disciplina(String nombre, VolumenEtapa preparativa, VolumenEtapa especial, VolumenEtapa precom, VolumenEtapa competitiva, int total) {
        this.nombre = nombre;
        this.preparativa = preparativa;
        this.especial = especial;
        this.precom = precom;
        this.competitiva = competitiva;
        this.total = total;
    }

    public Disciplina(int id, String nombre, VolumenEtapa preparativa, VolumenEtapa especial, VolumenEtapa precom, VolumenEtapa competitiva, int total) {
        this.id = id;
        this.nombre = nombre;
        this.preparativa = preparativa;
        this.especial = especial;
        this.precom = precom;
        this.competitiva = competitiva;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public VolumenEtapa getPreparativa() {
        return preparativa;
    }

    public void setPreparativa(VolumenEtapa preparativa) {
        this.preparativa = preparativa;
    }

    public VolumenEtapa getEspecial() {
        return especial;
    }

    public void setEspecial(VolumenEtapa especial) {
        this.especial = especial;
    }

    public VolumenEtapa getPrecom() {
        return precom;
    }

    public void setPrecom(VolumenEtapa precom) {
        this.precom = precom;
    }

    public VolumenEtapa getCompetitiva() {
        return competitiva;
    }

    public void setCompetitiva(VolumenEtapa competitiva) {
        this.competitiva = competitiva;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Disciplina{" + "id=" + id + ", nombre=" + nombre + ", preparativa=" + preparativa + ", especial=" + especial + ", precom=" + precom + ", competitiva=" + competitiva + ", total=" + total + '}';
    }
    
    
    
    
    
    
    
}
