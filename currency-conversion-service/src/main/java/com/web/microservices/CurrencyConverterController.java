package com.web.microservices;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConverterController {
	
	Logger log=LoggerFactory.getLogger(CurrencyConverterController.class);
	@Autowired
	Environment environment;
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;

	@GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public ExchangeConverter retrieveConvertedValue(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		Map<String,String> uriVariables=new HashMap();
		uriVariables.put("from",from);
		uriVariables.put("to",to);
		
		ResponseEntity<ExchangeConverter> responseEntity=new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", ExchangeConverter.class,uriVariables);
		ExchangeConverter body = responseEntity.getBody();
		return new ExchangeConverter(from,to,body.getId(),body.getConversionMultiple(),quantity,
				quantity.multiply(body.getConversionMultiple()),body.getPort());
	}
	

	@GetMapping("currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public ExchangeConverter retrieveFeignConvertedValue(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		try {
		ExchangeConverter body = proxy.retrieveExchangeValue(from, to);
		log.info("sfsdfdfdgfgd{}",body);
		String s=body.getFrom();
	//	ExchangeConverter body = responseEntity.getBody();
		return new ExchangeConverter(from,to,body.getId(),body.getConversionMultiple(),quantity,
				quantity.multiply(body.getConversionMultiple()),body.getPort());
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
