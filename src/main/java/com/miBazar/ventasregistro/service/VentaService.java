package com.miBazar.ventasregistro.service;

import com.miBazar.ventasregistro.dto.MayorVentaDTO;
import com.miBazar.ventasregistro.dto.VentaXDiaDTO;
import com.miBazar.ventasregistro.dto.VentaYProductosDTO;
import com.miBazar.ventasregistro.model.Cliente;
import com.miBazar.ventasregistro.model.Producto;
import com.miBazar.ventasregistro.model.Venta;
import com.miBazar.ventasregistro.repository.IClienteRepository;
import com.miBazar.ventasregistro.repository.IProductoRepository;
import com.miBazar.ventasregistro.repository.IVentaRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepo;
    @Autowired
    private IClienteRepository cliRepo;
    @Autowired
    private IProductoRepository prodRepo;

    @Override
    public Venta crearVenta(VentaYProductosDTO ventaYProductos) {
        Cliente cliente = cliRepo.findById(ventaYProductos.getVenta().getUnCliente().getId_cliente()).orElse(null);

        if (cliente != null)
        {
            ventaYProductos.getVenta().setUnCliente(cliente);
        }

        List<Producto> productos = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Producto producto : ventaYProductos.getListaProductos())
        {
            Producto prod = prodRepo.findById(producto.getCodigo_producto()).orElse(null);

            if (prod != null && prod.getCantidad_disponible() > 0)
            {
                prod.setCantidad_disponible(prod.getCantidad_disponible() - 1.0);
                prodRepo.save(prod);
                productos.add(prod);
                total = total.add(BigDecimal.valueOf(prod.getCosto()));
            }
        }

        total = total.setScale(2, RoundingMode.HALF_UP);
        ventaYProductos.getVenta().setTotal(total.doubleValue());

        ventaYProductos.getVenta().setListaProductos(productos);

        return ventaRepo.save(ventaYProductos.getVenta());

    }

    @Override
    public Venta editarVenta(VentaYProductosDTO ventaYProductos) {
        Venta venta = ventaRepo.findById(ventaYProductos.getVenta().getCodigo_venta()).orElse(null);

        Cliente cli = cliRepo.findById(ventaYProductos.getVenta().getUnCliente().getId_cliente()).orElse(null);

        if (cli != null)
        {
            venta.setUnCliente(cli);
        }

        venta.setFecha_venta(ventaYProductos.getVenta().getFecha_venta());

        for (Producto prod : venta.getListaProductos())
        {
            Producto prodEncontrado = prodRepo.findById(prod.getCodigo_producto()).orElse(null);
            prodEncontrado.setCantidad_disponible(prodEncontrado.getCantidad_disponible() + 1.0);
            prodRepo.save(prodEncontrado);
        }
        
        List<Producto> listaProductos = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Producto producto : ventaYProductos.getListaProductos())
        {
            Producto prod = prodRepo.findById(producto.getCodigo_producto()).orElse(null);

            if (prod != null && prod.getCantidad_disponible() > 0)
            {
                prod.setCantidad_disponible(prod.getCantidad_disponible() - 1.0);
                prodRepo.save(prod);
                listaProductos.add(prod);
                total = total.add(BigDecimal.valueOf(prod.getCosto()));
            }
        }

        total = total.setScale(2, RoundingMode.HALF_UP);

        venta.setTotal(total.doubleValue());

        venta.setListaProductos(listaProductos);

        return ventaRepo.save(venta);

    }

    @Override
    public List<Venta> listaVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta traerVenta(Long codigo_venta) {
        return ventaRepo.findById(codigo_venta).orElse(null);
    }

    @Override
    public void eliminarVenta(Long codigo_venta) {
        ventaRepo.deleteById(codigo_venta);
    }

    @Override
    public List<Producto> ventaProductos(Long codigo_venta) {
        Venta venta = ventaRepo.findById(codigo_venta).orElse(null);
        return venta.getListaProductos();

    }

    @Override
    public VentaXDiaDTO totalVentasDia(LocalDate fecha) {
        List<Venta> ventas = ventaRepo.findByFecha_venta(fecha);

        BigDecimal totalVentas = BigDecimal.ZERO;

        for (Venta venta : ventas)
        {
            totalVentas = totalVentas.add(BigDecimal.valueOf(venta.getTotal()));
        }

        VentaXDiaDTO ventasPorDia = new VentaXDiaDTO();

        totalVentas = totalVentas.setScale(2, RoundingMode.HALF_UP);

        ventasPorDia.setTotal_ventas(totalVentas.doubleValue());
        ventasPorDia.setCantidad_ventas(ventas.size());
        ventasPorDia.setFecha(fecha);

        return ventasPorDia;
    }

    @Override
    public MayorVentaDTO mayorVenta() {
        Venta venta = ventaRepo.mayorVenta();
        
        MayorVentaDTO mayorVenta = new MayorVentaDTO();
        mayorVenta.setCodigo_venta(venta.getCodigo_venta());
        mayorVenta.setTotal(venta.getTotal());
        mayorVenta.setCantidad_productos(venta.getListaProductos().size());
        mayorVenta.setNombre_cliente(venta.getUnCliente().getNombre());
        mayorVenta.setApellido_cliente(venta.getUnCliente().getApellido());
        
        return mayorVenta;
    }

}
