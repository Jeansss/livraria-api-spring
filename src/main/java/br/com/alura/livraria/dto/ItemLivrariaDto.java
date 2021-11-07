package br.com.alura.livraria.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class ItemLivrariaDto {
	
	private String autor;
	private Long quantidade;
	private Long quantidadeTotal;
	private BigDecimal percentual;
	
	public ItemLivrariaDto (String autor, Long quantidade, Long quantidadeTotal) {
		this.autor = autor;
		this.quantidade = quantidade;
		this.percentual = new  BigDecimal(quantidade)
				.divide(new BigDecimal(quantidadeTotal), 4, RoundingMode.HALF_UP)
				.multiply(new BigDecimal("100"))
				.setScale(2)
			;
	}




}
