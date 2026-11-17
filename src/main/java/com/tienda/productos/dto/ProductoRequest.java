package com.tienda.productos.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        String nombre,

        @Size(max = 255)
        String descripcion,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01")
        BigDecimal precio,

        @NotNull(message = "El stock es obligatorio")
        @Min(value = 0)
        Integer stock

) {
}