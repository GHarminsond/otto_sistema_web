package com.otto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otto.model.Producto;
import com.otto.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    // GET - Listar todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    // GET - Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
        return ResponseEntity.ok(producto);
    }

    // POST - Crear nuevo producto
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        if (!producto.esValido()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos: nombre, descripción y precio son obligatorios.");
        }
        Producto creado = productoService.crearProducto(
                producto.getNombre(),
                producto.getPrecio(),
                producto.getDescripcion()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // PUT - Actualizar producto existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        if (!producto.esValido()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos: nombre, descripción y precio son obligatorios.");
        }

        Producto actualizado = productoService.actualizarProducto(
                id,
                producto.getNombre(),
                producto.getPrecio(),
                producto.getDescripcion()
        );
        return ResponseEntity.ok(actualizado);
    }

    // DELETE - Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.ok().build();
    }
}

