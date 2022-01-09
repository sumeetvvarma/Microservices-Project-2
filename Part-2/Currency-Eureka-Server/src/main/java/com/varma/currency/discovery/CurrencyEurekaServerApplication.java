package com.varma.currency.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CurrencyEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyEurekaServerApplication.class, args);
	}

}
