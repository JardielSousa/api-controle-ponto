package com.jas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RegistroPonto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime dataHora;
	
}
