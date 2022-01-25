package br.com.nagem.workshop.model.response;

import lombok.Data;

@Data
public class HealthCheckResponse {
	
	private String message;
	private String timestamp;

}
