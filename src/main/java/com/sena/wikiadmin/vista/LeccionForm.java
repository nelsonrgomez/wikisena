package com.sena.wikiadmin.vista;

import com.sena.wikiadmin.modelo.Modulo;

import javax.swing.*;
import java.awt.*;

public class LeccionForm extends JFrame {

    public JTextField txtId;
    public JTextField txtTitulo;
    public JComboBox<Modulo> cbModulo;
    public JButton btnNuevo;
    public JButton btnGuardar;
    public JButton btnActualizar;
    public JButton btnEliminar;
    public JButton btnRefrescar;
    public JTable tabla;

    public LeccionForm() {
        setTitle("Lecciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 540);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setContentPane(root);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6,6,6,6);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 0; gc.gridy = 0; form.add(new JLabel("ID:"), gc);
        txtId = new JTextField(10);
        txtId.setEditable(false);
        gc.gridx = 1; gc.gridy = 0; form.add(txtId, gc);

        gc.gridx = 0; gc.gridy = 1; form.add(new JLabel("Título:"), gc);
        txtTitulo = new JTextField(30);
        gc.gridx = 1; gc.gridy = 1; form.add(txtTitulo, gc);

        gc.gridx = 0; gc.gridy = 2; form.add(new JLabel("Módulo:"), gc);
        cbModulo = new JComboBox<>();
        gc.gridx = 1; gc.gridy = 2; form.add(cbModulo, gc);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnRefrescar = new JButton("Refrescar");
        acciones.add(btnNuevo);
        acciones.add(btnGuardar);
        acciones.add(btnActualizar);
        acciones.add(btnEliminar);
        acciones.add(btnRefrescar);

        gc.gridx = 0; gc.gridy = 3; gc.gridwidth = 2;
        form.add(acciones, gc);

        root.add(form, BorderLayout.NORTH);

        tabla = new JTable();
        root.add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
