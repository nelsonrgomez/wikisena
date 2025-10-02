package com.sena.wikiadmin.vista;

import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {

    public MainApp() {
        setTitle("WikiAdmin - Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnModulos = new JButton("Gestionar Módulos");
        btnModulos.addActionListener(e -> new ModuloForm().setVisible(true));

        JButton btnLecciones = new JButton("Gestionar Lecciones");
        btnLecciones.addActionListener(e -> new LeccionForm().setVisible(true));
        
        JButton btnUsuarios = new JButton("Gestionar Usuarios");
        btnUsuarios.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "CRUD de usuarios aún no implementado");
            // aquí luego llamarías a new UsuarioForm().setVisible(true);
        });

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.add(btnModulos);
        panel.add(btnLecciones);
        panel.add(btnUsuarios);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}
