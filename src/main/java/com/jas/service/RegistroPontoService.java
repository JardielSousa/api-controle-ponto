package com.jas.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.jas.dto.Momento;
import com.jas.model.RegistroPonto;
import com.jas.repository.RegistroPontoRepository;

@Service
public class RegistroPontoService extends ModelService<RegistroPonto, RegistroPontoRepository> {

	public RegistroPonto save(Momento momento) {
		String dataHora = momento.getDataHora();
		LocalDateTime dateTime = LocalDateTime.parse(dataHora);
		RegistroPonto r = new RegistroPonto(dateTime);
		RegistroPonto save = save(r);
		
		return save;
	}
}
