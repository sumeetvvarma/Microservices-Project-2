package com.varma.limits.currency.exchange.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varma.limits.currency.exchange.beans.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long>{

	CurrencyExchange findByFromAndTo(String from,String to);
}
