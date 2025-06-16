let carrito = [];

function agregarAlCarrito(nombre, precio) {
    carrito.push({ nombre, precio });
    actualizarCarrito();
}

function actualizarCarrito() {
    let lista = document.getElementById("lista-carrito");
    lista.innerHTML = "";
    carrito.forEach((producto, index) => {
        let item = document.createElement("li");
        item.innerHTML = `${producto.nombre} - $${producto.precio} 
            <button onclick="eliminarDelCarrito(${index})">X</button>`;
        lista.appendChild(item);
    });

    document.getElementById("contador-carrito").innerText = carrito.length;
}

function eliminarDelCarrito(index) {
    carrito.splice(index, 1);
    actualizarCarrito();
}

function vaciarCarrito() {
    carrito = [];
    actualizarCarrito();
}

function mostrarSeccion(seccionId) {
    document.querySelectorAll(".seccion").forEach(seccion => {
        seccion.classList.remove("activa");
    });
    document.getElementById(seccionId).classList.add("activa");
}

