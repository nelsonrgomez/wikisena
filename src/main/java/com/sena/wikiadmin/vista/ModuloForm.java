package com.sena.wikiadmin.vista;

import com.sena.wikiadmin.controlador.ModuloController;
import com.sena.wikiadmin.modelo.Modulo;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class ModuloForm extends JFrame {

    private final JTextField txtId;
    private final JTextField txtTitulo;
    private final JTextField txtDescripcion;
    private final JTextField txtOrden;
    private final JComboBox<String> cmbCompetencia;
    private final JCheckBox chkActivo;
    private final JButton btnGuardar;
    private final JButton btnListar;
    private final JButton btnActualizar;
    private final JButton btnEliminar; 
    private final JTextArea txtResultado;

    private ModuloController controller;

    public ModuloForm() {
        controller = new ModuloController(this);

        setTitle("Gestión de Módulos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(9, 2));

        // Campos
        add(new JLabel("ID (solo para actualizar/eliminar):"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        add(new JLabel("Competencia:"));
        cmbCompetencia = new JComboBox<>(new String[]{"html","css","js","java","php"});
        add(cmbCompetencia);

        add(new JLabel("Activo:"));
        chkActivo = new JCheckBox("Sí");
        add(chkActivo);

        add(new JLabel("Orden:"));
        txtOrden = new JTextField();
        add(txtOrden);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnListar = new JButton("Listar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        add(btnGuardar);
        add(btnListar);
        add(btnActualizar);
        add(btnEliminar);

        // Área de resultados
        txtResultado = new JTextArea();
        add(new JScrollPane(txtResultado));

        // Eventos
        btnGuardar.addActionListener(e -> controller.guardarModulo());
        btnListar.addActionListener(e -> controller.listarModulos());
        btnActualizar.addActionListener(e -> controller.actualizarModulo());
        btnEliminar.addActionListener(e -> controller.eliminarModulo());
    }

    public Modulo getModuloFromForm() {
        Modulo m = new Modulo();
        if (!txtId.getText().isEmpty()) {
            m.setId(Integer.parseInt(txtId.getText()));
        }
        m.setTitulo(txtTitulo.getText());
        m.setDescripcion(txtDescripcion.getText());
        m.setCompetencia((String) cmbCompetencia.getSelectedItem());
        m.setActivo(chkActivo.isSelected());
        m.setFechaCreacion(new Date());
        m.setOrden(Integer.parseInt(txtOrden.getText().isEmpty() ? "0" : txtOrden.getText()));
        return m;
    }

    public int getIdFromForm() {
        return Integer.parseInt(txtId.getText());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarLista(List<Modulo> modulos) {
        txtResultado.setText("");
        for (Modulo m : modulos) {
            txtResultado.append(m.getId() + " - " + m.getTitulo() + "|"+ m.getDescripcion() + " - " + m.getCompetencia() + "\n");
        }
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtOrden.setText("");
        chkActivo.setSelected(false);
    }
}
