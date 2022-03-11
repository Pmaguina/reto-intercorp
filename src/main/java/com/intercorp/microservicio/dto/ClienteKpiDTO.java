package com.intercorp.microservicio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteKpiDTO {

	@JsonProperty("promedio")
	private double promedio;
	
	@JsonProperty("desviacionEstandar")
	private double desviacionEstandar;
}
