package br.com.alura.livraria.dto;

import br.com.alura.livraria.modelo.Autor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroDtoDetalhado extends LivroDto{
	
	private Autor autor;

}
