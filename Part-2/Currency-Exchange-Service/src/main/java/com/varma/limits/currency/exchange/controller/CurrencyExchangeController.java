package com.varma.limits.currency.exchange.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.varma.limits.currency.exchange.beans.CurrencyExchange;
import com.varma.limits.currency.exchange.dao.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

	
	private Logger logger=LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	CurrencyExchangeRepository currencExchangeRepository;
	
	@Autowired
	Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {
		//CurrencyExchange currencyExchange = new CurrencyExchange(1000L,from,to,BigDecimal.valueOf(50));
		
		logger.info("inside retrieve exchange : from "+from+" to : "+to);
		CurrencyExchange currencyExchange = currencExchangeRepository.findByFromAndTo(from,to);
		if(currencyExchange==null)
			throw new RuntimeException("Unable to fine currency data for from "+from+" to "+to);
		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		return currencyExchange;
	}
}
