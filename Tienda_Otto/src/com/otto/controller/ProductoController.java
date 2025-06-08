# Query: 
# ContextLines: 1

package com.otto.controller;

import com.otto.model.Producto;
import com.otto.service.ProductoService;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para manejar las operaciones de productos
 */
public class ProductoController {
    private ProductoService productoService;
    private Scanner scanner;

    public ProductoController() {
        this.productoService = new ProductoService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú de gestión de productos
     */
    public void mostrarMenuProductos() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS OTTO® ===");
            System.out.println("1. Crear producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    crearProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    buscarProductoPorId();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    /**
     * Crea un nuevo producto
     */
    private void crearProducto() {
        System.out.println("\n--- Crear Nuevo Producto ---");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Precio: $");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        
        if (productoService.crearProducto(nombre, precio, descripcion)) {
            System.out.println("✓ Producto creado exitosamente");
        } else {
            System.out.println("✗ Error al crear el producto");
        }
    }

    /**
     * Lista todos los productos
     */
    private void listarProductos() {
        System.out.println("\n--- Lista de Productos ---");
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    /**
     * Busca un producto por ID
     */
    private void buscarProductoPorId() {
        System.out.println("\n--- Buscar Producto ---");
        System.out.print("Ingrese el ID del producto: ");
        int id = scanner.nextInt();
        
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    /**
     * Actualiza un producto existente
     */
    private void actualizarProducto() {
        System.out.println("\n--- Actualizar Producto ---");
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        Producto productoExistente = productoService.obtenerProductoPorId(id);
        if (productoExistente == null) {
            System.out.println("Producto no encontrado");
            return;
        }
        
        System.out.println("Datos actuales: " + productoExistente);
        System.out.print("Nuevo nombre (actual: " + productoExistente.getNombre() + "): ");
        String nombre = scanner.nextLine();
        
        System.out.print("Nuevo precio (actual: $" + productoExistente.getPrecio() + "): $");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        
        System.out.print("Nueva descripción (actual: " + productoExistente.getDescripcion() + "): ");
        String descripcion = scanner.nextLine();
        
        if (productoService.actualizarProducto(id, nombre, precio, descripcion)) {
            System.out.println("✓ Producto actualizado exitosamente");
        } else {
            System.out.println("✗ Error al actualizar el producto");
        }
    }

    /**
     * Elimina un producto
      */
    private void eliminarProducto() {
        System.out.println("\n--- Eliminar Producto ---");
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado");
            return;
        }
        
        System.out.println("Producto a eliminar: " + producto);
        System.out.print("¿Está seguro de eliminar este producto? (s/n): ");
        String confirmacion = scanner.next();
        
        if (confirmacion.equalsIgnoreCase("s")) {
            if (productoService.eliminarProducto(id)) {
                System.out.println("✓ Producto eliminado exitosamente");
            } else {
                System.out.println("✗ Error al eliminar el producto");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
}