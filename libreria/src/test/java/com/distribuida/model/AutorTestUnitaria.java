package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutorTestUnitaria {

    private Autor autor;

    @BeforeEach
    public void setup() {
        autor = new Autor(1,
                "Juan",
                "Perez",
                "Ecuador",
                "AV. Amazonas",
                "0923567845",
                "juan21@gmail.com"
        );
    }

    @Test
    public void testAutorConstructor() {

        assertAll("Validar datos del autor con el constructor",
                () -> assertEquals(1, autor.getIdAutor()),
                () -> assertEquals("Juan", autor.getNombre()),
                () -> assertEquals("Perez", autor.getApellido()),
                () -> assertEquals("Ecuador", autor.getPais()),
                () -> assertEquals("AV. Amazonas", autor.getDirreccion()),
                () -> assertEquals("0923567845", autor.getTelefono()),
                () -> assertEquals("juan21@gmail.com", autor.getCorreo())
        );

    }

    @Test

    public void testAutorSetters() {
        autor.setIdAutor(2);
        autor.setNombre("Sofia");
        autor.setApellido("Cortez");
        autor.setPais("Mexico");
        autor.setDirreccion("AV ciudad Juares");
        autor.setTelefono("09235698");
        autor.setCorreo("soficor@gmail.com");

        assertAll("Validar datos de el autor con setters",
                () -> assertEquals(2, autor.getIdAutor()),
                () -> assertEquals("Sofia", autor.getNombre()),
                () -> assertEquals("Cortez", autor.getApellido()),
                () -> assertEquals("Mexico", autor.getPais()),
                () -> assertEquals("AV ciudad Juares", autor.getDirreccion()),
                () -> assertEquals("09235698", autor.getTelefono()),
                () -> assertEquals("soficor@gmail.com", autor.getCorreo())
        );
    }
    @Test

    public  void testAutorToString(){

        String str = autor.toString();

        assertAll("Validar datos de el Autor en toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Juan")),
                () -> assertTrue(str.contains("Perez")),
                () -> assertTrue(str.contains("Ecuador")),
                () -> assertTrue(str.contains("AV. Amazonas")),
                () -> assertTrue(str.contains("0923567845")),
                () -> assertTrue(str.contains("juan21@gmail.com"))
                );
    }


}
