package com.jas.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Alocacao {
	
	@NotNull(message = "dia não deve ser nulo")
	@NotEmpty(message = "dia não deve estar vazio")
	private String dia;
	
	@NotNull(message = "tempo não deve ser nulo")
	@NotEmpty(message = "tempo não deve estar vazio")
	private String tempo;
	
	@NotNull(message = "nomeProjeto não deve ser nulo")
	@NotEmpty(message = "nomeProjeto não deve estar vazio")
	private String nomeProjeto;
	
	public Alocacao() {
		this.dia = "";
		this.tempo = "";
		this.nomeProjeto = "";
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	@Override
	public String toString() {
		return "Alocacao [dia=" + dia + ", tempo=" + tempo + ", nomeProjeto=" + nomeProjeto + "]";
	}
	
}
