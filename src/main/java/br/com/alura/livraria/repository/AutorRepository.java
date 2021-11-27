package br.com.alura.livraria.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Usuario;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	

}
