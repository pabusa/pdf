package com.pbs.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disclaimer {
	int id;
	String descripcion;
	
	public Disclaimer(int i, String descripcion) {
		this.id = i;
		this.descripcion = descripcion;
	}
	
}
