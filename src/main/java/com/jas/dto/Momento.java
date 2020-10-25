package com.jas.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Momento {

	@NotNull(message = "dataHora não deve estar nulo")
	@NotEmpty(message = "dataHora não deve estar vazio")
	private String dataHora;
	
	public Momento() { }

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public String toString() {
		return "Momento [dataHora=" + dataHora + "]";
	}
	
}
