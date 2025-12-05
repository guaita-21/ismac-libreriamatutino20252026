package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class LibroTestIntegracion {

    @Autowired
    public LibroDAO libroDAO;

    @Autowired
    public CategoriaDAO categoriaDAO;

    @Autowired
    public AutorDAO autorDAO;

    @Test
    public void testLibroFindAll() {
        List<Libro> libros = libroDAO.findAll();
        assertNotNull(libros);
        assertTrue(libros.size() > 0);
        for (Libro item : libros) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne() {
        Optional<Libro> libro = libroDAO.findById(1);
        assertTrue(libro.isPresent(), "El libro si existe");
        System.out.println(libro.toString());
    }

    @Test
    public void save() {
        Optional<Autor> autor = autorDAO.findById(1);
        Optional<Categoria> categoria = categoriaDAO.findById(1);

        assertTrue(autor.isPresent());
        assertTrue(categoria.isPresent());

        Libro libro = new Libro();
        libro.setTitulo("El conocimiento Industrial");
        libro.setEditorial("Editorial Printo");
        libro.setNumpaginas(471);
        libro.setEdicion("Segunda edicion");
        libro.setIdioma("Español");
        libro.setFechapublicacion(new Date());
        libro.setDescripcion("Una obra acerca de la revolucion Industrial.");
        libro.setTipopasta("Pasta dura");
        libro.setIsbn("789-53-356-8569-8");
        libro.setNumejemplares(58);
        libro.setPortada("https://example.com/portadas/El-conocimiento-Industrial.jpg");
        libro.setPresentacion("Libro fisico");
        libro.setPrecio(52.99);

        libro.setAutor(autor.orElse(null));
        libro.setCategoria(categoria.orElse(null));

        Libro libroGuardado = libroDAO.save(libro);

        assertNotNull(libroGuardado, "El libro se guardo correctamente");
        assertEquals("Editorial Printo", libroGuardado.getEditorial());
        assertEquals("789-53-356-8569-8", libroGuardado.getIsbn());
        assertEquals("https://example.com/portadas/El-conocimiento-Industrial.jpg", libroGuardado.getPortada());
        assertEquals("Segunda edicion", libroGuardado.getEdicion());

    }

    @Test
    public void testLibroUpdate() {
        Optional<Autor> autor = autorDAO.findById(1);
        Optional<Categoria> categoria = categoriaDAO.findById(1);
        Optional<Libro> libro = libroDAO.findById(79);

        libro.orElse(null).setTitulo("Ecuaciones");
        libro.orElse(null).setEditorial("Academia Matematica");
        libro.orElse(null).setNumpaginas(220);
        libro.orElse(null).setEdicion("Primera Edición");
        libro.orElse(null).setIdioma("Ingles");
        libro.orElse(null).setFechapublicacion(new Date());
        libro.orElse(null).setDescripcion("Principios de las ecuaciones matematicas");
        libro.orElse(null).setTipopasta("Pasta Suave");
        libro.orElse(null).setEditorial("Academia Matematica");
        libro.orElse(null).setIsbn("586-78-895-8234-7");
        libro.orElse(null).setNumejemplares(321);
        libro.orElse(null).setPortada("https://example.com/portadas/Ecuaciones-Matematicas.jpg");
        libro.orElse(null).setPresentacion("Libro fisico y digital");
        libro.orElse(null).setPrecio(23.21);

        libro.orElse(null).setAutor(autor.orElse(null));
        libro.orElse(null).setCategoria(categoria.orElse(null));

        Libro libroActualizado = libroDAO.save(libro.orElse(null));

        assertNotNull(libroActualizado);
        assertEquals("Ecuaciones", libroActualizado.getTitulo());
        assertEquals("Academia Matematica", libroActualizado.getEditorial());
        assertEquals("Primera Edición", libroActualizado.getEdicion());
        assertEquals("Ingles", libroActualizado.getIdioma());
        assertEquals("Pamela", libroActualizado.getAutor().getNombre());
    }
    @Test
    public void delete(){
        if (libroDAO.existsById(79)){
            libroDAO.deleteById(79);
        }
        assertFalse(libroDAO.existsById(79),"El dato fue eliminado");
    }
}

