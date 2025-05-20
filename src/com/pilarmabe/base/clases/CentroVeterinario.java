package com.pilarmabe.base.clases;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CentroVeterinario {
    private int idCentro;
    private String nombre;
    private String direccion;
    private String codigoPostal;
    private String telefono;
    private String email;
    private String fechaRegistro;
    private boolean servicioUrgencias;
    private String fotoCentro;

    public CentroVeterinario() {}

    public CentroVeterinario(String nombre, String direccion, String codigoPostal, String telefono, String email,
                             String fechaRegistro, boolean servicioUrgencias, String fotoCentro) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.servicioUrgencias = servicioUrgencias;
        this.fotoCentro = fotoCentro;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCentro")
    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
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

    @Column(name = "codigoPostal")
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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

    @Column(name = "fechaRegistro")
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Column(name = "servicioUrgencias")
    public boolean isServicioUrgencias() {
        return servicioUrgencias;
    }

    public void setServicioUrgencias(boolean servicioUrgencias) {
        this.servicioUrgencias = servicioUrgencias;
    }

    @Lob
    @Column(name = "fotoCentro")
    public String getFotoCentro() {
        return fotoCentro;
    }

    public void setFotoCentro(String fotoCentro) {
        this.fotoCentro = fotoCentro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CentroVeterinario)) return false;
        CentroVeterinario that = (CentroVeterinario) o;
        return idCentro == that.idCentro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCentro);
    }

    @Override
    public String toString() {
        return "CentroVeterinario{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", servicioUrgencias=" + servicioUrgencias +
                ", fotoCentro=" + fotoCentro +
                '}';
    }
}
