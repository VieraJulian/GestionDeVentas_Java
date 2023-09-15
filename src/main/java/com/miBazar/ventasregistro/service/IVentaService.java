package com.miBazar.ventasregistro.service;

import com.miBazar.ventasregistro.dto.MayorVentaDTO;
import com.miBazar.ventasregistro.dto.VentaXDiaDTO;
import com.miBazar.ventasregistro.dto.VentaYProductosDTO;
import com.miBazar.ventasregistro.model.Producto;
import com.miBazar.ventasregistro.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    
    public Venta crearVenta(VentaYProductosDTO ventaYProductos);
    
    public List<Venta> listaVentas();
    
    public Venta traerVenta(Long codigo_venta);
    
    public void eliminarVenta(Long codigo_venta);
    
    public Venta editarVenta(VentaYProductosDTO ventaYProductos);
    
    public List<Producto> ventaProductos(Long codigo_venta);
    
    public VentaXDiaDTO totalVentasDia(LocalDate fecha);
    
    public MayorVentaDTO mayorVenta();
}
