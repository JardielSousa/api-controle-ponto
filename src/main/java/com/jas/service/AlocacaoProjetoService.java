package com.jas.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jas.model.AlocacaoProjeto;
import com.jas.repository.AlocacaoProjetoRepository;

@Service
public class AlocacaoProjetoService extends ModelService<AlocacaoProjeto, AlocacaoProjetoRepository> {

	public Duration horasAlocadas(LocalDate localDate) {
		List<String> horasAlocadas = this.repository.horasAlocadas(localDate);
		Duration duration = horasAlocadas.stream().map(Duration::parse).reduce(Duration.ZERO, Duration::plus);
		
		return duration;
	}

}
