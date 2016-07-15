/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocios.model;

/**
 *
 * @author Administrator
 */
public class EstructuraPalabras {
    private int id;
    
    private String idioma;
    private String nombreEstructura;
    private String estructuraEntrante;
    private String estructuraSaliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNombreEstructura() {
        return nombreEstructura;
    }

    public void setNombreEstructura(String nombreEstructura) {
        this.nombreEstructura = nombreEstructura;
    }

    public String getEstructuraEntrante() {
        return estructuraEntrante;
    }

    public void setEstructuraEntrante(String estructuraEntrante) {
        this.estructuraEntrante = estructuraEntrante;
    }

    public String getEstructuraSaliente() {
        return estructuraSaliente;
    }

    public void setEstructuraSaliente(String estructuraSaliente) {
        this.estructuraSaliente = estructuraSaliente;
    }

    
}
