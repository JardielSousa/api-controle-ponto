package com.jas.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AlocacaoProjeto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dia;
	
	private String hrAlocada;
	
	private String projeto;
	
	public AlocacaoProjeto() { }
	
	public AlocacaoProjeto(LocalDate dia, String hrAlocada, String projeto) {
		this.dia = dia;
		this.hrAlocada = hrAlocada;
		this.projeto = projeto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public String getHrAlocada() {
		return hrAlocada;
	}

	public void setHrAlocada(String hrAlocada) {
		this.hrAlocada = hrAlocada;
	}

	public String getProjeto() {
		return projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}
	
}
