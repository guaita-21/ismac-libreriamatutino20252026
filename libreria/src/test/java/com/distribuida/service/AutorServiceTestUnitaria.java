package com.distribuida.service;

import com.distribuida.dao.AutorDAO;
import com.distribuida.model.Autor;
import com.distribuida.service.AutorServiceImpl;
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
class AutorServiceTestUnitaria {

    @Mock
    private AutorDAO autorDAO;

    @InjectMocks
    private AutorServiceImpl autorService;

    private Autor autor;

    @BeforeEach
    void setUp() {
        autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombre("Gabriel");
        autor.setApellido("García Márquez");
        autor.setPais("Colombia");
        autor.setDirreccion("Aracataca");
        autor.setTelefono("0999999532");
        autor.setCorreo("ggm@gmail.com");
    }

    @Test
    void testFindAll() {
        when(autorDAO.findAll()).thenReturn(List.of(autor));

        List<Autor> autores = autorService.findAll();

        assertNotNull(autores);
        assertEquals(1, autores.size());
        verify(autorDAO, times(1)).findAll();
    }

    @Test
    void testFindOneExistente() {
        when(autorDAO.findById(1)).thenReturn(Optional.of(autor));

        Optional<Autor> resultado = autorService.findOne(1);

        assertTrue(resultado.isPresent());
        assertEquals("Gabriel", resultado.get().getNombre());
    }


    @Test
    void testFindOneNoExistente() {
        when(autorDAO.findById(999)).thenReturn(Optional.empty());

        Optional<Autor> resultado = autorService.findOne(999);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testSave() {
        when(autorDAO.save(autor)).thenReturn(autor);

        Autor guardado = autorService.save(autor);

        assertNotNull(guardado);
        assertEquals("Gabriel", guardado.getNombre());
    }


    @Test
    void testUpdateExistente() {
        Autor autorActualizado = new Autor();
        autorActualizado.setNombre("Mario");
        autorActualizado.setApellido("Vargas Llosa");

        when(autorDAO.findById(1)).thenReturn(Optional.of(autor));
        when(autorDAO.save(any())).thenReturn(autor);

        Autor resultado = autorService.update(1, autorActualizado);

        assertNotNull(resultado);
        assertEquals("Mario", resultado.getNombre());
        verify(autorDAO, times(1)).save(autor);
    }


    @Test
    void testUpdateNoExistente() {
        Autor autornuevo = new Autor();
        when(autorDAO.findById(999)).thenReturn(null);

        Autor resultado = autorService.update(999, autornuevo);

        assertNull(resultado);
        verify(autorDAO, never()).save(any());
    }


    @Test
    void testDeleteExistente() {
        when(autorDAO.existsById(1)).thenReturn(true);

        autorService.delete(1);

        verify(autorDAO).deleteById(1);
    }


    @Test
    void testDeleteNoExistente() {
        when(autorDAO.existsById(999)).thenReturn(false);

        autorService.delete(999);

        verify(autorDAO, never()).deleteById(anyInt());
    }
}
