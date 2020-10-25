package com.jas.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Projeto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany
	@JoinColumn(name = "projeto_id")
	private List<RegistroPonto> pontos;
	
	public Projeto() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<RegistroPonto> getPontos() {
		return pontos;
	}

	public void setPontos(List<RegistroPonto> pontos) {
		this.pontos = pontos;
	}
	
}
