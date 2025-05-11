package com.pilarmabe.base.mvc;

import com.pilarmabe.base.clases.Adopcion;
import com.pilarmabe.base.clases.Animal;
import com.pilarmabe.base.clases.Refugio;
import com.pilarmabe.base.util.HibernateUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.util.List;

public class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {

        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addListSelecctionListener(this);
        addWindowListener(this);

        try {
            refrescarSeccionRefugios();
            refrescarSeccionAnimales();
            refrescarSeccionAdoptantes();
            refrescarSeccionAdopciones();
            refrescarSeccionVeterinarios();
            refrescarSeccionCentrosVeterinarios();

        }catch (Exception e){
            System.out.println("Inicando el programa...");
        }

    }

    private void addActionListener(ActionListener listener){
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
        vista.btnAnimalAnadir.setActionCommand("NuevoAdoptante");
        vista.btnAnimalModificar.addActionListener(listener);
        vista.btnAnimalModificar.setActionCommand("ModificarAdoptante");
        vista.btnAnimalEliminar.addActionListener(listener);
        vista.btnAnimalEliminar.setActionCommand("EliminarAdoptante");

        vista.btnAnimalAnadir.addActionListener(listener);
        vista.btnAnimalAnadir.setActionCommand("NuevaAdopcion");
        vista.btnAnimalModificar.addActionListener(listener);
        vista.btnAnimalModificar.setActionCommand("ModificarAdopcion");
        vista.btnAnimalEliminar.addActionListener(listener);
        vista.btnAnimalEliminar.setActionCommand("EliminarAdopcion");

        vista.btnAnimalAnadir.addActionListener(listener);
        vista.btnAnimalAnadir.setActionCommand("NuevoVeterinario");
        vista.btnAnimalModificar.addActionListener(listener);
        vista.btnAnimalModificar.setActionCommand("ModificarVeterinario");
        vista.btnAnimalEliminar.addActionListener(listener);
        vista.btnAnimalEliminar.setActionCommand("EliminarVeterinario");

        vista.btnAnimalAnadir.addActionListener(listener);
        vista.btnAnimalAnadir.setActionCommand("NuevoCentroVeterinario");
        vista.btnAnimalModificar.addActionListener(listener);
        vista.btnAnimalModificar.setActionCommand("ModificarCentroVeterinario");
        vista.btnAnimalEliminar.addActionListener(listener);
        vista.btnAnimalEliminar.setActionCommand("EliminarCentroVeterinario");

        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
    }

    private void addListSelecctionListener(ListSelectionListener listener){
        vista.listRefugio.addListSelectionListener(listener);
        vista.listAnimal.addListSelectionListener(listener);
        vista.listAdoptante.addListSelectionListener(listener);
        vista.listAdopcion.addListSelectionListener(listener);
        vista.listVeterinario.addListSelectionListener(listener);
        vista.listCentro.addListSelectionListener(listener);
    }

    private void addKeyListener(KeyListener listener) {
        vista.txtBuscarAnimal.addKeyListener(listener);
        vista.txtBuscarRefugio.addKeyListener(listener);
        vista.txtBuscarAdopcion.addKeyListener(listener);
        vista.txtBuscarAdoptante.addKeyListener(listener);
        vista.txtBuscarVeterinario.addKeyListener(listener);
        vista.txtBuscarCentro.addKeyListener(listener);
    }

    private void addWindowListener(WindowListener listener){
        vista.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comando = actionEvent.getActionCommand();
        try {


        switch(comando){
            case "Conectar":{
                modelo.conectar();
            }
            break;

            case "Salir":{
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
                        Integer.parseInt(vista.capacidadRef.getText()),
                        vista.fechaAperturaRef.getText()
                );
                modelo.nuevoRefugio(refugio);
                break;
            }
            break;

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
                    refugioSeleccionado.setCapacidad(vista.capacidadRef.getText());
                    refugioSeleccionado.setFechaApertura(vista.fechaAperturaRef.getText());

                    modelo.modificarRefugio(refugioSeleccionado);
                } else {
                    JOptionPane.showMessageDialog(vista, "Seleccione un refugio para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            break;


            case "EliminarRefugio":{
                Refugio refugio = vista.listRefugio.getSelectedValue();
                modelo.eliminarRefugio(refugio);
            }
            break;

            // animal
            case "NuevoAnimal": {
                Animal animal = new Animal(
                        vista.txtNombre.getText(),
                        vista.txtEspecie.getText(),
                        vista.txtRaza.getText(),
                        vista.txtSexo.getText(),
                        vista.getFechaNacimiento(),
                        vista.getFechaIngreso(),
                        Integer.parseInt(vista.txtEdad.getText()),
                        Double.parseDouble(vista.txtPeso.getText()),
                        vista.txtSalud.getText(),
                        vista.txtComportamiento.getText(),
                        vista.txtNecesidades.getText(),
                        vista.txtNumeroMicrochip.getText(),
                        vista.getFoto(),
                        (Refugio) vista.comboRefugio.getSelectedItem(),
                        (Veterinario) vista.comboVeterinario.getSelectedItem()
                );
                modelo.nuevoAnimal(animal);
                break;
            }
            case "ModificarAnimal": {
                Animal seleccion = vista.listaAnimales.getSelectedValue();
                if (seleccion != null) {
                    seleccion.setNombre(vista.txtNombre.getText());
                    seleccion.setEspecie(vista.txtEspecie.getText());
                    seleccion.setRaza(vista.txtRaza.getText());
                    seleccion.setSexo(vista.txtSexo.getText());
                    seleccion.setFechaNacimiento(vista.getFechaNacimiento());
                    seleccion.setFechaIngreso(vista.getFechaIngreso());
                    seleccion.setEdad(Integer.parseInt(vista.txtEdad.getText()));
                    seleccion.setPeso(Double.parseDouble(vista.txtPeso.getText()));
                    seleccion.setSalud(vista.txtSalud.getText());
                    seleccion.setComportamiento(vista.txtComportamiento.getText());
                    seleccion.setNecesidades(vista.txtNecesidades.getText());
                    seleccion.setNumeroMicrochip(vista.txtNumeroMicrochip.getText());
                    seleccion.setFoto(vista.getFoto());
                    seleccion.setRefugio((Refugio) vista.comboRefugio.getSelectedItem());
                    seleccion.setVeterinario((Veterinario) vista.comboVeterinario.getSelectedItem());
                    modelo.modificarAnimal(seleccion);
                } else {
                    JOptionPane.showMessageDialog(vista, "Seleccione un animal para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "EliminarAnimal": {
                Animal seleccion = vista.listaAnimales.getSelectedValue();
                modelo.eliminarAnimal(seleccion);
                break;
            }

            // adopcion
            case "NuevaAdopcion": {
                Adopcion adopcion = new Adopcion(
                        (Animal) vista.comboAnimal.getSelectedItem(),
                        (Adoptante) vista.comboAdoptante.getSelectedItem(),
                        vista.getFechaAdopcion(),
                        vista.txtEstado.getText(),
                        vista.txtObservaciones.getText(),
                        vista.getDocumentoAdopcion()
                );
                modelo.nuevaAdopcion(adopcion);
                break;
            }
            case "ModificarAdopcion": {
                Adopcion seleccionada = vista.listaAdopciones.getSelectedValue();
                if (seleccionada != null) {
                    seleccionada.setAnimal((Animal) vista.comboAnimal.getSelectedItem());
                    seleccionada.setAdoptante((Adoptante) vista.comboAdoptante.getSelectedItem());
                    seleccionada.setFechaAdopcion(vista.getFechaAdopcion());
                    seleccionada.setEstadoAdopcion(vista.txtEstado.getText());
                    seleccionada.setObservaciones(vista.txtObservaciones.getText());
                    seleccionada.setDocumentoAdopcion(vista.getDocumentoAdopcion());
                    modelo.modificarAdopcion(seleccionada);
                } else {
                    JOptionPane.showMessageDialog(vista, "Seleccione una adopción para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "EliminarAdopcion": {
                Adopcion seleccionada = vista.listaAdopciones.getSelectedValue();
                modelo.eliminarAdopcion(seleccionada);
                break;
            }

            // adoptante
            case "NuevoAdoptante": {
                Adoptante adoptante = new Adoptante(
                        vista.txtNombre.getText(),
                        vista.txtApellidos.getText(),
                        vista.txtDNI.getText(),
                        vista.txtDireccion.getText(),
                        vista.txtTelefono.getText(),
                        vista.txtEmail.getText(),
                        vista.getFechaRegistro(),
                        vista.getFoto()
                );
                modelo.nuevoAdoptante(adoptante);
                break;
            }
            case "ModificarAdoptante": {
                Adoptante seleccion = vista.listaAdoptantes.getSelectedValue();
                if (seleccion != null) {
                    seleccion.setNombre(vista.txtNombre.getText());
                    seleccion.setApellidos(vista.txtApellidos.getText());
                    seleccion.setDni(vista.txtDNI.getText());
                    seleccion.setDireccion(vista.txtDireccion.getText());
                    seleccion.setTelefono(vista.txtTelefono.getText());
                    seleccion.setEmail(vista.txtEmail.getText());
                    seleccion.setFechaRegistro(vista.getFechaRegistro());
                    seleccion.setFoto(vista.getFoto());
                    modelo.modificarAdoptante(seleccion);
                } else {
                    JOptionPane.showMessageDialog(vista, "Seleccione un adoptante para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "EliminarAdoptante": {
                Adoptante seleccion = vista.listaAdoptantes.getSelectedValue();
                modelo.eliminarAdoptante(seleccion);
                break;
            }

            // veterinario
            case "NuevoVeterinario": {
                Veterinario vet = new Veterinario(
                        vista.txtNombre.getText(),
                        vista.txtApellidos.getText(),
                        vista.txtTelefono.getText(),
                        vista.txtEmail.getText(),
                        vista.txtEspecialidad.getText(),
                        Integer.parseInt(vista.txtAniosExperiencia.getText()),
                        vista.getFoto(),
                        (CentroVeterinario) vista.comboCentroVet.getSelectedItem()
                );
                modelo.nuevoVeterinario(vet);
                break;
            }
            case "ModificarVeterinario": {
                Veterinario seleccion = vista.listaVeterinarios.getSelectedValue();
                if (seleccion != null) {
                    seleccion.setNombre(vista.txtNombre.getText());
                    seleccion.setApellidos(vista.txtApellidos.getText());
                    seleccion.setTelefono(vista.txtTelefono.getText());
                    seleccion.setEmail(vista.txtEmail.getText());
                    seleccion.setEspecialidad(vista.txtEspecialidad.getText());
                    seleccion.setAniosExperiencia(Integer.parseInt(vista.txtAniosExperiencia.getText()));
                    seleccion.setFoto(vista.getFoto());
                    seleccion.setCentroVeterinario((CentroVeterinario) vista.comboCentroVet.getSelectedItem());
                    modelo.modificarVeterinario(seleccion);
                } else {
                    JOptionPane.showMessageDialog(vista, "Seleccione un veterinario para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "EliminarVeterinario": {
                Veterinario seleccion = vista.listaVeterinarios.getSelectedValue();
                modelo.eliminarVeterinario(seleccion);
                break;
            }

            // centro veterinario
            case "NuevoCentro": {
                CentroVeterinario centro = new CentroVeterinario(
                        vista.txtNombre.getText(),
                        vista.txtDireccion.getText(),
                        vista.txtCodigoPostal.getText(),
                        vista.txtTelefono.getText(),
                        vista.txtEmail.getText(),
                        vista.getFechaRegistro(),
                        vista.chkServicioUrgencias.isSelected(),
                        vista.getFotoCentro()
                );
                modelo.nuevoCentroVeterinario(centro);
                break;
            }
            case "ModificarCentro": {
                CentroVeterinario seleccion = vista.listaCentros.getSelectedValue();
                if (seleccion != null) {
                    seleccion.setNombre(vista.txtNombre.getText());
                    seleccion.setDireccion(vista.txtDireccion.getText());
                    seleccion.setCodigoPostal(vista.txtCodigoPostal.getText());
                    seleccion.setTelefono(vista.txtTelefono.getText());
                    seleccion.setEmail(vista.txtEmail.getText());
                    seleccion.setFechaRegistro(vista.getFechaRegistro());
                    seleccion.setServicioUrgencias(vista.chkServicioUrgencias.isSelected());
                    seleccion.setFotoCentro(vista.getFotoCentro());
                    modelo.modificarCentroVeterinario(seleccion);
                } else {
                    JOptionPane.showMessageDialog(vista, "Seleccione un centro veterinario para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "EliminarCentro": {
                CentroVeterinario seleccion = vista.listaCentros.getSelectedValue();
                modelo.eliminarCentroVeterinario(seleccion);
                break;
            }

        }
        refrescarSeccionRefugios();
        refrescarSeccionAnimales();
        refrescarSeccionAdopciones();
        refrescarSeccionAdoptantes();
        refrescarSeccionVeterinarios();
        refrescarSeccionCentrosVeterinarios

        } catch (Exception e) {
            HibernateUtil.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (vista.listaAnimales.getSelectedValue() != null) {
            Animal a = vista.listaAnimales.getSelectedValue();
            vista.txtNombre.setText(a.getNombre());
            vista.txtEspecie.setText(a.getEspecie());
            vista.txtRaza.setText(a.getRaza());
            vista.txtSexo.setText(a.getSexo());
            vista.setFechaNacimiento(a.getFechaNacimiento());
            vista.setFechaIngreso(a.getFechaIngreso());
            vista.txtEdad.setText(String.valueOf(a.getEdad()));
            vista.txtPeso.setText(String.valueOf(a.getPeso()));
            vista.txtSalud.setText(a.getSalud());
            vista.txtComportamiento.setText(a.getComportamiento());
            vista.txtNecesidades.setText(a.getNecesidades());
            vista.txtNumeroMicrochip.setText(a.getNumeroMicrochip());
            vista.setFoto(a.getFoto());
            vista.comboRefugio.setSelectedItem(a.getRefugio());
            vista.comboVeterinario.setSelectedItem(a.getVeterinario());

        }else if(listSelectionEvent.getSource() == vista.listRefugio && vista.listRefugio.getSelectedValue() != null) {
            Refugio refugio = vista.listRefugio.getSelectedValue();
            vista.txtNombreRef.setText(refugio.getNombre());
            vista.txtDireccionRef.setText(refugio.getApellido());
            vista.txtCiudadRef.setText(refugio.getAniosExperiencia());

        } else if (vista.listAdopcion.getSelectedValue() != null) {
            Adopcion a = vista.listAdopcion.getSelectedValue();
            vista.comboAnimal.setSelectedItem(a.getAnimal());
            vista.comboAdoptante.setSelectedItem(a.getAdoptante());
            vista.setFechaAdopcion(a.getFechaAdopcion());
            vista.txtEstado.setText(a.getEstadoAdopcion());
            vista.txtObservaciones.setText(a.getObservaciones());
            vista.setDocumentoAdopcion(a.getDocumentoAdopcion());

        }else if (vista.listaVeterinarios.getSelectedValue() != null) {
            Veterinario v = vista.listaVeterinarios.getSelectedValue();
            vista.txtNombre.setText(v.getNombre());
            vista.txtApellidos.setText(v.getApellidos());
            vista.txtTelefono.setText(v.getTelefono());
            vista.txtEmail.setText(v.getEmail());
            vista.txtEspecialidad.setText(v.getEspecialidad());
            vista.txtAniosExperiencia.setText(String.valueOf(v.getAniosExperiencia()));
            vista.setFoto(v.getFoto());
            vista.comboCentroVet.setSelectedItem(v.getCentroVeterinario());

        } else if (vista.listaAnimales.getSelectedValue() != null) {
            Animal a = vista.listaAnimales.getSelectedValue();
            vista.txtNombre.setText(a.getNombre());
            vista.txtEspecie.setText(a.getEspecie());
            vista.txtRaza.setText(a.getRaza());
            vista.txtSexo.setText(a.getSexo());
            vista.setFechaNacimiento(a.getFechaNacimiento());
            vista.setFechaIngreso(a.getFechaIngreso());
            vista.txtEdad.setText(String.valueOf(a.getEdad()));
            vista.txtPeso.setText(String.valueOf(a.getPeso()));
            vista.txtSalud.setText(a.getSalud());
            vista.txtComportamiento.setText(a.getComportamiento());
            vista.txtNecesidades.setText(a.getNecesidades());
            vista.txtNumeroMicrochip.setText(a.getNumeroMicrochip());
            vista.setFoto(a.getFoto());
            vista.comboRefugio.setSelectedItem(a.getRefugio());
            vista.comboVeterinario.setSelectedItem(a.getVeterinario());

        } else

    }




    // poner todos los combos que hay
    private void cargarCombos() {
        vista.comboAnimalAdopcion.removeAllItems();
        for (Animal a : modelo.obtenerAnimales()) {
            vista.comboAnimalAdopcion.addItem(a);
        }

        vista.comboAdoptanteAnimal.removeAllItems();
        for (Adoptante ad : modelo.obtenerAdoptantes()) {
            vista.comboAdoptante.addItem(ad);
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
        if(e.getSource() == vista.getTxtBuscarTexto){
            listarAnimales(modelo.getAnimales(vista.getTxtBuscarAnimal.getText()));
        }
        if(e.getSource() == vista.getTxtBuscarTexto){
            listarRefugios(modelo.getRefugios(vista.getTxtBuscarRefugio.getText()));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

}
