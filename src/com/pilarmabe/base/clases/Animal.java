package com.pilarmabe.base.clases;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Animal {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private String fechaNacimiento;
    private String fechaIngreso;
    private int edad;
    private double peso;
    private String salud;
    private String comportamiento;
    private String necesidades;
    private String numeroMicrochip;
    private byte[] foto;
    private Refugio refugio;
    private Veterinario veterinario;

    public Animal() {
    }

    public Animal(String nombre, String especie, String raza, String sexo, String fechaNacimiento,
                  String fechaIngreso, int edad, double peso, String salud, String comportamiento,
                  String necesidades, String numeroMicrochip, byte[] foto,
                  Refugio refugio, Veterinario veterinario) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.edad = edad;
        this.peso = peso;
        this.salud = salud;
        this.comportamiento = comportamiento;
        this.necesidades = necesidades;
        this.numeroMicrochip = numeroMicrochip;
        this.foto = foto;
        this.refugio = refugio;
        this.veterinario = veterinario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAnimal")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "especie")
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Column(name = "raza")
    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    @Column(name = "sexo")
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Column(name = "fechaNacimiento")
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name = "fechaIngreso")
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Column(name = "edad")
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Column(name = "peso")
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Column(name = "salud")
    public String getSalud() {
        return salud;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    @Column(name = "comportamiento")
    public String getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    @Column(name = "necesidades")
    public String getNecesidades() {
        return necesidades;
    }

    public void setNecesidades(String necesidades) {
        this.necesidades = necesidades;
    }

    @Column(name = "numeroMicrochip", unique = true)
    public String getNumeroMicrochip() {
        return numeroMicrochip;
    }

    public void setNumeroMicrochip(String numeroMicrochip) {
        this.numeroMicrochip = numeroMicrochip;
    }

    @Lob
    @Column(name = "foto")
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @ManyToOne
    @JoinColumn(name = "idRefugio", referencedColumnName = "idRefugio")
    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

    @ManyToOne
    @JoinColumn(name = "idVeterinario", referencedColumnName = "idVeterinario")
    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", raza='" + raza + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaIngreso=" + fechaIngreso +
                ", edad=" + edad +
                ", peso=" + peso +
                ", salud='" + salud + '\'' +
                ", comportamiento='" + comportamiento + '\'' +
                ", necesidades='" + necesidades + '\'' +
                ", numeroMicrochip='" + numeroMicrochip + '\'' +
                ", foto=" + (foto != null ? "[Archivo de imagen]" : "Ninguna foto") +
                ", refugio=" + refugio +
                ", veterinario=" + veterinario +
                '}';
    }
}
