package br.com.nagem.workshop.model.response;

import java.util.Date;

import lombok.Data;

@Data
public class CreateCustumerResponse {
	
	private long id;
	private long docId;
	private String message;
	private Date createdAt;

}
