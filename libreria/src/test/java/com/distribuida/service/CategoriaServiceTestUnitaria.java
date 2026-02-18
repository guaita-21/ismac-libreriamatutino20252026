package com.distribuida.service;


import com.distribuida.dao.CategoriaDAO;
import com.distribuida.model.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTestUnitaria {

    @Mock
    private CategoriaDAO categoriaDAO;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoria("Tecnología");
        categoria.setDescripcion("Productos tecnológicos");
    }

    @Test
    public void testfindAll() {
        when(categoriaDAO.findAll()).thenReturn(List.of(categoria));

        List<Categoria> categorias = categoriaService.findAll();

        assertNotNull(categorias);
        assertEquals(1, categorias.size());
        verify(categoriaDAO, times(1)).findAll();
    }

    @Test
    public void testFindOneExistente() {
        when(categoriaDAO.findById(1)).thenReturn(Optional.of(categoria));

        Optional<Categoria> resultado = categoriaService.findOne(1);

        assertNotNull(resultado);
        assertEquals("Tecnología", resultado.orElse(null).getCategoria());
    }

    @Test
    public void testFindOneNoExistente() {
        when(categoriaDAO.findById(2)).thenReturn(Optional.empty());

        Optional<Categoria> resultado = categoriaService.findOne(2);

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testSave() {
        when(categoriaDAO.save(categoria)).thenReturn(categoria);

        Categoria guardado = categoriaService.save(categoria);

        assertNotNull(guardado);
        assertEquals("Tecnología", guardado.getCategoria());
    }

    @Test
    public void testUpdateExistente() {
        Categoria categoriaActualizada = new Categoria();
        categoriaActualizada.setCategoria("Hogar");
        categoriaActualizada.setDescripcion("Productos para el hogar");

        when(categoriaDAO.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaDAO.save(any())).thenReturn(categoriaActualizada);

        Categoria resultado = categoriaService.update(1, categoriaActualizada);

        assertNotNull(resultado);
        assertEquals("Hogar", resultado.getCategoria());
        verify(categoriaDAO, times(1)).save(categoria);
    }

    @Test
    public void testUpdateNoExistente() {
        Categoria categoriaNueva = new Categoria();
        when(categoriaDAO.findById(999)).thenReturn(null);

        Categoria resultado = categoriaService.update(999, categoriaNueva);

        assertNull(resultado);
        verify(categoriaDAO, never()).save(any());
    }



    @Test
    public void testDeleteExistente() {
        when(categoriaDAO.existsById(1)).thenReturn(true);

        categoriaService.delete(1);

        verify(categoriaDAO).deleteById(1);
    }

    @Test
    public void testDeleteNoExistente() {
        when(categoriaDAO.existsById(999)).thenReturn(false);

        categoriaService.delete(999);

        verify(categoriaDAO, never()).deleteById(anyInt());
    }

}
