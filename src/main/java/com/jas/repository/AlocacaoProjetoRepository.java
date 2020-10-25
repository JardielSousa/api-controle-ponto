package com.jas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jas.model.AlocacaoProjeto;

public interface AlocacaoProjetoRepository extends JpaRepository<AlocacaoProjeto, Long> {

	@Query("SELECT hrAlocada FROM AlocacaoProjeto WHERE dia = ?1")
	List<String> horasAlocadas(LocalDate localDate);
	
}
