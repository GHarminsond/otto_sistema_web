# Query: 
# ContextLines: 1

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