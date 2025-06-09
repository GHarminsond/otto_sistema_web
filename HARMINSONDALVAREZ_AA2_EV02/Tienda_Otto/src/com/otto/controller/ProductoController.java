package com.otto.controller;

import com.otto.model.Producto;
import com.otto.service.ProductoService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductoController")
public class ProductoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductoService productoService = new ProductoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("crear".equals(action)) {
            handleCrearProducto(request, response);
        } else if ("listar".equals(action)) {
            handleListarProductos(request, response);
        } else if ("buscar".equals(action)) {
            handleBuscarProducto(request, response);
        } else if ("actualizar".equals(action)) {
            handleActualizarProducto(request, response);
        } else if ("eliminar".equals(action)) {
            handleEliminarProducto(request, response);
        }
    }

    private void handleCrearProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String descripcion = request.getParameter("descripcion");

        boolean resultado = productoService.crearProducto(nombre, precio, descripcion);

        if (resultado) {
            response.getWriter().println("Producto creado exitosamente");
        } else {
            response.getWriter().println("Error al crear el producto");
        }
    }

    private void handleListarProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("/listarProductos.jsp").forward(request, response);
    }

    private void handleBuscarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Producto producto = productoService.obtenerProductoPorId(id);

        if (producto != null) {
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/producto.jsp").forward(request, response);
        } else {
            response.getWriter().println("Producto no encontrado");
        }
    }

    private void handleActualizarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String descripcion = request.getParameter("descripcion");

        boolean resultado = productoService.actualizarProducto(id, nombre, precio, descripcion);

        if (resultado) {
            response.getWriter().println("Producto actualizado exitosamente");
        } else {
            response.getWriter().println("Error al actualizar el producto");
        }
    }

    private void handleEliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean resultado = productoService.eliminarProducto(id);

        if (resultado) {
            response.getWriter().println("Producto eliminado exitosamente");
        } else {
            response.getWriter().println("Error al eliminar el producto");
        }
    }
}