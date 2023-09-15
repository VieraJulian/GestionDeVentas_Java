package com.miBazar.ventasregistro.controller;

import com.miBazar.ventasregistro.model.Producto;
import com.miBazar.ventasregistro.service.IProductoService;
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
public class ProductoController {

    @Autowired
    private IProductoService prodServ;

    @PostMapping("/productos/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto prod) {
        try
        {
            Producto prodCreado = prodServ.crearProducto(prod);
            return new ResponseEntity<>(prodCreado, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> traerProductos() {
        try
        {
            List<Producto> listaProductos = prodServ.listaProductos();
            return new ResponseEntity<>(listaProductos, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productos/{codigo_producto}")
    public ResponseEntity<Producto> traerProducto(@PathVariable Long codigo_producto) {
        try
        {
            Producto prod = prodServ.traerProducto(codigo_producto);
            return new ResponseEntity<>(prod, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long codigo_producto) {
        try
        {
            prodServ.eliminarProducto(codigo_producto);
            return new ResponseEntity<>("Producto eliminado correctamente.", HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/productos/editar/{codigo_producto}")
    public ResponseEntity<Producto> editarProducto(@PathVariable Long codigo_producto, @RequestBody Producto prod) {
        try
        {
            Producto producto = prodServ.editarProducto(codigo_producto, prod);
            return new ResponseEntity<>(producto, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productos/falta_stock")
    public ResponseEntity<List<Producto>> faltaStock() {
        try
        {
            List<Producto> listaProductos = prodServ.faltaStock();
            return new ResponseEntity<>(listaProductos, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
