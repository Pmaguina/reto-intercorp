package com.intercorp.microservicio.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intercorp.microservicio.business.ClienteService;
import com.intercorp.microservicio.dto.ClienteDTO;
import com.intercorp.microservicio.dto.ClienteKpiDTO;
import com.intercorp.microservicio.entity.Cliente;
import com.intercorp.microservicio.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository repository;
	
	
	@Override
	public void crearCliente(ClienteDTO request) {
		// TODO Auto-generated method stub
		Cliente cliente = new Cliente();
		
		cliente.setNombre(request.getNombre());
		cliente.setApellido(request.getApellido());
		cliente.setEdad(request.getEdad());
		cliente.setFechaNacimiento(request.getFechaNacimiento());
		
		repository.save(cliente);
	}

	@Override
	public ClienteKpiDTO kpiDeCliente() {
		// TODO Auto-generated method stub
		ClienteKpiDTO response = new ClienteKpiDTO();

		try {
			List<Cliente> listCliente = new ArrayList<>();
			listCliente = repository.findAll();
			
			if (null !=listCliente) {
				
				int sumaEdad = 0;
				
				for (Cliente ed : listCliente) {
					sumaEdad+=ed.getEdad();
				}
				
				double promedioEdad = sumaEdad/listCliente.size();
				response.setPromedio(promedioEdad);
				
				
				
				
			}
			
			
		} catch(Exception e) {
			
		}
		return response;
	}

	@Override
	public List<ClienteDTO> listarClientes() {
		// TODO Auto-generated method stub
		List<ClienteDTO> response = new ArrayList<>();
		ClienteDTO clienteDTO = new ClienteDTO();
		List<Cliente> listClientes = repository.findAll();
		
		if(null != listClientes) {
			
			for(Cliente cliente : listClientes) {
				clienteDTO = new ClienteDTO();
				
				clienteDTO.setNombre(cliente.getNombre());
				clienteDTO.setApellido(cliente.getApellido());
				clienteDTO.setEdad(cliente.getEdad());
				clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento());
				//clienteDTO.setFechaProbMuerte(fechaProbMuerte);
				
				response.add(clienteDTO);
			}
			
		}
		
		return response;
	}
}
