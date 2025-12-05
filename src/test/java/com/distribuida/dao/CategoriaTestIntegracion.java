package com.distribuida.dao;

import com.distribuida.model.Categoria;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)

public class CategoriaTestIntegracion {
    @Autowired
    public CategoriaDAO categoriaDAO;

    @Test
    public void findAll(){
        List<Categoria> categorias = categoriaDAO.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size() > 0);
        for (Categoria item: categorias){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Categoria> categoria = categoriaDAO.findById(1);
        assertTrue(categoria.isPresent(),"La categoria con ID=1 si existe");
        System.out.println(categoria.toString());
    }

    @Test
    public void save(){
        Categoria categoria = new Categoria(0,"Ficcion","Fuera de lo normal");
        Categoria categoraGuardado = categoriaDAO.save(categoria);
        assertNotNull(categoraGuardado,"La categoria se guardo correctamente");
        assertEquals("Ficcion", categoraGuardado.getCategoria());
        assertEquals("Fuera de lo normal", categoraGuardado.getDescripcion());
    }

    @Test
    public void update(){
        Optional<Categoria> categoria = categoriaDAO.findById(58);

        categoria.orElse(null).setCategoria("Terror");
        categoria.orElse(null).setDescripcion("Una historia de miedo");
    }

    @Test
    public void delete(){
        if (categoriaDAO.existsById(58)){
            categoriaDAO.deleteById(58);
        }
        assertFalse(categoriaDAO.existsById(58),"El dato fue eliminado");
    }

}