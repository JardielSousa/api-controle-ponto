package com.jas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jas.dto.Momento;
import com.jas.model.RegistroPonto;
import com.jas.service.RegistroPontoService;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class RegistroPontoController {

	@Autowired
	private RegistroPontoService service;
	
	@PostMapping("batidas")
	public ResponseEntity<RegistroPonto> batidas(@RequestBody @Valid Momento momento) {
		RegistroPonto registroPonto = this.service.save(momento);
		return new ResponseEntity<>(registroPonto, HttpStatus.CREATED);
	}
	
}
