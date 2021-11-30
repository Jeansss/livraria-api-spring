package br.com.alura.livraria.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@Profile({"default", "test"})
public class EnviadorDeEmailFake implements EnviadorDeEmail{
	

	@Override
	public void enviarEmail(String destinatario, String assunto, String mensagem) {
		System.out.println("ENVIANDO EMAIL: ");
		System.out.println("Destinatário: " + destinatario );
		System.out.println("Assunto: " + assunto );
		System.out.println("Mensagem: " + mensagem );
	}

}
