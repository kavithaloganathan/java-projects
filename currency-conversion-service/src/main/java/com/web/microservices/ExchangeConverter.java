package com.web.microservices;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeConverter {
	
	@Column(name="currency_from")
	private String from;
	
	@Column(name="currency_to")
	private String to;
	@Id
	private Long id;
	private BigDecimal conversionMultiple;
	private BigDecimal quantity;
	private BigDecimal totalCalculatedAmount;
	
	private int port;
	
	public BigDecimal getTotalCalculatedAmount() {
		return totalCalculatedAmount;
	}
	public void setTotalCalculatedAmount(BigDecimal totalCalculatedAmount) {
		this.totalCalculatedAmount = totalCalculatedAmount;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public ExchangeConverter(String from, String to, Long id, BigDecimal conversionMultiple, BigDecimal quantity, BigDecimal totalCalculatedAmount,
			int port) {
		this.from = from;
		this.to = to;
		this.id = id;
		this.conversionMultiple = conversionMultiple;
		this.quantity = quantity;
		this.port = port;
		this.totalCalculatedAmount=totalCalculatedAmount;
	}
	public ExchangeConverter() {
	}
	
	

}
