package com.sena.wikiadmin.dao;

import com.sena.wikiadmin.modelo.Leccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** DAO para CRUD de lecciones (usa ConexionBD.getConnection()). */
public class LeccionDAO {

    public LeccionDAO() {}

    /** Proyección para la tabla (incluye título del módulo). */
    public record FilaLeccion(int id, String leccionTitulo, String moduloTitulo) {}

    public List<FilaLeccion> listarConModulo() throws SQLException {
        String sql = """
            SELECT l.id, l.titulo AS leccion_titulo, m.titulo AS modulo_titulo
            FROM lecciones l
            JOIN modulos m ON m.id = l.moduloId
            ORDER BY l.id
        """;
        List<FilaLeccion> out = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new FilaLeccion(
                        rs.getInt("id"),
                        rs.getString("leccion_titulo"),
                        rs.getString("modulo_titulo")
                ));
            }
        }
        return out;
    }

    public List<Leccion> listar() throws SQLException {
        String sql = "SELECT id, titulo, moduloId FROM lecciones ORDER BY id";
        List<Leccion> out = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Leccion l = new Leccion();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setModuloId(rs.getInt("moduloId"));
                out.add(l);
            }
        }
        return out;
    }

    public Leccion obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id, titulo, moduloId FROM lecciones WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Leccion l = new Leccion();
                    l.setId(rs.getInt("id"));
                    l.setTitulo(rs.getString("titulo"));
                    l.setModuloId(rs.getInt("moduloId"));
                    return l;
                }
            }
        }
        return null;
    }

    public int insertar(Leccion l) throws SQLException {
        String sql = "INSERT INTO lecciones(titulo, moduloId) VALUES(?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, l.getTitulo());
            ps.setInt(2, l.getModuloId());
            int affected = ps.executeUpdate();
            if (affected == 0) return 0;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    l.setId(id);
                    return id;
                }
            }
        }
        return 0;
    }

    public void actualizar(Leccion l) throws SQLException {
        String sql = "UPDATE lecciones SET titulo = ?, moduloId = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, l.getTitulo());
            ps.setInt(2, l.getModuloId());
            ps.setInt(3, l.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM lecciones WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
