package com.alphadev.AlphaHotel.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping("/all")
	public ResponseEntity<Response> getAllUsers(){
	    Response dummyResponse = new Response();

	    // Return the ResponseEntity with the dummy response and status code
	    return ResponseEntity.status(HttpStatus.OK).body(dummyResponse);

	}

}
