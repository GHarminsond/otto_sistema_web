package com.otto.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.otto.model.Producto;

public class ProductoDAOTest {

    @Test
    public void testInsertarYListarProducto() {
        ProductoDAO dao = new ProductoDAO();

        // Crear producto de prueba
        Producto producto = new Producto();
        producto.setNombre("Producto Test");
        producto.setPrecio(99.99);
        producto.setDescripcion("Este es un producto de prueba");

        // Insertar producto
        boolean insertado = dao.insertarProducto(producto);
        assertTrue(insertado, "El producto debe insertarse correctamente");

        // Listar productos
        List<Producto> productos = dao.listarProductos();
        assertNotNull(productos, "La lista de productos no debe ser nula");
        assertTrue(productos.size() > 0, "Debe haber al menos un producto");

        // Verificar que el producto insertado esté presente
        boolean encontrado = productos.stream()
                .anyMatch(p -> "Producto Test".equals(p.getNombre()));
        assertTrue(encontrado, "El producto de prueba debe estar en la lista");
    }
}

