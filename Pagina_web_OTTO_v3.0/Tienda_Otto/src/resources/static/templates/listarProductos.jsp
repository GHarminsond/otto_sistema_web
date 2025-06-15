<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Listar Productos</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}">
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
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Descripción</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr th:each="producto : ${productos}">
                        <td th:text="${producto.id}"></td>
                        <td th:text="${producto.nombre}"></td>
                        <td th:text="${producto.precio}"></td>
                        <td th:text="${producto.descripcion}"></td>
                        <td>
                            <a th:href="@{/productos/actualizar(id=${producto.id})}">Actualizar</a>
                            <a th:href="@{/productos/eliminar(id=${producto.id})}">Eliminar</a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <a th:href="@{/productos/nuevo}">Agregar Nuevo Producto</a>
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