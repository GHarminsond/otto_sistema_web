# Query: 
# ContextLines: 1

// ==================== ARCHIVO: UsuarioService.java ====================
package com.otto.service;

import com.otto.dao.UsuarioDAO;
import com.otto.model.Usuario;
import com.otto.model.RolUsuario;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Clase de servicio para la lógica de negocio de usuarios
 */
public class UsuarioService {
    private UsuarioDAO usuarioDAO;
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Registra un nuevo usuario
     * @param nombre Nombre del usuario
     * @param correo Correo electrónico
     * @param contrasena Contraseña
     * @param rol Rol del usuario
     * @return true si se registró correctamente
     */
    public boolean registrarUsuario(String nombre, String correo, String contrasena, RolUsuario rol) {
        if (validarDatosUsuario(nombre, correo, contrasena)) {
            // Verificar si el correo ya existe
            if (usuarioDAO.consultarUsuarioPorCorreo(correo) != null) {
                System.err.println("El correo electrónico ya está registrado");
                return false;
            }
            
            Usuario usuario = new Usuario(nombre, correo, contrasena, rol);
            return usuarioDAO.insertarUsuario(usuario);
        }
        return false;
    }

    /**
     * Autentica un usuario
     * @param correo Correo electrónico
     * @param contrasena Contraseña
     * @return Usuario autenticado o null
     */
    public Usuario autenticarUsuario(String correo, String contrasena) {
        if (correo == null || correo.trim().isEmpty() || 
            contrasena == null || contrasena.trim().isEmpty()) {
            System.err.println("Correo y contraseña son requeridos");
            return null;
        }
        return usuarioDAO.autenticarUsuario(correo, contrasena);
    }

    /**
     * Obtiene todos los usuarios
     * @return Lista de usuarios
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.consultarTodosLosUsuarios();
    }

    /**
     * Obtiene un usuario por correo
     * @param correo Correo del usuario
     * @return Usuario encontrado
     */
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioDAO.consultarUsuarioPorCorreo(correo);
    }

    /**
     * Actualiza un usuario
     * @param id ID del usuario
     * @param nombre Nuevo nombre
     * @param correo Nuevo correo
     * @param contrasena Nueva contraseña
     * @param rol Nuevo rol
     * @return true si se actualizó correctamente
     */
    public boolean actualizarUsuario(int id, String nombre, String correo, String contrasena, RolUsuario rol) {
        if (validarDatosUsuario(nombre, correo, contrasena)) {
            Usuario usuario = new Usuario(id, nombre, correo, contrasena, rol);
            return usuarioDAO.actualizarUsuario(usuario);
        }
        return false;
    }

    /**
     * Elimina un usuario
     * @param id ID del usuario a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarUsuario(int id) {
        return usuarioDAO.eliminarUsuario(id);
    }

    /**
     * Valida los datos de un usuario
     * @param nombre Nombre del usuario
     * @param correo Correo electrónico
     * @param contrasena Contraseña
     * @return true si los datos son válidos
     */
    private boolean validarDatosUsuario(String nombre, String correo, String contrasena) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío");
            return false;
        }
        if (correo == null || !EMAIL_PATTERN.matcher(correo).matches()) {
            System.err.println("Formato de correo electrónico inválido");
            return false;
        }
        if (contrasena == null || contrasena.length() < 6) {
            System.err.println("La contraseña debe tener al menos 6 caracteres");
            return false;
        }
        return true;
    }
}