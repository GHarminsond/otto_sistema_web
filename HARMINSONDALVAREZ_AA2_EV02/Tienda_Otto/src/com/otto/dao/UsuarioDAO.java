# Query: 
# ContextLines: 1

package com.otto.dao;

import com.otto.config.DatabaseConnection;
import com.otto.model.Usuario;
import com.otto.model.RolUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para operaciones CRUD de usuarios
 */
public class UsuarioDAO {

    /**
     * Inserta un nuevo usuario en la base de datos
     * @param usuario Usuario a insertar
     * @return true si se insertó correctamente, false en caso contrario
     */
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

    /**
     * Consulta todos los usuarios
     * @return Lista de usuarios
     */
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

    /**
     * Consulta un usuario por correo electrónico
     * @param correo Correo del usuario
     * @return Usuario encontrado o null
     */
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

    /**
     * Actualiza un usuario existente
     * @param usuario Usuario con datos actualizados
     * @return true si se actualizó correctamente, false en caso contrario
     */
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

    /**
     * Elimina un usuario por ID
     * @param id ID del usuario a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
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

    /**
     * Autentica un usuario
     * @param correo Correo del usuario
     * @param contrasena Contraseña del usuario
     * @return Usuario autenticado o null
     */
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

// ==================== ARCHIVO: ProductoService.java ====================
package com.otto.service;

import com.otto.dao.ProductoDAO;
import com.otto.model.Producto;
import java.util.List;

/**
 * Clase de servicio para la lógica de negocio de productos
 */
public class ProductoService {
    private ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    /**
     * Crea un nuevo producto
     * @param nombre Nombre del producto
     * @param precio Precio del producto
     * @param descripcion Descripción del producto
     * @return true si se creó correctamente
     */
    public boolean crearProducto(String nombre, double precio, String descripcion) {
        if (validarDatosProducto(nombre, precio, descripcion)) {
            Producto producto = new Producto(nombre, precio, descripcion);
            return productoDAO.insertarProducto(producto);
        }
        return false;
    }

    /**
     * Obtiene todos los productos
     * @return Lista de productos
     */
    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.consultarTodosLosProductos();
    }

    /**
     * Obtiene un producto por ID
     * @param id ID del producto
     * @return Producto encontrado
     */
    public Producto obtenerProductoPorId(int id) {
        return productoDAO.consultarProductoPorId(id);
    }

    /**
     * Actualiza un producto
     * @param id ID del producto
     * @param nombre Nuevo nombre
     * @param precio Nuevo precio
     * @param descripcion Nueva descripción
     * @return true si se actualizó correctamente
     */
    public boolean actualizarProducto(int id, String nombre, double precio, String descripcion) {
        if (validarDatosProducto(nombre, precio, descripcion)) {
            Producto producto = new Producto(id, nombre, precio, descripcion);
            return productoDAO.actualizarProducto(producto);
        }
        return false;
    }

    /**
     * Elimina un producto
     * @param id ID del producto a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarProducto(int id) {
        return productoDAO.eliminarProducto(id);
    }

    /**
     * Valida los datos de un producto
     * @param nombre Nombre del producto
     * @param precio Precio del producto
     * @param descripcion Descripción del producto
     * @return true si los datos son válidos
     */
    private boolean validarDatosProducto(String nombre, double precio, String descripcion) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del producto no puede estar vacío");
            return false;
        }
        if (precio <= 0) {
            System.err.println("El precio debe ser mayor a 0");
            return false;
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.err.println("La descripción no puede estar vacía");
            return false;
        }
        return true;
    }
}