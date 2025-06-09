# Query: 
# ContextLines: 1

-- Script SQL para crear la base de datos y tablas

CREATE DATABASE IF NOT EXISTS tienda_otto;
USE tienda_otto;

-- Tabla de productos
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    descripcion TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('cliente', 'vendedor', 'administrador') DEFAULT 'cliente',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insertar datos de ejemplo
INSERT INTO productos (nombre, precio, descripcion) VALUES
('Zapatillas para correr', 899.99, 'Muy comodas para largas caminatas'),
('Zapatos casuales', 79.99, 'Perfectos para calcuquier ocasion'),
('Zapatos para el gym', 129.99, 'No vas a encontrar algo mejor para un dia de gym');

INSERT INTO usuarios (nombre, correo, contrasena, rol) VALUES
('Juan Pérez', 'juan@ejemplo.com', '123456', 'cliente'),
('María García', 'maria@ejemplo.com', '123456', 'vendedor'),
('Carlos Admin', 'admin@otto.com', 'admin123', 'administrador'),
('Ana López', 'ana@ejemplo.com', '123456', 'cliente'),
('Roberto Venta', 'roberto@otto.com', 'venta123', 'vendedor');

-- Consultas útiles para verificar datos
-- SELECT * FROM productos;
-- SELECT * FROM usuarios;
-- SELECT COUNT(*) as total_productos FROM productos;
-- SELECT COUNT(*) as total_usuarios FROM usuarios;
-- SELECT rol, COUNT(*) as cantidad FROM usuarios GROUP BY rol;