package br.com.alura.livraria.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livraria.dto.ItemLivrariaDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class LivroRepositoryTest {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	void deveriaRetornarRelatorioDeLivrosPublicados() {
		
		Autor a1 = new Autor("autor1", "jean@test.com", LocalDate.now());
		Autor a2 = new Autor("autor2", "maria@test.com", LocalDate.now());
		Autor a3 = new Autor("autor3", "joao@test.com", LocalDate.now());
		
		em.persist(a1);
		em.persist(a2);
		em.persist(a3);

		Livro l1 = new Livro("Livro de teste 1", LocalDate.now(), 150, a1 );
		Livro l2 = new Livro("Livro de teste 2", LocalDate.now(), 160, a1 );
		Livro l3 = new Livro("Livro de teste 3", LocalDate.now(), 170, a1 );
		
		Livro l4 = new Livro("Livro de teste 4", LocalDate.now(), 180, a2 );
		Livro l5 = new Livro("Livro de teste 5", LocalDate.now(), 190, a2 );
		Livro l6 = new Livro("Livro de teste 6", LocalDate.now(), 200, a2 );

		Livro l7 = new Livro("Livro de teste 7", LocalDate.now(), 210, a3 );
		
		em.persist(l1);
		em.persist(l2);
		em.persist(l3);
		em.persist(l4);
		em.persist(l5);
		em.persist(l6);
		em.persist(l7);
		
		List<ItemLivrariaDto> relatorio = livroRepository.relatorioLivrosPublicados();
		
		Assertions
		.assertThat(relatorio)
		.hasSize(3)
		.extracting(ItemLivrariaDto::getAutor, ItemLivrariaDto::getQuantidade, ItemLivrariaDto::getPercentual)
		.containsExactlyInAnyOrder(
				Assertions.tuple("autor3", 1l, new BigDecimal("14.29")),
				Assertions.tuple("autor2", 3l, new BigDecimal("42.86")),
				Assertions.tuple("autor1", 3l, new BigDecimal("42.86"))
				);
		
	}

	@Test
	void naoDeveriaRetornarRelatorioDeLivrosPublicados() {
		List<ItemLivrariaDto> relatorio = livroRepository.relatorioLivrosPublicados();
		
		Assertions
		.assertThat(relatorio)
		.isEmpty();
		
	}

}
