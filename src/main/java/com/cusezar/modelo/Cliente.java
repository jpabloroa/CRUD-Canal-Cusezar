/**
 * NO MODIFIQUE ESTE CÓDIGO
 *
 * Licencia registrada Roverin Technologics - 2021
 */
package com.cusezar.modelo;

import java.util.Date;
import java.util.Calendar;

/**
 *
 * Clase que defina el objeto <code>Cliente</code>
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Cliente implements java.io.Serializable {

    //
    //------- Constructores
    public Cliente() {
        this.fechaDeCreacion = Calendar.getInstance().getTime();
    }

    //
    //------- Variables de datos a solicitar
    private int codigoConteo;           // Automática - Importante
    private Date fechaDeCreacion;       // Automática - Importante
    private boolean viable;
    private String nombre;              // Importante
    private String correo;              // Importante
    private String celular;             // Importante
    private String medioPublicitario;   // Importante
    private String zonaBusqueda;
    private String proyectoDeInteres;
    private boolean gestionDesdeSalaDeVentas;
    private boolean habeasData;

    //
    //------- Variables de estado de cliente
    // Contactado
    private Date fechaDeContacto;
    //private boolean contactado = fechaDeContacto != null;
    private Date fechaDeContactoEfectivo;
    //private boolean contactoEfectivo = fechaDeContactoEfectivo != null;

    // Calificado
    private String proyectoCalificado;
    //private boolean calificado = proyectoCalificado != null;

    // Agendado para visita   
    private Date fechaVisitaAgendada;
    //private boolean visitaAgendada = fechaVisitaAgendada != null;
    private Date fechaVisitaEfectiva;
    //private boolean visitaEfectiva = fechaVisitaEfectiva != null;

    // Estado del prospecto
    private String estado;
    private Date fechaModificacionEstado;
    private String asignadoA;

    //
    //------- Métodos de gestion
    public int getCodigoConteo() {
        return codigoConteo;
    }

    public void setCodigoConteo(int codigoConteo) {
        this.codigoConteo = codigoConteo;
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
    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Date getFechaDeContacto() {
        return fechaDeContacto;
    }

    public void setFechaDeContacto(Date fechaDeContacto) {
        this.fechaDeContacto = fechaDeContacto;
    }

    /*public boolean isContactado() {
        return contactado;
    }*/

    /*public void setContactado(boolean contactado) {
        this.contactado = contactado;
        this.fechaDeContacto = Calendar.getInstance().getTime();
    }*/
    public Date getFechaDeContactoEfectivo() {
        return fechaDeContactoEfectivo;
    }

    public void setFechaDeContactoEfectivo(Date fechaDeContactoEfectivo) {
        this.fechaDeContactoEfectivo = fechaDeContactoEfectivo;
    }

    /*public boolean isContactoEfectivo() {
        return contactoEfectivo;
    }*/

    /*public void setContactoEfectivo(boolean contactoEfectivo) {
        this.contactoEfectivo = contactoEfectivo;
        this.fechaDeContactoEfectivo = (this.fechaDeContactoEfectivo == null) ? Calendar.getInstance().getTime() : this.fechaDeContactoEfectivo;
    }*/

    public String getProyectoCalificado() {
        return proyectoCalificado;
    }

    public void setProyectoCalificado(String proyectoCalificado) {
        this.proyectoCalificado = proyectoCalificado;
    }

    /*public boolean isCalificado() {
        return calificado;
    }*/

    /*public void setCalificado(boolean calificado) {
        this.calificado = calificado;
    }*/
    public Date getFechaVisitaAdendada() {
        return fechaVisitaAgendada;
    }

    public void setFechaVisitaAdendada(Date fechaVisitaAdendada) {
        this.fechaVisitaAgendada = fechaVisitaAdendada;
    }

    /*public boolean isVisitaAgendada() {
        return visitaAgendada;
    }*/

    /*public void setVisitaAgendada(boolean visitaAgendada) {
        this.visitaAgendada = visitaAgendada;
        this.fechaVisitaAgendada = Calendar.getInstance().getTime();
    }*/
    public Date getFechaVisitaEfectiva() {
        return fechaVisitaEfectiva;
    }

    public void setFechaVisitaEfectiva(Date fechaVisitaEfectiva) {
        this.fechaVisitaEfectiva = fechaVisitaEfectiva;
    }

    /*public boolean isVisitaEfectiva() {
        return visitaEfectiva;
    }*/

    /*public void setVisitaEfectiva(boolean visitaEfectiva) {
        this.visitaEfectiva = visitaEfectiva;
        this.fechaVisitaEfectiva = Calendar.getInstance().getTime();
    }*/
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        this.fechaModificacionEstado = Calendar.getInstance().getTime();
    }

    public Date getFechaModificacionEstado() {
        return fechaModificacionEstado;
    }

    public void setFechaModificacionEstado(Date fechaModificacionEstado) {
        this.fechaModificacionEstado = fechaModificacionEstado;
    }

    public String getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(String asignadoA) {
        this.asignadoA = asignadoA;
    }
}
