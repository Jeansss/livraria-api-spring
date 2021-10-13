package br.com.alura.livraria.dto;

import java.time.LocalDate;

import br.com.alura.livraria.modelo.Autor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {
	
	private Long id;
	private String titulo;
	private LocalDate dataDeLancamento;
	private Integer paginas;
//	private Autor autor;


}
