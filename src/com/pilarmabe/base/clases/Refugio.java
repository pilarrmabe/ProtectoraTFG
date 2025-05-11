package com.pilarmabe.base.clases;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Refugio {
    private int idRefugio;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private String responsable;
    private String telefono;
    private String email;
    private int capacidad;
    private String fechaApertura;
    private byte[] logoRefugio;

    public Refugio() {}

    public Refugio(String nombre, String direccion, String ciudad, String codigoPostal, String responsable,
                   String telefono, String email, int capacidad, String fechaApertura, byte[] logoRefugio) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.responsable = responsable;
        this.telefono = telefono;
        this.email = email;
        this.capacidad = capacidad;
        this.fechaApertura = fechaApertura;
        this.logoRefugio = logoRefugio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRefugio")
    public int getIdRefugio() {
        return idRefugio;
    }

    public void setIdRefugio(int idRefugio) {
        this.idRefugio = idRefugio;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "ciudad")
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Column(name = "codigoPostal")
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Column(name = "responsable")
    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "capacidad")
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Column(name = "fechaApertura")
    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Lob
    @Column(name = "logoRefugio")
    public byte[] getLogoRefugio() {
        return logoRefugio;
    }

    public void setLogoRefugio(byte[] logoRefugio) {
        this.logoRefugio = logoRefugio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Refugio)) return false;
        Refugio refugio = (Refugio) o;
        return idRefugio == refugio.idRefugio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRefugio);
    }

    @Override
    public String toString() {
        return "Refugio{" +
                "idRefugio=" + idRefugio +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", responsable='" + responsable + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", capacidad=" + capacidad +
                ", fechaApertura=" + fechaApertura +
                ", logoRefugio=" + (logoRefugio != null ? "[Archivo de imagen]" : "Ningún logo") +
                '}';
    }
}
