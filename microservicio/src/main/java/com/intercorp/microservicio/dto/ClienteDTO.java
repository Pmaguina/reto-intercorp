package com.intercorp.microservicio.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;
	
	@JsonProperty("fechaProbMuerte")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaProbMuerte;
}
