package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriaTestUnitaria {

    private  Categoria categoria;

    @BeforeEach
    public  void setup() {
        categoria = new Categoria(
                1,
                "Terror",
                "Libros relacionados con historias de terror"
        );
    }

    @Test
    public  void testCategoriaConstructor(){

        assertAll("Validar datos de categoria con Constructor",
                () -> assertEquals(1, categoria.getIdCategoria()),
                () -> assertEquals("Terror", categoria.getCategoria()),
                () -> assertEquals("Libros relacionados con historias de terror", categoria.getDescripcion())
                );
    }
    @Test
    public  void testCategoriaSetters(){

        categoria.setIdCategoria(2);
        categoria.setCategoria("Romance");
        categoria.setDescripcion("Libros de historias romanticas");

        assertAll("Validar datos de categoria con setters",
                () -> assertEquals(2, categoria.getIdCategoria()),
                () -> assertEquals("Romance",categoria.getCategoria()),
                () -> assertEquals("Libros de historias romanticas", categoria.getDescripcion())
                );
    }

    @Test
    public void testCategoriaToString() {
        String str = categoria.toString();

        assertAll("Validar datos en toString de categoria",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Terror")),
                () -> assertTrue(str.contains("Libros relacionados con historias de terror"))
        );
    }
}
