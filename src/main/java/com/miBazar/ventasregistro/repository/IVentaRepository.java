package com.miBazar.ventasregistro.repository;

import com.miBazar.ventasregistro.model.Venta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long>{
    
    @Query("SELECT v FROM Venta v WHERE v.fecha_venta = :fecha")
    public List<Venta> findByFecha_venta(LocalDate fecha);
    
    @Query(value = "SELECT * FROM Venta WHERE total = (SELECT MAX(total) FROM Venta) ORDER BY fecha_venta DESC LIMIT 1", nativeQuery = true)
    public Venta mayorVenta();
    
}
