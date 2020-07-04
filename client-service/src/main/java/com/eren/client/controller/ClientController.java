package com.eren.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
	
	@Value("${value}")
	private String valueFromConfigFile;
	
	@GetMapping(value = "/value")
	public String getValue() {
		return valueFromConfigFile;
	}

}
