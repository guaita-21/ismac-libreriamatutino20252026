package com.distribuida.dao;

import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value= false)

public class ClienteTestIntegracion {

    @Autowired
    public ClienteDAO clienteDAO;

    @Test
    public void findAll() {
        List<Cliente> clientes = clienteDAO.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size()>0);
        for (Cliente item : clientes) {
            System.out.println(item.toString());
        }

    }
    @Test
    public  void findOne(){

        Optional<Cliente> cliente = clienteDAO.findById(1);
        assertTrue(cliente.isPresent(),"El cliente con id = 1 si existe ");
        System.out.println(cliente.toString());

        }

        @Test
    public void save(){

        Cliente cliente = new Cliente(0,"170123456","Juan2","Rodriguez","Av.Quito","0956895632","frg@gmail.com");

        Cliente clienteGuardado = clienteDAO.save(cliente);
        assertNotNull(clienteGuardado,"El cliente nuevo se guardo");
        assertEquals("170123456", clienteGuardado.getCedula());
        assertEquals("Juan2",clienteGuardado.getNombre());

    }
    @Test
    public  void update(){
        Optional<Cliente> cliente = clienteDAO.findById(40);
        assertTrue(cliente.isPresent(),"El cliente existe en BD");

        cliente.orElse(null).setCedula("1752459856");
        cliente.orElse(null).setNombre("Sofia");
        cliente.orElse(null).setApellido("Cortez");
        cliente.orElse(null).setDireccion("Av.Tumbaco");
        cliente.orElse(null).setTelefono("0956326578");
        cliente.orElse(null).setCorreo("sofia23@gmail.com");

        Cliente clienteActualizado = clienteDAO.save(cliente.orElse(null));
        assertNotNull(clienteActualizado,"El cliente fue actualizado");
        assertEquals("Sofia", clienteActualizado.getNombre());
        assertEquals("Cortez",clienteActualizado.getApellido());
    }

    @Test

    public  void delete(){

        if(clienteDAO.existsById(40));{
        clienteDAO.deleteById(40);
    }
    assertFalse(clienteDAO.existsById(40),"El dato fue eliminado");
    }
}


