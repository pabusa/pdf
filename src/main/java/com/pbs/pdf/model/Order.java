package com.pbs.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private String portfolioId;
	private String isin;
	private String description;
	private String operationType;
	private double volume;
	private double shares;
	
	public Order(String portfolioId, String isin, String description, String operationType, double volume, double shares) {
		this.portfolioId = portfolioId;
		this.isin = isin;
		this.description = description;
		this.operationType = operationType;
		this.volume = volume;
		this.shares = shares;
	}
}


