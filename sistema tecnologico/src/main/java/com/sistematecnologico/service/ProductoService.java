package com.sistematecnologico.service;

import com.sistematecnologico.model.Producto;
import com.sistematecnologico.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerProductosPorPrecio(Double minPrecio, Double maxPrecio) {
        return productoRepository.buscarPorRangoDePrecio(minPrecio, maxPrecio);
    }
}