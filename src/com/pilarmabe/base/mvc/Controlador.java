package com.pilarmabe.base.mvc;

import com.pilarmabe.base.clases.*;
import com.pilarmabe.base.clases.ValoresCombos.TipoUsuario;
import com.pilarmabe.base.util.HibernateUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener, KeyListener  {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
        addKeyListener(this);

        try {
            controlUsuarios();
            listarRefugios(null);
            listarCentros(null);
            listarAnimales(null);
            listarVeterinario(null);
            listarAdoptante(null);
            listarAdopcion(null);
            refrescarPanelUsuarios();
            cargarCombos();
            configurarComboRenderers();
            // listarRefugios(null);
            // base de datos¿?            
            // refrescarSeccionRefugios();
            // refrescarSeccionAnimales();
            // refrescarSeccionAdoptantes();
            // refrescarSeccionAdopciones();
            // refrescarSeccionVeterinarios();
            // refrescarSeccionCentrosVeterinarios();
        } catch (Exception e) {
            System.out.println("Error al iniciar programa");
        }
    }

    private void addActionListener(ActionListener listener) {
        vista.btnRefAñadir.addActionListener(listener);
        vista.btnRefAñadir.setActionCommand("NuevoRefugio");
        vista.btnRefModificar.addActionListener(listener);
        vista.btnRefModificar.setActionCommand("ModificarRefugio");
        vista.btnRefEliminar.addActionListener(listener);
        vista.btnRefEliminar.setActionCommand("EliminarRefugio");

        vista.btnAnimalAnadir.addActionListener(listener);
        vista.btnAnimalAnadir.setActionCommand("NuevoAnimal");
        vista.btnAnimalModificar.addActionListener(listener);
        vista.btnAnimalModificar.setActionCommand("ModificarAnimal");
        vista.btnAnimalEliminar.addActionListener(listener);
        vista.btnAnimalEliminar.setActionCommand("EliminarAnimal");
        vista.btnAnimalAnadir.addActionListener(listener);

        vista.btnAniadirCentro.setActionCommand("NuevoCentro");
        vista.btnAniadirCentro.addActionListener(listener);
        vista.btnModCentro.setActionCommand("ModificarCentro");
        vista.btnModCentro.addActionListener(listener);
        vista.btnEliminarCentro.setActionCommand("EliminarCentro");
        vista.btnEliminarCentro.addActionListener(listener);

        vista.btnUsuarioAnadir.setActionCommand("NuevoUsuario");
        vista.btnUsuarioAnadir.addActionListener(listener);
        vista.btnUsuarioModificar.setActionCommand("ModificarUsuario");
        vista.btnUsuarioModificar.addActionListener(listener);
        vista.btnUsuarioEliminar.setActionCommand("EliminarUsuario");
        vista.btnUsuarioEliminar.addActionListener(listener);

        vista.btnAniadirVet.setActionCommand("NuevoVeterinario");
        vista.btnAniadirVet.addActionListener(listener);
        vista.btnModVet.setActionCommand("ModificarVeterinario");
        vista.btnModVet.addActionListener(listener);
        vista.btnEliminarVet.setActionCommand("EliminarVeterinario");
        vista.btnEliminarVet.addActionListener(listener);

        vista.btnAniadirAdoptante.setActionCommand("NuevoAdoptante");
        vista.btnAniadirAdoptante.addActionListener(listener);
        vista.btnModAdoptante.setActionCommand("ModificarAdoptante");
        vista.btnModAdoptante.addActionListener(listener);
        vista.btnEliminarAdoptante.setActionCommand("EliminarAdoptante");
        vista.btnEliminarAdoptante.addActionListener(listener);

        vista.btnAniadirAdopcion.setActionCommand("NuevaAdopcion");
        vista.btnAniadirAdopcion.addActionListener(listener);
        vista.btnModAdopcion.setActionCommand("ModificarAdopcion");
        vista.btnModAdopcion.addActionListener(listener);
        vista.btnEliminarAdopcion.setActionCommand("EliminarAdopcion");
        vista.btnEliminarAdopcion.addActionListener(listener);

        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
    }

    private void controlUsuarios(){
        vista.btnRefAñadir.setVisible(false);
        vista.btnRefModificar.setVisible(false);
        vista.btnRefEliminar.setVisible(false);
        
        switch (modelo.getUsuario()) {
            case "Edición":
                vista.btnRefAñadir.setVisible(true);
                vista.btnRefModificar.setVisible(true);
                break;
            case "Administrador":
                vista.btnRefAñadir.setVisible(true);
                vista.btnRefModificar.setVisible(true);
                vista.btnRefEliminar.setVisible(true);
                break;
        }
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listRefugio.addListSelectionListener(listener);
        vista.listAnimal.addListSelectionListener(listener);
        vista.listAdoptante.addListSelectionListener(listener);
        vista.listAdopcion.addListSelectionListener(listener);
        vista.listVeterinario.addListSelectionListener(listener);
        vista.listCentro.addListSelectionListener(listener);
        vista.listUsuario.addListSelectionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.addWindowListener(listener);
    }

    private void addKeyListener(KeyListener listener) {
        vista.txtBuscarAnimal.addKeyListener(listener);
        vista.txtBuscarRefugio.addKeyListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comando = actionEvent.getActionCommand();
        try {
            switch (comando) {
                case "Conectar": {
                    modelo.conectar();
                    System.out.println("Conectado");
                    listarRefugios(null);
                    refrescarPanelUsuarios();
                }
                break;

                case "Salir": {
                    System.exit(0);
                }
                break;

                // refugio
                case "NuevoRefugio": {
                    Refugio refugio = new Refugio(
                        vista.txtNombreRef.getText(),
                        vista.txtDireccionRef.getText(),
                        vista.txtCiudadRef.getText(),
                        vista.txtCPRef.getText(),
                        vista.txtResponsable.getText(),
                        vista.txtTelfRef.getText(),
                        vista.txtEmailRef.getText(),
                        Integer.parseInt(vista.capacidadRef.getValue().toString()),
                        vista.fechaAperturaRef.getDate().toString(),
                        vista.getFoto()

                    );
                    modelo.nuevoRefugio(refugio);
                    refrescarPanelRefugios();
                    break;
                }

                case "ModificarRefugio": {
                    Refugio refugioSeleccionado = vista.listRefugio.getSelectedValue();
                    if (refugioSeleccionado != null) {
                        refugioSeleccionado.setNombre(vista.txtNombreRef.getText());
                        refugioSeleccionado.setDireccion(vista.txtDireccionRef.getText());
                        refugioSeleccionado.setCiudad(vista.txtCiudadRef.getText());
                        refugioSeleccionado.setCodigoPostal(vista.txtCPRef.getText());
                        refugioSeleccionado.setResponsable(vista.txtResponsable.getText());
                        refugioSeleccionado.setTelefono(vista.txtTelfRef.getText());
                        refugioSeleccionado.setEmail(vista.txtEmailRef.getText());
                        refugioSeleccionado.setCapacidad(Integer.parseInt(vista.capacidadRef.getValue().toString()));
                        refugioSeleccionado.setFechaApertura(vista.fechaAperturaRef.getDate().toString());

                        modelo.modificarRefugio(refugioSeleccionado);
                        refrescarPanelRefugios();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione un refugio para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }


                case "EliminarRefugio": {
                    Refugio refugio = vista.listRefugio.getSelectedValue();
                    modelo.eliminarRefugio(refugio);
                    refrescarPanelRefugios();
                    break;
                }

                // animal
                case "NuevoAnimal": {
                    Animal animal = new Animal(
                            vista.txtNombreAnimal.getText(),
                            vista.comboEspecie.getSelectedItem().toString(),
                            vista.comboRaza.getSelectedItem().toString(),
                            vista.comboSexo.getSelectedItem().toString(),
                            vista.fechaNacimAnimal.getDate().toString(),
                            vista.fechaIngresoAnimal.getDate().toString(),
                            Integer.parseInt(vista.txtEdadAnimal.getText()),
                            Double.parseDouble(vista.pesoAnimal.getValue().toString()),
                            vista.txtSaludAnimal.getText(),
                            vista.txtObservaciones.getText(),
                            vista.txtNecesidades.getText(),
                            vista.txtNumMicrochip.getText(),
                            (Refugio) vista.comboRefugioAnimal.getSelectedItem(),
                            (Veterinario) vista.comboVetAnimal.getSelectedItem(),
                            vista.getFoto()
                    );
                    modelo.nuevoAnimal(animal);
                    break;
                }
                case "ModificarAnimal": {
                    Animal seleccion = vista.listAnimal.getSelectedValue();
                    if (seleccion != null) {
                        seleccion.setNombre(vista.txtNombreAnimal.getText());
                        seleccion.setEspecie(vista.comboEspecie.getSelectedItem().toString());
                        seleccion.setRaza(vista.comboRaza.getSelectedItem().toString());
                        seleccion.setSexo(vista.comboSexo.getSelectedItem().toString());
                        seleccion.setFechaNacimiento(vista.fechaNacimAnimal.getDate().toString());
                        seleccion.setFechaIngreso(vista.fechaIngresoAnimal.getDate().toString());
                        seleccion.setEdad(Integer.parseInt(vista.txtEdadAnimal.getText()));
                        seleccion.setPeso(Double.parseDouble(vista.pesoAnimal.getValue().toString()));
                        seleccion.setSalud(vista.txtSaludAnimal.getText());
                        seleccion.setComportamiento(vista.txtObservaciones.getText());
                        seleccion.setNecesidades(vista.txtNecesidades.getText());
                        seleccion.setNumeroMicrochip(vista.txtNumMicrochip.getText());
                        // seleccion.setFoto(vista.getFoto());
                        seleccion.setRefugio((Refugio) vista.comboRefugioAnimal.getSelectedItem());
                        seleccion.setVeterinario((Veterinario) vista.comboVetAnimal.getSelectedItem());
                        modelo.modificarAnimal(seleccion);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione un animal para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case "EliminarAnimal": {
                    Animal seleccion = vista.listAnimal.getSelectedValue();
                    modelo.eliminarAnimal(seleccion);
                    break;
                }

                // adopcion
                case "NuevaAdopcion": {
                    Adopcion adopcion = new Adopcion(
                            (Animal) vista.comboAnimalAdopcion.getSelectedItem(),
                            (Adoptante) vista.comboAdoptanteAdopcion.getSelectedItem(),
                            Date.from(vista.fechaAdopcion.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            vista.comboEstadoAdopcion.getSelectedItem().toString(),
                            vista.txtObservacionAdopcion.getText()

                    );
                    modelo.nuevaAdopcion(adopcion);
                    break;
                }
                case "ModificarAdopcion": {
                    Adopcion seleccionada = vista.listAdopcion.getSelectedValue();
                    if (seleccionada != null) {
                        seleccionada.setAnimal((Animal) vista.comboAnimalAdopcion.getSelectedItem());
                        seleccionada.setAdoptante((Adoptante) vista.comboAdoptanteAdopcion.getSelectedItem());
                        seleccionada.setFechaAdopcion(Date.from(vista.fechaAdopcion.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                        seleccionada.setEstadoAdopcion(vista.comboEstadoAdopcion.getSelectedItem().toString());
                        seleccionada.setObservaciones(vista.txtObservacionAdopcion.getText());
                        modelo.modificarAdopcion(seleccionada);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione una adopción para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case "EliminarAdopcion": {
                    Adopcion seleccionada = vista.listAdopcion.getSelectedValue();
                    if (seleccionada != null) {
                        modelo.eliminarAdopcion(seleccionada);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione una adopción para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }

                // adoptante
                case "NuevoAdoptante": {
                    Adoptante adoptante = new Adoptante(
                        vista.txtNombreAdoptante.getText(),
                        vista.txtApellidosAdoptante.getText(),
                        vista.dniAdoptante.getText(),
                        vista.txtDireccAdoptante.getText(),
                        vista.txtTelfAdoptante.getText(),
                        vista.txtEmailAdoptante.getText(),
                        vista.fechaRegistroAdoptante.getDate().toString(),
                        vista.getFoto()
                    );
                    modelo.nuevoAdoptante(adoptante);
                    break;
                }
                case "ModificarAdoptante": {
                    Adoptante seleccion = vista.listAdoptante.getSelectedValue();
                    if (seleccion != null) {
                        seleccion.setNombre(vista.txtNombreAdoptante.getText()); // Cambiado txtNombre a txtNombreAdoptante
                        seleccion.setApellidos(vista.txtApellidosAdoptante.getText()); // Cambiado txtApellidos a txtApellidosAdoptante
                        seleccion.setDni(vista.dniAdoptante.getText()); // Cambiado txtDNI a dniAdoptante
                        seleccion.setDireccion(vista.txtDireccAdoptante.getText()); // Cambiado txtDireccion a txtDireccAdoptante
                        seleccion.setTelefono(vista.txtTelfAdoptante.getText()); // Cambiado txtTelefono a txtTelfAdoptante
                        seleccion.setEmail(vista.txtEmailAdoptante.getText()); // Cambiado txtEmail a txtEmailAdoptante
                        seleccion.setFechaRegistro(vista.fechaRegistroAdoptante.getDate().toString()); // Cambiado getFechaRegistro() a fechaRegistroCentro.getDate().toString()
                        // seleccion.setFoto(vista.getFoto()); 
                        modelo.modificarAdoptante(seleccion);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione un adoptante para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case "EliminarAdoptante": {
                    Adoptante seleccion = vista.listAdoptante.getSelectedValue();
                    modelo.eliminarAdoptante(seleccion);
                    break;
                }

                // veterinario
                case "NuevoVeterinario": {
                    Veterinario vet = new Veterinario(
                            vista.txtNombreVet.getText(),
                            vista.txtApellidosVet.getText(),
                            vista.txtTlfVet.getText(),
                            vista.txtEmailVet.getText(),
                            vista.comboEspecialidad.getSelectedItem().toString(),
                            Integer.parseInt(vista.aniosExperienciaVet.getValue().toString()),
                            vista.getFoto(),
                            (CentroVeterinario) vista.comboCentroDelVet.getSelectedItem()
                    );
                    modelo.nuevoVeterinario(vet);
                    break;
                }
                case "ModificarVeterinario": {
                    Veterinario seleccion = vista.listVeterinario.getSelectedValue();
                    if (seleccion != null) {
                        seleccion.setNombre(vista.txtNombreVet.getText());
                        seleccion.setApellidos(vista.txtApellidosVet.getText());
                        seleccion.setTelefono(vista.txtTlfVet.getText());
                        seleccion.setEmail(vista.txtEmailVet.getText());
                        seleccion.setEspecialidad(vista.comboEspecialidad.getSelectedItem().toString());
                        seleccion.setAniosExperiencia(Integer.parseInt(vista.aniosExperienciaVet.getValue().toString()));
                        // seleccion.setFoto(vista.getFoto());
                        seleccion.setCentroVeterinario((CentroVeterinario) vista.comboCentroDelVet.getSelectedItem());
                        modelo.modificarVeterinario(seleccion);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione un veterinario para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case "EliminarVeterinario": {
                    Veterinario seleccion = vista.listVeterinario.getSelectedValue();
                    modelo.eliminarVeterinario(seleccion);
                    break;
                }

                // centro veterinario
                case "NuevoCentro": {
                    CentroVeterinario centro = new CentroVeterinario(
                        vista.txtNombreCentro.getText(),
                        vista.txtDireccionCentro.getText(),
                        vista.txtCpCentro.getText(),
                        vista.txtTelfCentro.getText(),
                        vista.txtEmailCentro.getText(),
                        vista.fechaRegistroCentro.getDate().toString(),
                        vista.urgenciasCentro.isSelected(),
                        vista.getFoto()
                    );
                    modelo.nuevoCentroVeterinario(centro);
                    break;
                }
                case "ModificarCentro": {
                    CentroVeterinario seleccion = vista.listCentro.getSelectedValue();
                    if (seleccion != null) {
                        seleccion.setNombre(vista.txtNombreCentro.getText()); // Cambiado txtNombre a txtNombreCentro
                        seleccion.setDireccion(vista.txtDireccionCentro.getText()); // Cambiado txtDireccion a txtDireccionCentro
                        seleccion.setCodigoPostal(vista.txtCpCentro.getText()); // Cambiado txtCodigoPostal a txtCpCentro
                        seleccion.setTelefono(vista.txtTelfCentro.getText()); // Cambiado txtTelefono a txtTelfCentro
                        seleccion.setEmail(vista.txtEmailCentro.getText()); // Cambiado txtEmail a txtEmailCentro
                        seleccion.setFechaRegistro(vista.fechaRegistroCentro.getDate().toString()); // Cambiado getFechaRegistro() a fechaRegistroCentro.getDate().toString()
                        seleccion.setServicioUrgencias(vista.urgenciasCentro.isSelected()); // Cambiado chkServicioUrgencias a urgenciasCentro
                        //seleccion.setFotoCentro(vista.getFoto()); // Cambiado getFotoCentro() a getFoto()
                        modelo.modificarVeterinario(seleccion);
                        refrescarPanelCentros();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione un centro veterinario para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case "EliminarCentro": {
                    CentroVeterinario seleccion = vista.listCentro.getSelectedValue();
                    modelo.eliminarCentroVeterinario(seleccion);
                    break;
                }

                // Usuario
                case "NuevoUsuario": {
                    System.out.println("Nuevo Usuario");
                    Usuario usuario = new Usuario(
                        vista.txtNombre.getText(),
                        vista.txtPass.getText(),
                        vista.comboTipoUsuario.getSelectedItem().toString()
                    );
                    System.out.println("Usuario creado"+usuario);
                    modelo.nuevoUsuario(usuario);
                    refrescarPanelUsuarios();
                    break;
                }
                case "ModificarUsuario": {
                    Usuario usuario = vista.listUsuario.getSelectedValue();
                    if (usuario != null) {
                        usuario.setNombre(vista.txtNombre.getText()); // Cambiado txtNombre a txtNombreCentro
                        usuario.setPass(vista.txtPass.getText()); // Cambiado txtDireccion a txtDireccionCentro
                        usuario.setTipoUsuario(vista.comboTipoUsuario.getSelectedItem().toString()); // Cambiado txtCodigoPostal a txtCpCentro
                        modelo.modificarUsuario(usuario);
                        refrescarPanelUsuarios();

                    }
                    break;
                }
                case "EliminarUsuario": {
                    Usuario usuario = vista.listUsuario.getSelectedValue();
                    modelo.eliminarUsuario(usuario);
                    refrescarPanelUsuarios();
                    break;
                }

            }
            // base de datos?
            // refrescarSeccionRefugios();
            // refrescarSeccionAnimales();
            // refrescarSeccionAdopciones();
            // refrescarSeccionAdoptantes();
            // refrescarSeccionVeterinarios();
            // refrescarSeccionCentrosVeterinarios();

        } catch (Exception e) {
            HibernateUtil.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }

    }

   @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vista.listAnimal && vista.listAnimal.getSelectedValue() != null) {
            rellenarAnimal(vista.listAnimal.getSelectedValue());
        } else if (e.getSource() == vista.listRefugio && vista.listRefugio.getSelectedValue() != null) {
            rellenarRefugio(vista.listRefugio.getSelectedValue());
        } else if (e.getSource() == vista.listAdopcion && vista.listAdopcion.getSelectedValue() != null) {
            rellenarAdopcion(vista.listAdopcion.getSelectedValue());
        } else if (e.getSource() == vista.listVeterinario && vista.listVeterinario.getSelectedValue() != null) {
            rellenarVeterinario(vista.listVeterinario.getSelectedValue());
        } else if (e.getSource() == vista.listUsuario && vista.listUsuario.getSelectedValue() != null) {
            rellenarUsuario(vista.listUsuario.getSelectedValue());
        } else if (e.getSource() == vista.listCentro && vista.listCentro.getSelectedValue() != null) {
            rellenarCentro(vista.listCentro.getSelectedValue());
        } else if (e.getSource() == vista.listAdoptante && vista.listAdoptante.getSelectedValue() != null) {
            rellenarAdoptante(vista.listAdoptante.getSelectedValue());
        }
    }

    private void rellenarAnimal(Animal a) {
        vista.txtNombreAnimal.setText(a.getNombre());
        vista.comboEspecie.setSelectedItem(a.getEspecie());
        vista.comboRaza.setSelectedItem(a.getRaza());
        vista.comboSexo.setSelectedItem(a.getSexo());
        vista.fechaNacimAnimal.setDate(LocalDate.parse(a.getFechaNacimiento()));
        vista.fechaIngresoAnimal.setDate(LocalDate.parse(a.getFechaIngreso()));
        vista.txtEdadAnimal.setText(String.valueOf(a.getEdad()));
        vista.pesoAnimal.setValue(a.getPeso());
        vista.txtSaludAnimal.setText(a.getSalud());
        vista.txtObservaciones.setText(a.getComportamiento());
        vista.txtNecesidades.setText(a.getNecesidades());
        vista.txtNumMicrochip.setText(a.getNumeroMicrochip());
        vista.comboRefugioAnimal.setSelectedItem(a.getRefugio());
        vista.comboVetAnimal.setSelectedItem(a.getVeterinario());
        vista.animalLogo.setIcon(new ImageIcon(getImagen(a.getLogoAnimal())));
    }

    private void rellenarRefugio(Refugio refugio) {
        vista.txtNombreRef.setText(refugio.getNombre());
        vista.txtDireccionRef.setText(refugio.getDireccion());
        vista.txtCiudadRef.setText(refugio.getCiudad());
        vista.txtCPRef.setText(refugio.getCodigoPostal());
        vista.txtResponsable.setText(refugio.getResponsable());
        vista.txtTelfRef.setText(refugio.getTelefono());
        vista.txtEmailRef.setText(refugio.getEmail());
        vista.capacidadRef.setValue(refugio.getCapacidad());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaApertura = LocalDate.parse(refugio.getFechaApertura(), formatter);
        vista.fechaAperturaRef.setDate(fechaApertura);
        vista.refugioLogo.setIcon(new ImageIcon(getImagen(refugio.getLogoRefugio())));

    }

    private void rellenarAdopcion(Adopcion a) {
        vista.comboAnimalAdopcion.setSelectedItem(a.getAnimal());
        vista.comboAdoptanteAdopcion.setSelectedItem(a.getAdoptante());
        vista.fechaAdopcion.setDate(a.getFechaAdopcion().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        vista.comboEstadoAdopcion.setSelectedItem(a.getEstadoAdopcion());
        vista.txtObservacionAdopcion.setText(a.getObservaciones());
    }

    private void rellenarVeterinario(Veterinario v) {
        vista.txtNombreVet.setText(v.getNombre());
        vista.txtApellidosVet.setText(v.getApellidos());
        vista.txtTlfVet.setText(v.getTelefono());
        vista.txtEmailVet.setText(v.getEmail());
        vista.comboEspecialidad.setSelectedItem(v.getEspecialidad());
        vista.aniosExperienciaVet.setValue(v.getAniosExperiencia());
        vista.comboCentroDelVet.setSelectedItem(v.getCentroVeterinario());
        vista.fotoVeterinario.setIcon(new ImageIcon(getImagen(v.getFoto())));
    }

    private void rellenarUsuario(Usuario v) {
        vista.txtNombre.setText(v.getNombre());
        vista.txtPass.setText(v.getPass());
        ValoresCombos.TipoUsuario tipoUsuario = ValoresCombos.TipoUsuario.valueOf(v.getTipoUsuario().toUpperCase());
        vista.comboTipoUsuario.setSelectedItem(tipoUsuario);
    }

    private void rellenarCentro(CentroVeterinario v) {
        vista.txtNombreCentro.setText(v.getNombre());
        vista.txtDireccionCentro.setText(v.getDireccion());
        vista.txtCpCentro.setText(v.getCodigoPostal());
        vista.txtTelfCentro.setText(v.getTelefono());
        vista.txtEmailCentro.setText(v.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaRegistro = LocalDate.parse(v.getFechaRegistro(), formatter);
        vista.fechaRegistroCentro.setDate(fechaRegistro);
        vista.fotoCentro.setIcon(new ImageIcon(getImagen(v.getFotoCentro())));
    }

    private void rellenarAdoptante(Adoptante adoptante) {
        vista.txtNombreAdoptante.setText(adoptante.getNombre());
        vista.txtApellidosAdoptante.setText(adoptante.getApellidos());
        vista.dniAdoptante.setText(adoptante.getDni());
        vista.txtDireccAdoptante.setText(adoptante.getDireccion());
        vista.txtTelfAdoptante.setText(adoptante.getTelefono());
        vista.txtEmailAdoptante.setText(adoptante.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaRegistro = LocalDate.parse(adoptante.getFechaRegistro(), formatter);
        vista.fechaRegistroAdoptante.setDate(fechaRegistro);
        vista.fotoAdoptante.setIcon(new ImageIcon(getImagen(adoptante.getFoto())));
    }




    // poner todos los combos que hay
    private void cargarCombos() {
        vista.comboTipoUsuario.removeAllItems();
        for (ValoresCombos.TipoUsuario tipo : ValoresCombos.TipoUsuario.values()) {
            vista.comboTipoUsuario.addItem(tipo); 
        }

        vista.comboEspecie.removeAllItems();
        for (ValoresCombos.Especie tipo : ValoresCombos.Especie.values()) {
            vista.comboEspecie.addItem(tipo); 
        }

        vista.comboRaza.removeAllItems();
        for (ValoresCombos.Raza tipo : ValoresCombos.Raza.values()) {
            vista.comboRaza.addItem(tipo); 
        }

        vista.comboSexo.removeAllItems();
        for (ValoresCombos.Sexo tipo : ValoresCombos.Sexo.values()) {
            vista.comboSexo.addItem(tipo); 
        }

        vista.comboEstadoAdopcion.removeAllItems();
        for (ValoresCombos.Estado tipo : ValoresCombos.Estado.values()) {
            vista.comboEstadoAdopcion.addItem(tipo); 
        }

        vista.comboEspecialidad.removeAllItems();
        for (ValoresCombos.Especialidad tipo : ValoresCombos.Especialidad.values()) {
            vista.comboEspecialidad.addItem(tipo); 
        }

        vista.comboAnimalAdopcion.removeAllItems();
        for (Animal a : modelo.obtenerAnimales(false)) {
            vista.comboAnimalAdopcion.addItem(a); 
        }

        vista.comboCentroDelVet.removeAllItems();
        for (CentroVeterinario a : modelo.obtenerCentrosVeterinarios(false)) {
            vista.comboCentroDelVet.addItem(a); 
        }

        vista.comboAdoptanteAdopcion.removeAllItems();
        for (Adoptante ad : modelo.obtenerAdoptantes()) {
            vista.comboAdoptanteAdopcion.addItem(ad);
        }

        vista.comboRefugioAnimal.removeAllItems();
        for (Refugio ad : modelo.obtenerRefugios(true)) {
            vista.comboRefugioAnimal.addItem(ad);
        }

        vista.comboVetAnimal.removeAllItems();
        for (Veterinario vet : modelo.obtenerVeterinarios(true)) {
            vista.comboVetAnimal.addItem(vet);
        }

        vista.comboAdoptanteAnimal.removeAllItems();
        for (ValoresCombos.noAdoptante tipo : ValoresCombos.noAdoptante.values()) {
            vista.comboAdoptanteAnimal.addItem(tipo); 
        }
        for (Adoptante ad : modelo.obtenerAdoptantes()) {
            vista.comboAdoptanteAnimal.addItem(ad.getNombre());
        }

        vista.comboAnimalAdopcion.removeAllItems();
        for (Animal ad : modelo.obtenerAnimales(false)) {
            vista.comboAnimalAdopcion.addItem(ad);
        }
    }

    private void configurarComboRenderers() {
    // Adoptante
    vista.comboAdoptanteAdopcion.setRenderer(new DefaultListCellRenderer() {
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Adoptante) {
                value = ((Adoptante) value).getNombre();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    });

    // Refugio
    vista.comboRefugioAnimal.setRenderer(new DefaultListCellRenderer() {
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Refugio) {
                value = ((Refugio) value).getNombre();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    });

    // Veterinario (nombre y apellidos)
    vista.comboVetAnimal.setRenderer(new DefaultListCellRenderer() {
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Veterinario) {
                Veterinario v = (Veterinario) value;
                value = v.getNombre() + " " + v.getApellidos();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    });

    // Centro Veterinario
    vista.comboCentroDelVet.setRenderer(new DefaultListCellRenderer() {
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof CentroVeterinario) {
                value = ((CentroVeterinario) value).getNombre();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    });

    // Animal (solo nombre)
    vista.comboAnimalAdopcion.setRenderer(new DefaultListCellRenderer() {
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Animal) {
                value = ((Animal) value).getNombre();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    });
}

    private void refrescarPanelRefugios(){
        modelo.obtenerRefugios(true);
        listarRefugios(null);
    }

    private void refrescarPanelUsuarios(){
        modelo.obtenerUsuarios(true);
        listarUsuarios(null);
    }

    private void refrescarPanelCentros(){
        modelo.obtenerCentrosVeterinarios(true);
        listarCentros(null);
    }

    private void refrescarPanelAnimales(){
        modelo.obtenerAnimales(true);
        listarAnimales(null);
    }

    private void listarRefugios(String nombre) {
        List<Refugio> refugios = modelo.obtenerRefugios(false);
        vista.dlmRefugios.clear();
        if (nombre != null && !nombre.isEmpty() && !nombre.equals("")) {
            refugios = modelo.obtenerRefugiosPorNombre(nombre);
        }
        
        for(Refugio refugio : refugios){
            vista.dlmRefugios.addElement(refugio);
        }
    }

    private void listarVeterinario(String nombre) {
        List<Veterinario> veterinarios = modelo.obtenerVeterinarios(false);
        vista.dlmVeterinarios.clear();
        if (nombre != null && !nombre.isEmpty() && !nombre.equals("")) {
            veterinarios = modelo.obtenerVeterinariosPorNombre(nombre);
        }
        
        for(Veterinario veterinario : veterinarios){
            vista.dlmVeterinarios.addElement(veterinario);
        }
    }

    private void listarUsuarios(String nombre) {
        List<Usuario> listaUsuarios = modelo.obtenerUsuarios(false);
        vista.dlmUsuarios.clear();
        if (nombre != null && !nombre.isEmpty() && !nombre.equals("")) {
            // usuarios = modelo.obtenerUsuariosPorNombre(nombre);
        }
        
        for(Usuario usuario : listaUsuarios){
            vista.dlmUsuarios.addElement(usuario);
        }
    }

    private void listarAnimales(String nombre) {
        List<Animal> animales = modelo.obtenerAnimales(false);
        vista.dlmAnimales.clear();
        if (nombre != null && !nombre.isEmpty() && !nombre.equals("")) {
            animales = modelo.obtenerAnimalesPorNombre(nombre);
        }

        for(Animal animal : animales){
            vista.dlmAnimales.addElement(animal);
        }
    }

    private void listarCentros(String nombre) {
        List<CentroVeterinario> centros = modelo.obtenerCentrosVeterinarios(false);
        vista.dlmCentros.clear();
        if (nombre != null && !nombre.isEmpty() && !nombre.equals("")) {
            centros = modelo.obtenerCentrosPorNombre(nombre);
        }

        for(CentroVeterinario centro : centros){
            vista.dlmCentros.addElement(centro);
        }
    }

    private void listarAdoptante(String nombre) {
        List<Adoptante> adoptantes = modelo.obtenerAdoptantes();
        vista.dlmAdoptantes.clear();
        if (nombre != null && !nombre.isEmpty() && !nombre.equals("")) {
            // adoptantes = modelo.obtenerAdoptantesPorNombre(nombre);
        }
        
        for(Adoptante adoptante : adoptantes){
            vista.dlmAdoptantes.addElement(adoptante);
        }
    }

    private void listarAdopcion(String nombre) {
        List<Adopcion> adopciones = modelo.obtenerAdopciones();
        vista.dlmAdopcion.clear();
        if (nombre != null && !nombre.isEmpty() && !nombre.equals("")) {
            // adopciones = modelo.obtenerAdopcionesPorNombre(nombre);
        }
        
        for(Adopcion adopcion : adopciones){
            vista.dlmAdopcion.addElement(adopcion);
        }
    }

    private Image getImagen(String logo) {
        if(logo == null || logo.isEmpty()) {
            BufferedImage img = new BufferedImage(120, 120, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, 120, 120);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Sin imagen", 20, 60);
            g2d.dispose();
            return img.getScaledInstance(vista.refugioLogo.getWidth(), vista.refugioLogo.getHeight(), Image.SCALE_SMOOTH);
        }
        String rutaImagen = System.getProperty("user.dir") + java.io.File.separator +
            "src" + java.io.File.separator +
            "com" + java.io.File.separator +
            "pilarmabe" + java.io.File.separator +
            "base" + java.io.File.separator +
            "img" + java.io.File.separator +
            logo;

        ImageIcon icon = new ImageIcon(rutaImagen);
        // Escalar la imagen al tamaño del JLabel si lo necesitas:
        Image imagen = icon.getImage().getScaledInstance(vista.refugioLogo.getWidth(), vista.refugioLogo.getHeight(), Image.SCALE_SMOOTH);
        return imagen;
    }

    /**
     *  Método invocado al cerrar la ventana
     *  Se usa para desconectar
     * @param windowEvent
     */

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        modelo.desconectar();
        System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscarAnimal) { 
            listarAnimales(vista.txtBuscarRefugio.getText());
        } else if (e.getSource() == vista.txtBuscarRefugio) {
            listarRefugios(vista.txtBuscarRefugio.getText());
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

}
