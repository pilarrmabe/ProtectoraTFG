package com.pilarmabe.base.clases;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Adopcion {
    private int idAdopcion;
    private Animal animal;
    private Adoptante adoptante;
    private Date fechaAdopcion;
    private String estadoAdopcion;
    private String observaciones;
    // private byte[] documentoAdopcion;

    public Adopcion() {
    }

    public Adopcion(Animal animal, Adoptante adoptante, Date fechaAdopcion, String estadoAdopcion, String observaciones) {
        this.animal = animal;
        this.adoptante = adoptante;
        this.fechaAdopcion = fechaAdopcion;
        this.estadoAdopcion = estadoAdopcion;
        this.observaciones = observaciones;
        // this.documentoAdopcion = documentoAdopcion;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "idAdopcion")
    public int getIdAdopcion() {
        return idAdopcion;
    }

    public void setIdAdopcion(int idAdopcion) {
        this.idAdopcion = idAdopcion;
    }

    @ManyToOne
    @JoinColumn(name = "idAnimal", referencedColumnName = "idAnimal")
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @ManyToOne
    @JoinColumn(name = "idAdoptante", referencedColumnName = "idAdoptante")
    public Adoptante getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Adoptante adoptante) {
        this.adoptante = adoptante;

    }

    @Basic
    @Column(name = "fechaAdopcion")
    public Date getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(Date fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    @Basic
    @Column(name = "estadoAdopcion")
    public String getEstadoAdopcion() {
        return estadoAdopcion;
    }

    public void setEstadoAdopcion(String estadoAdopcion) {
        this.estadoAdopcion = estadoAdopcion;
    }

    @Basic
    @Column(name = "observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // @Basic
    // @Column(name = "documentoAdopcion")
    // public byte[] getDocumentoAdopcion() {
    //     return documentoAdopcion;
    // }

    // public void setDocumentoAdopcion(byte[] documentoAdopcion) {
    //     this.documentoAdopcion = documentoAdopcion;
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adopcion adopcion = (Adopcion) o;
        return idAdopcion == adopcion.idAdopcion &&
                Objects.equals(animal, adopcion.animal) &&
                Objects.equals(adoptante, adopcion.adoptante) &&
                Objects.equals(fechaAdopcion, adopcion.fechaAdopcion) &&
                Objects.equals(estadoAdopcion, adopcion.estadoAdopcion) &&
                Objects.equals(observaciones, adopcion.observaciones);
                // Objects.equals(documentoAdopcion, adopcion.documentoAdopcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdopcion, animal, adoptante, fechaAdopcion, estadoAdopcion, observaciones);
    }

    @Override
    public String toString() {
        return "Adopcion{" +
                "idAdopcion=" + idAdopcion +
                ", animal=" + animal.getNombre() +
                ", adoptante=" + adoptante.getNombre() +
                ", fechaAdopcion=" + fechaAdopcion +
                ", estadoAdopcion='" + estadoAdopcion + '\'' +
                ", observaciones='" + observaciones + '\'' +
                // ", documentoAdopcion=" + (documentoAdopcion != null ? "[Archivo]" : "Ningún documento") +
                '}';
    }
}
