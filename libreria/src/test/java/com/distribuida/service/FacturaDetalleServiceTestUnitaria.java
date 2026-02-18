package com.distribuida.service;
import com.distribuida.dao.FacturaDetalleDAO;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import com.distribuida.service.FacturaDetalleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class FacturaDetalleServiceTestUnitaria {

    @Mock
    private FacturaDetalleDAO facturaDetalleDAO;

    @InjectMocks
    private FacturaDetalleServiceImpl facturaDetalleService;

    private FacturaDetalle facturaDetalle;
    private Factura factura;
    private Libro libro;

    @BeforeEach
    void setUp() {
        factura = new Factura();
        factura.setIdFactura(1);

        libro = new Libro();
        libro.setLibro(1);
        libro.setTitulo("Java desde cero");

        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(2);
        facturaDetalle.setSubtotal(new BigDecimal("30.00"));
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);
    }

    @Test
    void testFindAll() {
        when(facturaDetalleDAO.findAll()).thenReturn(List.of(facturaDetalle));

        List<FacturaDetalle> detalles = facturaDetalleService.findAll();

        assertNotNull(detalles);
        assertEquals(1, detalles.size());
        verify(facturaDetalleDAO, times(1)).findAll();
    }

    @Test
    void testFindOneExistente() {
        when(facturaDetalleDAO.findById(1)).thenReturn(Optional.of(facturaDetalle));

        Optional<FacturaDetalle> resultado = facturaDetalleService.findOne(1);

        assertTrue(resultado.isPresent());
        assertEquals(2, resultado.get().getCantidad());
    }

    @Test
    void testFindOneNoExistente() {
        when(facturaDetalleDAO.findById(999)).thenReturn(Optional.empty());

        Optional<FacturaDetalle> resultado = facturaDetalleService.findOne(999);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testSave() {
        when(facturaDetalleDAO.save(facturaDetalle)).thenReturn(facturaDetalle);

        FacturaDetalle guardado = facturaDetalleService.save(facturaDetalle);

        assertNotNull(guardado);
        assertEquals(new BigDecimal("30.00"), guardado.getSubtotal());
    }

    @Test
    void testUpdateExistente() {
        FacturaDetalle detalleActualizado = new FacturaDetalle();
        detalleActualizado.setCantidad(5);
        detalleActualizado.setSubtotal(new BigDecimal("75.00"));

        when(facturaDetalleDAO.findById(1)).thenReturn(Optional.of(facturaDetalle));
        when(facturaDetalleDAO.save(any())).thenReturn(facturaDetalle);

        FacturaDetalle resultado = facturaDetalleService.update(1, detalleActualizado);
        assertNotNull(resultado);
        assertEquals(5, resultado.getCantidad());
        verify(facturaDetalleDAO, times(1)).save(facturaDetalle);
    }

    @Test
    void testUpdateNoExistente() {
        FacturaDetalle facturadetallenuevo = new FacturaDetalle();
        when(facturaDetalleDAO.findById(999)).thenReturn(null);

        FacturaDetalle resultado = facturaDetalleService.update(999,  facturadetallenuevo);

        assertNull(resultado);
        verify(facturaDetalleDAO, never()).save(any());
    }

    @Test
    void testDeleteExistente() {
        when(facturaDetalleDAO.existsById(1)).thenReturn(true);

        facturaDetalleService.delete(1);

        verify(facturaDetalleDAO).deleteById(1);
    }

    @Test
    void testDeleteNoExistente() {
        when(facturaDetalleDAO.existsById(999)).thenReturn(false);

        facturaDetalleService.delete(999);

        verify(facturaDetalleDAO, never()).deleteById(anyInt());
    }
}
