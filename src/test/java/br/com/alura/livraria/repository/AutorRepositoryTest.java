package br.com.alura.livraria.repository;

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

import br.com.alura.livraria.modelo.Autor;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AutorRepositoryTest {
	
	@Autowired
	private AutorRepository repository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	void deveriaRetornarAutores() {
		Autor a1 = new Autor("jeans teste", "jean@test.com", LocalDate.now());
		em.persist(a1);
		
		Autor a2 = new Autor("maria teste", "maria@test.com", LocalDate.now());
		em.persist(a2);

	
		List<Autor> autores = repository.findAll();
		
		Assertions
		.assertThat(autores)
	    .hasSize(2)
	    .extracting(Autor::getNome, Autor::getEmail, Autor::getData)
	    .containsExactly(
	    		Assertions.tuple("jeans teste", "jean@test.com", LocalDate.now()),
	    		Assertions.tuple("maria teste", "maria@test.com", LocalDate.now())
	    		);
	}
	
	@Test
	void naoDeveriaRetornarAutor() {
		List<Autor> autores = repository.findAll();
		
		Assertions
		.assertThat(autores)
	    .isNullOrEmpty();
	}

}
