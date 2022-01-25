package com.example.demo.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerResponse;

@RestController
@RequestMapping("/custumer/api/v1")
public class CustumerController {
	
	
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> create(@RequestBody Customer customer) {
		
		long leftLimit = 1L;
	    long rightLimit = 100L;
	    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		
        CustomerResponse response = new CustomerResponse();
        response.setCreatedAt(new Date());
        response.setId(generatedLong);
        response.setDocId(customer.getDocId());
        response.setMessage("Customer created with success!");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
