package com.distribuida.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacturaTestUnitaria {

    private Factura factura;
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
            factura= new Factura();
            factura.setIdFactura(1);
            factura.setNumFactura("FAC-001");
            factura.setTotalNeto(100.00);
            factura.setIva(15.00);
            factura.setTotal(115.00);

             //Inyeccionde dependencias
            factura.setCliente(cliente);


    }

    @Test

    public void testFacturaConstructor(){

        assertAll("Validar datos del constructor - Factura ",
                () ->assertEquals(1, factura.getIdFactura()),
                () ->assertEquals("FAC-001", factura.getNumFactura()),
                () ->assertEquals(100.00, factura.getTotalNeto()),
                () ->assertEquals(15.00, factura.getIva()),
                () ->assertEquals(115.00, factura.getTotal())
                );
    }
    @Test
    public void testFacturaSetters(){
        Cliente cliente = new Cliente(2, "5487548956", "Kevin", "Guaita", "Av rio", "095698653", "fhtg@gmail.com");
        factura=new Factura();

        factura.setIdFactura(2);
        factura.setNumFactura("FAC-002");
        factura.setFecha(new Date());
        factura.setTotalNeto(200.00);
        factura.setIva(30.00);
        factura.setTotal(230.00);

        factura.setCliente(cliente);

        //test en inyeccion de dependencias
        assertAll("Validar metodos setters- Factura",
                () ->assertEquals(2, factura.getIdFactura()),
                () ->assertEquals("FAC-002", factura.getNumFactura()),
                () ->assertEquals(200.00, factura.getTotalNeto()),
                () ->assertEquals(30.00, factura.getIva()),
                () ->assertEquals(230.00, factura.getTotal()),
                () ->assertEquals("5487548956", factura.getCliente().getCedula())
        );


    }
    @Test
    public void testFacturaToString(){
        String str = factura.toString();

        assertAll("Validar datos ToString- Factura",
                ()->assertTrue(str.contains("1")),
                ()->assertTrue(str.contains("FAC-001")),
                ()->assertTrue(str.contains("100.0")),
                ()->assertTrue(str.contains("15.0")),
                ()->assertTrue(str.contains("115.0")),
                ()->assertTrue(str.contains("Kevin"))

        );

    }

    @Test

    public  void  testFacturaInyeccion(){

        assertAll("Validar metodo inyector",
                ()->assertNotNull(factura.getCliente()),
                ()->assertEquals("Guaita", factura.getCliente().getApellido())
                );
    }

    @Test

    public void  testFacturaValoresNegativos(){

        factura.setTotal(-100.00);
        assertAll("Validar datod negativos -Factura",
                () -> assertEquals(-100.00, factura.getTotal())
                );
        // Validar que se evite valores negativos
    }
}