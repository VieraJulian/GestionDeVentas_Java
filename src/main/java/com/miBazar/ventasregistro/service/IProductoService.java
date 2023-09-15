package com.miBazar.ventasregistro.service;

import com.miBazar.ventasregistro.model.Producto;
import java.util.List;

public interface IProductoService {

    public Producto crearProducto(Producto producto);

    public List<Producto> listaProductos();

    public Producto traerProducto(Long codigo_producto);

    public void eliminarProducto(Long codigo_producto);

    public Producto editarProducto(Long codigo_producto, Producto producto);

    public List<Producto> faltaStock();
}
