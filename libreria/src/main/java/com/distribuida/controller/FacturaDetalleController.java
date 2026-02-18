package com.distribuida.controller;


import com.distribuida.model.FacturaDetalle;
import com.distribuida.service.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api/facturadetalle")
    public class FacturaDetalleController {

        @Autowired
        private FacturaDetalleService facturadetalleservice;
        @GetMapping
        public ResponseEntity<List<FacturaDetalle>> findAll(){
            return ResponseEntity.ok(facturadetalleservice.findAll());
        }
        @GetMapping("/{id}")
        public ResponseEntity<FacturaDetalle> findOne(@PathVariable int id){
            Optional<FacturaDetalle> facturaDetalle = facturadetalleservice.findOne(id);

            if (facturaDetalle==null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(facturaDetalle.orElse(null));
        }
        @PostMapping
        public ResponseEntity<FacturaDetalle> save(@RequestBody FacturaDetalle facturaDetalle){
            return ResponseEntity.ok(facturadetalleservice.save(facturaDetalle));
        }
        @PutMapping("/{id}")
        public ResponseEntity<FacturaDetalle> update(
                @PathVariable int id,
                @RequestBody FacturaDetalle facturaDetalle){

            FacturaDetalle actualizado = facturadetalleservice.update(id, facturaDetalle);

            if (actualizado == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(actualizado);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable int id){
            facturadetalleservice.delete(id);
            return ResponseEntity.noContent().build();
        }
    }


