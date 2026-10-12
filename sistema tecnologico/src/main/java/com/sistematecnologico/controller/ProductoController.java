package com.sistematecnologico.controller;

import com.sistematecnologico.model.Producto;
import com.sistematecnologico.repository.CategoriaRepository;
import com.sistematecnologico.repository.ProductoRepository;
import com.sistematecnologico.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoService productoService;

    public ProductoController(ProductoRepository productoRepository, 
                              CategoriaRepository categoriaRepository, 
                              ProductoService productoService) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoService = productoService;
    }

    @GetMapping("/principal")
    public String mostrarPrincipal(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("nuevoProducto", new Producto());
        return "principal";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoRepository.save(producto);
        return "redirect:/principal";
    }

    @GetMapping("/productos/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElse(null);
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("nuevoProducto", producto != null ? producto : new Producto());
        return "principal";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return "redirect:/principal";
    }

    @GetMapping("/productos/filtrar")
    public String filtrarPorPrecio(@RequestParam("min") Double min, 
                                   @RequestParam("max") Double max, 
                                   Model model) {
        List<Producto> productosFiltrados = productoService.obtenerProductosPorPrecio(min, max);
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("productos", productosFiltrados);
        model.addAttribute("nuevoProducto", new Producto());
        return "principal";
    }
}