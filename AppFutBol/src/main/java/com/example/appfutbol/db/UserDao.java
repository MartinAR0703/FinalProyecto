package com.example.appfutbol.db;

import com.example.appfutbol.models.usuario;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UserDao extends MySQLConnection implements Dao<usuario> {

    @Override
    public Optional<usuario> findById(int id) {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario user = new usuario();
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setPassword(rs.getString("contraseña"));
                return Optional.of(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<usuario> findAll() {
        // Implementación opcional
        return List.of(); // vacía por ahora
    }

    @Override
    public boolean save(usuario record) {
        String query = "INSERT INTO usuario (nombre, contraseña, correo) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, record.getNombre());
            stmt.setString(2, record.getPassword());
            stmt.setString(3, record.getCorreo());
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(usuario record) {
        String query = "UPDATE usuario SET username = ?, password_hash = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, record.getNombre());
            stmt.setString(2, record.getPassword());
            stmt.setInt(3, record.getId_usuario());
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ Método login
    public boolean login(String correo, String plainPassword) {
        String query = "SELECT * FROM usuario WHERE correo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.toString());
            if (rs.next()) {
                String storedHash = rs.getString("contraseña");
                String inputHash = sha1(plainPassword);

                if (storedHash.equalsIgnoreCase(inputHash)) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔐 Utilidad para cifrar contraseñas SHA-1
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
