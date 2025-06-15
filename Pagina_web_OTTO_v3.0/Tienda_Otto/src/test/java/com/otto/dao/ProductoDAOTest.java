
package com.otto.dao;

import com.otto.model.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ProductoDAOTest {

    @Test
    public void testListarProductos() {
        ProductoDAO dao = new ProductoDAO();
        List<Producto> productos = dao.listarProductos();
        assertNotNull(productos);
        assertTrue(productos.size() > 0);
    }
}
