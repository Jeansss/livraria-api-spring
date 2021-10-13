package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.LivroRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	private ModelMapper modelMapper = new ModelMapper();

	public Page<LivroDto> listar(Pageable paginacao) {
		Page<Livro> livros = livroRepository.findAll(paginacao);

		return livros.map(t -> modelMapper.map(t, LivroDto.class));
	}

	@Transactional
	public LivroDto cadastrar(@Valid LivroFormDto dto) {
		Livro livro = modelMapper.map(dto, Livro.class);
		livro.setId(null);
		livroRepository.save(livro);

		return modelMapper.map(livro, LivroDto.class);
	}

}
