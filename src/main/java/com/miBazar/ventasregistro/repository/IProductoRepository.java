package com.miBazar.ventasregistro.repository;

import com.miBazar.ventasregistro.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.cantidad_disponible < :maxCantidad")
    List<Producto> findByCantidad_disponibleLessThan(Double maxCantidad);

}
