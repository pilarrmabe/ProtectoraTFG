package com.pilarmabe.base.mvc;

import com.pilarmabe.base.clases.*;
import com.pilarmabe.base.util.HibernateUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.time.ZoneId;
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

        try {
            // base de datos¿?            
            // refrescarSeccionRefugios();
            // refrescarSeccionAnimales();
            // refrescarSeccionAdoptantes();
            // refrescarSeccionAdopciones();
            // refrescarSeccionVeterinarios();
            // refrescarSeccionCentrosVeterinarios();
        } catch (Exception e) {
            System.out.println("Iniciando el programa...");
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

        vista.btnAniadirAdoptante.addActionListener(listener);
        vista.btnAniadirAdoptante.setActionCommand("NuevoAdoptante");
        vista.btnModAdoptante.addActionListener(listener);
        vista.btnModAdoptante.setActionCommand("ModificarAdoptante");
        vista.btnEliminarAdoptante.addActionListener(listener);
        vista.btnEliminarAdoptante.setActionCommand("EliminarAdoptante");

        vista.btnAniadirAdopcion.addActionListener(listener);
        vista.btnAniadirAdopcion.setActionCommand("NuevaAdopcion");
        vista.btnModAdopcion.addActionListener(listener);
        vista.btnModAdopcion.setActionCommand("ModificarAdopcion");
        vista.btnEliminarAdopcion.addActionListener(listener);
        vista.btnEliminarAdopcion.setActionCommand("EliminarAdopcion");

        vista.btnAniadirCentro.addActionListener(listener);
        vista.btnAniadirCentro.setActionCommand("NuevoCentro");
        vista.btnModCentro.addActionListener(listener);
        vista.btnModCentro.setActionCommand("ModificarCentro");
        vista.btnEliminarCentro.addActionListener(listener);
        vista.btnEliminarCentro.setActionCommand("EliminarCentro");

        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listRefugio.addListSelectionListener(listener);
        vista.listAnimal.addListSelectionListener(listener);
        vista.listAdoptante.addListSelectionListener(listener);
        vista.listAdopcion.addListSelectionListener(listener);
        vista.listVeterinario.addListSelectionListener(listener);
        vista.listCentro.addListSelectionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comando = actionEvent.getActionCommand();
        try {
            switch (comando) {
                case "Conectar": {
                    modelo.conectar();
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
                            vista.fechaAperturaRef.getText(),
                            vista.getFoto()
                    );
                    modelo.nuevoRefugio(refugio);
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
                        refugioSeleccionado.setFechaApertura(vista.fechaAperturaRef.getText());

                        modelo.modificarRefugio(refugioSeleccionado);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione un refugio para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }


                case "EliminarRefugio": {
                    Refugio refugio = vista.listRefugio.getSelectedValue();
                    modelo.eliminarRefugio(refugio);
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
                            vista.getFoto(),
                            (Refugio) vista.comboRefugioAnimal.getSelectedItem(),
                            (Veterinario) vista.comboVetAnimal.getSelectedItem()
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
                        seleccion.setFoto(vista.getFoto());
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
                            vista.txtObservacionAdopcion.getText(),
                            vista.getFoto()
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
                        seleccionada.setDocumentoAdopcion(vista.getFoto());
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
                        java.sql.Date.valueOf(vista.fechaRegistroAdoptante.getDate()), 
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
                        seleccion.setFechaRegistro(java.sql.Date.valueOf(vista.fechaRegistroAdoptante.getDate())); // Cambiado getFechaRegistro() a fechaRegistroAdoptante.getDate()
                        seleccion.setFoto(vista.getFoto()); // Sin cambios
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
                        seleccion.setFoto(vista.getFoto());
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
                        seleccion.setFotoCentro(vista.getFoto()); // Cambiado getFotoCentro() a getFoto()
                        modelo.modificarVeterinario(seleccion);
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
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (vista.listAnimal.getSelectedValue() != null) { // Cambiado listaAnimales a listAnimal
            Animal a = vista.listAnimal.getSelectedValue();
            vista.txtNombreAnimal.setText(a.getNombre()); // Cambiado txtNombre a txtNombreAnimal
            vista.comboEspecie.setSelectedItem(a.getEspecie()); // Cambiado txtEspecie a comboEspecie
            vista.comboRaza.setSelectedItem(a.getRaza()); // Cambiado txtRaza a comboRaza
            vista.comboSexo.setSelectedItem(a.getSexo()); // Cambiado txtSexo a comboSexo
            vista.fechaNacimAnimal.setDate(java.time.LocalDate.parse(a.getFechaNacimiento())); // Cambiado setFechaNacimiento a fechaNacimAnimal.setDate
            vista.fechaIngresoAnimal.setDate(java.time.LocalDate.parse(a.getFechaIngreso())); // Cambiado setFechaIngreso a fechaIngresoAnimal.setDate
            vista.txtEdadAnimal.setText(String.valueOf(a.getEdad())); // Cambiado txtEdad a txtEdadAnimal
            vista.pesoAnimal.setValue(a.getPeso()); // Cambiado txtPeso a pesoAnimal
            vista.txtSaludAnimal.setText(a.getSalud()); // Cambiado txtSalud a txtSaludAnimal
            vista.txtObservaciones.setText(a.getComportamiento()); // Cambiado txtComportamiento a txtObservaciones
            vista.txtNecesidades.setText(a.getNecesidades());
            vista.txtNumMicrochip.setText(a.getNumeroMicrochip()); // Cambiado txtNumeroMicrochip a txtNumMicrochip
            vista.comboRefugioAnimal.setSelectedItem(a.getRefugio()); // Cambiado comboRefugio a comboRefugioAnimal
            vista.comboVetAnimal.setSelectedItem(a.getVeterinario()); // Cambiado comboVeterinario a comboVetAnimal

        } else if (listSelectionEvent.getSource() == vista.listRefugio && vista.listRefugio.getSelectedValue() != null) {
            Refugio refugio = vista.listRefugio.getSelectedValue();
            vista.txtNombreRef.setText(refugio.getNombre());
            vista.txtDireccionRef.setText(refugio.getDireccion()); // Cambiado txtApellido a txtDireccionRef
            vista.txtCiudadRef.setText(refugio.getCiudad()); // Cambiado txtAniosExperiencia a txtCiudadRef

        } else if (vista.listAdopcion.getSelectedValue() != null) {
            Adopcion a = vista.listAdopcion.getSelectedValue();
            vista.comboAnimalAdopcion.setSelectedItem(a.getAnimal()); // Cambiado comboAnimal a comboAnimalAdopcion
            vista.comboAdoptanteAdopcion.setSelectedItem(a.getAdoptante()); // Cambiado comboAdoptante a comboAdoptanteAdopcion
            vista.fechaAdopcion.setDate(a.getFechaAdopcion().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()); // Cambiado setFechaAdopcion a fechaAdopcion.setDate
            vista.comboEstadoAdopcion.setSelectedItem(a.getEstadoAdopcion()); // Cambiado txtEstado a comboEstadoAdopcion
            vista.txtObservacionAdopcion.setText(a.getObservaciones()); // Cambiado txtObservaciones a txtObservacionAdopcion

        } else if (vista.listVeterinario.getSelectedValue() != null) { // Cambiado listaVeterinarios a listVeterinario
            Veterinario v = vista.listVeterinario.getSelectedValue();
            vista.txtNombreVet.setText(v.getNombre()); // Cambiado txtNombre a txtNombreVet
            vista.txtApellidosVet.setText(v.getApellidos()); // Cambiado txtApellidos a txtApellidosVet
            vista.txtTlfVet.setText(v.getTelefono()); // Cambiado txtTelefono a txtTlfVet
            vista.txtEmailVet.setText(v.getEmail()); // Cambiado txtEmail a txtEmailVet
            vista.comboEspecialidad.setSelectedItem(v.getEspecialidad()); // Cambiado txtEspecialidad a comboEspecialidad
            vista.aniosExperienciaVet.setValue(v.getAniosExperiencia()); // Cambiado txtAniosExperiencia a aniosExperienciaVet
            vista.comboCentroDelVet.setSelectedItem(v.getCentroVeterinario()); // Cambiado comboCentroVet a comboCentroDelVet
        }
    }




    // poner todos los combos que hay
    private void cargarCombos() {
        vista.comboAnimalAdopcion.removeAllItems();
        for (Animal a : modelo.obtenerAnimales()) {
            vista.comboAnimalAdopcion.addItem(a); // Asegúrate de que este JComboBox maneja objetos de tipo Animal
        }

        vista.comboAdoptanteAdopcion.removeAllItems();
        for (Adoptante ad : modelo.obtenerAdoptantes()) {
            vista.comboAdoptanteAdopcion.addItem(ad); // Asegúrate de que este JComboBox maneja objetos de tipo Adoptante
        }
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
            List<Animal> listarAnimales = modelo.obtenerAnimalesPorNombre(vista.txtBuscarAnimal.getText());
        } else if (e.getSource() == vista.txtBuscarRefugio) { 
            List<Refugio> listaRefugio = modelo.obtenerRefugiosPorNombre(vista.txtBuscarRefugio.getText());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

}
