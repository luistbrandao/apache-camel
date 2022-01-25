package com.example.demo.model;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerResponse {
	
	
	private long id;
	private long docId;
	private String message;
	private Date createdAt;
}
