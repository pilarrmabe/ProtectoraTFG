package com.pilarmabe.base.clases;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Adoptante {
    private int idAdoptante;
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String telefono;
    private String email;
    private Date fechaRegistro;
    private byte[] foto;

    public Adoptante() {
    }

    public Adoptante(String nombre, String apellidos, String dni, String direccion, String telefono, String email, Date fechaRegistro, byte[] foto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.foto = foto;
    }

    @Id
    @Column(name = "idAdoptante")
    public int getIdAdoptante() {
        return idAdoptante;
    }

    public void setIdAdoptante(int idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellidos")
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "dni")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "fechaRegistro")
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Lob
    @Column(name = "foto")
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adoptante adoptante = (Adoptante) o;
        return idAdoptante == adoptante.idAdoptante &&
                Objects.equals(nombre, adoptante.nombre) &&
                Objects.equals(apellidos, adoptante.apellidos) &&
                Objects.equals(dni, adoptante.dni) &&
                Objects.equals(direccion, adoptante.direccion) &&
                Objects.equals(telefono, adoptante.telefono) &&
                Objects.equals(email, adoptante.email) &&
                Objects.equals(fechaRegistro, adoptante.fechaRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdoptante, nombre, apellidos, dni, direccion, telefono, email, fechaRegistro);
    }

    @Override
    public String toString() {
        return "Adoptante{" +
                "idAdoptante=" + idAdoptante +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", foto=" + (foto != null ? "[Archivo de imagen]" : "Ninguna foto") +
                '}';
    }
}
