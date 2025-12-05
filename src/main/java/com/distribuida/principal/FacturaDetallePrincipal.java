package com.distribuida.principal;

import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;

import java.math.BigDecimal;
import java.util.Date;

public class FacturaDetallePrincipal {

    public static void main(String[] args) {
        Factura factura = new Factura();
        {
            factura.setIdFactura(1);
            factura.setNumFactura("FAC-001");
            factura.setFecha(new Date());
            factura.setTotalNeto(60.00);
            factura.setIva(15.00);
            factura.setTotal(75.00);

            FacturaDetalle facturaDetalle=new FacturaDetalle();
            facturaDetalle.setIdFacturaDetalle(2);
            facturaDetalle.setCantidad(24);
            facturaDetalle.setSubtotal(new BigDecimal("48.00"));



            facturaDetalle.setFactura(factura);

            Libro libro= new Libro();
            libro.setLibro(12);
            libro.setLibro(12);
            libro.setTitulo("Programación en Java");
            libro.setEditorial("McGraw-Hill");
            libro.setNumpaginas(520);
            libro.setEdicion("3ra Edición");
            libro.setIdioma("Español");
            libro.setFechapublicacion(new Date());
            libro.setDescripcion("Un libro completo sobre programación orientada a objetos en Java.");
            libro.setTipopasta("Dura");
            libro.setIsbn("978-84-481-1234-5");
            libro.setNumejemplares(15);
            libro.setPortada("portada_java.jpg");
            libro.setPresentacion("Tapa dura con ilustraciones a color.");
            libro.setPrecio(39.99);

            System.out.println(facturaDetalle.toString());
            System.out.println(libro.toString());
            System.out.println(factura.toString());

        }

    }
}
