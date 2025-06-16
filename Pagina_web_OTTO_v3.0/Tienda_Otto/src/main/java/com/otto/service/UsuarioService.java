// ==================== ARCHIVO: UsuarioService.java ====================

package com.otto.service;

// Importamos las clases necesarias para la lógica del servicio de usuario
import com.otto.dao.UsuarioDAO;
import com.otto.model.Usuario;
import com.otto.model.RolUsuario;
import java.util.List;
import java.util.regex.Pattern;

// Clase de servicio que contiene la lógica de negocio para gestionar usuarios
public class UsuarioService {

    // Instancia del DAO para interactuar con la base de datos
    private UsuarioDAO usuarioDAO;

    // Expresión regular para validar formato de correo electrónico
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    // Constructor que inicializa el DAO
    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Método para registrar un nuevo usuario en el sistema
    public boolean registrarUsuario(String nombre, String correo, String contrasena, RolUsuario rol) {
        // Validamos los datos del usuario
        if (validarDatosUsuario(nombre, correo, contrasena)) {
            // Verificamos si ya existe un usuario con el mismo correo
            if (usuarioDAO.consultarUsuarioPorCorreo(correo) != null) {
                // Imprimimos error si ya está registrado
                System.err.println("El correo electrónico ya está registrado");
                return false;
            }
            
            // Creamos el nuevo objeto Usuario
            Usuario usuario = new Usuario(nombre, correo, contrasena, rol);

            // Insertamos el usuario en la base de datos y retornamos el resultado
            return usuarioDAO.insertarUsuario(usuario);
        }

        // Si los datos no son válidos, retornamos false
        return false;
    }

    // Método para autenticar a un usuario (login)
    public Usuario autenticarUsuario(String correo, String contrasena) {
        // Validamos que correo y contraseña no estén vacíos
        if (correo == null || correo.trim().isEmpty() || 
            contrasena == null || contrasena.trim().isEmpty()) {
            // Mostramos mensaje de error si faltan datos
            System.err.println("Correo y contraseña son requeridos");
            return null;
        }

        // Llamamos al DAO para verificar si el usuario existe con esos datos
        return usuarioDAO.autenticarUsuario(correo, contrasena);
    }

    // Método para obtener todos los usuarios registrados
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.consultarTodosLosUsuarios();
    }

    // Método para buscar un usuario por su correo electrónico
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioDAO.consultarUsuarioPorCorreo(correo);
    }

    // Método para actualizar los datos de un usuario existente
    public boolean actualizarUsuario(int id, String nombre, String correo, String contrasena, RolUsuario rol) {
        // Validamos los nuevos datos del usuario
        if (validarDatosUsuario(nombre, correo, contrasena)) {
            // Creamos un nuevo objeto Usuario con los datos actualizados
            Usuario usuario = new Usuario(id, nombre, correo, contrasena, rol);

            // Llamamos al DAO para actualizar los datos en la base
            return usuarioDAO.actualizarUsuario(usuario);
        }

        // Si los datos no son válidos, retornamos false
        return false;
    }

    // Método para eliminar un usuario por su ID
    public boolean eliminarUsuario(int id) {
        // Llamamos al DAO para eliminar el usuario y retornamos el resultado
        return usuarioDAO.eliminarUsuario(id);
    }

    // Método privado para validar los datos de un usuario
    private boolean validarDatosUsuario(String nombre, String correo, String contrasena) {
        // Validamos que el nombre no esté vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío");
            return false;
        }

        // Validamos que el correo tenga el formato correcto usando expresión regular
        if (correo == null || !EMAIL_PATTERN.matcher(correo).matches()) {
            System.err.println("Formato de correo electrónico inválido");
            return false;
        }

        // Validamos que la contraseña tenga al menos 6 caracteres
        if (contrasena == null || contrasena.length() < 6) {
            System.err.println("La contraseña debe tener al menos 6 caracteres");
            return false;
        }

        // Si todo es válido, retornamos true
        return true;
    }
}
