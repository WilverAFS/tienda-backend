package com.tienda.productos.controller;

import com.tienda.productos.dto.ProductoRequest;
import com.tienda.productos.dto.ProductoResponse;
import com.tienda.productos.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos",
     description = "Operaciones CRUD de productos")
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    @Operation(summary = "Listar productos")
    public List<ProductoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public ProductoResponse obtenerPorId(
            @PathVariable Long id) {

        return service.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear producto")
    public ProductoResponse crear(
            @Valid @RequestBody ProductoRequest request) {

        return service.crear(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto")
    public ProductoResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request) {

        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar producto")
    public void eliminar(
            @PathVariable Long id) {

        service.eliminar(id);
    }
}