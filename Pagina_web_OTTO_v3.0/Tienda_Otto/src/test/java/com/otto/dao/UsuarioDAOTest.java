
package com.otto.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.otto.model.RolUsuario;
import com.otto.model.Usuario;

public class UsuarioDAOTest {

    @Test
    public void testInsertarYConsultarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario("Prueba", "prueba@correo.com", "1234", RolUsuario.CLIENTE);
        dao.insertarUsuario(usuario);
        Usuario recuperado = dao.consultarUsuarioPorCorreo("prueba@correo.com");
        assertNotNull(recuperado);
        assertEquals("Prueba", recuperado.getNombre());
    }
}
