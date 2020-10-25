package com.jas.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jas.model.RegistroPonto;
import com.jas.projection.BatidasDiaQtdMax;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {
	@Query("SELECT COUNT(*) AS qtd, MAX(dataHora) AS maiorData FROM RegistroPonto WHERE TO_CHAR(dataHora,'yyyy-MM-dd') = ?1")
	BatidasDiaQtdMax BatidasDiaQtdMax(String localDate);

	@Query("SELECT dataHora FROM RegistroPonto WHERE TO_CHAR(dataHora,'yyyy-MM-dd') = ?1 ORDER BY dataHora")
	List<LocalDateTime> BatidasDia(String localDate);
	
	@Query("SELECT r FROM RegistroPonto r WHERE TO_CHAR(r.dataHora,'yyyy-MM') = ?1")
	List<RegistroPonto> getRegistros(String mes);
}
