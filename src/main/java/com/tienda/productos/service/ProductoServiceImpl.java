package com.tienda.productos.service;

import com.tienda.productos.dto.ProductoRequest;
import com.tienda.productos.dto.ProductoResponse;
import com.tienda.productos.entity.Producto;
import com.tienda.productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tienda.exception.ProductoNoEncontradoException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    @Override
    public List<ProductoResponse> listar() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ProductoResponse obtenerPorId(Long id) {

        Producto producto = repository.findById(id)
                .orElseThrow(() ->
                        new ProductoNoEncontradoException(id));

        return toResponse(producto);
    }

    @Override
    public ProductoResponse crear(ProductoRequest request) {

        Producto producto = Producto.builder()
                .nombre(request.nombre())
                .descripcion(request.descripcion())
                .precio(request.precio())
                .stock(request.stock())
                .build();

        return toResponse(repository.save(producto));
    }

    @Override
    public ProductoResponse actualizar(Long id,
                                       ProductoRequest request) {

        Producto producto = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));

        producto.setNombre(request.nombre());
        producto.setDescripcion(request.descripcion());
        producto.setPrecio(request.precio());
        producto.setStock(request.stock());

        return toResponse(repository.save(producto));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }

        repository.deleteById(id);
    }

    private ProductoResponse toResponse(Producto producto) {

        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock()
        );
    }
}