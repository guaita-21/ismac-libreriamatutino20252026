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
        for (Cliente item : clientes) {
            System.out.println(item.toString());
        }

    }
    @Test
    public  void findOne(){

        Optional<Cliente> cliente = clienteDAO.findById(1);
        System.out.println(cliente.toString());

        }

        @Test
    public void save(){

        Cliente cliente = new Cliente(0,"170123456","Juan2","Rodriguez","Av.Quito","0956895632","frg@gmail.com");
        clienteDAO.save(cliente);

    }
    @Test
    public  void update(){
        Optional<Cliente> cliente = clienteDAO.findById(39);

        cliente.orElse(null).setCedula("1752459856");
        cliente.orElse(null).setNombre("Sofia");
        cliente.orElse(null).setApellido("Cortez");
        cliente.orElse(null).setDireccion("Av.Tumbaco");
        cliente.orElse(null).setTelefono("0956326578");
        cliente.orElse(null).setCorreo("sofia23@gmail.com");

        clienteDAO.save(cliente.orElse(null));


    }

    @Test

    public  void delete(){

        clienteDAO.deleteById(39);
    }
    }



