package com.tienda.ventas.controller;

import com.tienda.ventas.dto.VentaRequest;
import com.tienda.ventas.dto.VentaResponse;
import com.tienda.ventas.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "Gestión de ventas")
public class VentaController {

    private final VentaService service;

    @GetMapping
    @Operation(summary = "Listar ventas")
    public List<VentaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar venta")
    public VentaResponse obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar venta")
    public VentaResponse registrar(@Valid @RequestBody VentaRequest request) {
        return service.registrar(request);
    }
}