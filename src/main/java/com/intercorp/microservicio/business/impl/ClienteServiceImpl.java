package com.intercorp.microservicio.business.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

		Cliente cliente = new Cliente();

		cliente.setNombre(request.getNombre());
		cliente.setApellido(request.getApellido());
		cliente.setEdad(request.getEdad());
		cliente.setFechaNacimiento(request.getFechaNacimiento());

		repository.save(cliente);
	}

	@Override
	public ClienteKpiDTO kpiDeCliente() {
		
		ClienteKpiDTO response = new ClienteKpiDTO();
		List<Cliente> listCliente = repository.findAll();

		if (null != listCliente) {

			int[] edades;
			edades = new int[listCliente.size()];
			double calcularPromedio;
			double calculoDesvEstandar;

			for (int i = 0; i < listCliente.size(); i++) {
				edades[i] = listCliente.get(i).getEdad();
			}

			calcularPromedio = calcularPromedioEdad(edades);
			calculoDesvEstandar = calcularDesvEstandar(edades, calcularPromedio, listCliente.size());

			response.setPromedio(calcularPromedio);
			response.setDesviacionEstandar(calculoDesvEstandar);
		}

		return response;
	}

	@Override
	public List<ClienteDTO> listarClientes() {
		
		List<ClienteDTO> response = new ArrayList<>();
		ClienteDTO clienteDTO;
		List<Cliente> listClientes = repository.findAll();

		if (null != listClientes) {

			Date calcularFechaProbMuerte;
			
			for (Cliente cliente : listClientes) {
				clienteDTO = new ClienteDTO();
				calcularFechaProbMuerte = calcularFechaProbMuerte(cliente);
					
				clienteDTO.setNombre(cliente.getNombre());
				clienteDTO.setApellido(cliente.getApellido());
				clienteDTO.setEdad(cliente.getEdad());
				clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento());
				clienteDTO.setFechaProbMuerte(calcularFechaProbMuerte);

				response.add(clienteDTO);
			}
		}

		return response;
	}

	private Double calcularPromedioEdad(int[] edades) {
		int sumaEdad = 0;
		double promedioEdad = 0;
		double redondeo = 0;
		
		for (int item : edades) {
			sumaEdad += item;
		}

		promedioEdad = Double.valueOf(sumaEdad) / Double.valueOf(edades.length);
		redondeo = Math.rint(promedioEdad * 100) / 100;
		return redondeo;
	}

	private Double calcularDesvEstandar(int[] edades, double promedio, int totalClientes) {

		double desvEstandar = 0;
		double varianza = 0;
		double sumatoria = 0;
		double redondeo = 0;

		for (int item : edades) {
			sumatoria = Math.pow(item - promedio, 2);
			varianza = varianza + sumatoria;
		}

		varianza = varianza / (totalClientes - 1);
		desvEstandar = Math.sqrt(varianza);
		redondeo = Math.rint(desvEstandar * 100) / 100;

		return redondeo;
	}
	
	private Date calcularFechaProbMuerte (Cliente cliente) {
		
		//https://www.inei.gob.pe/prensa/noticias/en-el-2021-ano-del-bicentenario-de-la-independencia-el-peru-contara-con-una-poblacion-de-33-millones-35-mil-304-habitantes-11624/#:~:text=El%20INEI%20inform%C3%B3%20que%20la,%2C%205%2C3%20a%C3%B1os%20menos.
		//las mujeres tendrán un promedio de vida de 79,8 años y 
		//los hombres 74,5 años, es decir, 5,3 años menos.
		
		Date fechaProbMuerte;
		double promVidMujer = 79.8;
		double promVidHomb = 74.5;
		double promedioTotal = (promVidMujer + promVidHomb)/2;
		
		Date fechaNac = cliente.getFechaNacimiento();
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(fechaNac);
		c.add(Calendar.YEAR, (int) Math.round(promedioTotal));
		
		fechaProbMuerte = c.getTime();
		
		return fechaProbMuerte;
	}
}
