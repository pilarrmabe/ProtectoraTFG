package com.pilarmabe.base.clases;

import javax.persistence.*;

@Entity
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String pass;
    private String tipoUsuario;

    public Usuario() {}

    public Usuario(String nombre, String pass, String tipoUsuario) {
        this.nombre = nombre;
        this.pass = pass;
        this.tipoUsuario = tipoUsuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Column(name = "password")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Column(name = "tipoUsuario")
    public String getTipoUsuario() {
        return tipoUsuario;
    }
      
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", pass='" + pass + '\'' +
                ", tipoUsuario='" + tipoUsuario;
    }

}
