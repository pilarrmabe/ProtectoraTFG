package com.pilarmabe.base.mvc;

import com.pilarmabe.base.clases.*;
import com.pilarmabe.base.util.HibernateUtil;

import java.util.List;

public class Modelo {
    public void conectar(){
        try {
            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();
            System.out.println("Conexión exitosa con la base de datos.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void desconectar(){
        HibernateUtil.closeSessionFactory();
    }

    public List<Refugio> obtenerRefugios(){
        return HibernateUtil.getCurrentSession().createQuery("FROM Refugio").getResultList();
    }

    public List<Refugio> obtenerRefugiosPorNombre(String nombre){
        return HibernateUtil.getCurrentSession().createQuery("FROM Refugio WHERE nombre = :nombre").setParameter("nombre", nombre).getResultList();
    }

    public List<Animal> obtenerAnimales() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Animal").getResultList();
    }

    public List<Animal> obtenerAnimalesPorNombre(String nombre) {
        return HibernateUtil.getCurrentSession().createQuery("FROM Animal WHERE nombre = :nombre").setParameter("nombre", nombre).getResultList();
    }

    public List<Adoptante> obtenerAdoptantes() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Adoptante").getResultList();
    }

    public List<Animal> obtenerAdopciones() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Adopcion").getResultList();
    }

    public List<Animal> obtenerVeterinarios() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Veterinario").getResultList();
    }

    public List<Animal> obtenerCentrosVeterinarios() {
        return HibernateUtil.getCurrentSession().createQuery("FROM CentroVeterinario").getResultList();
    }

    public void nuevoRefugio(Refugio refugio) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(refugio);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void nuevoAnimal(Animal animal) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(animal);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void nuevoAdoptante(Adoptante adoptante) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(adoptante);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void nuevaAdopcion(Adopcion adopcion) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(adopcion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void nuevoVeterinario(Veterinario veterinario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(veterinario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void nuevoCentroVeterinario(CentroVeterinario centroVeterinario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(centroVeterinario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarRefugio(Refugio refugio) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(refugio);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarAnimal(Animal animal) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(animal);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarAdoptante(Adoptante adoptante) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(adoptante);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarAdopcion(Adopcion adopcion) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(adopcion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarVeterinario(Veterinario veterinario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(veterinario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarVeterinario(CentroVeterinario centroVeterinario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(centroVeterinario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarRefugio(Refugio refugio) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(refugio);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarAnimal(Animal animal) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(animal);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarAdoptante(Adoptante adoptante) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(adoptante);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarAdopcion(Adopcion adopcion) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(adopcion);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarVeterinario(Veterinario veterinario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(veterinario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarCentroVeterinario(CentroVeterinario centroVeterinario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(centroVeterinario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

}

