package com.distribuida.principal;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;

import java.util.Date;

public class LibroPrincipal {
    public static void main(String[]args){

        Libro libro= new Libro();
       libro.setLibro(12);
        libro.setLibro(12);
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

        //autor
        Autor autor = new Autor(1,"Juan","Gallardo","Colombia","Av mi corazon","0923569856","gallar21@gmail.com");
       //Categoria
        Categoria categoria = new Categoria(1, "Electrónica", "Dispositivos electrónicos y accesorios");

        System.out.println(libro.toString());
        System.out.println(categoria.toString());
        System.out.println(autor.toString());

    }

}
