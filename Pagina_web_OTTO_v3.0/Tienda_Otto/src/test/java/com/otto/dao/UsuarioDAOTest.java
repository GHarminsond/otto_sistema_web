
package com.otto.dao;

import com.otto.model.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {

    @Test
    public void testInsertarYConsultarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario("Prueba", "prueba@correo.com", "1234");
        dao.insertarUsuario(usuario);
        Usuario recuperado = dao.buscarPorCorreo("prueba@correo.com");
        assertNotNull(recuperado);
        assertEquals("Prueba", recuperado.getNombre());
    }
}
