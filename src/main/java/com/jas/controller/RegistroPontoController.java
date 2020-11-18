package com.jas.controller;

import com.jas.dto.Alocacao;
import com.jas.dto.Mensagem;
import com.jas.dto.Momento;
import com.jas.dto.Relatorio;
import com.jas.service.RegistroPontoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class RegistroPontoController {

	private final RegistroPontoService service;
	
	@PostMapping(value = "batidas", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mensagem> batidas(@RequestBody @Valid Momento momento) {
		this.service.save(momento);
		return new ResponseEntity<>(new Mensagem("Horário registrado"), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "alocacoes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mensagem> alocacoes(@RequestBody @Valid Alocacao alocacao) {
		this.service.alocar(alocacao);
		return new ResponseEntity<>(new Mensagem("Horas alocadas ao projeto"), HttpStatus.CREATED);
	}
	
	/**
	 * INCOMPLETO
	 * @param mes
	 * @return
	 */
	@GetMapping("folhas-de-ponto/{mes}")
	public ResponseEntity<Relatorio> relatorio(@PathVariable String mes) {
		Relatorio relatorio = this.service.gerarRelatorio(mes);
		return ResponseEntity.ok(relatorio);
	}
	
}
