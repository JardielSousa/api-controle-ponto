package com.jas.service;

import java.time.LocalDateTime;

import com.jas.entity.RegistroPonto;

public class PopulatorRegistroPontoService {

	public static void saveObjetoMomentoBatidasDia(RegistroPontoService service) {

		RegistroPonto ponto1 = new RegistroPonto(null, LocalDateTime.parse("2020-10-28T07:00:00"));
		RegistroPonto ponto2 = new RegistroPonto(null, LocalDateTime.parse("2020-10-28T11:00:00"));
		RegistroPonto ponto3 = new RegistroPonto(null, LocalDateTime.parse("2020-10-28T13:00:00"));
		RegistroPonto ponto4 = new RegistroPonto(null, LocalDateTime.parse("2020-10-28T17:00:00"));
		
		service.save(ponto1);
		service.save(ponto2);
		service.save(ponto3);
		service.save(ponto4);
	}
	
	public static void saveObjetoMomentoIntervaloMinimo1Hr(RegistroPontoService service) {
		
		RegistroPonto ponto1 = new RegistroPonto(null, LocalDateTime.parse("2020-10-29T07:00:00"));
		RegistroPonto ponto2 = new RegistroPonto(null, LocalDateTime.parse("2020-10-29T11:00:00"));
		
		service.save(ponto1);
		service.save(ponto2);
	}
	
	public static void saveObjetoMomentoDuplicarHr(RegistroPontoService service) {
		RegistroPonto ponto1 = new RegistroPonto(null, LocalDateTime.parse("2020-10-30T07:00:00"));
		service.save(ponto1);
	}
	
}
