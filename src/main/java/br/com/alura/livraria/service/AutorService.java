package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AutorAtualizacaoFormDto;
import br.com.alura.livraria.dto.AutorDetalhadoDto;
import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	private ModelMapper modelMapper = new ModelMapper();

	public Page<AutorDto> listar(Pageable paginacao) {
		Page<Autor> autores = autorRepository.findAll(paginacao);
		return autores.map(t -> modelMapper.map(t, AutorDto.class));
	}

	@Transactional
	public AutorDto cadastrar(@Valid AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		autor.setId(null);
		autorRepository.save(autor);

		return modelMapper.map(autor, AutorDto.class);
	}
	
	@Transactional
	public AutorDto atualizar(@Valid AutorAtualizacaoFormDto dto) {
		Autor autor = autorRepository.getById(dto.getId());

		autor.atualizarInformacoes(dto.getNome(), dto.getEmail(), dto.getData());
		
		return modelMapper.map(autor, AutorDto.class);
	}
	
	@Transactional
	public void remover(@NotNull Long id) {
		autorRepository.deleteById(id);
	}
	
	public AutorDetalhadoDto detalhar(@NotNull Long id) {
		Autor autor = autorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(autor, AutorDetalhadoDto.class);
		
	}

}
