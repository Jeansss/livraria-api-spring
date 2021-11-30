package br.com.alura.livraria.infra;

public interface EnviadorDeEmail {
	
	void enviarEmail(String destinatario, String assunto, String mensagem);

}
