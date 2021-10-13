package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.com.alura.livraria.modelo.Autor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LivroFormDto {
	
	@NotBlank
	@Size(min=10, max=100, message= "Título precisa ter entre 10 e 100 caracteres")
	private String titulo;
	
	@PastOrPresent
	private LocalDate dataDeLancamento;
	
	@Min(value = 100, message = "O número de páginas precisa ser igual ou maior que 100")
	private int paginas;
		
	@JsonAlias("autor_id")
	private Long autorId;

}
