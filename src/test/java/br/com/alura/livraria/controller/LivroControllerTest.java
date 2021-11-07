package br.com.alura.livraria.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.service.AutorService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class LivroControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AutorRepository	autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	@Test
	void naoDeveriaCadastrarLivroComDadosIncompletos() throws Exception {
		String json = "{}";
		mvc.perform(MockMvcRequestBuilders
				.post("/livros")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void deveriaCadastrarLivro() throws Exception {
		AutorFormDto formDto = new AutorFormDto("autor1", "autor1@test.com", LocalDate.now());
		Autor autor = modelMapper.map(formDto, Autor.class);
		autorRepository.save(autor);
		
		Long idAutor = autorRepository.findAll().get(0).getId();
		
		String jsonRetorno = "{\"titulo\":\"Livro de teste 7\",\"dataDeLancamento\":\"2020-02-15\",\"paginas\":140}";

		
		String json = "{\"titulo\":\"Livro de teste 7\",\"dataDeLancamento\":\"2020-02-15\",\"paginas\":140,\"autor_id\":" + idAutor + "}";
		mvc.perform(MockMvcRequestBuilders
				.post("/livros")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.header().exists("Location"))
				.andExpect(MockMvcResultMatchers.content().json(jsonRetorno));
	}

}
