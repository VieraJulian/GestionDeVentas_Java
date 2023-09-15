package com.miBazar.ventasregistro.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MayorVentaDTO {
    
    private Long codigo_venta;
    private double total;
    private int cantidad_productos;
    private String nombre_cliente;
    private String apellido_cliente;

    public MayorVentaDTO() {
    }

    public MayorVentaDTO(Long codigo_venta, double total, int cantidad_productos, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantidad_productos = cantidad_productos;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }
    
}
