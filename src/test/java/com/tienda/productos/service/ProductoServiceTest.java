package com.tienda.productos.service;

import com.tienda.productos.dto.ProductoRequest;
import com.tienda.productos.dto.ProductoResponse;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void debeCrearProducto() {

        ProductoRequest request =
                new ProductoRequest(
                        "Laptop",
                        "Laptop Lenovo",
                        BigDecimal.valueOf(10000),
                        10
                );

        ProductoResponse response =
                productoService.crear(request);

        assertNotNull(response.id());

        assertEquals(
                "Laptop",
                response.nombre());

        assertEquals(
                10,
                response.stock());
    }
}