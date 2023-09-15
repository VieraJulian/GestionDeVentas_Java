package com.miBazar.ventasregistro.controller;

import com.miBazar.ventasregistro.model.Cliente;
import com.miBazar.ventasregistro.service.IClienteService;
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
public class ClienteController {

    @Autowired
    private IClienteService cliServ;

    @PostMapping("/clientes/crear")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        try
        {
            Cliente cli = cliServ.crearCliente(cliente);
            return new ResponseEntity<>(cli, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> traerClientes() {
        try
        {
            List<Cliente> listClientes = cliServ.listaClientes();
            return new ResponseEntity<>(listClientes, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes/{id_cliente}")
    public ResponseEntity<Cliente> traerCliente(@PathVariable Long id_cliente) {
        try
        {
            Cliente cliente = cliServ.traerCliente(id_cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id_cliente) {
        try
        {
            cliServ.eliminarCliente(id_cliente);
            return new ResponseEntity<>("Cliente eliminado correctamente.", HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clientes/editar/{id_cliente}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Long id_cliente, @RequestBody Cliente cliente) {
        try
        {
            Cliente cli = cliServ.editarCliente(id_cliente, cliente);
            return new ResponseEntity<>(cli, HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
