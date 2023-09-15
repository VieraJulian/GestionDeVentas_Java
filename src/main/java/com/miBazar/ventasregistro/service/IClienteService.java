package com.miBazar.ventasregistro.service;

import com.miBazar.ventasregistro.model.Cliente;
import java.util.List;

public interface IClienteService {
        
    public Cliente crearCliente(Cliente cliente);
    
    public List<Cliente> listaClientes();
    
    public Cliente traerCliente(Long id_cliente);
    
    public void eliminarCliente(Long id_cliente);
    
    public Cliente editarCliente(Long id_cliente, Cliente cliente);
}
