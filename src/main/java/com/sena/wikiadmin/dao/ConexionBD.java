package com.sena.wikiadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/varchate_bd_01"; // Cambia tu_base
    private static final String USER = "varchate_bd_01";  // Cambia si usas otro usuario
    private static final String PASS = "S3n4$ADS0#25";      // Cambia si tienes contraseña
    private static final Logger LOGGER = Logger.getLogger(ConexionBD.class.getName());


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error de conexión a la BD", e);
            return null;
        }
    }
} 
 
