package com.distribuida.principal;

import com.distribuida.model.Autor;
import com.distribuida.model.Libro;

public class AutorPrincipal {

    public static void main(String[] args){

        Autor autor = new Autor(1,"Juan","Gallardo","Colombia","Av mi corazon","0923569856","gallar21@gmail.com");

        System.out.println(autor.toString());
    }
}
