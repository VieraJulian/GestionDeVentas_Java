package com.miBazar.ventasregistro.service;

import com.miBazar.ventasregistro.model.Cliente;
import com.miBazar.ventasregistro.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository cliRepo;
    
    @Override
    public Cliente crearCliente(Cliente cliente) {
        return cliRepo.save(cliente);
    }

    @Override
    public List<Cliente> listaClientes() {
        return cliRepo.findAll();
    }

    @Override
    public Cliente traerCliente(Long id_cliente) {
        return cliRepo.findById(id_cliente).orElse(null);
    }

    @Override
    public void eliminarCliente(Long id_cliente) {
        cliRepo.deleteById(id_cliente);
    }

    @Override
    public Cliente editarCliente(Long id_cliente, Cliente cliente) {
        Cliente cli = this.traerCliente(id_cliente);
        
        cli.setNombre(cliente.getNombre());
        cli.setApellido(cliente.getApellido());
        cli.setDni(cliente.getDni());
        
        return cliRepo.save(cli);
    }

}
