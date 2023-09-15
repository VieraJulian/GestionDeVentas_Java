package com.miBazar.ventasregistro.controller;

import com.miBazar.ventasregistro.dto.MayorVentaDTO;
import com.miBazar.ventasregistro.dto.VentaXDiaDTO;
import com.miBazar.ventasregistro.dto.VentaYProductosDTO;
import com.miBazar.ventasregistro.model.Producto;
import com.miBazar.ventasregistro.model.Venta;
import com.miBazar.ventasregistro.service.IVentaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {

    @Autowired
    private IVentaService ventaServ;

    @PostMapping("/ventas/crear")
    public ResponseEntity<Venta> crearVenta(@RequestBody VentaYProductosDTO ventaYProductos) {
        try
        {
            Venta ventaCreada = ventaServ.crearVenta(ventaYProductos);
            return new ResponseEntity<>(ventaCreada, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ventas")
    public ResponseEntity<List<Venta>> traerVentas() {
        try
        {
            List<Venta> ventas = ventaServ.listaVentas();
            return new ResponseEntity<>(ventas, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ventas/{codigo_venta}")
    public ResponseEntity<Venta> traerVenta(@PathVariable Long codigo_venta) {
        try
        {
            Venta venta = ventaServ.traerVenta(codigo_venta);
            return new ResponseEntity<>(venta, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long codigo_venta) {
        try
        {
            ventaServ.eliminarVenta(codigo_venta);
            return new ResponseEntity<>("Venta eliminada correctamente.", HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ventas/productos/{codigo_venta}")
    public ResponseEntity<List<Producto>> ventaProductos(@PathVariable Long codigo_venta) {
        try
        {
            List<Producto> listaProductos = ventaServ.ventaProductos(codigo_venta);
            return new ResponseEntity<>(listaProductos, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ventas/fecha/{fecha_venta}")
    public ResponseEntity<VentaXDiaDTO> ventaProductos(@PathVariable String fecha_venta) {
        try
        {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(fecha_venta, formatter);

            VentaXDiaDTO ventaXDia = ventaServ.totalVentasDia(fecha);
            return new ResponseEntity<>(ventaXDia, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ventas/editar")
    public ResponseEntity<Venta> editarVenta(@RequestBody VentaYProductosDTO ventaYProductos) {
        try
        {
            Venta venta = ventaServ.editarVenta(ventaYProductos);
            return new ResponseEntity<>(venta, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ventas/mayor_venta")
    public ResponseEntity<MayorVentaDTO> mayorVenta() {
        try
        {
            MayorVentaDTO mayorVenta = ventaServ.mayorVenta();
            return new ResponseEntity<>(mayorVenta, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
