package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {
	
	@Mock
	private AutorRepository repository;
	
	@InjectMocks
	private AutorService autorService;

	@Test
	void deveriaCadastrarAutor() {
		AutorFormDto formDto = new AutorFormDto("autor1", "autor1@test.com", LocalDate.now());
		
		AutorDto dto = autorService.cadastrar(formDto);
		
		Mockito.verify(repository).save(Mockito.any());
		
		assertEquals(formDto.getNome(), dto.getNome());
		assertEquals(formDto.getEmail(), dto.getEmail());
		assertEquals(formDto.getData(), dto.getData());
	}
	
	@Test
	void naoDeveriaCadastrarAutor() {
		assertThrows(IllegalArgumentException.class, () -> autorService.cadastrar(null));
	}
	

}
