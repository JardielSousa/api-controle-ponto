package com.jas.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Registro {

	private LocalDate dia;
	
	private List<LocalTime> horarios;
	
	public Registro() { }
	
	public Registro(LocalDate dia, List<LocalTime> horarios) {
		this.dia = dia;
		this.horarios = horarios;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public List<LocalTime> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<LocalTime> horarios) {
		this.horarios = horarios;
	}
	
}
