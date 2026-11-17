package com.tienda.ventas.service;

import com.tienda.ventas.dto.VentaRequest;
import com.tienda.ventas.dto.VentaResponse;

import java.util.List;

public interface VentaService {

    List<VentaResponse> listar();

    VentaResponse obtenerPorId(Long id);

    VentaResponse registrar(VentaRequest request);
}