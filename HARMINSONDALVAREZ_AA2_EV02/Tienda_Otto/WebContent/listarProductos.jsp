<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.otto.model.Producto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listar Productos</title>
    <link rel="stylesheet" href="resources/styles.css">
</head>
<body>
    <header>
        <h1>OTTO®</h1>
        <p class="subtitulo">Calzado a tu estilo</p>
        <nav>
            <ul>
                <li><a href="index.html">Inicio</a></li>
                <li><a href="productos.html">Productos</a></li>
                <li><a href="carrito.html">Carrito</a></li>
                <li><a href="cuenta.html">Cuenta</a></li>
                <li><a href="contacto.html">Contacto</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section>
            <h2>Lista de Productos</h2>
            <%
                List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                if (productos != null && !productos.isEmpty()) {
            %>
            <ul>
                <% for (Producto producto : productos) { %>
                    <li><%= producto.getNombre() %> - $<%= producto.getPrecio() %></li>
                <% } %>
            </ul>
            <% } else { %>
            <p>No hay productos registrados</p>
            <% } %>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 OTTO® - Todos los derechos reservados.</p>
        <p><strong>Dirección:</strong> Cr.110a N°103-07, Ciudad Bogotá</p>
        <p><strong>Teléfono:</strong> +57 322 347 5860</p>
        <p><strong>Correo electrónico:</strong> tiendaOTTO01@gmail.com</p>
    </footer>
</body>
</html>