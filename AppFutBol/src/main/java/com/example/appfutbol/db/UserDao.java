package com.example.appfutbol.db;

import com.example.appfutbol.models.usuario;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de DAO para la entidad Usuario.
 * Proporciona operaciones CRUD y autenticación.
 *
 * <p>Autor: MartinAR</p>
 */
public class UserDao extends MySQLConnection implements Dao<usuario> {

    @Override
    public Optional<usuario> findById(int id) {
        String query = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario user = new usuario();
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setPassword(rs.getString("contraseña"));
                user.setCorreo(rs.getString("correo"));
                return Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<usuario> findAll() {
        String query = "SELECT * FROM usuario";
        List<usuario> usuarios = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuario user = new usuario();
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setPassword(rs.getString("contraseña"));
                user.setCorreo(rs.getString("correo"));
                usuarios.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public boolean save(usuario record) {
        String query = "INSERT INTO usuario (nombre, contraseña, correo) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, record.getNombre());
            stmt.setString(2, sha1(record.getPassword()));
            stmt.setString(3, record.getCorreo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(usuario record) {
        String query = "UPDATE usuario SET nombre = ?, contraseña = ?, correo = ? WHERE id_usuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, record.getNombre());
            stmt.setString(2, sha1(record.getPassword()));
            stmt.setString(3, record.getCorreo());
            stmt.setInt(4, record.getId_usuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Verifica las credenciales de un usuario.
     *
     * @param correo correo electrónico del usuario.
     * @param plainPassword contraseña sin encriptar.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean login(String correo, String plainPassword) {
        String query = "SELECT contraseña FROM usuario WHERE correo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("contraseña");
                String inputHash = sha1(plainPassword);
                return storedHash.equalsIgnoreCase(inputHash);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Encripta una cadena usando el algoritmo SHA-1.
     *
     * @param input texto plano a cifrar.
     * @return hash en formato hexadecimal.
     */
    public String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generando hash SHA-1", e);
        }
    }
}
