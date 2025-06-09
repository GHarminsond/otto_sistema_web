package com.otto.service;

import com.otto.dao.ProductoDAO;
import com.otto.model.Producto;
import java.util.List;

public class ProductoService {
    private ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    public boolean crearProducto(String nombre, double precio, String descripcion) {
        if (validarDatosProducto(nombre, precio, descripcion)) {
            Producto producto = new Producto(nombre, precio, descripcion);
            return productoDAO.insertarProducto(producto);
        }
        return false;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.consultarTodosLosProductos();
    }

    public Producto obtenerProductoPorId(int id) {
        return productoDAO.consultarProductoPorId(id);
    }

    public boolean actualizarProducto(int id, String nombre, double precio, String descripcion) {
        if (validarDatosProducto(nombre, precio, descripcion)) {
            Producto producto = new Producto(id, nombre, precio, descripcion);
            return productoDAO.actualizarProducto(producto);
        }
        return false;
    }

    public boolean eliminarProducto(int id) {
        return productoDAO.eliminarProducto(id);
    }

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