package com.jas.dto;

import java.util.List;

public class Relatorio {

	private String mes;
	
	private String horasTrabalhadas;
	
	private String horasExcedentes;
	
	private String horasDevidas;
	
	private List<Registro> registros;
	
	private List<Alocacao> alocacaos;
	
	public Relatorio() { }

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(String horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public String getHorasExcedentes() {
		return horasExcedentes;
	}

	public void setHorasExcedentes(String horasExcedentes) {
		this.horasExcedentes = horasExcedentes;
	}

	public String getHorasDevidas() {
		return horasDevidas;
	}

	public void setHorasDevidas(String horasDevidas) {
		this.horasDevidas = horasDevidas;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public List<Alocacao> getAlocacaos() {
		return alocacaos;
	}

	public void setAlocacaos(List<Alocacao> alocacaos) {
		this.alocacaos = alocacaos;
	}
	
}
