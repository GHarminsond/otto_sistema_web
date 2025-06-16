package com.otto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.otto.config.DatabaseConnection;
import com.otto.model.RolUsuario;
import com.otto.model.Usuario;

// Clase DAO para realizar operaciones CRUD con la tabla de usuarios

public class UsuarioDAO {

    // Inserta un nuevo usuario en la base de datos
    // @param usuario Objeto Usuario a insertar
    // @return true si se insertó correctamente, false si hubo un error
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, contrasena, rol) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getContrasena());
            pstmt.setString(4, usuario.getRol().name().toLowerCase());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // Consulta todos los usuarios registrados en la base de datos
    // @return Lista de objetos Usuario
    public List<Usuario> consultarTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    RolUsuario.valueOf(rs.getString("rol").toUpperCase())
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    // Consulta un usuario por su ID
    // @param id ID del usuario a buscar
    // @return Objeto Usuario si lo encuentra, null si no existe
public Usuario findById(int id) {
    String sql = "SELECT * FROM usuarios WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setContrasena(rs.getString("contrasena"));
            // Agrega más campos según los que tenga tu clase Usuario
            return usuario;
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar usuario por ID: " + e.getMessage());
        }
        return null;
    }
// Consulta un usuario por su correo
public Usuario consultarUsuarioPorCorreo(String correo) {
    String sql = "SELECT * FROM usuarios WHERE correo = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, correo);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contrasena"),
                RolUsuario.valueOf(rs.getString("rol").toUpperCase())
            );
        }
    } catch (SQLException e) {
        System.err.println("Error al consultar usuario por correo: " + e.getMessage());
    }
    return null;
}
   

    // Actualiza los datos de un usuario existente
    // @param usuario Objeto Usuario con los datos actualizados
    // @return true si se actualizó correctamente, false si hubo un error
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, correo = ?, contrasena = ?, rol = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getContrasena());
            pstmt.setString(4, usuario.getRol().name().toLowerCase());
            pstmt.setInt(5, usuario.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // Elimina un usuario por su ID
    // @param id ID del usuario a eliminar
    // @return true si se eliminó correctamente, false si hubo un error
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    // Autentica un usuario comparando correo y contraseña
    // @param correo Correo del usuario
    // @param contrasena Contraseña del usuario
    // @return Objeto Usuario si coincide, null si no
    public Usuario autenticarUsuario(String correo, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    RolUsuario.valueOf(rs.getString("rol").toUpperCase())
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al autenticar usuario: " + e.getMessage());
        }
        return null;
    }
}
