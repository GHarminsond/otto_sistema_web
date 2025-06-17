package com.otto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otto.model.Producto;
import com.otto.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(String nombre, double precio, String descripcion) {
        if (validarDatosProducto(nombre, precio, descripcion)) {
            Producto producto = new Producto(nombre, precio, descripcion);
            return productoRepository.save(producto); // ← retorna el objeto Producto
        }
        return null; // ← para indicar que hubo error en validación
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        return optionalProducto.orElse(null);
    }

    public Producto actualizarProducto(Long id, String nombre, double precio, String descripcion) {
        if (productoRepository.existsById(id) && validarDatosProducto(nombre, precio, descripcion)) {
            Producto producto = new Producto(id, nombre, precio, descripcion);
            return productoRepository.save(producto); // ← retorna el objeto actualizado
        }
        return null;
    }

    public boolean eliminarProducto(Long id) {
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
