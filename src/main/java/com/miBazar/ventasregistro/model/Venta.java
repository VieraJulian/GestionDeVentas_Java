package com.miBazar.ventasregistro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Venta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @ManyToMany
    @JoinTable(name = "ventas_productos",
               joinColumns = @JoinColumn(name = "id_venta", nullable = false),
               inverseJoinColumns = @JoinColumn(name = "id_producto", nullable = false))
    private List<Producto> listaProductos;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente unCliente;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }
    
}
