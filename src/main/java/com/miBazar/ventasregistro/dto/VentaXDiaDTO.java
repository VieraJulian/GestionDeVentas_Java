package com.miBazar.ventasregistro.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaXDiaDTO {
    
    private Double total_ventas;
    private int cantidad_ventas;
    private LocalDate fecha;

    public VentaXDiaDTO() {
    }

    public VentaXDiaDTO(Double total_ventas, int cantidad_ventas, LocalDate fecha) {
        this.total_ventas = total_ventas;
        this.cantidad_ventas = cantidad_ventas;
        this.fecha = fecha;
    }
    
}
