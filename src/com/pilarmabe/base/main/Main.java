package com.pilarmabe.base.main;

import com.pilarmabe.base.mvc.Vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.pilarmabe.base.mvc.Controlador;
import com.pilarmabe.base.mvc.Modelo;


public class Main {
    public static void main(String[] args) {
        // Modelo modelo = new Modelo();
        // Vista vista = new Vista();
        // Controlador controlador = new Controlador(vista, modelo);
        initApp();
        login();
    }

    private static void initApp(){
        Modelo modelo = new Modelo();
        modelo.conectar();
        
    }

    private static void login() {
        JFrame loginFrame = new JFrame("Iniciar sesión");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(320, 320);
        loginFrame.setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("logo.png");

        if (logoIcon.getIconWidth() > 0) {
            Image img = logoIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(img));
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        } else {
            logoLabel.setText("Logo no encontrado");
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelCentral.add(logoLabel, gbc);

        // Usuario
        gbc.gridy++;
        gbc.gridwidth = 1;
        panelCentral.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        JTextField textUsuario = new JTextField(10);
        panelCentral.add(textUsuario, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy++;
        panelCentral.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        JPasswordField passwordContrasena = new JPasswordField(10);
        panelCentral.add(passwordContrasena, gbc);
        // Botón Entrar

        gbc.gridx = 0;
        gbc.gridy++;
        JButton btnEntrar = new JButton("Entrar");
        JButton btnCrearCuenta= new JButton("Crear Cuenta");
        gbc.gridwidth = 2;
        panelCentral.add(btnEntrar, gbc);
        panelCentral.add(btnCrearCuenta, gbc);

        loginFrame.setLocationRelativeTo(null); // Centrar ventana
        loginFrame.add(panelCentral, BorderLayout.CENTER);
        loginFrame.setVisible(true);

        btnEntrar.addActionListener(new ActionListener() {
        // La acción del botón que ahora misma no tiene
            @Override
            public void actionPerformed(ActionEvent e) {
                Modelo modelo = new Modelo();
                String usuario = textUsuario.getText();
                String contrasena = new String(passwordContrasena.getPassword());
                modelo.loginUsuario(usuario, contrasena);   
                // si el login es incorrecto TODO
                if (modelo.getUsuario() != null) {
                    Vista vista = new Vista();
                    loginFrame.setVisible(false);
                    // Aquí mostrarías la siguiente ventana
                    // String tipoUsuario = modelo.setUsuario("Admin");S
                    Controlador controlador = new Controlador(vista, modelo);

                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
