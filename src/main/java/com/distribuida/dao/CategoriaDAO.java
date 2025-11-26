package com.distribuida.dao;

import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDAO extends JpaRepository<Categoria,Integer> {

}
