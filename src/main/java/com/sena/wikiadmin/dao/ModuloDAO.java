package com.sena.wikiadmin.dao;

import com.sena.wikiadmin.modelo.Modulo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuloDAO {

    public ModuloDAO(Connection conn) {
    }

    public ModuloDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean insertar(Modulo m) {
        String sql = "INSERT INTO modulos (titulo, descripcion, competencia, activo, fecha_creacion, orden) VALUES (?,?,?,?,?,?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getDescripcion());
            ps.setString(3, m.getCompetencia());
            ps.setBoolean(4, m.isActivo());
            if (m.getFechaCreacion() != null) {
                ps.setDate(5, new java.sql.Date(m.getFechaCreacion().getTime()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.setInt(6, m.getOrden());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar módulo: " + e.getMessage());
            return false;
        }
    }

    public List<Modulo> listar() {
        List<Modulo> lista = new ArrayList<>();
        String sql = "SELECT * FROM modulos";
        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Modulo m = new Modulo();
                m.setId(rs.getInt("id"));
                m.setTitulo(rs.getString("titulo"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setCompetencia(rs.getString("competencia"));
                m.setActivo(rs.getBoolean("activo"));
                m.setFechaCreacion(rs.getDate("fecha_creacion"));
                m.setOrden(rs.getInt("orden"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar módulos: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Modulo m) {
        String sql = "UPDATE modulos SET titulo=?, descripcion=?, competencia=?, activo=?, fecha_creacion=?, orden=? WHERE id=?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getDescripcion());
            ps.setString(3, m.getCompetencia());
            ps.setBoolean(4, m.isActivo());
            if (m.getFechaCreacion() != null) {
                ps.setDate(5, new java.sql.Date(m.getFechaCreacion().getTime()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.setInt(6, m.getOrden());
            ps.setInt(7, m.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar módulo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM modulos WHERE id=?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar módulo: " + e.getMessage());
            return false;
        }
    }
}
