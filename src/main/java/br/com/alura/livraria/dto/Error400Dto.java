package br.com.alura.livraria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Error400Dto {
	
	private String campo;
	private String mensagem;
	
	

}
