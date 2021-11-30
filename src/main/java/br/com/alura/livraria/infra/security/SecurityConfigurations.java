package br.com.alura.livraria.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.livraria.repository.UsuarioRepository;


@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //sobrescrevendo esse método nos desabilitamos a proteção do csrf token para poder fazer requests de post,put.. mas mantém as outras configs
		http
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers("/usuarios/**").hasRole("ADMIN")
		.anyRequest().authenticated() //todas requestes tem de ser autenticadas
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //dizendo ao spring que o gerenciamento de sessao deve ser o statless, ou seja quando alguem fizer login nao é pra guardr jsession id nem sessao do lado dos server
		.and().csrf().disable() //agora nós teremos de criar um controller por conta dessa linha de cima q nao é mais a config padrao do spring, e antes ele controlava sozinho a request.
		.addFilterBefore(
				new VerificacaoTokenFilter(tokenService, usuarioRepository), //adicionando a classe Filter pois nao existe notacao para o spring carrega la
				UsernamePasswordAuthenticationFilter.class); // o metodo addFilterBefore tem dois params, que é o filtro q eu quero primeiro e o segundo é o filtro que eu quero por ultimo
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception { 
		web
		.ignoring()
		.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**");
	}
	

}

