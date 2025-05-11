package com.pilarmabe.base.clases;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Veterinario {
    private int idVeterinario;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String especialidad;
    private int aniosExperiencia;
    private byte[] foto;
    private CentroVeterinario centroVeterinario;

    public Veterinario() {
    }

    public Veterinario(String nombre, String apellidos, String telefono, String email, String especialidad,
                       int aniosExperiencia, byte[] foto, CentroVeterinario centroVeterinario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.especialidad = especialidad;
        this.aniosExperiencia = aniosExperiencia;
        this.foto = foto;
        this.centroVeterinario = centroVeterinario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVeterinario")
    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
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
    @Column(name = "especialidad")
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Basic
    @Column(name = "aniosExperiencia")
    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    @Basic
    @Column(name = "foto")
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @ManyToOne
    @JoinColumn(name = "idCentroVeterinario", referencedColumnName = "idCentro")
    public CentroVeterinario getCentroVeterinario() {
        return centroVeterinario;
    }

    public void setCentroVeterinario(CentroVeterinario centroVeterinario) {
        this.centroVeterinario = centroVeterinario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veterinario that = (Veterinario) o;
        return idVeterinario == that.idVeterinario &&
                aniosExperiencia == that.aniosExperiencia &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellidos, that.apellidos) &&
                Objects.equals(telefono, that.telefono) &&
                Objects.equals(email, that.email) &&
                Objects.equals(especialidad, that.especialidad) &&
                Objects.equals(centroVeterinario, that.centroVeterinario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVeterinario, nombre, apellidos, telefono, email, especialidad, aniosExperiencia, centroVeterinario);
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "idVeterinario=" + idVeterinario +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", aniosExperiencia=" + aniosExperiencia +
                ", foto=" + (foto != null ? "[Archivo de imagen]" : "Ninguna foto") +
                ", centroVeterinario=" + centroVeterinario +
                '}';
    }
}
