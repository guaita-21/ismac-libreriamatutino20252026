package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaDetalleTestUnitaria {

    private FacturaDetalle facturaDetalle;
    private Factura factura;
    private Libro libro;

    @BeforeEach
    public void setup() {

        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(2);
        facturaDetalle.setCantidad(24);
        facturaDetalle.setSubtotal(new BigDecimal(48.00));

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-001");
        factura.setFecha(new Date());
        factura.setTotalNeto(60.00);
        factura.setIva(15.00);
        factura.setTotal(75.00);

        libro = new Libro();
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

        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);
    }

    @Test
    public void testFacturaDetalleConstructor() {

        assertAll("Validar datos de factura detalle con constructor",
                () -> assertEquals(2, facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(24, facturaDetalle.getCantidad()),
                () -> assertEquals(48, facturaDetalle.getSubtotal())
        );
    }

    @Test
    public void testFacturaDetalletoString() {
        String str = facturaDetalle.toString();

        assertAll("Validar datos de toString - Factura Detalle",
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("24")),
                () -> assertTrue(str.contains("48")),

                // Factura
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("FAC-001")),
                () -> assertTrue(str.contains("75")),

                // Libro
                () -> assertTrue(str.contains("Programación en Java")),
                () -> assertTrue(str.contains("McGraw-Hill")),
                () -> assertTrue(str.contains("Español"))
        );
    }

    @Test
    public void testFacturaDetalleInyeccion() {
        assertAll("Validar metodo Inyector",
                () -> assertNotNull(facturaDetalle.getFactura()),
                () -> assertNotNull(facturaDetalle.getLibro()),

                () -> assertEquals("FAC-001", facturaDetalle.getFactura().getNumFactura()),
                () -> assertEquals(75.00, facturaDetalle.getFactura().getTotal()),

                () -> assertEquals("Programación en Java", facturaDetalle.getLibro().getTitulo()),
                () -> assertEquals("Español", facturaDetalle.getLibro().getIdioma())
        );
    }
}
