package com.otto.service;

import com.otto.model.Producto;
import com.otto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public boolean crearProducto(String nombre, double precio, String descripcion) {
        if (validarDatosProducto(nombre, precio, descripcion)) {
            Producto producto = new Producto(nombre, precio, descripcion);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public boolean actualizarProducto(int id, String nombre, double precio, String descripcion) {
        if (validarDatosProducto(nombre, precio, descripcion)) {
            Producto producto = new Producto(id, nombre, precio, descripcion);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    public boolean eliminarProducto(int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
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