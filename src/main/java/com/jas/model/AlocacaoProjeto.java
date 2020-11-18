package com.jas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Data
@Entity
public class AlocacaoProjeto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate dia;

	private String hrAlocada;

	private String projeto;

}
