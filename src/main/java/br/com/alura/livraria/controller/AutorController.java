package br.com.alura.livraria.controller;

import br.com.alura.livraria.dto.AutorAtualizacaoFormDto;
import br.com.alura.livraria.dto.AutorDetalhadoDto;
import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.modelo.Usuario;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.service.AutorService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorService service;

	@GetMapping
	public Page<AutorDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
		return service.listar(paginacao);
	}

	@PostMapping
	public ResponseEntity<AutorDto> cadastrar(@RequestBody @Valid AutorFormDto dto, UriComponentsBuilder uriBuilder) {
		AutorDto autorDto = service.cadastrar(dto);
		URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autorDto.getId()).toUri();
		return ResponseEntity.created(uri).body(autorDto);

	}
	
	@PutMapping
	public ResponseEntity<AutorDto> atualizar(@RequestBody @Valid AutorAtualizacaoFormDto dto) {
		AutorDto atualizada = service.atualizar(dto);
		return ResponseEntity.ok(atualizada);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AutorDto> deletar(@PathVariable @NotNull Long id) {
		service.remover(id);
		return ResponseEntity.noContent().build();

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AutorDetalhadoDto> detalhar(@PathVariable @NotNull Long id) {
		AutorDetalhadoDto dto = service.detalhar(id);
		return ResponseEntity.ok(dto);

	}

}
