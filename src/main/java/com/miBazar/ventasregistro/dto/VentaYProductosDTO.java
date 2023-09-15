package com.miBazar.ventasregistro.dto;

import com.miBazar.ventasregistro.model.Producto;
import com.miBazar.ventasregistro.model.Venta;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaYProductosDTO {
    
    private Venta venta;
    private List<Producto> listaProductos;

    public VentaYProductosDTO() {
    }

    public VentaYProductosDTO(Venta venta, List<Producto> listaProductos) {
        this.venta = venta;
        this.listaProductos = listaProductos;
    }
    
}
