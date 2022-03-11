package com.intercorp.microservicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intercorp.microservicio.business.ClienteService;
import com.intercorp.microservicio.dto.ClienteDTO;
import com.intercorp.microservicio.dto.ClienteKpiDTO;

@RestController
@RequestMapping("/gestioncliente/v1")
public class ClienteController {

    @Autowired
    ClienteService service;
	
	
    @PostMapping(path = "/crearcliente", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> crearCliente(@RequestBody ClienteDTO request) {
    	
    	service.crearCliente(request);
    	return ResponseEntity.ok(request);
    }
	
    @GetMapping(path = "/kpideclientes", produces = "application/json")
    public ResponseEntity<ClienteKpiDTO> kpiDeClientes() {
    	
    	return ResponseEntity.ok(service.kpiDeCliente()); 
    }
	
    @GetMapping(path = "/listclientes", produces = "application/json")
    public ResponseEntity<List<ClienteDTO>>listClientes() {
        return ResponseEntity.ok(service.listarClientes());
    }
}
