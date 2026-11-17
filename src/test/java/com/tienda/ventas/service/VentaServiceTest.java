package com.tienda.ventas.service;

import com.tienda.productos.dto.ProductoRequest;
import com.tienda.productos.dto.ProductoResponse;
import com.tienda.productos.service.ProductoService;

import com.tienda.ventas.dto.VentaRequest;
import com.tienda.ventas.dto.VentaResponse;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

@SpringBootTest
class VentaServiceTest {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private VentaService ventaService;

    @Test
    void debeRegistrarVenta() {

        ProductoResponse producto =
                productoService.crear(
                        new ProductoRequest(
                                "Mouse",
                                "Logitech",
                                BigDecimal.valueOf(500),
                                20
                        )
                );

        VentaResponse venta =
                ventaService.registrar(
                        new VentaRequest(
                                producto.id(),
                                2
                        )
                );

        assertNotNull(
                venta.id());

        assertEquals(
                2,
                venta.cantidad());
    }
}