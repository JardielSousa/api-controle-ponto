package com.jas.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jas.dto.Alocacao;
import com.jas.dto.Momento;
import com.jas.dto.Registro;
import com.jas.dto.Relatorio;
import com.jas.exception.GenericException;
import com.jas.entity.AlocacaoProjeto;
import com.jas.entity.RegistroPonto;
import com.jas.projection.BatidasDiaQtdMax;
import com.jas.repository.RegistroPontoRepository;

@Service
public class RegistroPontoService extends ModelService<RegistroPonto, RegistroPontoRepository> {

	private static final Integer QTD_BATIDAS_DIA = 4;
	private static final Integer QTD_ALMOCO_DIA = 1;
	
	private AlocacaoProjetoService alocacaoProjetoService;

	public RegistroPontoService(RegistroPontoRepository repository, AlocacaoProjetoService alocacaoProjetoService) {
		super(repository);
		this.alocacaoProjetoService = alocacaoProjetoService;
	}

	public RegistroPonto save(Momento momento) {
		String dataHora = momento.getDataHora();
		LocalDateTime dateTime = LocalDateTime.parse(dataHora);
		validarData(dateTime);
		RegistroPonto r = new RegistroPonto(null, dateTime);
		RegistroPonto save = save(r);
		
		return save;
	}

	public void alocar(Alocacao alocacao) {
		Duration horasTrabalhasDia = getHorasTrabalhasDia(alocacao.getDia());	
		Duration tempoAlocacao = Duration.parse(alocacao.getTempo());

		LocalDate dia = LocalDate.parse(alocacao.getDia());
		Duration horasAlocadas = this.alocacaoProjetoService.horasAlocadas(dia);
		Duration durationLivreParaAlocacao = horasTrabalhasDia.minus(horasAlocadas);
		
		int compareTo = durationLivreParaAlocacao.compareTo(tempoAlocacao);
		if (compareTo == -1) {
			throw new GenericException("Não pode alocar tempo maior que o tempo trabalhado no dia");
		}

		AlocacaoProjeto aloc = new AlocacaoProjeto(null, dia, tempoAlocacao.toString(), alocacao.getNomeProjeto());
		this.alocacaoProjetoService.save(aloc);
	}

	public Relatorio gerarRelatorio(String mes) {
		Relatorio relatorio = new Relatorio(mes);
		List<RegistroPonto> registroPontos = this.repository.getRegistros(mes);
		
		String horasTrabalhadas = getHorasTrabalhaadasRelatorio(registroPontos);
		relatorio.setHorasTrabalhadas(horasTrabalhadas);
		
		List<Registro> registros = getRegistrosRelatorio(registroPontos);
		relatorio.setRegistros(registros);
		
		return relatorio;
	}

	/* ************************************************* */
	/* ************ MÉTODOS AUXILIARES ***************** */
	/* ************************************************* */

	private void validarData(LocalDateTime dateTime) {
		validarFinalSemana(dateTime.getDayOfWeek());
		BatidasDiaQtdMax batidasDia = this.repository.BatidasDiaQtdMax(dateTime.toLocalDate().toString());
		validarQtdDia(batidasDia.getQtd());
		validarBatidaDiaAposUltima(batidasDia.getMaiorData(), dateTime);
		validarHorasTrabaladasIntervaloAlmoco(dateTime.toLocalDate().toString(), batidasDia.getQtd(), dateTime);
	}

	private void validarFinalSemana(DayOfWeek dayOfWeek) {
		boolean isFinalSenama = dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
		if (isFinalSenama) {
			throw new GenericException("Sábado e domingo não são permitidos como dia de trabalho");
		}
	}

	private void validarQtdDia(Long qtd) {
		if (qtd >= QTD_BATIDAS_DIA) {
			String message = String.format("Apenas %d horários podem ser registrados por dia", QTD_BATIDAS_DIA);
			throw new GenericException(message);
		}
	}
	
	private void validarBatidaDiaAposUltima(LocalDateTime maiorData, LocalDateTime dateTime) {
		if (maiorData != null && maiorData.isBefore(dateTime) == false) {
			String message = String.format("A batida deve ser com data e hora posterior a última. %s", maiorData);
			throw new GenericException(message);
		}
	}
	
	private void validarHorasTrabaladasIntervaloAlmoco(String localDate, Long qtdBatidas, LocalDateTime dateTime) {
		if (qtdBatidas > 0) {
			Duration intervaloAlmoco = getIntervaloAlmoco(localDate, dateTime);

			int compareToHrAlomoco = intervaloAlmoco.compareTo(Duration.ofHours(QTD_ALMOCO_DIA));
			if (qtdBatidas > 1 && compareToHrAlomoco == -1) {
				String message = String.format("Deve haver no mínimo %d hora de almoço", QTD_ALMOCO_DIA);
				throw new GenericException(message);
			}
		}
		
	}

	private Duration getIntervaloAlmoco(String localDate, LocalDateTime dateTime) {
		LocalDateTime atual = null;
		LocalDateTime anterior = null;
		Duration intervaloAlmoco = Duration.ZERO;
		
		List<LocalDateTime> batidasDia = this.repository.BatidasDia(localDate);
		for (int i = 0; i < batidasDia.size(); i++) {
			atual = batidasDia.get(i);
			if (anterior != null && i % 2 == 0) {
				intervaloAlmoco = intervaloAlmoco.plus(Duration.between(anterior, atual));
			}
			
			anterior = batidasDia.get(i);
		}
		
		if (batidasDia.size() % 2 == 0) {
			intervaloAlmoco = intervaloAlmoco.plus(Duration.between(anterior, dateTime));
		}
		
		return intervaloAlmoco;
	}
	
	private Duration getHorasTrabalhasDia(String localDate) {
		LocalDateTime atual = null;
		LocalDateTime anterior = null;
		Duration hrTrabalhada = Duration.ZERO;
		
		List<LocalDateTime> batidasDia = this.repository.BatidasDia(localDate);
		for (int i = 0; i < batidasDia.size(); i++) {
			atual = batidasDia.get(i);
			if (anterior != null && i % 2 != 0) {
				hrTrabalhada = hrTrabalhada.plus(Duration.between(anterior, atual));
			}
			
			anterior = batidasDia.get(i);
		}
		
		return hrTrabalhada;
	}

	private String getHorasTrabalhaadasRelatorio(List<RegistroPonto> registroPontos) {
		Duration duration = Duration.ZERO;
		LocalDateTime anterior = null;
		List<LocalDateTime> list = registroPontos.stream().map(RegistroPonto::getDataHora).collect(Collectors.toUnmodifiableList());
		for (int i = 0; i < list.size(); i++) {
			if (anterior != null && i % 2 != 0) {
				duration = duration.plus(Duration.between(anterior, list.get(i)));
			}
			
			anterior = list.get(i);
		}
		
		return duration.toString();
	}

	private List<Registro> getRegistrosRelatorio(List<RegistroPonto> registroPontos) {
		Map<LocalDate, List<LocalTime>> collect = registroPontos.stream().collect(Collectors.groupingBy(ldt -> ldt.getDataHora().toLocalDate(), Collectors.mapping(ldt -> ldt.getDataHora().toLocalTime(), Collectors.toList())));
		List<Registro> registros = collect.entrySet().stream().map(en -> new Registro(en.getKey(), en.getValue())).collect(Collectors.toList());
		return registros;
	}

}
