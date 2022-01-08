package com.varma.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varma.microservices.beans.Limits;
import com.varma.microservices.config.LimitsConfig;

@RestController
public class LimitsController {

	@Autowired
	LimitsConfig limitsConfig;
	
	@GetMapping(path="/limits")
	public Limits getLimits() {
		//return new Limits(1,1000);
		return new Limits(limitsConfig.getMinimum(),limitsConfig.getMaximum());
	}
}
