/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.modelo;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Cliente implements java.io.Serializable {

    //
    //------- Constructores
    public Cliente() {
        this.diaDeCreacion = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);
        this.mesDeCreacion = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
        this.agnoDeCreacion = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    }

    //
    //------- Variables de datos a solicitar
    private int codigoConteo;
    private int diaDeCreacion;         // Automática - Importante
    private int mesDeCreacion;         // Automática - Importante
    private int agnoDeCreacion;        // Automática - Importante
    private boolean viable;
    private String nombre;             // Importante
    private String correo;             // Importante
    private String celular;            // Importante
    private String medioPublicitario;  // Importante
    private String zonaBusqueda;
    private String proyectoDeInteres;
    private boolean gestionDesdeSalaDeVentas;
    private boolean habeasData;

    //
    //------- Variables de estado de cliente
    // Contactado
    private String fechaUltimoContacto;
    private boolean contactado = fechaUltimoContacto != null;
    private boolean contactoEfectivo;

    // Calificado
    private String proyectoCalificado;
    private boolean calificado = proyectoCalificado != null;

    // Agendado para visita   
    private int diaVisita = -1;
    private int mesVisita = -1;
    private int agnoVisita = -1;
    private boolean visitaAgendada = diaVisita >= 0 && mesVisita >= 0 && agnoVisita >= 0;
    private boolean visitaEfectiva;

    // Estado del prospecto
    private String estado;

    //
    //------- Métodos de gestion
    public int getCodigoConteo() {
        return codigoConteo;
    }

    public void setCodigoConteo(int codigoConteo) {
        this.codigoConteo = codigoConteo;
    }

    public int getDiaDeCreacion() {
        return diaDeCreacion;
    }

    public void setDiaDeCreacion(int diaDeCreacion) {
        this.diaDeCreacion = diaDeCreacion;
    }

    public int getMesDeCreacion() {
        return mesDeCreacion;
    }

    public void setMesDeCreacion(int mesDeCreacion) {
        this.mesDeCreacion = mesDeCreacion;
    }

    public int getAgnoDeCreacion() {
        return agnoDeCreacion;
    }

    public void setAgnoDeCreacion(int agnoDeCreacion) {
        this.agnoDeCreacion = agnoDeCreacion;
    }

    public boolean isViable() {
        return viable;
    }

    public void setViable(boolean esViable) {
        this.viable = esViable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMedioPublicitario() {
        return medioPublicitario;
    }

    public void setMedioPublicitario(String medioPublicitario) {
        this.medioPublicitario = medioPublicitario;
    }

    public String getZonaBusqueda() {
        return zonaBusqueda;
    }

    public void setZonaBusqueda(String zonaBusqueda) {
        this.zonaBusqueda = zonaBusqueda;
    }

    public String getProyectoDeInteres() {
        return proyectoDeInteres;
    }

    public void setProyectoDeInteres(String proyectoDeInteres) {
        this.proyectoDeInteres = proyectoDeInteres;
    }

    public boolean isGestionDesdeSalaDeVentas() {
        return gestionDesdeSalaDeVentas;
    }

    public void setGestionDesdeSalaDeVentas(boolean gestionDesdeSalaDeVentas) {
        this.gestionDesdeSalaDeVentas = gestionDesdeSalaDeVentas;
    }

    public boolean isHabeasData() {
        return habeasData;
    }

    public void setHabeasData(boolean habeasData) {
        this.habeasData = habeasData;
    }

    //------- Métodos de estado de cliente
    public boolean isContactado() {
        return contactado;
    }

    public String getFechaUltimoContacto() {
        return fechaUltimoContacto;
    }

    public void setFechaUltimoContacto(String fechaUltimoContacto) {
        this.fechaUltimoContacto = fechaUltimoContacto;
    }

    public boolean isContactoEfectivo() {
        return contactoEfectivo;
    }

    public void setContactoEfectivo(boolean contactoEfectivo) {
        this.contactoEfectivo = contactoEfectivo;
    }

    public String getProyectoCalificado() {
        return proyectoCalificado;
    }

    public void setProyectoCalificado(String proyectoCalificado) {
        this.proyectoCalificado = proyectoCalificado;
    }

    public boolean isCalificado() {
        return this.calificado;
    }

    public int getDiaVisita() {
        return diaVisita;
    }

    public void setDiaVisita(int diaVisita) {
        this.diaVisita = diaVisita;
    }

    public int getMesVisita() {
        return mesVisita;
    }

    public void setMesVisita(int mesVisita) {
        this.mesVisita = mesVisita;
    }

    public int getAgnoVisita() {
        return agnoVisita;
    }

    public void setAgnoVisita(int agnoVisita) {
        this.agnoVisita = agnoVisita;
    }

    public boolean isVisitaAgendada() {
        return this.visitaAgendada;
    }

    public boolean isVisitaEfectiva() {
        return visitaEfectiva;
    }

    public void setVisitaEfectiva(boolean visitaEfectiva) {
        this.visitaEfectiva = visitaEfectiva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
