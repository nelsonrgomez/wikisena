package com.sena.wikiadmin.controlador;

import com.sena.wikiadmin.dao.ModuloDAO;
import com.sena.wikiadmin.modelo.Modulo;
import com.sena.wikiadmin.vista.ModuloForm;
import java.sql.Connection;
import java.util.List;

public class ModuloController {
    private final ModuloForm vista;
    private final ModuloDAO dao;
    private Connection conn;

    public ModuloController(ModuloForm vista) {
        this.vista = vista;
        this.dao = new ModuloDAO(conn);
    }

    public void guardarModulo() {
        Modulo m = vista.getModuloFromForm();
        if (dao.insertar(m)) {
            vista.mostrarMensaje("Módulo guardado con éxito.");
            vista.limpiarCampos();
        } else {
            vista.mostrarMensaje("Error al guardar el módulo.");
        }
    }

    public void listarModulos() {
        List<Modulo> modulos = dao.listar();
        vista.mostrarLista(modulos);
    }

    public void actualizarModulo() {
        Modulo m = vista.getModuloFromForm();
        if (dao.actualizar(m)) {
            vista.mostrarMensaje("Módulo actualizado.");
        } else {
            vista.mostrarMensaje("Error al actualizar.");
        }
    }

    public void eliminarModulo() {
        int id = vista.getIdFromForm();
        if (dao.eliminar(id)) {
            vista.mostrarMensaje("Módulo eliminado.");
        } else {
            vista.mostrarMensaje("Error al eliminar.");
        }
    }
} 
