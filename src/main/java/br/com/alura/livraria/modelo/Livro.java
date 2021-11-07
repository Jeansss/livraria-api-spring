package br.com.alura.livraria.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	@Column(name = "data")
	private LocalDate dataDeLancamento;

	private Integer paginas;

	@ManyToOne
	private Autor autor;
	
	public Livro(String titulo, LocalDate data, Integer paginas, Autor autor) {
		this.titulo = titulo;
		this.dataDeLancamento = data;
		this.paginas = paginas;
		this.autor = autor;
	}
	
	public void atualizarInformacoes(String titulo, Integer paginas, LocalDate data) {
		this.titulo = titulo;
		this.paginas = paginas;
		this.dataDeLancamento = data;
	}

}
