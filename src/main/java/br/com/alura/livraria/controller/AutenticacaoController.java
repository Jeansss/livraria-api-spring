package br.com.alura.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.dto.LoginFormDto;
import br.com.alura.livraria.dto.TokenDto;
import br.com.alura.livraria.infra.security.AutenticacaoService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AutenticacaoService service;
	
	@PostMapping
	public TokenDto autenticar(@RequestBody @Valid LoginFormDto dto) { //O Front end vai disparar uma request post para o /auth e vai trazer no corpo da request o login e senha que estamos representando no dto
		return new TokenDto(service.autenticar(dto));
		
	
	}

}
