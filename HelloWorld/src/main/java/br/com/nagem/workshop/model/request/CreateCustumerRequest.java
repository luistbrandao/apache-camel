package br.com.nagem.workshop.model.request;

import lombok.Data;

@Data
public class CreateCustumerRequest {
	
	private long docId;
	private String custumerName;

}
