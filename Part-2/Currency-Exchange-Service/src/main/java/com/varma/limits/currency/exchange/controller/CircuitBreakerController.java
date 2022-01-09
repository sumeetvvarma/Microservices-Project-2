package com.varma.limits.currency.exchange.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakerController {

	private Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);
	
	//@Retry(name="default") // 3 times retry
	//@Retry(name="sample-api",fallbackMethod="defaultMethod") 
	//@CircuitBreaker(name="default",fallbackMethod="defaultMethod")
	@GetMapping("/sample-api")
	//@RateLimiter(name="default")
	@Bulkhead(name="default")
	public String sampleApi() {
		logger.info("sample api called ");
		//ResponseEntity<String> responseEntity=new RestTemplate().getForEntity("http://localhost:8080/something",String.class);
		//return responseEntity.getBody();
		return "sameple";
		
	}
	
	public String defaultMethod(Throwable t) {
		return "default fall back response";
	}
}
