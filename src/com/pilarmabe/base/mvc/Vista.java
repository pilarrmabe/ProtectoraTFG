package com.pilarmabe.base.mvc;

import com.github.lgooddatepicker.components.DatePicker;
import com.pilarmabe.base.clases.*;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import javax.swing.*;

public class Vista extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;

    private final static String TITULO_FRAME = "Programa de Gestión para protectoras";

    // Menú
    public JMenuItem conexionItem;
    public JMenuItem salirItem;

    // List Models
    public DefaultListModel<Refugio> dlmRefugios;
    public DefaultListModel<Animal> dlmAnimales;
    public DefaultListModel<Usuario> dlmUsuarios;
    public DefaultListModel<CentroVeterinario> dlmCentros;
    public DefaultComboBoxModel<Refugio> dcbRefugio;

    // === Panel Animal ===
    public JPanel JPanelAnimal;
    public JTextField txtNombreAnimal;
    public JComboBox comboEspecie;
    public JComboBox comboRaza;
    public JComboBox comboSexo;
    public DatePicker fechaNacimAnimal;
    public DatePicker fechaIngresoAnimal;
    public JTextField txtEdadAnimal;
    public JSpinner pesoAnimal;
    public JTextField txtSaludAnimal;
    public JTextField txtObservaciones;
    public JTextField txtNecesidades;
    public JTextField txtNumMicrochip;
    public JComboBox comboRefugioAnimal;
    public JComboBox comboVetAnimal;
    public JTextField txtEstadoAnimal;
    public JComboBox comboAdoptanteAnimal;
    public JTextField txtBuscarAnimal;
    public JButton btnAnimalAnadir;
    public JButton btnAnimalModificar;
    public JButton btnAnimalEliminar;
    public JList<Animal> listAnimal;
    public JTextField txtFotoAnimal;

    // === Panel Refugio ===
    public JPanel JPanelRefugio;
    public JTextField txtNombreRef;
    public JTextField txtDireccionRef;
    public JTextField txtCiudadRef;
    public JTextField txtCPRef;
    public JTextField txtResponsable;
    public JTextField txtTelfRef;
    public JTextField txtEmailRef;
    public JSpinner capacidadRef;
    public DatePicker fechaAperturaRef;
    public JTextField txtBuscarRefugio;
    public JTextField logoRefugio;
    public JButton btnRefAñadir;
    public JButton btnRefModificar;
    public JButton btnRefEliminar;
    public JList<Refugio> listRefugio;

    // === Panel Adoptante ===
    public JPanel JPanelAdoptante;
    public JTextField txtNombreAdoptante;
    public JTextField txtApellidosAdoptante;
    public JTextField dniAdoptante;
    public JTextField txtDireccAdoptante;
    public JTextField txtTelfAdoptante;
    public JTextField txtEmailAdoptante;
    public JTextField fotoAdoptante;
    public DatePicker fechaRegistroAdoptante;
    public JButton btnAniadirAdoptante;
    public JButton btnModAdoptante;
    public JButton btnEliminarAdoptante;
    public JTextField txtBuscarAdoptante;
    public JList<Adoptante> listAdoptante;

    // === Panel Adopción ===
    public JPanel JPanelAdopcion;
    public JComboBox comboEstadoAdopcion;
    public JComboBox comboAnimalAdopcion;
    public JComboBox comboAdoptanteAdopcion;
    public DatePicker fechaAdopcion;
    public JTextField txtObservacionAdopcion;
    public JTextField txtDocuAdopcion;
    public JTextField txtBuscarAdopcion;
    public JList<Adopcion> listAdopcion;
    public JButton btnAniadirAdopcion;
    public JButton btnModAdopcion;
    public JButton btnEliminarAdopcion;

    // === Panel Centro Veterinario ===
    public JPanel JPanelCentroVet;
    public JTextField txtNombreCentro;
    public JRadioButton urgenciasCentro;
    public JTextField logoVeterinario;
    public JTextField txtBuscarCentro;
    public JButton btnAniadirCentro;
    public JButton btnModCentro;
    public JButton btnEliminarCentro;
    public JList<CentroVeterinario> listCentro;
    public JTextField txtDireccionCentro;
    public JTextField txtCpCentro;
    public JTextField txtTelfCentro;
    public JTextField txtEmailCentro;
    public DatePicker fechaRegistroCentro;

    // === Panel Veterinario ===
    public JPanel JPanelVeterinario;
    public JTextField txtNombreVet;
    public JTextField txtApellidosVet;
    public JTextField txtEmailVet;
    public JTextField txtTlfVet;
    public JComboBox comboEspecialidad;
    public JSpinner aniosExperienciaVet;
    public JTextField fotoVeterinario;
    public JTextField txtBuscarVeterinario;
    public JComboBox comboCentroDelVet;
    public JList<Veterinario> listVeterinario;
    public JButton btnAniadirVet;
    public JButton btnModVet;
    public JButton btnEliminarVet;

    // Usuarios
    public JPanel JPanelUsuarios;
    public JTextField txtNombre;
    public JTextField txtPass;
    public JComboBox comboTipoUsuario;
    public JButton btnUsuarioAnadir;
    public JButton btnUsuarioModificar;
    public JButton btnUsuarioEliminar;
    public JList<Usuario> listUsuario;

    // === Constructor e Inicialización ===
    public Vista() {
        super(TITULO_FRAME);
        initFrame();
    }

    public void initFrame() {

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(1080, 900);
        crearMenu();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        setListModels();
    }

    private void setListModels() {
        System.out.println("setlistmodel");
        dlmRefugios = new DefaultListModel<>();
        dlmAnimales = new DefaultListModel<>();
        dlmRefugios = new DefaultListModel<>();
        dlmUsuarios = new DefaultListModel<>();
        dlmCentros = new DefaultListModel<>();

        listRefugio.setModel(dlmRefugios);
        listAnimal.setModel(dlmAnimales);
        listUsuario.setModel(dlmUsuarios);
        listCentro.setModel(dlmCentros);
        System.out.println("List refugio: " + listRefugio);
        System.out.println("dlm refugio: " + dlmRefugios);
        dcbRefugio = new DefaultComboBoxModel<>();
        comboRefugioAnimal.setModel(dcbRefugio);
    }

    private void crearMenu() {
        System.out.println("inicio crear menu");
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        conexionItem = new JMenuItem("Conectar");
        conexionItem.setActionCommand("Conectar");

        salirItem = new JMenuItem("Salir");
        salirItem.setActionCommand("Salir");

        menu.add(conexionItem);
        menu.add(salirItem);
        barra.add(menu);
        this.setJMenuBar(barra);
        System.out.println("final crear menu");
    }

    public byte[] getFoto() {
        byte[] foto = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen");
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = fileChooser.getSelectedFile();
            try {
                foto = java.nio.file.Files.readAllBytes(archivo.toPath());
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        return foto;
    }

    public Blob convertirFotoABlob(byte[] foto) {
        try {
            return new SerialBlob(foto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
