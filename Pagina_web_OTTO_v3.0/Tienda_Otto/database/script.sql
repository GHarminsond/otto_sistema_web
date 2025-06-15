
-- Script para crear tablas y datos de prueba

CREATE DATABASE IF NOT EXISTS tienda_otto;
USE tienda_otto;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100) UNIQUE,
    contrasena VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion TEXT,
    precio DECIMAL(10, 2),
    imagen VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS contacto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100),
    mensaje TEXT
);

-- Datos de prueba
INSERT INTO usuarios (nombre, correo, contrasena) VALUES ('Luis', 'luis@example.com', '1234');
INSERT INTO productos (nombre, descripcion, precio, imagen) VALUES ('Producto 1', 'Descripción del producto 1', 100.00, 'producto1.jpg');
