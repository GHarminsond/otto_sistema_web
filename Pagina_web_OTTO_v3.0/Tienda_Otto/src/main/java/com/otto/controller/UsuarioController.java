package com.otto.controller;

import com.otto.model.Usuario;
import com.otto.model.RolUsuario;
import com.otto.service.UsuarioService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            handleLogin(request, response);
        } else if ("register".equals(action)) {
            handleRegister(request, response);
        } else if ("listar".equals(action)) {
            handleListarUsuarios(request, response);
        } else if ("buscar".equals(action)) {
            handleBuscarUsuario(request, response);
        } else if ("actualizar".equals(action)) {
            handleActualizarUsuario(request, response);
        } else if ("eliminar".equals(action)) {
            handleEliminarUsuario(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = usuarioService.autenticarUsuario(email, password);

        if (usuario != null) {
            response.getWriter().println("Inicio de sesión exitoso");
        } else {
            response.getWriter().println("Credenciales incorrectas");
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean resultado = usuarioService.registrarUsuario(nombre, email, password, RolUsuario.CLIENTE);

        if (resultado) {
            response.getWriter().println("Registro exitoso");
        } else {
            response.getWriter().println("Error al registrar");
        }
    }

    private void handleListarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/listarUsuarios.jsp").forward(request, response);
    }

    private void handleBuscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/usuario.jsp").forward(request, response);
        } else {
            response.getWriter().println("Usuario no encontrado");
        }
    }

    private void handleActualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean resultado = usuarioService.actualizarUsuario(id, nombre, email, password, RolUsuario.CLIENTE);

        if (resultado) {
            response.getWriter().println("Usuario actualizado exitosamente");
        } else {
            response.getWriter().println("Error al actualizar el usuario");
        }
    }

    private void handleEliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean resultado = usuarioService.eliminarUsuario(id);

        if (resultado) {
            response.getWriter().println("Usuario eliminado exitosamente");
        } else {
            response.getWriter().println("Error al eliminar el usuario");
        }
    }
}
