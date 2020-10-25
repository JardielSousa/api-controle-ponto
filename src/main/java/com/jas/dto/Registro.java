package com.jas.dto;

import java.util.List;

public class Registro {

	private String dia;
	
	private List<String> horarios;
	
	public Registro() { }

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public List<String> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<String> horarios) {
		this.horarios = horarios;
	}
	
}
