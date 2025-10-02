package com.sena.wikiadmin.controlador;

import com.sena.wikiadmin.dao.LeccionDAO;
import com.sena.wikiadmin.dao.ModuloDAO;
import com.sena.wikiadmin.modelo.Modulo;
import com.sena.wikiadmin.vista.LeccionForm;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LeccionController {

    private final LeccionForm view;
    private final LeccionDAO leccionDAO; 
    private final ModuloDAO moduloDAO;

    public LeccionController(LeccionForm view) {
        this.view = view;
        this.leccionDAO = new LeccionDAO(); // usa ConexionBD internamente
        this.moduloDAO = new ModuloDAO();   // ya lo tienes con listar()
        init();
    }

    private void init() {
        cargarModulosEnCombo();
        refrescarTabla();

        view.btnNuevo.addActionListener(e -> limpiarFormulario());
        view.btnGuardar.addActionListener(e -> leccionDAO.insertar());
        view.btnActualizar.addActionListener(e -> leccionDAO.actualizar());
        view.btnEliminar.addActionListener(e -> leccionDAO.eliminar());
        view.btnRefrescar.addActionListener(e -> refrescarTabla());

        view.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarSeleccionATextos();
        });
    }

    private void cargarModulosEnCombo() {
        try {
            view.cbModulo.removeAllItems();
            List<Modulo> modulos = moduloDAO.listar(); // <- usa tu función existente
            DefaultComboBoxModel<Modulo> model = new DefaultComboBoxModel<>();
            for (Modulo m : modulos) model.addElement(m);
            view.cbModulo.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error cargando módulos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refrescarTabla() {
        try {
            var filas = leccionDAO.listarConModulo(); // (id, leccionTitulo, moduloTitulo)
            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Lección", "Módulo"}, 0) {
                @Override public boolean isCellEditable(int r, int c) { return false; }
                @Override public Class<?> getColumnClass(int c) { return c == 0 ? Integer.class : String.class; }
            };
            for (var f : filas) model.addRow(new Object[]{f.id(), f.leccionTitulo(), f.moduloTitulo()});
            view.tabla.setModel(model);
            view.tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
            view.tabla.getColumnModel().getColumn(1).setPreferredWidth(260);
            view.tabla.getColumnModel().getColumn(2).setPreferredWidth(220);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error listando lecciones: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        view.txtId.setText("");
        view.txtTitulo.setText("");
        if (view.cbModulo.getItemCount() > 0) view.cbModulo.setSelectedIndex(0);
        view.tabla.clearSelection();
