package com.distribuida.dao;

import com.distribuida.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class AutorTestIntegracion {

    @Autowired
    private AutorDAO autorDAO;

    @Test
    public void testAutorFindAll() {
        List<Autor> autores = autorDAO.findAll();
        assertNotNull(autores);
        assertTrue(autores.size() > 0);
        for (Autor item : autores) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void testAutorFindOne() {
        Optional<Autor> autor = autorDAO.findById(1);
        assertTrue(autor.isPresent(), "El autor con el Id=1 si existe ");
        System.out.println(autor.toString());
    }

    @Test
    public void testAutorSave() {
        Autor autor = new Autor(0,"Juan","Soria","Ecuador","Av Eloy Alfaro","0932569856","juan11@gmail.com");
        Autor guardado = autorDAO.save(autor);
        assertNotNull(guardado, "El autor se guardó de manera correcta");
        assertEquals("Juan", guardado.getNombre());
        assertEquals("Soria", guardado.getApellido());
    }

    @Test
    public void testAutorUpdate() {
        Optional<Autor> autor = autorDAO.findById(50);

        autor.orElse(null).setPais("Mexico");
        autor.orElse(null).setDirreccion("Nuevo Mexico");
        autor.orElse(null).setCorreo("nvw@gmail.com");

        Autor autorActualizado =autorDAO.save(autor.orElse(null));
        assertEquals("Mexico", autorActualizado.getPais());
        assertEquals("Nuevo Mexico", autorActualizado.getDirreccion());
        assertTrue(autor.isPresent());

        Autor a = autor.get();
        a.setNombre("Mario Vargas Llosa");

        Autor actualizado = autorDAO.save(a);
        assertEquals("Mario Vargas Llosa", actualizado.getNombre());
    }
    @Test
    public void delete(){
        if (autorDAO.existsById(54)){
            autorDAO.deleteById(54);
        }
        assertFalse(autorDAO.existsById(55),"El autor fue eliminado correctamente");
    }
}
