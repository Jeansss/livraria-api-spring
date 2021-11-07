package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
	
	@Mock
	private LivroRepository repository;
	
	@InjectMocks
	private LivroService service;

	@Test
	void deveriaCadastrarLivros() {
		LivroFormDto livro = new LivroFormDto("Livro de testes", LocalDate.now(), 150, 1l);
		
		LivroDto dto = service.cadastrar(livro);
		
		Mockito.verify(repository).save(Mockito.any());
		
		assertEquals(livro.getDataDeLancamento(), dto.getDataDeLancamento());
		assertEquals(livro.getPaginas(), dto.getPaginas());
		assertEquals(livro.getTitulo(), dto.getTitulo());
		
	}
	
	@Test
	void naoDeveriaCadastrarLivros() {		
		assertThrows(IllegalArgumentException.class, () -> service.cadastrar(null));
	}
	

}
