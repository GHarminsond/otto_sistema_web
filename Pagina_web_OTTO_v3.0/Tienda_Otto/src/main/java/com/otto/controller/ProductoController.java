package com.otto.controller;

import com.otto.model.Producto;
import com.otto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return "listarProductos"; // Asegúrate de tener listarProductos.html
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "formularioProducto"; // Renombrado para evitar confusión
    }

    @PostMapping
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoService.crearProducto(producto.getNombre(), producto.getPrecio(), producto.getDescripcion());
        return "redirect:/productos";
    }

    @GetMapping("/buscar")
    public String buscarProducto(@RequestParam("id") Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "verProducto"; // Asegúrate de tener verProducto.html
        } else {
            model.addAttribute("mensaje", "Producto no encontrado");
            return "error"; // error.html con mensaje
        }
    }

    @GetMapping("/editar")
    public String mostrarFormularioActualizarProducto(@RequestParam("id") Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "actualizarProducto"; // actualizarProducto.html
        } else {
            model.addAttribute("mensaje", "Producto no encontrado");
            return "error";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute("producto") Producto producto) {
        productoService.actualizarProducto(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getDescripcion());
        return "redirect:/productos";
    }

    @GetMapping("/eliminar")
    public String eliminarProducto(@RequestParam("id") Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
}
