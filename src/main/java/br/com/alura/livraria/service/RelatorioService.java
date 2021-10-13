package br.com.alura.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.ItemLivrariaDto;
import br.com.alura.livraria.repository.LivroRepository;
import lombok.Getter;
import lombok.Setter;

@Service
public class RelatorioService {

	@Autowired
	private LivroRepository repository;

	public List<ItemLivrariaDto> relatorioLivrosPublicados() {
		return repository.relatorioLivrosPublicados();
	}

}
