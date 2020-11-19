package com.jas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class Momento {

	@NotNull(message = "dataHora não deve estar nulo")
	@NotEmpty(message = "dataHora não deve estar vazio")
	private String dataHora;
	
	public Momento() {
		this.dataHora = "";
	}

}
