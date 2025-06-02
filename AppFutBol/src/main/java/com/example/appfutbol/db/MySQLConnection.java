package com.example.appfutbol.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de utilidad para gestionar la conexión con una base de datos MySQL.
 * Utiliza el patrón Singleton para mantener una única conexión activa.
 *
 * Autor: MartinAR
 */
public class MySQLConnection {

    private static Connection conn = null;
    private static final String HOSTNAME = "localhost";
    private static final String DB_NAME = "deportibApp";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "martin";
    private static final String DB_PASS = "1234";

    /**
     * Establece la conexión con la base de datos si no ha sido creada aún.
     */
    public static void Connect() {
        if (conn != null) return;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + HOSTNAME + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
            conn = DriverManager.getConnection(url, DB_USER, DB_PASS);
            System.out.println("Successful database connection.");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, "Error connecting to database", ex);
        }
    }

    /**
     * Devuelve la conexión activa. Si no existe, la crea.
     *
     * @return Conexión activa a la base de datos.
     */
    public static Connection getConnection() {
        if (conn == null) {
            Connect();
        }
        return conn;
    }

    /**
     * Cierra la conexión con la base de datos si está abierta.
     */
    public static void Disconnect() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Database connection has been terminated.");
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, "Error closing the database connection", ex);
            }
        }
    }
}
