package com.intercorp.microservicio.business;

import java.util.List;

import com.intercorp.microservicio.dto.ClienteDTO;
import com.intercorp.microservicio.dto.ClienteKpiDTO;

public interface ClienteService {

	void crearCliente(ClienteDTO request);
	ClienteKpiDTO kpiDeCliente();
	List<ClienteDTO> listarClientes();
}
