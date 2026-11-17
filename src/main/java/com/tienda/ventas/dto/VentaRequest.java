package com.tienda.ventas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VentaRequest(

        @NotNull
        Long productoId,

        @NotNull
        @Min(1)
        Integer cantidad

) {
}