package com.intercorp.microservicio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

	@JsonProperty("nombre")
	private String nombre;
	
	@JsonProperty("apellido")
	private String apellido;
	
	@JsonProperty("edad")
	private Integer edad;
	
	@JsonProperty("fechaNacimiento")
	private String fechaNacimiento;
	
	@JsonProperty("fechaProbMuerte")
	private String fechaProbMuerte;
}
