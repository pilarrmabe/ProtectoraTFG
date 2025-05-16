package com.pilarmabe.base.mvc;

import com.pilarmabe.base.clases.*;
import com.pilarmabe.base.util.HibernateUtil;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {
    private static List<Refugio> listaRefugios;
    private static List<Animal> listaAnimales;
    private static List<Usuario> listaUsuarios;
    private static List<CentroVeterinario> listaCentroVeterinario;
    public String usuario = null;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
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

    public void loginUsuario(String usuario, String password){
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            Usuario usuarioLogueado = (Usuario) HibernateUtil.getCurrentSession()
                    .createQuery("FROM Usuario WHERE nombre = :nombre AND pass = :pass")
                    .setParameter("nombre", usuario)
                    .setParameter("pass", password)
                    .uniqueResult();
                    
            if (usuarioLogueado != null) {
                setUsuario(usuarioLogueado.getTipoUsuario());
                // System.out.println("Usuario logueado: " + this.usuario);
            }
            HibernateUtil.getCurrentSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void desconectar(){
        HibernateUtil.closeSessionFactory();
    }

    public List<Refugio> obtenerRefugios(Boolean refrescar) {
        if (listaRefugios == null || refrescar) {
            System.out.println("iniciando lista de refugios");
            listaRefugios =  HibernateUtil.getCurrentSession().createQuery("FROM Refugio").getResultList();
            System.out.println("lista de refugios: " + listaRefugios);
        }
        return listaRefugios;
    }

    public List<Usuario> obtenerUsuarios(Boolean refrescar) {
        if (listaUsuarios == null || refrescar) {
            System.out.println("iniciando lista de usuarios");
            listaUsuarios =  HibernateUtil.getCurrentSession().createQuery("FROM Usuario").getResultList();
            System.out.println("lista de usuarios: " + listaUsuarios);
        }
        return listaUsuarios;
    }

    public List<Refugio> obtenerRefugiosPorNombre(String nombre){
        List<Refugio> listaRefugiosFiltrados = new ArrayList<>();
        for(Refugio refugio : listaRefugios) {
            if (refugio.getNombre().contains(nombre)) {
                listaRefugiosFiltrados.add(refugio);
            }
        }
        return listaRefugiosFiltrados;

    }

    public List<Animal> obtenerAnimales() {
        if (listaAnimales == null) {
            listaAnimales = HibernateUtil.getCurrentSession().createQuery("FROM Animal").getResultList();
        }
        return listaAnimales;
    }

    public List<Animal> obtenerAnimalesPorNombre(String nombre) {
        List<Animal> listaAnimalesFiltrados = new ArrayList<>();
        for(Animal animal : listaAnimales) {
            if (animal.getNombre().contains(nombre)) {
                listaAnimalesFiltrados.add(animal);
            }
        }
        return listaAnimalesFiltrados;
    }

    public List<Adoptante> obtenerAdoptantes() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Adoptante").getResultList();
    }

    public List<Animal> obtenerAdopciones() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Adopcion").getResultList();
    }

    public List<Veterinario> obtenerVeterinarios() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Veterinario").getResultList();
    }

    public List<CentroVeterinario> obtenerCentrosVeterinarios(Boolean refrescar) {
        if (listaUsuarios == null || refrescar) {
            System.out.println("iniciando lista de centros veterinarios");
            listaCentroVeterinario =  HibernateUtil.getCurrentSession().createQuery("FROM CentroVeterinario").getResultList();
            System.out.println("lista de centros veterinarios: " + listaCentroVeterinario);
        }
        return listaCentroVeterinario;
    }

    public List<CentroVeterinario> obtenerCentrosPorNombre(String nombre){
        List<CentroVeterinario> listaCentrosFiltrados = new ArrayList<>();
        for(CentroVeterinario centro : listaCentroVeterinario) {
            if (centro.getNombre().contains(nombre)) {
                listaCentrosFiltrados.add(centro);
            }
        }
        return listaCentrosFiltrados;

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

    public void nuevoUsuario(Usuario usuario) {
        for(Usuario u : listaUsuarios) {
            if (u.getNombre().equals(usuario.getNombre())) {
                JFrame loginFrame = new JFrame("Iniciar sesión");
                JOptionPane.showMessageDialog(loginFrame, "El usuario introducido ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(usuario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarRefugio(Refugio refugio) {
        System.out.println("Modificando refugio: " + refugio);
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

    public void modificarUsuario(Usuario usuario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(usuario);
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

    public void eliminarUsuario(Usuario usuario) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(usuario);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

}

