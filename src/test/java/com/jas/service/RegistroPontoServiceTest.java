//package com.jas.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.time.format.DateTimeParseException;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.jas.ControlePontoApplication;
//import com.jas.dto.Alocacao;
//import com.jas.dto.Momento;
//import com.jas.exception.GenericException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ControlePontoApplication.class)
//@TestPropertySource(value = "classpath:application-test.properties")
//class RegistroPontoServiceTest {
//
//	@Autowired
//	private RegistroPontoService service;
//
//	@Test
//	void alocarTempoMaior() {
//
//		GenericException exception = assertThrows(GenericException.class, () -> {
//			Alocacao al = new Alocacao();
//			al.setDia("2020-10-26");
//			al.setTempo("PT1H20M25S");
//			al.setNomeProjeto("JAS Projetos");
//			this.service.alocar(al);
//		});
//
//		assertEquals("Não pode alocar tempo maior que o tempo trabalhado no dia", exception.getMessage());
//	}
//
//	@Test
//	void saveObjetoMomentoDataInvalida() {
//
//		DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
//			Momento momento = new Momento();
//			momento.setDataHora("2020-10-27T");
//			this.service.save(momento);
//		});
//
//		assertEquals("Text '2020-10-27T' could not be parsed at index 11", exception.getMessage());
//	}
//
//	@Test
//	void saveObjetoMomentoFinalSemana() {
//
//		GenericException exception = assertThrows(GenericException.class, () -> {
//			Momento momento = new Momento();
//			momento.setDataHora("2020-10-24T08:26:06");
//			this.service.save(momento);
//		});
//
//		assertEquals("Sábado e domingo não são permitidos como dia de trabalho", exception.getMessage());
//	}
//
//	@Test
//	void saveObjetoMomentoBatidasDia() {
//		PopulatorRegistroPontoService.saveObjetoMomentoBatidasDia(this.service);
//		GenericException exception = assertThrows(GenericException.class, () -> {
//			Momento momento = new Momento();
//			momento.setDataHora("2020-10-28T18:26:06");
//			this.service.save(momento);
//		});
//
//		assertEquals("Apenas 4 horários podem ser registrados por dia", exception.getMessage());
//	}
//
//	@Test
//	void saveObjetoMomentoIntervaloMinimo1Hr() {
//		PopulatorRegistroPontoService.saveObjetoMomentoIntervaloMinimo1Hr(this.service);
//		GenericException exception = assertThrows(GenericException.class, () -> {
//			Momento momento = new Momento();
//			momento.setDataHora("2020-10-29T11:56:06");
//			this.service.save(momento);
//		});
//
//		assertEquals("Deve haver no mínimo 1 hora de almoço", exception.getMessage());
//	}
//
//	@Test
//	void saveObjetoMomentoDuplicarHr() {
//		PopulatorRegistroPontoService.saveObjetoMomentoDuplicarHr(this.service);
//		GenericException exception = assertThrows(GenericException.class, () -> {
//			Momento momento = new Momento();
//			momento.setDataHora("2020-10-30T07:00:00");
//			this.service.save(momento);
//		});
//
//		assertEquals("A batida deve ser com data e hora posterior a última. 2020-10-30T07:00", exception.getMessage());
//	}
//
//}
