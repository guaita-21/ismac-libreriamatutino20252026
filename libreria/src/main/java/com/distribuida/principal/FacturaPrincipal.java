package com.distribuida.principal;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;

import java.util.Date;
public class FacturaPrincipal {


        public static void main(String[] args) {
            Cliente cliente = new Cliente(1, "5487548956", "Kevin", "Guaita", "Av rio", "095698653", "fhtg@gmail.com");

            Factura factura=new Factura();
            factura.setIdFactura(1);
            factura.setNumFactura("FAC-001");
            factura.setFecha(new Date());
            factura.setTotalNeto(60.00);
            factura.setIva(15.00);
            factura.setTotal(75.00);

            //Inyeccion
            factura.setCliente(cliente);
            System.out.println(factura.toString());
        }

    }
