package br.com.alura.livraria.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AutorControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AutorRepository repository;

	@Test
	void naoDeveriaCadastrarAutor() throws Exception {
		String json = "{}";
		mvc.perform(MockMvcRequestBuilders
				.post("/autores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void deveriaCadastrarAutor() throws Exception {
		String json = "{\"nome\":\"Jean\",\"email\":\"test@jean.com\",\"data\":\"2019-02-15\"}";
		
		mvc.perform(MockMvcRequestBuilders
				.post("/autores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.header().exists("Location"))
				.andExpect(MockMvcResultMatchers.content().json(json));
	}
	
	@Test
	void deveriaListarAutores() throws Exception {
		Autor a1 = new Autor("jean", "jean@test.com", LocalDate.now());
		repository.save(a1);
		
		Long autorId = repository.findAll().get(0).getId();
		
		String json = "{\"content\":[{\"id\":" + autorId + ",\"nome\":\"jean\",\"email\":\"jean@test.com\",\"data\":\"2021-11-04\"}],\"pageable\":{\"sort\":{\"sorted\":false,\"empty\":true,\"unsorted\":true},\"pageNumber\":0,\"pageSize\":10,\"offset\":0,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"totalElements\":1,\"sort\":{\"sorted\":false,\"empty\":true,\"unsorted\":true},\"numberOfElements\":1,\"size\":10,\"number\":0,\"first\":true,\"empty\":false}";
		
		mvc.perform(MockMvcRequestBuilders
				.get("/autores"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().json(json));
	}

}
