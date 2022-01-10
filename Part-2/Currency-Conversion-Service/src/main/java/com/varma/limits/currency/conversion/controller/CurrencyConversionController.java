package com.varma.limits.currency.conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.varma.limits.currency.conversion.beans.CurrencyConversion;
import com.varma.limits.currency.conversion.service.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {

	private Logger logger=LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	CurrencyExchangeProxy currencyExchangeProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		//return new CurrencyConversion();
		logger.info("inside calculateCurrencyConversion");
		HashMap<String,String> uriVariables=new HashMap<>();
		uriVariables.put("from",from);
		uriVariables.put("to",to);
		
		//ResponseEntity<CurrencyConversion> responseEntity=new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversion.class,uriVariables);
		ResponseEntity<CurrencyConversion> responseEntity=new RestTemplate()
				.getForEntity("http://192.168.99.100:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversion.class,uriVariables);
		CurrencyConversion currencyConversion=responseEntity.getBody();
		
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,
				currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+ " - RestTemplate");
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		//return new CurrencyConversion();
		logger.info("inside calculateCurrencyConversionFeign");
		CurrencyConversion currencyConversion=currencyExchangeProxy.retrieveExchangeValue(from,to);
		
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,
				currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+" - FeignClient");
	}
}
