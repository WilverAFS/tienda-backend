package com.tienda.ventas.service;

import com.tienda.exception.ProductoNoEncontradoException;
import com.tienda.exception.StockInsuficienteException;

import com.tienda.productos.entity.Producto;
import com.tienda.productos.repository.ProductoRepository;

import com.tienda.ventas.dto.VentaRequest;
import com.tienda.ventas.dto.VentaResponse;
import com.tienda.ventas.entity.Venta;
import com.tienda.ventas.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<VentaResponse> listar() {

        return ventaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public VentaResponse obtenerPorId(Long id) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada"));

        return toResponse(venta);
    }

    @Override
    @Transactional
    public VentaResponse registrar(VentaRequest request) {

        Producto producto = productoRepository
                .findById(request.productoId())
                .orElseThrow(() ->
                        new ProductoNoEncontradoException(
                                request.productoId()));

        if (producto.getStock() < request.cantidad()) {

            throw new StockInsuficienteException(
                    producto.getNombre());
        }

        BigDecimal total = producto.getPrecio()
                .multiply(
                        BigDecimal.valueOf(
                                request.cantidad()));

        producto.setStock(
                producto.getStock()
                        - request.cantidad());

        productoRepository.save(producto);

        Venta venta = Venta.builder()
                .producto(producto)
                .cantidad(request.cantidad())
                .total(total)
                .fechaVenta(LocalDateTime.now())
                .build();

        return toResponse(
                ventaRepository.save(venta));
    }

    private VentaResponse toResponse(Venta venta) {

        return new VentaResponse(
                venta.getId(),
                venta.getProducto().getId(),
                venta.getProducto().getNombre(),
                venta.getCantidad(),
                venta.getProducto().getPrecio(),
                venta.getTotal(),
                venta.getFechaVenta()
        );
    }
}