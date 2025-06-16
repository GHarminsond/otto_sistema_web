# Query: 
# ContextLines: 1

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Producto</title>
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
            <h2>Agregar Nuevo Producto</h2>
            <%
                String mensaje = request.getParameter("status");
                if ("success".equals(mensaje)) {
            %>
            <p style="color: green;">Producto agregado exitosamente</p>
            <% 
                } else if ("error".equals(mensaje)) {
            %>
            <p style="color: red;">Error al agregar el producto</p>
            <% 
                }
            %>
            <form action="ProductoController" method="post">
                <input type="hidden" name="action" value="crear">
                <label for="nombre">Nombre del producto:</label>
                <input type="text" id="nombre" name="nombre" required><br>
                <label for="precio">Precio:</label>
                <input type="number" id="precio" name="precio" step="0.01" required><br>
                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" required></textarea><br>
                <input type="submit" value="Agregar Producto">
            </form>
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