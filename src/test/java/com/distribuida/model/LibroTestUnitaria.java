package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class LibroTestUnitaria {
    private Autor autor;
    private Libro libro;
    private Categoria categoria;

    @BeforeEach
    public void setup() {

        libro = new Libro();
        libro.setLibro(1);
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

        categoria = new Categoria(
                1,
                "Electrónica",
                "Dispositivos electrónicos y accesorios"
        );

        autor = new Autor(
                1,
                "Juan",
                "Gallardo",
                "Colombia",
                "Av mi corazon",
                "0923569856",
                "gallar21@gmail.com"
        );

        // Inyecciones
        libro.setCategoria(categoria);
        libro.setAutor(autor);
    }

    @Test
    public void testLibroConstructor() {
        assertAll("Validar datos de libro con setters ",
                () -> assertEquals(1, libro.getLibro()),
                () -> assertEquals("Programación en Java", libro.getTitulo()),
                () -> assertEquals("McGraw-Hill", libro.getEditorial()),
                () -> assertEquals(520, libro.getNumpaginas()),
                () -> assertEquals("3ra Edición", libro.getEdicion()),
                () -> assertEquals("Español", libro.getIdioma()),
                () -> assertEquals("Un libro completo sobre programación orientada a objetos en Java.", libro.getDescripcion()),
                () -> assertEquals("Dura", libro.getTipopasta()),
                () -> assertEquals("978-84-481-1234-5", libro.getIsbn()),
                () -> assertEquals(15, libro.getNumejemplares()),
                () -> assertEquals("portada_java.jpg", libro.getPortada()),
                () -> assertEquals("Tapa dura con ilustraciones a color.", libro.getPresentacion()),
                () -> assertEquals(39.99, libro.getPrecio())
        );
    }

    @Test
    public void testLibroToString() {
        String str = libro.toString();

        assertAll("Validar contenido del toString",
                () -> assertTrue(str.contains("Programación en Java")),
                () -> assertTrue(str.contains("McGraw-Hill")),
                () -> assertTrue(str.contains("520")),
                () -> assertTrue(str.contains("Dura")),
                () -> assertTrue(str.contains("978-84-481-1234-5")),
                () -> assertTrue(str.contains("Electrónica")),
                () -> assertTrue(str.contains("Dispositivos electrónicos y accesorios"))
        );
    }

    @Test
    public void testLibroInyeccion() {
        assertAll("Validar inyección de Autor y Categoria",
                () -> assertNotNull(libro.getCategoria()),
                () -> assertNotNull(libro.getAutor()),
                () -> assertEquals("Electrónica", libro.getCategoria().getCategoria()),
                () -> assertEquals("Juan", libro.getAutor().getNombre())
        );
    }
}
