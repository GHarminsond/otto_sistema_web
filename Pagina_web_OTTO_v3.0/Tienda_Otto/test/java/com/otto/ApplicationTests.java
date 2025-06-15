package com.otto;

import com.otto.model.Producto;
import com.otto.repository.ProductoRepository;
import com.otto.service.ProductoService;
import com.otto.controller.ProductoController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
        // Verifica que el contexto de Spring Boot se cargue correctamente
    }
}

// Prueba unitaria del servicio ProductoService
class ProductoServiceTest {

    private ProductoRepository productoRepository;
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        productoRepository = mock(ProductoRepository.class);
        productoService = new ProductoService(productoRepository);
    }

    @Test
    void testBuscarProductoPorId() {
