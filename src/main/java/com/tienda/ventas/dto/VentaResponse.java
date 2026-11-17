package com.tienda.ventas.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VentaResponse(
        Long id,
        Long productoId,
        String producto,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal total,
        LocalDateTime fechaVenta
) {
}