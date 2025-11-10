package com.distribuida.principal;

import com.distribuida.model.Cliente;

public class ClientePrincipal {

    public static  void main(String[]args){
        Cliente cliente= new Cliente(1,"5487548956","Kevin","Guaita","Av rio","095698653","fhtg@gmail.com" );
        System.out.println(cliente.toString());
    }
}
