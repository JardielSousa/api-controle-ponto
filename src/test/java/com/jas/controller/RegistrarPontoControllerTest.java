package com.jas.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jas.ControlePontoApplication;
import com.jas.dto.Alocacao;
import com.jas.dto.Momento;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControlePontoApplication.class)
@TestPropertySource(value = "classpath:application-test.properties")
@AutoConfigureMockMvc
class RegistrarPontoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private RegistroPontoController controller;

	@Test
	void batidasMomentoVazio() throws Exception {
		String content = this.objectMapper.writeValueAsString(new Momento());
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("{\"mensagem\":\"dataHora nÃ£o deve estar vazio\"}"));
	}
	
	@Test
	void batidasMomentoCompleto() throws Exception {
		Momento m = new Momento();
		m.setDataHora("2020-10-26T07:20:40");
		String content = this.objectMapper.writeValueAsString(m);
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isCreated())
		.andExpect(content().string("{\"mensagem\":\"HorÃ¡rio registrado\"}"));
	}
	
	@Test
	void batidasMomento4PorDia() throws Exception {
		Momento m = new Momento();
		m.setDataHora("2020-10-27T07:20:40");
		String content = this.objectMapper.writeValueAsString(m);
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isCreated())
		.andExpect(content().string("{\"mensagem\":\"HorÃ¡rio registrado\"}"));

		m.setDataHora("2020-10-27T11:20:40");
		content = this.objectMapper.writeValueAsString(m);
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isCreated())
		.andExpect(content().string("{\"mensagem\":\"HorÃ¡rio registrado\"}"));
		
		m.setDataHora("2020-10-27T13:20:40");
		content = this.objectMapper.writeValueAsString(m);
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isCreated())
		.andExpect(content().string("{\"mensagem\":\"HorÃ¡rio registrado\"}"));
		
		m.setDataHora("2020-10-27T17:20:40");
		content = this.objectMapper.writeValueAsString(m);
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isCreated())
		.andExpect(content().string("{\"mensagem\":\"HorÃ¡rio registrado\"}"));
		
		m.setDataHora("2020-10-27T19:20:40");
		content = this.objectMapper.writeValueAsString(m);
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"mensagem\":\"Apenas 4 horÃ¡rios podem ser registrados por dia\"}"));
	}
	
	@Test
	void batidasMomentoIntervalo1Hr() throws Exception {
		Momento m = new Momento();
		m.setDataHora("2020-10-28T07:20:40");
		String content = this.objectMapper.writeValueAsString(m);
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isCreated())
		.andExpect(content().string("{\"mensagem\":\"HorÃ¡rio registrado\"}"));
		
		m.setDataHora("2020-10-28T11:20:40");
		content = this.objectMapper.writeValueAsString(m);
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isCreated())
		.andExpect(content().string("{\"mensagem\":\"HorÃ¡rio registrado\"}"));
		
		m.setDataHora("2020-10-28T12:00:20");
		content = this.objectMapper.writeValueAsString(m);
		this.mockMvc.perform(post("/batidas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"mensagem\":\"Deve haver no mÃ­nimo 1 hora de almoÃ§o\"}"));
		
	}

	@Test
	void alocacoesObjetoVazio() throws Exception {
		Alocacao al = new Alocacao();
		String content = this.objectMapper.writeValueAsString(al);
		this.mockMvc.perform(post("/alocacoes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"mensagem\":\"dia nÃ£o deve estar vazio\"}"));
	}
}
