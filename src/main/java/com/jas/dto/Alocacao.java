package com.jas.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
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

}
