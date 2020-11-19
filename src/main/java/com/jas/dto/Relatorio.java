package com.jas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Relatorio {

	private String mes;
	
	private String horasTrabalhadas;
	
	private String horasExcedentes;
	
	private String horasDevidas;
	
	private List<Registro> registros;
	
	private List<Alocacao> alocacaos;

	public Relatorio(String mes) {
		this.mes = mes;
	}

}
