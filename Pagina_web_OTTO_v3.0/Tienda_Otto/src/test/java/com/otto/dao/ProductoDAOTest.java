package com.otto.dao;

import com.otto.model.Producto;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoDAOTest {

    @Test
    public void testListarProductos() {
        ProductoDAO dao = new ProductoDAO();
        List<Producto> productos = dao.listarProductos();

        assertNotNull(productos, "La lista no debe ser nula");
        assertTrue(productos.size() > 0, "Debe haber al menos un producto en la base de datos");

        // Validar un producto si existe
        Producto p = productos.get(0);
        assertNotNull(p.getNombre());
        assertTrue(p.getPrecio() > 0);
    }
}
