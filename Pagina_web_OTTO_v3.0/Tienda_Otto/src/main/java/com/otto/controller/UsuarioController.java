package com.otto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otto.model.RolUsuario;
import com.otto.model.Usuario;
import com.otto.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        Usuario autenticado = usuarioService.autenticarUsuario(usuario.getCorreo(), usuario.getContrasena());
        if (autenticado != null) {
            return "Inicio de sesión exitoso";
        } else {
            return "Credenciales incorrectas";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody Usuario usuario) {
        boolean resultado = usuarioService.registrarUsuario(
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.getContrasena(),
            RolUsuario.CLIENTE
        );

        if (resultado) {
            return "Registro exitoso";
        } else {
            return "Error al registrar";
        }
    }

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable int id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PutMapping("/{id}")
    public String actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        boolean resultado = usuarioService.actualizarUsuario(
            id,
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.getContrasena(),
            RolUsuario.CLIENTE
        );

        return resultado ? "Usuario actualizado exitosamente" : "Error al actualizar el usuario";
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        boolean resultado = usuarioService.eliminarUsuario(id);
        return resultado ? "Usuario eliminado exitosamente" : "Error al eliminar el usuario";
    }
}

