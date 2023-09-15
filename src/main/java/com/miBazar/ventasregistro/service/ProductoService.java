package com.miBazar.ventasregistro.service;

import com.miBazar.ventasregistro.model.Producto;
import com.miBazar.ventasregistro.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository prodRepo;
    
    @Override
    public Producto crearProducto(Producto producto) {
        return prodRepo.save(producto);
    }

    @Override
    public List<Producto> listaProductos() {
        return prodRepo.findAll();
    }

    @Override
    public Producto traerProducto(Long codigo_producto) {
        return prodRepo.findById(codigo_producto).orElse(null);
    }

    @Override
    public void eliminarProducto(Long codigo_producto) {
        prodRepo.deleteById(codigo_producto);
    }

    @Override
    public Producto editarProducto(Long codigo_producto, Producto producto) {
        Producto prod = prodRepo.findById(codigo_producto).orElse(null);
        
        prod.setNombre(producto.getNombre());
        prod.setMarca(producto.getMarca());
        prod.setCosto(producto.getCosto());
        prod.setCantidad_disponible(producto.getCantidad_disponible());
        
        return prodRepo.save(prod);
    }

    @Override
    public List<Producto> faltaStock() {
        return prodRepo.findByCantidad_disponibleLessThan(5.0);
    }

}
