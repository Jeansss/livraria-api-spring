package br.com.alura.livraria.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.UsuarioDto;
import br.com.alura.livraria.dto.UsuarioFormDto;
import br.com.alura.livraria.modelo.Usuario;
import br.com.alura.livraria.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service // para dizer ao spring q essa classe é a service, que tme alguma lógica ou regra de negócio,  ai o spring consegue injetar essa classe no controller
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<UsuarioDto> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios
				.stream()
				.map(t -> modelMapper.map(t, UsuarioDto.class))
				.collect(Collectors.toList());
	}

	public UsuarioDto cadastrar(@Valid UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		
		String senha = new Random().nextInt(999999) + "";
		usuario.setSenha(bCryptPasswordEncoder.encode(senha));
		usuarioRepository.save(usuario);
		
		return modelMapper.map(usuario, UsuarioDto.class);
		
	}

}
