package com.sistematecnologico.repository;

import com.sistematecnologico.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :minPrecio AND :maxPrecio")
    List<Producto> buscarPorRangoDePrecio(@Param("minPrecio") Double minPrecio, @Param("maxPrecio") Double maxPrecio);

    List<Producto> findByCategoriaId(Long categoriaId);
}