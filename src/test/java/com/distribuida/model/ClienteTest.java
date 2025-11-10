package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setup(){
        cliente = new Cliente (1
                ,"251458754"
                , "Kevin"
                ,"Guaita"
                ,"Av rio rio"
                ,"09657845"
                ,"sdfdd@gmail.com");
    }
    @Test

    public void testClienteConstructor(){

        assertAll("Validar datos del Cliente con constuctor ",
                ()-> assertEquals(1, cliente.getIdCliente()),
                ()-> assertEquals("251458754", cliente.getCedula()),
                ()-> assertEquals("Kevin", cliente.getNombre()),
                ()-> assertEquals("Guaita", cliente.getApellido()),
                ()-> assertEquals("Av rio rio", cliente.getDireccion()),
                ()-> assertEquals("09657845", cliente.getTelefono()),
                ()-> assertEquals("sdfdd@gmail.com", cliente.getCorreo())

        );
    }
    @Test
    public  void testClienteSetters(){
        cliente.setIdCliente(2);
        cliente.setCedula("4521548745");
        cliente.setNombre("Juan");
        cliente.setApellido("Flores");
        cliente.setDireccion("AV quito");
        cliente.setTelefono("0965875498");
        cliente.setCorreo("dfgf@gmail.com");

        assertAll("Validad datos del cliente con setters",
                ()-> assertEquals(2, cliente.getIdCliente()),
                ()-> assertEquals("4521548745", cliente.getCedula()),
                ( )-> assertEquals("Juan", cliente.getNombre()),
                ( )-> assertEquals("Flores", cliente.getApellido()),
                ( )-> assertEquals("AV quito", cliente.getDireccion()),
                ( )-> assertEquals("0965875498", cliente.getTelefono()),
                ( )-> assertEquals("dfgf@gmail.com", cliente.getCorreo())
        );

    }
}
