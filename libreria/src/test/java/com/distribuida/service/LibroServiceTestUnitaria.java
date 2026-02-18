package com.distribuida.service;

import com.distribuida.dao.LibroDAO;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
 public class LibroServiceTestUnitaria {

    @Mock
    private LibroDAO libroDAO;

    @InjectMocks
    private LibroServiceImpl libroService;

    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoria("Tecnología");

        autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombre("Juan Pérez");

        libro = new Libro();
        libro.setLibro(1);
        libro.setTitulo("Java desde cero");
        libro.setEditorial("Pearson");
        libro.setNumpaginas(350);
        libro.setEdicion("Primera");
        libro.setIdioma("Español");
        libro.setFechapublicacion(new Date());
        libro.setDescripcion("Libro de programación Java");
        libro.setTipopasta("Dura");
        libro.setIsbn("978-1234567890");
        libro.setNumejemplares(10);
        libro.setPortada("portada.jpg");
        libro.setPresentacion("Física");
        libro.setPrecio(45.50);
        libro.setCategoria(categoria);
        libro.setAutor(autor);
    }
    @Test
    void testFindAll() {
        when(libroDAO.findAll()).thenReturn(List.of(libro));

        List<Libro> libros = libroService.findAll();

        assertNotNull(libros);
        assertEquals(1, libros.size());
        verify(libroDAO, times(1)).findAll();
    }

    @Test
    void testFindOneExistente() {
        when(libroDAO.findById(1)).thenReturn(Optional.of(libro));

        Optional<Libro> resultado = libroService.findOne(1);

        assertTrue(resultado.isPresent());
        assertEquals("Java desde cero", resultado.get().getTitulo());
    }

    @Test
    void testFindOneNoExistente() {
        when(libroDAO.findById(999)).thenReturn(Optional.empty());

        Optional<Libro> resultado = libroService.findOne(999);

        assertTrue(resultado.isEmpty());
    }
    @Test
    void testSave() {
        when(libroDAO.save(libro)).thenReturn(libro);

        Libro guardado = libroService.save(libro);

        assertNotNull(guardado);
        assertEquals("Java desde cero", guardado.getTitulo());
    }

    @Test
    void testUpdateExistente() {
        Libro libroActualizado = new Libro();
        libroActualizado.setTitulo("Java Avanzado");
        libroActualizado.setPrecio(55.00);

        when(libroDAO.findById(1)).thenReturn(Optional.of(libro));
        when(libroDAO.save(any())).thenReturn(libro);

        Libro resultado = libroService.update(1, libroActualizado);

        assertNotNull(resultado);
        assertEquals("Java Avanzado", resultado.getTitulo());
        verify(libroDAO, times(1)).save(libro);
    }

    @Test
    void testUpdateNoExistente() {
        Libro libroNuevo = new Libro();
        when(libroDAO.findById(999)).thenReturn(null);

        Libro resultado = libroService.update(999, libroNuevo);

        assertNull(resultado);
        verify(libroDAO, never()).save(any());
    }

    @Test
    void testDeleteExistente() {
        when(libroDAO.existsById(1)).thenReturn(true);

        libroService.delete(1);

        verify(libroDAO).deleteById(1);
    }

    @Test
    void testDeleteNoExistente() {
        when(libroDAO.existsById(999)).thenReturn(false);

        libroService.delete(999);

        verify(libroDAO, never()).deleteById(anyInt());
    }
}
